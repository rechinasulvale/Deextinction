package rafamv.deextinction.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.container.ContainerMicroscope;
import rafamv.deextinction.common.tileentity.TileMicroscope;

@SideOnly(Side.CLIENT)
public class GuiMicroscope extends GuiBase
{
	private static final ResourceLocation GUI_TEXTURE_MAIN = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_microscope.png");
	private static final ResourceLocation GUI_TEXTURE_BLOOD = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_microscope_blood.png");

	private final InventoryPlayer playerInventory;
	private TileMicroscope microscope;
	private int guiTick;

	public GuiMicroscope(InventoryPlayer playerInventory, TileMicroscope microscope)
	{
		super(new ContainerMicroscope(playerInventory, microscope));
		this.playerInventory = playerInventory;
		this.microscope = microscope;
		this.xSize = 176;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiMicroscope.GUI_TEXTURE_MAIN);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		if (this.microscope.isAnalyzing())
		{
			this.mc.getTextureManager().bindTexture(GuiMicroscope.GUI_TEXTURE_BLOOD);
			this.drawTexturedModalRect(this.guiLeft + 19, this.guiTop + 27, 0, 48, 138, 48);
			if (this.guiTick++ == 39)
				this.guiTick = 0;
		}
		else
			this.guiTick = 0;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String text = this.microscope.getDisplayName().getUnformattedText();
		this.writeCenteredText(text, this.xSize / 2, 6, 4210752);
		text = this.playerInventory.getDisplayName().getUnformattedText();
		this.writeText(text, 8, this.ySize - 96 + 2, 4210752);

		this.drawCreatureData();
	}

	private void drawCreatureData()
	{
		if (this.microscope.getAnalyzingProgress() > 0)
		{
			int progress = this.microscope.getAnalyzingProgressScaled(100);
			List<String> textList = new ArrayList<String>();

			String analizingText = StatCollector.translateToLocal("container.microscope.analizing");
			if ((int) (this.guiTick / 6) % 3 == 0)
				analizingText += "...";
			else if ((int) (this.guiTick / 6) % 2 == 0)
				analizingText += "..";
			else
				analizingText += ".";

			if (progress > 0)
				textList.add(StatCollector.translateToLocal("container.microscope.creature_name") + ": " + StatCollector.translateToLocal(this.microscope.getCreatureName()));
			else
				textList.add(analizingText);

			if (progress > 20)
				textList.add(StatCollector.translateToLocal("container.microscope.creature_gender") + ": " + (this.microscope.getCreatureGender() ? StatCollector.translateToLocal("container.microscope.male") : StatCollector.translateToLocal("container.microscope.female")));
			else
				textList.add(analizingText);

			if (progress > 40)
				textList.add(StatCollector.translateToLocal("container.microscope.creature_age") + ": " + this.getCreatureAge(this.microscope.getCreatureAge()));
			else
				textList.add(analizingText);

			if (progress > 60)
				textList.add(StatCollector.translateToLocal("container.microscope.creature_status") + ": " + StatCollector.translateToLocal(this.microscope.getCreatureStatus()));
			else
				textList.add(analizingText);

			if (progress > 80)
				textList.add(StatCollector.translateToLocal("container.microscope.creature_texture") + ": " + StatCollector.translateToLocal(this.microscope.getCreatureTexture()));
			else
				textList.add(analizingText);

			if (!textList.isEmpty())
			{
				int heightOffset = 0;

				GL11.glDisable(GL11.GL_DEPTH_TEST);
				for (int j = 0; j < textList.size(); j++)
				{
					String text = textList.get(j);
					int stringWidth = this.fontRendererObj.getStringWidth(text);
					this.fontRendererObj.drawSplitString(text, 20, 84 + heightOffset, 140, 4210752);
					while (stringWidth > 140)
					{
						stringWidth -= 140;
						heightOffset += 12;
					}
					heightOffset += 12;
				}
				GL11.glEnable(GL11.GL_DEPTH_TEST);

			}
		}
		else
			this.writeCenteredTextInGui(StatCollector.translateToLocal("container.microscope.no_data"), 84, 4210752);
	}

	public String getCreatureAge(int age)
	{
		int years = (int) (age / 8640000);
		int months = (int) (age / 720000) - 12 * years;
		int days = (int) (age / 24000) - 30 * (int) (age / 720000);

		String yearString = "";
		String monthString = "";
		String dayString = "";

		if (years > 0)
		{
			if (months > 0 || days > 0)
			{
				if (years == 1)
					yearString = years + " " + StatCollector.translateToLocal("entity.info.year") + ", ";
				else
					yearString = years + " " + StatCollector.translateToLocal("entity.info.years") + ", ";
			}
			else
			{
				if (years == 1)
					yearString = years + " " + StatCollector.translateToLocal("entity.info.year");
				else
					yearString = years + " " + StatCollector.translateToLocal("entity.info.years");
			}
		}

		if (months > 0)
		{
			if (days > 0)
			{
				if (months == 1)
					monthString = months + " " + StatCollector.translateToLocal("entity.info.month") + ", ";
				else
					monthString = months + " " + StatCollector.translateToLocal("entity.info.months") + ", ";
			}
			else
			{
				if (months == 1)
					monthString = months + " " + StatCollector.translateToLocal("entity.info.month");
				else
					monthString = months + " " + StatCollector.translateToLocal("entity.info.months");
			}
		}

		if (days == 0)
			dayString = StatCollector.translateToLocal("entity.info.newborn");
		else if (days == 1)
			dayString = days + " " + StatCollector.translateToLocal("entity.info.day");
		else
			dayString = days + " " + StatCollector.translateToLocal("entity.info.days");

		return (yearString + monthString + dayString);
	}
}
