package rafamv.deextinction.common.entity.ai;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedWaterCreatureBasic;

public class DEAIWaterWander extends EntityAIBase
{
	private EntityDeExtinctedWaterCreatureBasic creature;
	private Random rand;
	private double waterSpaceFromTarget;
	private double speedInWater;
	private double swimRadius;
	private double waypointX;
	private double waypointY;
	private double waypointZ;
	private int chance;
	private int duration;
	private int timer;

	public DEAIWaterWander(EntityDeExtinctedWaterCreatureBasic creature, double swimRadius, int chance, int duration)
	{
		this.creature = creature;
		this.rand = creature.getRNG();
		this.swimRadius = swimRadius;
		this.duration = duration;
		this.chance = chance;
		this.timer = 0;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.rand.nextInt(this.chance) == 0)
		{
			if (this.creature.isSitting() || this.creature.isSleeping() || this.creature.isOnNest())
				return false;

			if (this.creature.isInWater())
				return this.findNewWaterBlock(3);
		}
		return false;
	}

	@Override
	public void startExecuting()
	{
		if (this.creature.isSitting())
			this.creature.setSitting(false, null);

		if (this.creature.isSleeping())
			this.creature.setSleeping(false);

		if (this.creature.isOnNest())
			this.creature.setOnNest(false);

		this.timer = this.duration + this.rand.nextInt(this.duration);
		this.waterSpaceFromTarget = 1.0D + (double) this.creature.width;
		this.speedInWater = this.creature.getCreatureSpeedInWater();
	}

	@Override
	public void updateTask()
	{
		double distanceX = this.waypointX - this.creature.posX;
		double distanceY = this.waypointY - this.creature.posY;
		double distanceZ = this.waypointZ - this.creature.posZ;
		double distanceSq = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;

		double distance = Math.sqrt(distanceSq);
		this.creature.motionX += distanceX / distance * 0.05D * this.speedInWater;
		this.creature.motionY += distanceY / distance * 0.10D * this.speedInWater;
		this.creature.motionZ += distanceZ / distance * 0.05D * this.speedInWater;

		this.creature.rotationPitch += ((float) Math.atan2(this.creature.motionY, Math.sqrt(this.creature.motionX * this.creature.motionX + this.creature.motionZ * this.creature.motionZ)) * 180.0F / (float) Math.PI - this.creature.rotationPitch) * 0.5F;
		this.creature.renderYawOffset += (-((float) Math.atan2(this.creature.motionX, this.creature.motionZ)) * 180.0F / (float) Math.PI - this.creature.renderYawOffset) * 0.5F;
		this.creature.rotationYaw = this.creature.renderYawOffset;

		if (distanceSq < this.waterSpaceFromTarget)
			this.findNewWaterBlock(3);
	}

	@Override
	public boolean continueExecuting()
	{
		return this.timer-- > 0 && this.creature.isEntityAlive() && this.creature.isInWater();
	}

	@Override
	public void resetTask()
	{
		this.timer = -1;
		this.resetTarget();
	}

	public void setToAttackTarget(EntityLivingBase target)
	{
		double waypointX = 2.0D * this.creature.posX - target.posX;
		double waypointY = 2.0D * this.creature.posY - target.posY;
		double waypointZ = 2.0D * this.creature.posZ - target.posZ;
		if (this.isWaterBlock(waypointX, waypointY, waypointZ))
		{
			this.waypointX = waypointX;
			this.waypointY = waypointY;
			this.waypointZ = waypointZ;
		}
	}

	public boolean findNewWaterBlock(int numberOfTries)
	{
		for (int attempt = 0; attempt < numberOfTries; attempt++)
		{
			this.waypointX = this.creature.posX + (2.0D * this.rand.nextDouble() - 1.0D) * this.swimRadius;
			this.waypointY = this.creature.posY + (2.0D * this.rand.nextDouble() - 1.0D) * (this.swimRadius / 2.0D);
			this.waypointZ = this.creature.posZ + (2.0D * this.rand.nextDouble() - 1.0D) * this.swimRadius;
			if (this.isWaterBlock(this.waypointX, this.waypointY, this.waypointZ))
				return true;
		}
		return false;
	}

	public void resetTarget()
	{
		this.waypointX = this.creature.posX;
		this.waypointY = this.creature.posY;
		this.waypointZ = this.creature.posZ;
	}

	public void randomJump(EntityLivingBase entity, double speenInWater)
	{
		entity.motionY += 0.4D;
		entity.motionX += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
		entity.motionZ += (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * speenInWater);
		entity.rotationYaw = this.rand.nextFloat() * 360.0F;
		entity.onGround = false;
		entity.isAirBorne = true;
	}

	public void randomJump(EntityLivingBase entity)
	{
		entity.motionY += 0.4D;
		entity.motionX += (this.rand.nextDouble() * 2.0D - 1.0D) * 0.4D;
		entity.motionZ += (this.rand.nextDouble() * 2.0D - 1.0D) * 0.4D;
		entity.rotationYaw = this.rand.nextFloat() * 360.0F;
		entity.onGround = false;
		entity.isAirBorne = true;
	}

	public boolean isWaterBlock(double posX, double posY, double posZ)
	{
		return this.isWaterBlock(new BlockPos(posX, posY, posZ));
	}

	public boolean isWaterBlock(BlockPos pos)
	{
		return this.creature.worldObj.getBlockState(pos).getBlock().getMaterial() == Material.water;
	}

	public boolean isBBInWater(AxisAlignedBB axisAlignedBB)
	{
		int i = MathHelper.floor_double(axisAlignedBB.minX);
		int j = MathHelper.floor_double(axisAlignedBB.maxX + 1.0D);
		int k = MathHelper.floor_double(axisAlignedBB.minY);
		int l = MathHelper.floor_double(axisAlignedBB.maxY + 1.0D);
		int i1 = MathHelper.floor_double(axisAlignedBB.minZ);
		int j1 = MathHelper.floor_double(axisAlignedBB.maxZ + 1.0D);
		for (int k1 = i; k1 < j; k1++)
			for (int l1 = k; l1 < l; l1++)
				for (int i2 = i1; i2 < j1; i2++)
				{
					if (this.creature.worldObj.getBlockState(new BlockPos(k1, l1, i2)).getBlock().getMaterial() == Material.water)
						return true;
				}
		return false;
	}
}
