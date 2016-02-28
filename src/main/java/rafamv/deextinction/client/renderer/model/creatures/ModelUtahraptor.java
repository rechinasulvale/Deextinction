package rafamv.deextinction.client.renderer.model.creatures;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import rafamv.deextinction.client.renderer.Animator;
import rafamv.deextinction.client.renderer.ResettableModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;
import rafamv.deextinction.common.entity.ai.animation.DEAnimationList;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.entity.creature.EntityUtahraptor;

public class ModelUtahraptor extends ResettableModelBase
{
	public ResettableModelRenderer leftLeg1;
	public ResettableModelRenderer rightLeg1;
	public ResettableModelRenderer mainBody;
	public ResettableModelRenderer leftLeg2;
	public ResettableModelRenderer leftLeg3;
	public ResettableModelRenderer leftFoot;
	public ResettableModelRenderer leftToe3;
	public ResettableModelRenderer leftToe2;
	public ResettableModelRenderer leftToe1;
	public ResettableModelRenderer leftToeClaw;
	public ResettableModelRenderer rightLeg2;
	public ResettableModelRenderer rightLeg3;
	public ResettableModelRenderer rightFoot;
	public ResettableModelRenderer rightToe3;
	public ResettableModelRenderer rightToe2;
	public ResettableModelRenderer rightToe1;
	public ResettableModelRenderer rightToeClaw;
	public ResettableModelRenderer butt;
	public ResettableModelRenderer bodyFront;
	public ResettableModelRenderer tail1;
	public ResettableModelRenderer tail2;
	public ResettableModelRenderer tail3;
	public ResettableModelRenderer tail4;
	public ResettableModelRenderer tail5;
	public ResettableModelRenderer tailFan;
	public ResettableModelRenderer chest;
	public ResettableModelRenderer leftArm1;
	public ResettableModelRenderer rightArm1;
	public ResettableModelRenderer neck;
	public ResettableModelRenderer shape17;
	public ResettableModelRenderer bristle5;
	public ResettableModelRenderer bristle6;
	public ResettableModelRenderer bristle4;
	public ResettableModelRenderer bristle9;
	public ResettableModelRenderer bristle7;
	public ResettableModelRenderer bristle8;
	public ResettableModelRenderer head;
	public ResettableModelRenderer leftEye;
	public ResettableModelRenderer snout1;
	public ResettableModelRenderer snouthTop;
	public ResettableModelRenderer bristle1;
	public ResettableModelRenderer bristle2;
	public ResettableModelRenderer bristle3;
	public ResettableModelRenderer mouth;
	public ResettableModelRenderer rightEye;
	public ResettableModelRenderer tooth1;
	public ResettableModelRenderer tooth5;
	public ResettableModelRenderer tooth4;
	public ResettableModelRenderer tooth3;
	public ResettableModelRenderer tooth2;
	public ResettableModelRenderer tooth6;
	public ResettableModelRenderer tooth12;
	public ResettableModelRenderer tooth11;
	public ResettableModelRenderer tooth10;
	public ResettableModelRenderer tooth9;
	public ResettableModelRenderer tooth8;
	public ResettableModelRenderer tooth7;
	public ResettableModelRenderer leftArm2;
	public ResettableModelRenderer leftArmFeathers1;
	public ResettableModelRenderer shape53;
	public ResettableModelRenderer leftArmFeathers2;
	public ResettableModelRenderer leftFinger3;
	public ResettableModelRenderer leftFinger1;
	public ResettableModelRenderer leftFinger2;
	public ResettableModelRenderer rightArm2;
	public ResettableModelRenderer rightArmFeathers1;
	public ResettableModelRenderer shape53_1;
	public ResettableModelRenderer rightArmFeathers2;
	public ResettableModelRenderer rightFinger3;
	public ResettableModelRenderer rightFinger1;
	public ResettableModelRenderer rightFinger2;
	private Animator animator = new Animator();

	public ModelUtahraptor()
	{
		this.textureWidth = 256;
		this.textureHeight = 256;
		this.tooth4 = new ResettableModelRenderer(this, 0, 0);
		this.tooth4.mirror = true;
		this.tooth4.setRotationPoint(-2.7F, 1.3F, -7.0F);
		this.tooth4.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth4, 0.14590952546672595F, 0.0F, 0.0F);
		this.leftEye = new ResettableModelRenderer(this, 7, 58);
		this.leftEye.setRotationPoint(0.0F, -1.5F, -7.0F);
		this.leftEye.addBox(2.2F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(leftEye, 0.22759093446006054F, 0.0F, 0.0F);
		this.rightLeg1 = new ResettableModelRenderer(this, 5, 221);
		this.rightLeg1.setRotationPoint(-5.1F, -2.1F, 5.0F);
		this.rightLeg1.addBox(-7.0F, -6.5F, -5.5F, 7, 13, 11, 0.0F);
		this.leftArmFeathers2 = new ResettableModelRenderer(this, 50, 145);
		this.leftArmFeathers2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftArmFeathers2.addBox(0.0F, -5.0F, -5.0F, 0, 5, 13, 0.0F);
		this.leftLeg2 = new ResettableModelRenderer(this, 0, 191);
		this.leftLeg2.mirror = true;
		this.leftLeg2.setRotationPoint(3.7F, 2.5F, 0.0F);
		this.leftLeg2.addBox(-2.5F, 0.0F, -3.5F, 5, 14, 7, 0.0F);
		this.setRotateAngle(leftLeg2, 0.5918411493512771F, 0.0F, 0.0F);
		this.tooth1 = new ResettableModelRenderer(this, 0, 0);
		this.tooth1.mirror = true;
		this.tooth1.setRotationPoint(-2.7F, 1.3F, -2.5F);
		this.tooth1.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth1, 0.14590952546672595F, 0.0F, 0.0F);
		this.chest = new ResettableModelRenderer(this, 200, 109);
		this.chest.setRotationPoint(0.0F, 0.4F, -13.7F);
		this.chest.addBox(-5.5F, -5.5F, -6.0F, 11, 11, 11, 0.0F);
		this.setRotateAngle(chest, -0.7853981633974483F, 0.0F, 0.0F);
		this.leftToe3 = new ResettableModelRenderer(this, 0, 122);
		this.leftToe3.mirror = true;
		this.leftToe3.setRotationPoint(0.5F, 0.5F, -7.5F);
		this.leftToe3.addBox(0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(leftToe3, 0.0F, -0.22759093446006054F, 0.0F);
		this.leftLeg3 = new ResettableModelRenderer(this, 0, 159);
		this.leftLeg3.mirror = true;
		this.leftLeg3.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.leftLeg3.addBox(-2.0F, 0.0F, -2.5F, 4, 14, 5, 0.0F);
		this.setRotateAngle(leftLeg3, -0.9560913642424937F, 0.0F, 0.0F);
		this.rightLeg2 = new ResettableModelRenderer(this, 0, 191);
		this.rightLeg2.setRotationPoint(-3.7F, 2.5F, 0.0F);
		this.rightLeg2.addBox(-2.5F, 0.0F, -3.5F, 5, 14, 7, 0.0F);
		this.setRotateAngle(rightLeg2, 0.5918411493512771F, 0.0F, 0.0F);
		this.neck = new ResettableModelRenderer(this, 167, 83);
		this.neck.setRotationPoint(0.0F, -1.2F, -1.6F);
		this.neck.addBox(-3.5F, -4.5F, -14.0F, 7, 9, 14, 0.0F);
		this.setRotateAngle(neck, 0.27314402793711257F, 0.0F, 0.0F);
		this.mainBody = new ResettableModelRenderer(this, 128, 0);
		this.mainBody.setRotationPoint(0.0F, -3.5F, 3.0F);
		this.mainBody.addBox(-6.5F, -8.0F, -7.0F, 13, 16, 16, 0.0F);
		this.setRotateAngle(mainBody, -0.03490658503988659F, 0.0F, 0.0F);
		this.rightFoot = new ResettableModelRenderer(this, 0, 140);
		this.rightFoot.setRotationPoint(0.0F, 12.9F, 2.0F);
		this.rightFoot.addBox(-2.5F, -1.0F, -8.0F, 5, 2, 8, 0.0F);
		this.setRotateAngle(rightFoot, 0.34487706019407954F, 0.0F, 0.0F);
		this.tooth3 = new ResettableModelRenderer(this, 0, 0);
		this.tooth3.mirror = true;
		this.tooth3.setRotationPoint(-2.7F, 1.3F, -5.5F);
		this.tooth3.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth3, 0.14590952546672595F, 0.0F, 0.0F);
		this.tail3 = new ResettableModelRenderer(this, 104, 42);
		this.tail3.setRotationPoint(0.0F, 0.0F, 11.6F);
		this.tail3.addBox(-3.5F, -3.0F, 0.0F, 7, 7, 13, 0.0F);
		this.setRotateAngle(tail3, -0.03490658503988659F, 0.0F, 0.0F);
		this.rightArmFeathers2 = new ResettableModelRenderer(this, 50, 145);
		this.rightArmFeathers2.mirror = true;
		this.rightArmFeathers2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightArmFeathers2.addBox(0.0F, -5.0F, -5.0F, 0, 5, 13, 0.0F);
		this.tooth9 = new ResettableModelRenderer(this, 0, 0);
		this.tooth9.setRotationPoint(2.7F, 1.3F, -5.5F);
		this.tooth9.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth9, 0.14590952546672595F, 0.0F, 0.0F);
		this.bristle8 = new ResettableModelRenderer(this, 32, 27);
		this.bristle8.setRotationPoint(-2.0F, -4.3F, -12.7F);
		this.bristle8.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle8, 0.44087016905376764F, 0.0F, 0.0F);
		this.leftToe2 = new ResettableModelRenderer(this, 0, 122);
		this.leftToe2.mirror = true;
		this.leftToe2.setRotationPoint(0.0F, 0.5F, -7.5F);
		this.leftToe2.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
		this.tooth11 = new ResettableModelRenderer(this, 0, 0);
		this.tooth11.setRotationPoint(2.7F, 1.3F, -8.5F);
		this.tooth11.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth11, 0.14590952546672595F, 0.0F, 0.0F);
		this.leftFinger3 = new ResettableModelRenderer(this, 50, 136);
		this.leftFinger3.mirror = true;
		this.leftFinger3.setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.leftFinger3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(leftFinger3, 2.0943951023931953F, 0.0F, 0.0F);
		this.rightToe3 = new ResettableModelRenderer(this, 0, 122);
		this.rightToe3.setRotationPoint(-2.3F, 0.5F, -7.0F);
		this.rightToe3.addBox(0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(rightToe3, 0.0F, 0.22759093446006054F, 0.0F);
		this.tail5 = new ResettableModelRenderer(this, 70, 55);
		this.tail5.setRotationPoint(0.0F, -0.3F, 13.0F);
		this.tail5.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 11, 0.0F);
		this.setRotateAngle(tail5, -0.03490658503988659F, 0.0F, 0.0F);
		this.tooth6 = new ResettableModelRenderer(this, 0, 0);
		this.tooth6.mirror = true;
		this.tooth6.setRotationPoint(-2.7F, 1.3F, -10.0F);
		this.tooth6.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth6, 0.1651081472386636F, 0.0F, 0.0F);
		this.bristle2 = new ResettableModelRenderer(this, 32, 27);
		this.bristle2.setRotationPoint(2.0F, -4.0F, -0.8F);
		this.bristle2.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle2, 0.3349286834577118F, -0.0017453292519943296F, 0.0F);
		this.rightFinger1 = new ResettableModelRenderer(this, 50, 136);
		this.rightFinger1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.rightFinger1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(rightFinger1, 1.7453292519943295F, 0.0F, 0.0F);
		this.mouth = new ResettableModelRenderer(this, 93, 96);
		this.mouth.setRotationPoint(0.0F, 3.1F, -9.4F);
		this.mouth.addBox(-2.0F, -1.0F, -9.8F, 4, 2, 10, 0.0F);
		this.snouthTop = new ResettableModelRenderer(this, 120, 111);
		this.snouthTop.setRotationPoint(0.0F, -1.8F, -9.0F);
		this.snouthTop.addBox(-2.5F, -2.0F, -10.0F, 5, 3, 10, 0.0F);
		this.setRotateAngle(snouthTop, 0.16109388995907664F, 0.0F, 0.0F);
		this.tailFan = new ResettableModelRenderer(this, 200, 78);
		this.tailFan.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFan.addBox(-7.0F, 0.0F, 0.0F, 14, 0, 23, 0.0F);
		this.tooth12 = new ResettableModelRenderer(this, 0, 0);
		this.tooth12.setRotationPoint(2.7F, 1.3F, -10.0F);
		this.tooth12.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth12, 0.14590952546672595F, 0.0F, 0.0F);
		this.bristle4 = new ResettableModelRenderer(this, 32, 27);
		this.bristle4.setRotationPoint(0.0F, -4.4F, -13.5F);
		this.bristle4.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle4, 0.7285004297824331F, 0.0F, 0.0F);
		this.rightArm1 = new ResettableModelRenderer(this, 50, 220);
		this.rightArm1.setRotationPoint(-5.7F, 1.0F, -13.5F);
		this.rightArm1.addBox(-4.0F, -2.0F, 0.0F, 4, 4, 10, 0.0F);
		this.setRotateAngle(rightArm1, -0.6829473363053812F, 0.0F, 0.31869712141416456F);
		this.leftToe1 = new ResettableModelRenderer(this, 0, 115);
		this.leftToe1.mirror = true;
		this.leftToe1.setRotationPoint(-1.7F, 0.2F, -7.2F);
		this.leftToe1.addBox(-0.5F, -0.5F, -4.1F, 1, 1, 5, 0.0F);
		this.setRotateAngle(leftToe1, -0.7285004297824331F, 0.0F, 0.0F);
		this.leftFinger2 = new ResettableModelRenderer(this, 50, 115);
		this.leftFinger2.mirror = true;
		this.leftFinger2.setRotationPoint(-0.1F, 0.0F, 0.0F);
		this.leftFinger2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 10, 0.0F);
		this.setRotateAngle(leftFinger2, 1.9198621771937625F, 0.0F, 0.0F);
		this.shape53_1 = new ResettableModelRenderer(this, 50, 187);
		this.shape53_1.setRotationPoint(0.0F, 0.0F, 8.9F);
		this.shape53_1.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(shape53_1, -0.36425021489121656F, 0.0F, 0.0F);
		this.leftLeg1 = new ResettableModelRenderer(this, 5, 221);
		this.leftLeg1.mirror = true;
		this.leftLeg1.setRotationPoint(5.1F, -2.1F, 5.0F);
		this.leftLeg1.addBox(0.0F, -6.5F, -5.5F, 7, 13, 11, 0.0F);
		this.rightToeClaw = new ResettableModelRenderer(this, 0, 110);
		this.rightToeClaw.setRotationPoint(0.0F, -0.4F, -3.7F);
		this.rightToeClaw.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(rightToeClaw, 1.2747884856566583F, 0.0F, 0.0F);
		this.shape17 = new ResettableModelRenderer(this, 199, 150);
		this.shape17.setRotationPoint(0.0F, -1.7F, -11.6F);
		this.shape17.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(shape17, 0.5462880558742251F, 0.0F, 0.0F);
		this.bodyFront = new ResettableModelRenderer(this, 192, 5);
		this.bodyFront.setRotationPoint(0.0F, -0.5F, -5.8F);
		this.bodyFront.addBox(-7.0F, -8.0F, -14.0F, 14, 16, 14, 0.0F);
		this.setRotateAngle(bodyFront, 0.08726646259971647F, 0.0F, 0.0F);
		this.rightToe2 = new ResettableModelRenderer(this, 0, 122);
		this.rightToe2.setRotationPoint(0.0F, 0.5F, -7.5F);
		this.rightToe2.addBox(-0.5F, -0.5F, -5.0F, 1, 1, 5, 0.0F);
		this.snout1 = new ResettableModelRenderer(this, 117, 85);
		this.snout1.setRotationPoint(0.0F, 0.0F, -9.4F);
		this.snout1.addBox(-3.0F, -2.0F, -10.0F, 6, 4, 10, 0.0F);
		this.rightArm2 = new ResettableModelRenderer(this, 50, 200);
		this.rightArm2.setRotationPoint(-2.0F, -0.4F, 9.0F);
		this.rightArm2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(rightArm2, -1.9123572614101867F, 0.0F, 0.0F);
		this.bristle3 = new ResettableModelRenderer(this, 32, 27);
		this.bristle3.setRotationPoint(-2.0F, -4.0F, -0.8F);
		this.bristle3.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle3, 0.3349286834577118F, 0.0F, 0.0F);
		this.rightFinger2 = new ResettableModelRenderer(this, 50, 115);
		this.rightFinger2.setRotationPoint(0.1F, 0.0F, 0.0F);
		this.rightFinger2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 10, 0.0F);
		this.setRotateAngle(rightFinger2, 1.9198621771937625F, 0.0F, 0.0F);
		this.leftFinger1 = new ResettableModelRenderer(this, 50, 136);
		this.leftFinger1.mirror = true;
		this.leftFinger1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leftFinger1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(leftFinger1, 1.7453292519943295F, 0.0F, 0.0F);
		this.leftArmFeathers1 = new ResettableModelRenderer(this, 50, 155);
		this.leftArmFeathers1.setRotationPoint(2.0F, -1.3F, 5.8F);
		this.leftArmFeathers1.addBox(0.0F, -5.0F, -5.5F, 0, 5, 13, 0.0F);
		this.setRotateAngle(leftArmFeathers1, 0.0F, 0.0F, 0.22759093446006054F);
		this.head = new ResettableModelRenderer(this, 155, 109);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -4.5F, -10.0F, 8, 9, 10, 0.0F);
		this.setRotateAngle(head, -0.09250245035569946F, 0.0F, 0.0F);
		this.rightToe1 = new ResettableModelRenderer(this, 0, 115);
		this.rightToe1.setRotationPoint(1.7F, 0.2F, -7.2F);
		this.rightToe1.addBox(-0.5F, -0.5F, -4.1F, 1, 1, 5, 0.0F);
		this.setRotateAngle(rightToe1, -0.7285004297824331F, 0.0F, 0.0F);
		this.leftFoot = new ResettableModelRenderer(this, 0, 14);
		this.leftFoot.mirror = true;
		this.leftFoot.setRotationPoint(0.0F, 12.9F, 2.0F);
		this.leftFoot.addBox(-2.5F, -1.0F, -8.0F, 5, 2, 8, 0.0F);
		this.setRotateAngle(leftFoot, 0.34487706019407954F, 0.0F, 0.0F);
		this.tail4 = new ResettableModelRenderer(this, 69, 30);
		this.tail4.setRotationPoint(0.0F, -0.2F, 11.1F);
		this.tail4.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 14, 0.0F);
		this.setRotateAngle(tail4, -0.03490658503988659F, 0.0F, 0.0F);
		this.tooth7 = new ResettableModelRenderer(this, 0, 0);
		this.tooth7.setRotationPoint(2.7F, 1.3F, -2.5F);
		this.tooth7.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth7, 0.14590952546672595F, 0.0F, 0.0F);
		this.bristle5 = new ResettableModelRenderer(this, 32, 27);
		this.bristle5.setRotationPoint(2.0F, -4.4F, -13.6F);
		this.bristle5.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle5, 0.5585053606381855F, 0.0F, 0.0F);
		this.bristle1 = new ResettableModelRenderer(this, 32, 27);
		this.bristle1.setRotationPoint(0.0F, -4.0F, -0.8F);
		this.bristle1.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle1, 0.5009094953223726F, 0.0F, 0.0F);
		this.bristle7 = new ResettableModelRenderer(this, 32, 27);
		this.bristle7.setRotationPoint(2.0F, -4.3F, -12.7F);
		this.bristle7.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle7, 0.44087016905376764F, 0.0F, 0.0F);
		this.leftArm1 = new ResettableModelRenderer(this, 50, 220);
		this.leftArm1.mirror = true;
		this.leftArm1.setRotationPoint(5.7F, 1.0F, -13.5F);
		this.leftArm1.addBox(0.0F, -2.0F, 0.0F, 4, 4, 10, 0.0F);
		this.setRotateAngle(leftArm1, -0.6829473363053812F, 0.0F, -0.31869712141416456F);
		this.tooth8 = new ResettableModelRenderer(this, 0, 0);
		this.tooth8.setRotationPoint(2.7F, 1.3F, -4.0F);
		this.tooth8.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth8, 0.14590952546672595F, 0.0F, 0.0F);
		this.leftToeClaw = new ResettableModelRenderer(this, 0, 110);
		this.leftToeClaw.mirror = true;
		this.leftToeClaw.setRotationPoint(0.0F, -0.4F, -3.7F);
		this.leftToeClaw.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(leftToeClaw, 1.2747884856566583F, 0.0F, 0.0F);
		this.rightEye = new ResettableModelRenderer(this, 0, 58);
		this.rightEye.setRotationPoint(0.0F, -1.5F, -7.0F);
		this.rightEye.addBox(-4.2F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(rightEye, 0.22759093446006054F, 0.0F, 0.0F);
		this.shape53 = new ResettableModelRenderer(this, 50, 187);
		this.shape53.mirror = true;
		this.shape53.setRotationPoint(0.0F, 0.0F, 8.9F);
		this.shape53.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(shape53, -0.36425021489121656F, 0.0F, 0.0F);
		this.bristle6 = new ResettableModelRenderer(this, 32, 27);
		this.bristle6.setRotationPoint(-2.0F, -4.4F, -13.6F);
		this.bristle6.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle6, 0.5585053606381855F, 0.0F, 0.0F);
		this.tooth10 = new ResettableModelRenderer(this, 0, 0);
		this.tooth10.setRotationPoint(2.7F, 1.3F, -7.0F);
		this.tooth10.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth10, 0.14590952546672595F, 0.0F, 0.0F);
		this.bristle9 = new ResettableModelRenderer(this, 32, 27);
		this.bristle9.setRotationPoint(0.0F, -4.3F, -12.7F);
		this.bristle9.addBox(-0.5F, 0.0F, 0.0F, 1, 0, 7, 0.0F);
		this.setRotateAngle(bristle9, 0.31869712141416456F, 0.0F, 0.0F);
		this.rightFinger3 = new ResettableModelRenderer(this, 50, 136);
		this.rightFinger3.setRotationPoint(0.2F, 0.0F, 0.0F);
		this.rightFinger3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 9, 0.0F);
		this.setRotateAngle(rightFinger3, 2.0943951023931953F, 0.0F, 0.0F);
		this.tail1 = new ResettableModelRenderer(this, 204, 44);
		this.tail1.setRotationPoint(0.0F, -1.4F, 3.1F);
		this.tail1.addBox(-4.5F, -4.0F, 0.0F, 9, 10, 14, 0.0F);
		this.setRotateAngle(tail1, -0.7853981633974483F, 0.0F, 0.0F);
		this.tooth5 = new ResettableModelRenderer(this, 0, 0);
		this.tooth5.mirror = true;
		this.tooth5.setRotationPoint(-2.7F, 1.3F, -8.5F);
		this.tooth5.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth5, 0.14590952546672595F, 0.0F, 0.0F);
		this.butt = new ResettableModelRenderer(this, 76, 0);
		this.butt.setRotationPoint(0.0F, 0.3F, 7.5F);
		this.butt.addBox(-5.5F, -4.0F, -5.0F, 11, 10, 12, 0.0F);
		this.setRotateAngle(butt, 0.8726646259971648F, 0.0F, 0.0F);
		this.tail2 = new ResettableModelRenderer(this, 142, 53);
		this.tail2.setRotationPoint(0.0F, -0.1F, 12.4F);
		this.tail2.addBox(-4.0F, -3.5F, 0.0F, 8, 9, 13, 0.0F);
		this.setRotateAngle(tail2, -0.03490658503988659F, 0.0F, 0.0F);
		this.rightArmFeathers1 = new ResettableModelRenderer(this, 50, 155);
		this.rightArmFeathers1.mirror = true;
		this.rightArmFeathers1.setRotationPoint(-2.0F, -1.3F, 5.8F);
		this.rightArmFeathers1.addBox(0.0F, -5.0F, -5.5F, 0, 5, 13, 0.0F);
		this.setRotateAngle(rightArmFeathers1, 0.0F, 0.0F, -0.22759093446006054F);
		this.tooth2 = new ResettableModelRenderer(this, 0, 0);
		this.tooth2.mirror = true;
		this.tooth2.setRotationPoint(-2.7F, 1.3F, -4.0F);
		this.tooth2.addBox(0.0F, 0.0F, 0.0F, 0, 2, 1, 0.0F);
		this.setRotateAngle(tooth2, 0.14590952546672595F, 0.0F, 0.0F);
		this.leftArm2 = new ResettableModelRenderer(this, 50, 200);
		this.leftArm2.mirror = true;
		this.leftArm2.setRotationPoint(2.0F, -0.4F, 9.0F);
		this.leftArm2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(leftArm2, -1.9123572614101867F, 0.0F, 0.0F);
		this.rightLeg3 = new ResettableModelRenderer(this, 0, 159);
		this.rightLeg3.setRotationPoint(0.0F, 12.0F, 0.0F);
		this.rightLeg3.addBox(-2.0F, 0.0F, -2.5F, 4, 14, 5, 0.0F);
		this.setRotateAngle(rightLeg3, -0.9560913642424937F, 0.0F, 0.0F);
		this.snout1.addChild(this.tooth4);
		this.head.addChild(this.leftEye);
		this.leftArm2.addChild(this.leftArmFeathers2);
		this.leftLeg1.addChild(this.leftLeg2);
		this.snout1.addChild(this.tooth1);
		this.bodyFront.addChild(this.chest);
		this.leftFoot.addChild(this.leftToe3);
		this.leftLeg2.addChild(this.leftLeg3);
		this.rightLeg1.addChild(this.rightLeg2);
		this.chest.addChild(this.neck);
		this.rightLeg3.addChild(this.rightFoot);
		this.snout1.addChild(this.tooth3);
		this.tail2.addChild(this.tail3);
		this.rightArm2.addChild(this.rightArmFeathers2);
		this.snout1.addChild(this.tooth9);
		this.neck.addChild(this.bristle8);
		this.leftFoot.addChild(this.leftToe2);
		this.snout1.addChild(this.tooth11);
		this.shape53.addChild(this.leftFinger3);
		this.rightFoot.addChild(this.rightToe3);
		this.tail4.addChild(this.tail5);
		this.snout1.addChild(this.tooth6);
		this.head.addChild(this.bristle2);
		this.shape53_1.addChild(this.rightFinger1);
		this.head.addChild(this.mouth);
		this.head.addChild(this.snouthTop);
		this.tail5.addChild(this.tailFan);
		this.snout1.addChild(this.tooth12);
		this.neck.addChild(this.bristle4);
		this.bodyFront.addChild(this.rightArm1);
		this.leftFoot.addChild(this.leftToe1);
		this.shape53.addChild(this.leftFinger2);
		this.rightArm2.addChild(this.shape53_1);
		this.rightToe1.addChild(this.rightToeClaw);
		this.neck.addChild(this.shape17);
		this.mainBody.addChild(this.bodyFront);
		this.rightFoot.addChild(this.rightToe2);
		this.head.addChild(this.snout1);
		this.rightArm1.addChild(this.rightArm2);
		this.head.addChild(this.bristle3);
		this.shape53_1.addChild(this.rightFinger2);
		this.shape53.addChild(this.leftFinger1);
		this.leftArm1.addChild(this.leftArmFeathers1);
		this.shape17.addChild(this.head);
		this.rightFoot.addChild(this.rightToe1);
		this.leftLeg3.addChild(this.leftFoot);
		this.tail3.addChild(this.tail4);
		this.snout1.addChild(this.tooth7);
		this.neck.addChild(this.bristle5);
		this.head.addChild(this.bristle1);
		this.neck.addChild(this.bristle7);
		this.bodyFront.addChild(this.leftArm1);
		this.snout1.addChild(this.tooth8);
		this.leftToe1.addChild(this.leftToeClaw);
		this.head.addChild(this.rightEye);
		this.leftArm2.addChild(this.shape53);
		this.neck.addChild(this.bristle6);
		this.snout1.addChild(this.tooth10);
		this.neck.addChild(this.bristle9);
		this.shape53_1.addChild(this.rightFinger3);
		this.butt.addChild(this.tail1);
		this.snout1.addChild(this.tooth5);
		this.mainBody.addChild(this.butt);
		this.tail1.addChild(this.tail2);
		this.rightArm1.addChild(this.rightArmFeathers1);
		this.snout1.addChild(this.tooth2);
		this.leftArm1.addChild(this.leftArm2);
		this.rightLeg2.addChild(this.rightLeg3);

		this.leftLeg1.saveParameters();
		this.rightLeg1.saveParameters();
		this.mainBody.saveParameters();
		this.leftLeg2.saveParameters();
		this.leftLeg3.saveParameters();
		this.leftFoot.saveParameters();
		this.leftToe3.saveParameters();
		this.leftToe2.saveParameters();
		this.leftToe1.saveParameters();
		this.leftToeClaw.saveParameters();
		this.rightLeg2.saveParameters();
		this.rightLeg3.saveParameters();
		this.rightFoot.saveParameters();
		this.rightToe3.saveParameters();
		this.rightToe2.saveParameters();
		this.rightToe1.saveParameters();
		this.rightToeClaw.saveParameters();
		this.butt.saveParameters();
		this.bodyFront.saveParameters();
		this.tail1.saveParameters();
		this.tail2.saveParameters();
		this.tail3.saveParameters();
		this.tail4.saveParameters();
		this.tail5.saveParameters();
		this.tailFan.saveParameters();
		this.chest.saveParameters();
		this.leftArm1.saveParameters();
		this.rightArm1.saveParameters();
		this.neck.saveParameters();
		this.shape17.saveParameters();
		this.bristle5.saveParameters();
		this.bristle6.saveParameters();
		this.bristle4.saveParameters();
		this.bristle9.saveParameters();
		this.bristle7.saveParameters();
		this.bristle8.saveParameters();
		this.head.saveParameters();
		this.leftEye.saveParameters();
		this.snout1.saveParameters();
		this.snouthTop.saveParameters();
		this.bristle1.saveParameters();
		this.bristle2.saveParameters();
		this.bristle3.saveParameters();
		this.mouth.saveParameters();
		this.rightEye.saveParameters();
		this.tooth1.saveParameters();
		this.tooth5.saveParameters();
		this.tooth4.saveParameters();
		this.tooth3.saveParameters();
		this.tooth2.saveParameters();
		this.tooth6.saveParameters();
		this.tooth12.saveParameters();
		this.tooth11.saveParameters();
		this.tooth10.saveParameters();
		this.tooth9.saveParameters();
		this.tooth8.saveParameters();
		this.tooth7.saveParameters();
		this.leftArm2.saveParameters();
		this.leftArmFeathers1.saveParameters();
		this.shape53.saveParameters();
		this.leftArmFeathers2.saveParameters();
		this.leftFinger3.saveParameters();
		this.leftFinger1.saveParameters();
		this.leftFinger2.saveParameters();
		this.rightArm2.saveParameters();
		this.rightArmFeathers1.saveParameters();
		this.shape53_1.saveParameters();
		this.rightArmFeathers2.saveParameters();
		this.rightFinger3.saveParameters();
		this.rightFinger1.saveParameters();
		this.rightFinger2.saveParameters();
	}

	@Override
	public void render(Entity entity, float walkedDistance, float walkSpeed, float time, float headPitch, float headYaw, float scale)
	{
		EntityUtahraptor creature = (EntityUtahraptor) entity;
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
		EntityUtahraptor utahraptor = (EntityUtahraptor) entity;
		float sittingProgress = utahraptor.getSittingProgress(partialRenderTicks);
		if (sittingProgress > 0.001F)
		{
			this.mainBody.rotationPointY += 15.5F * sittingProgress;
			this.mainBody.rotateAngleX -= 0.2F * sittingProgress;
			this.bodyFront.rotationPointZ += 2.0F * sittingProgress;
			this.bodyFront.rotateAngleX += 0.1F * sittingProgress;
			this.butt.rotateAngleX += 0.25F * sittingProgress;

			this.rightLeg1.rotationPointY += 15.5F * sittingProgress;
			this.rightLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.rightLeg2.rotationPointY += 2.0F * sittingProgress;
			this.rightLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.rightLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.rightLeg3.rotateAngleX -= 1.8F * sittingProgress;
			this.rightFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.rightFoot.rotateAngleX += 1.2F * sittingProgress;

			this.leftLeg1.rotationPointY += 15.5F * sittingProgress;
			this.leftLeg1.rotateAngleX -= 1.0F * sittingProgress;
			this.leftLeg2.rotationPointY += 2.0F * sittingProgress;
			this.leftLeg2.rotateAngleX += 1.6F * sittingProgress;
			this.leftLeg3.rotationPointZ -= 1.0F * sittingProgress;
			this.leftLeg3.rotateAngleX -= 1.8F * sittingProgress;
			this.leftFoot.rotationPointZ -= 0.5F * sittingProgress;
			this.leftFoot.rotateAngleX += 1.2F * sittingProgress;

			this.rightArm1.rotationPointZ -= 3.0F * sittingProgress;
			this.rightArm1.rotateAngleX += 0.6F * sittingProgress;
			this.rightArm2.rotateAngleX -= 0.5F * sittingProgress;
			this.rightFinger1.rotateAngleX += 0.5F * sittingProgress;
			this.rightFinger2.rotateAngleX += 0.5F * sittingProgress;
			this.rightFinger3.rotateAngleX += 0.5F * sittingProgress;

			this.leftArm1.rotationPointZ -= 3.0F * sittingProgress;
			this.leftArm1.rotateAngleX += 0.6F * sittingProgress;
			this.leftArm2.rotateAngleX -= 0.5F * sittingProgress;
			this.leftFinger1.rotateAngleX += 0.5F * sittingProgress;
			this.leftFinger2.rotateAngleX += 0.5F * sittingProgress;
			this.leftFinger3.rotateAngleX += 0.5F * sittingProgress;
		}

		float tailBuffer = utahraptor.tailBuffer.getAnimation(partialRenderTicks);
		this.butt.rotateAngleY += tailBuffer;
		this.tail1.rotateAngleY += tailBuffer;
		this.tail2.rotateAngleY += tailBuffer;
		this.tail3.rotateAngleY += tailBuffer;
		this.tail4.rotateAngleY += tailBuffer;
		this.tail5.rotateAngleY += tailBuffer;
	}

	private void setRotationAngles(float walkedDistance, float walkSpeed, float time, float yaw, float pitch, float scale, EntityUtahraptor utahraptor)
	{
		super.setRotationAngles(walkedDistance, walkSpeed, time, yaw, pitch, scale, utahraptor);

		if (utahraptor.isSitting())
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
				this.mainBody.rotationPointX -= bodyBob1;
				this.rightLeg1.rotationPointX -= bodyBob1;
				this.leftLeg1.rotationPointX -= bodyBob1;

				float bodyBob2 = this.getRotateAngle(time, walkSpeed, 0.75F, 1.0F);
				this.mainBody.rotationPointY -= bodyBob2;
				this.rightLeg1.rotationPointY -= bodyBob2;
				this.leftLeg1.rotationPointY -= bodyBob2;

				float armMovement = this.getRotateAngleComplex(time, walkSpeed, 0.375F, 1.0F, 0.5F);
				this.rightArm1.rotateAngleX += 0.3F * armMovement;
				this.rightArm2.rotateAngleX += 0.7F * armMovement;
				this.leftArm1.rotateAngleX -= 0.3F * armMovement;
				this.leftArm2.rotateAngleX -= 0.7F * armMovement;

				float lleg1 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float lleg2 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float lleg3 = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float lfoot = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

				float rleg1 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, 3.14159F, 0.2F);
				float rleg2 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.6F, 1.5F, 0.3F);
				float rleg3 = this.getRotateAngleComplexInclinated(time, walkSpeed, 0.375F, 0.8F, -1.0F, -0.1F);
				float rfoot = this.getRotateAngleComplexInclinatedInverted(time, walkSpeed, 0.375F, 1.5F, -1.0F, 1.0F);

				this.leftLeg1.rotationPointY -= 0.2F * walkSpeed;
				this.leftLeg1.rotateAngleX += lleg1;
				this.leftLeg2.rotateAngleX += lleg2;
				this.leftLeg3.rotateAngleX += lleg3;
				this.leftFoot.rotateAngleX += lfoot;

				this.rightLeg1.rotationPointY -= 0.2F * walkSpeed;
				this.rightLeg1.rotateAngleX += rleg1;
				this.rightLeg2.rotateAngleX += rleg2;
				this.rightLeg3.rotateAngleX += rleg3;
				this.rightFoot.rotateAngleX += rfoot;

				if (walkSpeed > 0.6F)
				{
					float[] naturalTailMovement = this.getChainMovement(time, walkSpeed, 5, 0.75F, 0.1F, -2.0F);
					this.tail1.rotateAngleX -= naturalTailMovement[0];
					this.tail2.rotateAngleX -= naturalTailMovement[1];
					this.tail3.rotateAngleX -= naturalTailMovement[2];
					this.tail4.rotateAngleX -= naturalTailMovement[3];
					this.tail5.rotateAngleX -= naturalTailMovement[4];

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
			this.animator.rotate(this.mainBody, -0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.bodyFront, -0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.leftArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.rightArm2, 0.3F, 0.0F, 0.0F);
			this.animator.rotate(this.tail1, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail2, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail3, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail4, 0.1F, 0.0F, 0.0F);
			this.animator.rotate(this.tail5, 0.1F, 0.0F, 0.0F);
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
		this.leftLeg1.resetParameters();
		this.rightLeg1.resetParameters();
		this.mainBody.resetParameters();
		this.leftLeg2.resetParameters();
		this.leftLeg3.resetParameters();
		this.leftFoot.resetParameters();
		this.rightLeg2.resetParameters();
		this.rightLeg3.resetParameters();
		this.rightFoot.resetParameters();
		this.butt.resetParameters();
		this.bodyFront.resetParameters();
		this.tail1.resetParameters();
		this.tail2.resetParameters();
		this.tail3.resetParameters();
		this.tail4.resetParameters();
		this.tail5.resetParameters();
		this.tailFan.resetParameters();
		this.chest.resetParameters();
		this.leftArm1.resetParameters();
		this.rightArm1.resetParameters();
		this.neck.resetParameters();
		this.shape17.resetParameters();
		this.head.resetParameters();
		this.leftEye.resetParameters();
		this.snout1.resetParameters();
		this.snouthTop.resetParameters();
		this.bristle1.resetParameters();
		this.bristle2.resetParameters();
		this.bristle3.resetParameters();
		this.mouth.resetParameters();
		this.rightEye.resetParameters();
		this.leftArm2.resetParameters();
		this.leftArmFeathers1.resetParameters();
		this.shape53.resetParameters();
		this.leftArmFeathers2.resetParameters();
		this.leftFinger3.resetParameters();
		this.leftFinger1.resetParameters();
		this.leftFinger2.resetParameters();
		this.rightArm2.resetParameters();
		this.rightArmFeathers1.resetParameters();
		this.shape53_1.resetParameters();
		this.rightArmFeathers2.resetParameters();
		this.rightFinger3.resetParameters();
		this.rightFinger1.resetParameters();
		this.rightFinger2.resetParameters();
	}

	public void setChildCharacteristics(byte growthStage)
	{
		switch (growthStage)
		{
			case 6:
			case 5:
			case 4:
				this.bristle1.showModel = true;
				this.bristle2.showModel = true;
				this.bristle3.showModel = true;
				this.bristle4.showModel = true;
				this.bristle5.showModel = true;
				this.bristle6.showModel = true;
				this.bristle7.showModel = true;
				this.bristle8.showModel = true;
				this.bristle9.showModel = true;
				this.leftArmFeathers1.showModel = true;
				this.leftArmFeathers2.showModel = true;
				this.rightArmFeathers1.showModel = true;
				this.rightArmFeathers2.showModel = true;
				this.tailFan.showModel = true;
				break;
			case 3:
			case 2:
				this.bristle1.showModel = true;
				this.bristle2.showModel = true;
				this.bristle3.showModel = true;
				this.bristle4.showModel = true;
				this.bristle5.showModel = true;
				this.bristle6.showModel = true;
				this.bristle7.showModel = true;
				this.bristle8.showModel = true;
				this.bristle9.showModel = true;
				this.leftArmFeathers1.showModel = false;
				this.leftArmFeathers2.showModel = false;
				this.rightArmFeathers1.showModel = false;
				this.rightArmFeathers2.showModel = false;
				this.tailFan.showModel = true;
				break;
			case 1:
			case 0:
				this.bristle1.showModel = false;
				this.bristle2.showModel = false;
				this.bristle3.showModel = false;
				this.bristle4.showModel = false;
				this.bristle5.showModel = false;
				this.bristle6.showModel = false;
				this.bristle7.showModel = false;
				this.bristle8.showModel = false;
				this.bristle9.showModel = false;
				this.leftArmFeathers1.showModel = false;
				this.leftArmFeathers2.showModel = false;
				this.rightArmFeathers1.showModel = false;
				this.rightArmFeathers2.showModel = false;
				this.tailFan.showModel = false;
				break;
		}
	}

	public void setRotateAngle(ResettableModelRenderer resettableModelRenderer, float x, float y, float z)
	{
		resettableModelRenderer.rotateAngleX = x;
		resettableModelRenderer.rotateAngleY = y;
		resettableModelRenderer.rotateAngleZ = z;
	}
}
