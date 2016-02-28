package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;

@SideOnly(Side.CLIENT)
public class GuiToggleButton extends GuiButton
{
	/** Color of the button when selected */
	protected int selectedColor;
	/** Color of the button */
	protected int normalColor;
	/** Color of the button when disabled */
	protected int disabledColor;
	/** Initial position of the button. */
	protected int initPosX;
	/** Display string of the button when selected. */
	protected String secondDisplayString;
	/** State of the button. */
	protected boolean state;

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiToggleButton(int id, int xPos, int yPos, boolean flag, String firstDisplayString, String secondDisplayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		super(id, xPos, yPos, 0, 20, firstDisplayString);
		this.normalColor = normalColor;
		this.selectedColor = selectedColor;
		this.disabledColor = disabledColor;

		int widthFirst = mc.fontRendererObj.getStringWidth(firstDisplayString);
		int widthSecond = mc.fontRendererObj.getStringWidth(secondDisplayString);
		if (widthSecond > widthFirst)
			this.width = widthSecond + 10;
		else
			this.width = widthFirst + 10;
		this.initPosX = xPos;
		this.xPosition = this.initPosX - this.width / 2;

		if (firstDisplayString == null)
		{
			this.displayString = "None";
			this.enabled = false;
			DeExtinction.logger.error("GuiStringToggleButton with id = " + id + " has a display string equals to null. THIS IS A BUG!");
		}
		else
			this.displayString = firstDisplayString;

		if (secondDisplayString == null)
		{
			this.secondDisplayString = "None";
			this.enabled = false;
			DeExtinction.logger.error("GuiStringToggleButton with id = " + id + " has a display string equals to null. THIS IS A BUG!");
		}
		else
			this.secondDisplayString = secondDisplayString;

		this.state = flag;
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiToggleButton(int id, int xPos, int yPos, boolean flag, String firstDisplayString, String secondDisplayString, Minecraft mc)
	{
		this(id, xPos, yPos, flag, firstDisplayString, secondDisplayString, 14737632, 16777120, 10526880, mc);
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiToggleButton(int id, int xPos, int yPos, String firstDisplayString, String secondDisplayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		this(id, xPos, yPos, false, firstDisplayString, secondDisplayString, normalColor, selectedColor, disabledColor, mc);
	}

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiToggleButton(int id, int xPos, int yPos, String firstDisplayString, String secondDisplayString, Minecraft mc)
	{
		this(id, xPos, yPos, false, firstDisplayString, secondDisplayString, 14737632, 16777120, 10526880, mc);
	}

	/**
	 * Draws this button to the screen.
	 */
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.hovered || this.state);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int l = this.normalColor;

			if (packedFGColour != 0)
			{
				l = packedFGColour;
			}
			else if (!this.enabled)
			{
				l = this.disabledColor;
			}
			else if (this.hovered)
			{
				l = this.selectedColor;
			}

			String text = null;
			if (this.state)
				text = this.secondDisplayString;
			else
				text = this.displayString;

			this.drawCenteredString(fontrenderer, text, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}

	/** Sets the button string. */
	public void setFirstDisplayString(Minecraft mc, String text)
	{
		if (!text.isEmpty())
		{
			this.displayString = text;
			int widthFirst = mc.fontRendererObj.getStringWidth(this.displayString);
			int widthSecond = mc.fontRendererObj.getStringWidth(this.secondDisplayString);
			if (widthSecond > widthFirst)
				this.width = widthSecond;
			else
				this.width = widthFirst;
			this.width = mc.fontRendererObj.getStringWidth(text) + 10;
			this.xPosition = this.initPosX - this.width / 2;
		}
		else
			DeExtinction.logger.error("GuiToggleButton firstDisplayString is null. THIS IS A BUG!");
	}

	/** Sets the button string. */
	public void setSecondDisplayString(Minecraft mc, String text)
	{
		if (!text.isEmpty())
		{
			this.secondDisplayString = text;
			int widthFirst = mc.fontRendererObj.getStringWidth(this.displayString);
			int widthSecond = mc.fontRendererObj.getStringWidth(this.secondDisplayString);
			if (widthSecond > widthFirst)
				this.width = widthSecond;
			else
				this.width = widthFirst;
			this.width = mc.fontRendererObj.getStringWidth(text) + 10;
			this.xPosition = this.initPosX - this.width / 2;
		}
		else
			DeExtinction.logger.error("GuiToggleButton secondDisplayString is null. THIS IS A BUG!");
	}

	/** Sets the button state. */
	public void setState(boolean flag)
	{
		this.state = flag;
	}

	/** Toggles the button state. */
	public void toogleState()
	{
		this.state = !this.state;
	}

	/** Returns the button state. */
	public boolean getState()
	{
		return this.state;
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
