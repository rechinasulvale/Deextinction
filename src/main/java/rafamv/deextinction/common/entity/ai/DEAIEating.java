package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIEating extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private int duration;
	private int timer;

	public DEAIEating(EntityDeExtinctedAgeable creature, int duration)
	{
		this.creature = creature;
		this.duration = duration;
		this.timer = 0;
	}

	@Override
	public boolean shouldExecute()
	{
		return this.creature.isEating();
	}

	@Override
	public void startExecuting()
	{
		this.timer = this.duration;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.timer-- > 0 && !this.creature.isSitting() && !this.creature.isSleeping() && !this.creature.hasBeenHurt() && this.creature.riddenByEntity == null;
	}

	@Override
	public void resetTask()
	{
		this.creature.setEating(false);
		this.timer = 0;
	}
}
