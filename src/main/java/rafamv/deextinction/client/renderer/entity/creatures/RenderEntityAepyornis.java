package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelAepyornis;
import rafamv.deextinction.common.entity.creature.EntityAepyornis;

@SideOnly(Side.CLIENT)
public class RenderEntityAepyornis extends RenderLiving
{

	public RenderEntityAepyornis()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.0F);
		this.mainModel = new ModelAepyornis();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityAepyornis) entityLivingBase);
	}

	public void applyModelScale(EntityAepyornis creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 1.2F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityAepyornis) entity);
	}

	protected ResourceLocation getEntityTexture(EntityAepyornis creature)
	{
		return creature.getTexture();
	}
}
