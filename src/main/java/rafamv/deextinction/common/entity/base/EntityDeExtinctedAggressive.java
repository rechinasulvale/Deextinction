package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedAggressive extends EntityDeExtinctedAgeable
{

	public EntityDeExtinctedAggressive(World world)
	{
		super(world);
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
					List<EntityDeExtinctedAggressive> list = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.getEntityBoundingBox().expand(this.aggressiveAttackRadius(), this.aggressiveAttackRadius() / 2.0D, this.aggressiveAttackRadius()));

					for (EntityDeExtinctedAggressive nearEntity : list)
					{
						if (nearEntity.isSitting())
							nearEntity.setSitting(false, null);

						if (nearEntity.isOnNest())
							nearEntity.setOnNest(false);

						if (nearEntity.ridingEntity != null)
							nearEntity.mountEntity((Entity) null);

						nearEntity.setRevengeTarget(attacker);
						nearEntity.setAttackTarget(attacker);
					}
				}
			}

			if (entity != null && !(entity instanceof EntityArrow))
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

	public double aggressiveAttackRadius()
	{
		return 10.0D;
	}
}
