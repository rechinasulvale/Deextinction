package rafamv.deextinction.client.gui;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.buttons.GuiImageButton;
import rafamv.deextinction.client.gui.buttons.GuiToggleButton;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.gui.gallery.GuiButtonGallery;
import rafamv.deextinction.common.container.ContainerBotanicalResearchStation;
import rafamv.deextinction.common.container.SlotSwitchable;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.FoliageVariant;
import rafamv.deextinction.common.database.foliage.Fern;
import rafamv.deextinction.common.database.foliage.Spruce;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationCancelSapling;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationCreateSapling;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationFoliageName;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationGuiBranch;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationGuiPage;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationShouldResearch;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationVariant;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEMessageRegistry;
import rafamv.deextinction.common.tileentity.TileBotanicalResearchStation;

@SideOnly(Side.CLIENT)
public class GuiBotanicalResearchStation extends GuiGalleryContainer
{
	private static final ResourceLocation GUI_TEXTURE_FOLIAGE_LAYER = new ResourceLocation(DeExtinction.MODID, "textures/gui/botanicalresearchstation/gui_botanical_research_station_foliage_layer.png");
	private static final ResourceLocation GUI_TEXTURE_INDEX = new ResourceLocation(DeExtinction.MODID, "textures/gui/botanicalresearchstation/gui_botanical_research_station_index.png");
	private static final ResourceLocation GUI_TEXTURE_CREATE = new ResourceLocation(DeExtinction.MODID, "textures/gui/botanicalresearchstation/gui_botanical_research_station_create.png");
	private static final ResourceLocation GUI_TEXTURE_FOLIAGE = new ResourceLocation(DeExtinction.MODID, "textures/gui/botanicalresearchstation/gui_botanical_research_station_foliage.png");
	private static final ResourceLocation GUI_TEXTURE_WIDGETS = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_widgets.png");

	private TileBotanicalResearchStation researchStation;
	private final InventoryPlayer playerInventory;

	public GuiBotanicalResearchStation(InventoryPlayer playerInventory, TileBotanicalResearchStation researchStation)
	{
		super(new ContainerBotanicalResearchStation(playerInventory, researchStation));
		this.researchStation = researchStation;
		this.playerInventory = playerInventory;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.changeGuiSize(176, 256);
		int guiHalfxSize = (int) (this.xSize / 2);

		// Return button
		String buttonName = StatCollector.translateToLocal("container.botanical_research_station.return");
		int buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(0, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 140, 2 * buttonHalfWidth, 20, buttonName));

		// Start Researching
		this.buttonList.add(new GuiToggleButton(1, this.guiLeft + this.xSize / 2, this.guiTop + 60, this.researchStation.shouldResearch(), StatCollector.translateToLocal("container.botanical_research_station.research_toggle_button.off"), StatCollector
				.translateToLocal("container.botanical_research_station.research_toggle_button.on"), this.mc));

		// Change sapling page
		buttonName = StatCollector.translateToLocal("container.botanical_research_station.cultivate");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(2, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 90, 2 * buttonHalfWidth, 20, buttonName));

		// Decrease Variant
		this.buttonList.add(new GuiImageButton(3, this.guiLeft + 15, this.guiTop + 111, 0, 0, 10, 15, GuiBotanicalResearchStation.GUI_TEXTURE_WIDGETS));

		// Increase Variant
		this.buttonList.add(new GuiImageButton(4, this.guiLeft + 151, this.guiTop + 111, 10, 0, 10, 15, GuiBotanicalResearchStation.GUI_TEXTURE_WIDGETS));

		// Start Creating
		buttonName = StatCollector.translateToLocal("container.botanical_research_station.start_sapling");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) + 10;
		this.buttonList.add(new GuiButton(5, this.guiLeft + 52, this.guiTop + 52, buttonHalfWidth, 20, buttonName));

		// Cancel Creation
		buttonName = StatCollector.translateToLocal("container.botanical_research_station.cancel_sapling");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(6, this.guiLeft + this.xSize / 2 - buttonHalfWidth, this.guiTop + 115, 2 * buttonHalfWidth, 20, buttonName));

		// Foliage Options =====================================
		buttonHalfWidth = this.getLargerBranchStringWidth();
		buttonName = StatCollector.translateToLocal("container.botanical_research_station.bushes.name");
		this.buttonList.add(new GuiButton(7, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 50, 2 * buttonHalfWidth, 20, buttonName));

		buttonName = StatCollector.translateToLocal("container.botanical_research_station.trees.name");
		this.buttonList.add(new GuiButton(8, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 80, 2 * buttonHalfWidth, 20, buttonName));

		this.changeGalleryBranch(this.researchStation.getGuiBranch());
		int guiPage = this.researchStation.getGuiPage();
		if (guiPage == 1)
			this.changeGuiSize(256, 202);
		else
			this.changeGuiSize(176, 256);
		this.switchGuiPage(guiPage, true, true);
	}

	@Override
	protected GuiButtonGallery initGallery()
	{
		return new GuiButtonGallery(224, 154, true, true);
	}

	@Override
	protected int getGalleryButtonWidth()
	{
		return 26;
	}

	@Override
	protected int getGalleryButtonHeight()
	{
		return 26;
	}

	@Override
	protected int getGalleryButtonWidthRender()
	{
		return 45;
	}

	@Override
	protected int getGalleryButtonHeightRender()
	{
		return 37;
	}

	@Override
	protected int getGallerySpacingX()
	{
		return 80;
	}

	@Override
	protected int getGallerySpacingY()
	{
		return 50;
	}

	@Override
	protected int getGalleryBorderWidth()
	{
		return 15;
	}

	@Override
	protected int getGalleryBorderHeight()
	{
		return 17;
	}

	@Override
	protected void changeGalleryBranch(byte branchID)
	{
		this.gallery.clearGallery();
		this.sendMessageGuiBranch(branchID);
		switch (branchID)
		{
			case DEDatabaseRegistry.BUSHES_BRANCH:
				this.registerBranch(DEDatabaseRegistry.LIST_FOLIAGE_BUSHES_BRANCH, this.researchStation.getFoliageProgressList(), this.researchStation.getFoliageSelected());
				break;
			case DEDatabaseRegistry.TREES_BRANCH:
				this.registerBranch(DEDatabaseRegistry.LIST_FOLIAGE_TREES_BRANCH, this.researchStation.getFoliageProgressList(), this.researchStation.getFoliageSelected());
				break;
		}
	}

	public void registerBranch(HashMap<String, Foliage> branch, HashMap<String, Integer> researchDone, Foliage foliageFocused)
	{
		int focusedPositionX = (int) (this.xSize / 2);
		int focusedPositionY = (int) (this.ySize / 2);
		int buttonWidth = this.getGalleryButtonWidth();
		int buttonHeight = this.getGalleryButtonHeight();

		for (String foliageName : branch.keySet())
		{
			Foliage foliage = branch.get(foliageName);
			if (foliage != null)
			{
				GalleryPosition position = foliage.getResearchPosition();
				if (position != null)
				{
					if (researchDone.containsKey(foliageName))
						this.gallery.registerGalleryButton(foliage.getName(), foliage.getDisplayName(), position.column, position.row, buttonWidth, buttonHeight, foliage.getResearchButtonItem(), 4, researchDone.get(foliageName), foliage.canCreateFoliage(researchDone),
								foliage.getPreviousResearchPositions());
					else
						this.gallery.registerGalleryButton(foliage.getName(), foliage.getDisplayName(), position.column, position.row, buttonWidth, buttonHeight, foliage.getResearchButtonItem(), 4, 0, false, foliage.getPreviousResearchPositions());
				}
			}
		}
	}

	public void specialActionPerformed(GuiButton buttonFromTree)
	{
		this.sendMessageFoliage(buttonFromTree.displayString);
		this.switchGuiPage(2, true, true);
	}

	@Override
	public void actionPerformed(GuiButton button)
	{
		byte guiPage = this.researchStation.getGuiPage();
		switch (button.id)
		{
			case 0:
				if (guiPage == 2)
					this.changeGalleryBranch(this.researchStation.getGuiBranch());
				if (guiPage > 0 && guiPage < 5)
					this.switchGuiPage(guiPage - 1, true, false);
				break;
			case 1:
				if (guiPage == 2)
					if (button instanceof GuiToggleButton)
					{
						GuiToggleButton buttonToggle = (GuiToggleButton) button;
						buttonToggle.toogleState();
						this.sendMessageShouldResearch(buttonToggle.getState());
					}
				break;
			case 2:
				if (this.researchStation.getGuiPage() == 2)
					this.switchGuiPage(3, true, false);
				break;
			case 3:
				if (guiPage == 3)
				{
					Foliage foliage = this.researchStation.getFoliageSelected();
					if (foliage != null)
						if (this.researchStation.getFoliageVariantID() > 0)
							this.sendMessageVariant((byte) (this.researchStation.getFoliageVariantID() - 1));
				}
				break;
			case 4:
				if (guiPage == 3)
				{
					Foliage foliage = this.researchStation.getFoliageSelected();
					if (foliage != null)
					{
						HashMap<Byte, FoliageVariant> variantList = foliage.getVariants();
						if (variantList != null && !variantList.isEmpty() && this.researchStation.getFoliageVariantID() + 1 < variantList.size())
							this.sendMessageVariant((byte) (this.researchStation.getFoliageVariantID() + 1));
					}
				}
				break;
			case 5:
				if (this.researchStation.getGuiPage() == 3)
				{
					this.sendMessageCreateEmbryo();
					this.switchGuiPage(4, true, false);
				}
				break;
			case 6:
				if (this.researchStation.getGuiPage() == 4)
					this.sendMessageCancelEmbryo();
				break;
			default:
				if (guiPage == 0)
				{
					switch (button.id)
					{
						case 7:
							this.sendMessageFoliage(Fern.NAME);
							this.changeGalleryBranch(DEDatabaseRegistry.BUSHES_BRANCH);
							this.switchGuiPage(1, true, false);
							break;
						case 8:
							this.sendMessageFoliage(Spruce.NAME);
							this.changeGalleryBranch(DEDatabaseRegistry.TREES_BRANCH);
							this.switchGuiPage(1, true, false);
							break;
					}
				}
				break;
		}
		this.refreshButtons(this.researchStation.getGuiPage());
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
		switch (this.researchStation.getGuiPage())
		{
			case 2:
				this.setButtonEnabled(this.buttonList, 0, !this.researchStation.shouldResearch());
				this.setButtonEnabled(this.buttonList, 1, this.researchStation.getFoliageSelected().canCreateFoliage(this.researchStation.getFoliageProgressList()));
				break;
			case 3:
				Foliage foliage = this.researchStation.getFoliageSelected();
				if (foliage != null)
				{
					HashMap<Byte, FoliageVariant> variantList = foliage.getVariants();
					if (variantList != null && !variantList.isEmpty())
					{
						FoliageVariant variant = variantList.get(this.researchStation.getFoliageVariantID());
						if (variant != null)
						{
							if (this.researchStation.getFoliageProgress(foliage.getName()) >= variant.getVariantRequirement())
							{
								Slot slotSaplingContainer = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
								ItemStack requiredItem = this.researchStation.getFoliageSelected().getRequiredItem();
								if (requiredItem == null || requiredItem.getItem() == null)
								{
									if (slotSaplingContainer.getStack() == null)
										this.setButtonEnabled(this.buttonList, 5, this.researchStation.getNutrients() >= foliage.getNutrientsRequired(this.researchStation.getFoliageVariantID()));
								}
								else
								{
									ItemStack comparingStack = slotSaplingContainer.getStack();
									if (comparingStack != null && requiredItem.getItem() == comparingStack.getItem() && requiredItem.getMetadata() == comparingStack.getMetadata())
										this.setButtonEnabled(this.buttonList, 5, this.researchStation.getNutrients() >= foliage.getNutrientsRequired(this.researchStation.getFoliageVariantID()));
									else
										this.setButtonEnabled(this.buttonList, 5, false);
								}
							}
							else
								this.setButtonEnabled(this.buttonList, 5, false);
						}
						else
							this.setButtonEnabled(this.buttonList, 5, false);
					}
					else
						this.setButtonEnabled(this.buttonList, 5, false);
				}
				break;
			case 4:
				this.setButtonEnabled(this.buttonList, 6, this.researchStation.isCreating());
				Slot slotOutput = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				this.setButtonEnabled(this.buttonList, 0, !slotOutput.getHasStack() && !this.researchStation.isCreating());
				break;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		switch (this.researchStation.getGuiPage())
		{
			case 0:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_INDEX);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				break;
			case 2:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_FOLIAGE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				if (this.researchStation.isResearching())
				{
					this.mc.getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_WIDGETS);
					int research = this.researchStation.getResearchProgressScaled(122);
					this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 35, 0, 102, research, 9);
				}
				break;
			case 3:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_CREATE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				this.mc.getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_WIDGETS);
				if (this.researchStation.getNutrients() > 0)
				{
					int nutriets = this.researchStation.getNutrientsScaled(122);
					this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 35, 0, 84, nutriets, 9);
				}
				Foliage foliage = this.researchStation.getFoliageSelected();
				if (foliage != null)
					this.drawTexturedModalRect(this.guiLeft + 30 + 121 * foliage.getNutrientsRequired(this.researchStation.getFoliageVariantID()) / 1000, this.guiTop + 35, 0, 0, 1, 9);
				break;
			case 4:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_FOLIAGE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				if (this.researchStation.isCreating())
				{
					this.mc.getTextureManager().bindTexture(GuiBotanicalResearchStation.GUI_TEXTURE_WIDGETS);
					int creating = this.researchStation.getCreateProgressScaled(122);
					this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 35, 0, 102, creating, 9);
				}
				break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		Foliage foliage = this.researchStation.getFoliageSelected();
		byte guiPage = this.researchStation.getGuiPage();
		switch (guiPage)
		{
			case 0:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				this.writeCenteredTextWithScale(StatCollector.translateToLocal("container.botanical_research_station.select_branch"), this.xSize / 2, 30, 4210752, 1.4F);
				break;
			case 2:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (foliage != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsKey(foliage.getName()))
					this.writeText(foliage.getDisplayName() + ": " + this.researchStation.getFoliageProgress(foliage.getName()) + "%", 30, 25, 4210752);
				break;
			case 3:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (foliage != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsKey(foliage.getName()))
				{
					this.writeText(foliage.getDisplayName() + ": " + this.researchStation.getFoliageProgress(foliage.getName()) + "%", 30, 25, 4210752);

					FoliageVariant variant = foliage.getVariants().get(this.researchStation.getFoliageVariantID());
					if (variant != null)
					{
						this.writeCenteredText(variant.getVariantName(), this.xSize / 2, 110, 4210752);
						this.writeCenteredText(StatCollector.translateToLocal("container.botanical_research_station.dna") + ": " + variant.getVariantRequirement() + "%", this.xSize / 2, 120, 4210752);
					}
				}
				break;
			case 4:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (foliage != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsKey(foliage.getName()))
				{
					this.writeText(foliage.getDisplayName() + ": " + this.researchStation.getFoliageProgress(foliage.getName()) + "%", 30, 25, 4210752);
					this.writeTextWithScale(StatCollector.translateToLocal("container.botanical_research_station.period") + ": " + foliage.getFoliagePeriod(), 20, 60, 4210752, 0.8F);
					this.writeTextWithScale(StatCollector.translateToLocal("container.botanical_research_station.height") + ": " + foliage.getFoliageHeight(), 20, 80, 4210752, 0.8F);
					this.writeTextWithScale(StatCollector.translateToLocal("container.botanical_research_station.where") + ": " + foliage.getFoliageWhere(), 20, 100, 4210752, 0.8F);
				}
				break;
		}
	}

	private void sendMessageGuiPage(int guiPage)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setGuiPage(guiPage);
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationGuiPage(pos.getX(), pos.getY(), pos.getZ(), guiPage));
	}

	protected void sendMessageGuiBranch(byte guiBranch)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setGuiBranch(guiBranch);
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationGuiBranch(pos.getX(), pos.getY(), pos.getZ(), guiBranch));
	}

	private void sendMessageShouldResearch(boolean shouldResearch)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setShouldResearch(shouldResearch);
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationShouldResearch(pos.getX(), pos.getY(), pos.getZ(), shouldResearch));
	}

	private void sendMessageFoliage(String foliageName)
	{
		if (DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsKey(foliageName))
		{
			BlockPos pos = this.researchStation.getPos();
			this.researchStation.setFoliageSelected(foliageName);
			DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationFoliageName(pos.getX(), pos.getY(), pos.getZ(), foliageName));
		}
		else
		{
			DeExtinction.logger.error("GuiBotanicalResearchStation tried to send a message to change the foliage to " + foliageName + ",");
			DeExtinction.logger.error("but the foliage was not registered in the DEDatabaseRegistry.foliage_list. THIS IS A BUG!");
			DeExtinction.logger.error("DEDatabaseRegistry.foliage_list " + DEDatabaseRegistry.LIST_ALL_FOLIAGE);
		}
	}

	private void sendMessageVariant(byte variant)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setFoliageVariantID(variant);
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationVariant(pos.getX(), pos.getY(), pos.getZ(), variant));
	}

	private void sendMessageCreateEmbryo()
	{
		BlockPos pos = this.researchStation.getPos();
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationCreateSapling(pos.getX(), pos.getY(), pos.getZ()));
	}

	private void sendMessageCancelEmbryo()
	{
		BlockPos pos = this.researchStation.getPos();
		DEMessageRegistry.wrapper.sendToServer(new MessageBotanicalResearchStationCancelSapling(pos.getX(), pos.getY(), pos.getZ()));
	}

	protected void switchGuiPage(int guiPage, boolean shouldUpdateSlots, boolean shouldUpdateButtons)
	{
		if (guiPage == 1)
			this.changeGuiSize(256, 202);
		else
			this.changeGuiSize(176, 256);

		if (shouldUpdateSlots)
			this.refreshSlots(guiPage);
		if (shouldUpdateButtons)
			this.refreshButtons(guiPage);
		this.sendMessageGuiPage(guiPage);
	}

	private void refreshSlots(int guiPage)
	{
		Slot slot = null;
		switch (guiPage)
		{
			case 0:
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);

				for (int i = 4; i < this.inventorySlots.getInventory().size(); i++)
				{
					Slot slotPlayer = this.inventorySlots.getSlot(i);
					if (slotPlayer != null)
						((SlotSwitchable) slotPlayer).setState(true);
				}

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
			case 1:
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);

				for (int i = 4; i < this.inventorySlots.getInventory().size(); i++)
				{
					Slot slotPlayer = this.inventorySlots.getSlot(i);
					if (slotPlayer != null)
						((SlotSwitchable) slotPlayer).setState(false);
				}

				this.gallery.visible = true;
				this.gallery.enabled = true;
				break;
			case 2:
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);

				for (int i = 4; i < this.inventorySlots.getInventory().size(); i++)
				{
					Slot slotPlayer = this.inventorySlots.getSlot(i);
					if (slotPlayer != null)
						((SlotSwitchable) slotPlayer).setState(true);
				}

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
			case 3:
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
			case 4:
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
		}
	}

	private void refreshButtons(int guiPage)
	{
		Foliage foliage = this.researchStation.getFoliageSelected();

		if (guiPage == 1)
			((GuiButton) this.buttonList.get(0)).yPosition = this.guiTop + 177;
		else if (guiPage == 2)
			((GuiButton) this.buttonList.get(0)).yPosition = this.guiTop + 120;
		else
			((GuiButton) this.buttonList.get(0)).yPosition = this.guiTop + 140;

		this.setButtonVisible(this.buttonList, 0, guiPage != 0);
		this.setButtonVisible(this.buttonList, 1, guiPage == 2);
		this.setButtonVisible(this.buttonList, 2, guiPage == 2);
		this.setButtonVisible(this.buttonList, 3, guiPage == 3);
		this.setButtonVisible(this.buttonList, 4, guiPage == 3);
		this.setButtonVisible(this.buttonList, 5, guiPage == 3);
		this.setButtonVisible(this.buttonList, 6, guiPage == 4);
		this.setButtonVisible(this.buttonList, 7, guiPage == 0);
		this.setButtonVisible(this.buttonList, 8, guiPage == 0);

		this.setButtonEnabled(this.buttonList, 0, !this.researchStation.shouldResearch());
		this.setButtonEnabled(this.buttonList, 1, guiPage == 2);

		if (guiPage == 2 && foliage != null)
			this.setButtonEnabled(this.buttonList, 2, !this.researchStation.shouldResearch() && this.researchStation.getFoliageProgress(foliage.getName()) > 49 && foliage.canCreateFoliage(this.researchStation.getFoliageProgressList()));
		else
			this.setButtonEnabled(this.buttonList, 2, false);

		if (guiPage == 3 && foliage != null)
		{
			this.setButtonEnabled(this.buttonList, 3, this.researchStation.getFoliageVariantID() > 0);

			HashMap<Byte, FoliageVariant> variantList = foliage.getVariants();
			if (variantList != null)
			{
				this.setButtonEnabled(this.buttonList, 4, this.researchStation.getFoliageVariantID() + 1 < variantList.size());

				FoliageVariant variant = variantList.get(this.researchStation.getFoliageVariantID());
				if (variant != null)
				{
					if (this.researchStation.getFoliageProgress(foliage.getName()) >= variant.getVariantRequirement())
						this.setButtonEnabled(this.buttonList, 5, this.researchStation.getNutrients() >= foliage.getNutrientsRequired(this.researchStation.getFoliageVariantID()));
					else
						this.setButtonEnabled(this.buttonList, 5, false);
				}
			}
		}
		else
		{
			this.setButtonEnabled(this.buttonList, 3, false);
			this.setButtonEnabled(this.buttonList, 4, false);
			this.setButtonEnabled(this.buttonList, 5, false);
		}
		this.setButtonEnabled(this.buttonList, 6, guiPage == 4);
		this.setButtonEnabled(this.buttonList, 7, guiPage == 0);
		this.setButtonEnabled(this.buttonList, 8, guiPage == 0);
	}

	private int getLargerBranchStringWidth()
	{
		String buttonName = StatCollector.translateToLocal("container.botanical_research_station.bushes.name");
		int buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		int buttonHalfWidthOld = buttonHalfWidth;

		buttonName = StatCollector.translateToLocal("container.botanical_research_station.trees.name");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		if (buttonHalfWidthOld < buttonHalfWidth)
			buttonHalfWidthOld = buttonHalfWidth;

		return buttonHalfWidthOld;
	}
}
