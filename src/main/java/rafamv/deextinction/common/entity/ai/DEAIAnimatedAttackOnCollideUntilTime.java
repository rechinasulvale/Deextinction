package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.BlockPos;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIAnimatedAttackOnCollideUntilTime extends EntityAIBase
{
	protected EntityDeExtinctedAgeable creature;
	protected PathEntity entityPathEntity;
	protected double speedTowardsTarget;
	protected boolean longMemory;
	protected int animationDuration;
	protected int attackTick;
	protected int duration;
	protected int timer;
	private int timerToUpdatePath;
	private double targetPosX;
	private double targetPosYBB;
	private double targetPosZ;
	private int failedPathFindingPenalty = 0;

	public DEAIAnimatedAttackOnCollideUntilTime(EntityDeExtinctedAgeable creature, int duration, int animationDuration, boolean longMemory)
	{
		this.creature = creature;
		this.longMemory = longMemory;
		this.animationDuration = animationDuration;
		this.duration = duration;
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase entitylivingbase = this.creature.getAttackTarget();
		if (entitylivingbase == null)
			return false;
		else if (!entitylivingbase.isEntityAlive())
		{
			this.creature.setAttackTarget((EntityLivingBase) null);
			return false;
		}
		else
		{
			this.entityPathEntity = this.creature.getNavigator().getPathToEntityLiving(entitylivingbase);
			return this.entityPathEntity != null;
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

		if (this.creature.isFleeing())
			this.creature.setFleeing(false);

		this.timer = this.duration + this.creature.getRNG().nextInt(this.duration);
		this.speedTowardsTarget = 1.1D * this.creature.getCreatureSpeed();
		this.creature.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
		this.timerToUpdatePath = 0;
		this.attackTick = 0;
	}

	@Override
	public void updateTask()
	{
		EntityLivingBase target = this.creature.getAttackTarget();
		this.creature.getLookHelper().setLookPositionWithEntity(target, 30.0F, 30.0F);
		double distanceSqFromTarget = this.creature.getDistanceSq(target.posX, target.getEntityBoundingBox().minY, target.posZ);
		double minDistanceSqToAttack = this.getMinDistanceSqToAttack(target);
		this.timerToUpdatePath--;

		if ((this.longMemory || this.creature.getEntitySenses().canSee(target)) && this.timerToUpdatePath <= 0
				&& (this.targetPosX == 0.0D && this.targetPosYBB == 0.0D && this.targetPosZ == 0.0D || target.getDistanceSq(this.targetPosX, this.targetPosYBB, this.targetPosZ) >= 1.0D || this.creature.getRNG().nextFloat() < 0.05F))
		{
			this.targetPosX = target.posX;
			this.targetPosYBB = target.getEntityBoundingBox().minY;
			this.targetPosZ = target.posZ;
			this.timerToUpdatePath = 4 + this.creature.getRNG().nextInt(7);

			if (distanceSqFromTarget > 1024.0D)
				this.timerToUpdatePath += 10;
			else if (distanceSqFromTarget > 256.0D)
				this.timerToUpdatePath += 5;

			if (!this.creature.getNavigator().tryMoveToEntityLiving(target, this.speedTowardsTarget))
				this.timerToUpdatePath += 15;
		}

		if (this.timer-- < 0)
			this.creature.setAttackTarget(null);

		this.attackTick -= 1;
		if (distanceSqFromTarget <= minDistanceSqToAttack && this.attackTick <= 0)
		{
			this.attackTick = (int) (1.1F * this.animationDuration);
			this.timer += 20 + this.animationDuration;
			this.creature.attackEntityAsMob(target);
		}

		if (this.creature.hasBeenHurt())
			this.timer += 10;
	}

	@Override
	public boolean continueExecuting()
	{
		EntityLivingBase entitylivingbase = this.creature.getAttackTarget();

		if (entitylivingbase == null)
			return false;
		else
		{
			if (!entitylivingbase.isEntityAlive())
				return false;
			else
			{
				if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer) entitylivingbase).capabilities.isCreativeMode)
				{
					this.creature.setAttackTarget(null);
					return false;
				}
				else
				{
					if (!this.longMemory)
						return !this.creature.getNavigator().noPath();
					else
						return this.creature.func_180485_d(new BlockPos(entitylivingbase));
				}
			}
		}
	}

	@Override
	public void resetTask()
	{
		this.creature.getNavigator().clearPathEntity();
	}

	protected double getMinDistanceSqToAttack(EntityLivingBase target)
	{
		return (double) (4.0F * this.creature.width * this.creature.width + target.width);
	}
}
