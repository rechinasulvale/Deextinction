package rafamv.deextinction.common.entity.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class EntityDeExtinctedRidable extends EntityDeExtinctedAgeable
{

	public EntityDeExtinctedRidable(World world)
	{
		super(world);
	}

	@Override
	public boolean isOnLadder()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack playerItemStack = player.inventory.getCurrentItem();

		if (!this.worldObj.isRemote && this.checkRidingItem(player, playerItemStack))
		{
			if (this.isTamed() && this.isAdult() && !this.isSitting() && !this.isSleeping() && this.riddenByEntity == null)
			{
				this.setSitting(false, null);
				this.setRidingPlayer(player);
			}
			else
			{
				if (!this.isAdult())
					this.displayTextInChat(player, this.getCreatureDisplayName() + " " + StatCollector.translateToLocal("entity.info.isnt_adult"));
				else if (this.isSitting())
					this.displayTextInChat(player, this.getCreatureDisplayName() + " " + StatCollector.translateToLocal("entity.info.is_sitting"));
				else if (this.riddenByEntity != null)
					this.displayTextInChat(player, this.getCreatureDisplayName() + " " + StatCollector.translateToLocal("entity.info.is_being_ridden"));
			}
		}
		return super.interact(player);
	}

	public float getMountingSpeed()
	{
		return (float) this.getCreatureSpeed();
	}

	public void setRidingPlayer(EntityPlayer player)
	{
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;
		player.mountEntity(this);
	}

	/**
	 * Sets the mob rotation depending on where the player is looking (Horse
	 * Style). ID: 0.
	 */
	protected void handleMouseControlledRiding()
	{
		this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
		this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
		this.setRotation(this.rotationYaw, this.rotationPitch);
		this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
		this.setRotation(this.rotationYaw, this.rotationPitch);
	}

	/**
	 * Sets the mob rotation depending on the item position (Pig Style). ID: 1.
	 */
	protected void handleFastItemControlledRiding()
	{
		this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

		float adjust = MathHelper.wrapAngleTo180_float(((EntityLivingBase) this.riddenByEntity).rotationYaw - this.rotationYaw) * 0.5F;
		this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw + MathHelper.clamp_float(adjust, -6.0F, 6.0F));
		this.setRotation(this.rotationYaw, this.rotationPitch);
	}

	/**
	 * Sets the mob rotation depending on the item position (Pig Style). ID: 2.
	 */
	protected void handleSlowItemControlledRiding()
	{
		this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

		float adjust = MathHelper.wrapAngleTo180_float(((EntityLivingBase) this.riddenByEntity).rotationYaw - this.rotationYaw) * 0.5F;
		this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw + MathHelper.clamp_float(adjust, -1.7F, 1.7F));
		this.setRotation(this.rotationYaw, this.rotationPitch);
	}

	@Override
	public void moveEntityWithHeading(float movementStrafing, float movementForward)
	{
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.riddenByEntity;
			if (this.checkRidingItem(player, player.getHeldItem()))
			{
				switch (this.getRidingStyle())
				{
					case 0:
						this.handleMouseControlledRiding();
						break;
					case 1:
						this.handleFastItemControlledRiding();
						break;
					case 2:
						this.handleSlowItemControlledRiding();
						break;
					default:
						this.handleSlowItemControlledRiding();
				}

				this.stepHeight = 1.0F;
				movementStrafing = 0.25F * player.moveStrafing * this.getMountingSpeed();

				if (moveForward < 0)
					movementForward = player.moveForward * 0.3F * this.getMountingSpeed();
				else
					movementForward = player.moveForward * this.getMountingSpeed();

				this.decreaseHeldItemDurability(1);

				if (!this.worldObj.isRemote)
					super.moveEntityWithHeading(movementStrafing, movementForward);

				this.handleLimbMovement();
			}
		}
		else
		{
			this.stepHeight = 0.5F;
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(movementStrafing, movementForward);
		}
	}

	public void rideJump()
	{
		if (this.onGround && !this.isJumping && !this.isAirBorne)
		{
			this.decreaseHeldItemDurability(20);
			this.jump();
		}
	}

	/**
	 * Makes corrections to the limbs.
	 */
	protected void handleLimbMovement()
	{
		this.prevLimbSwingAmount = this.limbSwingAmount;
		double pointX = this.posX - this.prevPosX;
		double pointZ = this.posZ - this.prevPosZ;

		float distance = MathHelper.sqrt_double(pointX * pointX + pointZ * pointZ) * 4.0F;

		if (distance > 1.0F)
			distance = 1.0F;

		this.limbSwingAmount += (distance - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
	}

	/**
	 * Decreases the held item durability and destroys the item if stack size is
	 * 0 or less.
	 */
	protected void decreaseHeldItemDurability(int damage)
	{
		EntityPlayer player = (EntityPlayer) this.riddenByEntity;

		ItemStack heldItem = player.getHeldItem();

		if (player != null && heldItem != null && heldItem.getItemDamage() + damage > heldItem.getMaxDamage())
		{
			heldItem.stackSize--;
			if (heldItem.stackSize <= 0)
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		}
		else
			heldItem.setItemDamage(heldItem.getItemDamage() + damage);
	}

	/**
	 * Returns the riding style of this creature. 0 = Mouse controlled; 1 = Fast
	 * Item Controlled; 2 = Slow Item Controlled.
	 */
	protected abstract int getRidingStyle();

	/** Returns true if the player is holding the right riding item. */
	protected abstract boolean checkRidingItem(EntityPlayer player, ItemStack playerHeldItem);
}
