package rafamv.deextinction.client.renderer.animations;

import net.minecraft.entity.EntityLivingBase;

public class HeadYawChainBuffer
{
	private float yawVariation;
	private float prevYaw;
	private float yaw;

	public HeadYawChainBuffer()
	{
		this.yaw = this.prevYaw = 0.0F;
		this.yawVariation = 0.0F;
	}

	public float getYaw()
	{
		return this.yaw;
	}

	public float getPrevYaw()
	{
		return this.prevYaw;
	}

	public void setYawVariation(float variation)
	{
		this.yawVariation = variation;
	}

	public float getAnimation(float partialRenderTicks)
	{
		return this.prevYaw + (this.yaw - this.prevYaw) * partialRenderTicks;
	}

	public void update(EntityLivingBase entity, int numberOfParentedBoxes, float maxAngle, float angleDecrement)
	{
		if (entity.rotationYawHead != entity.prevRotationYawHead)
		{
			this.yawVariation += (entity.prevRotationYawHead - entity.rotationYawHead);
			if (this.yawVariation > maxAngle)
				this.yawVariation = maxAngle;
		}

		this.prevYaw = this.yaw;
		if (this.yawVariation * this.yawVariation < angleDecrement)
		{
			this.yawVariation = 0;
			this.yaw = 0.0F;
		}
		else
		{
			this.yaw = 0.01745329251F * this.yawVariation / numberOfParentedBoxes;

			if (this.yawVariation > 0)
				this.yawVariation -= angleDecrement;
			else
				this.yawVariation += angleDecrement;
		}
	}
}
