package rafamv.deextinction.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelEggNestTiny extends ModelBase
{
	public ModelRenderer base;
	public ModelRenderer stick1;
	public ModelRenderer stick2;
	public ModelRenderer stick3;
	public ModelRenderer stick4;
	public ModelRenderer stick5;
	public ModelRenderer stick6;
	public ModelRenderer stick7;
	public ModelRenderer stick8;
	public ModelRenderer stick9;
	public ModelRenderer stick10;
	public ModelRenderer stick11;
	public ModelRenderer stick12;
	public ModelRenderer stick13;
	public ModelRenderer stick14;
	public ModelRenderer baseSide1;
	public ModelRenderer baseSide2;
	public ModelRenderer baseSide3;
	public ModelRenderer baseSide4;

	public ModelEggNestTiny()
	{
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.stick7 = new ModelRenderer(this, 0, 0);
		this.stick7.setRotationPoint(5.3F, -1.0F, 0.0F);
		this.stick7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick7, 0.0F, -0.32637657012293964F, 0.0F);
		this.stick14 = new ModelRenderer(this, 0, 0);
		this.stick14.setRotationPoint(5.3F, -1.3F, 1.0F);
		this.stick14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(stick14, -0.07312929565856241F, -0.4038691889114879F, 0.6897541203881591F);
		this.baseSide2 = new ModelRenderer(this, 0, 0);
		this.baseSide2.setRotationPoint(4.6F, -0.4F, 0.0F);
		this.baseSide2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(baseSide2, 0.0F, 0.0F, 0.42394047530942264F);
		this.stick1 = new ModelRenderer(this, 0, 0);
		this.stick1.setRotationPoint(0.3F, -1.0F, -1.0F);
		this.stick1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick1, 0.0F, -0.4614650542273008F, 0.0F);
		this.baseSide1 = new ModelRenderer(this, 0, 0);
		this.baseSide1.setRotationPoint(-0.4F, 0.0F, 0.0F);
		this.baseSide1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 5, 0.0F);
		this.setRotateAngle(baseSide1, 0.0F, 0.0F, -0.42394047530942264F);
		this.stick10 = new ModelRenderer(this, 0, 0);
		this.stick10.setRotationPoint(0.3F, -1.0F, 0.0F);
		this.stick10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(stick10, 0.091106186954104F, -0.7285004297824331F, 0.6897541203881591F);
		this.stick11 = new ModelRenderer(this, 0, 0);
		this.stick11.setRotationPoint(3.3F, -1.0F, -1.0F);
		this.stick11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(stick11, -0.136659280431156F, -1.2292353921796064F, 0.6897541203881591F);
		this.stick3 = new ModelRenderer(this, 0, 0);
		this.stick3.setRotationPoint(2.4F, -1.0F, -0.6F);
		this.stick3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick3, 0.0F, -1.2583823906879115F, 0.0F);
		this.stick4 = new ModelRenderer(this, 0, 0);
		this.stick4.setRotationPoint(-1.0F, -1.0F, 4.0F);
		this.stick4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick4, 0.0F, 0.8295549934729047F, 0.0F);
		this.stick5 = new ModelRenderer(this, 0, 0);
		this.stick5.setRotationPoint(2.5F, -1.05F, 4.1F);
		this.stick5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(stick5, 0.0F, -1.0161306905110985F, 0.0F);
		this.stick12 = new ModelRenderer(this, 0, 0);
		this.stick12.setRotationPoint(0.3F, -1.0F, 4.0F);
		this.stick12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(stick12, 0.091106186954104F, -1.2292353921796064F, 0.6897541203881591F);
		this.stick8 = new ModelRenderer(this, 0, 0);
		this.stick8.setRotationPoint(5.3F, -0.9F, 3.0F);
		this.stick8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick8, 0.0F, -0.9927432785343746F, 0.0F);
		this.stick6 = new ModelRenderer(this, 0, 0);
		this.stick6.setRotationPoint(2.3F, -1.05F, -1.0F);
		this.stick6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick6, 0.0F, 0.7595672904679323F, 0.0F);
		this.baseSide4 = new ModelRenderer(this, 0, 0);
		this.baseSide4.setRotationPoint(0.0F, 0.0F, -0.4F);
		this.baseSide4.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(baseSide4, 0.4221951460574283F, 0.0F, 0.0F);
		this.base = new ModelRenderer(this, 4, 5);
		this.base.setRotationPoint(-2.5F, 23.0F, -2.5F);
		this.base.addBox(0.5F, 0.0F, 0.5F, 4, 1, 4, 0.0F);
		this.stick9 = new ModelRenderer(this, 0, 0);
		this.stick9.setRotationPoint(5.3F, -1.0F, -1.0F);
		this.stick9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick9, 0.0F, -1.5659094048893123F, 0.0F);
		this.stick2 = new ModelRenderer(this, 0, 0);
		this.stick2.setRotationPoint(-0.7F, -1.0F, 2.0F);
		this.stick2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(stick2, -0.08848819307611251F, 0.13491395117916166F, 0.0F);
		this.baseSide3 = new ModelRenderer(this, 0, 0);
		this.baseSide3.setRotationPoint(0.0F, -0.2F, 4.4F);
		this.baseSide3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(baseSide3, -0.4221951460574283F, 0.0F, 0.0F);
		this.stick13 = new ModelRenderer(this, 0, 0);
		this.stick13.setRotationPoint(3.3F, -1.0F, 5.0F);
		this.stick13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(stick13, 0.091106186954104F, -1.2292353921796064F, 0.3530801076784528F);
		this.base.addChild(this.stick7);
		this.base.addChild(this.stick14);
		this.base.addChild(this.baseSide2);
		this.base.addChild(this.stick1);
		this.base.addChild(this.baseSide1);
		this.base.addChild(this.stick10);
		this.base.addChild(this.stick11);
		this.base.addChild(this.stick3);
		this.base.addChild(this.stick4);
		this.base.addChild(this.stick5);
		this.base.addChild(this.stick12);
		this.base.addChild(this.stick8);
		this.base.addChild(this.stick6);
		this.base.addChild(this.baseSide4);
		this.base.addChild(this.stick9);
		this.base.addChild(this.stick2);
		this.base.addChild(this.baseSide3);
		this.base.addChild(this.stick13);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.base.render(f5);
		this.stick8.isHidden = true;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
