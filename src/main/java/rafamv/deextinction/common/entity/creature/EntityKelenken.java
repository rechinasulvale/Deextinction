package rafamv.deextinction.common.entity.creature;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.creatures.Kelenken;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityKelenken extends EntityDeExtinctedAgeable
{
	protected static final List<Item> foodList = new ArrayList<Item>();
	static
	{
		EntityKelenken.foodList.add(Items.beef);
		EntityKelenken.foodList.add(Items.cooked_beef);
		EntityKelenken.foodList.add(Items.chicken);
		EntityKelenken.foodList.add(Items.cooked_chicken);
		EntityKelenken.foodList.add(Items.fish);
		EntityKelenken.foodList.add(Items.cooked_fish);
		EntityKelenken.foodList.add(Items.mutton);
		EntityKelenken.foodList.add(Items.cooked_mutton);
		EntityKelenken.foodList.add(Items.porkchop);
		EntityKelenken.foodList.add(Items.cooked_porkchop);
		EntityKelenken.foodList.add(Items.rabbit);
		EntityKelenken.foodList.add(Items.cooked_rabbit);
	}

	public EntityKelenken(World world)
	{
		super(world);
		this.setSize(1.0F, 2.0F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public Creature getCreature()
	{
		return DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(Kelenken.NAME);
	}

	@Override
	public boolean isIndependent()
	{
		return this.getGrowthStage() > 1;
	}

	@Override
	public boolean canBeTamedUponSpawning()
	{
		return false;
	}

	@Override
	protected float getMaxHeight()
	{
		return 1.8F;
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
		return 600;
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
			this.dropItemWithOffset(DEItemRegistry.kelenken_feather, this.rand.nextInt(3), 0.8F * this.height);
			if (this.isBurning())
				this.dropItemWithOffset(DEItemRegistry.kelenken_cooked, this.rand.nextInt(2), 0.8F * this.height);
			else
				this.dropItemWithOffset(DEItemRegistry.kelenken_raw, this.rand.nextInt(2), 0.8F * this.height);
		}
	}
}
