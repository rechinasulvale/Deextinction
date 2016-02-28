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
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.animations.ControlledAnimation;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Aepyornis;
import rafamv.deextinction.common.entity.ai.DEAIAttackOnCollideUntilTime;
import rafamv.deextinction.common.entity.ai.DEAIEatDroppedItem;
import rafamv.deextinction.common.entity.ai.DEAIEggNestFinder;
import rafamv.deextinction.common.entity.ai.DEAIEggNestLaying;
import rafamv.deextinction.common.entity.ai.DEAIFollowFood;
import rafamv.deextinction.common.entity.ai.DEAIFollowParent;
import rafamv.deextinction.common.entity.ai.DEAIMating;
import rafamv.deextinction.common.entity.ai.DEAIPanic;
import rafamv.deextinction.common.entity.ai.DEAISittingNatural;
import rafamv.deextinction.common.entity.ai.DEAIWander;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedProtective;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityAepyornis extends EntityDeExtinctedProtective
{
	private ControlledAnimation sittingAnimation = new ControlledAnimation(30);

	protected static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityAepyornis.foodList.add(Items.apple);
		EntityAepyornis.foodList.add(Items.melon);
		EntityAepyornis.foodList.add(Items.melon_seeds);
		EntityAepyornis.foodList.add(Items.bread);
		EntityAepyornis.foodList.add(Items.wheat);
		EntityAepyornis.foodList.add(Items.wheat_seeds);
	}

	public EntityAepyornis(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new DEAIPanic(this));
		this.tasks.addTask(2, new DEAIAttackOnCollideUntilTime(this, 100, false));
		this.tasks.addTask(3, new DEAISittingNatural(this, 60, 2000, 500));
		this.tasks.addTask(3, new DEAIEggNestFinder(this, 300, 18.0D, 2.5D));
		this.tasks.addTask(3, new DEAIEggNestLaying(this, 2000));
		this.tasks.addTask(4, new DEAIFollowFood(this, 30, this.foodList));
		this.tasks.addTask(4, new DEAIEatDroppedItem(this, 40, 1.25D, 16.0D, this.foodList));
		this.tasks.addTask(5, new DEAIWander(this));
		this.tasks.addTask(6, new DEAIFollowParent(this, 30));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F, 0.025F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.tasks.addTask(9, new DEAIMating(this, 24000, 600, 18.0D, 2.5D));
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

	@SideOnly(Side.CLIENT)
	public float getSittingProgress(float partialRenderTicks)
	{
		return this.sittingAnimation.getAnimationProgressSinSqrt(partialRenderTicks);
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Aepyornis.NAME);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 2;
	}

	protected String getLivingSound()
	{
		return "mob.chicken.say";
	}

	protected String getHurtSound()
	{
		return "mob.chicken.hurt";
	}

	protected String getDeathSound()
	{
		return "mob.chicken.hurt";
	}

	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("mob.pig.step", 0.1F, 0.6F);
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return 1.45F * this.height;
	}

	@Override
	protected float getMaxHeight()
	{
		return 2.7F;
	}

	@Override
	protected float getMaxWidth()
	{
		return 1.8F;
	}

	@Override
	protected void updateAIForGrowthStage(byte stage)
	{

	}

	@Override
	public int getMaxHunger()
	{
		return 700;
	}

	@Override
	public List<Item> getCreatureFoodList()
	{
		return this.foodList;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting)
	{
		if (this.isIndependent())
		{
			this.dropItemWithOffset(DEItemRegistry.aepyornis_feather, this.rand.nextInt(4), 0.8F * this.height);
			if (this.isBurning())
				this.dropItemWithOffset(DEItemRegistry.aepyornis_cooked, this.rand.nextInt(3), 0.8F * this.height);
			else
				this.dropItemWithOffset(DEItemRegistry.aepyornis_raw, this.rand.nextInt(3), 0.8F * this.height);
		}
	}
}
