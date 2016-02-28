package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelKelenken;
import rafamv.deextinction.common.entity.creature.EntityKelenken;

@SideOnly(Side.CLIENT)
public class RenderEntityKelenken extends RenderLiving
{

	public RenderEntityKelenken()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.8F);
		this.mainModel = new ModelKelenken();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityKelenken) entityLivingBase);
	}

	public void applyModelScale(EntityKelenken kelenken)
	{
		float scale = kelenken.getModelScale();
		this.shadowSize = 0.8F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityKelenken) entity);
	}

	protected ResourceLocation getEntityTexture(EntityKelenken kelenken)
	{
		return kelenken.getTexture();
	}
}
