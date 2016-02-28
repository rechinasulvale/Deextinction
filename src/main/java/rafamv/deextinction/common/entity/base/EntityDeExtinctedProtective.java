package rafamv.deextinction.common.entity.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedProtective extends EntityDeExtinctedAgeable
{

	public EntityDeExtinctedProtective(World world)
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
					ArrayList<EntityDeExtinctedProtective> listChildren = new ArrayList<EntityDeExtinctedProtective>();
					ArrayList<EntityDeExtinctedProtective> listAdult = new ArrayList<EntityDeExtinctedProtective>();

					List<EntityDeExtinctedProtective> list = this.worldObj.getEntitiesWithinAABB(this.getClass(), this.getEntityBoundingBox().expand(this.protectiveAttackRadius(), this.protectiveAttackRadius() / 2.0D, this.protectiveAttackRadius()));
					for (EntityDeExtinctedProtective nearEntity : list)
					{
						if (nearEntity.isIndependent())
							listAdult.add(nearEntity);
						else
							listChildren.add(nearEntity);
					}

					if (this.isSleeping())
						this.setSleeping(false);

					for (EntityDeExtinctedProtective childEntity : listChildren)
					{
						if (childEntity.isSitting())
							childEntity.setSitting(false, null);

						if (childEntity.isOnNest())
							childEntity.setOnNest(false);

						if (childEntity.ridingEntity != null)
							childEntity.mountEntity((Entity) null);

						childEntity.setRevengeTarget(attacker);
						childEntity.setFleeing(true);
					}

					for (EntityDeExtinctedProtective adultEntity : listAdult)
					{
						if (adultEntity.isSitting())
							adultEntity.setSitting(false, null);

						if (adultEntity.isOnNest())
							adultEntity.setOnNest(false);

						if (adultEntity.ridingEntity != null)
							adultEntity.mountEntity((Entity) null);

						adultEntity.setRevengeTarget(attacker);
						adultEntity.setAttackTarget(attacker);
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

	public double protectiveAttackRadius()
	{
		return 10.0D;
	}
}
