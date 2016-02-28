package rafamv.deextinction.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

import org.apache.commons.lang3.StringUtils;

import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public abstract class DEAITarget extends EntityAIBase
{
	protected final EntityDeExtinctedAgeable creature;
	private int targetSearchStatus;
	private int targetSearchDelay;
	private int targetUnseenTicks;
	protected boolean shouldCheckSight;
	private boolean nearbyOnly;

	public DEAITarget(EntityDeExtinctedAgeable creature, boolean shouldCheckSight)
	{
		this(creature, shouldCheckSight, false);
	}

	public DEAITarget(EntityDeExtinctedAgeable creature, boolean shouldCheckSight, boolean nearbyOnly)
	{
		this.creature = creature;
		this.shouldCheckSight = shouldCheckSight;
		this.nearbyOnly = nearbyOnly;
	}

	protected double getTargetDistance()
	{
		IAttributeInstance iattributeinstance = this.creature.getEntityAttribute(SharedMonsterAttributes.followRange);
		return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
	}

	public void startExecuting()
	{
		this.targetSearchStatus = 0;
		this.targetSearchDelay = 0;
		this.targetUnseenTicks = 0;
	}

	public boolean continueExecuting()
	{
		EntityLivingBase entitylivingbase = this.creature.getAttackTarget();

		if (entitylivingbase == null)
		{
			return false;
		}
		else if (!entitylivingbase.isEntityAlive())
		{
			return false;
		}
		else
		{
			Team team = this.creature.getTeam();
			Team team1 = entitylivingbase.getTeam();

			if (team != null && team1 == team)
			{
				return false;
			}
			else
			{
				double d0 = this.getTargetDistance();

				if (this.creature.getDistanceSqToEntity(entitylivingbase) > d0 * d0)
				{
					return false;
				}
				else
				{
					if (this.shouldCheckSight)
					{
						if (this.creature.getEntitySenses().canSee(entitylivingbase))
						{
							this.targetUnseenTicks = 0;
						}
						else if (++this.targetUnseenTicks > 60)
						{
							return false;
						}
					}

					return !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer) entitylivingbase).capabilities.disableDamage;
				}
			}
		}
	}

	public void resetTask()
	{
		this.creature.setAttackTarget((EntityLivingBase) null);
	}

	public static boolean func_179445_a(EntityLiving entityLiving, EntityLivingBase entityLivingBase, boolean flag, boolean shouldCheckSight)
	{
		if (entityLivingBase == null)
			return false;
		else if (entityLivingBase == entityLiving)
			return false;
		else if (!entityLivingBase.isEntityAlive())
			return false;
		else if (!entityLiving.canAttackClass(entityLivingBase.getClass()))
			return false;
		else
		{
			Team team = entityLiving.getTeam();
			Team team1 = entityLivingBase.getTeam();

			if (team != null && team1 == team)
				return false;
			else
			{
				if (entityLiving instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable) entityLiving).getOwnerId()))
				{
					if (entityLivingBase instanceof IEntityOwnable && ((IEntityOwnable) entityLiving).getOwnerId().equals(((IEntityOwnable) entityLivingBase).getOwnerId()))
						return false;

					if (entityLivingBase == ((IEntityOwnable) entityLiving).getOwner())
						return false;
				}
				else if (entityLivingBase instanceof EntityPlayer && !flag && ((EntityPlayer) entityLivingBase).capabilities.disableDamage)
					return false;

				return !shouldCheckSight || entityLiving.getEntitySenses().canSee(entityLivingBase);
			}
		}
	}

	protected boolean isSuitableTarget(EntityLivingBase target, boolean flag)
	{
		if (!func_179445_a(this.creature, target, flag, this.shouldCheckSight))
			return false;
		else if (!this.creature.func_180485_d(new BlockPos(target)))
			return false;
		else
		{
			if (this.nearbyOnly)
			{
				if (this.targetSearchDelay-- <= 0)
					this.targetSearchStatus = 0;

				if (this.targetSearchStatus == 0)
					this.targetSearchStatus = this.canEasilyReach(target) ? 1 : 2;

				if (this.targetSearchStatus == 2)
					return false;
			}

			return true;
		}
	}

	private boolean canEasilyReach(EntityLivingBase target)
	{
		this.targetSearchDelay = 10 + this.creature.getRNG().nextInt(5);
		PathEntity pathentity = this.creature.getNavigator().getPathToEntityLiving(target);

		if (pathentity == null)
			return false;
		else
		{
			PathPoint pathpoint = pathentity.getFinalPathPoint();

			if (pathpoint == null)
				return false;
			else
			{
				int i = pathpoint.xCoord - MathHelper.floor_double(target.posX);
				int j = pathpoint.zCoord - MathHelper.floor_double(target.posZ);
				return (double) (i * i + j * j) <= 2.25D;
			}
		}
	}
}
