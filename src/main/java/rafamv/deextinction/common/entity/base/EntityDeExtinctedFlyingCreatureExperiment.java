package rafamv.deextinction.common.entity.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import rafamv.deextinction.common.message.MessageFlyingEntity;
import rafamv.deextinction.common.registry.DEMessageRegistry;

/**
 * From GenProject.
 */
public abstract class EntityDeExtinctedFlyingCreatureExperiment extends EntityDeExtinctedAgeable
{
	public static final int FLYING_STATE = 17;

	private Vec3 targetLocation;
	public float prevRoll = 0;
	public float roll = 0;

	public EntityDeExtinctedFlyingCreatureExperiment(World world)
	{
		super(world);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(EntityDeExtinctedFlyingCreatureExperiment.FLYING_STATE, (byte) State.IDLE_GROUND.ordinal());
	}

	public State getState()
	{
		return State.values()[this.dataWatcher.getWatchableObjectByte(FLYING_STATE)];
	}

	public void setState(State state)
	{
		if (this.getState() != State.FLYING && state == State.FLYING)
		{
			this.setTargetLocation(null);
			this.sendUpdateMessage();
		}

		this.dataWatcher.updateObject(EntityDeExtinctedFlyingCreatureExperiment.FLYING_STATE, (byte) state.ordinal());
	}

	public void setTargetLocation(Vec3 location)
	{
		this.targetLocation = location;
	}

	public Vec3 getTargetLocation()
	{
		return this.targetLocation;
	}

	protected void sendUpdateMessage()
	{
		if (!worldObj.isRemote)
			DEMessageRegistry.sendToAllTracking(new MessageFlyingEntity(this), this);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		State state = getState();

		if (entityAge % 25 == 0)
			this.sendUpdateMessage();

		if (this.worldObj.isRemote)
		{
			double diffX = this.posX - this.prevPosX;
			double diffY = this.posY - this.prevPosY;
			double diffZ = this.posZ - this.prevPosZ;

			Vec3 posDiff = new Vec3(diffX, diffY, diffZ);
			double rads = Math.toRadians(this.rotationYaw);
			Vec3 forward = new Vec3(Math.cos(rads), 0.0D, Math.sin(rads));
			double dotSpeed = MathHelper.sqrt_double(posDiff.dotProduct(forward));
			double maxSpeed = 0.3D * this.getCreatureSpeed();

			final float tiltSpeed = 0.5F;

			// Calculate new pitch.
			float pitchTarget = 0;

			if (state.onSide)
			{
				pitchTarget = -90;
			}
			else if (state == State.LANDING_SIDE)
			{
				float amt = (float) (Math.max(maxSpeed - dotSpeed, 0) / maxSpeed);
				pitchTarget = -90 * amt;
			}
			else
			{
				double horizSpeed = MathHelper.sqrt_double((diffX * diffX + diffZ * diffZ));

				if (horizSpeed > 0.1)
				{
					float movePitch = (float) -Math.toDegrees(Math.atan2(diffY, horizSpeed));
					pitchTarget = movePitch;
				}
			}

			this.rotationPitch += MathHelper.wrapAngleTo180_float(pitchTarget - this.rotationPitch) * tiltSpeed;

			// Calculate new roll for banking.
			this.prevRoll = this.roll;

			if (state.category == StateCategory.LANDED)
				this.roll = 0;
			else
			{
				float diffYaw = this.rotationYaw - this.prevRotationYaw;
				diffYaw = this.rotationYawHead - this.prevRotationYawHead;
				diffYaw += this.renderYawOffset - this.prevRenderYawOffset;
				final float max = 15;
				this.roll = MathHelper.clamp_float(diffYaw, -max, max) / max * 45;
			}

			this.roll = this.prevRoll + (this.roll - this.prevRoll) * tiltSpeed;
		}
	}

	public void setPositionAndRotationWithoutPitch(double x, double y, double z, float yaw, float pitch, int increments, boolean unknown)
	{
		float oldRotationPitch = this.rotationPitch;
		float oldPrevRotationPitch = this.prevRotationPitch;
		this.newRotationPitch = this.rotationPitch = oldRotationPitch;
		this.prevRotationPitch = oldPrevRotationPitch;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void updateAITasks()
	{
		super.updateAITasks();

		Vec3 ourPos = this.getPositionVector();
		Vec3 ourMotion = new Vec3(this.motionX, this.motionY, this.motionZ);
		State ourState = this.getState();
		Vec3 ourOldTarget = this.getTargetLocation();
		Vec3 ourNewTarget = ourOldTarget;

		if (ourNewTarget == null)
			ourNewTarget = ourPos;

		BlockPos targetPos = new BlockPos(ourNewTarget);
		IBlockState atTarget = this.worldObj.getBlockState(targetPos);

		double targetDistance = ourNewTarget.squareDistanceTo(ourPos);

		double close = 0.75D;
		close *= close;
		double far = 1.0D;
		far *= far;
		boolean reachedClose = targetDistance <= close;
		boolean reachedFar = targetDistance <= far;

		ourState = getState();

		switch (ourState)
		{
			case FLYING:
				if (reachedFar)
					ourNewTarget = null;

				if (this.rand.nextInt(75) == 0)
				{
					double distance = 16 + this.rand.nextInt(17);

					for (int i = 0; i < 16; i++)
					{
						Vec3 random = new Vec3(MathHelper.getRandomDoubleInRange(this.rand, -1.0D, 1.0D), MathHelper.getRandomDoubleInRange(this.rand, -1.0D, 0.25D), MathHelper.getRandomDoubleInRange(this.rand, -1.0D, 1.0D)).normalize();
						Vec3 to = ourPos.addVector(random.xCoord * distance, random.yCoord * distance, random.zCoord * distance);
						MovingObjectPosition hit = this.worldObj.rayTraceBlocks(ourPos, to, true, false, false);

						if (hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
						{
							BlockPos checkPos = hit.getBlockPos();
							Block checkBlock = this.worldObj.getBlockState(checkPos).getBlock();

							if (!checkBlock.getMaterial().isLiquid())
							{
								boolean setTarget = false;

								switch (hit.sideHit)
								{
									case UP:
										this.setState(State.LANDING_GROUND);
										setTarget = true;
										break;
									case EAST:
									case NORTH:
									case SOUTH:
									case WEST:
										double offset = 0.0625 * 4;
										MovingObjectPosition aboveHit = this.worldObj.rayTraceBlocks(ourPos.addVector(0, offset, 0), to.addVector(0, offset, 0), true, false, false);

										if (aboveHit != null && aboveHit.typeOfHit == MovingObjectType.BLOCK && aboveHit.sideHit == hit.sideHit && (aboveHit.getBlockPos().equals(hit.getBlockPos()) || aboveHit.getBlockPos().equals(hit.getBlockPos().up())))
										{
											this.setState(State.LANDING_SIDE);
											setTarget = true;
										}
										break;
									case DOWN:
										break;
								}

								if (setTarget)
								{
									this.sendUpdateMessage();
									ourNewTarget = hit.hitVec;
									break;
								}
							}
						}
					}
				}

				break;
			case LANDING_GROUND:
				if (this.onGround && reachedClose)
				{
					this.setState(State.IDLE_GROUND);
				}
				break;
			case IDLE_GROUND:
			case IDLE_SIDE:
				if (this.rand.nextInt(100) == 0)
				{
					this.setState(State.FLYING);
				}
				else if (ourState == State.IDLE_GROUND)
				{
					if (!this.onGround)
						this.setState(State.FLYING);
				}
				else
				{
					Vec3 forward = ourNewTarget.subtract(ourPos);
					forward = forward.normalize();
					double epsilon = 0.01D;
					forward = forward.addVector(forward.xCoord * epsilon, forward.yCoord * epsilon, forward.zCoord * epsilon).add(ourPos);
					MovingObjectPosition hit = this.worldObj.rayTraceBlocks(ourPos, forward, true, false, false);

					if (hit == null || hit.typeOfHit != MovingObjectType.BLOCK)
						this.setState(State.FLYING);
				}
				break;
			case LANDING_SIDE:
				boolean calamites = atTarget.getBlock() == Blocks.log;

				if (reachedClose)
				{
					this.setState(State.IDLE_SIDE);
					this.sendUpdateMessage();
				}
				else
				{
					MovingObjectPosition hit = this.worldObj.rayTraceBlocks(ourPos, ourNewTarget, false, false, true);

					if (hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
					{
						EnumFacing side = hit.sideHit;

						switch (side)
						{
							case EAST:
							case NORTH:
							case SOUTH:
							case WEST:
								ourNewTarget = hit.hitVec;
								break;
							default:
								this.setState(State.FLYING);
								break;
						}
					}
				}
		}

		ourState = getState();

		boolean slowing = ourState.category == StateCategory.SLOW;
		boolean inAir = ourState.category == StateCategory.AIR || slowing;

		if (ourNewTarget != null && inAir)
		{
			if (ourState.category == StateCategory.AIR)
				ourNewTarget = null;
			else if (ourState == State.FLYING && this.entityAge % 20 == 0 && new Vec3(this.motionX, this.motionY, this.motionZ).squareDistanceTo(new Vec3(0, 0, 0)) < 0.01)
				ourNewTarget = null;
			else
			{
				MovingObjectPosition hit = this.worldObj.rayTraceBlocks(ourPos, ourNewTarget, false, false, true);

				if (hit != null && hit.typeOfHit == MovingObjectType.BLOCK)
				{
					if (ourState != State.LANDING_SIDE)
						ourNewTarget = null;
					else if (this.worldObj.getBlockState(hit.getBlockPos()).getBlock() != Blocks.log)
						ourNewTarget = null;
				}
				else if (slowing)
				{
					if (this.rand.nextInt(75) == 0)
						ourNewTarget = null;
				}
				else
				{
					if (this.rand.nextInt(30) == 0)
						ourNewTarget = null;
				}
			}
		}

		if (ourNewTarget == null)
		{
			double vertMove = MathHelper.getRandomDoubleInRange(this.rand, 4.0D, 8.0D);

			if (!this.isInWater())
			{
				Vec3 to = new Vec3(this.posX, 0, this.posZ);
				MovingObjectPosition hit = this.worldObj.rayTraceBlocks(ourPos, to, true, false, true);

				double ground = 0;

				if (hit != null)
				{
					ground = Math.min((hit.hitVec.yCoord + 0.5) - this.posY, 0);
				}

				vertMove += ground;

				to = new Vec3(this.posX, this.posY + vertMove, this.posZ);
				hit = this.worldObj.rayTraceBlocks(ourPos, to, true, false, true);

				if (hit != null)
				{
					double ceiling = Math.max((hit.hitVec.yCoord - 0.5) - this.posY, 0);
					vertMove = Math.min(vertMove, ceiling);
				}
			}

			Vec3 random = new Vec3(MathHelper.getRandomDoubleInRange(this.rand, -1.0D, 1.0D), 0, MathHelper.getRandomDoubleInRange(this.rand, -1.0D, 1.0D)).normalize();

			double distance = MathHelper.getRandomDoubleInRange(this.rand, 5.0D, 10.0D);
			random = new Vec3(random.xCoord * distance, random.yCoord * distance, random.zCoord * distance);
			random = random.addVector(0, vertMove, 0);

			this.setState(State.FLYING);
			ourNewTarget = ourPos.add(random);
		}

		double moveX = this.motionX, moveY = this.motionY, moveZ = this.motionZ;
		boolean strafe = ourState.onSide;

		if (inAir || strafe)
		{
			Vec3 moveVec = ourNewTarget.subtract(ourPos).normalize();
			float targetYaw = (float) (Math.toDegrees(Math.atan2(moveVec.zCoord, moveVec.xCoord)));
			float diffYaw = MathHelper.wrapAngleTo180_float(targetYaw - this.rotationYaw);

			final double maneuverability = 0.75;
			final double maxTurn = 30;
			double turn = MathHelper.clamp_double(diffYaw * maneuverability, -maxTurn, maxTurn);
			this.rotationYaw += turn;

			if (strafe)
			{
				moveX = moveVec.xCoord;
				moveZ = moveVec.zCoord;
			}
			else
			{
				double rads = Math.toRadians(this.rotationYaw);
				moveX = Math.cos(rads);
				moveZ = Math.sin(rads);
			}

			moveY = moveVec.yCoord;
		}

		double speed = 0.3D * this.getCreatureSpeed();

		if (slowing || !inAir)
			speed = Math.min(ourNewTarget.distanceTo(ourPos) / 10.0D, speed);

		moveX *= speed;
		moveZ *= speed;

		this.motionX += (moveX - this.motionX) * 0.5;
		this.motionY += (moveY - this.motionY) * 0.5;
		this.motionZ += (moveZ - this.motionZ) * 0.5;

		if (ourOldTarget == null || ourOldTarget.xCoord != ourNewTarget.xCoord || ourOldTarget.yCoord != ourNewTarget.yCoord || ourOldTarget.zCoord != ourNewTarget.zCoord)
			this.sendUpdateMessage();

		this.setTargetLocation(ourNewTarget);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		boolean ret = super.attackEntityFrom(source, amount);

		if (!worldObj.isRemote && amount > 0 && ret)
			this.setState(State.FLYING);

		return ret;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);

		String state = compound.getString("state");
		if (state != null && !"".equals(state))
		{
			setState(State.valueOf(state));
		}

		NBTTagCompound targetComp = compound.getCompoundTag("target");

		if (targetComp.hasKey("x") && targetComp.hasKey("y") && targetComp.hasKey("z"))
		{
			setTargetLocation(new Vec3(targetComp.getDouble("x"), targetComp.getDouble("y"), targetComp.getDouble("z")));
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);

		compound.setString("state", getState().toString());
		Vec3 target = getTargetLocation();

		if (target != null)
		{
			NBTTagCompound targetComp = new NBTTagCompound();
			targetComp.setDouble("x", target.xCoord);
			targetComp.setDouble("y", target.yCoord);
			targetComp.setDouble("z", target.zCoord);
			compound.setTag("target", targetComp);
		}
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected void collideWithEntity(Entity other)
	{

	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{

	}

	public static enum StateCategory
	{
		AIR,
		SLOW,
		LANDED;
	}

	public static enum State
	{
		FLYING(StateCategory.AIR),
		LANDING_GROUND(StateCategory.SLOW),
		LANDING_SIDE(StateCategory.SLOW),
		IDLE_GROUND(StateCategory.LANDED),
		IDLE_SIDE(StateCategory.LANDED, true),
		PLACING_EGG(StateCategory.LANDED, true);

		public final StateCategory category;
		public final boolean onSide;

		State(StateCategory category, boolean onSide)
		{
			this.category = category;
			this.onSide = onSide;
		}

		State(StateCategory category)
		{
			this(category, false);
		}
	}
}
