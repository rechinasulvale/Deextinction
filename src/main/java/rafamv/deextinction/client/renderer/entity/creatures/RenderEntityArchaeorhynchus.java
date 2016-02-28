package rafamv.deextinction.client.renderer.entity.creatures;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.client.renderer.model.creatures.ModelArchaeorhynchus;
import rafamv.deextinction.common.entity.creature.EntityArchaeorhynchus;

@SideOnly(Side.CLIENT)
public class RenderEntityArchaeorhynchus extends RenderLiving
{

	public RenderEntityArchaeorhynchus()
	{
		super(Minecraft.getMinecraft().getRenderManager(), null, 0.3F);
		this.mainModel = new ModelArchaeorhynchus();
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLivingBase, float partialTickTime)
	{
		this.applyModelScale((EntityArchaeorhynchus) entityLivingBase);
	}

	public void applyModelScale(EntityArchaeorhynchus creature)
	{
		float scale = creature.getModelScale();
		this.shadowSize = 0.3F * scale;
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return this.getEntityTexture((EntityArchaeorhynchus) entity);
	}

	protected ResourceLocation getEntityTexture(EntityArchaeorhynchus creature)
	{
		return creature.getTexture();
	}
}
