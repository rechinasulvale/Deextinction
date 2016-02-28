package rafamv.deextinction.common.entity.ai.animation;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;

public abstract class DEAIAnimation extends EntityAIBase
{
	private IAnimatedEntity animatedEntity;

	public DEAIAnimation(IAnimatedEntity entity)
	{
		this.animatedEntity = entity;
		this.setMutexBits(7);
	}

	public <T extends EntityLiving> T getEntity()
	{
		return (T) this.animatedEntity;
	}

	public IAnimatedEntity getEntityAsIAnimatedEntity()
	{
		return this.animatedEntity;
	}

	public boolean shouldAnimate()
	{
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.isAutomatic())
			return this.animatedEntity.getAnimID() == this.getAnimID();
		return this.shouldAnimate();
	}

	@Override
	public void startExecuting()
	{
		if (!this.isAutomatic())
			DeExtinction.sendAnimPacket(this.animatedEntity, this.getAnimID());
		this.animatedEntity.setAnimTick(0);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.animatedEntity.getAnimTick() < this.getDuration();
	}

	@Override
	public void resetTask()
	{
		DeExtinction.sendAnimPacket(this.animatedEntity, 0);
	}

	public abstract int getAnimID();

	public abstract int getDuration();

	public abstract boolean isAutomatic();
}
