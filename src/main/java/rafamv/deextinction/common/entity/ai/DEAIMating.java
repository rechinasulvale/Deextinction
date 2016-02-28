package rafamv.deextinction.common.entity.ai;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.item.speciesitems.ItemCreatureEgg;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class DEAIMating extends EntityAIBase
{
	private EntityDeExtinctedAgeable femaleCreature;
	private EntityDeExtinctedAgeable maleCreature;
	private EntityEggNest targetEggNest;
	private double distanceFromNest;
	private double searchDistance;
	private double speed;
	private int minDuration;
	private int chance;
	private int timer;

	public DEAIMating(EntityDeExtinctedAgeable creature, int minDuration, int chance, double searchDistance, double distanceFromNest)
	{
		this.femaleCreature = creature;
		this.chance = chance;
		this.searchDistance = searchDistance;
		this.distanceFromNest = distanceFromNest;
		this.minDuration = minDuration;
		this.timer = (int) (minDuration * (1.0F + 4.0F * this.femaleCreature.getRNG().nextFloat()));
	}

	public boolean shouldExecute()
	{
		if (this.timer-- < 0 && this.femaleCreature.getRNG().nextInt(this.chance) == 0)
		{
			if (this.femaleCreature.isMale())
				return false;

			if (this.femaleCreature.getAge() < 100 || this.femaleCreature.ridingEntity != null)
				return false;

			if (this.femaleCreature.isSitting() || this.femaleCreature.isSleeping() || this.femaleCreature.isFlying() || this.femaleCreature.isEating() || this.femaleCreature.isOnNest())
				return false;

			List list = this.femaleCreature.worldObj.getEntitiesWithinAABB(EntityEggNest.class, this.femaleCreature.getEntityBoundingBox().expand(this.searchDistance, this.searchDistance / 2.0D, this.searchDistance));
			EntityEggNest targetEggNest = null;
			double lastDistance = Double.MAX_VALUE;
			Iterator iterator = list.iterator();
			while (iterator.hasNext())
			{
				EntityEggNest eggNest = (EntityEggNest) iterator.next();
				if (eggNest != null)
				{
					double newDistance = this.femaleCreature.getDistanceSqToEntity(eggNest);
					if (newDistance <= lastDistance && eggNest.riddenByEntity == null && eggNest.hasEmptyEggSlot())
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
				iterator = this.femaleCreature.worldObj.getEntitiesWithinAABB(this.femaleCreature.getClass(), this.femaleCreature.getEntityBoundingBox().expand(this.searchDistance, this.searchDistance / 2.0D, this.searchDistance)).iterator();
				while (iterator.hasNext())
				{
					EntityDeExtinctedAgeable targetMale = (EntityDeExtinctedAgeable) iterator.next();
					if (targetMale != null && targetMale.isAdult())
					{
						this.maleCreature = targetMale;
						this.targetEggNest = targetEggNest;
						return true;
					}
				}
			}
		}
		return false;
	}

	public void startExecuting()
	{
		if (this.femaleCreature.isSitting())
			this.femaleCreature.setSitting(false, null);

		if (this.femaleCreature.isOnNest())
			this.femaleCreature.setOnNest(false);

		if (this.femaleCreature.isSocializing())
			this.femaleCreature.setSocializing(false);

		this.speed = this.femaleCreature.getCreatureSpeed();
		this.femaleCreature.getNavigator().tryMoveToEntityLiving(this.targetEggNest, this.speed);
	}

	@Override
	public void updateTask()
	{
		this.femaleCreature.getLookHelper().setLookPositionWithEntity(this.targetEggNest, 30.0F, this.femaleCreature.getVerticalFaceSpeed());
		if (this.femaleCreature.getDistanceSqToEntity(this.targetEggNest) < this.distanceFromNest)
		{
			InventoryBasic inventory = this.targetEggNest.getInventoryBasic();
			ItemStack itemStack = null;
			ItemStack newEgg = this.femaleCreature.createCreatureEgg(this.maleCreature);
			if (newEgg != null)
			{
				Creature creature = DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(((ItemCreatureEgg) newEgg.getItem()).getCreatureName());
				if (creature != null && creature instanceof EggCreature)
				{
					for (byte slot = 0; slot < this.targetEggNest.getNumberOfEggs(); slot++)
					{
						if (inventory.getStackInSlot(slot) == null)
						{
							this.femaleCreature.mountEntity(this.targetEggNest);
							inventory.setInventorySlotContents(slot, newEgg);
							this.targetEggNest.playSound("mob.chicken.plop", 1.0F, (this.femaleCreature.getRNG().nextFloat() - this.femaleCreature.getRNG().nextFloat()) * 0.2F + 1.0F);
							break;
						}
					}
				}
			}
			this.femaleCreature.getNavigator().clearPathEntity();
		}
		else
			this.femaleCreature.getNavigator().tryMoveToEntityLiving(this.targetEggNest, this.speed);
	}

	public boolean continueExecuting()
	{
		return this.femaleCreature.isEntityAlive() && this.targetEggNest.isEntityAlive() && this.targetEggNest.hasEmptyEggSlot() && !this.femaleCreature.hasBeenHurt() && this.targetEggNest.riddenByEntity == null && !this.femaleCreature.getNavigator().noPath();
	}

	@Override
	public void resetTask()
	{
		this.timer = this.minDuration + this.femaleCreature.getRNG().nextInt((int) (2.0F * this.minDuration));
		this.femaleCreature.getNavigator().clearPathEntity();
		this.targetEggNest = null;
	}
}
