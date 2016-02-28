package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityZhenyuanlong;

public class ModelZhenyuanlong extends ResettableModelBase
{
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer butt;
	public ResettableModelRenderer mainBodyFront;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tailFeathersRight1;
	public ResettableModelRenderer tailFeathersLeft1;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tailFeathersRight2;
	public ResettableModelRenderer tailFeathersLeft2;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tailFeathers3;
	public ResettableModelRenderer tailFeathers;
	public ResettableModelRenderer chest;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer neck1;
	public ResettableModelRenderer neck2;
	public ResettableModelRenderer headJoint;
	public ResettableModelRenderer head;
	public ResettableModelRenderer upperjaw;
	public ResettableModelRenderer down_jaw;
	public ResettableModelRenderer upperjaw_1;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftArmFeathers1;
	public ResettableModelRenderer leftFinger1;
	public ResettableModelRenderer leftFinger2;
	public ResettableModelRenderer leftArmFeathers2;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightArmFeathers1;
	public ResettableModelRenderer rightFinger1;
	public ResettableModelRenderer rightFinger2;
	public ResettableModelRenderer rightArmFeathers2;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg1Feathers;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftLeg2Feathers;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg1Feathers;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightLeg2Feathers;
	public ResettableModelRenderer rightFoot;
	private Animator animator = new Animator();

	public ModelZhenyuanlong()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.mainBody = new ResettableModelRenderer(this, 88, 0);
		this.mainBody.setRotationPoint(0.0F, 8.0F, 1.0F);
		this.mainBody.addBox(-3.5F, -4.0F, -1.0F, 7, 8, 8, 0.0F);
		this.setRotateAngle(mainBody, -0.10471975511965977F, 0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.rightLeg2.mirror = true;
		this.rightLeg2.setRotationPoint(-0.5F, 3.5F, 0.0F);
		this.rightLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
		this.setRotateAngle(rightLeg2, 1.0471975511965976F, 0.0F, 0.0F);
		this.rightArmFeathers1 = new ResettableModelRenderer(this, 20, 85);
		this.rightArmFeathers1.setRotationPoint(0.0F, 3.0F, 1.0F);
		this.rightArmFeathers1.addBox(0.0F, -2.75F, -1.5F, 0, 8, 4, 0.0F);
		this.upperjaw_1 = new ResettableModelRenderer(this, 23, 32);
		this.upperjaw_1.setRotationPoint(0.5F, -0.8F, 1.0F);
		this.upperjaw_1.addBox(-1.5F, 0.0F, -7.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(upperjaw_1, 0.10471975511965977F, 0.0F, 0.0F);
		this.leftArmFeathers1 = new ResettableModelRenderer(this, 20, 85);
		this.leftArmFeathers1.setRotationPoint(0.0F, 3.0F, 1.0F);
		this.leftArmFeathers1.addBox(0.0F, -2.75F, -1.5F, 0, 8, 4, 0.0F);
		this.rightLeg2Feathers = new ResettableModelRenderer(this, 5, 117);
		this.rightLeg2Feathers.mirror = true;
		this.rightLeg2Feathers.setRotationPoint(0.0F, 3.5F, 0.5F);
		this.rightLeg2Feathers.addBox(0.0F, -2.0F, 0.0F, 0, 7, 3, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 52, 35);
		this.rightArm1.mirror = true;
		this.rightArm1.setRotationPoint(-3.0F, -1.0F, -4.7F);
		this.rightArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.setRotateAngle(rightArm1, 0.5235987755982988F, 0.0F, 0.3490658503988659F);
		this.headJoint = new ResettableModelRenderer(this, 72, 46);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -3.25F);
		this.headJoint.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(headJoint, 0.31869712141416456F, 0.0F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.leftLeg3.setRotationPoint(0.0F, 7.0F, 0.5F);
		this.leftLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(leftLeg3, -1.0471975511965976F, 0.0F, 0.0F);
		this.mainBodyFront = new ResettableModelRenderer(this, 91, 20);
		this.mainBodyFront.setRotationPoint(0.0F, 0.4F, -2.0F);
		this.mainBodyFront.addBox(-3.0F, -4.0F, -6.0F, 6, 7, 8, 0.0F);
		this.setRotateAngle(mainBodyFront, 0.17453292519943295F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 55, 45);
		this.leftArm2.mirror = true;
		this.leftArm2.setRotationPoint(0.5F, 5.5F, 0.0F);
		this.leftArm2.addBox(-1.0F, 0.0F, -1.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(leftArm2, -1.5707963267948966F, 0.08726646259971647F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 40, 30);
		this.tail3.setRotationPoint(0.0F, -0.2F, 7.4F);
		this.tail3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F);
		this.setRotateAngle(tail3, -0.08726646259971647F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 37, 0);
		this.tail1.setRotationPoint(0.0F, -1.25F, 0.0F);
		this.tail1.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 8, 0.0F);
		this.setRotateAngle(tail1, 0.4363323129985824F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 90, 35);
		this.chest.setRotationPoint(0.0F, -0.5F, -3.0F);
		this.chest.addBox(-2.5F, -3.0F, -4.5F, 5, 6, 4, 0.0F);
		this.setRotateAngle(chest, -0.4363323129985824F, 0.0F, 0.0F);
		this.tailFeathers = new ResettableModelRenderer(this, 29, 71);
		this.tailFeathers.setRotationPoint(0.0F, -0.5F, 0.2F);
		this.tailFeathers.addBox(-3.5F, 0.0F, 0.0F, 7, 0, 11, 0.0F);
		this.leftLeg2Feathers = new ResettableModelRenderer(this, 5, 117);
		this.leftLeg2Feathers.mirror = true;
		this.leftLeg2Feathers.setRotationPoint(0.0F, 3.5F, 0.5F);
		this.leftLeg2Feathers.addBox(0.0F, -2.0F, 0.0F, 0, 7, 3, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 4, 94);
		this.leftFoot.setRotationPoint(0.0F, 7.0F, 0.8F);
		this.leftFoot.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(leftFoot, 0.4363323129985824F, 0.0F, 0.0F);
		this.upperjaw = new ResettableModelRenderer(this, 22, 41);
		this.upperjaw.setRotationPoint(-0.0F, -1.3F, -3.0F);
		this.upperjaw.addBox(-1.5F, 0.0F, -6.2F, 3, 2, 5, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.rightLeg1.mirror = true;
		this.rightLeg1.setRotationPoint(-4.2F, 8.2F, 3.6F);
		this.rightLeg1.addBox(-2.0F, -2.5F, -2.5F, 3, 8, 5, 0.0F);
		this.setRotateAngle(rightLeg1, -0.4363323129985824F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 4, 94);
		this.rightFoot.mirror = true;
		this.rightFoot.setRotationPoint(0.0F, 7.0F, 0.8F);
		this.rightFoot.addBox(-1.0F, -0.5F, -4.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(rightFoot, 0.4363323129985824F, 0.0F, 0.0F);
		this.leftLeg1Feathers = new ResettableModelRenderer(this, 5, 106);
		this.leftLeg1Feathers.mirror = true;
		this.leftLeg1Feathers.setRotationPoint(0.0F, -0.25F, 1.5F);
		this.leftLeg1Feathers.addBox(0.0F, -2.0F, 0.0F, 0, 8, 4, 0.0F);
		this.rightArmFeathers2 = new ResettableModelRenderer(this, 20, 97);
		this.rightArmFeathers2.setRotationPoint(-0.4F, 2.2F, 0.0F);
		this.rightArmFeathers2.addBox(0.0F, -2.0F, -0.5F, 0, 5, 3, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 37, 15);
		this.tail2.setRotationPoint(0.0F, 0.0F, 7.0F);
		this.tail2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8, 0.0F);
		this.setRotateAngle(tail2, -0.08726646259971647F, 0.0F, 0.0F);
		this.tailFeathersLeft1 = new ResettableModelRenderer(this, 29, 55);
		this.tailFeathersLeft1.setRotationPoint(0.0F, -1.8F, 0.0F);
		this.tailFeathersLeft1.addBox(1.0F, 0.0F, 0.0F, 4, 0, 9, 0.0F);
		this.setRotateAngle(tailFeathersLeft1, 0.0F, 0.0F, 0.17453292519943295F);
		this.rightFinger2 = new ResettableModelRenderer(this, 24, 82);
		this.rightFinger2.setRotationPoint(-0.6F, 4.0F, -0.25F);
		this.rightFinger2.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightFinger2, 1.0471975511965976F, 0.08726646259971647F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 0, 48);
		this.leftLeg1.setRotationPoint(5.2F, 8.2F, 3.6F);
		this.leftLeg1.addBox(-2.0F, -2.5F, -2.5F, 3, 8, 5, 0.0F);
		this.setRotateAngle(leftLeg1, -0.4363323129985824F, 0.0F, 0.0F);
		this.head = new ResettableModelRenderer(this, 1, 31);
		this.head.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.head.addBox(-2.0F, -2.0F, -5.0F, 4, 4, 5, 0.0F);
		this.tailFeathersRight1 = new ResettableModelRenderer(this, 29, 55);
		this.tailFeathersRight1.mirror = true;
		this.tailFeathersRight1.setRotationPoint(0.0F, -1.8F, 0.0F);
		this.tailFeathersRight1.addBox(-5.0F, 0.0F, 0.0F, 4, 0, 9, 0.0F);
		this.setRotateAngle(tailFeathersRight1, 0.0F, 0.0F, -0.17453292519943295F);
		this.down_jaw = new ResettableModelRenderer(this, 22, 51);
		this.down_jaw.setRotationPoint(0.0F, 0.7F, -5.0F);
		this.down_jaw.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
		this.setRotateAngle(down_jaw, -0.08726646259971647F, 0.0F, 0.0F);
		this.rightFinger1 = new ResettableModelRenderer(this, 23, 74);
		this.rightFinger1.setRotationPoint(-0.61F, 4.0F, -0.5F);
		this.rightFinger1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(rightFinger1, 0.6981317007977318F, 0.08726646259971647F, 0.0F);
		this.tailFeathersRight2 = new ResettableModelRenderer(this, 43, 59);
		this.tailFeathersRight2.mirror = true;
		this.tailFeathersRight2.setRotationPoint(-0.5F, -1.2F, 1.0F);
		this.tailFeathersRight2.addBox(-4.0F, 0.0F, 0.0F, 4, 0, 8, 0.0F);
		this.setRotateAngle(tailFeathersRight2, 0.0F, 0.0F, -0.2617993877991494F);
		this.tailFeathersLeft2 = new ResettableModelRenderer(this, 43, 59);
		this.tailFeathersLeft2.setRotationPoint(0.5F, -1.2F, 1.0F);
		this.tailFeathersLeft2.addBox(0.0F, 0.0F, 0.0F, 4, 0, 8, 0.0F);
		this.setRotateAngle(tailFeathersLeft2, 0.0F, 0.0F, 0.2617993877991494F);
		this.tail4 = new ResettableModelRenderer(this, 38, 40);
		this.tail4.setRotationPoint(0.0F, 0.0F, 5.5F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 8, 0.0F);
		this.setRotateAngle(tail4, -0.08726646259971647F, 0.0F, 0.0F);
		this.leftFinger1 = new ResettableModelRenderer(this, 23, 74);
		this.leftFinger1.setRotationPoint(-0.61F, 4.0F, -0.5F);
		this.leftFinger1.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftFinger1, 0.6981317007977318F, -0.08726646259971647F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 5, 79);
		this.rightLeg3.mirror = true;
		this.rightLeg3.setRotationPoint(0.0F, 7.0F, 0.5F);
		this.rightLeg3.addBox(-0.5F, 0.0F, -1.0F, 1, 7, 2, 0.0F);
		this.setRotateAngle(rightLeg3, -1.0471975511965976F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 52, 35);
		this.leftArm1.mirror = true;
		this.leftArm1.setRotationPoint(3.0F, -1.0F, -4.7F);
		this.leftArm1.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
		this.setRotateAngle(leftArm1, 0.5235987755982988F, 0.0F, -0.3490658503988659F);
		this.leftArmFeathers2 = new ResettableModelRenderer(this, 20, 97);
		this.leftArmFeathers2.setRotationPoint(-0.4F, 2.2F, 0.0F);
		this.leftArmFeathers2.addBox(0.0F, -2.0F, -0.5F, 0, 5, 3, 0.0F);
		this.neck1 = new ResettableModelRenderer(this, 95, 55);
		this.neck1.setRotationPoint(0.0F, 0.0F, -3.25F);
		this.neck1.addBox(-2.0F, -2.0F, -6.0F, 4, 4, 6, 0.0F);
		this.setRotateAngle(neck1, -0.3490658503988659F, 0.0F, 0.0F);
		this.neck2 = new ResettableModelRenderer(this, 95, 65);
		this.neck2.setRotationPoint(0.0F, -0.4F, -6.0F);
		this.neck2.addBox(-1.5F, -1.25F, -4.0F, 3, 4, 5, 0.0F);
		this.setRotateAngle(neck2, 0.5235987755982988F, 0.0F, 0.0F);
		this.leftFinger2 = new ResettableModelRenderer(this, 24, 82);
		this.leftFinger2.setRotationPoint(-0.6F, 4.0F, -0.25F);
		this.leftFinger2.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 1, 0.0F);
		this.setRotateAngle(leftFinger2, 1.0471975511965976F, -0.08726646259971647F, 0.0F);
		this.butt = new ResettableModelRenderer(this, 93, 80);
		this.butt.setRotationPoint(0.0F, -0.3F, 6.5F);
		this.butt.addBox(-3.0F, -3.0F, -2.5F, 6, 6, 5, 0.0F);
		this.setRotateAngle(butt, -0.20943951023931953F, 0.0F, 0.0F);
		this.rightLeg1Feathers = new ResettableModelRenderer(this, 5, 106);
		this.rightLeg1Feathers.mirror = true;
		this.rightLeg1Feathers.setRotationPoint(-1.0F, -0.25F, 1.5F);
		this.rightLeg1Feathers.addBox(0.0F, -2.0F, 0.0F, 0, 8, 4, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 3, 65);
		this.leftLeg2.setRotationPoint(-0.5F, 3.5F, 0.0F);
		this.leftLeg2.addBox(-1.0F, 0.0F, -1.5F, 2, 8, 3, 0.0F);
		this.setRotateAngle(leftLeg2, 1.0471975511965976F, 0.0F, 0.0F);
		this.tailFeathers3 = new ResettableModelRenderer(this, 29, 87);
		this.tailFeathers3.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.tailFeathers3.addBox(-2.5F, 0.0F, 0.0F, 5, 0, 7, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 55, 45);
		this.rightArm2.mirror = true;
		this.rightArm2.setRotationPoint(0.5F, 5.5F, 0.0F);
		this.rightArm2.addBox(-1.0F, 0.0F, -1.5F, 1, 5, 2, 0.0F);
		this.setRotateAngle(rightArm2, -1.5707963267948966F, -0.08726646259971647F, 0.0F);
		this.rightLeg1.addChild(this.rightLeg2);
		this.rightArm1.addChild(this.rightArmFeathers1);
		this.upperjaw.addChild(this.upperjaw_1);
		this.leftArm1.addChild(this.leftArmFeathers1);
		this.rightLeg2.addChild(this.rightLeg2Feathers);
		this.mainBodyFront.addChild(this.rightArm1);
		this.neck2.addChild(this.headJoint);
		this.leftLeg2.addChild(this.leftLeg3);
		this.mainBody.addChild(this.mainBodyFront);
		this.leftArm1.addChild(this.leftArm2);
		this.tail2.addChild(this.tail3);
		this.butt.addChild(this.tail1);
		this.mainBodyFront.addChild(this.chest);
		this.tail4.addChild(this.tailFeathers);
		this.leftLeg2.addChild(this.leftLeg2Feathers);
		this.leftLeg3.addChild(this.leftFoot);
		this.head.addChild(this.upperjaw);
		this.rightLeg3.addChild(this.rightFoot);
		this.leftLeg1.addChild(this.leftLeg1Feathers);
		this.rightArm2.addChild(this.rightArmFeathers2);
		this.tail1.addChild(this.tail2);
		this.tail1.addChild(this.tailFeathersLeft1);
		this.rightArm2.addChild(this.rightFinger2);
		this.headJoint.addChild(this.head);
		this.tail1.addChild(this.tailFeathersRight1);
		this.head.addChild(this.down_jaw);
		this.rightArm2.addChild(this.rightFinger1);
		this.tail2.addChild(this.tailFeathersRight2);
		this.tail2.addChild(this.tailFeathersLeft2);
		this.tail3.addChild(this.tail4);
		this.leftArm2.addChild(this.leftFinger1);
		this.rightLeg2.addChild(this.rightLeg3);
		this.mainBodyFront.addChild(this.leftArm1);
		this.leftArm2.addChild(this.leftArmFeathers2);
		this.chest.addChild(this.neck1);
		this.neck1.addChild(this.neck2);
		this.leftArm2.addChild(this.leftFinger2);
		this.mainBody.addChild(this.butt);
		this.rightLeg1.addChild(this.rightLeg1Feathers);
		this.leftLeg1.addChild(this.leftLeg2);
		this.tail3.addChild(this.tailFeathers3);
		this.rightArm1.addChild(this.rightArm2);

		this.mainBody.saveParameters();
		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.butt.saveParameters();
		this.mainBodyFront.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tailFeathersRight1.saveParameters();
		this.tailFeathersLeft1.saveParameters();
		this.tail3.saveParameters();
		this.tailFeathersRight2.saveParameters();
		this.tailFeathersLeft2.saveParameters();
		this.tail4.saveParameters();
		this.tailFeathers3.saveParameters();
		this.tailFeathers.saveParameters();
		this.chest.saveParameters();
		this.leftArm1.saveParameters();
		this.rightArm1.saveParameters();
		this.neck1.saveParameters();
		this.neck2.saveParameters();
		this.headJoint.saveParameters();
		this.head.saveParameters();
		this.upperjaw.saveParameters();
		this.down_jaw.saveParameters();
		this.upperjaw_1.saveParameters();
		this.leftArm2.saveParameters();
		this.leftArmFeathers1.saveParameters();
		this.leftFinger1.saveParameters();
		this.leftFinger2.saveParameters();
		this.leftArmFeathers2.saveParameters();
		this.rightArm2.saveParameters();
		this.rightArmFeathers1.saveParameters();
		this.rightFinger1.saveParameters();
		this.rightFinger2.saveParameters();
		this.rightArmFeathers2.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg1Feathers.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftLeg2Feathers.saveParameters();
		this.leftFoot.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg1Feathers.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightLeg2Feathers.saveParameters();
		this.rightFoot.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityZhenyuanlong creature = (EntityZhenyuanlong) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);

		this.mainBody.render(scale);
		this.rightLeg1.render(scale);
		this.leftLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();
		EntityZhenyuanlong creature = (EntityZhenyuanlong) entity;
		float sittingProgress = creature.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 10.0F * sittingProgress;

			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotateAngleX -= 1.0F * sittingProgress;

			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotateAngleX -= 1.0F * sittingProgress;

			this.rightLeg1.rotationPointY += 10.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.62F * sittingProgress;
			this.rightFoot.rotationPointZ -= 0.65F * sittingProgress;
			this.rightFoot.rotationPointY -= 0.2F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.0F * sittingProgress;

			this.leftLeg1.rotationPointY += 10.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.62F * sittingProgress;
			this.leftFoot.rotationPointZ -= 0.65F * sittingProgress;
			this.leftFoot.rotationPointY -= 0.2F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.0F * sittingProgress;
		}

		float tailBuffer = creature.tailBuffer.getAnimation(partialRenderTicks);
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityZhenyuanlong creature)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, creature);

		if (creature.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.mainBody.rotateAngleX -= naturalMovement;
			this.mainBodyFront.rotateAngleX -= naturalMovement;
			this.neck1.rotateAngleX -= 0.5F * naturalMovement;
			this.neck2.rotateAngleX -= 0.5F * naturalMovement;
			this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
			this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

			float headX = this.getHeadAngle(pitch) / 2.0F;
			float headY = this.getHeadAngle(yaw) / 2.0F;
			this.head.rotateAngleX += 0.4F * headX;
			this.head.rotateAngleY += 0.4F * headY;
			this.neck1.rotateAngleX += 0.125F * headX;
			this.neck1.rotateAngleY += 0.25F * headY;
			this.neck2.rotateAngleX += 0.125F * headX;
			this.neck2.rotateAngleY += 0.25F * headY;
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
				this.neck1.rotateAngleX -= 0.5F * naturalMovement;
				this.neck2.rotateAngleX -= 0.5F * naturalMovement;
				this.leftArm1.rotateAngleX += 3.0F * naturalMovement;
				this.rightArm1.rotateAngleX += 3.0F * naturalMovement;

				float headX = this.getHeadAngle(pitch) / 2.0F;
				float headY = this.getHeadAngle(yaw) / 2.0F;
				this.head.rotateAngleX += 0.4F * headX;
				this.head.rotateAngleY += 0.4F * headY;
				this.neck1.rotateAngleX += 0.125F * headX;
				this.neck1.rotateAngleY += 0.25F * headY;
				this.neck2.rotateAngleX += 0.125F * headX;
				this.neck2.rotateAngleY += 0.25F * headY;
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
			this.animator.rotate(this.neck1, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.neck2, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.down_jaw, 0.6F, 0.0F, 0.0F);
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
		this.tailFeathersRight1.resetParameters();
		this.tailFeathersLeft1.resetParameters();
		this.tail3.resetParameters();
		this.tailFeathersRight2.resetParameters();
		this.tailFeathersLeft2.resetParameters();
		this.tail4.resetParameters();
		this.tailFeathers3.resetParameters();
		this.tailFeathers.resetParameters();
		this.chest.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.headJoint.resetParameters();
		this.head.resetParameters();
		this.upperjaw.resetParameters();
		this.down_jaw.resetParameters();
		this.upperjaw_1.resetParameters();
		this.leftArm2.resetParameters();
		this.leftArmFeathers1.resetParameters();
		this.leftFinger1.resetParameters();
		this.leftFinger2.resetParameters();
		this.leftArmFeathers2.resetParameters();
		this.rightArm2.resetParameters();
		this.rightArmFeathers1.resetParameters();
		this.rightFinger1.resetParameters();
		this.rightFinger2.resetParameters();
		this.rightArmFeathers2.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg1Feathers.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftLeg2Feathers.resetParameters();
		this.leftFoot.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg1Feathers.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightLeg2Feathers.resetParameters();
		this.rightFoot.resetParameters();
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.butt.resetParameters();
		this.mainBodyFront.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tailFeathersRight1.resetParameters();
		this.tailFeathersLeft1.resetParameters();
		this.tail3.resetParameters();
		this.tailFeathersRight2.resetParameters();
		this.tailFeathersLeft2.resetParameters();
		this.tail4.resetParameters();
		this.tailFeathers3.resetParameters();
		this.tailFeathers.resetParameters();
		this.chest.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.neck1.resetParameters();
		this.neck2.resetParameters();
		this.headJoint.resetParameters();
		this.head.resetParameters();
		this.upperjaw.resetParameters();
		this.down_jaw.resetParameters();
		this.upperjaw_1.resetParameters();
		this.leftArm2.resetParameters();
		this.leftArmFeathers1.resetParameters();
		this.leftFinger1.resetParameters();
		this.leftFinger2.resetParameters();
		this.leftArmFeathers2.resetParameters();
		this.rightArm2.resetParameters();
		this.rightArmFeathers1.resetParameters();
		this.rightFinger1.resetParameters();
		this.rightFinger2.resetParameters();
		this.rightArmFeathers2.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg1Feathers.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftLeg2Feathers.resetParameters();
		this.leftFoot.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg1Feathers.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightLeg2Feathers.resetParameters();
		this.rightFoot.resetParameters();
	}

	public void setRotateAngle(ResettableModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
