package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;

@SideOnly(Side.CLIENT)
public class GuiButtonCenteredChangeableString extends GuiButton
{
	/** Initial position of the button. */
	protected int initPosX;

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiButtonCenteredChangeableString(int id, int xPos, int yPos, String displayString, Minecraft mc)
	{
		super(id, xPos, yPos, 0, 20, displayString);
		this.setDisplayString(mc, displayString);
	}

	/** Sets the button display string. */
	public void setDisplayString(Minecraft mc, String displayString)
	{
		if (displayString != null)
		{
			this.displayString = displayString;
			this.width = mc.fontRendererObj.getStringWidth(this.displayString) + 10;
			this.xPosition = this.initPosX - this.width / 2;
		}
		else
			DeExtinction.logger.error("GuiButtonCenteredChangeableString with id " + this.id + " has a display string equals to null. Display string was set to 'None'. THIS IS A BUG!");
	}
}
