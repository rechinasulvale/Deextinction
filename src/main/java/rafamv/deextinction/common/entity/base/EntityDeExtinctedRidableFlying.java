package rafamv.deextinction.common.entity.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedRidableFlying extends EntityDeExtinctedRidable
{
	public float adjustYaw = 0;
	private int takeOffTimer;
	private int flapDelay;

	public EntityDeExtinctedRidableFlying(World world)
	{
		super(world);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void moveEntityWithHeading(float movementStrafing, float movementForward)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.riddenByEntity;

			if (this.isFlying() || (player.getHeldItem() != null && this.checkRidingItem(player, player.getHeldItem())))
			{
				/** There is a valid rider. */

				/**
				 * This starts the taking off. Player should jump and move
				 * forward. After some ticks, the state will change from taking
				 * off to flying
				 */
				if (!this.worldObj.isRemote)
				{
					if ((this.isTakingOff() || !this.isFlying()) && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown() && Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown())
					{
						this.increaseTakeOffTimer();

						if (!this.isTakingOff())
							this.setTakingOff(true);
					}
					else
					{
						this.decreaseTakeOffTimer(2);

						if (this.isTakingOff())
						{
							this.setTakingOff(false);
							this.resetTakeOffTimer();
						}
					}
				}

				/**
				 * If creature collided with a wall, it should stop flying and
				 * taking off.
				 */
				if (this.isCollidedHorizontally)
				{
					this.setTakingOff(false);
					this.setFlying(false);
					this.resetTakeOffTimer();
				}

				/**
				 * SET MOVEMENTS AND ROTATIONS DEPENDING ON THE CREATURE STATE
				 * (TAKING OFF/FLYING).
				 */
				if (this.isTakingOff())
				{
					this.stepHeight = 0.5F;
					this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
					// SERVER AND CLIENT CHECK LATER
					this.onUpdateTakingOffClient();
					this.onUpdateTakingOffServer();
					/** END METHOD HERE IF TAKING OFF. */
					return;
				}

				if (this.isFlying())
				{
					/**
					 * If creature collided with the ground while flying, it
					 * should stop flying and taking off, and also reduce its
					 * speed.
					 */
					if (this.onGround)
					{
						this.motionX *= 0.75F;
						this.motionZ *= 0.75F;

						this.setTakingOff(false);
						this.setFlying(false);
						this.resetTakeOffTimer();
					}

					this.stepHeight = 1.25F;
					this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
					// SERVER AND CLIENT CHECK LATER
					this.onUpdateFlyingClient();
					this.onUpdateFlyingServerGlide();
					/** END METHOD HERE IF FLYING. */
					return;
				}
			}
			else
			{
				/**
				 * There is a invalid rider (no riding item). Decrease speed
				 * slowly.
				 */
				if (this.isFlying())
				{
					if (this.onGround)
						this.setFlying(false);
				}
			}
		}
		/**
		 * If there is no rider and creature is flying, decrease motion slowly
		 * and remove flying state if true.
		 */
		if (this.isFlying())
		{
			if (this.onGround)
				this.setFlying(false);
		}
		/** Remove takingOff state if true and there is no rider. */
		if (this.isTakingOff())
		{
			this.setTakingOff(false);
			this.resetTakeOffTimer();
		}
		/** No rider = normal moveEntityWithHeading(). */
		super.moveEntityWithHeading(movementStrafing, movementForward);
	}

	private void onUpdateTakingOffClient()
	{
		this.rotationYaw = this.prevRotationYaw;
		this.rotationPitch = this.prevRotationPitch;
		this.setRotation(this.rotationYaw, this.rotationPitch);
		this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
		this.handleLimbMovement();
	}

	private void onUpdateTakingOffServer()
	{
		float newAngleYaw = 0.01745329251F * this.rotationYaw + 1.57079632679F;

		this.motionY += 0.025D * this.getTakingOffMotionY();
		this.motionX += 0.01D * Math.cos(newAngleYaw);
		this.motionZ += 0.01D * Math.sin(newAngleYaw);

		this.moveEntity(this.motionX, this.motionY, this.motionZ);

		if (this.getTakeOffProgress() >= 1.0F)
		{
			this.setFlying(true);
			this.setTakingOff(false);
			this.resetTakeOffTimer();
			this.motionY = -0.1F;
		}
	}

	private void onUpdateFlyingClient()
	{
		this.stepHeight = 0.0F;

		if (adjustYaw > 0)
			adjustYaw -= 0.1;

		if (adjustYaw < 0)
			adjustYaw += 0.1;

		GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

		if (gameSettings.keyBindRight.isKeyDown() && adjustYaw < 2.4F)
		{
			adjustYaw += 0.2F;
		}

		if (gameSettings.keyBindLeft.isKeyDown() && adjustYaw > -2.4F)
		{
			adjustYaw -= 0.2F;
		}

		this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw + adjustYaw);
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;

		this.setRotation(this.rotationYaw, this.rotationPitch);
		this.handleLimbMovement();
	}

	private void onUpdateFlyingServerGlide()
	{
		this.motionX = 2.0F * this.getMountingSpeed() * MathHelper.cos(0.01745329251F * this.rotationPitch) * MathHelper.sin(3.14159265359F + 0.01745329251F * this.rotationYaw);
		this.motionZ = 2.0F * this.getMountingSpeed() * MathHelper.cos(0.01745329251F * this.rotationPitch) * MathHelper.cos(0.01745329251F * this.rotationYaw);

		/** Flap wings and go up if delay is negative */
		if (this.getFlapDelay() < 0 && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown())
		{
			this.motionY += 1.5D;
			this.setFlapDelay(20);
		}
		else
		{
			this.decreaseFlapDelay();
			/** Decrease motion Y if delay is positive. Decrease more first. */
			this.motionY *= 0.7D + 0.15D * Math.sin(1.57079632679D * this.getFlapDelay() / 20.0D);
		}

		/**
		 * Go down if delay is negative, creature should keep flapping its wings
		 */
		if (this.getFlapDelay() < 0)
		{
			this.moveEntity(this.motionX, this.motionY - 0.2D, this.motionZ);
		}
		/**
		 * Keep the motion Y, which is decreasing to 0 gradually, if delay is
		 * positive
		 */
		else
		{
			this.moveEntity(this.motionX, this.motionY, this.motionZ);
		}
	}

	private void onUpdateFlyingServerFreeMovement()
	{
		// Don't use Minecraft.getMinecraft() serverside!

		GameSettings gameSettings = Minecraft.getMinecraft().gameSettings;

		if (this.motionY <= 2.0F * this.getMountingSpeed() && gameSettings.keyBindForward.isKeyDown())
			this.motionY += 0.02D;

		if (this.motionY >= -2.0F * this.getMountingSpeed() && gameSettings.keyBindBack.isKeyDown())
			this.motionY -= 0.02D;

		this.motionX = 2.0F * this.getMountingSpeed() * MathHelper.cos(0.01745329251F * this.rotationPitch) * MathHelper.sin(3.14159265359F + 0.01745329251F * this.rotationYaw);
		this.motionZ = 2.0F * this.getMountingSpeed() * MathHelper.cos(0.01745329251F * this.rotationPitch) * MathHelper.cos(0.01745329251F * this.rotationYaw);

		this.moveEntity(this.motionX, this.motionY + 0.05D, this.motionZ);
	}

	/**
	 * Returns the number of required ticks to start flying. Override this to
	 * set a new value different from default.
	 */
	protected int getMaximumTakeOffTime()
	{
		return EntityDeExtinctedCreatureKeys.MAX_TAKEOFF_TIME;
	}

	private void increaseTakeOffTimer()
	{
		if (this.getTakeOffTimer() <= this.getMaximumTakeOffTime())
			this.takeOffTimer++;
	}

	private void decreaseTakeOffTimer()
	{
		if (this.getTakeOffTimer() >= 0)
			this.takeOffTimer--;
	}

	private void decreaseTakeOffTimer(int value)
	{
		if (this.getTakeOffTimer() - value >= 0)
			this.takeOffTimer -= value;
	}

	private void resetTakeOffTimer()
	{
		this.takeOffTimer = 0;
	}

	public int getTakeOffTimer()
	{
		return this.takeOffTimer;
	}

	private void setTakeOffTimer(int time)
	{
		this.takeOffTimer = time;
	}

	public float getTakeOffProgress()
	{
		return this.getTakeOffTimer() / this.getMaximumTakeOffTime();
	}

	public float getTakingOffMotionY()
	{
		float result = MathHelper.sin(1.57079632679F * this.getTakeOffTimer() / this.getMaximumTakeOffTime());
		return result * result;
	}

	private void decreaseFlapDelay()
	{
		this.flapDelay--;
	}

	public int getFlapDelay()
	{
		return this.flapDelay;
	}

	private void setFlapDelay(int time)
	{
		this.flapDelay = time;
	}

	@Override
	public void collideWithEntity(Entity target)
	{
		super.collideWithEntity(target);

		if (this.isFlying())
		{
			double deltaX = target.posX - target.posX;
			double deltaZ = target.posZ - target.posZ;
			double angleYaw = (float) Math.atan2(deltaZ, deltaX);
			target.motionX += Math.cos(angleYaw);
			target.motionZ += Math.sin(angleYaw);
			target.motionY += 0.2;
		}
	}

	@Override
	public boolean isOnLadder()
	{
		return false;
	}

	protected void fall(float f)
	{
	}

	protected void updateFallState(double distanceFallen, boolean onGround)
	{
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setInteger("TakeOffTimer", this.getTakeOffTimer());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		this.setTakeOffTimer(compound.getInteger("TakeOffTimer"));
	}
}
