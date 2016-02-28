package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.creature.EntityRaphus;

@SideOnly(Side.CLIENT)
public class ModelRaphus extends ResettableModelBase
{
	public ResettableModelRenderer body1;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer body2;
	public ResettableModelRenderer bodyBack;
	public ResettableModelRenderer leftWing1;
	public ResettableModelRenderer rightWing1;
	public ResettableModelRenderer body3;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer neck3;
	public ResettableModelRenderer head;
	public ResettableModelRenderer upperJaw1;
	public ResettableModelRenderer lowerJaw1;
	public ResettableModelRenderer upperJawTop;
	public ResettableModelRenderer upperJaw2;
	public ResettableModelRenderer upperJaw3;
	public ResettableModelRenderer lowerJaw2;
	public ResettableModelRenderer lowerJaw3;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer leftWing2;
	public ResettableModelRenderer rightWing2;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftToeMiddle;
	public ResettableModelRenderer leftToeRight;
	public ResettableModelRenderer leftToeLeft;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightToeMiddle;
	public ResettableModelRenderer rightToeRight;
	public ResettableModelRenderer rightToeLeft;

	public ModelRaphus()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		this.body1 = new ResettableModelRenderer(this, 32, 47);
		this.body1.setRotationPoint(0.0F, 14.0F, 1.0F);
		this.body1.addBox(-3.5F, -3.6F, -4.0F, 7, 8, 9, 0.0F);
		this.setRotateAngle(body1, -0.08726646259971647F, 0.0F, 0.0F);
		this.lowerJaw2 = new ResettableModelRenderer(this, 2, 33);
		this.lowerJaw2.setRotationPoint(0.0F, 0.05F, -2.0F);
		this.lowerJaw2.addBox(-0.5F, 0.0F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(lowerJaw2, -0.04363323129985824F, 0.0F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 58, 0);
		this.leftLeg3.setRotationPoint(0.0F, 3.0F, 0.6F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(leftLeg3, -0.9599310885968813F, 0.0F, 0.0F);
		this.bodyBack = new ResettableModelRenderer(this, 44, 37);
		this.bodyBack.setRotationPoint(0.0F, -0.75F, 4.0F);
		this.bodyBack.addBox(-3.0F, -3.0F, -1.0F, 6, 6, 4, 0.0F);
		this.setRotateAngle(bodyBack, 0.3490658503988659F, 0.0F, 0.0F);
		this.upperJaw2 = new ResettableModelRenderer(this, 3, 13);
		this.upperJaw2.setRotationPoint(0.0F, 0.0F, -1.5F);
		this.upperJaw2.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(upperJaw2, -0.04363323129985824F, 0.0F, 0.0F);
		this.leftToeRight = new ResettableModelRenderer(this, 54, 9);
		this.leftToeRight.setRotationPoint(-0.1F, 0.0F, 0.0F);
		this.leftToeRight.addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(leftToeRight, 0.0F, 0.5235987755982988F, 0.0F);
		this.body2 = new ResettableModelRenderer(this, 18, 42);
		this.body2.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.body2.addBox(-3.0F, -2.5F, -5.0F, 6, 8, 5, 0.0F);
		this.setRotateAngle(body2, -0.2617993877991494F, 0.0F, 0.0F);
		this.leftToeLeft = new ResettableModelRenderer(this, 54, 9);
		this.leftToeLeft.setRotationPoint(0.1F, 0.0F, 0.0F);
		this.leftToeLeft.addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(leftToeLeft, 0.0F, -0.5235987755982988F, 0.0F);
		this.rightWing2 = new ResettableModelRenderer(this, 0, 46);
		this.rightWing2.mirror = true;
		this.rightWing2.setRotationPoint(-0.25F, 3.0F, 0.0F);
		this.rightWing2.addBox(-0.45F, -1.0F, -1.0F, 1, 3, 5, 0.0F);
		this.setRotateAngle(rightWing2, 0.0F, 0.0F, -0.17453292519943295F);
		this.upperJaw3 = new ResettableModelRenderer(this, 3, 18);
		this.upperJaw3.setRotationPoint(0.0F, -0.0F, -3.0F);
		this.upperJaw3.addBox(-1.0F, -0.85F, -1.65F, 2, 2, 3, 0.0F);
		this.setRotateAngle(upperJaw3, 0.7853981633974483F, 0.0F, 0.0F);
		this.rightToeLeft = new ResettableModelRenderer(this, 54, 9);
		this.rightToeLeft.mirror = true;
		this.rightToeLeft.setRotationPoint(0.1F, 0.0F, 0.0F);
		this.rightToeLeft.addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(rightToeLeft, 0.0F, -0.5235987755982988F, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 19, 21);
		this.neck1.setRotationPoint(0.0F, 3.75F, -2.0F);
		this.neck1.addBox(-2.0F, -2.0F, -7.5F, 4, 4, 7, 0.0F);
		this.setRotateAngle(neck1, -0.8726646259971648F, 0.0F, 0.0F);
		this.upperJaw1 = new ResettableModelRenderer(this, 2, 8);
		this.upperJaw1.setRotationPoint(0.0F, 0.0F, -4.5F);
		this.upperJaw1.addBox(-1.5F, -1.0F, -2.0F, 3, 2, 3, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 40, 0);
		this.rightLeg1.mirror = true;
		this.rightLeg1.setRotationPoint(-3.0F, 14.5F, 2.75F);
		this.rightLeg1.addBox(-1.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
		this.setRotateAngle(rightLeg1, -0.5235987755982988F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 48, 30);
		this.tail1.setRotationPoint(0.0F, -1.25F, 0.0F);
		this.tail1.addBox(-2.0F, -1.0F, 0.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(tail1, 0.3490658503988659F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 47, 20);
		this.tail3.setRotationPoint(0.0F, -0.25F, 4.0F);
		this.tail3.addBox(-3.0F, 0.0F, -0.5F, 6, 0, 5, 0.0F);
		this.setRotateAngle(tail3, -1.0471975511965976F, 0.0F, 0.0F);
		this.leftToeMiddle = new ResettableModelRenderer(this, 52, 13);
		this.leftToeMiddle.setRotationPoint(0.0F, 4.2F, -0.2F);
		this.leftToeMiddle.addBox(-0.5F, 0.0F, -3.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(leftToeMiddle, 0.4363323129985824F, 0.0F, 0.0F);
		this.rightToeRight = new ResettableModelRenderer(this, 54, 9);
		this.rightToeRight.mirror = true;
		this.rightToeRight.setRotationPoint(-0.1F, 0.0F, 0.0F);
		this.rightToeRight.addBox(-0.5F, 0.0F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(rightToeRight, 0.0F, 0.5235987755982988F, 0.0F);
		this.rightWing1 = new ResettableModelRenderer(this, 0, 39);
		this.rightWing1.mirror = true;
		this.rightWing1.setRotationPoint(-3.5F, -2.5F, -2.0F);
		this.rightWing1.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 3, 0.0F);
		this.setRotateAngle(rightWing1, -0.5235987755982988F, 0.0F, 0.17453292519943295F);
		this.rightLeg3 = new ResettableModelRenderer(this, 58, 0);
		this.rightLeg3.mirror = true;
		this.rightLeg3.setRotationPoint(0.0F, 3.0F, 0.6F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(rightLeg3, -0.9599310885968813F, 0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 43, 11);
		this.rightLeg2.mirror = true;
		this.rightLeg2.setRotationPoint(0.25F, 3.0F, 0.0F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 1.0471975511965976F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 43, 11);
		this.leftLeg2.setRotationPoint(0.75F, 3.0F, 0.0F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 4, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 1.0471975511965976F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -0.5F, -1.0F);
		this.head.addBox(-2.0F, -2.5F, -4.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(head, 0.5235987755982988F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 40, 0);
		this.leftLeg1.setRotationPoint(2.5F, 14.5F, 2.75F);
		this.leftLeg1.addBox(-1.0F, -1.0F, -2.5F, 3, 6, 5, 0.0F);
		this.setRotateAngle(leftLeg1, -0.5235987755982988F, 0.0F, 0.0F);
		this.lowerJaw3 = new ResettableModelRenderer(this, 0, 36);
		this.lowerJaw3.setRotationPoint(0.0F, 0.05F, -1.25F);
		this.lowerJaw3.addBox(-1.0F, 0.0F, -2.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(lowerJaw3, -0.04363323129985824F, 0.0F, 0.0F);
		this.rightToeMiddle = new ResettableModelRenderer(this, 52, 13);
		this.rightToeMiddle.mirror = true;
		this.rightToeMiddle.setRotationPoint(0.0F, 4.2F, -0.2F);
		this.rightToeMiddle.addBox(-0.5F, 0.0F, -3.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(rightToeMiddle, 0.4363323129985824F, 0.0F, 0.0F);
		this.lowerJaw1 = new ResettableModelRenderer(this, 0, 29);
		this.lowerJaw1.setRotationPoint(0.0F, 0.8F, -4.0F);
		this.lowerJaw1.addBox(-1.0F, 0.0F, -2.0F, 2, 1, 3, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 20, 12);
		this.neck2.setRotationPoint(0.0F, -0.35F, -6.3F);
		this.neck2.addBox(-2.0F, -2.0F, -4.5F, 4, 4, 5, 0.0F);
		this.setRotateAngle(neck2, 0.8726646259971648F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 47, 25);
		this.tail2.setRotationPoint(0.0F, 0.85F, 4.0F);
		this.tail2.addBox(-3.0F, 0.0F, -0.5F, 6, 0, 5, 0.0F);
		this.setRotateAngle(tail2, -0.7853981633974483F, 0.0F, 0.0F);
		this.leftWing1 = new ResettableModelRenderer(this, 0, 39);
		this.leftWing1.setRotationPoint(3.5F, -2.5F, -2.0F);
		this.leftWing1.addBox(-0.5F, -0.5F, -1.5F, 1, 4, 3, 0.0F);
		this.setRotateAngle(leftWing1, -0.5235987755982988F, 0.0F, -0.17453292519943295F);
		this.body3 = new ResettableModelRenderer(this, 18, 31);
		this.body3.setRotationPoint(0.0F, 2.5F, -3.2F);
		this.body3.addBox(-2.5F, -1.9F, -5.0F, 5, 5, 6, 0.0F);
		this.setRotateAngle(body3, -0.8726646259971648F, 0.0F, 0.0F);
		this.upperJawTop = new ResettableModelRenderer(this, 2, 23);
		this.upperJawTop.setRotationPoint(0.0F, -2.0F, 0.5F);
		this.upperJawTop.addBox(-1.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(upperJawTop, 0.36425021489121656F, 0.0F, 0.0F);
		this.leftWing2 = new ResettableModelRenderer(this, 0, 46);
		this.leftWing2.setRotationPoint(0.25F, 3.0F, 0.0F);
		this.leftWing2.addBox(-0.45F, -1.0F, -1.0F, 1, 3, 5, 0.0F);
		this.setRotateAngle(leftWing2, 0.0F, 0.0F, 0.17453292519943295F);
		this.neck3 = new ResettableModelRenderer(this, 22, 4);
		this.neck3.setRotationPoint(0.0F, -0.1F, -3.85F);
		this.neck3.addBox(-1.5F, -1.5F, -3.0F, 3, 4, 4, 0.0F);
		this.setRotateAngle(neck3, 0.8726646259971648F, 0.0F, 0.0F);
		this.lowerJaw1.addChild(this.lowerJaw2);
		this.leftLeg2.addChild(this.leftLeg3);
		this.body1.addChild(this.bodyBack);
		this.upperJaw1.addChild(this.upperJaw2);
		this.leftToeMiddle.addChild(this.leftToeRight);
		this.body1.addChild(this.body2);
		this.leftToeMiddle.addChild(this.leftToeLeft);
		this.rightWing1.addChild(this.rightWing2);
		this.upperJaw2.addChild(this.upperJaw3);
		this.rightToeMiddle.addChild(this.rightToeLeft);
		this.body3.addChild(this.neck1);
		this.head.addChild(this.upperJaw1);
		this.bodyBack.addChild(this.tail1);
		this.tail1.addChild(this.tail3);
		this.leftLeg3.addChild(this.leftToeMiddle);
		this.rightToeMiddle.addChild(this.rightToeRight);
		this.body1.addChild(this.rightWing1);
		this.rightLeg2.addChild(this.rightLeg3);
		this.rightLeg1.addChild(this.rightLeg2);
		this.leftLeg1.addChild(this.leftLeg2);
		this.neck3.addChild(this.head);
		this.lowerJaw2.addChild(this.lowerJaw3);
		this.rightLeg3.addChild(this.rightToeMiddle);
		this.head.addChild(this.lowerJaw1);
		this.neck1.addChild(this.neck2);
		this.tail1.addChild(this.tail2);
		this.body1.addChild(this.leftWing1);
		this.body2.addChild(this.body3);
		this.upperJaw1.addChild(this.upperJawTop);
		this.leftWing1.addChild(this.leftWing2);
		this.neck2.addChild(this.neck3);

		this.body1.saveParameters();
		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.body2.saveParameters();
		this.bodyBack.saveParameters();
		this.leftWing1.saveParameters();
		this.rightWing1.saveParameters();
		this.body3.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.neck3.saveParameters();
		this.head.saveParameters();
		this.upperJaw1.saveParameters();
		this.lowerJaw1.saveParameters();
		this.upperJawTop.saveParameters();
		this.upperJaw2.saveParameters();
		this.upperJaw3.saveParameters();
		this.lowerJaw2.saveParameters();
		this.lowerJaw3.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.leftWing2.saveParameters();
		this.rightWing2.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftToeMiddle.saveParameters();
		this.leftToeRight.saveParameters();
		this.leftToeLeft.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightToeMiddle.saveParameters();
		this.rightToeRight.saveParameters();
		this.rightToeLeft.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, (EntityRaphus) entity);
		this.body1.render(scale);
		this.rightLeg1.render(scale);
		this.leftLeg1.render(scale);
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale, EntityRaphus entity)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, entity);
		this.resetPose();

		float legX1 = this.getRotateAngleSimple(0.5F * time, 1.2F * walkSpeed, 1.0F);
		float headX = this.getHeadAngle(headPitch);
		float headY = this.getHeadAngle(headYaw);

		this.head.rotateAngleY = headY;

		this.leftLeg1.rotateAngleX += legX1;
		this.leftLeg2.rotateAngleX += legX1;
		this.leftToeMiddle.rotateAngleX -= legX1;

		this.rightLeg1.rotateAngleX -= legX1;
		this.rightLeg2.rotateAngleX -= legX1;
		this.rightToeMiddle.rotateAngleX += legX1;
	}

	private void resetPose()
	{
		this.body1.resetParameters();
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.body2.resetParameters();
		this.bodyBack.resetParameters();
		this.leftWing1.resetParameters();
		this.rightWing1.resetParameters();
		this.body3.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.neck3.resetParameters();
		this.head.resetParameters();
		this.upperJaw1.resetParameters();
		this.lowerJaw1.resetParameters();
		this.upperJawTop.resetParameters();
		this.upperJaw2.resetParameters();
		this.upperJaw3.resetParameters();
		this.lowerJaw2.resetParameters();
		this.lowerJaw3.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.leftWing2.resetParameters();
		this.rightWing2.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftToeMiddle.resetParameters();
		this.leftToeRight.resetParameters();
		this.leftToeLeft.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightToeMiddle.resetParameters();
		this.rightToeRight.resetParameters();
		this.rightToeLeft.resetParameters();
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
