package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedFlyingCoward extends EntityDeExtinctedFlyingCreature
{

	public EntityDeExtinctedFlyingCoward(World world)
	{
		super(world);
	}

	@Override
	protected void handleMovement()
	{
		if (this.motionY < 0.0D)
			this.motionY *= 0.6D;

		if (!this.worldObj.isRemote)
		{
			if (this.isSitting() || this.isOnNest() || this.isSleeping())
			{
				if (this.getAttackTarget() != null)
				{
					if (this.isSitting())
						this.setSitting(false, null);
					if (this.isOnNest())
						this.setOnNest(false);
					if (this.isSleeping())
						this.setSleeping(false);
					this.setFlying(true);
				}
			}
			else
			{
				if (this.isIndependent())
				{
					if (this.getAttackTarget() == null)
					{
						if (this.rand.nextInt(300) == 0)
							this.setFlying(!this.isFlying());
					}
					else
					{
						if (this.rand.nextInt(60) == 0)
							this.setAttackTarget(null);

						if (!this.isFlying())
							this.setFlying(true);
					}

					if (this.isFlying())
					{
						this.flyAroundUntilXZ(this.onGround, 15, 5, 15);
						if (this.rand.nextInt(500) == 0)
						{
							this.flyingTarget = null;
							this.flyAroundUntilXZ(this.onGround, 30, 8, 30);
						}
						if (this.isCollidedHorizontally && this.rand.nextInt(10) == 0)
							this.setFlying(false);
					}
					else if (this.inWater)
						this.motionY += 0.2F;
				}
				else
				{
					if (this.getAttackTarget() == null)
					{
						if (this.rand.nextInt(150) == 0)
							this.jump();
						else if (this.rand.nextInt(600) == 0)
							this.setFlying(true);
					}
					else
					{
						if (this.rand.nextInt(60) == 0)
							this.setAttackTarget(null);

						if (!this.isFlying())
							this.setFlying(true);
					}

					if (this.isFlying())
					{
						this.flyAroundUntilXZ(this.onGround, 5, 3, 5);

						if (this.isCollidedHorizontally && this.rand.nextInt(10) == 0)
							this.setFlying(false);
					}
					else if (this.inWater)
						this.motionY += 0.2F;
				}
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
			if (amount == 0 && this.isFlying())
				return false;

			Entity entity = source.getEntity();

			if (entity instanceof EntityLivingBase)
			{
				EntityLivingBase attacker = (EntityLivingBase) entity;
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(16.0D, 8.0D, 16.0D));
				list.add(this);

				for (int i = 0; i < list.size(); i++)
				{
					Entity nearEntity = (Entity) list.get(i);
					if (nearEntity.getClass() == this.getClass())
					{
						EntityDeExtinctedFlyingCoward validNearEntity = (EntityDeExtinctedFlyingCoward) nearEntity;

						if (validNearEntity.isSitting())
							validNearEntity.setSitting(false, null);

						if (validNearEntity.isSleeping())
							validNearEntity.setSleeping(false);

						if (validNearEntity.isOnNest())
							validNearEntity.setOnNest(false);

						if (validNearEntity.ridingEntity != null)
							validNearEntity.mountEntity((Entity) null);

						validNearEntity.setAttackTarget(attacker);
					}
				}
			}

			if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
				amount = (amount + 1.0F) / 2.0F;

			return super.attackEntityFrom(source, amount);
		}
	}

	public void forceFly()
	{
		if (!this.isFlying())
			this.setFlying(true);
		this.flyingTarget = null;
		this.flyAroundUntilXZ(this.onGround, 30, 8, 30);
	}

	@Override
	public float getFlyingSpeed()
	{
		return 1.5F * (float) this.getCreatureSpeed();
	}
}
