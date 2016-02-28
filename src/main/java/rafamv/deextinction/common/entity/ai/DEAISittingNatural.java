package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAISittingNatural extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private World worldObj;
	private int sittingTimeInterval;
	private int minDuration;
	private int chance;
	private int timer;

	public DEAISittingNatural(EntityDeExtinctedAgeable creature, int chance, int sittingTimeInterval, int minDuration)
	{
		this.creature = creature;
		this.worldObj = creature.worldObj;

		if (chance > 0)
			this.chance = chance;
		else
			this.chance = 1;

		if (sittingTimeInterval > 499)
			this.sittingTimeInterval = sittingTimeInterval;
		else
			this.sittingTimeInterval = 500;

		if (minDuration > 299)
			this.minDuration = minDuration;
		else
			this.minDuration = 300;

		this.timer = this.sittingTimeInterval + this.creature.getRNG().nextInt((int) (2.0F * this.sittingTimeInterval));

		this.setMutexBits(5);
	}

	public boolean shouldExecute()
	{
		if (this.timer-- < 0)
		{
			if (this.worldObj.rand.nextInt(this.chance) == 0)
				return !this.creature.isInWater() && this.creature.onGround && !this.creature.isTakingOff() && !this.creature.isFlying() && this.creature.riddenByEntity == null && this.creature.ridingEntity == null && !this.creature.isEating();
		}
		return this.creature.isSitting() && !this.creature.isOnNest();
	}

	public void startExecuting()
	{
		this.creature.getNavigator().clearPathEntity();

		if (this.creature.isEating())
			this.creature.setEating(false);

		if (this.creature.isFlying())
			this.creature.setFlying(false);

		if (this.creature.isTakingOff())
			this.creature.setTakingOff(false);

		this.creature.setSitting(true, null);

		this.timer = this.minDuration + this.worldObj.rand.nextInt(this.sittingTimeInterval);
	}

	public boolean continueExecuting()
	{
		return this.timer-- > 0 && this.creature.isSitting() && !this.creature.hasBeenHurt() && this.creature.getAttackTarget() == null;
	}

	public void resetTask()
	{
		this.timer = this.sittingTimeInterval + this.worldObj.rand.nextInt((int) (2.0F * this.sittingTimeInterval));
		this.creature.setSitting(false, null);
	}
}
