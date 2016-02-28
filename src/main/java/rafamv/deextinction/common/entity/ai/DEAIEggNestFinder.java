package rafamv.deextinction.common.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

public class DEAIEggNestFinder extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private EntityEggNest targetEggNest;
	private boolean onlyFemales;
	private double distanceFromNestSQ;
	private double searchDistance;
	private double speed;
	private int chance;

	public DEAIEggNestFinder(EntityDeExtinctedAgeable creature, double searchDistance, double distanceFromNestSQ)
	{
		this(creature, 200, true, searchDistance, distanceFromNestSQ);
	}

	public DEAIEggNestFinder(EntityDeExtinctedAgeable creature, int chance, double searchDistance, double distanceFromNestSQ)
	{
		this(creature, chance, true, searchDistance, distanceFromNestSQ);
	}

	public DEAIEggNestFinder(EntityDeExtinctedAgeable creature, boolean onlyFemales, double searchDistance, double distanceFromNestSQ)
	{
		this(creature, 200, onlyFemales, searchDistance, distanceFromNestSQ);
	}

	public DEAIEggNestFinder(EntityDeExtinctedAgeable creature, int chance, boolean onlyFemales, double searchDistance, double distanceFromNestSQ)
	{
		this.creature = creature;
		this.chance = chance;
		this.onlyFemales = onlyFemales;
		this.searchDistance = searchDistance;
		this.distanceFromNestSQ = distanceFromNestSQ;
	}

	public boolean shouldExecute()
	{
		if (this.creature.worldObj.rand.nextInt(this.chance) == 0)
		{
			if (this.onlyFemales && this.creature.isMale())
				return false;

			if (this.creature.getAge() < 100 || this.creature.ridingEntity != null)
				return false;

			if (this.creature.isSitting() || this.creature.isSleeping() || this.creature.isFlying() || this.creature.isEating() || this.creature.isOnNest())
				return false;

			List list = this.creature.worldObj.getEntitiesWithinAABB(EntityEggNest.class, this.creature.getEntityBoundingBox().expand(20.0D, 10.0D, 20.0D));

			EntityEggNest targetEggNest = null;
			double lastDistance = Double.MAX_VALUE;
			Iterator iterator = list.iterator();
			while (iterator.hasNext())
			{
				EntityEggNest eggNest = (EntityEggNest) iterator.next();
				if (eggNest != null)
				{
					double newDistance = this.creature.getDistanceSqToEntity(eggNest);
					if (newDistance <= lastDistance && eggNest.riddenByEntity == null && eggNest.getCreatureNames().contains(this.creature.getCreature().getName())
							&& (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1) || eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2)))
					{
						lastDistance = newDistance;
						targetEggNest = eggNest;
					}
				}
			}

			if (targetEggNest == null)
				return false;
			else
			{
				this.targetEggNest = targetEggNest;
				return true;
			}
		}
		return false;
	}

	public void startExecuting()
	{
		if (this.creature.isSitting())
			this.creature.setSitting(false, null);

		if (this.creature.isOnNest())
			this.creature.setOnNest(false);

		if (this.creature.isSocializing())
			this.creature.setSocializing(false);

		this.speed = this.creature.getCreatureSpeed();
		this.creature.getNavigator().tryMoveToEntityLiving(this.targetEggNest, this.speed);
	}

	@Override
	public void updateTask()
	{
		this.creature.getLookHelper().setLookPositionWithEntity(this.targetEggNest, 30.0F, this.creature.getVerticalFaceSpeed());
		if (this.creature.getDistanceSqToEntity(this.targetEggNest) < this.distanceFromNestSQ && this.targetEggNest.riddenByEntity == null && this.targetEggNest.getCreatureNames().contains(this.creature.getCreature().getName())
				&& (this.targetEggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || this.targetEggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1) || this.targetEggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2)))
		{
			this.creature.mountEntity(this.targetEggNest);
			this.creature.getNavigator().clearPathEntity();
		}
		else
			this.creature.getNavigator().tryMoveToEntityLiving(this.targetEggNest, this.speed);
	}

	public boolean continueExecuting()
	{
		return this.creature.isEntityAlive() && this.targetEggNest.isEntityAlive() && !this.creature.hasBeenHurt() && this.targetEggNest.riddenByEntity == null && !this.creature.getNavigator().noPath();
	}

	@Override
	public void resetTask()
	{
		this.creature.getNavigator().clearPathEntity();
		this.targetEggNest = null;
	}
}
