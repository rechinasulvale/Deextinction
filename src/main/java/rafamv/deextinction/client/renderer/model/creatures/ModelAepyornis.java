package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.creature.EntityAepyornis;

public class ModelAepyornis extends ResettableModelBase
{
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer shape3;
	public ResettableModelRenderer bodyNeck;
	public ResettableModelRenderer leftWing;
	public ResettableModelRenderer rightWing;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer head;
	public ResettableModelRenderer beakTop;
	public ResettableModelRenderer beakMiddle;
	public ResettableModelRenderer beakBottom;

	public ModelAepyornis()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.rightFoot = new ResettableModelRenderer(this, 84, 55);
		this.rightFoot.setRotationPoint(0.0F, 12.25F, 1.6F);
		this.rightFoot.addBox(-2.5F, -1.0F, -8.0F, 5, 2, 8, 0.0F);
		this.setRotateAngle(rightFoot, 0.17453292519943295F, 0.0F, 0.0F);
		this.leftWing = new ResettableModelRenderer(this, 0, 0);
		this.leftWing.setRotationPoint(7.3F, 0.4F, -12.0F);
		this.leftWing.addBox(0.0F, -1.5F, 0.0F, 1, 3, 7, 0.0F);
		this.setRotateAngle(leftWing, -0.4553564018453205F, 0.22759093446006054F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 0, 81);
		this.mainBody.setRotationPoint(0.0F, -6.9F, 0.0F);
		this.mainBody.addBox(-7.5F, -10.0F, -13.5F, 15, 20, 27, 0.0F);
		this.setRotateAngle(mainBody, -0.08726646259971647F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 102, 7);
		this.head.setRotationPoint(0.0F, -12.7F, 2.2F);
		this.head.addBox(-2.0F, -2.0F, -7.0F, 4, 4, 7, 0.0F);
		this.setRotateAngle(head, -0.08726646259971647F, 0.0F, 0.0F);
		this.beakTop = new ResettableModelRenderer(this, 59, 0);
		this.beakTop.setRotationPoint(0.0F, -0.6F, -5.5F);
		this.beakTop.addBox(-1.0F, -0.5F, -6.0F, 2, 1, 6, 0.0F);
		this.setRotateAngle(beakTop, 0.17453292519943295F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 91, 71);
		this.rightLeg3.setRotationPoint(0.0F, 11.5F, 0.5F);
		this.rightLeg3.addBox(-1.5F, 0.0F, -2.5F, 3, 12, 5, 0.0F);
		this.setRotateAngle(rightLeg3, -0.5235987755982988F, 0.0F, 0.0F);
		this.shape3 = new ResettableModelRenderer(this, 0, 50);
		this.shape3.setRotationPoint(0.0F, -0.9F, 11.3F);
		this.shape3.addBox(-6.0F, -7.0F, -6.0F, 12, 17, 12, 0.0F);
		this.setRotateAngle(shape3, 0.4886921905584123F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 61, 79);
		this.leftLeg1.mirror = true;
		this.leftLeg1.setRotationPoint(10.0F, -6.0F, 3.5F);
		this.leftLeg1.addBox(-3.0F, -7.0F, -6.0F, 6, 14, 12, 0.0F);
		this.setRotateAngle(leftLeg1, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 54, 55);
		this.leftLeg2.mirror = true;
		this.leftLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.leftLeg2.addBox(-2.5F, -1.0F, -4.0F, 5, 14, 8, 0.0F);
		this.setRotateAngle(leftLeg2, 0.4363323129985824F, 0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 84, 55);
		this.leftFoot.mirror = true;
		this.leftFoot.setRotationPoint(0.0F, 12.25F, 1.6F);
		this.leftFoot.addBox(-2.5F, -1.0F, -8.0F, 5, 2, 8, 0.0F);
		this.setRotateAngle(leftFoot, 0.17453292519943295F, 0.0F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 52, 28);
		this.neck1.setRotationPoint(0.0F, -5.1F, -0.8F);
		this.neck1.addBox(-2.5F, -16.0F, -3.5F, 5, 16, 7, 0.0F);
		this.setRotateAngle(neck1, -0.5462880558742251F, 0.0F, 0.0F);
		this.beakBottom = new ResettableModelRenderer(this, 59, 0);
		this.beakBottom.setRotationPoint(0.0F, 1.7F, -5.4F);
		this.beakBottom.addBox(-1.0F, -1.0F, -6.0F, 2, 1, 6, 0.0F);
		this.setRotateAngle(beakBottom, -0.08726646259971647F, 0.0F, 0.0F);
		this.bodyNeck = new ResettableModelRenderer(this, 0, 21);
		this.bodyNeck.setRotationPoint(0.0F, 0.0F, -12.8F);
		this.bodyNeck.addBox(-5.0F, -7.0F, -7.0F, 10, 14, 14, 0.0F);
		this.setRotateAngle(bodyNeck, 0.7853981633974483F, 0.0F, 0.0F);
		this.rightWing = new ResettableModelRenderer(this, 0, 0);
		this.rightWing.setRotationPoint(-8.5F, 0.4F, -12.0F);
		this.rightWing.addBox(0.0F, -1.5F, 0.0F, 1, 3, 7, 0.0F);
		this.setRotateAngle(rightWing, -0.4642575810304917F, -0.22863813201125716F, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 79, 29);
		this.neck2.setRotationPoint(0.0F, -14.5F, -0.6F);
		this.neck2.addBox(-1.5F, -14.0F, -2.5F, 3, 14, 5, 0.0F);
		this.setRotateAngle(neck2, 0.12217304763960307F, 0.0F, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 61, 79);
		this.rightLeg1.setRotationPoint(-10.0F, -6.0F, 3.5F);
		this.rightLeg1.addBox(-3.0F, -7.0F, -6.0F, 6, 14, 12, 0.0F);
		this.setRotateAngle(rightLeg1, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 91, 71);
		this.leftLeg3.mirror = true;
		this.leftLeg3.setRotationPoint(0.0F, 11.5F, 0.5F);
		this.leftLeg3.addBox(-1.5F, 0.0F, -2.5F, 3, 12, 5, 0.0F);
		this.setRotateAngle(leftLeg3, -0.5235987755982988F, 0.0F, 0.0F);
		this.beakMiddle = new ResettableModelRenderer(this, 59, 0);
		this.beakMiddle.setRotationPoint(0.0F, -0.6F, -5.6F);
		this.beakMiddle.addBox(-1.0F, 0.5F, -6.0F, 2, 1, 6, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 54, 55);
		this.rightLeg2.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.rightLeg2.addBox(-2.5F, -1.0F, -4.0F, 5, 14, 8, 0.0F);
		this.setRotateAngle(rightLeg2, 0.4363323129985824F, 0.0F, 0.0F);
		this.rightLeg3.addChild(this.rightFoot);
		this.mainBody.addChild(this.leftWing);
		this.neck2.addChild(this.head);
		this.head.addChild(this.beakTop);
		this.rightLeg2.addChild(this.rightLeg3);
		this.mainBody.addChild(this.shape3);
		this.leftLeg1.addChild(this.leftLeg2);
		this.leftLeg3.addChild(this.leftFoot);
		this.bodyNeck.addChild(this.neck1);
		this.head.addChild(this.beakBottom);
		this.mainBody.addChild(this.bodyNeck);
		this.mainBody.addChild(this.rightWing);
		this.neck1.addChild(this.neck2);
		this.leftLeg2.addChild(this.leftLeg3);
		this.head.addChild(this.beakMiddle);
		this.rightLeg1.addChild(this.rightLeg2);

		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.mainBody.saveParameters();
		this.shape3.saveParameters();
		this.bodyNeck.saveParameters();
		this.leftWing.saveParameters();
		this.rightWing.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.head.saveParameters();
		this.beakTop.saveParameters();
		this.beakMiddle.saveParameters();
		this.beakBottom.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, (EntityAepyornis) entity);

		this.mainBody.render(scale);
		this.leftLeg1.render(scale);
		this.rightLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();

		EntityAepyornis aepyornis = (EntityAepyornis) entity;
		float sittingProgress = aepyornis.getSittingProgress(partialRenderTicks);

		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 18.0F * sittingProgress;
			this.mainBody.rotateAngleX -= 0.4F * sittingProgress;
			this.neck1.rotateAngleX += 0.4F * sittingProgress;

			this.rightLeg1.rotationPointY += 16.0F * sittingProgress;
			this.rightLeg2.rotationPointY -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointZ -= 6.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.0F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 6.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 2.4F * sittingProgress;
			this.rightFoot.rotationPointZ -= 2.0F * sittingProgress;
			this.rightFoot.rotationPointY -= 2.0F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.5F * sittingProgress;

			this.leftLeg1.rotationPointY += 16.0F * sittingProgress;
			this.leftLeg2.rotationPointY -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointZ -= 6.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.0F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 6.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 2.4F * sittingProgress;
			this.leftFoot.rotationPointZ -= 2.0F * sittingProgress;
			this.leftFoot.rotationPointY -= 2.0F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.5F * sittingProgress;
		}
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityAepyornis aepyornis)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, aepyornis);

		float headX = this.getHeadAngle(pitch) / 2.0F;
		float headY = this.getHeadAngle(yaw) / 2.0F;

		if (aepyornis.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.02F, 0.1F);
			this.mainBody.rotateAngleX -= 0.5F * naturalMovement;
			this.neck1.rotateAngleX += 0.5F * naturalMovement;
			this.neck2.rotateAngleX -= 0.5F * naturalMovement;
			this.leftWing.rotateAngleX -= naturalMovement;
			this.rightWing.rotateAngleX -= naturalMovement;

			this.head.rotateAngleX += 0.25F * headX;
			this.head.rotateAngleY += 0.25F * headY;
			this.head.rotateAngleZ -= 0.25F * headY;
			this.neck2.rotateAngleY += 0.4F * headY;
			this.neck2.rotateAngleZ -= 0.4F * headY;
			this.neck1.rotateAngleY += 0.8F * headY;
			this.neck1.rotateAngleZ -= 0.8F * headY;
		}
		else
		{
			float legX1 = this.getRotateAngle(time, walkSpeed, 0.4F, 1.0F);
			this.rightLeg1.rotateAngleX -= legX1;
			this.leftLeg1.rotateAngleX += legX1;

			float runningInclination = MathHelper.sin(walkSpeed);
			if (runningInclination > 0.001F)
			{
				if (runningInclination > 0.1F)
					runningInclination = 0.1F;

				float runningPose = this.getAlwaysRotateAngle(0.4F * time, 1.0F, 0.5F);
				this.neck1.rotateAngleX += 4.0F * runningInclination;
				this.neck2.rotateAngleX += 4.0F * runningInclination;
				this.head.rotateAngleX -= 8.0F * runningInclination;

				float bodyBob = this.getRotateAngle(time, walkSpeed, 0.8F, 3.0F);
				this.mainBody.rotationPointY -= bodyBob;
			}
			else
			{
				float naturalMovement = this.getAlwaysRotateAngle(time, 0.02F, 0.1F);
				this.mainBody.rotateAngleX -= naturalMovement;
				this.neck1.rotateAngleX += naturalMovement;
				this.neck2.rotateAngleX -= naturalMovement;
				this.leftWing.rotateAngleX -= naturalMovement;
				this.rightWing.rotateAngleX -= naturalMovement;

				this.head.rotateAngleX += 0.25F * headX;
				this.head.rotateAngleY += 0.25F * headY;
				this.head.rotateAngleZ -= 0.25F * headY;
				this.neck2.rotateAngleY += 0.4F * headY;
				this.neck2.rotateAngleZ -= 0.4F * headY;
				this.neck1.rotateAngleY += 0.8F * headY;
				this.neck1.rotateAngleZ -= 0.8F * headY;
			}
		}
	}

	private void resetPose()
	{
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.mainBody.resetParameters();
		this.shape3.resetParameters();
		this.bodyNeck.resetParameters();
		this.leftWing.resetParameters();
		this.rightWing.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.head.resetParameters();
		this.beakTop.resetParameters();
		this.beakMiddle.resetParameters();
		this.beakBottom.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
