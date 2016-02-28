package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.BlockPos;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIAttackOnCollideUntilTime extends EntityAIBase
{
	protected EntityDeExtinctedAgeable creature;
	protected PathEntity entityPathEntity;
	protected Class classTarget;
	protected double speedTowardsTarget;
	protected boolean longMemory;
	protected int attackTick;
	protected int duration;
	protected int timer;
	private int field_75445_i;
	private double field_151497_i;
	private double field_151495_j;
	private double field_151496_k;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;

	public DEAIAttackOnCollideUntilTime(EntityDeExtinctedAgeable creature, int duration, Class classTarget, boolean longMemory)
	{
		this(creature, duration, longMemory);
		this.classTarget = classTarget;
		this.canPenalize = classTarget == null || !net.minecraft.entity.player.EntityPlayer.class.isAssignableFrom(classTarget);
	}

	public DEAIAttackOnCollideUntilTime(EntityDeExtinctedAgeable creature, int duration, boolean longMemory)
	{
		this.creature = creature;
		this.longMemory = longMemory;
		this.duration = duration;
		this.setMutexBits(3);
	}

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
		else if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass()))
			return false;
		else
		{
			if (this.canPenalize)
			{
				if (this.field_75445_i-- <= 0)
				{
					this.entityPathEntity = this.creature.getNavigator().getPathToEntityLiving(entitylivingbase);
					this.field_151497_i = 4 + this.creature.getRNG().nextInt(7);
					return this.entityPathEntity != null;
				}
				else
					return true;
			}
			this.entityPathEntity = this.creature.getNavigator().getPathToEntityLiving(entitylivingbase);
			return this.entityPathEntity != null;
		}
	}

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
		this.field_75445_i = 0;
	}

	public void updateTask()
	{
		EntityLivingBase entitylivingbase = this.creature.getAttackTarget();
		this.creature.getLookHelper().setLookPositionWithEntity(entitylivingbase, 30.0F, 30.0F);
		double d0 = this.creature.getDistanceSq(entitylivingbase.posX, entitylivingbase.getEntityBoundingBox().minY, entitylivingbase.posZ);
		double d1 = this.getDistanceSqToAttack(entitylivingbase);
		this.field_75445_i--;

		if ((this.longMemory || this.creature.getEntitySenses().canSee(entitylivingbase)) && this.field_75445_i <= 0
				&& (this.field_151497_i == 0.0D && this.field_151495_j == 0.0D && this.field_151496_k == 0.0D || entitylivingbase.getDistanceSq(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D || this.creature.getRNG().nextFloat() < 0.05F))
		{
			this.field_151497_i = entitylivingbase.posX;
			this.field_151495_j = entitylivingbase.getEntityBoundingBox().minY;
			this.field_151496_k = entitylivingbase.posZ;
			this.field_75445_i = 4 + this.creature.getRNG().nextInt(7);

			if (this.canPenalize)
			{
				this.field_151497_i += this.failedPathFindingPenalty;
				if (this.creature.getNavigator().getPath() != null)
				{
					net.minecraft.pathfinding.PathPoint finalPathPoint = this.creature.getNavigator().getPath().getFinalPathPoint();
					if (finalPathPoint != null && entitylivingbase.getDistanceSq(finalPathPoint.xCoord, finalPathPoint.yCoord, finalPathPoint.zCoord) < 1)
						this.failedPathFindingPenalty = 0;
					else
						this.failedPathFindingPenalty += 10;
				}
				else
				{
					this.failedPathFindingPenalty += 10;
				}
			}

			if (d0 > 1024.0D)
			{
				this.field_75445_i += 10;
			}
			else if (d0 > 256.0D)
			{
				this.field_75445_i += 5;
			}

			if (!this.creature.getNavigator().tryMoveToEntityLiving(entitylivingbase, this.speedTowardsTarget))
			{
				this.field_75445_i += 15;
			}
		}

		if (this.timer-- < 0)
			this.creature.setAttackTarget(null);

		this.attackTick -= 1;
		if (d0 <= d1 && this.attackTick <= 0)
		{
			this.attackTick = 20;
			this.timer += 50;
			this.creature.attackEntityAsMob(entitylivingbase);
		}

		if (this.creature.hasBeenHurt())
			this.timer += 10;
	}

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

	public void resetTask()
	{
		this.creature.getNavigator().clearPathEntity();
	}

	protected double getDistanceSqToAttack(EntityLivingBase target)
	{
		return (double) (this.creature.width * 2.0F * this.creature.width * 2.0F + target.width);
	}
}
