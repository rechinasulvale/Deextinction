package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

public class DEAIEggNestLaying extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private EntityEggNest eggNest;
	private int layingTime;
	private int timer;

	public DEAIEggNestLaying(EntityDeExtinctedAgeable creature, int layingTime)
	{
		this.creature = creature;
		this.layingTime = layingTime;
	}

	public boolean shouldExecute()
	{
		if (this.creature.ridingEntity != null && this.creature.ridingEntity instanceof EntityEggNest && this.creature.getAge() > 99)
		{
			Entity entity = this.creature.ridingEntity;
			if (entity instanceof EntityEggNest)
			{
				EntityEggNest eggNest = (EntityEggNest) entity;
				if (eggNest != null)
				{
					this.eggNest = eggNest;
					return true;
				}
			}
			else
			{
				this.creature.mountEntity((Entity) null);
				this.creature.setSitting(false, null);
				this.creature.setOnNest(false);
			}
		}
		return this.creature.isOnNest();
	}

	public void startExecuting()
	{
		this.creature.setSitting(true, null);
		this.creature.setOnNest(true);
		this.timer = (int) (this.layingTime + 2.0F * this.creature.getRNG().nextInt(this.layingTime));
	}

	public boolean continueExecuting()
	{
		return this.creature.isEntityAlive() && this.eggNest.isEntityAlive() && !this.creature.hasBeenHurt() && this.timer-- > 0
				&& (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1) || eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2));
	}

	@Override
	public void resetTask()
	{
		this.creature.mountEntity((Entity) null);
		this.creature.setSitting(false, null);
		this.creature.setOnNest(false);
		this.eggNest = null;
	}
}
