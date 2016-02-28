package rafamv.deextinction.common.entity.ai;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedPack;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class DEAINearestTargetSelectorForPackIfHungry extends EntityAIBase
{
	protected DEAINearestTargetSelectorForPackIfHungry.DistanceComparator nearestTargetSorter;
	protected EntityDeExtinctedAgeable creature;
	protected Predicate targetEntitySelector;
	protected int chance;

	public DEAINearestTargetSelectorForPackIfHungry(EntityDeExtinctedAgeable creature, int chance, Predicate predicate)
	{
		this.creature = creature;
		this.chance = chance;
		this.targetEntitySelector = predicate;
		this.nearestTargetSorter = new DEAINearestTargetSelectorForPackIfHungry.DistanceComparator(this.creature);
	}

	public boolean shouldExecute()
	{
		if (this.creature.getRNG().nextInt(this.chance) == 0)
		{
			if (this.creature.shouldHunt() && this.creature.getAITarget() == null)
			{
				double targetDistance = this.getTargetDistance();
				List<EntityLivingBase> list = this.creature.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.creature.getEntityBoundingBox().expand(targetDistance, targetDistance / 2.0D, targetDistance), Predicates.and(this.targetEntitySelector, IEntitySelector.NOT_SPECTATING));

				if (list.isEmpty())
					return false;
				else
				{
					Collections.sort(list, this.nearestTargetSorter);
					for (EntityLivingBase target : list)
					{
						if (target != null)
						{
							List<EntityDeExtinctedPack> listNearbyCreatures = this.creature.worldObj.getEntitiesWithinAABB(EntityDeExtinctedPack.class, this.creature.getEntityBoundingBox().expand(targetDistance, targetDistance / 2.0D, targetDistance));
							for (EntityDeExtinctedPack nearbyEntity : listNearbyCreatures)
							{
								if (nearbyEntity.getClass() == this.creature.getClass())
								{
									if (nearbyEntity.isIndependent())
									{
										if (nearbyEntity.isSitting())
											nearbyEntity.setSitting(false, null);

										if (nearbyEntity.isOnNest())
											nearbyEntity.setOnNest(false);

										if (nearbyEntity.ridingEntity != null)
											nearbyEntity.mountEntity((Entity) null);

										nearbyEntity.setRevengeTarget(target);
										nearbyEntity.setAttackTarget(target);
									}
								}
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean continueExecuting()
	{
		return false;
	}

	protected double getTargetDistance()
	{
		IAttributeInstance iattributeinstance = this.creature.getEntityAttribute(SharedMonsterAttributes.followRange);
		return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
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
