package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityCitipati;

public class ModelCitipati extends ResettableModelBase
{
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer neck;
	public ResettableModelRenderer head;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer beak;
	public ResettableModelRenderer mouth;
	public ResettableModelRenderer crest;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tailFeather;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightHand;
	public ResettableModelRenderer rightArmFeather;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftHand;
	public ResettableModelRenderer leftArmFeather;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	private Animator animator = new Animator();

	public ModelCitipati()
	{
		this.textureWidth = 256;
		this.textureHeight = 256;
		this.leftArm1 = new ResettableModelRenderer(this, 41, 0);
		this.leftArm1.setRotationPoint(3.5F, 0.7F, -4.3F);
		this.leftArm1.addBox(0.0F, -1.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(leftArm1, 0.3490658503988659F, -0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 62, 27);
		this.rightLeg2.setRotationPoint(-0.8F, 2.1F, 0.9F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 6, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 0.8196066167365371F, -0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 0, 47);
		this.head.setRotationPoint(0.0F, -6.1F, -8.1F);
		this.head.addBox(-2.5F, -2.2F, -6.0F, 5, 5, 6, 0.0F);
		this.setRotateAngle(head, 0.091106186954104F, -0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 0, 89);
		this.tail2.setRotationPoint(0.0F, -1.9F, 5.5F);
		this.tail2.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 8, 0.0F);
		this.setRotateAngle(tail2, -0.18203784098300857F, -0.0F, 0.0F);
		this.beak = new ResettableModelRenderer(this, 14, 38);
		this.beak.setRotationPoint(0.0F, -0.4F, -4.5F);
		this.beak.addBox(-1.5F, -1.0F, -2.0F, 3, 4, 2, 0.0F);
		this.setRotateAngle(beak, -0.41887902047863906F, -0.0F, 0.0F);
		this.tailFeather = new ResettableModelRenderer(this, 0, 79);
		this.tailFeather.setRotationPoint(0.0F, 0.6F, 7.0F);
		this.tailFeather.addBox(-3.0F, 0.0F, -1.0F, 6, 0, 7, 0.0F);
		this.setRotateAngle(tailFeather, -0.27314402793711257F, -0.0F, 0.0F);
		this.leftHand = new ResettableModelRenderer(this, 39, 20);
		this.leftHand.setRotationPoint(0.0F, 3.9F, 0.3F);
		this.leftHand.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(leftHand, -0.3839724354387525F, -0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 80, 50);
		this.leftFoot.mirror = true;
		this.leftFoot.setRotationPoint(0.0F, 6.3F, 0.3F);
		this.leftFoot.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(leftFoot, 0.45029494701453704F, -0.0F, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 64, 14);
		this.rightLeg1.setRotationPoint(-3.5F, 10.6F, 2.4F);
		this.rightLeg1.addBox(-2.0F, -3.0F, -2.0F, 2, 6, 5, 0.0F);
		this.setRotateAngle(rightLeg1, -0.3490658503988659F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 80, 27);
		this.leftLeg2.mirror = true;
		this.leftLeg2.setRotationPoint(0.8F, 2.1F, 0.9F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 6, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 0.8196066167365371F, -0.0F, 0.0F);
		this.neck = new ResettableModelRenderer(this, 0, 61);
		this.neck.setRotationPoint(0.0F, 2.1F, -4.2F);
		this.neck.addBox(-2.0F, -2.0F, -11.0F, 4, 4, 11, 0.0F);
		this.setRotateAngle(neck, -1.0016444577195458F, -0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 31, 0);
		this.rightArm1.setRotationPoint(-3.5F, 0.7F, -4.3F);
		this.rightArm1.addBox(-2.0F, -1.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(rightArm1, 0.3490658503988659F, -0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 43, 11);
		this.leftArm2.setRotationPoint(1.0F, 0.1F, 0.4F);
		this.leftArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(leftArm2, -0.7504915783575618F, -0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 63, 39);
		this.rightLeg3.setRotationPoint(0.0F, 4.8F, 0.0F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg3, -0.9105382707654417F, -0.0F, 0.0F);
		this.mouth = new ResettableModelRenderer(this, 0, 29);
		this.mouth.setRotationPoint(0.0F, 2.6F, -4.5F);
		this.mouth.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 1, 0.0F);
		this.setRotateAngle(mouth, -1.6755160819145563F, -0.0F, 0.0F);
		this.leftArmFeather = new ResettableModelRenderer(this, 69, 0);
		this.leftArmFeather.setRotationPoint(-0.4F, 2.1F, 0.3F);
		this.leftArmFeather.addBox(0.0F, 0.0F, -2.0F, 3, 0, 4, 0.0F);
		this.setRotateAngle(leftArmFeather, 3.141592653589793F, -1.2747884856566583F, 1.5707963267948966F);
		this.leftLeg3 = new ResettableModelRenderer(this, 79, 38);
		this.leftLeg3.mirror = true;
		this.leftLeg3.setRotationPoint(0.0F, 4.8F, 0.0F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg3, -0.9105382707654417F, -0.0F, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 33, 10);
		this.rightArm2.setRotationPoint(-1.0F, 0.1F, 0.4F);
		this.rightArm2.addBox(-0.5F, 0.0F, -1.0F, 1, 5, 2, 0.0F);
		this.setRotateAngle(rightArm2, -0.7504915783575618F, -0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 82, 14);
		this.leftLeg1.mirror = true;
		this.leftLeg1.setRotationPoint(3.6F, 10.6F, 2.4F);
		this.leftLeg1.addBox(0.0F, -3.0F, -2.0F, 2, 6, 5, 0.0F);
		this.setRotateAngle(leftLeg1, -0.3490658503988659F, 0.0F, 0.0F);
		this.rightHand = new ResettableModelRenderer(this, 25, 19);
		this.rightHand.setRotationPoint(0.0F, 3.9F, -0.3F);
		this.rightHand.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(rightHand, -0.3839724354387525F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 0, 106);
		this.tail1.setRotationPoint(0.0F, -1.4F, 5.5F);
		this.tail1.addBox(-2.4F, -2.5F, 0.0F, 5, 5, 6, 0.0F);
		this.setRotateAngle(tail1, -0.17453292519943295F, -0.0F, 0.0F);
		this.crest = new ResettableModelRenderer(this, 0, 37);
		this.crest.setRotationPoint(-0.2F, -1.1F, -4.1F);
		this.crest.addBox(-0.5F, -2.0F, 0.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(crest, 1.2566370614359172F, -0.0F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 0, 121);
		this.mainBody.setRotationPoint(0.0F, 9.8F, 0.4F);
		this.mainBody.addBox(-3.5F, -4.0F, -6.0F, 7, 8, 12, 0.0F);
		this.setRotateAngle(mainBody, -0.10471975511965977F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 63, 50);
		this.rightFoot.setRotationPoint(0.0F, 6.3F, 0.3F);
		this.rightFoot.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(rightFoot, 0.45029494701453704F, -0.0F, 0.0F);
		this.rightArmFeather = new ResettableModelRenderer(this, 53, 0);
		this.rightArmFeather.setRotationPoint(-0.4F, 2.1F, 0.3F);
		this.rightArmFeather.addBox(0.0F, 0.0F, -2.0F, 3, 0, 4, 0.0F);
		this.setRotateAngle(rightArmFeather, 3.141592653589793F, -1.2747884856566583F, 1.5707963267948966F);
		this.mainBody.addChild(this.leftArm1);
		this.rightLeg1.addChild(this.rightLeg2);
		this.mainBody.addChild(this.head);
		this.tail1.addChild(this.tail2);
		this.head.addChild(this.beak);
		this.tail2.addChild(this.tailFeather);
		this.leftArm2.addChild(this.leftHand);
		this.leftLeg3.addChild(this.leftFoot);
		this.leftLeg1.addChild(this.leftLeg2);
		this.mainBody.addChild(this.neck);
		this.mainBody.addChild(this.rightArm1);
		this.leftArm1.addChild(this.leftArm2);
		this.rightLeg2.addChild(this.rightLeg3);
		this.head.addChild(this.mouth);
		this.leftArm2.addChild(this.leftArmFeather);
		this.leftLeg2.addChild(this.leftLeg3);
		this.rightArm1.addChild(this.rightArm2);
		this.rightArm2.addChild(this.rightHand);
		this.mainBody.addChild(this.tail1);
		this.head.addChild(this.crest);
		this.rightLeg3.addChild(this.rightFoot);
		this.rightArm2.addChild(this.rightArmFeather);

		this.mainBody.saveParameters();
		this.rightLeg1.saveParameters();
		this.leftLeg1.saveParameters();
		this.neck.saveParameters();
		this.head.saveParameters();
		this.tail1.saveParameters();
		this.rightArm1.saveParameters();
		this.leftArm1.saveParameters();
		this.beak.saveParameters();
		this.mouth.saveParameters();
		this.crest.saveParameters();
		this.tail2.saveParameters();
		this.tailFeather.saveParameters();
		this.rightArm2.saveParameters();
		this.rightHand.saveParameters();
		this.rightArmFeather.saveParameters();
		this.leftArm2.saveParameters();
		this.leftHand.saveParameters();
		this.leftArmFeather.saveParameters();
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
		EntityCitipati creature = (EntityCitipati) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);

		this.rightLeg1.render(scale);
		this.leftLeg1.render(scale);
		this.mainBody.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();
		EntityCitipati creature = (EntityCitipati) entity;
		float sittingProgress = creature.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 8.0F * sittingProgress;

			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotateAngleX -= 1.0F * sittingProgress;

			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotateAngleX -= 1.0F * sittingProgress;

			this.rightLeg1.rotationPointY += 8.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.65F * sittingProgress;
			this.rightFoot.rotationPointZ -= 0.65F * sittingProgress;
			this.rightFoot.rotationPointY -= 0.2F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.0F * sittingProgress;

			this.leftLeg1.rotationPointY += 8.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.65F * sittingProgress;
			this.leftFoot.rotationPointZ -= 0.65F * sittingProgress;
			this.leftFoot.rotationPointY -= 0.2F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.0F * sittingProgress;
		}

		float tailBuffer = creature.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityCitipati creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);

		if (creature.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.mainBody.rotateAngleX -= naturalMovement;
			this.neck.rotateAngleX -= 0.5F * naturalMovement;
			this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
			this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

			float headX = this.getHeadAngle(pitch) / 2.0F;
			float headY = this.getHeadAngle(yaw) / 2.0F;
			this.head.rotateAngleX += 0.4F * headX;
			this.head.rotateAngleY += 0.4F * headY;
			this.neck.rotateAngleX += 0.15F * headX;
			this.neck.rotateAngleY += 0.3F * headY;

			float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 2, 0.046875F, 0.05F, -2.0F);
			this.tail1.rotateAngleX -= naturalTailMovement[0];
			this.tail2.rotateAngleX -= naturalTailMovement[1];
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
					float[] naturalTailMovement = this.getChainMovement(time, walkSpeed, 2, 0.75F, 0.05F, -2.0F);
					this.tail1.rotateAngleX -= naturalTailMovement[0];
					this.tail2.rotateAngleX -= naturalTailMovement[1];

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
				this.neck.rotateAngleX -= 0.5F * naturalMovement;
				this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
				this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

				float headX = this.getHeadAngle(pitch) / 2.0F;
				float headY = this.getHeadAngle(yaw) / 2.0F;
				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.neck.rotateAngleX += 0.15F * headX;
				this.neck.rotateAngleY += 0.3F * headY;

				float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 2, 0.09375F, 0.05F, -2.0F);
				this.tail1.rotateAngleX -= naturalTailMovement[0];
				this.tail2.rotateAngleX -= naturalTailMovement[1];
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
			this.animator.rotate(this.crest, -0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.tail1, 0.15F, 0.0F, 0.0F);
			this.animator.rotate(this.tail2, 0.15F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(3);
			this.animator.rotate(this.crest, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.neck, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.mouth, 0.6F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.setStationaryPhase(2);
			this.animator.startPhase(2);
			this.animator.rotate(this.crest, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(2);
			this.animator.rotate(this.crest, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.resetPhase(2);
		}
	}

	private void resetPose()
	{
		this.mainBody.resetParameters();
		this.rightLeg1.resetParameters();
		this.leftLeg1.resetParameters();
		this.neck.resetParameters();
		this.head.resetParameters();
		this.tail1.resetParameters();
		this.rightArm1.resetParameters();
		this.leftArm1.resetParameters();
		this.beak.resetParameters();
		this.mouth.resetParameters();
		this.crest.resetParameters();
		this.tail2.resetParameters();
		this.tailFeather.resetParameters();
		this.rightArm2.resetParameters();
		this.rightHand.resetParameters();
		this.rightArmFeather.resetParameters();
		this.leftArm2.resetParameters();
		this.leftHand.resetParameters();
		this.leftArmFeather.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
