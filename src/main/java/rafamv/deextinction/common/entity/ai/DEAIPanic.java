package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIPanic extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private double randPosX;
	private double randPosY;
	private double randPosZ;
	private double speed;

	public DEAIPanic(EntityDeExtinctedAgeable creature)
	{
		this.creature = creature;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (!this.creature.isFleeing() && !this.creature.isBurning())
			return false;
		else
		{
			Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.creature, 8, 4);
			if (vec3 == null)
				return false;
			else
			{
				this.randPosX = vec3.xCoord;
				this.randPosY = vec3.yCoord;
				this.randPosZ = vec3.zCoord;
				return true;
			}
		}
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

		if (this.creature.isEating())
			this.creature.setEating(false);

		if (this.creature.isSocializing())
			this.creature.setSocializing(false);

		if (this.creature.isStalking())
			this.creature.setStalking(false);

		if (!this.creature.isFleeing())
			this.creature.setFleeing(true);

		this.speed = this.creature.getCreatureSpeed();
		this.creature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
	}

	@Override
	public boolean continueExecuting()
	{
		return !this.creature.getNavigator().noPath() && this.creature.getAITarget() != null;
	}

	@Override
	public void resetTask()
	{
		this.creature.setFleeing(false);
	}
}
