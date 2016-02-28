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

public abstract class EntityDeExtinctedWaterCreatureConfused extends EntityDeExtinctedAgeable
{
	protected BlockPos currentSwimTarget;

	public EntityDeExtinctedWaterCreatureConfused(World world)
	{
		super(world);
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
					this.swimAbout();
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

	public void swimAbout()
	{
		if (this.currentSwimTarget != null && !this.isWaterBlock(this.currentSwimTarget))
			this.currentSwimTarget = null;

		if (this.currentSwimTarget == null || this.rand.nextInt(30) == 0 || this.currentSwimTarget.distanceSq(this.posX, this.posY, this.posZ) < 10.0F)
		{
			int swimRadius = this.getSwimRadius();
			int invertX = this.rand.nextBoolean() ? 1 : -1;
			int invertZ = this.rand.nextBoolean() ? 1 : -1;
			this.currentSwimTarget = new BlockPos(this.posX + invertX * this.rand.nextInt(swimRadius), this.posY + this.rand.nextInt((int) (swimRadius / 2)) + 1, this.posZ + invertZ * this.rand.nextInt(swimRadius));
		}
		System.out.println("Target: " + this.currentSwimTarget);
		this.swimToTarget();
	}

	protected void swimToTarget()
	{
		double targetX = this.currentSwimTarget.getX() + 0.5D - this.posX;
		double targetY = this.currentSwimTarget.getY() + 1.0D - this.posY;
		double targetZ = this.currentSwimTarget.getZ() + 0.5D - this.posZ;
		this.motionX += (Math.signum(targetX) * 0.5D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(targetY) * 0.699999988079071D - this.motionY) * 0.010000000149011612D;
		this.motionZ += (Math.signum(targetZ) * 0.5D - this.motionZ) * 0.10000000149011612D;
		this.motionY -= 0.01D;
		this.moveForward = this.getCreatureSpeedInWater();
	}

	protected void swimToTarget(int posX, int posY, int posZ)
	{
		double targetX = posX + 0.5D - this.posX;
		double targetY = posY + 1.0D - this.posY;
		double targetZ = posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(targetX) * 0.5D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(targetY) * 0.699999988079071D - this.motionY) * 0.010000000149011612D;
		this.motionZ += (Math.signum(targetZ) * 0.5D - this.motionZ) * 0.10000000149011612D;
		this.motionY -= 0.01D;
		this.moveForward = this.getCreatureSpeedInWater();
	}

	public void jumpToTarget(double speenInWater)
	{
		this.motionY += 0.4D;
		this.motionX += ((double) Math.signum((float) this.currentSwimTarget.getX()) * 0.3D - this.motionX) * 0.10000000149011612D;
		this.motionZ += ((double) Math.signum((float) this.currentSwimTarget.getZ()) * 0.3D - this.motionZ) * 0.10000000149011612D;
		this.rotationYaw = this.rand.nextFloat() * 360.0F;
		this.onGround = false;
		this.isAirBorne = true;
	}

	public void jumpToTarget(int posX, int posZ, double speenInWater)
	{
		this.motionY += 0.4D;
		this.motionX += ((double) Math.signum((float) posX) * 0.3D - this.motionX) * 0.10000000149011612D;
		this.motionZ += ((double) Math.signum((float) posZ) * 0.3D - this.motionZ) * 0.10000000149011612D;
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

	protected abstract float getCreatureSpeedInWater();

	protected abstract List<Class> getTargetList();

	protected abstract int getWaterAttackChance();

	protected abstract int getSwimRadius();

	protected abstract int getMaxAir();
}
