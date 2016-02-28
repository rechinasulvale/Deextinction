package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelRaphus;
import rafamv.deextinction.common.entity.creature.EntityRaphus;

@SideOnly(Side.CLIENT)
public class RenderEntityRaphus extends RenderLiving
{

	public RenderEntityRaphus()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.7F);
		this.mainModel = new ModelRaphus();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityRaphus) entityLivingBase);
	}

	public void applyModelScale(EntityRaphus kelenken)
	{
		float scale = kelenken.getModelScale();
		this.shadowSize = 0.7F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityRaphus) entity);
	}

	protected ResourceLocation getEntityTexture(EntityRaphus kelenken)
	{
		return kelenken.getTexture();
	}
}
