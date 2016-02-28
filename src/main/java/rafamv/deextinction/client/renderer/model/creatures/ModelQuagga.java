package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.creature.EntityQuagga;

public class ModelQuagga extends ResettableModelBase
{
	public ResettableModelRenderer LegBackLeftBottom;
	public ResettableModelRenderer HoofBackLeft;
	public ResettableModelRenderer LegFrontRightTop;
	public ResettableModelRenderer LegFrontRightBottom;
	public ResettableModelRenderer Body;
	public ResettableModelRenderer LegBackLeftTop;
	public ResettableModelRenderer LegBackRightTop;
	public ResettableModelRenderer LegBackRightBottom;
	public ResettableModelRenderer HoofBackRight;
	public ResettableModelRenderer HoofFrontRight;
	public ResettableModelRenderer Neck;
	public ResettableModelRenderer TailTop;
	public ResettableModelRenderer LegFrontLeftTop;
	public ResettableModelRenderer Head;
	public ResettableModelRenderer Mane;
	public ResettableModelRenderer MouthTop;
	public ResettableModelRenderer Bottom;
	public ResettableModelRenderer EarLeft;
	public ResettableModelRenderer EarRight;
	public ResettableModelRenderer TailMiddle;
	public ResettableModelRenderer TailBottom;
	public ResettableModelRenderer LegFrontLeftBottom;
	public ResettableModelRenderer HoofFrontLeft;

	public ModelQuagga()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.LegBackLeftBottom = new ResettableModelRenderer(this, 78, 43);
		this.LegBackLeftBottom.setRotationPoint(4.0F, 16.0F, 9.0F);
		this.LegBackLeftBottom.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		this.LegFrontRightTop = new ResettableModelRenderer(this, 60, 29);
		this.LegFrontRightTop.setRotationPoint(-4.0F, 10.0F, -8.0F);
		this.LegFrontRightTop.addBox(-1.1F, -1.0F, -2.1F, 3, 7, 4, 0.0F);
		this.Head = new ResettableModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, -1.0F, -1.0F);
		this.Head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7, 0.0F);
		this.Mane = new ResettableModelRenderer(this, 58, 0);
		this.Mane.setRotationPoint(0.0F, 0.9F, 2.5F);
		this.Mane.addBox(-1.0F, -12.5F, 0.0F, 2, 16, 4, 0.0F);
		this.setRotateAngle(Mane, 0.016755160819145562F, 0.0F, 0.0F);
		this.HoofBackRight = new ResettableModelRenderer(this, 96, 52);
		this.HoofBackRight.setRotationPoint(-4.0F, 16.0F, 9.0F);
		this.HoofBackRight.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
		this.TailTop = new ResettableModelRenderer(this, 44, 0);
		this.TailTop.setRotationPoint(0.0F, -6.7F, 2.0F);
		this.TailTop.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(TailTop, -1.0295697257514551F, 0.0F, 0.0F);
		this.EarLeft = new ResettableModelRenderer(this, 0, 0);
		this.EarLeft.setRotationPoint(1.4F, -8.8F, 4.3F);
		this.EarLeft.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(EarLeft, 0.0F, 0.0F, 0.3665191429188092F);
		this.LegBackRightTop = new ResettableModelRenderer(this, 96, 29);
		this.LegBackRightTop.setRotationPoint(-4.0F, 10.0F, 9.0F);
		this.LegBackRightTop.addBox(-1.5F, -2.0F, -2.5F, 4, 8, 5, 0.0F);
		this.Body = new ResettableModelRenderer(this, 0, 38);
		this.Body.setRotationPoint(0.0F, 12.0F, 9.0F);
		this.Body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 22, 0.0F);
		this.HoofFrontRight = new ResettableModelRenderer(this, 60, 51);
		this.HoofFrontRight.setRotationPoint(-4.0F, 16.0F, -8.0F);
		this.HoofFrontRight.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4, 0.0F);
		this.LegFrontLeftTop = new ResettableModelRenderer(this, 44, 29);
		this.LegFrontLeftTop.setRotationPoint(4.0F, -2.0F, -17.0F);
		this.LegFrontLeftTop.addBox(-1.9F, -1.0F, -2.1F, 3, 7, 4, 0.0F);
		this.MouthTop = new ResettableModelRenderer(this, 24, 18);
		this.MouthTop.setRotationPoint(0.0F, -7.68F, -0.98F);
		this.MouthTop.addBox(-2.0F, -2.0F, -6.0F, 4, 3, 6, 0.0F);
		this.HoofFrontLeft = new ResettableModelRenderer(this, 43, 51);
		this.HoofFrontLeft.setRotationPoint(0.4F, 0.0F, 0.0F);
		this.HoofFrontLeft.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4, 0.0F);
		this.Neck = new ResettableModelRenderer(this, 0, 14);
		this.Neck.setRotationPoint(0.0F, -5.0F, -18.0F);
		this.Neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 7, 0.0F);
		this.setRotateAngle(Neck, 0.5061454830783556F, 0.0F, 0.0F);
		this.EarRight = new ResettableModelRenderer(this, 0, 0);
		this.EarRight.setRotationPoint(-1.5F, -9.1F, 4.2F);
		this.EarRight.addBox(-1.0F, -4.0F, 0.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(EarRight, 0.0F, 0.0F, -0.3665191429188092F);
		this.LegFrontRightBottom = new ResettableModelRenderer(this, 60, 41);
		this.LegFrontRightBottom.setRotationPoint(-4.0F, 16.0F, -8.0F);
		this.LegFrontRightBottom.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3, 0.0F);
		this.TailMiddle = new ResettableModelRenderer(this, 38, 7);
		this.TailMiddle.setRotationPoint(0.0F, 0.1F, 2.5F);
		this.TailMiddle.addBox(-1.0F, -1.2F, 0.0F, 2, 2, 7, 0.0F);
		this.setRotateAngle(TailMiddle, -0.31869712141416456F, 0.0F, 0.0F);
		this.LegBackLeftTop = new ResettableModelRenderer(this, 78, 29);
		this.LegBackLeftTop.setRotationPoint(4.0F, 10.0F, 9.0F);
		this.LegBackLeftTop.addBox(-2.5F, -2.0F, -2.5F, 4, 8, 5, 0.0F);
		this.HoofBackLeft = new ResettableModelRenderer(this, 78, 52);
		this.HoofBackLeft.setRotationPoint(4.0F, 16.0F, 9.0F);
		this.HoofBackLeft.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
		this.LegBackRightBottom = new ResettableModelRenderer(this, 96, 43);
		this.LegBackRightBottom.setRotationPoint(-4.0F, 16.0F, 9.0F);
		this.LegBackRightBottom.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		this.TailBottom = new ResettableModelRenderer(this, 25, 3);
		this.TailBottom.setRotationPoint(0.0F, -0.2F, 6.1F);
		this.TailBottom.addBox(-1.5F, -1.5F, -0.1F, 3, 3, 6, 0.0F);
		this.setRotateAngle(TailBottom, -0.18203784098300857F, 0.0F, 0.0F);
		this.LegFrontLeftBottom = new ResettableModelRenderer(this, 44, 41);
		this.LegFrontLeftBottom.setRotationPoint(-0.4F, 6.0F, 0.0F);
		this.LegFrontLeftBottom.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
		this.Bottom = new ResettableModelRenderer(this, 24, 27);
		this.Bottom.setRotationPoint(0.0F, -6.2F, -0.7F);
		this.Bottom.addBox(-2.0F, -1.0F, -5.5F, 4, 2, 5, 0.0F);
		this.Neck.addChild(this.Head);
		this.Neck.addChild(this.Mane);
		this.Body.addChild(this.TailTop);
		this.Head.addChild(this.EarLeft);
		this.Body.addChild(this.LegFrontLeftTop);
		this.Head.addChild(this.MouthTop);
		this.LegFrontLeftBottom.addChild(this.HoofFrontLeft);
		this.Body.addChild(this.Neck);
		this.Head.addChild(this.EarRight);
		this.TailTop.addChild(this.TailMiddle);
		this.TailMiddle.addChild(this.TailBottom);
		this.LegFrontLeftTop.addChild(this.LegFrontLeftBottom);
		this.Head.addChild(this.Bottom);

		this.LegBackLeftBottom.saveParameters();
		this.HoofBackLeft.saveParameters();
		this.LegFrontRightTop.saveParameters();
		this.LegFrontRightBottom.saveParameters();
		this.Body.saveParameters();
		this.LegBackLeftTop.saveParameters();
		this.LegBackRightTop.saveParameters();
		this.LegBackRightBottom.saveParameters();
		this.HoofBackRight.saveParameters();
		this.HoofFrontRight.saveParameters();
		this.Neck.saveParameters();
		this.TailTop.saveParameters();
		this.LegFrontLeftTop.saveParameters();
		this.Head.saveParameters();
		this.Mane.saveParameters();
		this.MouthTop.saveParameters();
		this.Bottom.saveParameters();
		this.EarLeft.saveParameters();
		this.EarRight.saveParameters();
		this.TailMiddle.saveParameters();
		this.TailBottom.saveParameters();
		this.LegFrontLeftBottom.saveParameters();
		this.HoofFrontLeft.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityQuagga creature = (EntityQuagga) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);

		this.LegBackLeftBottom.render(scale);
		this.LegFrontRightTop.render(scale);
		this.HoofBackRight.render(scale);
		this.LegBackRightTop.render(scale);
		this.Body.render(scale);
		this.HoofFrontRight.render(scale);
		this.LegFrontRightBottom.render(scale);
		this.LegBackLeftTop.render(scale);
		this.HoofBackLeft.render(scale);
		this.LegBackRightBottom.render(scale);
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityQuagga creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);
		this.resetPose();

		if (creature.isSitting())
		{

		}
		else
		{
			float headX = this.getHeadAngle(pitch/2.0F);
			float headY = this.getHeadAngle(yaw/2.0F);
			this.Neck.rotateAngleX += headX;
			this.Neck.rotateAngleY += headY;

			if (walkSpeed > 0.001F)
			{

			}
		}
	}

	private void resetPose()
	{
		this.LegBackLeftBottom.resetParameters();
		this.HoofBackLeft.resetParameters();
		this.LegFrontRightTop.resetParameters();
		this.LegFrontRightBottom.resetParameters();
		this.Body.resetParameters();
		this.LegBackLeftTop.resetParameters();
		this.LegBackRightTop.resetParameters();
		this.LegBackRightBottom.resetParameters();
		this.HoofBackRight.resetParameters();
		this.HoofFrontRight.resetParameters();
		this.Neck.resetParameters();
		this.TailTop.resetParameters();
		this.LegFrontLeftTop.resetParameters();
		this.Head.resetParameters();
		this.Mane.resetParameters();
		this.MouthTop.resetParameters();
		this.Bottom.resetParameters();
		this.EarLeft.resetParameters();
		this.EarRight.resetParameters();
		this.TailMiddle.resetParameters();
		this.TailBottom.resetParameters();
		this.LegFrontLeftBottom.resetParameters();
		this.HoofFrontLeft.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
