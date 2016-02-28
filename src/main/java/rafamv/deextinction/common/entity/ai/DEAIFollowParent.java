package rafamv.deextinction.common.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIFollowParent extends EntityAIBase
{
	private EntityDeExtinctedAgeable childCreature;
	private EntityDeExtinctedAgeable parentCreature;
	private World worldObj;
	private double speed;
	private int updatePathTimer;
	private int chance;

	public DEAIFollowParent(EntityDeExtinctedAgeable creature, int chance)
	{
		this.childCreature = creature;
		this.worldObj = creature.worldObj;
		this.chance = chance;
	}

	public boolean shouldExecute()
	{
		if (this.worldObj.rand.nextInt(this.chance) == 0)
		{
			if (this.childCreature.isIndependent())
				return false;
			else
			{
				if (this.childCreature.isSleeping() || this.childCreature.isFlying() || this.childCreature.isTakingOff() || this.childCreature.isEating() || this.childCreature.isSocializing())
					return false;

				List list = this.childCreature.worldObj.getEntitiesWithinAABB(this.childCreature.getClass(), this.childCreature.getEntityBoundingBox().expand(16.0D, 8.0D, 16.0D));
				EntityDeExtinctedAgeable parentCreature = null;
				double lastDistance = Double.MAX_VALUE;
				Iterator iterator = list.iterator();

				while (iterator.hasNext())
				{
					EntityDeExtinctedAgeable possibleParent = (EntityDeExtinctedAgeable) iterator.next();

					if (possibleParent.isAdult())
					{
						double newDistance = this.childCreature.getDistanceSqToEntity(possibleParent);

						if (newDistance <= lastDistance)
						{
							lastDistance = newDistance;
							parentCreature = possibleParent;
						}
					}
				}

				if (parentCreature == null)
					return false;
				else if (lastDistance < 36.0D)
					return false;
				else
				{
					this.parentCreature = parentCreature;
					return true;
				}
			}
		}
		return false;
	}

	public void startExecuting()
	{
		if (this.childCreature.isSitting())
			this.childCreature.setSitting(false, null);

		if (this.childCreature.isSleeping())
			this.childCreature.setSleeping(false);

		if (this.childCreature.isOnNest())
			this.childCreature.setOnNest(false);

		if (this.childCreature.isFlying())
			this.childCreature.setFlying(false);

		if (this.childCreature.isTakingOff())
			this.childCreature.setTakingOff(false);

		if (this.childCreature.isEating())
			this.childCreature.setEating(false);

		if (this.childCreature.isSocializing())
			this.childCreature.setSocializing(false);

		this.speed = this.childCreature.getCreatureSpeed();
		this.updatePathTimer = 0;
	}

	public void updateTask()
	{
		if (this.updatePathTimer-- <= 0)
		{
			this.updatePathTimer = 20;
			this.childCreature.getNavigator().tryMoveToEntityLiving(this.parentCreature, this.speed);
		}
	}

	public boolean continueExecuting()
	{
		if (this.childCreature != null && this.parentCreature != null && this.childCreature.isEntityAlive() && this.parentCreature.isEntityAlive())
		{
			if (this.childCreature.isAdult())
				return false;
			else
			{
				double distance = this.childCreature.getDistanceSqToEntity(this.parentCreature);
				return distance >= 36.0D && distance <= 256.0D;
			}
		}
		return false;
	}

	public void resetTask()
	{
		this.parentCreature = null;
	}
}
