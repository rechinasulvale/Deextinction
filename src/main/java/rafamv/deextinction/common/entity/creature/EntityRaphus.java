package rafamv.deextinction.common.entity.creature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Raphus;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityRaphus extends EntityDeExtinctedAgeable
{
	public static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityRaphus.foodList.add(Items.apple);
		EntityRaphus.foodList.add(Items.melon);
		EntityRaphus.foodList.add(Items.melon_seeds);
		EntityRaphus.foodList.add(Items.bread);
		EntityRaphus.foodList.add(Items.wheat);
		EntityRaphus.foodList.add(Items.wheat_seeds);
	}

	public EntityRaphus(World world)
	{
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Raphus.NAME);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 1;
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
		this.playSound("mob.chicken.step", 0.15F, 1.0F);
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	protected float getMaxHeight()
	{
		return 1.4F;
	}

	@Override
	protected float getMaxWidth()
	{
		return 1.0F;
	}

	@Override
	protected void updateAIForGrowthStage(byte stage)
	{

	}

	@Override
	public int getMaxHunger()
	{
		return 300;
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
			this.dropItemWithOffset(DEItemRegistry.raphus_feather, this.rand.nextInt(3), 0.8F * this.height);
			if (this.isBurning())
				this.dropItemWithOffset(DEItemRegistry.raphus_cooked, this.rand.nextInt(3), 0.8F * this.height);
			else
				this.dropItemWithOffset(DEItemRegistry.raphus_raw, this.rand.nextInt(3), 0.8F * this.height);
		}
	}
}
