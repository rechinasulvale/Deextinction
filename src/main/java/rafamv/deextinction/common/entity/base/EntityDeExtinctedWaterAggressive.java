package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedWaterAggressive extends EntityDeExtinctedWaterCreature
{
	protected EntityLivingBase waterTargetEntity;
	protected boolean isAttacking;

	public EntityDeExtinctedWaterAggressive(World world)
	{
		super(world);
	}

	@Override
	protected void handleCreatureMovementOnLand()
	{
		if (this.getAir() > (int) (this.getMaxAir() / 2.0F))
		{
			if (this.waterTargetEntity == null)
			{
				if (this.onGround && this.rand.nextInt(20) == 0)
				{
					this.motionY = 0.3F;
					this.motionX = -0.2F + this.rand.nextFloat() * 0.4F;
					this.motionZ = -0.2F + this.rand.nextFloat() * 0.4F;
				}
			}
			else if (this.onGround && this.rand.nextInt(10) == 0)
			{
				double swimSpeed = this.getSwimSpeed();
				double distanceX = this.swimmingTarget.getX() - this.posX;
				double distanceY = this.swimmingTarget.getY() - this.posY;
				double distanceZ = this.swimmingTarget.getZ() - this.posZ;
				double distanceSq = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

				this.motionY = 0.4F;
				this.motionX += (distanceX / distanceSq * 0.05D * swimSpeed) / this.getAttackSpeedBonus();
				this.motionZ += (distanceZ / distanceSq * 0.05D * swimSpeed) / this.getAttackSpeedBonus();
			}
		}
		else
		{
			if (this.isWaterBlock(this.swimmingTarget))
			{
				if (this.rand.nextInt(10) == 0 && this.onGround)
				{
					double swimSpeed = this.getSwimSpeed();
					double distanceX = this.swimmingTarget.getX() - this.posX;
					double distanceY = this.swimmingTarget.getY() - this.posY;
					double distanceZ = this.swimmingTarget.getZ() - this.posZ;
					double distanceSq = Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);

					this.motionY = 0.4F;
					this.motionX += (distanceX / distanceSq * 0.05D * swimSpeed) / this.getAttackSpeedBonus();
					this.motionZ += (distanceZ / distanceSq * 0.05D * swimSpeed) / this.getAttackSpeedBonus();
				}
			}
			else
			{
				if (this.rand.nextInt(20) == 0 && this.onGround)
				{
					this.motionY = 0.3F;
					this.motionX = -0.2F + this.rand.nextFloat() * 0.4F;
					this.motionZ = -0.2F + this.rand.nextFloat() * 0.4F;
				}

				double swimRadiusSq = this.getSwimRadius();
				BlockPos waterBlock = new BlockPos(this.posX + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadiusSq, this.posY + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadiusSq, this.posZ + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadiusSq);
				if (this.isWaterBlock(waterBlock))
					this.swimmingTarget = waterBlock;
			}
		}
	}

	@Override
	protected void handleCreatureAttackInWater()
	{
		if (this.rand.nextInt(this.getAttackChance()) == 0 && this.shouldHunt())
			this.setWaterAttackTarget(this.findEntityLivingBaseToAttack(16.0D));

		if (this.isAttacking)
		{
			if (!this.waterTargetEntity.isEntityAlive())
				this.setWaterAttackTarget(null);

			double speedBonus = this.getAttackSpeedBonus();
			this.motionX *= speedBonus;
			this.motionY *= speedBonus;
			this.motionZ *= speedBonus;
		}
	}

	/**
	 * This method finds all nearby entity living base and filter them with a
	 * list of classes. It also prioritizes players.
	 */
	protected EntityLivingBase findEntityLivingBaseToAttack(double searchDistance)
	{
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(searchDistance, searchDistance / 2.0D, searchDistance));
		List<Class> filterList = this.getTargetList();

		double lastDistanceFromCreature = Double.MAX_VALUE;
		EntityPlayer playerTarget = null;
		EntityLivingBase target = null;

		for (Entity entityFromList : list)
		{
			if (filterList.contains(entityFromList.getClass()))
			{
				double distanceFromCreature = this.getDistanceSq(entityFromList.getPosition());
				if (distanceFromCreature < lastDistanceFromCreature && this.canEntityBeSeen(entityFromList))
				{
					lastDistanceFromCreature = distanceFromCreature;
					target = (EntityLivingBase) entityFromList;
					if (target instanceof EntityPlayer)
						playerTarget = (EntityPlayer) target;
				}
			}
		}

		if (playerTarget != null)
			return playerTarget;
		else
			return target;
	}

	private void setWaterAttackTarget(EntityLivingBase target)
	{
		if (target == null)
		{
			this.isAttacking = false;
			this.waterTargetEntity = (EntityLivingBase) null;
		}
		else
		{
			if (this.isInWater() && target.isInWater())
			{
				this.isAttacking = true;
				this.waterTargetEntity = target;
				this.swimmingTarget = target.getPosition();
			}
			else if (!this.isInWater() && !target.isInWater() && this.getAir() > (int) (this.getMaxAir() / 2.0F))
			{
				this.isAttacking = true;
				this.waterTargetEntity = target;
				this.swimmingTarget = target.getPosition();
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
			return false;
		else
		{
			Entity entity = source.getEntity();

			if (entity instanceof EntityLivingBase)
			{
				EntityLivingBase attacker = (EntityLivingBase) entity;
				if (attacker instanceof EntityPlayer && ((EntityPlayer) attacker).capabilities.isCreativeMode)
				{

				}
				else
				{
					List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(16.0D, 8.0D, 16.0D));
					list.add(this);

					for (Entity entityFromList : list)
					{
						if (entityFromList.getClass() == this.getClass())
						{
							EntityDeExtinctedWaterAggressive validNearEntity = (EntityDeExtinctedWaterAggressive) entityFromList;

							if (validNearEntity.isSitting())
								validNearEntity.setSitting(false, null);

							if (validNearEntity.isSleeping())
								validNearEntity.setSleeping(false);

							if (validNearEntity.isOnNest())
								validNearEntity.setOnNest(false);

							if (validNearEntity.ridingEntity != null)
								validNearEntity.mountEntity((Entity) null);

							validNearEntity.setWaterAttackTarget(attacker);
						}
					}
				}
			}

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
				amount = (amount + 1.0F) / 2.0F;

			return super.attackEntityFrom(source, amount);
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()));
		if (flag)
			this.func_174815_a(this, entity);
		return flag;
	}

	@Override
	public void applyEntityCollision(Entity entity)
	{
		super.applyEntityCollision(entity);
		if (this.waterTargetEntity == entity)
			this.attackEntityAsMob(entity);
	}

	protected abstract int getAttackChance();

	protected abstract double getAttackSpeedBonus();

	public abstract List<Class> getTargetList();
}
