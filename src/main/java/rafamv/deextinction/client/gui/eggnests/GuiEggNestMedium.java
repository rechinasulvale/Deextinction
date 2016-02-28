package rafamv.deextinction.client.gui.eggnests;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.GuiBase;
import rafamv.deextinction.common.container.eggnests.ContainerEggNestMedium;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestKeys;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestMedium;

@SideOnly(Side.CLIENT)
public class GuiEggNestMedium extends GuiBase
{
	private static final ResourceLocation GUI_TEXTURE_MAIN = new ResourceLocation(DeExtinction.MODID, "textures/gui/eggnests/gui_egg_nest_medium.png");
	private static final ResourceLocation GUI_TEXTURE_WIDGETS = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_widgets.png");

	private final InventoryPlayer playerInventory;
	private EntityEggNestMedium eggNest;

	public GuiEggNestMedium(InventoryPlayer playerInventory, EntityEggNestMedium eggNest)
	{
		super(new ContainerEggNestMedium(playerInventory, eggNest.getInventoryBasic(), eggNest));
		this.playerInventory = playerInventory;
		this.eggNest = eggNest;
		this.xSize = 176;
		this.ySize = 190;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiEggNestMedium.GUI_TEXTURE_MAIN);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (this.eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_THERMOMETER))
		{
			this.mc.getTextureManager().bindTexture(GuiEggNestMedium.GUI_TEXTURE_WIDGETS);
			int warmth = this.eggNest.getWarmthScaled(54);
			this.drawTexturedModalRect(this.guiLeft + 120, this.guiTop + 72, 198, 55, 10, 6);
			this.drawTexturedModalRect(this.guiLeft + 120, this.guiTop + 72 - warmth, 198, 1, 10, warmth);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.writeCenteredTextInGui(this.eggNest.getDisplayName().getUnformattedText(), 6, 4210752);
		this.writeText(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);

		if (this.eggNest.getWatchableBoolean(EntityEggNestKeys.FLAG_THERMOMETER))
		{
			int warmth = this.eggNest.getWarmth();
			String warmthValue = null;
			if (warmth > 74)
				warmthValue = "warm";
			else if (warmth > 44)
				warmthValue = "slightly_warm";
			else if (warmth > 30)
				warmthValue = "neutral";
			else if (warmth > 16)
				warmthValue = "slightly_cool";
			else if (warmth > 8)
				warmthValue = "cool";
			else
				warmthValue = "cold";
			this.writeCenteredText(StatCollector.translateToLocal("container.egg_nest.warmth." + warmthValue), 72, 80, 4210752);
		}
	}
}
