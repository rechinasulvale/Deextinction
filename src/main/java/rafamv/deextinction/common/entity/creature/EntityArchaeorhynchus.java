package rafamv.deextinction.common.entity.creature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.animations.ControlledAnimation;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Archaeorhynchus;
import rafamv.deextinction.common.entity.ai.DEAIEatDroppedItem;
import rafamv.deextinction.common.entity.ai.DEAIEggNestFinder;
import rafamv.deextinction.common.entity.ai.DEAIEggNestLaying;
import rafamv.deextinction.common.entity.ai.DEAIFollowFood;
import rafamv.deextinction.common.entity.ai.DEAIFollowParent;
import rafamv.deextinction.common.entity.ai.DEAIMating;
import rafamv.deextinction.common.entity.ai.DEAIPlayerMountingSocialization;
import rafamv.deextinction.common.entity.ai.DEAISittingNatural;
import rafamv.deextinction.common.entity.ai.DEAIWander;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedFlyingCoward;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityArchaeorhynchus extends EntityDeExtinctedFlyingCoward
{
	private ControlledAnimation sittingAnimation = new ControlledAnimation(30);
	private DEAIFollowParent aiFollowParent;
	private DEAIEggNestFinder aiEggNestFinder;
	private DEAIEggNestLaying aiEggNestLaying;
	private DEAIMating aiMating;

	protected static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityArchaeorhynchus.foodList.add(Items.apple);
		EntityArchaeorhynchus.foodList.add(Items.wheat_seeds);
		EntityArchaeorhynchus.foodList.add(Items.melon_seeds);
		EntityArchaeorhynchus.foodList.add(Items.melon);
		EntityArchaeorhynchus.foodList.add(Items.cookie);
	}

	public EntityArchaeorhynchus(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new DEAISittingNatural(this, 60, 2000, 500));
		this.tasks.addTask(3, new DEAIFollowFood(this, 30, this.foodList));
		this.tasks.addTask(4, new DEAIEatDroppedItem(this, 40, 0.5D, 12.0D, this.foodList));
		this.tasks.addTask(5, new DEAIWander(this));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 4.0F, 0.05F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(10, new DEAIPlayerMountingSocialization(this, 150));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote)
		{
			this.sittingAnimation.update();

			if (this.isSitting())
				this.sittingAnimation.runAnimation();
			else
				this.sittingAnimation.stopAnimation();
		}
	}

	@Override
	protected void eatingSpecialEvent(EntityPlayer player, ItemStack itemstack)
	{
		if (player.riddenByEntity == null && this.isIndependent() && this.hunger > (int) (0.5F * this.getMaxHunger()) && this.rand.nextInt(3) == 0 && !this.isSitting() && !this.isFlying() && !this.isOnNest() && !this.isSleeping())
		{
			this.creatureTarget = player;
			this.setSocializing(true);
			this.jump();
		}
	}

	@Override
	public void updateRidden()
	{
		super.updateRidden();

		if (this.ridingEntity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.ridingEntity;
			double distance = 0.4D;
			double offsetX = distance * MathHelper.cos(0.01745329251F * player.renderYawOffset);
			double offsetZ = distance * MathHelper.sin(0.01745329251F * player.renderYawOffset);

			this.setPosition(this.posX - offsetX, this.posY + 0.15D, this.posZ - offsetZ);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getSittingProgress(float partialRenderTicks)
	{
		return this.sittingAnimation.getAnimationProgressSinSqrt(partialRenderTicks);
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Archaeorhynchus.NAME);
	}

	@Override
	protected void updateAIForGrowthStage(byte stage)
	{
		if (this.aiFollowParent == null)
			this.aiFollowParent = new DEAIFollowParent(this, 30);

		if (this.aiEggNestFinder == null)
			this.aiEggNestFinder = new DEAIEggNestFinder(this, 300, 12.0D, 0.8D);

		if (this.aiEggNestLaying == null)
			this.aiEggNestLaying = new DEAIEggNestLaying(this, 1500);

		if (this.aiMating == null)
			this.aiMating = new DEAIMating(this, 18000, 600, 12.0D, 2.5D);

		this.tasks.removeTask(this.aiFollowParent);
		this.tasks.removeTask(this.aiEggNestFinder);
		this.tasks.removeTask(this.aiEggNestLaying);
		this.tasks.removeTask(this.aiMating);

		if (this.isIndependent())
		{
			if (this.isFemale())
			{
				this.tasks.addTask(1, this.aiEggNestFinder);
				this.tasks.addTask(9, this.aiEggNestLaying);
				this.tasks.addTask(11, this.aiMating);
			}
		}
		else
			this.tasks.addTask(6, this.aiFollowParent);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 0;
	}

	@Override
	public List<Item> getCreatureFoodList()
	{
		return this.foodList;
	}

	@Override
	public int getMaxHunger()
	{
		return 150;
	}

	@Override
	public int getTalkInterval()
	{
		return 240;
	}

	@Override
	protected String getLivingSound()
	{
		return DeExtinction.prependModID("archaeorhynchus_spathula");
	}

	@Override
	protected float getSoundPitch()
	{
		if (this.isIndependent())
			return 1.0F + 0.2F * (this.rand.nextFloat() - this.rand.nextFloat());
		else
			return 1.5F + 0.3F * (this.rand.nextFloat() - this.rand.nextFloat());
	}

	@Override
	protected float getSoundVolume()
	{
		if (this.isIndependent())
			return 1.0F + 0.15F * (this.rand.nextFloat() - this.rand.nextFloat());
		else
			return 0.8F + 0.15F * (this.rand.nextFloat() - this.rand.nextFloat());
	}

	@Override
	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("mob.chicken.step", 0.1F, 1.0F);
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return 1.2F * this.height;
	}

	@Override
	protected float getMaxHeight()
	{
		return 0.6F;
	}

	@Override
	protected float getMaxWidth()
	{
		return 0.6F;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting)
	{
		if (this.isIndependent())
		{
			this.dropItemWithOffset(DEItemRegistry.archaeorhynchus_feather, 2, 0.8F * this.height);
			if (this.isBurning())
				this.dropItemWithOffset(DEItemRegistry.archaeorhynchus_cooked, this.rand.nextInt(2), 0.8F * this.height);
			else
				this.dropItemWithOffset(DEItemRegistry.archaeorhynchus_raw, this.rand.nextInt(2), 0.8F * this.height);
		}
	}
}
