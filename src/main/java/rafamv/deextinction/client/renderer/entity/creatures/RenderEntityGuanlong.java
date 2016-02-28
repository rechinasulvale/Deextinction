package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelGuanlong;
import rafamv.deextinction.common.entity.creature.EntityGuanlong;

@SideOnly(Side.CLIENT)
public class RenderEntityGuanlong extends RenderLiving
{

	public RenderEntityGuanlong()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.85F);
		this.mainModel = new ModelGuanlong();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityGuanlong) entityLivingBase);
	}

	public void applyModelScale(EntityGuanlong creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 0.85F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityGuanlong) entity);
	}

	protected ResourceLocation getEntityTexture(EntityGuanlong creature)
	{
		return creature.getTexture();
	}
}
