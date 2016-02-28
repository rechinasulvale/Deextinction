package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiImageToggleButton extends GuiButton
{
	/** State of the button. */
	protected boolean state;
	/**
	 * This is the texture where the button is. It should be in the following
	 * vertical order: normal, selected, disabled.
	 */
	protected ResourceLocation guiTextureWidgets;
	/**
	 * This is the x position of the left-top corner of the normal button in the
	 * texture.
	 */
	protected int xPosTexture;
	/**
	 * This is the y position of the left-top corner of the normal button in the
	 * texture.
	 */
	protected int yPosTexture;

	public GuiImageToggleButton(int id, int xPos, int yPos, boolean flag, int xPosTexture, int yPosTexture, int width, int height, ResourceLocation resourceLocation)
	{
		super(id, xPos, yPos, width, height, "");
		this.guiTextureWidgets = resourceLocation;
		this.xPosTexture = xPosTexture;
		this.yPosTexture = yPosTexture;
		this.state = flag;
	}

	public GuiImageToggleButton(int id, int xPos, int yPos, int xPosTexture, int yPosTexture, int width, int height, ResourceLocation resourceLocation)
	{
		this(id, xPos, yPos, false, xPosTexture, yPosTexture, width, height, resourceLocation);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRendererObj;
			mc.renderEngine.bindTexture(this.guiTextureWidgets);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			if (this.enabled)
			{
				if (this.state)
				{
					if (this.hovered)
						this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xPosTexture, this.yPosTexture + this.height, this.width, this.height);
					else
						this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xPosTexture, this.yPosTexture + this.height, this.width, this.height);
				}
				else
				{
					if (this.hovered)
						this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xPosTexture, this.yPosTexture, this.width, this.height);
					else
						this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xPosTexture, this.yPosTexture, this.width, this.height);
				}
			}
			else
				this.drawTexturedModalRect(this.xPosition, this.yPosition, this.xPosTexture, this.yPosTexture + 2 * this.height, this.width, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	public void setState(boolean flag)
	{
		this.state = flag;
	}

	public boolean getState()
	{
		return this.state;
	}

	public void setImage(ResourceLocation resourceLocation, int xPosTexture, int yPosTexture)
	{
		this.guiTextureWidgets = resourceLocation;
		this.xPosTexture = xPosTexture;
		this.yPosTexture = yPosTexture;
	}
}
