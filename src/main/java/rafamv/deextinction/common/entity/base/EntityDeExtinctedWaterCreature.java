package rafamv.deextinction.common.entity.base;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedWaterCreature extends EntityDeExtinctedAgeable
{
	protected int updateSwimmingTarget = 0;
	protected int findWaterTimer = 0;
	protected BlockPos swimmingTarget;

	public EntityDeExtinctedWaterCreature(World world)
	{
		super(world);
		this.swimmingTarget = this.getPosition();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.isInWater())
			this.motionY *= 0.1D;
	}

	@Override
	public void onEntityUpdate()
	{
		int air = this.getAir() - 1;

		super.onEntityUpdate();
		if (this.isEntityAlive() && !this.isInWater())
		{
			this.setAir(air);
			if (air == -10)
			{
				this.setAir(0);
				this.attackEntityFrom(DamageSource.drown, 2.0F);
			}
		}
		else
			this.setAir(this.getMaxAir());
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		if (this.isInWater())
			this.handleCreatureMovementInWater();
		else
			this.handleCreatureMovementOnLand();
	}

	/** This is what the creature does when it's in water. */
	protected void handleCreatureMovementInWater()
	{
		System.out.println(this.swimmingTarget + ", update: " + this.updateSwimmingTarget);

		double distanceX = this.swimmingTarget.getX() - this.posX;
		double distanceY = this.swimmingTarget.getY() - this.posY;
		double distanceZ = this.swimmingTarget.getZ() - this.posZ;
		double distanceSq = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

		double swimRadius = this.getSwimRadius();
		if (distanceSq < 4.0D || distanceSq > Math.pow(swimRadius, 3.0D) || this.updateSwimmingTarget > 20)
		{
			this.swimmingTarget = new BlockPos(this.posX + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadius, this.posY + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadius, this.posZ + (2.0D * this.rand.nextDouble() - 1.0D) * swimRadius);
			this.updateSwimmingTarget = 0;
		}

		if (this.isWaterBlock(this.swimmingTarget))
		{
			double swimSpeed = this.getSwimSpeed();
			double distance = Math.sqrt(distanceSq);

			this.motionX += distanceX / distance * 0.05D * swimSpeed;
			this.motionY += distanceY / distance * 0.10D * swimSpeed;
			this.motionZ += distanceZ / distance * 0.05D * swimSpeed;
		}
		else
			this.updateSwimmingTarget++;

		this.handleCreatureAttackInWater();

		this.rotationPitch += ((float) Math.atan2(this.motionY, Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)) * 180.0F / (float) Math.PI - this.rotationPitch) * 0.5F;
		this.renderYawOffset += (-((float) Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float) Math.PI - this.renderYawOffset) * 0.5F;
		this.rotationYaw = this.renderYawOffset;
	}

	protected abstract void handleCreatureAttackInWater();

	/**
	 * This is what the creature does when it's on land. By default it should
	 * jump.
	 */
	protected void handleCreatureMovementOnLand()
	{
		if (this.onGround && this.rand.nextInt(20) == 0)
		{
			this.motionY = 0.3F;
			this.motionX = -0.2F + this.rand.nextFloat() * 0.4F;
			this.motionZ = -0.2F + this.rand.nextFloat() * 0.4F;
		}
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return !this.inWater;
	}

	@Override
	public boolean isInWater()
	{
		return this.worldObj.handleMaterialAcceleration(this.getEntityBoundingBox(), Material.water, this);
	}

	protected boolean isWaterBlock(BlockPos waterBlock)
	{
		return this.worldObj.getBlockState(waterBlock).getBlock().getMaterial() == Material.water;
	}

	protected abstract double getSwimSpeed();

	protected abstract double getSwimRadius();

	protected abstract int getMaxAir();
}
