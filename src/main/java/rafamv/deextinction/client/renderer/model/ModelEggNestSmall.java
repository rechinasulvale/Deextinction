package rafamv.deextinction.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

@SideOnly(Side.CLIENT)
public class ModelEggNestSmall extends ModelBase
{
	public ModelRenderer base0;
	public ModelRenderer base1;
	public ModelRenderer base2;
	public ModelRenderer base3;
	public ModelRenderer base4;
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
	public ModelRenderer stick15;
	public ModelRenderer stick16;
	public ModelRenderer stick17;
	public ModelRenderer stick18;
	public ModelRenderer stick19;
	public ModelRenderer leaves1;
	public ModelRenderer leaves2;
	public ModelRenderer leaves3;
	public ModelRenderer leaves4;
	public ModelRenderer leaves5;
	public ModelRenderer leaves6;

	public ModelEggNestSmall()
	{
		this.textureWidth = 28;
		this.textureHeight = 16;
		this.leaves1 = new ModelRenderer(this, 18, 11);
		this.leaves1.setRotationPoint(-3.0F, 21.7F, -6.6F);
		this.leaves1.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves1, 0.3028146252210162F, 0.0F, 0.0F);
		this.base3 = new ModelRenderer(this, 0, 0);
		this.base3.setRotationPoint(6.0F, 0.45F, 1.0F);
		this.base3.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(base3, 0.0F, 0.0F, -0.6995279641993272F);
		this.base4 = new ModelRenderer(this, 0, 0);
		this.base4.setRotationPoint(-0.8F, -0.9F, 1.0F);
		this.base4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(base4, 0.0F, 0.0F, 0.6995279641993272F);
		this.leaves3 = new ModelRenderer(this, 18, 11);
		this.leaves3.setRotationPoint(-7.6F, 22.6F, -1.1F);
		this.leaves3.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves3, 0.27523842303950574F, 1.2887511196726131F, -0.2701769682087222F);
		this.leaves4 = new ModelRenderer(this, 18, 11);
		this.leaves4.setRotationPoint(-2.6F, 22.2F, 6.4F);
		this.leaves4.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves4, 0.3028146252210162F, 2.302612882156119F, -0.19949113350295186F);
		this.leaves2 = new ModelRenderer(this, 18, 11);
		this.leaves2.setRotationPoint(5.0F, 22.3F, -4.8F);
		this.leaves2.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves2, 0.5510004448546099F, -0.8093091741497707F, 0.10419615634406146F);
		this.stick8 = new ModelRenderer(this, 0, 0);
		this.stick8.setRotationPoint(3.0F, -1.0F, 8.0F);
		this.stick8.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick8, 0.2748893571891069F, 0.5005604294719737F, 0.0F);
		this.stick2 = new ModelRenderer(this, 0, 0);
		this.stick2.setRotationPoint(1.0F, -1.0F, -2.0F);
		this.stick2.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick2, 0.2748893571891069F, -0.060562925044203235F, 0.0F);
		this.stick3 = new ModelRenderer(this, 0, 0);
		this.stick3.setRotationPoint(4.4F, -1.0F, -2.0F);
		this.stick3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick3, 0.2748893571891069F, -0.6597344572538565F, 0.0F);
		this.stick5 = new ModelRenderer(this, 0, 0);
		this.stick5.setRotationPoint(6.5F, -1.0F, 7.0F);
		this.stick5.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick5, 0.2748893571891069F, 1.2369148408883814F, 0.0F);
		this.base0 = new ModelRenderer(this, 0, 0);
		this.base0.setRotationPoint(-3.3F, 22.5F, -3.3F);
		this.base0.addBox(0.0F, 0.0F, 0.0F, 7, 2, 7, 0.0F);
		this.base2 = new ModelRenderer(this, 0, 0);
		this.base2.setRotationPoint(1.0F, -1.0F, 6.5F);
		this.base2.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2, 0.0F);
		this.setRotateAngle(base2, -0.6995279641993272F, 0.0F, 0.0F);
		this.stick4 = new ModelRenderer(this, 0, 0);
		this.stick4.setRotationPoint(8.0F, -1.0F, 1.0F);
		this.stick4.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick4, 0.2748893571891069F, -1.1929325437381242F, 0.0F);
		this.stick1 = new ModelRenderer(this, 0, 0);
		this.stick1.setRotationPoint(-1.0F, -1.0F, -1.0F);
		this.stick1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick1, 0.2748893571891069F, 0.2802998778702893F, 0.0F);
		this.stick16 = new ModelRenderer(this, 0, 12);
		this.stick16.setRotationPoint(-4.0F, 0.0F, 4.0F);
		this.stick16.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick16, 0.0F, 1.0901326507956584F, 0.0F);
		this.stick14 = new ModelRenderer(this, 0, 12);
		this.stick14.setRotationPoint(12.0F, -0.6F, 2.6F);
		this.stick14.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick14, 0.0F, -2.151118303083011F, 0.0F);
		this.leaves5 = new ModelRenderer(this, 18, 11);
		this.leaves5.setRotationPoint(7.0F, 21.7F, 1.4F);
		this.leaves5.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves5, 0.3028146252210162F, -1.8449875522832058F, 0.0F);
		this.leaves6 = new ModelRenderer(this, 18, 11);
		this.leaves6.setRotationPoint(-0.5F, 21.3F, 4.4F);
		this.leaves6.addBox(0.0F, 0.0F, 0.0F, 4, 0, 2, 0.0F);
		this.setRotateAngle(leaves6, -0.5012585611727715F, 0.0F, 0.0F);
		this.stick9 = new ModelRenderer(this, 0, 0);
		this.stick9.setRotationPoint(1.0F, -1.0F, 7.3F);
		this.stick9.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick9, 0.2748893571891069F, 0.10629055144645466F, 0.0F);
		this.stick10 = new ModelRenderer(this, 0, 0);
		this.stick10.setRotationPoint(-0.5F, -1.0F, 5.5F);
		this.stick10.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick10, 0.2748893571891069F, -0.4735078260660616F, 0.0F);
		this.stick13 = new ModelRenderer(this, 0, 12);
		this.stick13.setRotationPoint(7.0F, 0.4F, -4.0F);
		this.stick13.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick13, 0.0F, -1.1074114103904023F, 0.0F);
		this.stick6 = new ModelRenderer(this, 0, 0);
		this.stick6.setRotationPoint(-3.0F, -1.0F, 1.0F);
		this.stick6.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick6, 0.2748893571891069F, 0.4836307357276287F, 0.0F);
		this.base1 = new ModelRenderer(this, 0, 0);
		this.base1.setRotationPoint(1.0F, 0.4F, -1.5F);
		this.base1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 2, 0.0F);
		this.setRotateAngle(base1, 0.6995279641993272F, 0.0F, 0.0F);
		this.stick15 = new ModelRenderer(this, 0, 12);
		this.stick15.setRotationPoint(-4.0F, -0.7F, -1.0F);
		this.stick15.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick15, 0.0F, 0.39444441095071847F, 0.0F);
		this.stick7 = new ModelRenderer(this, 0, 0);
		this.stick7.setRotationPoint(-3.0F, -1.0F, 4.0F);
		this.stick7.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick7, 0.2748893571891069F, 1.0993828958312282F, 0.0F);
		this.stick17 = new ModelRenderer(this, 0, 12);
		this.stick17.setRotationPoint(0.1F, 0.0F, 8.4F);
		this.stick17.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick17, 0.0F, 0.19146261894377795F, 0.0F);
		this.stick12 = new ModelRenderer(this, 0, 13);
		this.stick12.setRotationPoint(2.0F, 0.0F, -3.0F);
		this.stick12.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick12, 0.0F, -0.16807520696705394F, 0.0F);
		this.stick18 = new ModelRenderer(this, 0, 12);
		this.stick18.setRotationPoint(-4.0F, 0.4F, 2.0F);
		this.stick18.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick18, 0.0F, -0.8962265708990883F, 0.0F);
		this.stick11 = new ModelRenderer(this, 0, 0);
		this.stick11.setRotationPoint(-2.0F, -1.0F, 3.0F);
		this.stick11.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.setRotateAngle(stick11, 0.2748893571891069F, -0.7808603073422631F, 0.0F);
		this.stick19 = new ModelRenderer(this, 0, 12);
		this.stick19.setRotationPoint(-3.0F, 0.1F, 5.0F);
		this.stick19.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.setRotateAngle(stick19, 0.0F, -0.3965388060531116F, 0.0F);

		this.base0.addChild(this.base2);
		this.base0.addChild(this.base1);
		this.base0.addChild(this.base3);
		this.base0.addChild(this.base4);
		this.base0.addChild(this.stick1);
		this.base0.addChild(this.stick2);
		this.base0.addChild(this.stick3);
		this.base0.addChild(this.stick4);
		this.base0.addChild(this.stick5);
		this.base0.addChild(this.stick6);
		this.base0.addChild(this.stick7);
		this.base0.addChild(this.stick8);
		this.base0.addChild(this.stick9);
		this.base0.addChild(this.stick10);
		this.base0.addChild(this.stick11);
		this.base0.addChild(this.stick12);
		this.base0.addChild(this.stick13);
		this.base0.addChild(this.stick14);
		this.base0.addChild(this.stick15);
		this.base0.addChild(this.stick16);
		this.base0.addChild(this.stick17);
		this.base0.addChild(this.stick18);
		this.base0.addChild(this.stick19);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		this.base0.render(f5);

		if (((EntityEggNest) entity).getWatchableBoolean(EntityEggNestKeys.FLAG_FOLIAGE))
		{
			this.leaves1.render(f5);
			this.leaves2.render(f5);
			this.leaves3.render(f5);
			this.leaves4.render(f5);
			this.leaves5.render(f5);
			this.leaves6.render(f5);
		}
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
