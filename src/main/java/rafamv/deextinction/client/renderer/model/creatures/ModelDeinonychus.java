package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityDeinonychus;

public class ModelDeinonychus extends ResettableModelBase
{
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer bodyFront;
	public ResettableModelRenderer butt;
	public ResettableModelRenderer bodyMiddle;
	public ResettableModelRenderer neck;
	public ResettableModelRenderer head;
	public ResettableModelRenderer quills3;
	public ResettableModelRenderer quills2;
	public ResettableModelRenderer quills1;
	public ResettableModelRenderer quills4;
	public ResettableModelRenderer quills9;
	public ResettableModelRenderer quills7;
	public ResettableModelRenderer shape50;
	public ResettableModelRenderer shape50_1;
	public ResettableModelRenderer quills5;
	public ResettableModelRenderer quills6;
	public ResettableModelRenderer quills8;
	public ResettableModelRenderer quills10;
	public ResettableModelRenderer snoutmiddle;
	public ResettableModelRenderer snoutTop;
	public ResettableModelRenderer mouth;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tailFeathers1;
	public ResettableModelRenderer tailFeathers2;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer leftToe;
	public ResettableModelRenderer leftToeClaw;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftArmFeathers2;
	public ResettableModelRenderer leftArmFeathers1;
	public ResettableModelRenderer leftFinger3;
	public ResettableModelRenderer leftFinger2;
	public ResettableModelRenderer leftFinger1;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightArmFeathers2;
	public ResettableModelRenderer rightArmFeathers1;
	public ResettableModelRenderer rightFinger3;
	public ResettableModelRenderer rightFinger2;
	public ResettableModelRenderer rightFinger1;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer rightToe;
	public ResettableModelRenderer rightClaw;
	private Animator animator = new Animator();

	public ModelDeinonychus()
	{
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.leftArmFeathers2 = new ResettableModelRenderer(this, 44, 14);
		this.leftArmFeathers2.setRotationPoint(2.2F, 5.7F, -4.5F);
		this.leftArmFeathers2.addBox(0.0F, 0.0F, -4.0F, 0, 6, 9, 0.0F);
		this.setRotateAngle(leftArmFeathers2, 0.4553564018453205F, 0.0F, 0.0F);
		this.snoutTop = new ResettableModelRenderer(this, 59, 46);
		this.snoutTop.setRotationPoint(0.0F, -1.7F, -7.6F);
		this.snoutTop.addBox(-1.6F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.setRotateAngle(snoutTop, 0.2331759880664424F, 0.0F, 0.0F);
		this.rightArmFeathers2 = new ResettableModelRenderer(this, 44, 14);
		this.rightArmFeathers2.setRotationPoint(-2.1F, 5.7F, -4.5F);
		this.rightArmFeathers2.addBox(0.0F, 0.0F, -4.0F, 0, 6, 9, 0.0F);
		this.setRotateAngle(rightArmFeathers2, 0.4553564018453205F, 0.0F, 0.0F);
		this.rightFinger2 = new ResettableModelRenderer(this, 63, 12);
		this.rightFinger2.setRotationPoint(0.7F, 1.8F, -9.2F);
		this.rightFinger2.addBox(-1.0F, -1.0F, -9.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(rightFinger2, 0.8194320838113378F, 0.0F, -0.08726646259971647F);
		this.rightLeg2 = new ResettableModelRenderer(this, 0, 26);
		this.rightLeg2.setRotationPoint(0.8F, 4.0F, 0.0F);
		this.rightLeg2.addBox(-2.5F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(rightLeg2, 0.5846852994181004F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 102, 0);
		this.rightArm1.mirror = true;
		this.rightArm1.setRotationPoint(-4.0F, 1.0F, -6.7F);
		this.rightArm1.addBox(-4.0F, -2.0F, -2.0F, 4, 7, 3, 0.0F);
		this.setRotateAngle(rightArm1, 0.3490658503988659F, 0.04537856055185257F, 0.40142572795869574F);
		this.leftFinger2 = new ResettableModelRenderer(this, 63, 12);
		this.leftFinger2.setRotationPoint(0.7F, 1.8F, -9.2F);
		this.leftFinger2.addBox(-1.0F, -1.0F, -9.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(leftFinger2, 0.8194320838113378F, 0.0F, 0.08726646259971647F);
		this.quills10 = new ResettableModelRenderer(this, 0, 0);
		this.quills10.setRotationPoint(1.7F, -2.5F, -11.7F);
		this.quills10.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills10, 0.4553564018453205F, 0.27314402793711257F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 41, 66);
		this.leftLeg3.setRotationPoint(-0.5F, 11.0F, -1.1F);
		this.leftLeg3.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
		this.setRotateAngle(leftLeg3, -0.9995500626171526F, 0.0F, 0.0F);
		this.rightArmFeathers1 = new ResettableModelRenderer(this, 65, 13);
		this.rightArmFeathers1.setRotationPoint(-2.1F, 4.7F, 0.3F);
		this.rightArmFeathers1.addBox(0.0F, 0.0F, -4.0F, 0, 4, 10, 0.0F);
		this.setRotateAngle(rightArmFeathers1, 1.9577358219620393F, -0.045553093477052F, -0.091106186954104F);
		this.tail4 = new ResettableModelRenderer(this, 0, 0);
		this.tail4.setRotationPoint(0.0F, -0.5F, 10.0F);
		this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 14, 0.0F);
		this.setRotateAngle(tail4, 0.031415926535897934F, 0.0F, 0.0F);
		this.rightFinger3 = new ResettableModelRenderer(this, 63, 0);
		this.rightFinger3.setRotationPoint(0.7F, 2.1F, -9.3F);
		this.rightFinger3.addBox(-1.0F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
		this.setRotateAngle(rightFinger3, 1.0927506446736497F, 0.0F, -0.08726646259971647F);
		this.shape50_1 = new ResettableModelRenderer(this, 0, 0);
		this.shape50_1.setRotationPoint(-1.7F, -2.6F, -13.7F);
		this.shape50_1.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(shape50_1, 1.1838568316277536F, -0.27314402793711257F, 0.0F);
		this.snoutmiddle = new ResettableModelRenderer(this, 36, 53);
		this.snoutmiddle.setRotationPoint(0.0F, 0.0F, -8.0F);
		this.snoutmiddle.addBox(-2.0F, -1.5F, -7.0F, 4, 3, 7, 0.0F);
		this.leftFinger3 = new ResettableModelRenderer(this, 63, 0);
		this.leftFinger3.setRotationPoint(0.7F, 2.1F, -9.3F);
		this.leftFinger3.addBox(-1.0F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
		this.setRotateAngle(leftFinger3, 1.0927506446736497F, 0.0F, 0.08726646259971647F);
		this.leftFoot = new ResettableModelRenderer(this, 18, 34);
		this.leftFoot.setRotationPoint(0.1F, 7.4F, 1.0F);
		this.leftFoot.addBox(-1.5F, -0.5F, -6.0F, 3, 1, 6, 0.0F);
		this.setRotateAngle(leftFoot, 0.39950586578150205F, 0.0F, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 0, 26);
		this.leftLeg2.setRotationPoint(0.8F, 4.0F, 0.0F);
		this.leftLeg2.addBox(-2.5F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(leftLeg2, 0.5846852994181004F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 41, 66);
		this.rightLeg3.setRotationPoint(-0.5F, 11.0F, -1.1F);
		this.rightLeg3.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, 0.0F);
		this.setRotateAngle(rightLeg3, -0.9995500626171526F, 0.0F, 0.0F);
		this.bodyMiddle = new ResettableModelRenderer(this, 0, 90);
		this.bodyMiddle.setRotationPoint(0.0F, 0.1F, 2.0F);
		this.bodyMiddle.addBox(-5.0F, -7.0F, -13.0F, 10, 13, 11, 0.0F);
		this.shape50 = new ResettableModelRenderer(this, 0, 0);
		this.shape50.setRotationPoint(-1.7F, -2.6F, -13.7F);
		this.shape50.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(shape50, 1.1838568316277536F, -0.27314402793711257F, 0.0F);
		this.mouth = new ResettableModelRenderer(this, 54, 34);
		this.mouth.setRotationPoint(0.0F, 2.0F, -7.6F);
		this.mouth.addBox(-1.6F, -1.0F, -7.0F, 3, 2, 7, 0.0F);
		this.setRotateAngle(mouth, -0.136659280431156F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 77, 0);
		this.leftArm2.setRotationPoint(2.2F, 1.7F, 0.0F);
		this.leftArm2.addBox(-1.0F, 1.0F, -10.0F, 2, 2, 8, 0.0F);
		this.setRotateAngle(leftArm2, 0.3141592653589793F, 0.0F, 0.17453292519943295F);
		this.rightArm2 = new ResettableModelRenderer(this, 77, 0);
		this.rightArm2.setRotationPoint(-2.0F, 1.7F, 0.0F);
		this.rightArm2.addBox(-1.0F, 1.0F, -10.0F, 2, 2, 8, 0.0F);
		this.setRotateAngle(rightArm2, 0.3141592653589793F, 0.0F, -0.17453292519943295F);
		this.rightLeg1 = new ResettableModelRenderer(this, 0, 45);
		this.rightLeg1.setRotationPoint(-7.5F, 2.6F, 5.2F);
		this.rightLeg1.addBox(-3.0F, -4.0F, -3.5F, 6, 10, 7, 0.0F);
		this.quills5 = new ResettableModelRenderer(this, 0, 0);
		this.quills5.setRotationPoint(-1.7F, -2.6F, -13.7F);
		this.quills5.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills5, 1.1838568316277536F, -0.27314402793711257F, 0.0F);
		this.rightToe = new ResettableModelRenderer(this, 34, 34);
		this.rightToe.setRotationPoint(0.25F, 0.6F, -1.6F);
		this.rightToe.addBox(0.5F, -0.5F, -4.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(rightToe, -0.8726646259971648F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 83, 83);
		this.tail1.setRotationPoint(0.0F, -2.5F, -0.1F);
		this.tail1.addBox(-3.5F, -3.5F, 0.0F, 7, 7, 15, 0.0F);
		this.setRotateAngle(tail1, -0.6373942428283291F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 89, 62);
		this.tail2.setRotationPoint(0.0F, -0.2F, 14.4F);
		this.tail2.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 12, 0.0F);
		this.setRotateAngle(tail2, 0.010471975511965976F, 0.0F, 0.0F);
		this.neck = new ResettableModelRenderer(this, 54, 75);
		this.neck.setRotationPoint(0.0F, 0.6F, -0.8F);
		this.neck.addBox(-2.5F, -3.5F, -15.0F, 5, 6, 14, 0.0F);
		this.setRotateAngle(neck, -1.2747884856566583F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 18, 34);
		this.rightFoot.setRotationPoint(-0.1F, 7.4F, 1.0F);
		this.rightFoot.addBox(-1.5F, -0.5F, -6.0F, 3, 1, 6, 0.0F);
		this.setRotateAngle(rightFoot, 0.39950586578150205F, 0.0F, 0.0F);
		this.tailFeathers1 = new ResettableModelRenderer(this, 70, 37);
		this.tailFeathers1.setRotationPoint(0.0F, 0.2F, 7.1F);
		this.tailFeathers1.addBox(-4.5F, 0.0F, 0.0F, 9, 0, 16, 0.0F);
		this.setRotateAngle(tailFeathers1, 0.045553093477052F, 0.0F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 36, 102);
		this.mainBody.setRotationPoint(-0.0F, 2.5F, 0.0F);
		this.mainBody.addBox(-6.0F, -7.0F, -1.5F, 12, 14, 13, 0.0F);
		this.setRotateAngle(mainBody, -0.18203784098300857F, 0.0F, 0.0F);
		this.bodyFront = new ResettableModelRenderer(this, 0, 67);
		this.bodyFront.setRotationPoint(0.0F, 0.0F, -12.4F);
		this.bodyFront.addBox(-4.0F, -5.5F, -4.0F, 8, 10, 8, 0.0F);
		this.setRotateAngle(bodyFront, 0.6806784082777886F, 0.0F, 0.0F);
		this.quills1 = new ResettableModelRenderer(this, 0, 0);
		this.quills1.setRotationPoint(0.0F, -2.5F, -11.7F);
		this.quills1.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills1, 0.4553564018453205F, 0.0F, 0.0F);
		this.leftToe = new ResettableModelRenderer(this, 34, 34);
		this.leftToe.setRotationPoint(-2.25F, 0.6F, -1.6F);
		this.leftToe.addBox(0.5F, -0.5F, -4.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(leftToe, -0.8726646259971648F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 91, 40);
		this.tail3.setRotationPoint(0.0F, -0.5F, 10.0F);
		this.tail3.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 14, 0.0F);
		this.setRotateAngle(tail3, 0.03316125578789226F, 0.0F, 0.0F);
		this.quills3 = new ResettableModelRenderer(this, 0, 0);
		this.quills3.setRotationPoint(0.0F, -2.7F, -13.1F);
		this.quills3.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills3, 1.1838568316277536F, 0.0F, 0.0F);
		this.quills6 = new ResettableModelRenderer(this, 0, 0);
		this.quills6.setRotationPoint(1.7F, -2.6F, -13.7F);
		this.quills6.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills6, 1.1838568316277536F, 0.27314402793711257F, 0.0F);
		this.head = new ResettableModelRenderer(this, 60, 57);
		this.head.setRotationPoint(0.0F, -1.5F, -12.5F);
		this.head.addBox(-3.0F, -3.5F, -8.0F, 6, 7, 8, 0.0F);
		this.setRotateAngle(head, 0.9192649170254134F, 0.0F, 0.0F);
		this.quills2 = new ResettableModelRenderer(this, 0, 0);
		this.quills2.setRotationPoint(0.0F, -2.7F, -12.6F);
		this.quills2.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills2, 0.7285004297824331F, 0.0F, 0.0F);
		this.leftArmFeathers1 = new ResettableModelRenderer(this, 65, 13);
		this.leftArmFeathers1.setRotationPoint(2.2F, 4.7F, 0.3F);
		this.leftArmFeathers1.addBox(0.0F, 0.0F, -4.0F, 0, 4, 10, 0.0F);
		this.setRotateAngle(leftArmFeathers1, 1.9577358219620393F, -0.045553093477052F, 0.091106186954104F);
		this.quills9 = new ResettableModelRenderer(this, 0, 0);
		this.quills9.setRotationPoint(-1.7F, -2.5F, -11.7F);
		this.quills9.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills9, 0.4553564018453205F, -0.27314402793711257F, 0.0F);
		this.tailFeathers2 = new ResettableModelRenderer(this, 85, 16);
		this.tailFeathers2.setRotationPoint(0.0F, 0.0F, 10.0F);
		this.tailFeathers2.addBox(-5.5F, 0.0F, 0.0F, 11, 0, 16, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 0, 45);
		this.leftLeg1.setRotationPoint(7.5F, 2.6F, 5.2F);
		this.leftLeg1.addBox(-3.0F, -4.0F, -3.5F, 6, 10, 7, 0.0F);
		this.rightFinger1 = new ResettableModelRenderer(this, 63, 0);
		this.rightFinger1.setRotationPoint(0.7F, 1.8F, -9.2F);
		this.rightFinger1.addBox(-1.0F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
		this.setRotateAngle(rightFinger1, 0.6169738905799955F, -0.10122909661567112F, -0.08726646259971647F);
		this.quills8 = new ResettableModelRenderer(this, 0, 0);
		this.quills8.setRotationPoint(1.7F, -2.5F, -12.7F);
		this.quills8.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills8, 0.7740535232594852F, 0.27314402793711257F, 0.0F);
		this.butt = new ResettableModelRenderer(this, 93, 107);
		this.butt.setRotationPoint(0.0F, 0.8F, 9.4F);
		this.butt.addBox(-4.0F, -5.5F, -3.5F, 8, 11, 9, 0.0F);
		this.setRotateAngle(butt, 0.8651597102135892F, 0.0F, 0.0F);
		this.leftFinger1 = new ResettableModelRenderer(this, 63, 0);
		this.leftFinger1.setRotationPoint(0.7F, 1.8F, -9.2F);
		this.leftFinger1.addBox(-1.0F, -1.0F, -6.0F, 1, 1, 6, 0.0F);
		this.setRotateAngle(leftFinger1, 0.6169738905799955F, 0.10122909661567112F, 0.08726646259971647F);
		this.leftToeClaw = new ResettableModelRenderer(this, 22, 24);
		this.leftToeClaw.setRotationPoint(0.0F, -0.5F, -3.5F);
		this.leftToeClaw.addBox(0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(leftToeClaw, 1.2747884856566583F, 0.0F, 0.0F);
		this.quills4 = new ResettableModelRenderer(this, 0, 0);
		this.quills4.setRotationPoint(0.0F, -2.7F, -13.5F);
		this.quills4.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills4, 1.48352986419518F, 0.0F, 0.0F);
		this.rightClaw = new ResettableModelRenderer(this, 22, 24);
		this.rightClaw.setRotationPoint(0.0F, -0.5F, -3.5F);
		this.rightClaw.addBox(0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(rightClaw, 1.2747884856566583F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 102, 0);
		this.leftArm1.setRotationPoint(4.0F, 1.0F, -6.7F);
		this.leftArm1.addBox(0.0F, -2.0F, -2.0F, 4, 7, 3, 0.0F);
		this.setRotateAngle(leftArm1, 0.3490658503988659F, 0.04537856055185257F, -0.40142572795869574F);
		this.quills7 = new ResettableModelRenderer(this, 0, 0);
		this.quills7.setRotationPoint(-1.7F, -2.5F, -12.7F);
		this.quills7.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 5, 0.0F);
		this.setRotateAngle(quills7, 0.7740535232594852F, -0.27314402793711257F, 0.0F);
		this.leftArm1.addChild(this.leftArmFeathers2);
		this.head.addChild(this.snoutTop);
		this.rightArm1.addChild(this.rightArmFeathers2);
		this.rightArm2.addChild(this.rightFinger2);
		this.rightLeg1.addChild(this.rightLeg2);
		this.bodyMiddle.addChild(this.rightArm1);
		this.leftArm2.addChild(this.leftFinger2);
		this.neck.addChild(this.quills10);
		this.leftLeg2.addChild(this.leftLeg3);
		this.rightArm1.addChild(this.rightArmFeathers1);
		this.tail3.addChild(this.tail4);
		this.rightArm2.addChild(this.rightFinger3);
		this.neck.addChild(this.shape50_1);
		this.head.addChild(this.snoutmiddle);
		this.leftArm2.addChild(this.leftFinger3);
		this.leftLeg3.addChild(this.leftFoot);
		this.leftLeg1.addChild(this.leftLeg2);
		this.rightLeg2.addChild(this.rightLeg3);
		this.mainBody.addChild(this.bodyMiddle);
		this.neck.addChild(this.shape50);
		this.head.addChild(this.mouth);
		this.leftArm1.addChild(this.leftArm2);
		this.rightArm1.addChild(this.rightArm2);
		this.neck.addChild(this.quills5);
		this.rightFoot.addChild(this.rightToe);
		this.butt.addChild(this.tail1);
		this.tail1.addChild(this.tail2);
		this.bodyFront.addChild(this.neck);
		this.rightLeg3.addChild(this.rightFoot);
		this.tail3.addChild(this.tailFeathers1);
		this.bodyMiddle.addChild(this.bodyFront);
		this.neck.addChild(this.quills1);
		this.leftFoot.addChild(this.leftToe);
		this.tail2.addChild(this.tail3);
		this.neck.addChild(this.quills3);
		this.neck.addChild(this.quills6);
		this.neck.addChild(this.head);
		this.neck.addChild(this.quills2);
		this.leftArm1.addChild(this.leftArmFeathers1);
		this.neck.addChild(this.quills9);
		this.tail4.addChild(this.tailFeathers2);
		this.rightArm2.addChild(this.rightFinger1);
		this.neck.addChild(this.quills8);
		this.mainBody.addChild(this.butt);
		this.leftArm2.addChild(this.leftFinger1);
		this.leftToe.addChild(this.leftToeClaw);
		this.neck.addChild(this.quills4);
		this.rightToe.addChild(this.rightClaw);
		this.bodyMiddle.addChild(this.leftArm1);
		this.neck.addChild(this.quills7);

		this.mainBody.saveParameters();
		this.leftLeg1.saveParameters();
		this.leftArm1.saveParameters();
		this.rightArm1.saveParameters();
		this.rightLeg1.saveParameters();
		this.bodyFront.saveParameters();
		this.butt.saveParameters();
		this.bodyMiddle.saveParameters();
		this.neck.saveParameters();
		this.head.saveParameters();
		this.quills3.saveParameters();
		this.quills2.saveParameters();
		this.quills1.saveParameters();
		this.quills4.saveParameters();
		this.quills9.saveParameters();
		this.quills7.saveParameters();
		this.shape50.saveParameters();
		this.shape50_1.saveParameters();
		this.quills5.saveParameters();
		this.quills6.saveParameters();
		this.quills8.saveParameters();
		this.quills10.saveParameters();
		this.snoutmiddle.saveParameters();
		this.snoutTop.saveParameters();
		this.mouth.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.tailFeathers1.saveParameters();
		this.tailFeathers2.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
		this.leftToe.saveParameters();
		this.leftToeClaw.saveParameters();
		this.leftArm2.saveParameters();
		this.leftArmFeathers2.saveParameters();
		this.leftArmFeathers1.saveParameters();
		this.leftFinger3.saveParameters();
		this.leftFinger2.saveParameters();
		this.leftFinger1.saveParameters();
		this.rightArm2.saveParameters();
		this.rightArmFeathers2.saveParameters();
		this.rightArmFeathers1.saveParameters();
		this.rightFinger3.saveParameters();
		this.rightFinger2.saveParameters();
		this.rightFinger1.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.rightToe.saveParameters();
		this.rightClaw.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityDeinonychus creature = (EntityDeinonychus) entity;
		this.setRotationAngles(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.animateModel(walkedDistance, walkSpeed, time, headPitch, headYaw, scale, creature);
		this.mainBody.render(scale);
		this.leftLeg1.render(scale);
		this.rightLeg1.render(scale);
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float yaw, float pitch, float partialRenderTicks)
	{
		this.resetPose();
		EntityDeinonychus deinonychus = (EntityDeinonychus) entity;
		float sittingProgress = deinonychus.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.01F)
		{
			this.mainBody.rotationPointY += 12.0F * sittingProgress;

			this.rightLeg1.rotationPointY += 12.0F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.8F * sittingProgress;
			this.rightFoot.rotationPointZ -= 1.4F * sittingProgress;
			this.rightFoot.rotationPointY -= 0.2F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.2F * sittingProgress;

			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotationPointZ += 1.0F * sittingProgress;
			this.rightArm2.rotationPointY += 2.0F * sittingProgress;
			this.rightArm2.rotateAngleX -= 1.25F * sittingProgress;
			this.rightArmFeathers2.rotationPointY -= 4F * sittingProgress;
			this.rightArmFeathers2.rotationPointZ += 4F * sittingProgress;
			this.rightArmFeathers2.rotateAngleX -= 1.25F * sittingProgress;
			this.rightFinger1.rotateAngleX += 1.5F * sittingProgress;
			this.rightFinger2.rotateAngleX += 1.5F * sittingProgress;
			this.rightFinger3.rotateAngleX += 1.5F * sittingProgress;

			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotationPointZ += 1.0F * sittingProgress;
			this.leftArm2.rotationPointY += 2.0F * sittingProgress;
			this.leftArm2.rotateAngleX -= 1.25F * sittingProgress;
			this.leftArmFeathers2.rotationPointY -= 4F * sittingProgress;
			this.leftArmFeathers2.rotationPointZ += 4F * sittingProgress;
			this.leftArmFeathers2.rotateAngleX -= 1.25F * sittingProgress;
			this.leftFinger1.rotateAngleX += 1.5F * sittingProgress;
			this.leftFinger2.rotateAngleX += 1.5F * sittingProgress;
			this.leftFinger3.rotateAngleX += 1.5F * sittingProgress;

			this.leftLeg1.rotationPointY += 12.0F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.8F * sittingProgress;
			this.leftFoot.rotationPointZ -= 1.4F * sittingProgress;
			this.leftFoot.rotationPointY -= 0.2F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.2F * sittingProgress;
		}

		float tailBuffer = deinonychus.tailBuffer.getAnimation(partialRenderTicks);
		this.butt.rotateAngleY += tailBuffer;
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityDeinonychus deinonychus)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, deinonychus);

		if (deinonychus.isSitting())
		{
			float naturalMovement = this.getAlwaysRotateAngle(time, 0.046875F, 0.03F);
			this.mainBody.rotateAngleX -= naturalMovement;
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

			float[] naturalTailMovement = this.getChainMovement(time, 1.0F, 4, 0.046875F, 0.05F, -2.0F);
			this.tail1.rotateAngleX -= naturalTailMovement[0];
			this.tail2.rotateAngleX -= naturalTailMovement[1];
			this.tail3.rotateAngleX -= naturalTailMovement[2];
			this.tail4.rotateAngleX -= naturalTailMovement[3];
		}
		else
		{
			if (walkSpeed > 0.01F)
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

					this.mainBody.rotateAngleX += 0.5F * runningInclination;
				}
			}
			else
			{
				float naturalMovement = this.getAlwaysRotateAngle(time, 0.09375F, 0.03F);
				this.mainBody.rotateAngleX -= naturalMovement;
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
			this.animator.rotate(this.bodyFront, -0.1F, 0.0F, 0.0F);
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
			this.animator.rotate(this.bodyFront, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.4F, 0.0F, 0.0F);
			this.animator.rotate(this.neck, -0.6F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.mouth, 0.6F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.setStationaryPhase(2);
			this.animator.startPhase(2);
			this.animator.rotate(this.mainBody, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, -0.15F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.startPhase(2);
			this.animator.rotate(this.mainBody, 0.2F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.head, 0.2F, 0.0F, 0.0F);
			this.animator.endPhase();
			this.animator.resetPhase(2);
		}
	}

	private void resetPose()
	{
		this.mainBody.resetParameters();
		this.leftLeg1.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.rightLeg1.resetParameters();
		this.bodyFront.resetParameters();
		this.butt.resetParameters();
		this.bodyMiddle.resetParameters();
		this.neck.resetParameters();
		this.head.resetParameters();
		this.mouth.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.leftArm2.resetParameters();
		this.leftArmFeathers2.resetParameters();
		this.leftFinger3.resetParameters();
		this.leftFinger2.resetParameters();
		this.leftFinger1.resetParameters();
		this.rightArm2.resetParameters();
		this.rightArmFeathers2.resetParameters();
		this.rightFinger3.resetParameters();
		this.rightFinger2.resetParameters();
		this.rightFinger1.resetParameters();
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
