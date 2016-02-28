package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntitySinovenator;

public class ModelSinovenator extends ResettableModelBase
{
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer butt;
	public ResettableModelRenderer mainBodyFront;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tailFeathers;
	public ResettableModelRenderer chest;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer headJoint;
	public ResettableModelRenderer head;
	public ResettableModelRenderer mouthBottom;
	public ResettableModelRenderer mouthMiddleBottom;
	public ResettableModelRenderer mouthTop;
	public ResettableModelRenderer mouthMiddleTop;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftHand2;
	public ResettableModelRenderer leftHand1;
	public ResettableModelRenderer leftArmFeathers;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightHand2;
	public ResettableModelRenderer rightHand1;
	public ResettableModelRenderer rightArmFeathers;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	private Animator animator = new Animator();

	public ModelSinovenator()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.rightLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.rightLeg1.mirror = true;
		this.rightLeg1.setRotationPoint(-3.8F, 7.9F, 4.6F);
		this.rightLeg1.addBox(-2.0F, -3.5F, -2.5F, 3, 7, 5, 0.0F);
		this.rightHand1 = new ResettableModelRenderer(this, 23, 74);
		this.rightHand1.mirror = true;
		this.rightHand1.setRotationPoint(0.1F, 5.5F, 0.5F);
		this.rightHand1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightHand1, -0.41015237421866746F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 64, 45);
		this.head.setRotationPoint(0.0F, -0.5F, 0.5F);
		this.head.addBox(-2.0F, -2.5F, -3.0F, 4, 4, 4, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 23, 61);
		this.rightArm2.mirror = true;
		this.rightArm2.setRotationPoint(-0.8F, 0.1F, 0.3F);
		this.rightArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(rightArm2, -0.3490658503988659F, 0.0F, 0.0F);
		this.mainBodyFront = new ResettableModelRenderer(this, 91, 20);
		this.mainBodyFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mainBodyFront.addBox(-3.0F, -4.0F, -6.0F, 6, 7, 7, 0.0F);
		this.setRotateAngle(mainBodyFront, -0.08726646259971647F, 0.0F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 88, 0);
		this.mainBody.setRotationPoint(0.0F, 8.0F, 0.8F);
		this.mainBody.addBox(-3.5F, -4.0F, -1.0F, 7, 8, 8, 0.0F);
		this.setRotateAngle(mainBody, 0.08726646259971647F, 0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.rightLeg2.mirror = true;
		this.rightLeg2.setRotationPoint(-0.6F, 2.1F, 0.0F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 0.3490658503988659F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 20, 50);
		this.rightArm1.mirror = true;
		this.rightArm1.setRotationPoint(-3.8F, 1.0F, -3.7F);
		this.rightArm1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(rightArm1, 0.0F, 0.0F, 0.17453292519943295F);
		this.butt = new ResettableModelRenderer(this, 93, 80);
		this.butt.setRotationPoint(0.0F, -0.2F, 6.5F);
		this.butt.addBox(-3.0F, -3.0F, -2.5F, 6, 6, 5, 0.0F);
		this.setRotateAngle(butt, -0.3490658503988659F, 0.0F, 0.0F);
		this.mouthMiddleTop = new ResettableModelRenderer(this, 0, 0);
		this.mouthMiddleTop.setRotationPoint(0.0F, -0.9F, -0.65F);
		this.mouthMiddleTop.addBox(-1.0F, -0.6F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(mouthMiddleTop, -0.012740903539558604F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 37, 15);
		this.tail2.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8, 0.0F);
		this.setRotateAngle(tail2, -0.08726646259971647F, 0.0F, 0.0F);
		this.rightArmFeathers = new ResettableModelRenderer(this, 20, 85);
		this.rightArmFeathers.mirror = true;
		this.rightArmFeathers.setRotationPoint(0.0F, 4.2F, 0.0F);
		this.rightArmFeathers.addBox(0.0F, 0.0F, -3.5F, 0, 4, 7, 0.0F);
		this.setRotateAngle(rightArmFeathers, 1.6390387005478748F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 90, 35);
		this.chest.setRotationPoint(0.0F, -0.25F, -4.0F);
		this.chest.addBox(-2.5F, -3.0F, -4.5F, 5, 6, 9, 0.0F);
		this.setRotateAngle(chest, -0.2617993877991494F, 0.0F, 0.0F);
		this.mouthBottom = new ResettableModelRenderer(this, 0, 0);
		this.mouthBottom.setRotationPoint(0.0F, 0.5F, -2.5F);
		this.mouthBottom.addBox(-1.0F, -0.6F, -4.75F, 2, 1, 5, 0.0F);
		this.setRotateAngle(mouthBottom, -0.08726646259971647F, 0.0F, 0.0F);
		this.rightHand2 = new ResettableModelRenderer(this, 24, 82);
		this.rightHand2.mirror = true;
		this.rightHand2.setRotationPoint(0.1F, 5.3F, 0.8F);
		this.rightHand2.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightHand2, -0.1832595714594046F, 0.04363323129985824F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 4, 94);
		this.rightFoot.mirror = true;
		this.rightFoot.setRotationPoint(0.0F, 7.0F, 0.8F);
		this.rightFoot.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(rightFoot, 0.3490658503988659F, 0.0F, 0.0F);
		this.leftHand1 = new ResettableModelRenderer(this, 23, 74);
		this.leftHand1.setRotationPoint(-0.1F, 5.5F, 0.5F);
		this.leftHand1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftHand1, -0.41015237421866746F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.rightLeg3.mirror = true;
		this.rightLeg3.setRotationPoint(0.0F, 7.1F, 0.0F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg3, -0.6981317007977318F, 0.0F, 0.0F);
		this.mouthTop = new ResettableModelRenderer(this, 0, 0);
		this.mouthTop.setRotationPoint(0.0F, -2.0F, -2.5F);
		this.mouthTop.addBox(-1.0F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(mouthTop, 0.27314402793711257F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 40, 30);
		this.tail3.setRotationPoint(0.0F, -0.2F, 7.4F);
		this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F);
		this.setRotateAngle(tail3, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftHand2 = new ResettableModelRenderer(this, 24, 82);
		this.leftHand2.setRotationPoint(-0.1F, 5.3F, 0.8F);
		this.leftHand2.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftHand2, -0.1832595714594046F, 0.04363323129985824F, 0.0F);
		this.leftArmFeathers = new ResettableModelRenderer(this, 20, 85);
		this.leftArmFeathers.setRotationPoint(0.0F, 4.2F, 0.0F);
		this.leftArmFeathers.addBox(0.0F, 0.0F, -3.5F, 0, 4, 7, 0.0F);
		this.setRotateAngle(leftArmFeathers, 1.6390387005478748F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 20, 50);
		this.leftArm1.setRotationPoint(3.8F, 1.0F, -3.7F);
		this.leftArm1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(leftArm1, 0.0F, 0.0F, -0.17453292519943295F);
		this.tail1 = new ResettableModelRenderer(this, 37, 0);
		this.tail1.setRotationPoint(0.0F, -1.25F, 0.0F);
		this.tail1.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 8, 0.0F);
		this.setRotateAngle(tail1, 0.39269908169872414F, 0.0F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 95, 55);
		this.neck1.setRotationPoint(0.0F, -0.5F, -3.2F);
		this.neck1.addBox(-1.5F, -2.0F, -6.0F, 3, 3, 6, 0.0F);
		this.setRotateAngle(neck1, -0.41364303272265607F, 0.0F, -0.011868238913561441F);
		this.leftLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.leftLeg3.setRotationPoint(0.0F, 7.1F, 0.0F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg3, -0.6981317007977318F, 0.0F, 0.0F);
		this.tail4 = new ResettableModelRenderer(this, 38, 40);
		this.tail4.setRotationPoint(0.0F, 0.0F, 5.5F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
		this.setRotateAngle(tail4, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.leftLeg1.setRotationPoint(4.7F, 7.9F, 4.6F);
		this.leftLeg1.addBox(-2.0F, -3.5F, -2.5F, 3, 7, 5, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 95, 65);
		this.neck2.setRotationPoint(0.0F, 0.0F, -5.6F);
		this.neck2.addBox(-1.5F, -2.0F, -6.0F, 3, 3, 6, 0.0F);
		this.setRotateAngle(neck2, -0.32009338481576005F, 0.0F, 0.0F);
		this.headJoint = new ResettableModelRenderer(this, 72, 46);
		this.headJoint.setRotationPoint(0.0F, -0.5F, -6.0F);
		this.headJoint.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(headJoint, 0.9948376736367678F, 0.0F, 0.0F);
		this.tailFeathers = new ResettableModelRenderer(this, 29, 55);
		this.tailFeathers.setRotationPoint(0.0F, -0.5F, 0.2F);
		this.tailFeathers.addBox(-3.5F, 0.0F, 0.0F, 7, 0, 12, 0.0F);
		this.mouthMiddleBottom = new ResettableModelRenderer(this, 0, 0);
		this.mouthMiddleBottom.setRotationPoint(0.0F, -0.6F, -2.5F);
		this.mouthMiddleBottom.addBox(-1.0F, -0.5F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(mouthMiddleBottom, -0.013264502315156905F, 0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 4, 94);
		this.leftFoot.setRotationPoint(0.0F, 7.0F, 0.8F);
		this.leftFoot.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(leftFoot, 0.3490658503988659F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.leftLeg2.setRotationPoint(-0.2F, 2.1F, 0.0F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 0.3490658503988659F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 23, 61);
		this.leftArm2.setRotationPoint(0.8F, 0.1F, 0.3F);
		this.leftArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 6, 2, 0.0F);
		this.setRotateAngle(leftArm2, -0.3490658503988659F, 0.0F, 0.0F);

		this.rightArm2.addChild(this.rightHand1);
		this.headJoint.addChild(this.head);
		this.rightArm1.addChild(this.rightArm2);
		this.mainBody.addChild(this.mainBodyFront);
		this.rightLeg1.addChild(this.rightLeg2);
		this.mainBodyFront.addChild(this.rightArm1);
		this.mainBody.addChild(this.butt);
		this.head.addChild(this.mouthMiddleTop);
		this.tail1.addChild(this.tail2);
		this.rightArm2.addChild(this.rightArmFeathers);
		this.mainBodyFront.addChild(this.chest);
		this.head.addChild(this.mouthBottom);
		this.rightArm2.addChild(this.rightHand2);
		this.rightLeg3.addChild(this.rightFoot);
		this.leftArm2.addChild(this.leftHand1);
		this.rightLeg2.addChild(this.rightLeg3);
		this.head.addChild(this.mouthTop);
		this.tail2.addChild(this.tail3);
		this.leftArm2.addChild(this.leftHand2);
		this.leftArm2.addChild(this.leftArmFeathers);
		this.mainBodyFront.addChild(this.leftArm1);
		this.butt.addChild(this.tail1);
		this.chest.addChild(this.neck1);
		this.leftLeg2.addChild(this.leftLeg3);
		this.tail3.addChild(this.tail4);
		this.neck1.addChild(this.neck2);
		this.neck2.addChild(this.headJoint);
		this.tail4.addChild(this.tailFeathers);
		this.head.addChild(this.mouthMiddleBottom);
		this.leftLeg3.addChild(this.leftFoot);
		this.leftLeg1.addChild(this.leftLeg2);
		this.leftArm1.addChild(this.leftArm2);

		this.mainBody.saveParameters();
		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.butt.saveParameters();
		this.mainBodyFront.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.tailFeathers.saveParameters();
		this.chest.saveParameters();
		this.leftArm1.saveParameters();
		this.rightArm1.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.headJoint.saveParameters();
		this.head.saveParameters();
		this.mouthBottom.saveParameters();
		this.mouthMiddleBottom.saveParameters();
		this.mouthTop.saveParameters();
		this.mouthMiddleTop.saveParameters();
		this.leftArm2.saveParameters();
		this.leftHand2.saveParameters();
		this.leftHand1.saveParameters();
		this.leftArmFeathers.saveParameters();
		this.rightArm2.saveParameters();
		this.rightHand2.saveParameters();
		this.rightHand1.saveParameters();
		this.rightArmFeathers.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntitySinovenator creature = (EntitySinovenator) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.rightLeg1.render(scale);
		this.mainBody.render(scale);
		this.leftLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();

		EntitySinovenator sinovenator = (EntitySinovenator) entity;
		float sittingProgress = sinovenator.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 8.0F * sittingProgress;

			this.rightLeg1.rotationPointY += 9.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.2F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 2.0F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 2.0F * sittingProgress;
			this.rightFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.rightFoot.rotationPointY -= 1.0F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.0F * sittingProgress;

			this.leftLeg1.rotationPointY += 9.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.2F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 2.0F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 2.0F * sittingProgress;
			this.leftFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.leftFoot.rotationPointY -= 1.0F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.0F * sittingProgress;
		}

		float tailBuffer = sinovenator.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntitySinovenator sinovenator)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, sinovenator);

		float headX = this.getHeadAngle(pitch) / 2.0F;
		float headY = this.getHeadAngle(yaw) / 2.0F;

		if (sinovenator.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.0234375F, 0.1F);
			this.mainBody.rotateAngleX -= 0.5F * naturalMovement;
			this.leftArm1.rotateAngleX -= naturalMovement;
			this.rightArm1.rotateAngleX -= naturalMovement;
			this.neck1.rotateAngleX += 0.5F * naturalMovement;
			this.neck2.rotateAngleX -= 0.5F * naturalMovement;

			this.head.rotateAngleX += 0.3F * headX;
			this.head.rotateAngleY += 0.3F * headY;
			this.neck2.rotateAngleX += 0.1F * headX;
			this.neck2.rotateAngleY += 0.2F * headY;
			this.neck1.rotateAngleX += 0.1F * headX;
			this.neck1.rotateAngleY += 0.2F * headY;
		}
		else
		{
			if (walkSpeed > 0.001F)
			{
				float bodyBob1 = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 0.6F, 0.6F) - 0.6F;
				this.mainBody.rotationPointX -= bodyBob1;
				this.rightLeg1.rotationPointX -= bodyBob1;
				this.leftLeg1.rotationPointX -= bodyBob1;

				float bodyBob2 = this.getRotateAngle(time, walkSpeed, 0.75F, 1.0F);
				this.mainBody.rotationPointY -= bodyBob2;
				this.rightLeg1.rotationPointY -= bodyBob2;
				this.leftLeg1.rotationPointY -= bodyBob2;

				float armMovement = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 1.0F, 0.5F);
				this.rightArm1.rotationPointZ += 2.5F * walkSpeed;
				this.rightArm1.rotateAngleX += 0.5F * armMovement;
				this.leftArm1.rotationPointZ -= 2.5F * walkSpeed;
				this.leftArm1.rotateAngleX -= 0.5F * armMovement;

				float lleg1 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float lleg2 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float lleg3 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float lfoot = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

				float rleg1 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float rleg2 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float rleg3 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float rfoot = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

				this.leftLeg1.rotateAngleX += lleg1;
				this.leftLeg2.rotateAngleX += lleg2;
				this.leftLeg3.rotateAngleX += lleg3;
				this.leftFoot.rotateAngleX += lfoot;

				this.rightLeg1.rotateAngleX += rleg1;
				this.rightLeg2.rotateAngleX += rleg2;
				this.rightLeg3.rotateAngleX += rleg3;
				this.rightFoot.rotateAngleX += rfoot;

				if (walkSpeed > 0.6F)
				{
					float[] naturalTailMovement = this.getChainMovement(time, walkSpeed, 4, 0.75F, 0.1F, -2.0F);
					this.tail1.rotateAngleX -= naturalTailMovement[0];
					this.tail2.rotateAngleX -= naturalTailMovement[1];
					this.tail3.rotateAngleX -= naturalTailMovement[2];
					this.tail4.rotateAngleX -= naturalTailMovement[3];

					float tailSwing = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 0.3F, 0.0F);
					this.tail1.rotateAngleY -= tailSwing;
				}

				float runningInclination = MathHelper.sin(walkSpeed);
				if (runningInclination > 0.3F)
				{
					runningInclination -= 0.3F;
					if (runningInclination > 0.6F)
						runningInclination = 0.6F;

					this.mainBody.rotateAngleX += 0.25F * runningInclination;
				}
			}
			else
			{
				float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.2F);
				this.mainBody.rotateAngleX -= 0.25F * naturalMovement;
				this.mainBodyFront.rotateAngleX -= 0.5F * naturalMovement;
				this.neck1.rotateAngleX += naturalMovement;
				this.neck2.rotateAngleX -= naturalMovement;
				this.leftArm1.rotateAngleX += 0.5F * naturalMovement;
				this.rightArm1.rotateAngleX += 0.5F * naturalMovement;

				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.neck2.rotateAngleX += 0.2F * headX;
				this.neck2.rotateAngleY += 0.4F * headY;
				this.neck1.rotateAngleX += 0.2F * headX;
				this.neck1.rotateAngleY += 0.4F * headY;
				this.chest.rotateAngleY += 0.2F * headY;
				this.mainBodyFront.rotateAngleY += 0.2F * headY;

				float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 4, 0.046875F, 0.05F, -2.0F);
				this.tail1.rotateAngleX -= naturalTailMovement[0];
				this.tail2.rotateAngleX -= naturalTailMovement[1];
				this.tail3.rotateAngleX -= naturalTailMovement[2];
				this.tail4.rotateAngleX -= naturalTailMovement[3];
			}
		}
	}

	public void animateModel(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, IAnimatedEntity entity)
	{
		if (entity.getAnimID() == DEAnimationList.ATTACKING)
		{
			this.animator.update(entity);
			this.animator.setAnim(1);

			this.animator.startPhase(3);
			this.animator.rotate(this.mainBody, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.mainBodyFront, -0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.tail1, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail2, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail3, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail4, 0.1F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(3);
			this.animator.move(this.mainBody, 0.0F, 0.0F, -3.0F);
			this.animator.rotate(this.mainBody, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.mainBodyFront, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.neck1, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.neck1, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.mouthBottom, 0.6F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.setStationaryPhase(2);
			this.animator.startPhase(2);
			this.animator.rotate(this.mainBody, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.mainBodyFront, -0.15F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(2);
			this.animator.rotate(this.mainBody, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.mainBodyFront, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.resetPhase(2);
		}
	}

	private void resetPose()
	{
		this.mainBody.resetParameters();
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.mainBodyFront.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.chest.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.head.resetParameters();
		this.mouthBottom.resetParameters();
		this.leftArm2.resetParameters();
		this.leftHand2.resetParameters();
		this.leftHand1.resetParameters();
		this.rightArm2.resetParameters();
		this.rightHand2.resetParameters();
		this.rightHand1.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
