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
import rafamv.deextinction.client.gui.buttons.GuiImageToggleButton;
import rafamv.deextinction.client.gui.buttons.GuiToggleButton;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.gui.gallery.GuiButtonGallery;
import rafamv.deextinction.common.container.ContainerGeneticResearchStation;
import rafamv.deextinction.common.container.SlotSwitchable;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.CreatureTexture;
import rafamv.deextinction.common.database.creatures.Chicken;
import rafamv.deextinction.common.database.creatures.Horse;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCancelEmbryo;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCreateEmbryo;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCreatureName;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGenderAndTexture;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGuiBranch;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGuiPage;
import rafamv.deextinction.common.message.MessageGeneticResearchStationShouldResearch;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEMessageRegistry;
import rafamv.deextinction.common.tileentity.TileGeneticResearchStation;

@SideOnly(Side.CLIENT)
public class GuiGeneticResearchStation extends GuiGalleryContainer
{
	private static final ResourceLocation GUI_TEXTURE_CREATURE_LAYER = new ResourceLocation(DeExtinction.MODID, "textures/gui/geneticresearchstation/gui_genetic_research_station_creature_layer.png");
	private static final ResourceLocation GUI_TEXTURE_INDEX = new ResourceLocation(DeExtinction.MODID, "textures/gui/geneticresearchstation/gui_genetic_research_station_index.png");
	private static final ResourceLocation GUI_TEXTURE_CREATE = new ResourceLocation(DeExtinction.MODID, "textures/gui/geneticresearchstation/gui_genetic_research_station_create.png");
	private static final ResourceLocation GUI_TEXTURE_CREATURE = new ResourceLocation(DeExtinction.MODID, "textures/gui/geneticresearchstation/gui_genetic_research_station_creature.png");
	private static final ResourceLocation GUI_TEXTURE_WIDGETS = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_widgets.png");

	private TileGeneticResearchStation researchStation;
	private final InventoryPlayer playerInventory;

	public GuiGeneticResearchStation(InventoryPlayer playerInventory, TileGeneticResearchStation researchStation)
	{
		super(new ContainerGeneticResearchStation(playerInventory, researchStation));
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
		String buttonName = StatCollector.translateToLocal("container.genetic_research_station.return");
		int buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(0, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 140, 2 * buttonHalfWidth, 20, buttonName));

		// Start Researching
		this.buttonList.add(new GuiToggleButton(1, this.guiLeft + this.xSize / 2, this.guiTop + 60, this.researchStation.shouldResearch(), StatCollector.translateToLocal("container.genetic_research_station.research_toggle_button.off"), StatCollector
				.translateToLocal("container.genetic_research_station.research_toggle_button.on"), this.mc));

		// Change embryo page
		buttonName = StatCollector.translateToLocal("container.genetic_research_station.cultivate");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(2, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 90, 2 * buttonHalfWidth, 20, buttonName));

		// Gender Button
		this.buttonList.add(new GuiImageToggleButton(3, this.guiLeft + 43, this.guiTop + 79, true, 143, 0, 15, 15, GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS));

		// Decrease Texture
		this.buttonList.add(new GuiImageButton(4, this.guiLeft + 15, this.guiTop + 111, 0, 0, 10, 15, GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS));

		// Increase Texture
		this.buttonList.add(new GuiImageButton(5, this.guiLeft + 151, this.guiTop + 111, 10, 0, 10, 15, GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS));

		// Start Creating
		buttonName = StatCollector.translateToLocal("container.genetic_research_station.start_embryo");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) + 10;
		this.buttonList.add(new GuiButton(6, this.guiLeft + 52, this.guiTop + 52, buttonHalfWidth, 20, buttonName));

		// Cancel Creation
		buttonName = StatCollector.translateToLocal("container.genetic_research_station.cancel_embryo");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(7, this.guiLeft + this.xSize / 2 - buttonHalfWidth, this.guiTop + 115, 2 * buttonHalfWidth, 20, buttonName));

		// Creature Options =====================================
		buttonHalfWidth = this.getLargerBranchStringWidth();
		buttonName = StatCollector.translateToLocal("container.genetic_research_station.theropod.name");
		this.buttonList.add(new GuiButton(8, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 50, 2 * buttonHalfWidth, 20, buttonName));

		buttonName = StatCollector.translateToLocal("container.genetic_research_station.mammals.name");
		this.buttonList.add(new GuiButton(9, this.guiLeft + guiHalfxSize - buttonHalfWidth, this.guiTop + 80, 2 * buttonHalfWidth, 20, buttonName));

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
			case DEDatabaseRegistry.THEROPODS_BRANCH:
				this.registerBranch(DEDatabaseRegistry.LIST_CREATURE_THEROPODS_BRANCH, this.researchStation.getCreatureProgressList(), this.researchStation.getCreatureSelected());
				break;
			case DEDatabaseRegistry.MAMMALS_BRANCH:
				this.registerBranch(DEDatabaseRegistry.LIST_CREATURE_MAMMALS_BRANCH, this.researchStation.getCreatureProgressList(), this.researchStation.getCreatureSelected());
				break;
		}
	}

	public void registerBranch(HashMap<String, Creature> branch, HashMap<String, Integer> researchDone, Creature creatureFocused)
	{
		int focusedPositionX = (int) (this.xSize / 2);
		int focusedPositionY = (int) (this.ySize / 2);
		int buttonWidth = this.getGalleryButtonWidth();
		int buttonHeight = this.getGalleryButtonHeight();

		for (String creatureName : branch.keySet())
		{
			Creature creature = branch.get(creatureName);
			if (creature != null)
			{
				GalleryPosition position = creature.getResearchPosition();
				if (position != null)
				{
					if (researchDone.containsKey(creatureName))
						this.gallery.registerGalleryButton(creature.getName(), creature.getDisplayName(), position.column, position.row, buttonWidth, buttonHeight, creature.getResearchButtonItem(), 4, researchDone.get(creatureName), creature.canCreateCreature(researchDone),
								creature.getPreviousResearchPositions());
					else
						this.gallery.registerGalleryButton(creature.getName(), creature.getDisplayName(), position.column, position.row, buttonWidth, buttonHeight, creature.getResearchButtonItem(), 4, 0, false, creature.getPreviousResearchPositions());
				}
			}
		}
	}

	@Override
	public void specialActionPerformed(GuiButton buttonFromTree)
	{
		this.sendMessageCreature(buttonFromTree.displayString);
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
				if (this.researchStation.getGuiPage() == 3)
					this.sendMessageGenderTexture(!this.researchStation.getCreatureGender(), (byte) 0);
				break;
			case 4:
				if (guiPage == 3)
				{
					Creature creature = this.researchStation.getCreatureSelected();
					if (creature != null)
						if (this.researchStation.getCreatureTextureID() > 0)
							this.sendMessageGenderTexture(this.researchStation.getCreatureGender(), (byte) (this.researchStation.getCreatureTextureID() - 1));
				}
				break;
			case 5:
				if (guiPage == 3)
				{
					Creature creature = this.researchStation.getCreatureSelected();
					if (creature != null)
					{
						if (this.researchStation.getCreatureGender())
						{
							HashMap<Byte, CreatureTexture> textureList = creature.getMaleCreatureTextures();
							if (textureList != null && !textureList.isEmpty() && this.researchStation.getCreatureTextureID() + 1 < textureList.size())
								this.sendMessageGenderTexture(this.researchStation.getCreatureGender(), (byte) (this.researchStation.getCreatureTextureID() + 1));
						}
						else
						{
							HashMap<Byte, CreatureTexture> textureList = creature.getFemaleCreatureTextures();
							if (textureList != null && !textureList.isEmpty() && this.researchStation.getCreatureTextureID() + 1 < textureList.size())
								this.sendMessageGenderTexture(this.researchStation.getCreatureGender(), (byte) (this.researchStation.getCreatureTextureID() + 1));
						}
					}
				}
				break;
			case 6:
				if (this.researchStation.getGuiPage() == 3)
				{
					this.sendMessageCreateEmbryo();
					this.switchGuiPage(4, true, false);
				}
				break;
			case 7:
				if (this.researchStation.getGuiPage() == 4)
					this.sendMessageCancelEmbryo();
				break;
			default:
				if (guiPage == 0)
				{
					switch (button.id)
					{
						case 8:
							this.sendMessageCreature(Chicken.NAME);
							this.changeGalleryBranch(DEDatabaseRegistry.THEROPODS_BRANCH);
							this.switchGuiPage(1, true, false);
							break;
						case 9:
							this.sendMessageCreature(Horse.NAME);
							this.changeGalleryBranch(DEDatabaseRegistry.MAMMALS_BRANCH);
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
				this.setButtonEnabled(this.buttonList, 1, this.researchStation.getCreatureSelected().canCreateCreature(this.researchStation.getCreatureProgressList()));
				break;
			case 3:
				Creature creature = this.researchStation.getCreatureSelected();
				if (creature != null)
				{
					if (this.researchStation.getCreatureGender())
					{
						HashMap<Byte, CreatureTexture> textureList = creature.getMaleCreatureTextures();
						if (textureList != null && !textureList.isEmpty())
						{
							CreatureTexture texture = textureList.get(this.researchStation.getCreatureTextureID());
							if (texture != null)
							{
								if (this.researchStation.getCreatureProgress(creature.getName()) >= texture.getTextureRequirement())
								{
									Slot slotEmbryoContainer = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);

									ItemStack requiredItem = this.researchStation.getCreatureSelected().getEmbryoContainerItem();
									if (requiredItem == null || requiredItem.getItem() == null)
									{
										if (slotEmbryoContainer.getStack() == null)
											this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
									}
									else
									{
										ItemStack comparingStack = slotEmbryoContainer.getStack();
										if (comparingStack != null && requiredItem.getItem() == comparingStack.getItem() && requiredItem.getMetadata() == comparingStack.getMetadata())
											this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
										else
											this.setButtonEnabled(this.buttonList, 6, false);
									}
								}
								else
									this.setButtonEnabled(this.buttonList, 6, false);
							}
							else
								this.setButtonEnabled(this.buttonList, 6, false);
						}
						else
							this.setButtonEnabled(this.buttonList, 6, false);
					}
					else
					{
						HashMap<Byte, CreatureTexture> textureList = creature.getFemaleCreatureTextures();
						if (textureList != null && !textureList.isEmpty())
						{
							CreatureTexture texture = textureList.get(this.researchStation.getCreatureTextureID());
							if (texture != null)
							{
								if (this.researchStation.getCreatureProgress(creature.getName()) >= texture.getTextureRequirement())
								{
									Slot slotEmbryoContainer = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);

									ItemStack requiredItem = this.researchStation.getCreatureSelected().getEmbryoContainerItem();
									if (requiredItem == null || requiredItem.getItem() == null)
									{
										if (slotEmbryoContainer.getStack() == null)
											this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
									}
									else
									{
										ItemStack comparingStack = slotEmbryoContainer.getStack();
										if (comparingStack != null && requiredItem.getItem() == comparingStack.getItem() && requiredItem.getMetadata() == comparingStack.getMetadata())
											this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
										else
											this.setButtonEnabled(this.buttonList, 6, false);
									}
								}
								else
									this.setButtonEnabled(this.buttonList, 6, false);
							}
							else
								this.setButtonEnabled(this.buttonList, 6, false);
						}
						else
							this.setButtonEnabled(this.buttonList, 6, false);
					}
				}
				break;
			case 4:
				this.setButtonEnabled(this.buttonList, 7, this.researchStation.isCreating());
				Slot slotOutput = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
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
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_INDEX);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				break;
			case 2:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_CREATURE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				if (this.researchStation.isResearching())
				{
					this.mc.getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS);
					int research = this.researchStation.getResearchProgressScaled(122);
					this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 35, 0, 102, research, 9);
				}
				break;
			case 3:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_CREATE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				this.mc.getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS);
				if (this.researchStation.getNutrients() > 0)
				{
					int nutriets = this.researchStation.getNutrientsScaled(122);
					this.drawTexturedModalRect(this.guiLeft + 30, this.guiTop + 35, 0, 84, nutriets, 9);
				}
				Creature creature = this.researchStation.getCreatureSelected();
				if (creature != null)
					this.drawTexturedModalRect(this.guiLeft + 30 + 121 * creature.getNutrientsRequired() / 1000, this.guiTop + 35, 0, 0, 1, 9);
				break;
			case 4:
				Minecraft.getMinecraft().getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_CREATURE);
				this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
				if (this.researchStation.isCreating())
				{
					this.mc.getTextureManager().bindTexture(GuiGeneticResearchStation.GUI_TEXTURE_WIDGETS);
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
		Creature creature = this.researchStation.getCreatureSelected();
		byte guiPage = this.researchStation.getGuiPage();
		switch (guiPage)
		{
			case 0:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				this.writeCenteredTextWithScale(StatCollector.translateToLocal("container.genetic_research_station.select_branch"), this.xSize / 2, 30, 4210752, 1.4F);
				break;
			case 2:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (creature != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_CREATURES.containsKey(creature.getName()))
					this.writeText(creature.getDisplayName() + ": " + this.researchStation.getCreatureProgress(creature.getName()) + "%", 30, 25, 4210752);
				break;
			case 3:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (creature != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_CREATURES.containsKey(creature.getName()))
				{
					this.writeText(creature.getDisplayName() + ": " + this.researchStation.getCreatureProgress(creature.getName()) + "%", 30, 25, 4210752);
					if (this.researchStation.getCreatureGender())
					{
						CreatureTexture texture = creature.getMaleCreatureTextures().get(this.researchStation.getCreatureTextureID());
						if (texture != null)
						{
							this.writeCenteredText(texture.getTextureName(), this.xSize / 2, 110, 4210752);
							this.writeCenteredText(StatCollector.translateToLocal("container.genetic_research_station.dna") + ": " + texture.getTextureRequirement() + "%", this.xSize / 2, 120, 4210752);
						}
						this.writeCenteredText(StatCollector.translateToLocal("container.genetic_research_station.male"), this.xSize / 2, 83, 4210752);
					}
					else
					{
						CreatureTexture texture = creature.getFemaleCreatureTextures().get(this.researchStation.getCreatureTextureID());
						if (texture != null)
						{
							this.writeCenteredText(texture.getTextureName(), this.xSize / 2, 110, 4210752);
							this.writeCenteredText(StatCollector.translateToLocal("container.genetic_research_station.dna") + ": " + texture.getTextureRequirement() + "%", this.xSize / 2, 120, 4210752);
						}
						this.writeCenteredText(StatCollector.translateToLocal("container.genetic_research_station.female"), this.xSize / 2, 83, 4210752);
					}
				}
				break;
			case 4:
				this.writeCenteredText(this.researchStation.getDisplayName().getUnformattedText(), this.xSize / 2, 6, 4210752);
				this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
				if (creature != null && DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES != null && DEDatabaseRegistry.LIST_ALL_CREATURES.containsKey(creature.getName()))
				{
					this.writeText(creature.getDisplayName() + ": " + this.researchStation.getCreatureProgress(creature.getName()) + "%", 30, 25, 4210752);
					this.writeTextWithScale(StatCollector.translateToLocal("container.genetic_research_station.period") + ": " + creature.getCreaturePeriod(), 20, 55, 4210752, 0.8F);
					this.writeTextWithScale(StatCollector.translateToLocal("container.genetic_research_station.length") + ": " + creature.getCreatureLength(), 20, 70, 4210752, 0.8F);
					this.writeTextWithScale(StatCollector.translateToLocal("container.genetic_research_station.height") + ": " + creature.getCreatureHeight(), 20, 85, 4210752, 0.8F);
					this.writeTextWithScale(StatCollector.translateToLocal("container.genetic_research_station.weight") + ": " + creature.getCreatureWeight(), 20, 100, 4210752, 0.8F);
				}
				break;
		}
	}

	private void sendMessageGuiPage(int guiPage)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setGuiPage(guiPage);
		DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationGuiPage(pos.getX(), pos.getY(), pos.getZ(), guiPage));
	}

	protected void sendMessageGuiBranch(byte guiBranch)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setGuiBranch(guiBranch);
		DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationGuiBranch(pos.getX(), pos.getY(), pos.getZ(), guiBranch));
	}

	private void sendMessageShouldResearch(boolean shouldResearch)
	{
		BlockPos pos = this.researchStation.getPos();
		this.researchStation.setShouldResearch(shouldResearch);
		DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationShouldResearch(pos.getX(), pos.getY(), pos.getZ(), shouldResearch));
	}

	private void sendMessageCreature(String creatureName)
	{
		if (DEDatabaseRegistry.LIST_ALL_CREATURES.containsKey(creatureName))
		{
			BlockPos pos = this.researchStation.getPos();
			this.researchStation.setCreatureSelected(creatureName);
			DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationCreatureName(pos.getX(), pos.getY(), pos.getZ(), creatureName));
		}
		else
		{
			DeExtinction.logger.error("GuiGeneticResearchStation tried to send a message to change the creature to " + creatureName + ",");
			DeExtinction.logger.error("but the creature was not registered in the DEDatabaseRegistry.creature_list. THIS IS A BUG!");
			DeExtinction.logger.error("DEDatabaseRegistry.creature_list " + DEDatabaseRegistry.LIST_ALL_CREATURES);
		}
	}

	private void sendMessageGenderTexture(boolean gender, byte texture)
	{
		BlockPos pos = this.researchStation.getPos();
		if (this.buttonList.get(3) instanceof GuiImageToggleButton)
		{
			GuiImageToggleButton buttonGender = (GuiImageToggleButton) this.buttonList.get(3);
			buttonGender.setState(gender);
			this.researchStation.setCreatureGender(buttonGender.getState());
			this.researchStation.setTextureID(texture);
			DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationGenderAndTexture(pos.getX(), pos.getY(), pos.getZ(), buttonGender.getState(), texture));
		}
		else
			DeExtinction.logger.error("Wrong button type for button with id 3");
	}

	private void sendMessageCreateEmbryo()
	{
		BlockPos pos = this.researchStation.getPos();
		DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationCreateEmbryo(pos.getX(), pos.getY(), pos.getZ()));
	}

	private void sendMessageCancelEmbryo()
	{
		BlockPos pos = this.researchStation.getPos();
		DEMessageRegistry.wrapper.sendToServer(new MessageGeneticResearchStationCancelEmbryo(pos.getX(), pos.getY(), pos.getZ()));
	}

	protected void switchGuiPage(int guiPage, boolean shouldUpdateSlots, boolean shouldUpdateButtons)
	{
		switch (guiPage)
		{
			case 0:
				this.changeGuiSize(176, 256);
				break;
			case 1:
				this.changeGuiSize(256, 202);
				break;
			case 2:
				this.changeGuiSize(176, 256);
				break;
			case 3:
				this.sendMessageGenderTexture(this.researchStation.getCreatureGender(), (byte) 0);
				this.changeGuiSize(176, 256);
				break;
			case 4:
				this.changeGuiSize(176, 256);
				break;
		}

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
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
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
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
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
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
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
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
			case 4:
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_NUTRIENT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(false);
				slot = this.inventorySlots.getSlot(TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]);
				if (slot instanceof SlotSwitchable)
					((SlotSwitchable) slot).setState(true);

				this.gallery.visible = false;
				this.gallery.enabled = false;
				break;
		}
	}

	private void refreshButtons(int guiPage)
	{
		Creature creature = this.researchStation.getCreatureSelected();

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
		this.setButtonVisible(this.buttonList, 6, guiPage == 3);
		this.setButtonVisible(this.buttonList, 7, guiPage == 4);
		this.setButtonVisible(this.buttonList, 8, guiPage == 0);
		this.setButtonVisible(this.buttonList, 9, guiPage == 0);

		this.setButtonEnabled(this.buttonList, 0, !this.researchStation.shouldResearch());
		this.setButtonEnabled(this.buttonList, 1, guiPage == 2);

		if (guiPage == 2 && creature != null)
			this.setButtonEnabled(this.buttonList, 2, !this.researchStation.shouldResearch() && this.researchStation.getCreatureProgress(creature.getName()) > 49 && creature.canCreateCreature(this.researchStation.getCreatureProgressList()));
		else
			this.setButtonEnabled(this.buttonList, 2, false);

		if (guiPage == 3 && creature != null)
		{
			this.setButtonEnabled(this.buttonList, 3, true);
			this.setButtonEnabled(this.buttonList, 4, this.researchStation.getCreatureTextureID() > 0);

			if (this.researchStation.getCreatureGender())
			{
				HashMap<Byte, CreatureTexture> textureList = creature.getMaleCreatureTextures();
				if (textureList != null)
				{
					this.setButtonEnabled(this.buttonList, 5, this.researchStation.getCreatureTextureID() + 1 < textureList.size());

					CreatureTexture texture = textureList.get(this.researchStation.getCreatureTextureID());
					if (texture != null)
					{
						if (this.researchStation.getCreatureProgress(creature.getName()) >= texture.getTextureRequirement())
							this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
						else
							this.setButtonEnabled(this.buttonList, 6, false);
					}
				}
			}
			else
			{
				HashMap<Byte, CreatureTexture> textureList = creature.getFemaleCreatureTextures();
				if (textureList != null)
				{
					this.setButtonEnabled(this.buttonList, 5, this.researchStation.getCreatureTextureID() + 1 < textureList.size());

					CreatureTexture texture = textureList.get(this.researchStation.getCreatureTextureID());
					if (texture != null)
					{
						if (this.researchStation.getCreatureProgress(creature.getName()) >= texture.getTextureRequirement())
							this.setButtonEnabled(this.buttonList, 6, this.researchStation.getNutrients() >= creature.getNutrientsRequired());
						else
							this.setButtonEnabled(this.buttonList, 6, false);
					}
				}
			}
		}
		else
		{
			this.setButtonEnabled(this.buttonList, 3, false);
			this.setButtonEnabled(this.buttonList, 4, false);
			this.setButtonEnabled(this.buttonList, 5, false);
			this.setButtonEnabled(this.buttonList, 6, false);
		}
		this.setButtonEnabled(this.buttonList, 7, guiPage == 4);
		this.setButtonEnabled(this.buttonList, 8, guiPage == 0);
		this.setButtonEnabled(this.buttonList, 9, guiPage == 0);
	}

	private int getLargerBranchStringWidth()
	{
		String buttonName = StatCollector.translateToLocal("container.genetic_research_station.theropod.name");
		int buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		int buttonHalfWidthOld = buttonHalfWidth;

		buttonName = StatCollector.translateToLocal("container.genetic_research_station.mammals.name");
		buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		if (buttonHalfWidthOld < buttonHalfWidth)
			buttonHalfWidthOld = buttonHalfWidth;

		return buttonHalfWidthOld;
	}
}
