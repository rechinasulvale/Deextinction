package rafamv.deextinction.common.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class DEAIEatDroppedItem extends EntityAIBase
{
	protected DEAIEatDroppedItem.DistanceComparator nearestTargetSorter;
	private EntityDeExtinctedAgeable creature;
	private EntityItem droppedFood;
	private List<Item> listItems;
	private World worldObj;
	private double searchDistance;
	private double distanceToEat;
	private double speed;
	private int timeTryingToEat;
	private int eatDelay;
	private int chance;

	public DEAIEatDroppedItem(EntityDeExtinctedAgeable creature, int chance, double distanceToEat, double searchDistance, List<Item> listItems)
	{
		this.creature = creature;
		this.worldObj = creature.worldObj;
		this.chance = chance;
		this.distanceToEat = distanceToEat * distanceToEat;
		this.searchDistance = searchDistance;
		this.listItems = listItems;
		this.timeTryingToEat = 0;
		this.nearestTargetSorter = new DEAIEatDroppedItem.DistanceComparator(this.creature);
	}

	public DEAIEatDroppedItem(EntityDeExtinctedAgeable creature, int chance, double distanceToEat, double searchDistance, Item... listItems)
	{
		this.creature = creature;
		this.worldObj = creature.worldObj;
		this.chance = chance;
		this.distanceToEat = distanceToEat * distanceToEat;
		this.searchDistance = searchDistance;
		this.timeTryingToEat = 0;
		for (Item item : listItems)
			this.listItems.add(item);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.worldObj.rand.nextInt(this.chance) == 0)
		{
			if (this.creature.isSitting() || this.creature.getAttackTarget() != null || this.creature.isTakingOff() || this.creature.isFlying() || this.creature.isEating() || !this.creature.isNotFull())
				return false;

			List<EntityItem> nearEntityList = this.creature.worldObj.getEntitiesWithinAABB(EntityItem.class, this.creature.getEntityBoundingBox().expand(this.searchDistance, this.searchDistance / 2.0D, this.searchDistance));
			if (!nearEntityList.isEmpty())
			{
				Collections.sort(nearEntityList, this.nearestTargetSorter);
				for (EntityItem target : nearEntityList)
				{
					if (this.listItems.contains(target.getEntityItem().getItem()))
					{
						this.droppedFood = target;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting()
	{
		if (this.creature.isSitting())
			this.creature.setSitting(false, null);

		if (this.creature.isSleeping())
			this.creature.setSleeping(false);

		if (this.creature.isFleeing())
			this.creature.setFleeing(false);

		if (this.creature.isOnNest())
			this.creature.setOnNest(false);

		if (this.creature.isFlying())
			this.creature.setFlying(false);

		if (this.creature.isTakingOff())
			this.creature.setTakingOff(false);

		if (this.creature.isSocializing())
			this.creature.setSocializing(false);

		if (this.creature.isStalking())
			this.creature.setStalking(false);

		if (!this.creature.isEating())
			this.creature.setEating(true);

		this.speed = this.creature.getCreatureSpeed();
		this.creature.getNavigator().tryMoveToXYZ(this.droppedFood.posX, this.droppedFood.posY, this.droppedFood.posZ, this.speed);
		this.timeTryingToEat = 150;
	}

	@Override
	public void updateTask()
	{
		double distanceSq = this.creature.getDistanceSqToEntity(this.droppedFood);

		if (distanceSq < this.distanceToEat)
		{
			if (this.creature instanceof IAnimatedEntity)
			{
				IAnimatedEntity animatedCreature = (IAnimatedEntity) this.creature;
				if (animatedCreature.getAnimID() == 0)
					animatedCreature.setAnimID(DEAnimationList.EATING);
			}

			if (this.eatDelay-- < 0)
			{
				ItemStack itemstack = this.droppedFood.getEntityItem();
				if (itemstack != null)
				{
					Item currentItem = itemstack.getItem();
					if (currentItem != null && this.creature.getCreatureFoodList().contains(currentItem))
					{
						if (!this.worldObj.isRemote)
						{
							String itemUnlocalizedName = currentItem.getUnlocalizedName();
							if (DEDatabaseRegistry.FOOD_LIST.containsKey(itemUnlocalizedName))
								this.creature.decreaseHunger(DEDatabaseRegistry.FOOD_LIST.get(itemUnlocalizedName));
							else
								this.creature.decreaseHunger(30);
						}
						this.creature.playEatingSound(currentItem);
						this.eatDelay = 3;
					}
				}

				itemstack.stackSize--;
				if (this.droppedFood.getEntityItem().stackSize < 1)
				{
					this.droppedFood.setDead();

					if (this.creature.isNotFull())
					{
						List<EntityItem> nearEntityList = this.creature.worldObj.getEntitiesWithinAABB(EntityItem.class, this.creature.getEntityBoundingBox().expand(this.searchDistance, this.searchDistance / 2.0D, this.searchDistance));
						if (!nearEntityList.isEmpty())
						{
							Collections.sort(nearEntityList, this.nearestTargetSorter);
							for (EntityItem target : nearEntityList)
							{
								if (this.listItems.contains(target.getEntityItem().getItem()))
								{
									this.droppedFood = target;
									this.creature.getNavigator().tryMoveToXYZ(this.droppedFood.posX, this.droppedFood.posY, this.droppedFood.posZ, this.speed);
									this.timeTryingToEat = 150;
								}
							}
						}
					}
				}
			}
		}
		else
		{
			if (this.creature.getNavigator().noPath())
				this.creature.getNavigator().tryMoveToXYZ(this.droppedFood.posX, this.droppedFood.posY, this.droppedFood.posZ, this.speed);
		}
	}

	@Override
	public boolean continueExecuting()
	{
		return this.timeTryingToEat-- > 0 && this.droppedFood.isEntityAlive() && this.creature.isEntityAlive() && this.creature.isNotFull() && !this.creature.isSitting() && this.creature.riddenByEntity == null;
	}

	@Override
	public void resetTask()
	{
		this.creature.setEating(false);
		this.creature.getNavigator().clearPathEntity();
		this.droppedFood = null;
		this.timeTryingToEat = 0;
	}

	public static class DistanceComparator implements Comparator
	{
		private final Entity theEntity;

		public DistanceComparator(Entity entity)
		{
			this.theEntity = entity;
		}

		public int compare(Entity compare1, Entity compare2)
		{
			double distanceFrom1 = this.theEntity.getDistanceSqToEntity(compare1);
			double distanceFrom2 = this.theEntity.getDistanceSqToEntity(compare2);
			return distanceFrom1 < distanceFrom2 ? -1 : (distanceFrom1 > distanceFrom2 ? 1 : 0);
		}

		public int compare(Object compare1, Object compare2)
		{
			return this.compare((Entity) compare1, (Entity) compare2);
		}
	}
}
