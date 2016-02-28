package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedCreature;

public class DEAIPlayerMountingSocialization extends EntityAIBase
{
	private EntityDeExtinctedCreature creature;
	private int duration;
	private int timer;

	public DEAIPlayerMountingSocialization(EntityDeExtinctedCreature creature, int duration)
	{
		this.creature = creature;
		this.duration = duration;
		this.timer = 0;
	}

	@Override
	public boolean shouldExecute()
	{
		if (!this.creature.isSocializing() || this.creature.isSitting() || this.creature.isFlying() || this.creature.isOnNest() || this.creature.isSleeping())
			return false;

		return this.creature.getCreatureTarget() != null;
	}

	@Override
	public void startExecuting()
	{
		if (this.creature.isSitting())
			this.creature.setSitting(false, null);

		if (this.creature.isSleeping())
			this.creature.setSleeping(false);

		if (this.creature.isOnNest())
			this.creature.setOnNest(false);

		if (this.creature.isFlying())
			this.creature.setFlying(false);

		EntityLivingBase target = this.creature.getCreatureTarget();
		this.creature.rotationYaw = target.rotationYaw;
		this.creature.rotationPitch = target.rotationPitch;
		this.creature.mountEntity(target);

		this.timer = this.duration + this.creature.worldObj.rand.nextInt(this.duration);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.timer-- > 0 && !this.creature.isSitting() && !this.creature.isSleeping() && !this.creature.hasBeenHurt() && this.creature.ridingEntity != null && !this.creature.hasBeenHurt();
	}

	@Override
	public void resetTask()
	{
		this.creature.mountEntity((Entity) null);
		this.creature.setCreatureTarget(null);
		this.creature.setSocializing(false);
		this.timer = 0;
	}
}
