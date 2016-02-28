package rafamv.deextinction.client.gui.gallery;

import java.util.List;

import net.minecraft.item.ItemStack;

public abstract class GuiGalleryButton
{
	public final List<GalleryPosition> parents;
	public final ItemStack itemStack;
	public final String buttonName;
	public final int displayColumn;
	public final int displayRow;
	public final int imageOffset;
	public final int width;
	public final int height;
	public boolean visible;
	public int positionX;
	public int positionY;

	public GuiGalleryButton(String buttonName, int displayColumn, int displayRow, int width, int height, ItemStack itemStack, int imageOffset, List<GalleryPosition> parents)
	{
		this.buttonName = buttonName;
		this.itemStack = itemStack;
		this.displayColumn = displayColumn;
		this.displayRow = displayRow;
		this.parents = parents;
		this.width = width;
		this.height = height;
		this.imageOffset = imageOffset;
	}

	public abstract boolean shouldBeClicked();
}
