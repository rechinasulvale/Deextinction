package rafamv.deextinction.client.renderer.animations;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class YawChainAnimator
{
	private final EntityLivingBase entity;
	private float yawVariation;
	private float prevYaw;
	private float yaw;

	public YawChainAnimator(EntityLivingBase entity)
	{
		this.entity = entity;
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

	public void update(int numberOfParentedBoxes, float maxAngle, float angleDecrement)
	{
		if (this.entity.renderYawOffset != this.entity.prevRenderYawOffset)
		{
			this.yawVariation += (this.entity.prevRenderYawOffset - this.entity.renderYawOffset);
			if (this.yawVariation > maxAngle)
				this.yawVariation = maxAngle;
			else if (this.yawVariation < -maxAngle)
				this.yawVariation = -maxAngle;
		}

		this.prevYaw = this.yaw;
		if (MathHelper.abs(this.yawVariation) < angleDecrement)
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
