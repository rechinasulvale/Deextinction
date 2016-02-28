package rafamv.deextinction.client.renderer.entity.eggnests;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.model.ModelEggNestSmall;
import rafamv.deextinction.client.renderer.model.ModelEggSmall;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;

@SideOnly(Side.CLIENT)
public class RenderEntityEggNestSmall extends Render
{
	private static final ResourceLocation texture = new ResourceLocation(DeExtinction.MODID, "textures/entities/eggnests/entity_egg_nest_small_texture.png");
	private static final ModelEggNestSmall mainModel = new ModelEggNestSmall();

	private static final ResourceLocation textureEggDefault = new ResourceLocation(DeExtinction.MODID, "textures/entities/entity_egg_small_default_texture.png");
	private static final ModelEggSmall modelEggDefault = new ModelEggSmall();

	public RenderEntityEggNestSmall()
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
			GlStateManager.translate((float) x, ((float) y + 1.86F), (float) z);
			GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180.0F, 1, 0, 0);
			GlStateManager.scale(1.25F, 1.25F, 1.25F);
			this.bindEntityTexture(eggNest);
			RenderEntityEggNestSmall.mainModel.render(eggNest, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.3F, 1.35F, -0.05F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0))
			{
				this.bindTexture(eggNest.getEggTexture(0));
				eggNest.getEggModel(0).render(eggNest, 0.0625F, 0);
			}
			GlStateManager.translate(-0.5F, 0.0F, -0.15F);
			if (eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1))
			{
				this.bindTexture(eggNest.getEggTexture(1));
				eggNest.getEggModel(1).render(eggNest, 0.0625F, 1);
			}
			GlStateManager.translate(0.125F, 0.0F, 0.45F);
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
		return RenderEntityEggNestSmall.texture;
	}
}
