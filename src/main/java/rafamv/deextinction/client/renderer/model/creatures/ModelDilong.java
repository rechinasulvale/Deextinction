package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityDilong;

public class ModelDilong extends ResettableModelBase
{
	public ResettableModelRenderer bodyMain;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer bodyMiddle;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer rightShoulder;
	public ResettableModelRenderer leftShoulder;
	public ResettableModelRenderer bodyFront;
	public ResettableModelRenderer chest;
	public ResettableModelRenderer neck;
	public ResettableModelRenderer shape16;
	public ResettableModelRenderer head;
	public ResettableModelRenderer mouthMiddle;
	public ResettableModelRenderer mouth;
	public ResettableModelRenderer snoutTop2;
	public ResettableModelRenderer snoutTop1;
	public ResettableModelRenderer eyeLeft;
	public ResettableModelRenderer eyeRight;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tail5;
	public ResettableModelRenderer tailFeathers;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightFinger1;
	public ResettableModelRenderer rightFinger2;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftFinger1;
	public ResettableModelRenderer leftFinger2;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	private Animator animator = new Animator();

	public ModelDilong()
	{
		this.textureWidth = 100;
		this.textureHeight = 100;
		this.tail4 = new ResettableModelRenderer(this, 29, 23);
		this.tail4.setRotationPoint(0.0F, -0.2F, 6.9F);
		this.tail4.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(tail4, 0.04363323129985824F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 60, 40);
		this.leftArm1.setRotationPoint(1.6F, 0.9F, 0.2F);
		this.leftArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(leftArm1, 0.45378560551852565F, 0.0F, 0.0F);
		this.rightShoulder = new ResettableModelRenderer(this, 57, 49);
		this.rightShoulder.setRotationPoint(-2.6F, 1.5F, -0.2F);
		this.rightShoulder.addBox(-3.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(rightShoulder, 0.0F, 0.0F, 0.22689280275926282F);
		this.leftShoulder = new ResettableModelRenderer(this, 57, 49);
		this.leftShoulder.setRotationPoint(2.6F, 1.5F, -0.2F);
		this.leftShoulder.addBox(0.0F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(leftShoulder, 0.0F, 0.0F, -0.22689280275926282F);
		this.rightLeg2 = new ResettableModelRenderer(this, 68, 73);
		this.rightLeg2.setRotationPoint(-1.5F, 2.5F, -0.1F);
		this.rightLeg2.addBox(-2.0F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 0.6370451769779303F, 0.0F, 0.0F);
		this.leftFinger1 = new ResettableModelRenderer(this, 57, 15);
		this.leftFinger1.setRotationPoint(0.0F, 4.6F, 0.1F);
		this.leftFinger1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(leftFinger1, 0.0F, 0.0F, 0.3490658503988659F);
		this.rightLeg1 = new ResettableModelRenderer(this, 67, 88);
		this.rightLeg1.setRotationPoint(-3.0F, 9.3F, 3.5F);
		this.rightLeg1.addBox(-4.0F, -3.5F, -2.5F, 4, 7, 5, 0.0F);
		this.mouthMiddle = new ResettableModelRenderer(this, 0, 12);
		this.mouthMiddle.setRotationPoint(0.0F, 0.2F, -4.2F);
		this.mouthMiddle.addBox(-2.0F, -1.0F, -7.0F, 4, 2, 7, 0.0F);
		this.snoutTop2 = new ResettableModelRenderer(this, 20, 1);
		this.snoutTop2.setRotationPoint(0.0F, -0.3F, -7.7F);
		this.snoutTop2.addBox(-1.5F, -1.5F, -3.0F, 3, 2, 3, 0.0F);
		this.setRotateAngle(snoutTop2, 0.3012438288942213F, 0.0F, 0.0F);
		this.snoutTop1 = new ResettableModelRenderer(this, 34, 0);
		this.snoutTop1.setRotationPoint(0.0F, -0.9F, -4.2F);
		this.snoutTop1.addBox(-1.5F, -1.5F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(snoutTop1, 0.18203784098300857F, 0.0F, 0.0F);
		this.leftFinger2 = new ResettableModelRenderer(this, 57, 15);
		this.leftFinger2.setRotationPoint(0.0F, 4.5F, 0.4F);
		this.leftFinger2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(leftFinger2, 0.41015237421866746F, 0.0F, 0.4886921905584123F);
		this.leftLeg2 = new ResettableModelRenderer(this, 68, 73);
		this.leftLeg2.setRotationPoint(2.5F, 2.5F, -0.1F);
		this.leftLeg2.addBox(-2.0F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 0.6370451769779303F, 0.0F, 0.0F);
		this.bodyMain = new ResettableModelRenderer(this, 31, 85);
		this.bodyMain.setRotationPoint(0.0F, 8.2F, 0.0F);
		this.bodyMain.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 7, 0.0F);
		this.setRotateAngle(bodyMain, -0.11344640137963141F, 0.0F, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 62, 28);
		this.rightArm2.setRotationPoint(-0.1F, 2.7F, 0.3F);
		this.rightArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(rightArm2, -1.0471975511965976F, 0.0F, 0.0F);
		this.eyeLeft = new ResettableModelRenderer(this, 19, 39);
		this.eyeLeft.setRotationPoint(3.6F, -0.6F, -3.5F);
		this.eyeLeft.addBox(-3.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(eyeLeft, 0.36425021489121656F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 27, 35);
		this.tail3.setRotationPoint(0.0F, -0.2F, 6.1F);
		this.tail3.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 9, 0.0F);
		this.setRotateAngle(tail3, 0.04363323129985824F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 37, 66);
		this.tail1.setRotationPoint(0.0F, -0.1F, 4.9F);
		this.tail1.addBox(-3.0F, -3.5F, 0.0F, 6, 7, 7, 0.0F);
		this.setRotateAngle(tail1, 0.04363323129985824F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 62, 28);
		this.leftArm2.setRotationPoint(-0.1F, 2.7F, 0.3F);
		this.leftArm2.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(leftArm2, -1.0471975511965976F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 61, 62);
		this.rightFoot.setRotationPoint(0.0F, 6.5F, 1.5F);
		this.rightFoot.addBox(-1.5F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
		this.setRotateAngle(rightFoot, -1.0821041362364843F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 0, 55);
		this.chest.setRotationPoint(0.0F, -1.0F, -3.0F);
		this.chest.addBox(-2.5F, -3.0F, -4.0F, 5, 6, 4, 0.0F);
		this.setRotateAngle(chest, 0.4886921905584123F, 0.0F, 0.0F);
		this.shape16 = new ResettableModelRenderer(this, 0, 0);
		this.shape16.setRotationPoint(0.0F, -0.9F, -7.0F);
		this.shape16.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(shape16, 0.6373942428283291F, 0.0F, 0.0F);
		this.tailFeathers = new ResettableModelRenderer(this, 61, 20);
		this.tailFeathers.setRotationPoint(0.0F, 0.0F, 5.1F);
		this.tailFeathers.addBox(-3.5F, 0.0F, 0.0F, 7, 0, 11, 0.0F);
		this.neck = new ResettableModelRenderer(this, 0, 38);
		this.neck.setRotationPoint(0.0F, -0.7F, -1.4F);
		this.neck.addBox(-2.0F, -2.0F, -8.0F, 4, 4, 8, 0.0F);
		this.setRotateAngle(neck, -1.2217304763960306F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 70, 61);
		this.rightLeg3.setRotationPoint(-0.5F, 6.5F, -0.2F);
		this.rightLeg3.addBox(-1.0F, 0.0F, -0.5F, 2, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg3, -1.1344640137963142F, 0.0F, 0.0F);
		this.rightFinger2 = new ResettableModelRenderer(this, 57, 15);
		this.rightFinger2.setRotationPoint(0.0F, 4.5F, 0.4F);
		this.rightFinger2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(rightFinger2, 0.41015237421866746F, 0.0F, -0.4886921905584123F);
		this.head = new ResettableModelRenderer(this, 0, 24);
		this.head.setRotationPoint(0.0F, 0.2F, 0.2F);
		this.head.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 70, 61);
		this.leftLeg3.setRotationPoint(-0.5F, 6.5F, -0.2F);
		this.leftLeg3.addBox(-1.0F, 0.0F, -0.5F, 2, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg3, -1.1344640137963142F, 0.0F, 0.0F);
		this.mouth = new ResettableModelRenderer(this, 15, 29);
		this.mouth.setRotationPoint(0.0F, 1.5F, -4.0F);
		this.mouth.addBox(-1.5F, -0.5F, -7.0F, 3, 1, 7, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 61, 62);
		this.leftFoot.setRotationPoint(0.0F, 6.5F, 1.5F);
		this.leftFoot.addBox(-1.5F, 0.0F, -0.5F, 3, 4, 1, 0.0F);
		this.setRotateAngle(leftFoot, -1.0821041362364843F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 67, 88);
		this.leftLeg1.setRotationPoint(3.0F, 9.3F, 3.5F);
		this.leftLeg1.addBox(0.0F, -3.5F, -2.5F, 4, 7, 5, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 30, 49);
		this.tail2.setRotationPoint(0.0F, -0.8F, 4.7F);
		this.tail2.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 8, 0.0F);
		this.setRotateAngle(tail2, 0.08726646259971647F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 60, 40);
		this.rightArm1.setRotationPoint(-1.6F, 0.9F, 0.2F);
		this.rightArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(rightArm1, 0.45378560551852565F, 0.0F, 0.0F);
		this.rightFinger1 = new ResettableModelRenderer(this, 57, 15);
		this.rightFinger1.setRotationPoint(0.0F, 4.6F, 0.1F);
		this.rightFinger1.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(rightFinger1, 0.0F, 0.0F, -0.3490658503988659F);
		this.bodyFront = new ResettableModelRenderer(this, 0, 70);
		this.bodyFront.setRotationPoint(0.0F, -0.5F, -4.6F);
		this.bodyFront.addBox(-3.0F, -3.5F, -5.0F, 6, 7, 5, 0.0F);
		this.setRotateAngle(bodyFront, 0.17453292519943295F, 0.0F, 0.0F);
		this.eyeRight = new ResettableModelRenderer(this, 19, 39);
		this.eyeRight.setRotationPoint(0.4F, -0.6F, -3.5F);
		this.eyeRight.addBox(-3.0F, -1.0F, -1.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(eyeRight, 0.36425021489121656F, 0.0F, 0.0F);
		this.tail5 = new ResettableModelRenderer(this, 27, 10);
		this.tail5.setRotationPoint(0.0F, -0.2F, 6.9F);
		this.tail5.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 9, 0.0F);
		this.setRotateAngle(tail5, 0.04363323129985824F, 0.0F, 0.0F);
		this.bodyMiddle = new ResettableModelRenderer(this, 0, 86);
		this.bodyMiddle.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.bodyMiddle.addBox(-3.5F, -4.0F, -6.0F, 7, 8, 6, 0.0F);
		this.setRotateAngle(bodyMiddle, 0.13962634015954636F, 0.0F, 0.0F);
		this.tail3.addChild(this.tail4);
		this.leftShoulder.addChild(this.leftArm1);
		this.bodyFront.addChild(this.rightShoulder);
		this.bodyFront.addChild(this.leftShoulder);
		this.rightLeg1.addChild(this.rightLeg2);
		this.leftArm2.addChild(this.leftFinger1);
		this.head.addChild(this.mouthMiddle);
		this.head.addChild(this.snoutTop2);
		this.head.addChild(this.snoutTop1);
		this.leftArm2.addChild(this.leftFinger2);
		this.leftLeg1.addChild(this.leftLeg2);
		this.rightArm1.addChild(this.rightArm2);
		this.head.addChild(this.eyeLeft);
		this.tail2.addChild(this.tail3);
		this.bodyMain.addChild(this.tail1);
		this.leftArm1.addChild(this.leftArm2);
		this.rightLeg3.addChild(this.rightFoot);
		this.bodyFront.addChild(this.chest);
		this.neck.addChild(this.shape16);
		this.tail5.addChild(this.tailFeathers);
		this.chest.addChild(this.neck);
		this.rightLeg2.addChild(this.rightLeg3);
		this.rightArm2.addChild(this.rightFinger2);
		this.shape16.addChild(this.head);
		this.leftLeg2.addChild(this.leftLeg3);
		this.head.addChild(this.mouth);
		this.leftLeg3.addChild(this.leftFoot);
		this.tail1.addChild(this.tail2);
		this.rightShoulder.addChild(this.rightArm1);
		this.rightArm2.addChild(this.rightFinger1);
		this.bodyMiddle.addChild(this.bodyFront);
		this.head.addChild(this.eyeRight);
		this.tail4.addChild(this.tail5);
		this.bodyMain.addChild(this.bodyMiddle);

		this.bodyMain.saveParameters();
		this.rightLeg1.saveParameters();
		this.leftLeg1.saveParameters();
		this.bodyMiddle.saveParameters();
		this.tail1.saveParameters();
		this.rightShoulder.saveParameters();
		this.leftShoulder.saveParameters();
		this.bodyFront.saveParameters();
		this.chest.saveParameters();
		this.neck.saveParameters();
		this.shape16.saveParameters();
		this.head.saveParameters();
		this.mouthMiddle.saveParameters();
		this.mouth.saveParameters();
		this.snoutTop2.saveParameters();
		this.snoutTop1.saveParameters();
		this.eyeLeft.saveParameters();
		this.eyeRight.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.tail5.saveParameters();
		this.tailFeathers.saveParameters();
		this.rightArm1.saveParameters();
		this.rightArm2.saveParameters();
		this.rightFinger1.saveParameters();
		this.rightFinger2.saveParameters();
		this.leftArm1.saveParameters();
		this.leftArm2.saveParameters();
		this.leftFinger1.saveParameters();
		this.leftFinger2.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityDilong creature = (EntityDilong) entity;
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
		EntityDilong creature = (EntityDilong) entity;
		float sittingProgress = creature.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.bodyMain.rotationPointY += 8.0F * sittingProgress;

			this.rightShoulder.rotationPointZ -= 2.0F * sittingProgress;
			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotateAngleX -= 1.25F * sittingProgress;

			this.leftShoulder.rotationPointZ -= 2.0F * sittingProgress;
			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotateAngleX -= 1.25F * sittingProgress;

			this.rightLeg1.rotationPointY += 8.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.62F * sittingProgress;
			this.rightFoot.rotationPointZ -= 1.4F * sittingProgress;
			this.rightFoot.rotationPointY -= 0.2F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.0F * sittingProgress;

			this.leftLeg1.rotationPointY += 8.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.62F * sittingProgress;
			this.leftFoot.rotationPointZ -= 1.4F * sittingProgress;
			this.leftFoot.rotationPointY -= 0.2F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.0F * sittingProgress;
		}

		float tailBuffer = creature.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
		this.tail5.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityDilong creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);

		if (creature.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.bodyMain.rotateAngleX -= naturalMovement;
			this.bodyFront.rotateAngleX -= naturalMovement;
			this.neck.rotateAngleX -= naturalMovement;
			this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
			this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

			float headX = this.getHeadAngle(pitch) / 2.0F;
			float headY = this.getHeadAngle(yaw) / 2.0F;
			this.head.rotateAngleX += 0.4F * headX;
			this.head.rotateAngleY += 0.4F * headY;
			this.neck.rotateAngleX += 0.25F * headX;
			this.neck.rotateAngleY += 0.5F * headY;
			this.bodyFront.rotateAngleY += 0.3F * headY;
			this.bodyMiddle.rotateAngleY += 0.3F * headY;

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
				this.bodyMiddle.rotateAngleX -= naturalMovement;
				this.neck.rotateAngleX -= naturalMovement;
				this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
				this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

				float headX = this.getHeadAngle(pitch) / 2.0F;
				float headY = this.getHeadAngle(yaw) / 2.0F;
				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.neck.rotateAngleX += 0.25F * headX;
				this.neck.rotateAngleY += 0.5F * headY;
				this.chest.rotateAngleY += 0.3F * headY;
				this.bodyFront.rotateAngleY += 0.3F * headY;

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
			this.animator.rotate(this.bodyFront, -0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.tail1, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail2, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail3, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail4, 0.1F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(3);
			this.animator.move(this.bodyMain, 0.0F, 0.0F, -3.0F);
			this.animator.rotate(this.bodyMain, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.neck, -0.6F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.mouth, 0.6F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.setStationaryPhase(2);
			this.animator.startPhase(2);
			this.animator.rotate(this.bodyMain, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, -0.15F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(2);
			this.animator.rotate(this.bodyMain, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.resetPhase(2);
		}
	}

	private void resetPose()
	{
		this.bodyMain.resetParameters();
		this.rightLeg1.resetParameters();
		this.leftLeg1.resetParameters();
		this.bodyMiddle.resetParameters();
		this.tail1.resetParameters();
		this.rightShoulder.resetParameters();
		this.leftShoulder.resetParameters();
		this.bodyFront.resetParameters();
		this.chest.resetParameters();
		this.neck.resetParameters();
		this.shape16.resetParameters();
		this.head.resetParameters();
		this.mouthMiddle.resetParameters();
		this.mouth.resetParameters();
		this.snoutTop2.resetParameters();
		this.snoutTop1.resetParameters();
		this.eyeLeft.resetParameters();
		this.eyeRight.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.tail5.resetParameters();
		this.tailFeathers.resetParameters();
		this.rightArm1.resetParameters();
		this.rightArm2.resetParameters();
		this.rightFinger1.resetParameters();
		this.rightFinger2.resetParameters();
		this.leftArm1.resetParameters();
		this.leftArm2.resetParameters();
		this.leftFinger1.resetParameters();
		this.leftFinger2.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
