package rafamv.deextinction.client.gui.buttons;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiImageButtonHoldingClass extends GuiImageButton
{
	/** This is a class that this button holds. */
	protected Class clazz = null;

	public GuiImageButtonHoldingClass(int id, int xPos, int yPos, int xPosTexture, int yPosTexture, int width, int height, ResourceLocation resourceLocation)
	{
		super(id, xPos, yPos, xPosTexture, yPosTexture, width, height, resourceLocation);
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
