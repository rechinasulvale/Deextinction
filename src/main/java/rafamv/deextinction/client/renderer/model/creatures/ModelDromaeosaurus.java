package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityDromaeosaurus;

public class ModelDromaeosaurus extends ResettableModelBase
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
	public ResettableModelRenderer chest;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer head;
	public ResettableModelRenderer lowerJaw;
	public ResettableModelRenderer upperJaw1;
	public ResettableModelRenderer upperJaw2;
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
	public ResettableModelRenderer leftClaw;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer rightClaw;
	private Animator animator = new Animator();

	public ModelDromaeosaurus()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.lowerJaw = new ResettableModelRenderer(this, 5, 39);
		this.lowerJaw.setRotationPoint(0.1F, 1.85F, -3.0F);
		this.lowerJaw.addBox(-1.5F, -0.5F, -5.3F, 3, 1, 6, 0.0F);
		this.setRotateAngle(lowerJaw, 0.04363323129985824F, 0.0F, 0.0F);
		this.leftClaw = new ResettableModelRenderer(this, 16, 102);
		this.leftClaw.setRotationPoint(-0.4F, -0.1F, -1.3F);
		this.leftClaw.addBox(-1.1F, -3.0F, -0.6F, 1, 3, 1, 0.0F);
		this.setRotateAngle(leftClaw, 0.7285004297824331F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 90, 39);
		this.chest.setRotationPoint(0.0F, -0.25F, -4.0F);
		this.chest.addBox(-2.5F, -3.0F, -4.5F, 5, 6, 9, 0.0F);
		this.setRotateAngle(chest, -0.18203784098300857F, 0.0F, 0.0F);
		this.mainBodyFront = new ResettableModelRenderer(this, 91, 20);
		this.mainBodyFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mainBodyFront.addBox(-3.0F, -4.0F, -6.0F, 6, 9, 7, 0.0F);
		this.setRotateAngle(mainBodyFront, -0.15812683023068624F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 37, 0);
		this.tail1.setRotationPoint(0.0F, -1.25F, 0.0F);
		this.tail1.addBox(-1.5F, -2.0F, 0.0F, 3, 5, 8, 0.0F);
		this.setRotateAngle(tail1, 0.4363323129985824F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 2, 13);
		this.head.setRotationPoint(0.0F, -0.9F, -3.9F);
		this.head.addBox(-2.5F, -1.7F, -4.4F, 5, 5, 5, 0.0F);
		this.setRotateAngle(head, 0.7285004297824331F, 0.0F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.leftLeg3.setRotationPoint(0.0F, 6.0F, 0.7F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg3, -0.6981317007977318F, 0.0F, 0.0F);
		this.tail4 = new ResettableModelRenderer(this, 38, 43);
		this.tail4.setRotationPoint(0.0F, 0.0F, 6.7F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 11, 0.0F);
		this.setRotateAngle(tail4, -0.08726646259971647F, 0.0F, 0.0F);
		this.rightClaw = new ResettableModelRenderer(this, 16, 102);
		this.rightClaw.setRotationPoint(0.4F, -0.1F, -1.3F);
		this.rightClaw.addBox(0.0F, -3.0F, -0.6F, 1, 3, 1, 0.0F);
		this.setRotateAngle(rightClaw, 0.7285004297824331F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.rightLeg3.mirror = true;
		this.rightLeg3.setRotationPoint(0.0F, 6.0F, 0.7F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg3, -0.6981317007977318F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 4, 94);
		this.rightFoot.mirror = true;
		this.rightFoot.setRotationPoint(0.0F, 7.0F, 0.7F);
		this.rightFoot.addBox(-1.0F, -0.5F, -4.6F, 2, 1, 5, 0.0F);
		this.setRotateAngle(rightFoot, 0.3490658503988659F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 40, 30);
		this.tail3.setRotationPoint(0.0F, -0.5F, 9.9F);
		this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 3, 8, 0.0F);
		this.setRotateAngle(tail3, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 20, 50);
		this.leftArm1.setRotationPoint(3.8F, 1.0F, -3.7F);
		this.leftArm1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(leftArm1, 1.2292353921796064F, 0.5462880558742251F, -0.17453292519943295F);
		this.leftArm2 = new ResettableModelRenderer(this, 23, 61);
		this.leftArm2.setRotationPoint(0.8F, 0.8F, 0.3F);
		this.leftArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(leftArm2, -1.3962634015954636F, 0.0F, 0.0F);
		this.leftHand2 = new ResettableModelRenderer(this, 24, 82);
		this.leftHand2.setRotationPoint(-0.1F, 4.4F, 0.4F);
		this.leftHand2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 1, 0.0F);
		this.setRotateAngle(leftHand2, 0.6829473363053812F, 0.04363323129985824F, 0.0F);
		this.upperJaw2 = new ResettableModelRenderer(this, 5, 32);
		this.upperJaw2.setRotationPoint(0.0F, -0.8F, -0.2F);
		this.upperJaw2.addBox(-1.0F, -0.5F, -4.8F, 2, 1, 5, 0.0F);
		this.setRotateAngle(upperJaw2, 0.17453292519943295F, 0.0F, 0.0F);
		this.rightHand2 = new ResettableModelRenderer(this, 24, 82);
		this.rightHand2.mirror = true;
		this.rightHand2.setRotationPoint(0.1F, 4.4F, 0.4F);
		this.rightHand2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 1, 0.0F);
		this.setRotateAngle(rightHand2, 0.6829473363053812F, 0.0F, 0.0F);
		this.leftHand1 = new ResettableModelRenderer(this, 23, 74);
		this.leftHand1.setRotationPoint(-0.1F, 4.6F, 0.0F);
		this.leftHand1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftHand1, 0.5462880558742251F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.leftLeg1.setRotationPoint(4.2F, 9.0F, 4.6F);
		this.leftLeg1.addBox(-2.0F, -3.5F, -2.5F, 3, 8, 6, 0.0F);
		this.setRotateAngle(leftLeg1, -0.3490658503988659F, 0.0F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 3, 0);
		this.neck1.setRotationPoint(0.0F, -0.6F, -4.4F);
		this.neck1.addBox(-2.0F, -2.1F, -4.2F, 4, 5, 6, 0.0F);
		this.setRotateAngle(neck1, -0.36425021489121656F, 0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 4, 94);
		this.leftFoot.setRotationPoint(0.0F, 7.0F, 0.7F);
		this.leftFoot.addBox(-1.0F, -0.5F, -4.6F, 2, 1, 5, 0.0F);
		this.setRotateAngle(leftFoot, 0.3490658503988659F, 0.0F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 88, 0);
		this.mainBody.setRotationPoint(0.0F, 8.0F, 0.8F);
		this.mainBody.addBox(-3.5F, -4.0F, -1.0F, 7, 10, 8, 0.0F);
		this.setRotateAngle(mainBody, -0.022514747350726852F, 0.0F, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.rightLeg1.mirror = true;
		this.rightLeg1.setRotationPoint(-3.3F, 9.0F, 4.6F);
		this.rightLeg1.addBox(-2.0F, -3.5F, -2.5F, 3, 8, 6, 0.0F);
		this.setRotateAngle(rightLeg1, -0.3490658503988659F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 37, 15);
		this.tail2.setRotationPoint(0.0F, -0.5F, 7.0F);
		this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 4, 10, 0.0F);
		this.setRotateAngle(tail2, -0.08726646259971647F, 0.0F, 0.0F);
		this.upperJaw1 = new ResettableModelRenderer(this, 4, 24);
		this.upperJaw1.setRotationPoint(0.1F, -0.3F, -3.2F);
		this.upperJaw1.addBox(-1.5F, -0.3F, -5.3F, 3, 2, 5, 0.0F);
		this.setRotateAngle(upperJaw1, 0.04363323129985824F, 0.0F, 0.0F);
		this.rightArmFeathers = new ResettableModelRenderer(this, 20, 99);
		this.rightArmFeathers.mirror = true;
		this.rightArmFeathers.setRotationPoint(0.0F, 4.3F, 0.5F);
		this.rightArmFeathers.addBox(0.0F, 0.0F, -3.5F, 0, 8, 10, 0.0F);
		this.setRotateAngle(rightArmFeathers, 1.5707963267948966F, 0.2617993877991494F, 0.0F);
		this.butt = new ResettableModelRenderer(this, 93, 59);
		this.butt.setRotationPoint(0.0F, -0.2F, 6.5F);
		this.butt.addBox(-3.0F, -3.0F, -2.5F, 6, 7, 5, 0.0F);
		this.setRotateAngle(butt, -0.27314402793711257F, 0.0F, 0.0F);
		this.rightHand1 = new ResettableModelRenderer(this, 23, 74);
		this.rightHand1.mirror = true;
		this.rightHand1.setRotationPoint(0.1F, 4.6F, 0.0F);
		this.rightHand1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightHand1, 0.5462880558742251F, 0.0F, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 23, 61);
		this.rightArm2.mirror = true;
		this.rightArm2.setRotationPoint(-0.8F, 0.8F, 0.3F);
		this.rightArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(rightArm2, -1.3962634015954636F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 20, 50);
		this.rightArm1.mirror = true;
		this.rightArm1.setRotationPoint(-3.8F, 1.0F, -3.7F);
		this.rightArm1.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(rightArm1, 1.2292353921796064F, -0.5462880558742251F, 0.17453292519943295F);
		this.leftArmFeathers = new ResettableModelRenderer(this, 20, 99);
		this.leftArmFeathers.setRotationPoint(0.0F, 4.3F, 0.5F);
		this.leftArmFeathers.addBox(0.0F, -0.1F, -3.5F, 0, 8, 10, 0.0F);
		this.setRotateAngle(leftArmFeathers, 1.5707963267948966F, -0.2617993877991494F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.rightLeg2.mirror = true;
		this.rightLeg2.setRotationPoint(-0.6F, 2.5F, 0.0F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 4, 0.0F);
		this.setRotateAngle(rightLeg2, 0.6981317007977318F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.leftLeg2.setRotationPoint(-0.2F, 2.5F, 0.0F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 7, 4, 0.0F);
		this.setRotateAngle(leftLeg2, 0.6981317007977318F, 0.0F, 0.0F);
		this.head.addChild(this.lowerJaw);
		this.leftFoot.addChild(this.leftClaw);
		this.mainBodyFront.addChild(this.chest);
		this.mainBody.addChild(this.mainBodyFront);
		this.butt.addChild(this.tail1);
		this.neck1.addChild(this.head);
		this.leftLeg2.addChild(this.leftLeg3);
		this.tail3.addChild(this.tail4);
		this.rightFoot.addChild(this.rightClaw);
		this.rightLeg2.addChild(this.rightLeg3);
		this.rightLeg3.addChild(this.rightFoot);
		this.tail2.addChild(this.tail3);
		this.mainBodyFront.addChild(this.leftArm1);
		this.leftArm1.addChild(this.leftArm2);
		this.leftArm2.addChild(this.leftHand2);
		this.upperJaw1.addChild(this.upperJaw2);
		this.rightArm2.addChild(this.rightHand2);
		this.leftArm2.addChild(this.leftHand1);
		this.chest.addChild(this.neck1);
		this.leftLeg3.addChild(this.leftFoot);
		this.tail1.addChild(this.tail2);
		this.head.addChild(this.upperJaw1);
		this.rightArm2.addChild(this.rightArmFeathers);
		this.mainBody.addChild(this.butt);
		this.rightArm2.addChild(this.rightHand1);
		this.rightArm1.addChild(this.rightArm2);
		this.mainBodyFront.addChild(this.rightArm1);
		this.leftArm2.addChild(this.leftArmFeathers);
		this.rightLeg1.addChild(this.rightLeg2);
		this.leftLeg1.addChild(this.leftLeg2);

		this.mainBody.saveParameters();
		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.butt.saveParameters();
		this.mainBodyFront.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.chest.saveParameters();
		this.leftArm1.saveParameters();
		this.rightArm1.saveParameters();
		this.neck1.saveParameters();
		this.head.saveParameters();
		this.lowerJaw.saveParameters();
		this.upperJaw1.saveParameters();
		this.upperJaw2.saveParameters();
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
		this.leftClaw.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.rightClaw.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityDromaeosaurus creature = (EntityDromaeosaurus) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);

		this.leftLeg1.render(scale);
		this.mainBody.render(scale);
		this.rightLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();
		EntityDromaeosaurus creature = (EntityDromaeosaurus) entity;
		float sittingProgress = creature.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 10.0F * sittingProgress;

			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotationPointY += 1.2F * sittingProgress;
			this.rightArm2.rotationPointZ -= 1.2F * sittingProgress;
			this.rightArm2.rotateAngleX -= 1.3F * sittingProgress;

			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotationPointY += 1.2F * sittingProgress;
			this.leftArm2.rotationPointZ -= 1.2F * sittingProgress;
			this.leftArm2.rotateAngleX -= 1.3F * sittingProgress;

			this.rightLeg1.rotationPointY += 10.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.5F * sittingProgress;
			this.rightLeg2.rotationPointY += 0.8F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotationPointY -= 1.0F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.3F * sittingProgress;
			this.rightFoot.rotationPointY -= 1.25F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.2F * sittingProgress;

			this.leftLeg1.rotationPointY += 10.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.5F * sittingProgress;
			this.leftLeg2.rotationPointY += 0.8F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotationPointY -= 1.0F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.3F * sittingProgress;
			this.leftFoot.rotationPointY -= 1.25F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.2F * sittingProgress;
		}

		float tailBuffer = creature.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityDromaeosaurus creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);

		if (creature.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.mainBody.rotateAngleX -= naturalMovement;
			this.mainBodyFront.rotateAngleX -= naturalMovement;
			this.neck1.rotateAngleX -= 0.6F * naturalMovement;
			this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
			this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

			float headX = this.getHeadAngle(pitch) / 2.0F;
			float headY = this.getHeadAngle(yaw) / 2.0F;
			this.head.rotateAngleX += 0.4F * headX;
			this.head.rotateAngleY += 0.4F * headY;
			this.neck1.rotateAngleX += 0.25F * headX;
			this.neck1.rotateAngleY += 0.5F * headY;
			this.mainBodyFront.rotateAngleY += 0.4F * headY;

			float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 4, 0.046875F, 0.05F, -2.0F);
			this.tail1.rotateAngleX -= naturalTailMovement[0];
			this.tail2.rotateAngleX -= naturalTailMovement[1];
			this.tail3.rotateAngleX -= naturalTailMovement[2];
			this.tail4.rotateAngleX -= naturalTailMovement[3];
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
				this.rightArm1.rotateAngleX += 0.5F * armMovement;
				this.leftArm1.rotateAngleX -= 0.5F * armMovement;

				float lleg1 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float lleg2 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float lleg3 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float lfoot = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

				float rleg1 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float rleg2 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float rleg3 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float rfoot = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

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
					float[] naturalTailMovement = this.getChainMovement(time, walkSpeed, 4, 0.75F, 0.05F, -2.0F);
					this.tail1.rotateAngleX -= naturalTailMovement[0];
					this.tail2.rotateAngleX -= naturalTailMovement[1];
					this.tail3.rotateAngleX -= naturalTailMovement[2];
					this.tail4.rotateAngleX -= naturalTailMovement[3];

					float tailSwing = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 0.15F, 0.0F);
					this.tail1.rotateAngleY -= tailSwing;
				}

				float runningInclination = MathHelper.sin(walkSpeed);
				if (runningInclination > 0.3F)
				{
					runningInclination -= 0.3F;
					if (runningInclination > 0.6F)
						runningInclination = 0.6F;

					this.mainBody.rotateAngleX += 0.125F * runningInclination;
				}
			}
			else
			{
				float naturalMovement = this.getAlwaysRotateAngle(time, 0.09375F, 0.03F);
				this.mainBody.rotateAngleX -= naturalMovement;
				this.mainBodyFront.rotateAngleX -= naturalMovement;
				this.neck1.rotateAngleX -= 0.6F * naturalMovement;
				this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
				this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

				float headX = this.getHeadAngle(pitch) / 2.0F;
				float headY = this.getHeadAngle(yaw) / 2.0F;
				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.neck1.rotateAngleX += 0.25F * headX;
				this.neck1.rotateAngleY += 0.5F * headY;
				this.chest.rotateAngleY += 0.3F * headY;
				this.mainBodyFront.rotateAngleY += 0.3F * headY;

				float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 4, 0.09375F, 0.05F, -2.0F);
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
			this.animator.rotate(this.neck1, -0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
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
		this.butt.resetParameters();
		this.mainBodyFront.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.chest.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.neck1.resetParameters();
		this.head.resetParameters();
		this.lowerJaw.resetParameters();
		this.upperJaw1.resetParameters();
		this.upperJaw2.resetParameters();
		this.leftArm2.resetParameters();
		this.leftHand2.resetParameters();
		this.leftHand1.resetParameters();
		this.leftArmFeathers.resetParameters();
		this.rightArm2.resetParameters();
		this.rightHand2.resetParameters();
		this.rightHand1.resetParameters();
		this.rightArmFeathers.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.leftClaw.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.rightClaw.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
