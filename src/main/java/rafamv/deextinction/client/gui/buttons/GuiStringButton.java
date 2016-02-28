package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStringButton extends GuiButton
{
	/** Color of the button when selected */
	protected int selectedColor;
	/** Color of the button */
	protected int normalColor;
	/** Color of the button when disabled */
	protected int disabledColor;
	/** Extra space in the right and left sides of the button */
	protected int extraSpaceX;
	/**
	 * Initial position of the button. Needed to center the if the display
	 * string were changed.
	 */
	protected int initPosX;

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringButton(int id, int xPos, int yPos, int extraSpaceX, String displayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		super(id, xPos - (mc.fontRendererObj.getStringWidth(displayString) + 2 * extraSpaceX) / 2, yPos, mc.fontRendererObj.getStringWidth(displayString) + 2 * extraSpaceX, 12, displayString);
		this.selectedColor = selectedColor;
		this.disabledColor = disabledColor;
		this.normalColor = normalColor;
		this.extraSpaceX = extraSpaceX;
		this.initPosX = xPos;
		if (displayString == null)
		{
			this.visible = false;
			this.enabled = false;
		}
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringButton(int id, int xPos, int yPos, int extraSpaceX, String text, Minecraft mc)
	{
		this(id, xPos, yPos, extraSpaceX, text, 14737632, 16777120, 10526880, mc);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible && this.displayString != null)
		{
			FontRenderer fontrenderer = mc.fontRendererObj;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			if (this.enabled)
			{
				if (this.hovered)
					fontrenderer.drawString(this.displayString, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.selectedColor);
				else
					fontrenderer.drawString(this.displayString, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.normalColor);
			}
			else
				fontrenderer.drawString(this.displayString, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.disabledColor);
			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	/** Sets the normal color of the button */
	public void setNormalColor(int normalColor)
	{
		this.normalColor = normalColor;
	}

	/** Sets the color of the button when selected */
	public void setSelectedColor(int selectedColor)
	{
		this.selectedColor = selectedColor;
	}

	/** Sets the color of the button when disabled */
	public void setDisabledColor(int disabledColor)
	{
		this.disabledColor = disabledColor;
	}

	/** Sets the extra space in the right and left sides of the button */
	public void setExtraSpaceX(Minecraft mc, int extraSpaceX)
	{
		this.extraSpaceX = extraSpaceX;
		this.setDisplayString(mc, this.displayString);
	}

	/** Sets the button string */
	public void setDisplayString(Minecraft mc, String text)
	{
		this.width = mc.fontRendererObj.getStringWidth(text) + 2 * this.extraSpaceX;
		this.xPosition = this.initPosX - this.width / 2;
		this.displayString = text;
	}
}
