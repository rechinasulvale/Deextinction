package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityVelociraptor;

public class ModelVelociraptor extends ResettableModelBase
{
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer bodyMain;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer leftFootClaw;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer rightFootClaw;
	public ResettableModelRenderer chest;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer neck3;
	public ResettableModelRenderer neck4;
	public ResettableModelRenderer head;
	public ResettableModelRenderer upperjaw;
	public ResettableModelRenderer teeth;
	public ResettableModelRenderer upperjaw_1;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightWing;
	public ResettableModelRenderer rightFinder2;
	public ResettableModelRenderer rightFinder1;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftWing;
	public ResettableModelRenderer leftFinger1;
	public ResettableModelRenderer leftFinger2;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tail5;
	private Animator animator = new Animator();

	public ModelVelociraptor()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.rightWing = new ResettableModelRenderer(this, 0, -2);
		this.rightWing.setRotationPoint(-0.6F, 3.2F, -0.3F);
		this.rightWing.addBox(0.0F, -0.1F, -5.5F, 0, 8, 9, 0.0F);
		this.setRotateAngle(rightWing, 0.9105382707654417F, 0.0F, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 50, 80);
		this.rightLeg1.setRotationPoint(-1.5F, 9.4F, 3.2F);
		this.rightLeg1.addBox(-3.0F, 0.0F, 0.0F, 3, 7, 5, 0.0F);
		this.setRotateAngle(rightLeg1, -0.40980330836826856F, 0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 50, 120);
		this.leftFoot.setRotationPoint(0.0F, 4.4F, 0.3F);
		this.leftFoot.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(leftFoot, -1.117010721276371F, 0.0F, 0.0F);
		this.rightFootClaw = new ResettableModelRenderer(this, 34, 115);
		this.rightFootClaw.setRotationPoint(0.8F, 1.2F, 0.3F);
		this.rightFootClaw.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(rightFootClaw, -0.8726646259971648F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 0, 17);
		this.chest.setRotationPoint(0.0F, -0.3F, -1.5F);
		this.chest.addBox(-2.5F, -2.5F, -4.5F, 5, 6, 5, 0.0F);
		this.setRotateAngle(chest, 0.17453292519943295F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 34, 85);
		this.leftArm1.setRotationPoint(1.0F, 2.0F, -3.4F);
		this.leftArm1.addBox(0.0F, 0.0F, -1.0F, 1, 5, 3, 0.0F);
		this.setRotateAngle(leftArm1, 1.1383037381507017F, 0.9105382707654417F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 34, 3);
		this.tail1.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail1.addBox(-2.5F, -2.5F, 0.0F, 5, 6, 3, 0.0F);
		this.setRotateAngle(tail1, 0.2617993877991494F, 0.0F, 0.0F);
		this.upperjaw_1 = new ResettableModelRenderer(this, 23, 29);
		this.upperjaw_1.setRotationPoint(0.5F, 0.8F, -1.97F);
		this.upperjaw_1.addBox(-2.0F, -0.6F, -5.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(upperjaw_1, -0.091106186954104F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 50, 95);
		this.leftLeg2.setRotationPoint(1.5F, 6.0F, 0.9F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 1.1383037381507017F, 0.0F, 0.0F);
		this.leftFootClaw = new ResettableModelRenderer(this, 34, 115);
		this.leftFootClaw.setRotationPoint(-0.8F, 1.2F, 0.3F);
		this.leftFootClaw.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(leftFootClaw, -0.8726646259971648F, 0.0F, 0.0F);
		this.leftFinger1 = new ResettableModelRenderer(this, 6, 1);
		this.leftFinger1.setRotationPoint(0.0F, 2.7F, -0.1F);
		this.leftFinger1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(leftFinger1, 0.6981317007977318F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 34, 30);
		this.tail3.setRotationPoint(0.0F, -0.1F, 5.2F);
		this.tail3.addBox(-1.5F, -1.5F, 0.0F, 3, 4, 6, 0.0F);
		this.setRotateAngle(tail3, -0.05235987755982988F, 0.0F, -0.024260076602721177F);
		this.head = new ResettableModelRenderer(this, 1, 31);
		this.head.setRotationPoint(0.0F, -0.2F, -1.8F);
		this.head.addBox(-2.0F, -1.5F, -4.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(head, 0.0874409955249159F, 0.0F, 0.0F);
		this.rightFinder2 = new ResettableModelRenderer(this, 1, 0);
		this.rightFinder2.setRotationPoint(0.0F, 2.8F, 1.0F);
		this.rightFinder2.addBox(-0.5F, -0.5F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(rightFinder2, 0.8726646259971648F, 0.0F, 0.0F);
		this.neck4 = new ResettableModelRenderer(this, 64, 34);
		this.neck4.setRotationPoint(0.0F, 0.2F, -1.6F);
		this.neck4.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 2, 0.0F);
		this.setRotateAngle(neck4, 0.5918411493512771F, 0.0F, 0.0F);
		this.leftWing = new ResettableModelRenderer(this, 0, -2);
		this.leftWing.setRotationPoint(0.5F, 3.2F, -0.3F);
		this.leftWing.addBox(0.0F, -0.1F, -5.5F, 0, 8, 9, 0.0F);
		this.setRotateAngle(leftWing, 0.9105382707654417F, 0.0F, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 34, 100);
		this.rightArm2.setRotationPoint(-0.5F, 3.5F, 0.3F);
		this.rightArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 2, 0.0F);
		this.setRotateAngle(rightArm2, -1.3962634015954636F, 0.0F, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 63, 41);
		this.neck2.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.neck2.addBox(-1.5F, -1.5F, -4.0F, 3, 4, 4, 0.0F);
		this.setRotateAngle(neck2, -0.40980330836826856F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 50, 110);
		this.rightLeg3.setRotationPoint(0.0F, 5.4F, 0.5F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(rightLeg3, -1.1838568316277536F, 0.0F, 0.0F);
		this.upperjaw = new ResettableModelRenderer(this, 22, 41);
		this.upperjaw.setRotationPoint(0.5F, -0.8F, -1.9F);
		this.upperjaw.addBox(-1.5F, 0.0F, -7.0F, 2, 2, 5, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 50, 95);
		this.rightLeg2.setRotationPoint(-1.5F, 6.0F, 0.9F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 1.1383037381507017F, 0.0F, 0.0F);
		this.teeth = new ResettableModelRenderer(this, 1, 42);
		this.teeth.setRotationPoint(0.0F, 2.2F, -3.5F);
		this.teeth.addBox(-1.0F, -1.2F, -5.0F, 2, 1, 5, 0.0F);
		this.rightFinder1 = new ResettableModelRenderer(this, 6, 1);
		this.rightFinder1.setRotationPoint(0.0F, 2.7F, -0.1F);
		this.rightFinder1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(rightFinder1, 0.6981317007977318F, 0.0F, 0.0F);
		this.leftFinger2 = new ResettableModelRenderer(this, 1, 0);
		this.leftFinger2.setRotationPoint(0.0F, 0.8F, 0.8F);
		this.leftFinger2.addBox(-0.5F, -0.5F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(leftFinger2, 0.17453292519943295F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 34, 16);
		this.tail2.setRotationPoint(0.0F, -0.3F, 2.6F);
		this.tail2.addBox(-2.0F, -2.0F, 0.0F, 4, 5, 6, 0.0F);
		this.setRotateAngle(tail2, 0.136659280431156F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 50, 120);
		this.rightFoot.setRotationPoint(0.0F, 4.4F, 0.3F);
		this.rightFoot.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
		this.setRotateAngle(rightFoot, -1.117010721276371F, 0.0F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 1, 51);
		this.neck1.setRotationPoint(0.0F, -0.4F, -4.0F);
		this.neck1.addBox(-2.0F, -2.0F, -2.0F, 4, 5, 3, 0.0F);
		this.setRotateAngle(neck1, -0.4363323129985824F, 0.0F, 0.0F);
		this.neck3 = new ResettableModelRenderer(this, 64, 25);
		this.neck3.setRotationPoint(0.0F, -0.45F, -4.2F);
		this.neck3.addBox(-1.5F, -1.0F, -2.5F, 3, 4, 3, 0.0F);
		this.setRotateAngle(neck3, 0.27314402793711257F, 0.0F, 0.0F);
		this.bodyMain = new ResettableModelRenderer(this, 68, 1);
		this.bodyMain.setRotationPoint(0.0F, 11.0F, 2.7F);
		this.bodyMain.addBox(-3.0F, -3.0F, -2.0F, 6, 7, 7, 0.0F);
		this.setRotateAngle(bodyMain, -0.136659280431156F, 0.0F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 50, 110);
		this.leftLeg3.setRotationPoint(0.0F, 5.4F, 0.5F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(leftLeg3, -1.1838568316277536F, 0.0F, 0.0F);
		this.tail4 = new ResettableModelRenderer(this, 32, 44);
		this.tail4.setRotationPoint(0.0F, -0.2F, 5.7F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 3, 9, 0.0F);
		this.setRotateAngle(tail4, -0.05235987755982988F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 34, 100);
		this.leftArm2.setRotationPoint(0.5F, 3.5F, 0.3F);
		this.leftArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 2, 0.0F);
		this.setRotateAngle(leftArm2, -1.3962634015954636F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 50, 80);
		this.leftLeg1.setRotationPoint(1.5F, 9.4F, 3.2F);
		this.leftLeg1.addBox(0.0F, 0.0F, 0.0F, 3, 7, 5, 0.0F);
		this.setRotateAngle(leftLeg1, -0.40980330836826856F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 34, 85);
		this.rightArm1.setRotationPoint(-1.0F, 2.0F, -3.4F);
		this.rightArm1.addBox(-1.0F, 0.0F, -1.0F, 1, 5, 3, 0.0F);
		this.setRotateAngle(rightArm1, 1.1383037381507017F, -0.9105382707654417F, 0.0F);
		this.tail5 = new ResettableModelRenderer(this, 34, 61);
		this.tail5.setRotationPoint(0.0F, -0.2F, 7.7F);
		this.tail5.addBox(-0.5F, -0.5F, 0.0F, 1, 2, 12, 0.0F);
		this.setRotateAngle(tail5, -0.05235987755982988F, 0.0F, 0.0F);
		this.rightArm1.addChild(this.rightWing);
		this.leftLeg3.addChild(this.leftFoot);
		this.rightFoot.addChild(this.rightFootClaw);
		this.bodyMain.addChild(this.chest);
		this.chest.addChild(this.leftArm1);
		this.bodyMain.addChild(this.tail1);
		this.upperjaw.addChild(this.upperjaw_1);
		this.leftLeg1.addChild(this.leftLeg2);
		this.leftFoot.addChild(this.leftFootClaw);
		this.leftArm2.addChild(this.leftFinger1);
		this.tail2.addChild(this.tail3);
		this.neck4.addChild(this.head);
		this.rightArm2.addChild(this.rightFinder2);
		this.neck3.addChild(this.neck4);
		this.leftArm1.addChild(this.leftWing);
		this.rightArm1.addChild(this.rightArm2);
		this.neck1.addChild(this.neck2);
		this.rightLeg2.addChild(this.rightLeg3);
		this.head.addChild(this.upperjaw);
		this.rightLeg1.addChild(this.rightLeg2);
		this.head.addChild(this.teeth);
		this.rightArm2.addChild(this.rightFinder1);
		this.leftFinger1.addChild(this.leftFinger2);
		this.tail1.addChild(this.tail2);
		this.rightLeg3.addChild(this.rightFoot);
		this.chest.addChild(this.neck1);
		this.neck2.addChild(this.neck3);
		this.leftLeg2.addChild(this.leftLeg3);
		this.tail3.addChild(this.tail4);
		this.leftArm1.addChild(this.leftArm2);
		this.chest.addChild(this.rightArm1);
		this.tail4.addChild(this.tail5);

		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.bodyMain.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
		this.leftFootClaw.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.rightFootClaw.saveParameters();
		this.chest.saveParameters();
		this.tail1.saveParameters();
		this.neck1.saveParameters();
		this.rightArm1.saveParameters();
		this.leftArm1.saveParameters();
		this.neck2.saveParameters();
		this.neck3.saveParameters();
		this.neck4.saveParameters();
		this.head.saveParameters();
		this.upperjaw.saveParameters();
		this.teeth.saveParameters();
		this.upperjaw_1.saveParameters();
		this.rightArm2.saveParameters();
		this.rightWing.saveParameters();
		this.rightFinder2.saveParameters();
		this.rightFinder1.saveParameters();
		this.leftArm2.saveParameters();
		this.leftWing.saveParameters();
		this.leftFinger1.saveParameters();
		this.leftFinger2.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.tail5.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityVelociraptor creature = (EntityVelociraptor) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);

		this.rightLeg1.render(scale);
		this.bodyMain.render(scale);
		this.leftLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();
		EntityVelociraptor creature = (EntityVelociraptor) entity;
		float sittingProgress = creature.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.bodyMain.rotationPointY += 6.5F * sittingProgress;

			this.rightArm2.rotateAngleX -= 0.8F * sittingProgress;
			this.rightFinder1.rotateAngleX += 1.4F * sittingProgress;
			this.leftArm2.rotateAngleX -= 0.8F * sittingProgress;
			this.leftFinger1.rotateAngleX += 1.4F * sittingProgress;

			this.rightLeg1.rotationPointY += 7.0F * sittingProgress;
			this.rightLeg1.rotationPointZ += 4.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.2F * sittingProgress;
			this.rightLeg2.rotationPointY += 0.5F * sittingProgress;
			this.rightLeg2.rotationPointZ += 1.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.1F * sittingProgress;
	    	this.rightLeg3.rotationPointY += 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 0.9F * sittingProgress;
			this.rightFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.0F * sittingProgress;

			this.leftLeg1.rotationPointY += 7.0F * sittingProgress;
			this.leftLeg1.rotationPointZ += 4.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.2F * sittingProgress;
			this.leftLeg2.rotationPointY += 0.5F * sittingProgress;
			this.leftLeg2.rotationPointZ += 1.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.1F * sittingProgress;
	    	this.leftLeg3.rotationPointY += 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 0.9F * sittingProgress;
			this.leftFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.0F * sittingProgress;
		}

		float tailBuffer = creature.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
		this.tail5.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityVelociraptor creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);

		if (creature.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.bodyMain.rotateAngleX -= naturalMovement;
			this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
			this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

			float headX = this.getHeadAngle(pitch) / 2.0F;
			float headY = this.getHeadAngle(yaw) / 2.0F;
			this.head.rotateAngleX += 0.4F * headX;
			this.head.rotateAngleY += 0.4F * headY;

			float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 5, 0.046875F, 0.05F, -2.0F);
			this.tail1.rotateAngleX -= naturalTailMovement[0];
			this.tail2.rotateAngleX -= naturalTailMovement[1];
			this.tail3.rotateAngleX -= naturalTailMovement[2];
			this.tail4.rotateAngleX -= naturalTailMovement[3];
			this.tail5.rotateAngleX -= naturalTailMovement[4];
		}
		else
		{
			if (walkSpeed > 0.001F)
			{
				float bodyBob1 = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 0.6F, 0.6F) - 0.6F;
				this.bodyMain.rotationPointX -= bodyBob1;
				this.rightLeg1.rotationPointX -= bodyBob1;
				this.leftLeg1.rotationPointX -= bodyBob1;

				float bodyBob2 = this.getRotateAngle(time, walkSpeed, 0.75F, 1.0F);
				this.bodyMain.rotationPointY -= bodyBob2;
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
					float[] naturalTailMovement = this.getChainMovement(time, walkSpeed, 5, 0.75F, 0.05F, -2.0F);
					this.tail1.rotateAngleX -= naturalTailMovement[0];
					this.tail2.rotateAngleX -= naturalTailMovement[1];
					this.tail3.rotateAngleX -= naturalTailMovement[2];
					this.tail4.rotateAngleX -= naturalTailMovement[3];
					this.tail5.rotateAngleX -= naturalTailMovement[4];

					float tailSwing = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 0.15F, 0.0F);
					this.tail1.rotateAngleY -= tailSwing;
				}

				float runningInclination = MathHelper.sin(walkSpeed);
				if (runningInclination > 0.3F)
				{
					runningInclination -= 0.3F;
					if (runningInclination > 0.6F)
						runningInclination = 0.6F;

					this.bodyMain.rotateAngleX += 0.125F * runningInclination;
				}
			}
			else
			{
				float naturalMovement = this.getAlwaysRotateAngle(time, 0.09375F, 0.03F);
				this.bodyMain.rotateAngleX -= naturalMovement;
				this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
				this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

				float headX = this.getHeadAngle(pitch) / 2.0F;
				float headY = this.getHeadAngle(yaw) / 2.0F;
				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.chest.rotateAngleY += 0.3F * headY;

				float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 5, 0.09375F, 0.05F, -2.0F);
				this.tail1.rotateAngleX -= naturalTailMovement[0];
				this.tail2.rotateAngleX -= naturalTailMovement[1];
				this.tail3.rotateAngleX -= naturalTailMovement[2];
				this.tail4.rotateAngleX -= naturalTailMovement[3];
				this.tail5.rotateAngleX -= naturalTailMovement[4];
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
			this.animator.rotate(this.bodyMain, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.tail1, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail2, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail3, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail4, 0.1F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(3);
			this.animator.move(this.bodyMain, 0.0F, 0.0F, -3.0F);
			this.animator.rotate(this.bodyMain, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.setStationaryPhase(2);
			this.animator.startPhase(2);
			this.animator.rotate(this.bodyMain, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(2);
			this.animator.rotate(this.bodyMain, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.resetPhase(2);
		}
	}

	private void resetPose()
	{
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.bodyMain.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.leftFootClaw.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.rightFootClaw.resetParameters();
		this.chest.resetParameters();
		this.tail1.resetParameters();
		this.neck1.resetParameters();
		this.rightArm1.resetParameters();
		this.leftArm1.resetParameters();
		this.neck2.resetParameters();
		this.neck3.resetParameters();
		this.neck4.resetParameters();
		this.head.resetParameters();
		this.upperjaw.resetParameters();
		this.teeth.resetParameters();
		this.upperjaw_1.resetParameters();
		this.rightArm2.resetParameters();
		this.rightWing.resetParameters();
		this.rightFinder2.resetParameters();
		this.rightFinder1.resetParameters();
		this.leftArm2.resetParameters();
		this.leftWing.resetParameters();
		this.leftFinger1.resetParameters();
		this.leftFinger2.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.tail5.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
