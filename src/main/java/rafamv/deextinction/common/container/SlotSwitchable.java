package rafamv.deextinction.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotSwitchable extends Slot
{
	/** Initial display position of the inventory slot on the screen x axis */
	private final int initXDisplayPosition;

	/** Initial display position of the inventory slot on the screen y axis */
	private final int initYDisplayPosition;

	public SlotSwitchable(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
		this.initXDisplayPosition = xPosition;
		this.initYDisplayPosition = yPosition;
	}

	public void setState(boolean flag)
	{
		if (flag)
		{
			this.xDisplayPosition = this.initXDisplayPosition;
			this.yDisplayPosition = this.initYDisplayPosition;
		}
		else
		{
			this.xDisplayPosition = Integer.MAX_VALUE;
			this.yDisplayPosition = Integer.MAX_VALUE;
		}
	}

	public void setDisplayPosition(int posX, int posY)
	{
		this.xDisplayPosition = posX;
		this.yDisplayPosition = posY;
	}

	public void setPosX(int posX)
	{
		this.xDisplayPosition = posX;
	}

	public int getPosX()
	{
		return this.xDisplayPosition;
	}

	public void setPosY(int posY)
	{
		this.yDisplayPosition = posY;
	}

	public int getPosY()
	{
		return this.yDisplayPosition;
	}
}
