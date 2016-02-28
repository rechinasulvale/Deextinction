package rafamv.deextinction.client.gui.gallery;

import java.util.List;

import net.minecraft.item.ItemStack;

public class GuiGalleryButtonCreature extends GuiGalleryButton
{
	public boolean isResearchUnlocked;
	public boolean isResearchDone;
	public String displayName;
	public int research;
	public int color;

	public GuiGalleryButtonCreature(String creatureName, String displayName, int displayColumn, int displayRow, int width, int height, ItemStack itemStack, int imageOffset, int research, boolean isResearchUnlocked, List<GalleryPosition> parents)
	{
		super(creatureName, displayColumn, displayRow, width, height, itemStack, imageOffset, parents);
		this.displayName = displayName;
		this.research = research;
		this.isResearchDone = research > 49 ? true : false;
		this.isResearchUnlocked = isResearchUnlocked;
		if (this.research < 20)
			this.color = -907339228;
		else if (this.research < 40)
			this.color = -587888865;
		else if (this.research < 60)
			this.color = -402988794;
		else if (this.research < 80)
			this.color = -757996784;
		else
			this.color = 1669120;
	}

	public boolean shouldBeClicked()
	{
		return this.isResearchUnlocked;
	}
}
