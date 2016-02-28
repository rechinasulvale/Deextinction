package rafamv.deextinction.client.renderer.entity.eggnests;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.model.ModelEggLarge;
import rafamv.deextinction.client.renderer.model.ModelEggNestTiny;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

@SideOnly(Side.CLIENT)
public class RenderEntityEggNestTiny extends Render
{
	private static final ResourceLocation texture = new ResourceLocation(DeExtinction.MODID, "textures/entities/eggnests/entity_egg_nest_tiny_texture.png");
	private static final ModelEggNestTiny mainModel = new ModelEggNestTiny();

	private static final ResourceLocation textureEggDefault = new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_large_default_texture.png");
	private static final ModelEggLarge modelEggDefault = new ModelEggLarge();

	public RenderEntityEggNestTiny()
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
			GlStateManager.translate((float) x, ((float) y + 1.775F), (float) z);
			GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.scale(1.2F, 1.2F, 1.2F);
			this.bindEntityTexture(eggNest);
			RenderEntityEggNestTiny.mainModel.render(eggNest, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GlStateManager.scale(0.3F, 0.3F, 0.3F);
			GlStateManager.translate(0.2F, 3.3F, 0.0F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0))
			{
				this.bindTexture(eggNest.getEggTexture(0));
				eggNest.getEggModel(0).render(eggNest, 0.0625F, 0);
			}
			GlStateManager.translate(-0.35F, 0.0F, -0.2F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1))
			{
				this.bindTexture(eggNest.getEggTexture(1));
				eggNest.getEggModel(1).render(eggNest, 0.0625F, 1);
			}
			GlStateManager.translate(-0.05F, 0.0F, 0.4F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2))
			{
				this.bindTexture(eggNest.getEggTexture(2));
				eggNest.getEggModel(2).render(eggNest, 0.0625F, 2);
			}
			GlStateManager.popMatrix();
			super.doRender(eggNest, x, y, z, rotationYaw, partialTicks);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return RenderEntityEggNestTiny.texture;
	}
}
