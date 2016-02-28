package rafamv.deextinction.common.entity.creature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.animations.ControlledAnimation;
import rafamv.deextinction.client.renderer.animations.YawChainAnimator;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Deinonychus;
import rafamv.deextinction.common.database.creatures.Dromaeosaurus;
import rafamv.deextinction.common.entity.ai.DEAIAnimatedAttackOnCollideUntilTime;
import rafamv.deextinction.common.entity.ai.DEAIEatDroppedItem;
import rafamv.deextinction.common.entity.ai.DEAIEggNestFinder;
import rafamv.deextinction.common.entity.ai.DEAIEggNestLaying;
import rafamv.deextinction.common.entity.ai.DEAIFollowFood;
import rafamv.deextinction.common.entity.ai.DEAIFollowParent;
import rafamv.deextinction.common.entity.ai.DEAIMating;
import rafamv.deextinction.common.entity.ai.DEAINearestTargetSelectorForPackIfHungry;
import rafamv.deextinction.common.entity.ai.DEAISittingNatural;
import rafamv.deextinction.common.entity.ai.DEAIWander;
import rafamv.deextinction.common.entity.ai.animation.DEAIAnimationAttack;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.ai.predicate.DETargetSelector;
import rafamv.deextinction.common.entity.ai.predicate.TargetSecondary;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedPack;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityDromaeosaurus extends EntityDeExtinctedPack
{
	public YawChainAnimator tailBuffer = new YawChainAnimator(this);

	private ControlledAnimation sittingAnimation = new ControlledAnimation(30);
	private DEAIFollowParent aiFollowParent;
	private DEAIEggNestFinder aiEggNestFinder;
	private DEAIEggNestLaying aiEggNestLaying;
	private DEAIMating aiMating;

	protected static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityDromaeosaurus.foodList.add(Items.beef);
		EntityDromaeosaurus.foodList.add(Items.porkchop);
		EntityDromaeosaurus.foodList.add(Items.chicken);
		EntityDromaeosaurus.foodList.add(Items.mutton);
		EntityDromaeosaurus.foodList.add(Items.fish);
		EntityDromaeosaurus.foodList.add(Items.cooked_beef);
		EntityDromaeosaurus.foodList.add(Items.cooked_porkchop);
		EntityDromaeosaurus.foodList.add(Items.cooked_chicken);
		EntityDromaeosaurus.foodList.add(Items.cooked_mutton);
		EntityDromaeosaurus.foodList.add(Items.cooked_fish);
		EntityDromaeosaurus.foodList.add(Items.bone);
	}

	public EntityDromaeosaurus(World worldIn)
	{
		super(worldIn);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new DEAIAnimatedAttackOnCollideUntilTime(this, 200, 14, false));
		this.tasks.addTask(2, new DEAIAnimationAttack(this, 14, 6));
		this.tasks.addTask(3, new DEAISittingNatural(this, 60, 2000, 500));
		this.tasks.addTask(4, new DEAIFollowFood(this, 30, this.foodList));
		this.tasks.addTask(5, new DEAIEatDroppedItem(this, 40, 0.8D, 16.0D, this.foodList));
		this.tasks.addTask(6, new DEAIWander(this));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 4.0F, 0.025F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		this.targetTasks.addTask(9, new DEAINearestTargetSelectorForPackIfHungry(this, 20, new DETargetSelector(this, new TargetSecondary(EntityChicken.class, 10.0D, 1, 5))));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.worldObj.isRemote)
		{
			this.tailBuffer.update(4, 45.0F, 4.0F);

			this.sittingAnimation.update();

			if (this.isSitting())
				this.sittingAnimation.runAnimation();
			else
				this.sittingAnimation.stopAnimation();
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (this.animID == 0)
			DeExtinction.sendAnimPacket(this, DEAnimationList.ATTACKING);
		return true;
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
				this.tasks.addTask(11, this.aiEggNestLaying);
				this.tasks.addTask(12, this.aiMating);
				this.tasks.addTask(13, this.aiEggNestFinder);
			}
		}
		else
			this.tasks.addTask(10, this.aiFollowParent);
	}

	@SideOnly(Side.CLIENT)
	public float getSittingProgress(float partialRenderTicks)
	{
		return this.sittingAnimation.getAnimationProgressSin(partialRenderTicks);
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Dromaeosaurus.NAME);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 2;
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return 1.1F * this.height;
	}

	@Override
	protected float getMaxHeight()
	{
		return 2.0F;
	}

	@Override
	protected float getMaxWidth()
	{
		return 1.25F;
	}

	@Override
	public int getMaxHunger()
	{
		return 600;
	}

	@Override
	public int getTalkInterval()
	{
		return 300;
	}

	@Override
	protected String getLivingSound()
	{
		return DeExtinction.prependModID("deinonychus_antirrhopus");
	}

	@Override
	protected float getSoundVolume()
	{
		if (this.isIndependent())
			return 0.8F + 0.2F * (this.rand.nextFloat());
		else
			return 0.6F + 0.2F * (this.rand.nextFloat());
	}

	@Override
	public List<Item> getCreatureFoodList()
	{
		return this.foodList;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting)
	{
		
	}
}
