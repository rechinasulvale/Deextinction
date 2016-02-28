package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelTroodon;
import rafamv.deextinction.common.entity.creature.EntityTroodon;

@SideOnly(Side.CLIENT)
public class RenderEntityTroodon extends RenderLiving
{

	public RenderEntityTroodon()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.85F);
		this.mainModel = new ModelTroodon();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityTroodon) entityLivingBase);
	}

	public void applyModelScale(EntityTroodon creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 0.85F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityTroodon) entity);
	}

	protected ResourceLocation getEntityTexture(EntityTroodon creature)
	{
		return creature.getTexture();
	}
}
