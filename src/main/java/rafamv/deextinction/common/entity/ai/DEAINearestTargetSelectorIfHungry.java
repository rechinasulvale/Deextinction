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

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

public class DEAINearestTargetSelectorIfHungry extends EntityAIBase
{
	protected DEAINearestTargetSelectorIfHungry.DistanceComparator nearestTargetSorter;
	protected EntityDeExtinctedAgeable creature;
	protected Predicate targetEntitySelector;
	protected int chance;

	public DEAINearestTargetSelectorIfHungry(EntityDeExtinctedAgeable creature, int chance, Predicate predicate)
	{
		this.creature = creature;
		this.chance = chance;
		this.targetEntitySelector = predicate;
		this.nearestTargetSorter = new DEAINearestTargetSelectorIfHungry.DistanceComparator(this.creature);
	}

	public boolean shouldExecute()
	{
		if (this.creature.getRNG().nextInt(this.chance) == 0)
		{
			if (this.creature.shouldHunt())
			{
				double targetDistance = this.getTargetDistance();
				List<EntityLivingBase> list = this.creature.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, this.creature.getEntityBoundingBox().expand(targetDistance, 4.0D, targetDistance), Predicates.and(this.targetEntitySelector, IEntitySelector.NOT_SPECTATING));
				list.remove(this.creature);

				if (list.isEmpty())
					return false;
				else
				{
					Collections.sort(list, this.nearestTargetSorter);
					for (EntityLivingBase target : list)
					{
						if (target != null)
						{
							this.creature.setRevengeTarget(target);
							this.creature.setAttackTarget(target);
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
