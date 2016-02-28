package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.creature.EntityDinornis;

public class ModelDinornis extends ResettableModelBase
{
	public ResettableModelRenderer BodyMiddle;
	public ResettableModelRenderer HipRight;
	public ResettableModelRenderer HipLeft;
	public ResettableModelRenderer Tail1;
	public ResettableModelRenderer Chest1;
	public ResettableModelRenderer Tail2;
	public ResettableModelRenderer Chest2;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer neck3;
	public ResettableModelRenderer neck4;
	public ResettableModelRenderer Head;
	public ResettableModelRenderer Beak;
	public ResettableModelRenderer UpperUpperLegRight;
	public ResettableModelRenderer UpperLegRight;
	public ResettableModelRenderer KneeRight;
	public ResettableModelRenderer LowerLegRight;
	public ResettableModelRenderer FootBase;
	public ResettableModelRenderer Toe1Right;
	public ResettableModelRenderer Toe2Right;
	public ResettableModelRenderer Toe3Right;
	public ResettableModelRenderer UpperUpperLegLeft;
	public ResettableModelRenderer UpperLegLeft;
	public ResettableModelRenderer KneeLeft;
	public ResettableModelRenderer LowerLegLeft;
	public ResettableModelRenderer FootBase_1;
	public ResettableModelRenderer Toe1Left;
	public ResettableModelRenderer Toe2Left;
	public ResettableModelRenderer Toe3Left;

	public ModelDinornis()
	{
		this.textureWidth = 200;
		this.textureHeight = 200;
		this.UpperUpperLegRight = new ResettableModelRenderer(this, 131, 100);
		this.UpperUpperLegRight.setRotationPoint(-0.5F, -1.2F, -2.1F);
		this.UpperUpperLegRight.addBox(-3.1F, 0.0F, -3.0F, 6, 10, 6, 0.0F);
		this.setRotateAngle(UpperUpperLegRight, 0.0F, 0.029670597283903602F, 0.0F);
		this.UpperLegLeft = new ResettableModelRenderer(this, 134, 118);
		this.UpperLegLeft.setRotationPoint(0.0F, 6.0F, 0.68F);
		this.UpperLegLeft.addBox(-2.6F, 0.1F, -2.7F, 5, 6, 5, 0.0F);
		this.setRotateAngle(UpperLegLeft, -0.14486232791552936F, -0.0017453292519943296F, 0.0F);
		this.Toe1Right = new ResettableModelRenderer(this, 175, 128);
		this.Toe1Right.setRotationPoint(1.6F, 0.8F, 1.2F);
		this.Toe1Right.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.setRotateAngle(Toe1Right, 0.0F, 0.3211405823669566F, 0.0F);
		this.Chest1 = new ResettableModelRenderer(this, 0, 46);
		this.Chest1.setRotationPoint(0.0F, -1.8F, 23.4F);
		this.Chest1.addBox(-10.5F, -7.0F, 0.0F, 21, 21, 15, 0.0F);
		this.setRotateAngle(Chest1, -1.1838568316277536F, 0.0F, 0.0F);
		this.UpperLegRight = new ResettableModelRenderer(this, 134, 118);
		this.UpperLegRight.setRotationPoint(-0.1F, 5.8F, 0.7F);
		this.UpperLegRight.addBox(-2.5F, 0.1F, -2.7F, 5, 6, 5, 0.0F);
		this.setRotateAngle(UpperLegRight, -0.14486232791552936F, 0.010471975511965976F, 0.0F);
		this.KneeRight = new ResettableModelRenderer(this, 134, 130);
		this.KneeRight.setRotationPoint(0.0F, 5.6F, -0.5F);
		this.KneeRight.addBox(-2.5F, -0.1F, -2.3F, 5, 3, 5, 0.0F);
		this.setRotateAngle(KneeRight, 0.13823007675795088F, 0.005235987755982988F, 0.0F);
		this.LowerLegRight = new ResettableModelRenderer(this, 155, 117);
		this.LowerLegRight.setRotationPoint(-0.3F, 2.0F, -1.9F);
		this.LowerLegRight.addBox(-1.9F, -0.1F, 0.0F, 4, 21, 4, 0.0F);
		this.setRotateAngle(LowerLegRight, 0.0F, 0.06806784082777885F, -0.019198621771937624F);
		this.BodyMiddle = new ResettableModelRenderer(this, 0, 0);
		this.BodyMiddle.setRotationPoint(0.0F, -11.6F, -14.5F);
		this.BodyMiddle.addBox(-11.0F, -8.0F, 0.0F, 22, 22, 23, 0.0F);
		this.setRotateAngle(BodyMiddle, 0.38F, 0.0F, 0.0F);
		this.Tail2 = new ResettableModelRenderer(this, 74, 38);
		this.Tail2.setRotationPoint(-0.5F, 5.8F, 8.1F);
		this.Tail2.addBox(-9.0F, -4.0F, -19.0F, 19, 11, 20, 0.0F);
		this.setRotateAngle(Tail2, 0.15184364492350666F, 0.0F, 0.0F);
		this.KneeLeft = new ResettableModelRenderer(this, 134, 130);
		this.KneeLeft.setRotationPoint(-0.1F, 5.6F, -0.4F);
		this.KneeLeft.addBox(-2.4F, -0.1F, -2.3F, 5, 3, 5, 0.0F);
		this.setRotateAngle(KneeLeft, 0.15184364492350666F, 0.010471975511965976F, 0.0F);
		this.FootBase = new ResettableModelRenderer(this, 172, 118);
		this.FootBase.setRotationPoint(-0.05F, 20.0F, 1.7F);
		this.FootBase.addBox(-3.5F, -0.1F, -2.0F, 7, 2, 5, 0.0F);
		this.setRotateAngle(FootBase, 0.0F, 0.013962634015954637F, 0.0F);
		this.Tail1 = new ResettableModelRenderer(this, 91, 1);
		this.Tail1.setRotationPoint(-2.5F, -1.0F, 4.1F);
		this.Tail1.addBox(-7.5F, -5.0F, -1.0F, 20, 13, 22, 0.0F);
		this.setRotateAngle(Tail1, -2.5071654704898547F, 0.0F, 0.0F);
		this.Chest2 = new ResettableModelRenderer(this, 0, 83);
		this.Chest2.setRotationPoint(1.5F, 3.69F, 32.9F);
		this.Chest2.addBox(-12.3F, -5.0F, -6.0F, 22, 19, 10, 0.0F);
		this.setRotateAngle(Chest2, -2.0207422079590347F, 0.0F, 0.0F);
		this.FootBase_1 = new ResettableModelRenderer(this, 172, 118);
		this.FootBase_1.setRotationPoint(0.25F, 20.0F, 1.6F);
		this.FootBase_1.addBox(-3.5F, -0.1F, -2.0F, 7, 2, 5, 0.0F);
		this.setRotateAngle(FootBase_1, 0.0F, -0.005235987755982988F, 0.0F);
		this.Toe1Left = new ResettableModelRenderer(this, 175, 128);
		this.Toe1Left.setRotationPoint(1.6F, 0.8F, 1.2F);
		this.Toe1Left.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.setRotateAngle(Toe1Left, 0.0F, 0.3211405823669566F, 0.0F);
		this.neck3 = new ResettableModelRenderer(this, 1, 138);
		this.neck3.setRotationPoint(-2.5F, -12.3F, -1.0F);
		this.neck3.addBox(-3.6F, -16.0F, -4.0F, 7, 16, 6, 0.0F);
		this.setRotateAngle(neck3, -0.7740535232594852F, 0.03490658503988659F, 0.0F);
		this.Head = new ResettableModelRenderer(this, 66, 94);
		this.Head.setRotationPoint(0.1F, -8.9F, 0.4F);
		this.Head.addBox(-3.0F, -11.0F, -2.7F, 6, 11, 6, 0.0F);
		this.setRotateAngle(Head, -0.7285004297824331F, 0.0F, 0.0F);
		this.LowerLegLeft = new ResettableModelRenderer(this, 155, 117);
		this.LowerLegLeft.setRotationPoint(0.2F, 1.9F, -1.8F);
		this.LowerLegLeft.addBox(-1.9F, -0.1F, 0.0F, 4, 21, 4, 0.0F);
		this.setRotateAngle(LowerLegLeft, 0.0F, -0.06806784082777885F, 0.0F);
		this.Toe3Right = new ResettableModelRenderer(this, 175, 128);
		this.Toe3Right.setRotationPoint(-1.6F, 0.8F, 1.2F);
		this.Toe3Right.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.setRotateAngle(Toe3Right, 0.0F, -0.3211405823669566F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 1, 113);
		this.neck1.setRotationPoint(-0.9F, 2.0F, 0.0F);
		this.neck1.addBox(-5.9F, -15.0F, -5.0F, 11, 15, 9, 0.0F);
		this.setRotateAngle(neck1, 1.4570008595648662F, 0.0F, 0.0F);
		this.Toe2Right = new ResettableModelRenderer(this, 175, 128);
		this.Toe2Right.setRotationPoint(0.0F, 0.8F, 2.3F);
		this.Toe2Right.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 42, 113);
		this.neck2.setRotationPoint(2.0F, -13.0F, 0.4F);
		this.neck2.addBox(-6.6F, -16.0F, -4.0F, 8, 17, 7, 0.0F);
		this.setRotateAngle(neck2, 0.6373942428283291F, 0.0F, 0.0F);
		this.Beak = new ResettableModelRenderer(this, 76, 97);
		this.Beak.setRotationPoint(0.4F, 3.0F, -2.0F);
		this.Beak.addBox(-0.8F, -2.0F, 5.0F, 1, 3, 16, 0.0F);
		this.setRotateAngle(Beak, 1.2863076587198208F, 0.0F, 0.0F);
		this.Toe2Left = new ResettableModelRenderer(this, 175, 128);
		this.Toe2Left.setRotationPoint(0.0F, 0.8F, 2.3F);
		this.Toe2Left.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.HipLeft = new ResettableModelRenderer(this, 156, 92);
		this.HipLeft.setRotationPoint(-11.5F, -10.2F, -1.0F);
		this.HipLeft.addBox(-4.1F, -5.0F, -3.0F, 9, 12, 12, 0.0F);
		this.neck4 = new ResettableModelRenderer(this, 28, 143);
		this.neck4.setRotationPoint(-0.1F, -14.8F, -1.9F);
		this.neck4.addBox(-2.9F, -10.3F, -2.0F, 6, 11, 6, 0.0F);
		this.setRotateAngle(neck4, -1.0016444577195458F, 0.0F, 0.0F);
		this.HipRight = new ResettableModelRenderer(this, 156, 92);
		this.HipRight.setRotationPoint(10.7F, -10.1F, -1.0F);
		this.HipRight.addBox(-4.1F, -5.0F, -3.0F, 9, 12, 12, 0.0F);
		this.UpperUpperLegLeft = new ResettableModelRenderer(this, 131, 100);
		this.UpperUpperLegLeft.setRotationPoint(1.5F, -1.2F, -2.1F);
		this.UpperUpperLegLeft.addBox(-3.1F, 0.0F, -3.0F, 6, 10, 6, 0.0F);
		this.setRotateAngle(UpperUpperLegLeft, 0.0F, -0.029670597283903602F, 0.0F);
		this.Toe3Left = new ResettableModelRenderer(this, 175, 128);
		this.Toe3Left.setRotationPoint(-1.6F, 0.8F, 1.2F);
		this.Toe3Left.addBox(-1.0F, -0.8F, 0.0F, 2, 2, 9, 0.0F);
		this.setRotateAngle(Toe3Left, 0.0F, -0.3211405823669566F, 0.0F);
		this.HipRight.addChild(this.UpperUpperLegRight);
		this.UpperUpperLegLeft.addChild(this.UpperLegLeft);
		this.FootBase.addChild(this.Toe1Right);
		this.BodyMiddle.addChild(this.Chest1);
		this.UpperUpperLegRight.addChild(this.UpperLegRight);
		this.UpperLegRight.addChild(this.KneeRight);
		this.KneeRight.addChild(this.LowerLegRight);
		this.BodyMiddle.addChild(this.Tail2);
		this.UpperLegLeft.addChild(this.KneeLeft);
		this.LowerLegRight.addChild(this.FootBase);
		this.BodyMiddle.addChild(this.Tail1);
		this.BodyMiddle.addChild(this.Chest2);
		this.LowerLegLeft.addChild(this.FootBase_1);
		this.FootBase_1.addChild(this.Toe1Left);
		this.neck2.addChild(this.neck3);
		this.neck4.addChild(this.Head);
		this.KneeLeft.addChild(this.LowerLegLeft);
		this.FootBase.addChild(this.Toe3Right);
		this.Chest2.addChild(this.neck1);
		this.FootBase.addChild(this.Toe2Right);
		this.neck1.addChild(this.neck2);
		this.Head.addChild(this.Beak);
		this.FootBase_1.addChild(this.Toe2Left);
		this.neck3.addChild(this.neck4);
		this.HipLeft.addChild(this.UpperUpperLegLeft);
		this.FootBase_1.addChild(this.Toe3Left);

		this.BodyMiddle.saveParameters();
		this.HipRight.saveParameters();
		this.HipLeft.saveParameters();
		this.Tail1.saveParameters();
		this.Chest1.saveParameters();
		this.Tail2.saveParameters();
		this.Chest2.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.neck3.saveParameters();
		this.neck4.saveParameters();
		this.Head.saveParameters();
		this.Beak.saveParameters();
		this.UpperUpperLegRight.saveParameters();
		this.UpperLegRight.saveParameters();
		this.KneeRight.saveParameters();
		this.LowerLegRight.saveParameters();
		this.FootBase.saveParameters();
		this.Toe1Right.saveParameters();
		this.Toe2Right.saveParameters();
		this.Toe3Right.saveParameters();
		this.UpperUpperLegLeft.saveParameters();
		this.UpperLegLeft.saveParameters();
		this.KneeLeft.saveParameters();
		this.LowerLegLeft.saveParameters();
		this.FootBase_1.saveParameters();
		this.Toe1Left.saveParameters();
		this.Toe2Left.saveParameters();
		this.Toe3Left.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, (EntityDinornis) entity);

		this.HipLeft.render(scale);
		this.BodyMiddle.render(scale);
		this.HipRight.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();

		EntityDinornis moa = (EntityDinornis) entity;
		float sittingProgress = moa.getSittingProgress(partialRenderTicks);

		if (sittingProgress > 0.01F)
		{
			this.BodyMiddle.rotationPointY += 18.0F * sittingProgress;
			this.BodyMiddle.rotateAngleX -= 0.4F * sittingProgress;
			this.neck1.rotateAngleX += 0.4F * sittingProgress;

			this.HipRight.rotationPointY += 20.0F * sittingProgress;
			this.LowerLegRight.rotationPointY -= 20.0F * sittingProgress;
			this.LowerLegRight.rotationPointX -= 2.0F * sittingProgress;

			this.HipLeft.rotationPointY += 20.0F * sittingProgress;
			this.LowerLegLeft.rotationPointY -= 20.0F * sittingProgress;
			this.LowerLegLeft.rotationPointX += 2.0F * sittingProgress;
		}
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityDinornis moa)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, moa);

		float headX = this.getHeadAngle(pitch) / 2.0F;
		float headY = this.getHeadAngle(yaw) / 2.0F;

		if (moa.isSitting())
		{
			this.neck4.rotateAngleY += headY;
			this.neck3.rotateAngleY += headY / 4.0F;
			this.neck2.rotateAngleY += headY / 8.0F;
			this.neck4.rotateAngleX -= headX;
			this.neck3.rotateAngleX -= headX / 4.0F;
			this.neck2.rotateAngleX -= headX / 8.0F;
		}
		else
		{
			float legX1 = this.getRotateAngle(time, walkSpeed, 0.4F, 1.0F);
			this.HipRight.rotateAngleX -= legX1;
			this.HipLeft.rotateAngleX += legX1;

			float runningInclination = MathHelper.sin(walkSpeed);
			if (runningInclination > 0.001F)
			{
				if (runningInclination > 0.1F)
					runningInclination = 0.1F;

				float runningPose = this.getAlwaysRotateAngle(0.4F * time, 1.0F, 0.5F);
				this.BodyMiddle.rotateAngleX -= 4.0F * runningInclination;
				this.neck1.rotateAngleX -= 8.0F * runningInclination;
				this.neck2.rotateAngleX += 12.0F * runningInclination;
				this.neck2.rotationPointY += 12.0F * runningInclination;
				this.neck2.rotationPointZ -= 30.0F * runningInclination;

				this.neck1.rotateAngleX -= 0.5F * runningPose;
				this.neck2.rotateAngleX += 1.0F * runningPose - runningInclination;
				this.neck3.rotateAngleX += 0.5F * runningPose;
				this.neck4.rotateAngleX -= 0.5F * runningPose;
				this.neck4.rotateAngleX -= 0.5F * runningPose;
				this.Head.rotateAngleX -= 0.25F * runningPose;

				this.neck4.rotateAngleZ += headY / 1.5F;
				this.neck3.rotateAngleZ += headY / 3.0F;
				this.neck2.rotateAngleZ += headY / 4.5F;
				this.neck1.rotateAngleZ += headY / 6.0F;

				float bodyBob = this.getRotateAngle(time, walkSpeed, 0.8F, 5.0F);
				this.BodyMiddle.rotationPointY -= bodyBob;
			}
			else
			{
				float neck1 = this.getAlwaysRotateAngle(time, 0.02F, 0.1F);
				this.BodyMiddle.rotateAngleX -= neck1;
				this.neck1.rotateAngleX += neck1;
				this.neck2.rotateAngleX += neck1;
				this.neck3.rotateAngleX -= 2.0F * neck1;
				this.neck4.rotateAngleX += neck1;

				this.neck1.rotateAngleZ += 0.5F * headY;
				this.neck4.rotateAngleY += headY;
				this.neck3.rotateAngleY += 0.25F * headY;
				this.neck2.rotateAngleY += 0.125F * headY;
				this.neck4.rotateAngleX -= headX;
				this.neck3.rotateAngleX -= 0.25F * headX;
				this.neck2.rotateAngleX -= 0.125F * headX;
			}
		}
	}

	private void resetPose()
	{
		this.BodyMiddle.resetParameters();
		this.HipRight.resetParameters();
		this.HipLeft.resetParameters();
		this.Tail1.resetParameters();
		this.Chest1.resetParameters();
		this.Tail2.resetParameters();
		this.Chest2.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.neck3.resetParameters();
		this.neck4.resetParameters();
		this.Head.resetParameters();
		this.Beak.resetParameters();
		this.UpperUpperLegRight.resetParameters();
		this.UpperLegRight.resetParameters();
		this.KneeRight.resetParameters();
		this.LowerLegRight.resetParameters();
		this.FootBase.resetParameters();
		this.Toe1Right.resetParameters();
		this.Toe2Right.resetParameters();
		this.Toe3Right.resetParameters();
		this.UpperUpperLegLeft.resetParameters();
		this.UpperLegLeft.resetParameters();
		this.KneeLeft.resetParameters();
		this.LowerLegLeft.resetParameters();
		this.FootBase_1.resetParameters();
		this.Toe1Left.resetParameters();
		this.Toe2Left.resetParameters();
		this.Toe3Left.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
