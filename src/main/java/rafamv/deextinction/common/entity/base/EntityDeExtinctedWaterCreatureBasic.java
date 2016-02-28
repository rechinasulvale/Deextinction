package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.base.Predicate;

public abstract class EntityDeExtinctedWaterCreatureBasic extends EntityDeExtinctedAgeable
{
	protected BlockPos swimmingTarget;

	public EntityDeExtinctedWaterCreatureBasic(World world)
	{
		super(world);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.inWater)
			this.motionY *= 0.15D;
		else if (this.rand.nextInt(15) == 0)
			this.randomJump(this, this.getCreatureSpeedInWater());
	}

	@Override
	public void onEntityUpdate()
	{
		int air = this.getAir();

		super.onEntityUpdate();
		if (this.isEntityAlive() && !this.isInWater())
		{
			air--;
			this.setAir(air);
			if (this.getAir() == -10)
			{
				this.setAir(0);
				this.attackEntityFrom(DamageSource.drown, 2.0F);
			}
		}
		else
			this.setAir(300);
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public boolean handleLavaMovement()
	{
		return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty();
	}

	public void lookToDistance(double distanceX, double distanceY, double distanceZ, double distanceSq)
	{
		EntityLookHelper entitylookhelper = this.getLookHelper();
		double newDistanceX = this.posX + distanceX / distanceSq * 2.0D;
		double newDistanceY = (double) this.getEyeHeight() + this.posY + distanceY / distanceSq * 1.0D;
		double newDistanceZ = this.posZ + distanceZ / distanceSq * 2.0D;
		double distanceY0 = entitylookhelper.func_180423_e();
		double distanceY1 = entitylookhelper.func_180422_f();
		double distanceY2 = entitylookhelper.func_180421_g();

		if (!entitylookhelper.func_180424_b())
		{
			distanceY0 = newDistanceX;
			distanceY1 = newDistanceY;
			distanceY2 = newDistanceZ;
		}
		this.getLookHelper().setLookPosition(distanceY0 + (newDistanceX - distanceY0) * 0.125D, distanceY1 + (newDistanceY - distanceY1) * 0.125D, distanceY2 + (newDistanceZ - distanceY2) * 0.125D, 10.0F, 40.0F);
	}

	public void randomJump(EntityLivingBase entity, double speenInWater)
	{
		if (entity.onGround)
		{
			entity.motionY += 0.4D;
			entity.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
			entity.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
			entity.rotationYaw = this.rand.nextFloat() * 360.0F;
			entity.onGround = false;
			entity.isAirBorne = true;
		}
	}

	public void randomJump(EntityLivingBase entity)
	{
		if (entity.onGround)
		{
			entity.motionY += 0.4D;
			entity.motionX += (this.rand.nextDouble() * 2.0D - 1.0D) * 0.4D;
			entity.motionZ += (this.rand.nextDouble() * 2.0D - 1.0D) * 0.4D;
			entity.rotationYaw = this.rand.nextFloat() * 360.0F;
			entity.onGround = false;
			entity.isAirBorne = true;
		}
	}

	protected float limitAngle(float angle, float f, float limitAngle)
	{
		float f3 = MathHelper.wrapAngleTo180_float(f - angle);

		if (f3 > limitAngle)
			f3 = limitAngle;

		if (f3 < -limitAngle)
			f3 = -limitAngle;

		float f4 = angle + f3;

		if (f4 < 0.0F)
			f4 += 360.0F;
		else if (f4 > 360.0F)
			f4 -= 360.0F;

		return f4;
	}

	@Override
	protected PathNavigate func_175447_b(World worldIn)
	{
		return new PathNavigateSwimmer(this, worldIn);
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return !this.inWater;
	}

	public abstract int getMaxAir();

	public abstract List<Class> getTargetList();

	public abstract double getCreatureSpeedInWater();

	class WaterTargetSelector implements Predicate
	{
		private EntityDeExtinctedWaterCreatureBasic creature = EntityDeExtinctedWaterCreatureBasic.this;

		public boolean validateTarget(EntityLivingBase target)
		{
			return this.creature.getTargetList() != null && this.creature.getTargetList().contains(target.getClass()) && target.getDistanceSqToEntity(this.creature) > 9.0D;
		}

		@Override
		public boolean apply(Object target)
		{
			return this.validateTarget((EntityLivingBase) target);
		}
	}
}
