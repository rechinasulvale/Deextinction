package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiButtonCreature extends GuiButton
{
	public static final String DEFAULT_NAME = "none";
	private String creatureName;
	private byte creatureID;

	/**
	 * Note that the xPos and yPos is the center of the button.
	 */
	public GuiButtonCreature(int id, int xPos, int yPos, String creatureName, byte creatureID, Minecraft mc)
	{
		super(id, xPos, yPos, 0, 20, creatureName);
		this.setCreature(mc, creatureName, creatureID);
	}

	/** Returns the creature name. */
	public String getCreatureName()
	{
		return this.creatureName;
	}

	/** Returns the creature id for the list. */
	public byte getCreatureID()
	{
		return this.creatureID;
	}

	/** Sets the button display string. */
	public void setCreature(Minecraft mc, String creatureName, byte creatureID)
	{
		if (creatureName != null)
		{
			this.creatureName = creatureName;
			this.displayString = StatCollector.translateToLocal("entity." + this.creatureName + ".name");
			this.width = mc.fontRendererObj.getStringWidth(this.displayString) + 10;
			this.creatureID = creatureID;
		}
		else
		{
			this.creatureName = GuiButtonCreature.DEFAULT_NAME;
			this.displayString = StatCollector.translateToLocal("entity." + this.creatureName + ".name");
			this.width = mc.fontRendererObj.getStringWidth(this.displayString) + 10;
			this.creatureID = (byte) 0;
		}
	}
}
