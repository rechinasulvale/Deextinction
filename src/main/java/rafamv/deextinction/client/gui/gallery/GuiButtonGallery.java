package rafamv.deextinction.client.gui.gallery;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiButtonGallery
{
	public List<GuiGalleryButtonCreature> list = new ArrayList<GuiGalleryButtonCreature>();
	public final int panelSizeX;
	public final int panelSizeY;
	public GuiButton guiButtonSelected;
	public boolean enabled;
	public boolean visible;

	public GuiButtonGallery(int panelSizeX, int panelSizeY, boolean enabled, boolean visible)
	{
		this.panelSizeX = panelSizeX;
		this.panelSizeY = panelSizeY;
		this.enabled = enabled;
		this.visible = visible;
	}

	public GuiButtonGallery(int panelSizeX, int panelSizeY, boolean enabled, boolean visible, List<GuiGalleryButtonCreature> list)
	{
		this.panelSizeX = panelSizeX;
		this.panelSizeY = panelSizeY;
		this.enabled = enabled;
		this.visible = visible;
		this.list = list;
	}

	public GuiButtonGallery(int panelSizeX, int panelSizeY, boolean enabled, boolean visible, GuiGalleryButtonCreature... list)
	{
		this.panelSizeX = panelSizeX;
		this.panelSizeY = panelSizeY;
		this.enabled = enabled;
		this.visible = visible;
		for (GuiGalleryButtonCreature galleryItem : list)
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, Block block, int imageOffset, int research, boolean isResearchUnlocked, GalleryPosition[] parents)
	{
		List<GalleryPosition> parentList = new ArrayList<GalleryPosition>();
		if (parents != null)
			for (GalleryPosition button : parents)
				parentList.add(button);

		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, new ItemStack(Item.getItemFromBlock(block)), imageOffset, research, isResearchUnlocked, parentList);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, Item item, int imageOffset, int research, boolean isResearchUnlocked, GalleryPosition[] parents)
	{
		List<GalleryPosition> parentList = new ArrayList<GalleryPosition>();
		if (parents != null)
			for (GalleryPosition button : parents)
				parentList.add(button);

		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, new ItemStack(item), imageOffset, research, isResearchUnlocked, parentList);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, ItemStack itemStack, int imageOffset, int research, boolean isResearchUnlocked, GalleryPosition[] parents)
	{
		List<GalleryPosition> parentList = new ArrayList<GalleryPosition>();
		if (parents != null)
			for (GalleryPosition button : parents)
				parentList.add(button);

		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, itemStack, imageOffset, research, isResearchUnlocked, parentList);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, Block block, int imageOffset, int research, boolean isResearchUnlocked, List<GalleryPosition> parents)
	{
		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, new ItemStack(Item.getItemFromBlock(block)), imageOffset, research, isResearchUnlocked, parents);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, Item item, int imageOffset, int research, boolean isResearchUnlocked, List<GalleryPosition> parents)
	{
		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, new ItemStack(item), imageOffset, research, isResearchUnlocked, parents);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, ItemStack itemStack, int imageOffset, int research, boolean isResearchUnlocked, List<GalleryPosition> parents)
	{
		GuiGalleryButtonCreature galleryItem = new GuiGalleryButtonCreature(creatureName, displayName, displayColumn, displayRow, width, height, itemStack, imageOffset, research, isResearchUnlocked, parents);
		if (!this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public void registerGalleryButton(GuiGalleryButtonCreature galleryItem)
	{
		if (galleryItem != null && !this.list.contains(galleryItem))
			this.list.add(galleryItem);
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		if (this.enabled && this.visible)
		{
			for (int id = 0; id < this.list.size(); id++)
			{
				GuiGalleryButtonCreature button = this.list.get(id);
				if (button.visible && mouseX >= button.positionX && mouseY >= button.positionY && mouseX < button.positionX + button.width && mouseY < button.positionY + button.height && button.shouldBeClicked())
				{
					this.guiButtonSelected = new GuiButton(id, 0, 0, button.buttonName);
					return true;
				}
			}
		}
		return false;
	}

	public void clearGallery()
	{
		this.list.clear();
	}

	public GuiButton getButtonSelected()
	{
		return this.guiButtonSelected;
	}
}
