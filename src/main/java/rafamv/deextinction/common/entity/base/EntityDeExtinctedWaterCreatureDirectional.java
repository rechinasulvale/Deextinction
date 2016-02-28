package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedWaterCreatureDirectional extends EntityDeExtinctedAgeable
{
	protected BlockPos currentSwimTarget;
	protected double swimDirectionX = 0.0D;
	protected double swimDirectionY = 0.0D;
	protected double swimDirectionZ = 0.0D;
	protected boolean shouldSwim;
	protected int idleInWater;

	public EntityDeExtinctedWaterCreatureDirectional(World world)
	{
		super(world);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.currentSwimTarget = this.getPosition();
		this.shouldSwim = true;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.isCreatureInWater())
		{
			if (this.rand.nextInt(this.getWaterAttackChance()) == 0 && this.shouldHunt())
				this.setAttackTarget(this.findEntityLivingBaseToAttack(this.getWaterAttackSearchDistance()));

			if (!this.worldObj.isRemote)
			{
				if (this.getAttackTarget() != null)
				{
					EntityLivingBase target = this.getAttackTarget();
					this.currentSwimTarget = new BlockPos(target.posX, target.posY + (double) target.getEyeHeight(), target.posZ);
					this.swimToTarget();
				}
				else
				{
					if (this.shouldSwim)
						this.swimDirectional();
					else
					{
						if (this.idleInWater-- < 0)
							this.shouldSwim = true;
					}
				}
			}
			this.renderYawOffset += (-((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.14159265359F - this.renderYawOffset) * 0.1F;
			this.rotationYaw = this.renderYawOffset;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				if (this.onGround && this.rand.nextInt(1 + MathHelper.clamp_int(this.getMaxAir() - this.getAir(), 0, this.getMaxAir())) == 0)
					this.jumpToRandomDirection(this.getCreatureSpeed());
			}
		}
	}

	@Override
	public void onEntityUpdate()
	{
		int i = this.getAir();
		super.onEntityUpdate();

		if (this.isEntityAlive() && !this.isCreatureInWater())
		{
			i--;
			this.setAir(i);

			if (this.getAir() == -20)
			{
				this.setAir(0);
				this.attackEntityFrom(DamageSource.drown, 2.0F);
			}
		}
		else
			this.setAir(this.getMaxAir());
	}

	public void swimDirectional()
	{
		if (this.rand.nextInt(this.getWaterTargetChangeChance()) == 0 || !this.isWaterBlock(this.currentSwimTarget))
		{
			double dirX = this.rand.nextDouble();
			double dirY = 0.2D * this.rand.nextDouble();
			double dirZ = this.rand.nextDouble();
			double dirMax = Math.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ);

			this.swimDirectionX = dirX / dirMax;
			this.swimDirectionY = dirY / dirMax;
			this.swimDirectionZ = dirZ / dirMax;

			System.out.println("Direction: " + this.swimDirectionX + ", " + this.swimDirectionY + ", " + this.swimDirectionZ);
			System.out.println("Target = " + this.currentSwimTarget);
		}

		this.swim(this.swimDirectionX, this.swimDirectionY, this.swimDirectionZ);

		if (this.rand.nextInt(this.getWaterIdleChance()) == 0)
		{
			this.shouldSwim = false;
			this.idleInWater = this.getWaterIdleTime();
		}
	}

	protected void swim(double addX, double addY, double addZ)
	{
		this.motionX += (Math.signum(addX + 0.5D) * 0.5D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(addY + 1.0D) * 0.699999988079071D - this.motionY) * 0.010000000149011612D;
		this.motionZ += (Math.signum(addZ + 0.5D) * 0.5D - this.motionZ) * 0.10000000149011612D;
		this.motionY -= 0.01D;
		this.moveForward = this.getCreatureSpeedInWater();
		this.currentSwimTarget = new BlockPos(this.posX + addX, this.posY, this.posZ + addZ);
	}

	protected void swimToTarget()
	{
		double targetX = this.currentSwimTarget.getX() + 0.5D - this.posX;
		double targetY = this.currentSwimTarget.getY() - this.posY;
		double targetZ = this.currentSwimTarget.getZ() + 0.5D - this.posZ;
		this.motionX += (Math.signum(targetX) * 0.3D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(targetY) * 0.599999988079071D - this.motionY) * 0.010000000149011612D;
		this.motionZ += (Math.signum(targetZ) * 0.3D - this.motionZ) * 0.10000000149011612D;
		this.motionY -= 0.01D;
		this.moveForward = this.getCreatureSpeedInWater();
	}

	public void jumpToTarget(int posX, int posZ, double speenInWater)
	{
		double targetX = this.currentSwimTarget.getX() + 0.5D - this.posX;
		double targetZ = this.currentSwimTarget.getZ() + 0.5D - this.posZ;
		this.motionY += 0.4D;
		this.motionX += (Math.signum(targetX) * 0.3D - this.motionX) * 0.10000000149011612D;
		this.motionZ += (Math.signum(targetZ) * 0.3D - this.motionZ) * 0.10000000149011612D;
		this.rotationYaw = this.rand.nextFloat() * 360.0F;
		this.moveForward = this.getCreatureSpeedInWater();
		this.onGround = false;
		this.isAirBorne = true;
	}

	public void jumpToTarget(double speenInWater)
	{
		this.motionY += 0.4D;
		this.motionX += (Math.signum(this.currentSwimTarget.getX()) * 0.3D - this.motionX) * 0.10000000149011612D;
		this.motionZ += (Math.signum(this.currentSwimTarget.getZ()) * 0.3D - this.motionZ) * 0.10000000149011612D;
		this.rotationYaw = this.rand.nextFloat() * 360.0F;
		this.onGround = false;
		this.isAirBorne = true;
	}

	public void jumpToRandomDirection(double speenInWater)
	{
		this.motionY += 0.4D;
		this.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
		this.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
		this.rotationYaw = this.rand.nextFloat() * 360.0F;
		this.onGround = false;
		this.isAirBorne = true;
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

							validNearEntity.setRevengeTarget(attacker);
							validNearEntity.setAttackTarget(attacker);
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
		if (this.getAttackTarget() == entity)
			this.attackEntityAsMob(entity);
	}

	public boolean isCreatureInWater()
	{
		return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this);
	}

	public boolean isGrounded()
	{
		BlockPos pos = this.getPosition();
		return !this.isCreatureInWater() && this.worldObj.getBlockState(pos.up()).getBlock() == Blocks.air && this.worldObj.getBlockState(pos.down()).getBlock().isCollidable();
	}

	protected boolean isWaterBlock(BlockPos waterBlock)
	{
		return this.worldObj.getBlockState(waterBlock).getBlock().getMaterial() == Material.water;
	}

	@Override
	public boolean handleLavaMovement()
	{
		return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this);
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public boolean isPushedByWater()
	{
		return false;
	}

	protected abstract double getWaterAttackSearchDistance();

	protected abstract int getWaterTargetChangeChance();

	protected abstract float getCreatureSpeedInWater();

	protected abstract List<Class> getTargetList();

	protected abstract int getWaterAttackChance();

	protected abstract int getWaterIdleChance();

	protected abstract int getWaterIdleTime();

	protected abstract int getMaxAir();
}
