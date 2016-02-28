package rafamv.deextinction.common.entity.base;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedFlyingCreature extends EntityDeExtinctedAgeable
{
	public BlockPos flyingTarget;

	public EntityDeExtinctedFlyingCreature(World world)
	{
		super(world);
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{

	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.handleMovement();
	}

	/** Returns true if the target is invalid */
	public boolean validateTarget(BlockPos pos)
	{
		return !this.worldObj.isAirBlock(pos);
	}

	public void flyTowardsTarget(BlockPos pos)
	{
		if (pos != null)
		{
			double targetX = pos.getX() + 0.5D - this.posX;
			double targetY = pos.getY() + 1.0D - this.posY;
			double targetZ = pos.getZ() + 0.5D - this.posZ;
			this.motionX += (Math.signum(targetX) * 0.5D - this.motionX) * 0.10000000149011612D;
			this.motionY += (Math.signum(targetY) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
			this.motionZ += (Math.signum(targetZ) * 0.5D - this.motionZ) * 0.10000000149011612D;
			float angle = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float rotation = MathHelper.wrapAngleTo180_float(angle - this.rotationYaw);
			this.moveForward = this.getFlyingSpeed();
			this.rotationYaw += rotation;
		}
	}

	public void flyAround(boolean shouldJump, int rangeX, int rangeY, int rangeZ)
	{
		if (this.isEntityAlive())
		{
			if (shouldJump || this.rand.nextInt(100) == 0)
				this.jump();

			if (this.flyingTarget == null || this.getDistanceSq(this.flyingTarget) < 4.0F)
			{
				this.flyingTarget = new BlockPos((int) this.posX - (int) (rangeX / 2) + this.rand.nextInt(rangeX), (int) this.posY + this.rand.nextInt(rangeY) - 2, (int) this.posZ - (int) (rangeX / 2) + this.rand.nextInt(rangeZ));

				if (this.validateTarget(this.flyingTarget))
					this.flyingTarget = null;
			}

			this.flyTowardsTarget(this.flyingTarget);
		}
	}

	public void flyAroundUntilXZ(boolean shouldJump, int rangeX, int rangeY, int rangeZ)
	{
		if (this.isEntityAlive())
		{
			if (shouldJump || this.rand.nextInt(100) == 0)
				this.jump();

			if (this.flyingTarget == null || this.getDistanceSq(this.flyingTarget) < 4.0F || ((int) this.posX == this.flyingTarget.getX() || (int) this.posZ == this.flyingTarget.getZ()))
			{
				this.flyingTarget = new BlockPos((int) this.posX - (int) (rangeX / 2) + this.rand.nextInt(rangeX), (int) this.posY + this.rand.nextInt(rangeY) - 2, (int) this.posZ - (int) (rangeX / 2) + this.rand.nextInt(rangeZ));

				if (this.validateTarget(this.flyingTarget))
					this.flyingTarget = null;
			}

			this.flyTowardsTarget(this.flyingTarget);
		}
	}

	public void flyAround(boolean shouldJump, int rangeX, int rangeY, int rangeZ, int chanceToChangeTarget)
	{
		if (this.isEntityAlive())
		{
			if (shouldJump || this.rand.nextInt(100) == 0)
				this.jump();

			if (this.flyingTarget == null || this.getDistanceSq(this.flyingTarget) < 4.0F || this.rand.nextInt(chanceToChangeTarget) == 0)
			{
				this.flyingTarget = new BlockPos((int) this.posX - (int) (rangeX / 2) + this.rand.nextInt(rangeX), (int) this.posY + this.rand.nextInt(rangeY) - 2, (int) this.posZ - (int) (rangeX / 2) + this.rand.nextInt(rangeZ));

				if (this.validateTarget(this.flyingTarget))
					this.flyingTarget = null;
			}

			this.flyTowardsTarget(this.flyingTarget);
		}
	}

	protected abstract void handleMovement();

	public abstract float getFlyingSpeed();
}
