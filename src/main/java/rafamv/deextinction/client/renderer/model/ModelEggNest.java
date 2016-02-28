package rafamv.deextinction.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

@SideOnly(Side.CLIENT)
public class ModelEggNest extends ModelBase
{
	public ModelRenderer stick_1;
	public ModelRenderer stick_2;
	public ModelRenderer stick_3;
	public ModelRenderer stick_4;
	public ModelRenderer stick_5;
	public ModelRenderer stick_6;
	public ModelRenderer stick_7;
	public ModelRenderer stick_8;
	public ModelRenderer stick_9;
	public ModelRenderer stick_10;
	public ModelRenderer stick_11;
	public ModelRenderer stick_12;
	public ModelRenderer stick_13;
	public ModelRenderer leaves_1;
	public ModelRenderer leaves_2;
	public ModelRenderer leaves_3;
	public ModelRenderer leaves_4;
	public ModelRenderer leaves_5;
	public ModelRenderer branches_1;
	public ModelRenderer branches_2;
	public ModelRenderer branches_3;

	public ModelEggNest()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.stick_3 = new ModelRenderer(this, 0, 2);
		this.stick_3.setRotationPoint(-9.0F, 22.5F, 2.0F);
		this.stick_3.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_3, 0.0F, -0.6108652381980153F, -0.148352986419518F);
		this.branches_2 = new ModelRenderer(this, 16, 6);
		this.branches_2.setRotationPoint(0.0F, 23.75F, -5.0F);
		this.branches_2.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(branches_2, 1.5707963267948966F, 0.8203047484373349F, 0.0F);
		this.stick_1 = new ModelRenderer(this, 0, 2);
		this.stick_1.setRotationPoint(-11.0F, 23.5F, 0.0F);
		this.stick_1.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_1, 0.0F, 0.890117918517108F, 0.0F);
		this.leaves_5 = new ModelRenderer(this, 0, 6);
		this.leaves_5.setRotationPoint(5.0F, 22.5F, -2.5F);
		this.leaves_5.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(leaves_5, 1.7453292519943295F, 2.5656340004316642F, -0.20943951023931953F);
		this.stick_5 = new ModelRenderer(this, 0, 0);
		this.stick_5.setRotationPoint(-4.0F, 22.0F, -11.0F);
		this.stick_5.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_5, 0.0F, -0.5235987755982988F, -0.12217304763960307F);
		this.stick_7 = new ModelRenderer(this, 0, 2);
		this.stick_7.setRotationPoint(6.0F, 22.5F, -7.0F);
		this.stick_7.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_7, 0.0F, -1.2217304763960306F, 0.0F);
		this.leaves_3 = new ModelRenderer(this, 0, 6);
		this.leaves_3.setRotationPoint(-3.0F, 22.5F, 3.0F);
		this.leaves_3.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(leaves_3, 1.7104226669544427F, 0.24434609527920614F, 0.296705972839036F);
		this.stick_12 = new ModelRenderer(this, 0, 2);
		this.stick_12.setRotationPoint(0.0F, 23.5F, 11.0F);
		this.stick_12.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_12, 0.0F, 0.6632251157578453F, 0.0F);
		this.stick_2 = new ModelRenderer(this, 0, 2);
		this.stick_2.setRotationPoint(-7.5F, 21.25F, 6.0F);
		this.stick_2.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_2, 0.08726646259971647F, 1.3962634015954636F, 0.08726646259971647F);
		this.stick_4 = new ModelRenderer(this, 0, 0);
		this.stick_4.setRotationPoint(0.0F, 22.0F, 10.0F);
		this.stick_4.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_4, 0.0F, 0.6981317007977318F, -0.05235987755982988F);
		this.branches_1 = new ModelRenderer(this, 16, 6);
		this.branches_1.setRotationPoint(-2.0F, 23.75F, 2.0F);
		this.branches_1.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(branches_1, 1.5707963267948966F, -0.2617993877991494F, 0.0F);
		this.leaves_1 = new ModelRenderer(this, 0, 6);
		this.leaves_1.setRotationPoint(0.0F, 23.5F, 0.0F);
		this.leaves_1.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(leaves_1, 1.5707963267948966F, 0.0F, 0.0F);
		this.stick_10 = new ModelRenderer(this, 0, 2);
		this.stick_10.setRotationPoint(-9.0F, 22.5F, -6.0F);
		this.stick_10.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_10, 0.0F, 0.2792526803190927F, 0.0F);
		this.leaves_4 = new ModelRenderer(this, 0, 6);
		this.leaves_4.setRotationPoint(5.0F, 22.5F, 3.0F);
		this.leaves_4.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(leaves_4, 1.7104226669544427F, -0.06981317007977318F, -0.2617993877991494F);
		this.branches_3 = new ModelRenderer(this, 16, 6);
		this.branches_3.setRotationPoint(4.0F, 23.75F, 1.0F);
		this.branches_3.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(branches_3, 1.5707963267948966F, -1.8325957145940461F, 0.0F);
		this.stick_6 = new ModelRenderer(this, 0, 4);
		this.stick_6.setRotationPoint(10.0F, 21.25F, -6.0F);
		this.stick_6.addBox(-15.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_6, 0.0F, -0.20943951023931953F, -0.10471975511965977F);
		this.stick_8 = new ModelRenderer(this, 0, 4);
		this.stick_8.setRotationPoint(-4.0F, 23.5F, 5.0F);
		this.stick_8.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_8, 0.40142572795869574F, 1.9547687622336491F, 0.40142572795869574F);
		this.leaves_2 = new ModelRenderer(this, 0, 6);
		this.leaves_2.setRotationPoint(-2.0F, 22.5F, -5.0F);
		this.leaves_2.addBox(-4.0F, -4.0F, 0.0F, 8, 8, 0, 0.0F);
		this.setRotateAngle(leaves_2, 1.3089969389957472F, 0.41887902047863906F, 0.0F);
		this.stick_9 = new ModelRenderer(this, 0, 4);
		this.stick_9.setRotationPoint(12.0F, 23.5F, 5.0F);
		this.stick_9.addBox(-15.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_9, 0.0F, 0.2617993877991494F, 0.10471975511965977F);
		this.stick_11 = new ModelRenderer(this, 0, 0);
		this.stick_11.setRotationPoint(-9.0F, 23.5F, 0.0F);
		this.stick_11.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_11, 0.0F, -0.9948376736367678F, 0.0F);
		this.stick_13 = new ModelRenderer(this, 0, 0);
		this.stick_13.setRotationPoint(0.0F, 23.5F, -11.0F);
		this.stick_13.addBox(-0.5F, -0.5F, -0.5F, 16, 1, 1, 0.0F);
		this.setRotateAngle(stick_13, 0.0F, -0.8028514559173915F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.stick_1.render(f5);
		this.stick_2.render(f5);
		this.stick_3.render(f5);
		this.stick_4.render(f5);
		this.stick_5.render(f5);
		this.stick_6.render(f5);
		this.stick_7.render(f5);
		this.stick_8.render(f5);
		this.stick_9.render(f5);
		this.stick_10.render(f5);
		this.stick_11.render(f5);
		this.stick_12.render(f5);
		this.stick_13.render(f5);
		this.branches_1.render(f5);
		this.branches_2.render(f5);
		this.branches_3.render(f5);
		if (((EntityEggNest) entity).getWatchableBoolean(EntityEggNestKeys.FLAG_FOLIAGE))
		{
			this.leaves_1.render(f5);
			this.leaves_3.render(f5);
			this.leaves_2.render(f5);
			this.leaves_4.render(f5);
			this.leaves_5.render(f5);
		}
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
