package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelZhenyuanlong;
import rafamv.deextinction.common.entity.creature.EntityZhenyuanlong;

@SideOnly(Side.CLIENT)
public class RenderEntityZhenyuanlong extends RenderLiving
{

	public RenderEntityZhenyuanlong()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.85F);
		this.mainModel = new ModelZhenyuanlong();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityZhenyuanlong) entityLivingBase);
	}

	public void applyModelScale(EntityZhenyuanlong creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 0.85F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityZhenyuanlong) entity);
	}

	protected ResourceLocation getEntityTexture(EntityZhenyuanlong creature)
	{
		return creature.getTexture();
	}
}
