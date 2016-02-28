package rafamv.deextinction.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCultivatedChickenEgg extends EntityEgg
{
	public EntityCultivatedChickenEgg(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
	}

	@Override
	protected void onImpact(MovingObjectPosition obj)
	{
		if (obj.entityHit != null)
			obj.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);

		if (!this.worldObj.isRemote)
		{
			int numberOfHatchlings = 1;
			if (this.rand.nextInt(16) == 0)
				numberOfHatchlings = this.rand.nextInt(4);

			for (int i = 0; i < numberOfHatchlings; i++)
			{
				EntityChicken entitychicken = new EntityChicken(this.worldObj);
				entitychicken.setGrowingAge(-24000);
				entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
				this.worldObj.spawnEntityInWorld(entitychicken);
			}
		}

		for (int j = 0; j < 8; ++j)
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, ((double) this.rand.nextFloat() - 0.5D) * 0.08D, new int[] { Item.getIdFromItem(Items.egg) });

		if (!this.worldObj.isRemote)
			this.setDead();
	}
}
