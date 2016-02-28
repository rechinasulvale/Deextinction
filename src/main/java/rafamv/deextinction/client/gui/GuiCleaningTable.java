package rafamv.deextinction.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.buttons.GuiToggleButton;
import rafamv.deextinction.common.container.ContainerCleaningTable;
import rafamv.deextinction.common.message.MessageCleaningTable;
import rafamv.deextinction.common.registry.DEMessageRegistry;
import rafamv.deextinction.common.tileentity.TileCleaningTable;

@SideOnly(Side.CLIENT)
public class GuiCleaningTable extends GuiBase
{
	private static final ResourceLocation GUI_TEXTURE_MAIN = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_cleaning_table.png");
	private static final ResourceLocation GUI_TEXTURE_WIDGETS = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_widgets.png");

	private final InventoryPlayer playerInventory;
	private TileCleaningTable cleaning_table;

	public GuiCleaningTable(InventoryPlayer playerInventory, TileCleaningTable cleaning_table)
	{
		super(new ContainerCleaningTable(playerInventory, cleaning_table));
		this.playerInventory = playerInventory;
		this.cleaning_table = cleaning_table;
		this.xSize = 176;
		this.ySize = 190;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiToggleButton(0, this.guiLeft + this.xSize / 2, this.guiTop + 74, this.cleaning_table.shouldClean(), StatCollector.translateToLocal("container.cleaning_table.cleaning_toggle_button.off"), StatCollector
				.translateToLocal("container.cleaning_table.cleaning_toggle_button.on"), this.mc));
	}

	@Override
	public void actionPerformed(GuiButton button)
	{
		switch (button.id)
		{
			case 0:
				if (button instanceof GuiToggleButton)
				{
					GuiToggleButton buttonToggle = (GuiToggleButton) button;
					buttonToggle.toogleState();
					BlockPos pos = this.cleaning_table.getPos();
					this.cleaning_table.setShouldClean(buttonToggle.getState());
					DEMessageRegistry.wrapper.sendToServer(new MessageCleaningTable(pos.getX(), pos.getY(), pos.getZ(), buttonToggle.getState()));
				}
				break;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiCleaningTable.GUI_TEXTURE_MAIN);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (this.cleaning_table.isCleaning())
		{
			this.mc.getTextureManager().bindTexture(GuiCleaningTable.GUI_TEXTURE_WIDGETS);
			int cleaningProgress = this.cleaning_table.getCleaningProgressScaled(24);
			this.drawTexturedModalRect(this.guiLeft + 71, this.guiTop + 36, 0, 128, cleaningProgress + 1, 16);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String text = this.cleaning_table.getDisplayName().getUnformattedText();
		this.writeCenteredText(text, this.xSize / 2, 6, 4210752);
		text = this.playerInventory.getDisplayName().getUnformattedText();
		this.writeText(text, 8, this.ySize - 96 + 2, 4210752);
	}
}
