package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelDinornis;
import rafamv.deextinction.common.entity.creature.EntityDinornis;

@SideOnly(Side.CLIENT)
public class RenderEntityDinornis extends RenderLiving
{

	public RenderEntityDinornis()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.0F);
		this.mainModel = new ModelDinornis();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityDinornis) entityLivingBase);
	}

	public void applyModelScale(EntityDinornis creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 1.2F * scale;
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityDinornis) entity);
	}

	protected ResourceLocation getEntityTexture(EntityDinornis creature)
	{
		return creature.getTexture();
	}
}
