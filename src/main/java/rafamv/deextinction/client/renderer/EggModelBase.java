package rafamv.deextinction.client.renderer;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;

@SideOnly(Side.CLIENT)
public class EggModelBase extends ResettableModelBase
{
	protected ResettableModelRenderer egg_1;
	protected ResettableModelRenderer egg_2;
	protected ResettableModelRenderer egg_3;
	protected ResettableModelRenderer egg_4;

	public void render(Entity entity, float f5, int slot)
	{
		this.setRotationAngles((EntityEggNest) entity, slot);
		this.egg_1.render(f5);
	}

	protected void setRotationAngles(EntityEggNest entity, int slot)
	{
		this.resetPose();
		float egg_movement = entity.getEggAnimationProgress(slot);
		float shake = egg_movement * egg_movement - egg_movement;

		this.egg_1.rotationPointY += 3.5F * shake;
		this.egg_1.rotateAngleY += 1.0F * egg_movement;
		this.egg_1.rotateAngleZ -= 0.6F * shake;
	}

	protected void resetPose()
	{
		this.egg_1.resetParameters();
		this.egg_2.resetParameters();
		this.egg_3.resetParameters();
		this.egg_4.resetParameters();
	}

	protected void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
