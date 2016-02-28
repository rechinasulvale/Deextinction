package rafamv.deextinction.client.renderer.entity.eggnests;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.model.ModelEggMedium;
import rafamv.deextinction.client.renderer.model.ModelEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

@SideOnly(Side.CLIENT)
public class RenderEntityEggNestLarge extends Render
{
	private static final ResourceLocation texture = new ResourceLocation(DeExtinction.MODID, "textures/entities/entity_egg_nest_texture.png");
	private static final ModelEggNest mainModel = new ModelEggNest();

	private static final ResourceLocation textureEggDefault = new ResourceLocation(DeExtinction.MODID, "textures/entities/entity_egg_medium_default_texture.png");
	private static final ModelEggMedium modelEggDefault = new ModelEggMedium();

	public RenderEntityEggNestLarge()
	{
		super(Minecraft.getMinecraft().getRenderManager());
		this.shadowSize = 0.0F;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTicks)
	{
		this.renderEggNest((EntityEggNest) entity, x, y, z, rotationYaw, partialTicks);
	}

	private void renderEggNest(EntityEggNest eggNest, double x, double y, double z, float rotationYaw, float partialTicks)
	{
		if (!eggNest.isDead)
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate((float) x, ((float) y + 1.8F), (float) z);
			GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 1, 0, 0);
			GlStateManager.scale(1.2F, 1.2F, 1.2F);
			this.bindEntityTexture(eggNest);
			RenderEntityEggNestLarge.mainModel.render(eggNest, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GlStateManager.translate(0.3F, 0.0F, -0.1F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0))
			{
				this.bindTexture(eggNest.getEggTexture(0));
				eggNest.getEggModel(0).render(eggNest, 0.0625F, 0);
			}
			GlStateManager.translate(-0.5F, 0.0F, 0.2F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1))
			{
				this.bindTexture(eggNest.getEggTexture(1));
				eggNest.getEggModel(1).render(eggNest, 0.0625F, 1);
			}
			GlStateManager.popMatrix();
			super.doRender(eggNest, x, y, z, rotationYaw, partialTicks);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return RenderEntityEggNestLarge.texture;
	}
}
