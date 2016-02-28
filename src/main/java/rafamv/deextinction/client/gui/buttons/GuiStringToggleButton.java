package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;

@SideOnly(Side.CLIENT)
public class GuiStringToggleButton extends GuiButton
{
	/** Color of the button when selected */
	protected int selectedColor;
	/** Color of the button */
	protected int normalColor;
	/** Color of the button when disabled */
	protected int disabledColor;
	/** Extra space in the right and left sides of the button */
	protected int extraSpaceX;
	/** Initial position of the button. */
	protected int initPosX;
	/** Display string of the button when selected. */
	protected String secondDisplayString;
	/** State of the button. */
	protected boolean state;

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringToggleButton(int id, int xPos, int yPos, int extraSpaceX, boolean flag, String firstDisplayString, String secondDisplayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		super(id, xPos - (mc.fontRendererObj.getStringWidth(firstDisplayString) + 2 * extraSpaceX) / 2, yPos, mc.fontRendererObj.getStringWidth(firstDisplayString) + 2 * extraSpaceX, 12, firstDisplayString);
		this.normalColor = normalColor;
		this.selectedColor = selectedColor;
		this.disabledColor = disabledColor;
		this.extraSpaceX = extraSpaceX;
		this.initPosX = xPos;

		if (firstDisplayString == null)
		{
			this.displayString = "None";
			this.visible = false;
			this.enabled = false;
			DeExtinction.logger.error("GuiStringToggleButton with id = " + id + " has a display string equals to null. THIS IS A BUG!");
		}
		else
			this.displayString = firstDisplayString;

		if (secondDisplayString == null)
		{
			this.secondDisplayString = "None";
			this.visible = false;
			this.enabled = false;
			DeExtinction.logger.error("GuiStringToggleButton with id = " + id + " has a display string equals to null. THIS IS A BUG!");
		}
		else
			this.secondDisplayString = secondDisplayString;

		this.setState(mc, flag);
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringToggleButton(int id, int xPos, int yPos, int extraSpaceX, boolean flag, String firstDisplayString, String secondDisplayString, Minecraft mc)
	{
		this(id, xPos, yPos, extraSpaceX, flag, firstDisplayString, secondDisplayString, 14737632, 16777120, 10526880, mc);
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringToggleButton(int id, int xPos, int yPos, int extraSpaceX, String firstDisplayString, String secondDisplayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		this(id, xPos, yPos, extraSpaceX, false, firstDisplayString, secondDisplayString, normalColor, selectedColor, disabledColor, mc);
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiStringToggleButton(int id, int xPos, int yPos, int extraSpaceX, String firstDisplayString, String secondDisplayString, Minecraft mc)
	{
		this(id, xPos, yPos, extraSpaceX, false, firstDisplayString, secondDisplayString, 14737632, 16777120, 10526880, mc);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRendererObj;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			String text = null;
			if (this.state)
				text = this.displayString;
			else
				text = this.secondDisplayString;
			if (this.enabled)
			{
				if (this.hovered)
					fontrenderer.drawString(text, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.selectedColor);
				else
					fontrenderer.drawString(text, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.normalColor);
			}
			else
				fontrenderer.drawString(text, this.xPosition + this.extraSpaceX, this.yPosition + 2, this.disabledColor);
			this.mouseDragged(mc, mouseX, mouseY);
		}
	}

	/** Sets the button string. */
	public void setFirstDisplayString(Minecraft mc, String text)
	{
		if (!text.isEmpty())
		{
			this.width = mc.fontRendererObj.getStringWidth(text) + 2 * this.extraSpaceX;
			this.xPosition = this.initPosX - this.width / 2;
			this.displayString = text;
		}
		else
			DeExtinction.logger.error("GuiStringToggleButton firstDisplayString is null. THIS IS A BUG!");
	}

	/** Sets the button string. */
	public void setFirstDisplayString(Minecraft mc)
	{
		this.width = mc.fontRendererObj.getStringWidth(this.displayString) + 2 * this.extraSpaceX;
		this.xPosition = this.initPosX - this.width / 2;
	}

	/** Sets the button string. */
	public void setSecondDisplayString(Minecraft mc, String text)
	{
		if (!text.isEmpty())
		{
			this.width = mc.fontRendererObj.getStringWidth(text) + 2 * this.extraSpaceX;
			this.xPosition = this.initPosX - this.width / 2;
			this.secondDisplayString = text;
		}
		else
			DeExtinction.logger.error("GuiStringToggleButton secondDisplayString is null. THIS IS A BUG!");
	}

	/** Sets the button string. */
	public void setSecondDisplayString(Minecraft mc)
	{
		this.width = mc.fontRendererObj.getStringWidth(this.secondDisplayString) + 2 * this.extraSpaceX;
		this.xPosition = this.initPosX - this.width / 2;
	}

	/** Sets the extra space in the right and left sides of the button. */
	public void setExtraSpaceX(Minecraft mc, int extraSpaceX)
	{
		this.extraSpaceX = extraSpaceX;
		if (this.state)
			this.setSecondDisplayString(mc);
		else
			this.setFirstDisplayString(mc);
	}

	/** Sets the state of the button. */
	public void setState(Minecraft mc, boolean flag)
	{
		this.state = flag;
		if (flag)
			this.setSecondDisplayString(mc);
		else
			this.setFirstDisplayString(mc);
	}

	/** Sets the normal color of the button. */
	public void setNormalColor(int normalColor)
	{
		this.normalColor = normalColor;
	}

	/** Sets the color of the button when selected. */
	public void setSelectedColor(int selectedColor)
	{
		this.selectedColor = selectedColor;
	}

	/** Sets the color of the button when disabled. */
	public void setDisabledColor(int disabledColor)
	{
		this.disabledColor = disabledColor;
	}
}
