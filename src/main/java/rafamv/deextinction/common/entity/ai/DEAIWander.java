package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIWander extends EntityAIBase
{
	private EntityDeExtinctedAgeable creature;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private double speed;
	private int chanceToMove;

	public DEAIWander(EntityDeExtinctedAgeable creature)
	{
		this(creature, 100);
	}

	public DEAIWander(EntityDeExtinctedAgeable creature, int chanceToMove)
	{
		this.creature = creature;
		this.chanceToMove = chanceToMove;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.creature.getRNG().nextInt(this.chanceToMove) != 0)
			return false;

		if (this.creature.isSitting() || this.creature.isSleeping() || this.creature.isOnNest())
			return false;

		Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.creature, 12, 7);

		if (vec3 == null)
			return false;
		else
		{
			this.xPosition = vec3.xCoord;
			this.yPosition = vec3.yCoord;
			this.zPosition = vec3.zCoord;
			return true;
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

		this.speed = this.creature.getCreatureSpeed();
		this.creature.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.creature.isEntityAlive() && !this.creature.getNavigator().noPath();
	}

	public void setChanceToMove(int chanceToMove)
	{
		this.chanceToMove = chanceToMove;
	}
}
