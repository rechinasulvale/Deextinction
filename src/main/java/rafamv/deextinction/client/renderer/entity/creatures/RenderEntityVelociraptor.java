package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelVelociraptor;
import rafamv.deextinction.common.entity.creature.EntityVelociraptor;

@SideOnly(Side.CLIENT)
public class RenderEntityVelociraptor extends RenderLiving
{

	public RenderEntityVelociraptor()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.85F);
		this.mainModel = new ModelVelociraptor();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityVelociraptor) entityLivingBase);
	}

	public void applyModelScale(EntityVelociraptor creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 0.85F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityVelociraptor) entity);
	}

	protected ResourceLocation getEntityTexture(EntityVelociraptor creature)
	{
		return creature.getTexture();
	}
}
