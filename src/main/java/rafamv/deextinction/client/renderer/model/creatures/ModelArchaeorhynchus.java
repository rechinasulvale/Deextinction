package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.creature.EntityArchaeorhynchus;

public class ModelArchaeorhynchus extends ResettableModelBase
{
	public ResettableModelRenderer bodyMain;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer bodyNeck;
	public ResettableModelRenderer bodyBottom;
	public ResettableModelRenderer tail;
	public ResettableModelRenderer leftArm;
	public ResettableModelRenderer rightArm;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer head;
	public ResettableModelRenderer beak1;
	public ResettableModelRenderer beak2;
	public ResettableModelRenderer tailFeather1;
	public ResettableModelRenderer tailFeather2;
	public ResettableModelRenderer tailFeather3;
	public ResettableModelRenderer tailFeather4;
	public ResettableModelRenderer tailFeather5;
	public ResettableModelRenderer tailFeather6;
	public ResettableModelRenderer leftWing1;
	public ResettableModelRenderer leftWing2;
	public ResettableModelRenderer leftWing3;
	public ResettableModelRenderer rightWing1;
	public ResettableModelRenderer rightWing2;
	public ResettableModelRenderer rightWing3;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftFeet;
	public ResettableModelRenderer leftFeet2;
	public ResettableModelRenderer leftFeet3;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightFeet;
	public ResettableModelRenderer rightFeet2;
	public ResettableModelRenderer rightFeet3;

	public ModelArchaeorhynchus()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.beak1 = new ResettableModelRenderer(this, 28, 33);
		this.beak1.setRotationPoint(0.0F, -0.2F, -2.0F);
		this.beak1.addBox(-1.0F, -0.9F, -3.1F, 2, 1, 3, 0.0F);
		this.setRotateAngle(beak1, 0.20943951023931953F, 0.0F, 0.0F);
		this.leftFeet3 = new ResettableModelRenderer(this, 15, 58);
		this.leftFeet3.setRotationPoint(0.5F, 0.05F, -0.75F);
		this.leftFeet3.addBox(-0.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(leftFeet3, 0.0F, 1.0471975511965976F, 0.0F);
		this.tailFeather3 = new ResettableModelRenderer(this, 29, 6);
		this.tailFeather3.setRotationPoint(-0.5F, 0.4F, 0.0F);
		this.tailFeather3.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 8, 0.0F);
		this.setRotateAngle(tailFeather3, 0.6108652381980153F, -0.13962634015954636F, 0.0F);
		this.leftWing2 = new ResettableModelRenderer(this, 0, 45);
		this.leftWing2.setRotationPoint(0.5F, 6.0F, -1.2F);
		this.leftWing2.addBox(0.0F, -5.0F, -1.0F, 0, 7, 3, 0.0F);
		this.setRotateAngle(leftWing2, -3.193952531149623F, -0.017453292519943295F, 0.0F);
		this.leftFeet2 = new ResettableModelRenderer(this, 15, 61);
		this.leftFeet2.setRotationPoint(-0.5F, 0.05F, -0.75F);
		this.leftFeet2.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(leftFeet2, 0.0F, -1.0471975511965976F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 15, 50);
		this.leftLeg2.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.leftLeg2.addBox(-0.5F, -0.5F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftLeg2, -0.5235987755982988F, 0.0F, 0.0F);
		this.rightFeet3 = new ResettableModelRenderer(this, 29, 58);
		this.rightFeet3.setRotationPoint(0.5F, 0.05F, -0.75F);
		this.rightFeet3.addBox(-0.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(rightFeet3, 0.0F, 1.0471975511965976F, 0.0F);
		this.bodyMain = new ResettableModelRenderer(this, 0, 6);
		this.bodyMain.setRotationPoint(0.0F, 15.5F, -2.5F);
		this.bodyMain.addBox(-2.5F, -1.0F, -1.5F, 5, 4, 8, 0.0F);
		this.setRotateAngle(bodyMain, -0.13892820845874865F, 0.0F, 0.0F);
		this.leftWing3 = new ResettableModelRenderer(this, 0, 54);
		this.leftWing3.setRotationPoint(-0.2F, -2.5F, 0.3F);
		this.leftWing3.addBox(0.0F, 0.0F, -1.0F, 0, 6, 2, 0.0F);
		this.setRotateAngle(leftWing3, 3.193952531149623F, 0.0F, 0.022689280275926284F);
		this.neck2 = new ResettableModelRenderer(this, 30, 21);
		this.neck2.setRotationPoint(0.0F, -2.38F, 0.47F);
		this.neck2.addBox(-1.0F, -2.3F, -1.4F, 2, 2, 2, 0.0F);
		this.setRotateAngle(neck2, 0.40142572795869574F, 0.0F, 0.0F);
		this.tailFeather1 = new ResettableModelRenderer(this, 23, 6);
		this.tailFeather1.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.tailFeather1.addBox(-1.5F, -0.3F, 0.0F, 3, 0, 7, 0.0F);
		this.setRotateAngle(tailFeather1, 0.5235987755982988F, 0.0F, 0.0F);
		this.rightWing1 = new ResettableModelRenderer(this, 7, 37);
		this.rightWing1.setRotationPoint(-0.6F, 0.0F, -1.0F);
		this.rightWing1.mirror = true;
		this.rightWing1.addBox(0.1F, 0.0F, -2.0F, 0, 7, 3, 0.0F);
		this.setRotateAngle(rightWing1, -0.03490658503988659F, 0.0F, 0.03490658503988659F);
		this.rightLeg1 = new ResettableModelRenderer(this, 29, 44);
		this.rightLeg1.setRotationPoint(-2.0F, 18.43F, 0.0F);
		this.rightLeg1.addBox(-1.0F, -0.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(rightLeg1, 0.3490658503988659F, 0.0F, 0.0F);
		this.tailFeather4 = new ResettableModelRenderer(this, 36, 6);
		this.tailFeather4.setRotationPoint(0.5F, 0.5F, 0.0F);
		this.tailFeather4.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 6, 0.0F);
		this.setRotateAngle(tailFeather4, 0.6108652381980153F, 0.17453292519943295F, 0.3490658503988659F);
		this.tailFeather2 = new ResettableModelRenderer(this, 29, 6);
		this.tailFeather2.setRotationPoint(0.5F, 0.4F, 0.0F);
		this.tailFeather2.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 8, 0.0F);
		this.setRotateAngle(tailFeather2, 0.6108652381980153F, 0.13962634015954636F, 0.0F);
		this.rightWing3 = new ResettableModelRenderer(this, 7, 54);
		this.rightWing3.setRotationPoint(0.2F, -2.5F, 0.3F);
		this.rightWing3.mirror = true;
		this.rightWing3.addBox(0.0F, 0.0F, -1.0F, 0, 6, 2, 0.0F);
		this.setRotateAngle(rightWing3, 3.193952531149623F, 0.0F, -0.022689280275926284F);
		this.leftWing1 = new ResettableModelRenderer(this, 0, 37);
		this.leftWing1.setRotationPoint(0.4F, 0.0F, -1.0F);
		this.leftWing1.addBox(0.1F, 0.0F, -2.0F, 0, 7, 3, 0.0F);
		this.setRotateAngle(leftWing1, -0.03490658503988659F, 0.0F, -0.03490658503988659F);
		this.rightWing2 = new ResettableModelRenderer(this, 7, 45);
		this.rightWing2.setRotationPoint(-0.5F, 6.0F, -1.2F);
		this.rightWing2.mirror = true;
		this.rightWing2.addBox(0.0F, -5.0F, -1.0F, 0, 7, 3, 0.0F);
		this.setRotateAngle(rightWing2, -3.193952531149623F, 0.017453292519943295F, 0.0F);
		this.leftArm = new ResettableModelRenderer(this, 1, 32);
		this.leftArm.setRotationPoint(2.0F, 0.5F, -1.5F);
		this.leftArm.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.setRotateAngle(leftArm, 1.6580627893946132F, 0.13962634015954636F, 0.0F);
		this.rightArm = new ResettableModelRenderer(this, 7, 32);
		this.rightArm.setRotationPoint(-2.0F, 0.5F, -1.5F);
		this.rightArm.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1, 0.0F);
		this.setRotateAngle(rightArm, 1.6580627893946132F, -0.13962634015954636F, 0.0F);
		this.bodyNeck = new ResettableModelRenderer(this, 28, 15);
		this.bodyNeck.setRotationPoint(0.0F, 0.5F, -1.0F);
		this.bodyNeck.addBox(-1.5F, -1.15F, -1.6F, 3, 2, 3, 0.0F);
		this.setRotateAngle(bodyNeck, 0.5235987755982988F, 0.0F, 0.0F);
		this.bodyBottom = new ResettableModelRenderer(this, 0, 19);
		this.bodyBottom.setRotationPoint(0.0F, 0.0F, 1.5F);
		this.bodyBottom.addBox(-2.0F, -1.0F, -3.2F, 4, 4, 8, 0.0F);
		this.setRotateAngle(bodyBottom, -0.2617993877991494F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 15, 44);
		this.leftLeg1.setRotationPoint(2.0F, 18.43F, 0.0F);
		this.leftLeg1.addBox(-1.0F, -0.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(leftLeg1, 0.3490658503988659F, 0.0F, 0.0F);
		this.rightFeet2 = new ResettableModelRenderer(this, 29, 61);
		this.rightFeet2.setRotationPoint(-0.5F, 0.05F, -0.75F);
		this.rightFeet2.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(rightFeet2, 0.0F, -1.0471975511965976F, 0.0F);
		this.leftFeet = new ResettableModelRenderer(this, 16, 52);
		this.leftFeet.setRotationPoint(0.01F, 3.25F, 0.0F);
		this.leftFeet.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(leftFeet, 0.17453292519943295F, 0.0F, 0.0F);
		this.beak2 = new ResettableModelRenderer(this, 28, 38);
		this.beak2.setRotationPoint(0.0F, 0.7F, -2.0F);
		this.beak2.addBox(-1.0F, -0.9F, -2.9F, 2, 1, 3, 0.0F);
		this.setRotateAngle(beak2, -0.08726646259971647F, 0.0F, 0.0F);
		this.tail = new ResettableModelRenderer(this, 15, 32);
		this.tail.setRotationPoint(0.0F, 0.9F, 6.2F);
		this.tail.addBox(-2.0F, -1.5F, -1.0F, 4, 4, 2, 0.0F);
		this.setRotateAngle(tail, -0.4363323129985824F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 28, 26);
		this.head.setRotationPoint(0.0F, -1.5F, -0.4F);
		this.head.addBox(-1.5F, -1.9F, -2.75F, 3, 3, 3, 0.0F);
		this.setRotateAngle(head, -0.5811946409141118F, 0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 29, 50);
		this.rightLeg2.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.rightLeg2.addBox(-0.5F, -0.5F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightLeg2, -0.5235987755982988F, 0.0F, 0.0F);
		this.tailFeather5 = new ResettableModelRenderer(this, 36, 6);
		this.tailFeather5.setRotationPoint(-0.5F, 0.5F, 0.0F);
		this.tailFeather5.addBox(-1.0F, 0.0F, 0.0F, 2, 0, 6, 0.0F);
		this.setRotateAngle(tailFeather5, 0.6108652381980153F, -0.17453292519943295F, -0.3490658503988659F);
		this.rightFeet = new ResettableModelRenderer(this, 30, 52);
		this.rightFeet.setRotationPoint(-0.01F, 3.25F, 0.0F);
		this.rightFeet.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(rightFeet, 0.17453292519943295F, 0.0F, 0.0F);
		this.tailFeather6 = new ResettableModelRenderer(this, 35, 6);
		this.tailFeather6.setRotationPoint(0.0F, 1.0F, 0.0F);
		this.tailFeather6.addBox(-1.0F, -0.3F, 0.0F, 2, 0, 7, 0.0F);
		this.setRotateAngle(tailFeather6, 0.6806784082777886F, 0.0F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 39, 18);
		this.neck1.setRotationPoint(0.0F, 0.0F, -0.55F);
		this.neck1.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(neck1, -0.22689280275926282F, 0.0F, 0.0F);
		this.head.addChild(this.beak1);
		this.leftFeet.addChild(this.leftFeet3);
		this.tail.addChild(this.tailFeather3);
		this.leftArm.addChild(this.leftWing2);
		this.leftFeet.addChild(this.leftFeet2);
		this.leftLeg1.addChild(this.leftLeg2);
		this.rightFeet.addChild(this.rightFeet3);
		this.leftWing2.addChild(this.leftWing3);
		this.neck1.addChild(this.neck2);
		this.tail.addChild(this.tailFeather1);
		this.rightArm.addChild(this.rightWing1);
		this.tail.addChild(this.tailFeather4);
		this.tail.addChild(this.tailFeather2);
		this.rightWing2.addChild(this.rightWing3);
		this.leftArm.addChild(this.leftWing1);
		this.rightArm.addChild(this.rightWing2);
		this.bodyMain.addChild(this.leftArm);
		this.bodyMain.addChild(this.rightArm);
		this.bodyMain.addChild(this.bodyNeck);
		this.bodyMain.addChild(this.bodyBottom);
		this.rightFeet.addChild(this.rightFeet2);
		this.leftLeg2.addChild(this.leftFeet);
		this.head.addChild(this.beak2);
		this.bodyMain.addChild(this.tail);
		this.neck2.addChild(this.head);
		this.rightLeg1.addChild(this.rightLeg2);
		this.tail.addChild(this.tailFeather5);
		this.rightLeg2.addChild(this.rightFeet);
		this.tail.addChild(this.tailFeather6);
		this.bodyNeck.addChild(this.neck1);

		this.bodyMain.saveParameters();
		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.bodyNeck.saveParameters();
		this.bodyBottom.saveParameters();
		this.tail.saveParameters();
		this.leftArm.saveParameters();
		this.rightArm.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.head.saveParameters();
		this.beak1.saveParameters();
		this.beak2.saveParameters();
		this.tailFeather1.saveParameters();
		this.tailFeather2.saveParameters();
		this.tailFeather3.saveParameters();
		this.tailFeather4.saveParameters();
		this.tailFeather5.saveParameters();
		this.tailFeather6.saveParameters();
		this.leftWing1.saveParameters();
		this.leftWing2.saveParameters();
		this.leftWing3.saveParameters();
		this.rightWing1.saveParameters();
		this.rightWing2.saveParameters();
		this.rightWing3.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftFeet.saveParameters();
		this.leftFeet2.saveParameters();
		this.leftFeet3.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightFeet.saveParameters();
		this.rightFeet2.saveParameters();
		this.rightFeet3.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale)
	{
		this.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, (EntityArchaeorhynchus) entity);

		this.bodyMain.render(scale);
		this.rightLeg1.render(scale);
		this.leftLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();

		EntityArchaeorhynchus archaeorhynchus = (EntityArchaeorhynchus) entity;
		float sittingProgress = archaeorhynchus.getSittingProgress(partialRenderTicks);

		if (sittingProgress > 0.01F)
		{
			this.bodyMain.rotationPointY += 6.0F * sittingProgress;
			this.leftLeg1.rotationPointY += 6.0F * sittingProgress;
			this.rightLeg1.rotationPointY += 6.0F * sittingProgress;

			this.leftLeg1.rotationPointZ += 1.25F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.5F * sittingProgress;
			this.leftLeg2.rotateAngleX += 3.25F * sittingProgress;
			this.leftLeg2.rotationPointZ += 1.0F * sittingProgress;
			this.leftFeet.rotateAngleX -= 1.75F * sittingProgress;
			this.leftFeet.rotationPointZ -= 0.5F * sittingProgress;

			this.rightLeg1.rotationPointZ += 1.25F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.5F * sittingProgress;
			this.rightLeg2.rotateAngleX += 3.25F * sittingProgress;
			this.rightLeg2.rotationPointZ += 1.0F * sittingProgress;
			this.rightFeet.rotateAngleX -= 1.75F * sittingProgress;
			this.rightFeet.rotationPointZ -= 0.5F * sittingProgress;
		}
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityArchaeorhynchus archaeorhynchus)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, archaeorhynchus);

		float headX = 0.5F * this.getHeadAngle(pitch);
		float headY = this.getHeadAngle(yaw);

		if (archaeorhynchus.isOnNest())
		{
			float body1 = this.getAlwaysRotateAngle(time, 0.046875F, 0.1F);
			this.bodyMain.rotateAngleX -= body1;
			this.head.rotateAngleX += headX;
			this.neck2.rotateAngleY += headY;
		}
		else if (archaeorhynchus.ridingEntity instanceof EntityPlayer)
		{
			float body1 = this.getAlwaysRotateAngle(time, 0.046875F, 0.1F);
			this.bodyMain.rotateAngleX -= body1;
			this.head.rotateAngleX += 0.25F * headX;
			this.neck2.rotateAngleY += headY;
			this.bodyMain.rotationPointY += 6.0F;
			this.leftLeg1.rotationPointY += 6.0F;
			this.rightLeg1.rotationPointY += 6.0F;

			this.leftLeg1.rotationPointZ += 1.25F;
			this.leftLeg1.rotateAngleX -= 1.5F;
			this.leftLeg2.rotateAngleX += 3.25F;
			this.leftLeg2.rotationPointZ += 1.0F;
			this.leftFeet.rotateAngleX -= 1.75F;
			this.leftFeet.rotationPointZ -= 0.5F;

			this.rightLeg1.rotationPointZ += 1.25F;
			this.rightLeg1.rotateAngleX -= 1.5F;
			this.rightLeg2.rotateAngleX += 3.25F;
			this.rightLeg2.rotationPointZ += 1.0F;
			this.rightFeet.rotateAngleX -= 1.75F;
			this.rightFeet.rotationPointZ -= 0.5F;
		}
		else
		{
			if (!archaeorhynchus.onGround)
			{
				this.head.rotateAngleX += headX - 0.8F;
				this.head.rotateAngleZ -= headY + 0.2F;
				this.neck1.rotateAngleX += 0.8F;
				this.bodyMain.rotateAngleX += 0.1F;

				this.leftLeg1.rotateAngleX += 0.6F;
				this.leftLeg2.rotateAngleX += 0.8F;
				this.leftFeet.rotateAngleX += 0.8F;

				this.rightLeg1.rotateAngleX += 0.6F;
				this.rightLeg2.rotateAngleX += 0.8F;
				this.rightFeet.rotateAngleX += 0.8F;

				float flyingPose = this.getAlwaysRotateAngle(time, 0.375F, 1.0F);
				this.leftArm.rotateAngleX += 1.1 - 0.2F * flyingPose;
				this.leftArm.rotateAngleZ -= flyingPose - 1.0F;
				this.leftWing2.rotateAngleZ += 0.25F * (flyingPose - 1.0F);
				this.leftWing3.rotateAngleZ -= 0.125F * (flyingPose - 1.0F);

				this.rightArm.rotateAngleX += 1.1 - 0.2F * flyingPose;
				this.rightArm.rotateAngleZ += flyingPose - 1.0F;
				this.rightWing2.rotateAngleZ -= 0.25F * (flyingPose - 1.0F);
				this.rightWing3.rotateAngleZ += 0.125F * (flyingPose - 1.0F);
			}
			else
			{
				float legX1 = this.getRotateAngle(time, walkSpeed, 0.375F, 1.0F);
				this.leftLeg1.rotateAngleX -= legX1;
				this.rightLeg1.rotateAngleX += legX1;

				float runningInclination = MathHelper.sin(walkSpeed);
				if (runningInclination > 0.001F)
				{
					float runningPose = this.getAlwaysRotateAngle(time, 0.375F, 0.5F);
					this.neck1.rotateAngleX -= 0.25F * runningPose;
					this.neck2.rotateAngleX += 0.5F * runningPose;
				}
				else
				{
					float body1 = this.getAlwaysRotateAngle(time, 0.046875F, 0.1F);
					this.bodyMain.rotateAngleX -= body1;

					this.head.rotateAngleX += headX;
					this.neck2.rotateAngleY += headY;
					this.neck1.rotateAngleY -= 0.5F * headY;
					this.neck1.rotateAngleX -= 0.5F * headX;
				}
			}
		}
	}

	private void resetPose()
	{
		this.bodyMain.resetParameters();
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.bodyNeck.resetParameters();
		this.bodyBottom.resetParameters();
		this.tail.resetParameters();
		this.leftArm.resetParameters();
		this.rightArm.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.head.resetParameters();
		this.beak1.resetParameters();
		this.beak2.resetParameters();
		this.tailFeather1.resetParameters();
		this.tailFeather2.resetParameters();
		this.tailFeather3.resetParameters();
		this.tailFeather4.resetParameters();
		this.tailFeather5.resetParameters();
		this.tailFeather6.resetParameters();
		this.leftWing1.resetParameters();
		this.leftWing2.resetParameters();
		this.leftWing3.resetParameters();
		this.rightWing1.resetParameters();
		this.rightWing2.resetParameters();
		this.rightWing3.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftFeet.resetParameters();
		this.leftFeet2.resetParameters();
		this.leftFeet3.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightFeet.resetParameters();
		this.rightFeet2.resetParameters();
		this.rightFeet3.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
