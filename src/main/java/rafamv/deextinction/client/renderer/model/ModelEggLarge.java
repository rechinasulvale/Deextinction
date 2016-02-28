package rafamv.deextinction.client.renderer.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.ResettableModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelEggLarge extends EggModelBase
{

	public ModelEggLarge()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.egg_1 = new ResettableModelRenderer(this, 0, 26);
		this.egg_1.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.egg_1.addBox(-2.5F, -0.5F, -2.5F, 5, 1, 5, 0.0F);
		this.egg_2 = new ResettableModelRenderer(this, 0, 13);
		this.egg_2.setRotationPoint(0.0F, -2.75F, 0.0F);
		this.egg_2.addBox(-3.0F, -3.5F, -3.0F, 6, 6, 6, 0.0F);
		this.egg_4 = new ResettableModelRenderer(this, 0, 0);
		this.egg_4.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.egg_4.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3, 0.0F);
		this.egg_3 = new ResettableModelRenderer(this, 0, 5);
		this.egg_3.setRotationPoint(0.0F, -3.75F, 0.0F);
		this.egg_3.addBox(-2.5F, -1.5F, -2.5F, 5, 2, 5, 0.0F);
		this.egg_1.addChild(this.egg_2);
		this.egg_3.addChild(this.egg_4);
		this.egg_2.addChild(this.egg_3);
		this.egg_1.saveParameters();
		this.egg_2.saveParameters();
		this.egg_3.saveParameters();
		this.egg_4.saveParameters();
	}
}
