package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedCoward extends EntityDeExtinctedAgeable
{

	public EntityDeExtinctedCoward(World world)
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
					List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(16.0D, 8.0D, 16.0D));
					list.add(this);

					for (int i = 0; i < list.size(); i++)
					{
						Entity nearEntity = (Entity) list.get(i);
						if (nearEntity.getClass() == this.getClass())
						{
							EntityDeExtinctedCoward validNearEntity = (EntityDeExtinctedCoward) nearEntity;

							if (validNearEntity.isSitting())
								validNearEntity.setSitting(false, null);

							if (validNearEntity.isSleeping())
								validNearEntity.setSleeping(false);

							if (validNearEntity.isOnNest())
								validNearEntity.setOnNest(false);

							if (validNearEntity.ridingEntity != null)
								validNearEntity.mountEntity((Entity) null);

							validNearEntity.setFleeing(true);
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
}
