package rafamv.deextinction.common.entity.base;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityPushable extends Entity
{

	public EntityPushable(World worldIn, float xzSize, float ySize)
	{
		super(worldIn);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.setSize(xzSize, ySize);
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(5, Float.valueOf(0.0F));
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.getDamageTaken() > 0.0F)
			this.setDamageTaken(this.getDamageTaken() - 1.0F);

		this.handlePushMotion();
	}

	/** From minecart code */
	protected void handlePushMotion()
	{
		if (this.worldObj.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
		else
		{
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			this.motionY -= 0.03999999910593033D;
			int j = MathHelper.floor_double(this.posX);
			int k = MathHelper.floor_double(this.posZ);

			this.motionX = MathHelper.clamp_double(this.motionX, -0.4D, 0.4D);
			this.motionZ = MathHelper.clamp_double(this.motionZ, -0.4D, 0.4D);

			if (this.onGround)
			{
				this.motionX *= 0.5D;
				this.motionY *= 0.5D;
				this.motionZ *= 0.5D;
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			if (!this.onGround)
			{
				this.motionX *= 0.94999998807907104D;
				this.motionZ *= 0.94999998807907104D;
				if (this.inWater)
				{
					this.motionY -= 0.0125F;
					if (this.motionY < -0.15F)
						this.motionY = -0.15F;
				}
				else
				{
					this.motionY -= 0.025F;
					if (this.motionY < -0.6F)
						this.motionY = -0.6F;
				}
			}

			this.doBlockCollisions();
			this.rotationPitch = 0.0F;
			double xDifference = this.prevPosX - this.posX;
			double zDifference = this.prevPosZ - this.posZ;

			if (xDifference * xDifference + zDifference * zDifference > 0.001D)
				this.rotationYaw = (float) (Math.atan2(zDifference, xDifference) * 180.0D / Math.PI);

			double d3 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);
			if (d3 < -170.0D || d3 >= 170.0D)
				this.rotationYaw += 180.0F;

			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.handleWaterMovement();
		}

		if (Math.abs(this.motionX) < 0.005D)
			this.motionX = 0.0D;

		if (Math.abs(this.motionY) < 0.005D)
			this.motionY = 0.0D;

		if (Math.abs(this.motionZ) < 0.005D)
			this.motionZ = 0.0D;

		if (!this.worldObj.isRemote)
			this.collideWithNearbyEntities();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
			return false;
		else if (!this.isDead)
		{
			this.playBreakingSoundEffect();
			if (!this.worldObj.isRemote)
			{
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.setBeenAttacked();
				boolean creativeFlag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer) source.getEntity()).capabilities.isCreativeMode;
				if (creativeFlag || this.getDamageTaken() > 25.0F)
				{
					if (!creativeFlag)
						this.dropItemsWhenBroken();
					this.setDead();
				}
			}
		}
		return true;
	}

	public void setDamageTaken(float amount)
	{
		this.dataWatcher.updateObject(5, Float.valueOf(amount));
	}

	public float getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectFloat(5);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	public boolean canBePushed()
	{
		return !this.isDead;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected void collideWithNearbyEntities()
	{
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

		if (list != null && !list.isEmpty())
		{
			for (int i = 0; i < list.size(); ++i)
			{
				Entity entity = (Entity) list.get(i);
				if (entity.canBePushed())
					this.collideWithEntity(entity);
			}
		}
	}

	protected void collideWithEntity(Entity entity)
	{
		entity.applyEntityCollision(this);
	}

	protected abstract void dropItemsWhenBroken();

	protected abstract void playBreakingSoundEffect();
}
