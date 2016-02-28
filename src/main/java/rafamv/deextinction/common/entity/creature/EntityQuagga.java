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
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Quagga;
import rafamv.deextinction.common.entity.ai.DEAIAttackOnCollideUntilTime;
import rafamv.deextinction.common.entity.ai.DEAIEatDroppedItem;
import rafamv.deextinction.common.entity.ai.DEAIFollowFood;
import rafamv.deextinction.common.entity.ai.DEAIFollowParent;
import rafamv.deextinction.common.entity.ai.DEAIPanic;
import rafamv.deextinction.common.entity.ai.DEAISittingNatural;
import rafamv.deextinction.common.entity.ai.DEAIWander;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedProtective;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityQuagga extends EntityDeExtinctedProtective
{
	protected static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityQuagga.foodList.add(Items.apple);
		EntityQuagga.foodList.add(Items.melon);
		EntityQuagga.foodList.add(Items.bread);
		EntityQuagga.foodList.add(Items.wheat);
	}

	public EntityQuagga(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new DEAIPanic(this));
		this.tasks.addTask(2, new DEAIAttackOnCollideUntilTime(this, 100, false));
		this.tasks.addTask(3, new DEAISittingNatural(this, 100, 3000, 1000));
		this.tasks.addTask(4, new DEAIFollowFood(this, 30, this.foodList));
		this.tasks.addTask(4, new DEAIEatDroppedItem(this, 40, 0.75D, 12.0D, this.foodList));
		this.tasks.addTask(5, new DEAIWander(this));
		this.tasks.addTask(6, new DEAIFollowParent(this, 30));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F, 0.025F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Quagga.NAME);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 1;
	}

	protected String getLivingSound()
	{
		return "mob.horse.say";
	}

	protected String getHurtSound()
	{
		return "mob.horse.hurt";
	}

	protected String getDeathSound()
	{
		return "mob.horse.hurt";
	}

	protected void playStepSound(BlockPos pos, Block block)
	{
		this.playSound("mob.horse.step", 0.1F, 0.3F);
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	public float getEyeHeight()
	{
		return 0.9F * this.height;
	}

	@Override
	protected float getMaxHeight()
	{
		return 2.0F;
	}

	@Override
	protected float getMaxWidth()
	{
		return 1.5F;
	}

	@Override
	protected void updateAIForGrowthStage(byte stage)
	{

	}

	@Override
	public int getMaxHunger()
	{
		return 450;
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
			this.dropItemWithOffset(DEItemRegistry.quagga_hide, this.rand.nextInt(3), 0.8F * this.height);
	}
}
