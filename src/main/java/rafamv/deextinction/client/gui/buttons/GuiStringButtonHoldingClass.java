package rafamv.deextinction.client.gui.buttons;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiStringButtonHoldingClass extends GuiStringButton
{
	/** This is a class that this button holds. */
	protected Class clazz = null;

	public GuiStringButtonHoldingClass(int id, int xPos, int yPos, int extraSpaceX, Class clazz, String displayString, int normalColor, int selectedColor, int disabledColor, Minecraft mc)
	{
		super(id, xPos, yPos, extraSpaceX, displayString, normalColor, selectedColor, disabledColor, mc);
		this.setHoldingClass(clazz);
	}

	public GuiStringButtonHoldingClass(int id, int xPos, int yPos, int extraSpaceX, Class clazz, String displayString, Minecraft mc)
	{
		super(id, xPos, yPos, extraSpaceX, displayString, mc);
		this.setHoldingClass(clazz);
	}

	/** Sets the class that this button holds. */
	public void setHoldingClass(Class clazz)
	{
		this.clazz = clazz;
	}

	/** Returns the class that this button holds. */
	public Class getHoldingClass()
	{
		return this.clazz;
	}
}
