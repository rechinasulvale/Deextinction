package rafamv.deextinction.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.gui.gallery.GuiButtonGallery;
import rafamv.deextinction.client.gui.gallery.GuiGalleryButtonCreature;

@SideOnly(Side.CLIENT)
public abstract class GuiGallery extends GuiScreen
{
	private static final ResourceLocation GUI_TEXTURE_WIDGETS = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_widgets.png");
	private static final ResourceLocation GUI_TEXTURE_ACHIEVEMENT = new ResourceLocation("textures/gui/achievement/achievement_background.png");
	private static final ResourceLocation GUI_TEXTURE_BACKGROUND = new ResourceLocation(DeExtinction.MODID, "textures/gui/gui_gallery_background.png");
	protected GuiButtonGallery gallery;

	protected GuiScreen parentScreen;
	protected double prevDraggingX;
	protected double prevDraggingY;
	protected double draggingX;
	protected double draggingY;
	protected double field_146565_w;
	protected double field_146573_x;
	protected float screenScale = 1.5F;
	protected int prevMouseX;
	protected int prevMouseY;
	private int mouseDragClick;

	protected int guiLeftBorderOffset;
	protected int guiTopBorderOffset;
	protected int guiLeft;
	protected int guiTop;
	protected int xSize;
	protected int ySize;

	public GuiGallery()
	{

	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.gallery = this.initGallery();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		this.guiLeftBorderOffset = this.guiLeft + this.getGalleryBorderWidth();
		this.guiTopBorderOffset = this.guiTop + this.getGalleryBorderHeight();
	}

	public void changeGuiSize(int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		this.guiLeftBorderOffset = this.guiLeft + this.getGalleryBorderWidth();
		this.guiTopBorderOffset = this.guiTop + this.getGalleryBorderHeight();
	}

	@Override
	public void updateScreen()
	{
		if (this.gallery.enabled)
		{
			this.prevDraggingX = this.draggingX;
			this.prevDraggingY = this.draggingY;
			double d0 = this.field_146565_w - this.draggingX;
			double d1 = this.field_146573_x - this.draggingY;

			if (d0 * d0 + d1 * d1 < 4.0D)
			{
				this.draggingX += d0;
				this.draggingY += d1;
			}
			else
			{
				this.draggingX += d0 * 0.85D;
				this.draggingY += d1 * 0.85D;
			}
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		if (this.gallery.enabled)
		{
			if (Mouse.isButtonDown(0))
			{
				int guiLeftButtonOffset = this.guiLeft + this.getGalleryBorderWidth();
				int guiTopButtonOffset = this.guiTop + this.getGalleryBorderHeight();

				if ((this.mouseDragClick == 0 || this.mouseDragClick == 1) && mouseX >= guiLeftButtonOffset && mouseX < guiLeftButtonOffset + this.gallery.panelSizeX && mouseY >= guiTopButtonOffset && mouseY < guiTopButtonOffset + this.gallery.panelSizeY)
				{
					if (this.mouseDragClick == 0)
						this.mouseDragClick = 1;
					else
					{
						this.draggingX -= (double) ((float) (mouseX - this.prevMouseX) * this.screenScale);
						this.draggingY -= (double) ((float) (mouseY - this.prevMouseY) * this.screenScale);
						this.field_146565_w = this.prevDraggingX = this.draggingX;
						this.field_146573_x = this.prevDraggingY = this.draggingY;
					}
					this.prevMouseX = mouseX;
					this.prevMouseY = mouseY;
				}
			}
			else
				this.mouseDragClick = 0;

			int mouseWheel = Mouse.getDWheel();
			float prevScreenScale = this.screenScale;
			if (mouseWheel < 0)
				this.screenScale += 0.25F;
			else if (mouseWheel > 0)
				this.screenScale -= 0.25F;

			this.screenScale = MathHelper.clamp_float(this.screenScale, 1.5F, 4.0F);

			if (this.screenScale != prevScreenScale)
			{
				float xSizePrevScaled = prevScreenScale * (float) this.xSize;
				float ySizePrevScaled = prevScreenScale * (float) this.ySize;
				float xSizeScaled = this.screenScale * (float) this.xSize;
				float ySizeScaled = this.screenScale * (float) this.ySize;
				this.draggingX -= (double) ((xSizeScaled - xSizePrevScaled) * 0.5F);
				this.draggingY -= (double) ((ySizeScaled - ySizePrevScaled) * 0.5F);
				this.field_146565_w = this.prevDraggingX = this.draggingX;
				this.field_146573_x = this.prevDraggingY = this.draggingY;
			}

			if (this.gallery.visible)
				this.drawTreeScreen(mouseX, mouseY, partialTicks);
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if (this.gallery.enabled)
		{
			if (mouseButton == 0)
			{
				if (this.gallery.mouseClicked((int) ((mouseX - this.guiLeftBorderOffset) * this.screenScale), (int) ((mouseY - this.guiTopBorderOffset) * this.screenScale), mouseButton))
				{
					ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, this.gallery.getButtonSelected(), this.buttonList);
					if (MinecraftForge.EVENT_BUS.post(event))
						return;
					event.button.playPressSound(this.mc.getSoundHandler());
					this.specialActionPerformed(event.button);
					if (this.equals(this.mc.currentScreen))
						MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonList));
				}
			}
		}
	}

	protected void drawTreeScreen(int mouseX, int mouseY, float partialTicks)
	{
		int buttonWidthRender = this.getGalleryButtonWidthRender();
		int buttonHeightRender = this.getGalleryButtonHeightRender();

		int partialButtonPositionX = MathHelper.floor_double(this.prevDraggingX + (this.draggingX - this.prevDraggingX) * (double) partialTicks) - (int) ((this.gallery.panelSizeX - buttonWidthRender) / 2);
		int partialButtonPositionY = MathHelper.floor_double(this.prevDraggingY + (this.draggingY - this.prevDraggingY) * (double) partialTicks) - (int) ((this.gallery.panelSizeY - buttonHeightRender) / 2);

		this.zLevel = 0.0F;
		GlStateManager.depthFunc(518);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) this.guiLeftBorderOffset, (float) this.guiTopBorderOffset, -200.0F);
		GlStateManager.scale(1.0F / this.screenScale, 1.0F / this.screenScale, 1.0F);
		GlStateManager.enableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableColorMaterial();

		int finalX = (int) ((this.gallery.panelSizeX + ((partialButtonPositionX + 288) % 16)) * this.screenScale - ((partialButtonPositionX + 288) % 16));
		int finalY = (int) ((this.gallery.panelSizeY + ((partialButtonPositionY + 288) % 16)) * this.screenScale - ((partialButtonPositionY + 288) % 16));

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GuiGallery.GUI_TEXTURE_BACKGROUND);
		this.drawTexturedModalRect(0, 0, 0, 0, finalX, finalY);

		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
		this.mc.getTextureManager().bindTexture(GuiGallery.GUI_TEXTURE_ACHIEVEMENT);
		int buttonWidth = this.getGalleryButtonWidth();
		int buttonHeight = this.getGalleryButtonHeight();
		int parentButtonPosX = 0;
		int parentButtonPosY = 0;
		int buttonPosX = 0;
		int buttonPosY = 0;

		List<GuiGalleryButtonCreature> galleryList = this.gallery.list;
		if (galleryList != null)
		{
			for (int i = 0; i < galleryList.size(); i++)
			{
				GuiGalleryButtonCreature galleryButton0 = galleryList.get(i);
				if (galleryButton0 != null && galleryButton0.parents != null)
				{
					for (GalleryPosition parentButton : galleryButton0.parents)
					{
						if (parentButton != null)
						{
							parentButtonPosX = parentButton.column * this.getGallerySpacingX() - partialButtonPositionX + (int) (buttonWidth / 2);
							parentButtonPosY = parentButton.row * this.getGallerySpacingY() - partialButtonPositionY + (int) (buttonHeight / 2);

							buttonPosX = galleryButton0.displayColumn * this.getGallerySpacingX() - partialButtonPositionX + (int) (buttonWidth / 2);
							buttonPosY = galleryButton0.displayRow * this.getGallerySpacingY() - partialButtonPositionY + (int) (buttonHeight / 2);

							this.drawHorizontalLine(buttonPosX, parentButtonPosX, buttonPosY, -6250336);
							this.drawVerticalLine(parentButtonPosX, buttonPosY, parentButtonPosY, -6250336);
						}
					}
				}
			}

			RenderHelper.enableGUIStandardItemLighting();
			GlStateManager.disableLighting();
			GlStateManager.enableRescaleNormal();
			GlStateManager.enableColorMaterial();
			int drawingButtonPosX = 0;
			int drawingButtonPosY = 0;
			for (int j = 0; j < galleryList.size(); j++)
			{
				GuiGalleryButtonCreature galleryButton0 = (GuiGalleryButtonCreature) galleryList.get(j);
				if (galleryButton0 != null)
				{
					drawingButtonPosX = galleryButton0.displayColumn * this.getGallerySpacingX() - partialButtonPositionX;
					drawingButtonPosY = galleryButton0.displayRow * this.getGallerySpacingY() - partialButtonPositionY;

					if (drawingButtonPosX >= -buttonWidthRender && drawingButtonPosY >= -buttonHeightRender && (float) drawingButtonPosX <= this.gallery.panelSizeX * this.screenScale && (float) drawingButtonPosY <= this.gallery.panelSizeY * this.screenScale)
					{
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						this.mc.getTextureManager().bindTexture(GuiGallery.GUI_TEXTURE_WIDGETS);

						GlStateManager.enableBlend();
						if (galleryButton0.isResearchUnlocked)
						{
							if (galleryButton0.isResearchDone)
								this.drawTexturedModalRect(drawingButtonPosX, drawingButtonPosY, 114, 111, buttonWidthRender, buttonHeightRender);
							else
								this.drawTexturedModalRect(drawingButtonPosX, drawingButtonPosY, 69, 111, buttonWidthRender, buttonHeightRender);
						}
						else
							this.drawTexturedModalRect(drawingButtonPosX, drawingButtonPosY, 24, 111, buttonWidthRender, buttonHeightRender);
						GlStateManager.disableBlend();
						GlStateManager.disableLighting();
						GlStateManager.enableCull();
						this.itemRender.renderItemAndEffectIntoGUI(galleryButton0.itemStack, drawingButtonPosX + galleryButton0.imageOffset, drawingButtonPosY + galleryButton0.imageOffset);
						GlStateManager.blendFunc(770, 771);
						GlStateManager.disableLighting();

						galleryButton0.visible = true;
						galleryButton0.positionX = drawingButtonPosX;
						galleryButton0.positionY = drawingButtonPosY;

						String text = galleryButton0.research + "%";
						this.mc.fontRendererObj.drawString(text, drawingButtonPosX + buttonWidth + 2 - (int) (this.mc.fontRendererObj.getStringWidth(text) / 2), drawingButtonPosY + buttonHeight - 2, galleryButton0.color);

						int mouseXScaled = (int) ((mouseX - this.guiLeftBorderOffset) * this.screenScale);
						int mouseYScaled = (int) ((mouseY - this.guiTopBorderOffset) * this.screenScale);
						if (mouseXScaled >= drawingButtonPosX && mouseYScaled >= drawingButtonPosY && mouseXScaled < drawingButtonPosX + buttonWidth && mouseYScaled < drawingButtonPosY + buttonHeight)
						{
							int textWidth = this.mc.fontRendererObj.getStringWidth(galleryButton0.displayName);
							int textPosX = drawingButtonPosX + (int) ((buttonWidth - textWidth) / 2);
							int textPosY = drawingButtonPosY - (galleryButton0.isResearchDone ? 12 : 10);
							this.drawGradientRect(textPosX - 3, textPosY - 3, textPosX + textWidth + 2, textPosY + 11, -6250336, -6250336);
							this.drawGradientRect(textPosX - 2, textPosY - 2, textPosX + textWidth + 1, textPosY + 10, 0xf0100010, 0xf0100010);
							this.drawString(this.mc.fontRendererObj, galleryButton0.displayName, textPosX, textPosY, galleryButton0.color);
						}
					}
					else
						galleryButton0.visible = false;
				}
			}
		}

		GlStateManager.disableDepth();
		GlStateManager.enableBlend();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiGallery.GUI_TEXTURE_ACHIEVEMENT);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		this.zLevel = 0.0F;
		GlStateManager.depthFunc(515);
		GlStateManager.disableDepth();
		GlStateManager.enableTexture2D();
		super.drawScreen(mouseX, mouseY, partialTicks);
		GlStateManager.enableDepth();
		GlStateManager.enableLighting();
		RenderHelper.disableStandardItemLighting();
	}

	/** Sets a button to the list. */
	protected void registerButton(List<GuiButton> buttonList, int id, int xPos, int yPos, String buttonName)
	{
		int buttonWidth = this.fontRendererObj.getStringWidth(buttonName) + 10;
		this.buttonList.add(new GuiButton(id, this.guiLeft + xPos, this.guiTop + yPos, buttonWidth, 20, buttonName));
	}

	/** Sets a button to the list. */
	protected void registerCenteredButton(List<GuiButton> buttonList, int id, int yPos, String buttonName)
	{
		int buttonHalfWidth = this.fontRendererObj.getStringWidth(buttonName) / 2 + 5;
		this.buttonList.add(new GuiButton(id, this.guiLeft + this.xSize / 2 - buttonHalfWidth, this.guiTop + yPos, 2 * buttonHalfWidth, 20, buttonName));
	}

	/** Sets a button visible. */
	protected void setButtonVisible(List<GuiButton> buttonList, int id, boolean flag)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.visible = flag;
		}
	}

	/** Sets a button enabled. */
	protected void setButtonEnabled(List<GuiButton> buttonList, int id, boolean flag)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.enabled = flag;
		}
	}

	/** Sets a button visible and enabled. */
	protected void setButtonVisibleAndEnabled(List<GuiButton> buttonList, int id, boolean flag)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.enabled = flag;
			button.visible = flag;
		}
	}

	/** Flips the state of a button: visible. */
	protected void flipButtonVisible(List<GuiButton> buttonList, int id)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.visible = !button.visible;
		}
	}

	/** Flips the state of a button: enabled. */
	protected void flipButtonEnabled(List<GuiButton> buttonList, int id)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.enabled = !button.enabled;
		}
	}

	/** Flips the state of a button: visible and enabled. */
	protected void flipButtonVisibleAndEnabled(List<GuiButton> buttonList, int id)
	{
		if (id < buttonList.size() && buttonList.get(id) instanceof GuiButton)
		{
			GuiButton button = buttonList.get(id);
			button.enabled = !button.enabled;
			button.visible = !button.visible;
		}
	}

	protected void writeText(String text, int xPos, int yPos, int color)
	{
		if (text != null)
			this.fontRendererObj.drawString(text, xPos, yPos, color);
	}

	protected void writeCenteredText(String text, int xPos, int yPos, int color)
	{
		if (text != null)
		{
			int textHalfWidth = this.fontRendererObj.getStringWidth(text) / 2;
			this.fontRendererObj.drawString(text, xPos - textHalfWidth, yPos, color);
		}
	}

	protected void writeCenteredTextInGui(String text, int yPos, int color)
	{
		if (text != null)
		{
			int textHalfWidth = this.fontRendererObj.getStringWidth(text) / 2;
			this.fontRendererObj.drawString(text, this.xSize / 2 - textHalfWidth, yPos, color);
		}
	}

	protected void writeTextWithScale(String text, int xPos, int yPos, int color, float scale)
	{
		if (text != null)
		{
			GL11.glScalef(scale, scale, 1.0F);
			this.fontRendererObj.drawString(text, (int) (xPos / scale), (int) (yPos / scale), color);
			GL11.glScalef(1 / scale, 1 / scale, 1.0F);
		}
	}

	protected void writeCenteredTextWithScale(String text, int xPos, int yPos, int color, float scale)
	{
		if (text != null)
		{
			GL11.glScalef(scale, scale, 1.0F);
			this.fontRendererObj.drawString(text, (int) (xPos / scale) - this.fontRendererObj.getStringWidth(text) / 2, (int) (yPos / scale), color);
			GL11.glScalef(1 / scale, 1 / scale, 1.0F);
		}
	}

	protected void writeCenteredTextInGuiWithScale(String text, int xPos, int yPos, int color, float scale)
	{
		if (text != null)
		{
			GL11.glScalef(scale, scale, 1.0F);
			this.fontRendererObj.drawString(text, (int) (this.xSize / (2 * scale)) - this.fontRendererObj.getStringWidth(text) / 2, (int) (yPos / scale), color);
			GL11.glScalef(1 / scale, 1 / scale, 1.0F);
		}
	}

	protected List<String> getTextInLinesCharacterSize(String text, int numberOfCharsPerLine)
	{
		if (text != null && text != "")
		{
			List<String> text_lines = new ArrayList<String>();
			int lastIndex = 0;
			String line = "";
			for (int index = 0; index < text.length(); index++)
			{
				Character charAt = text.charAt(index);
				if ((charAt.equals(' ') || index == text.length() - 1) && index > lastIndex)
				{
					String word = text.substring(lastIndex, index + 1);
					if (line.length() + word.length() < numberOfCharsPerLine)
						line += word + " ";
					else
					{
						if (!line.isEmpty())
							text_lines.add(line);
						line = word;
					}
					lastIndex = index + 1;
				}
			}
			text_lines.add(line);
			return text_lines;
		}
		return null;
	}

	protected List<String> getTextInLinesJustifiedCharacterSize(String text, int numberOfCharsPerLine)
	{
		if (text != null && text != "")
		{
			List<String> text_lines = this.getTextInLinesCharacterSize(text, numberOfCharsPerLine);
			if (!text_lines.isEmpty())
			{
				List<String> final_text = new ArrayList<String>();
				for (int i = 0; i < text_lines.size() - 1; i++)
				{
					String line = text_lines.get(i);
					int numberOfCharsMissing = numberOfCharsPerLine - line.length();
					if (numberOfCharsMissing > 0)
						for (int index = 0; index < line.length(); index++)
						{
							Character charAt = line.charAt(index);
							if (charAt.equals(' ') && numberOfCharsMissing > 0)
							{
								line = line.substring(0, index) + " " + line.substring(index);
								numberOfCharsMissing--;
								index++;
							}
						}
					final_text.add(line);
				}
				final_text.add(text_lines.get(text_lines.size() - 1));
				return final_text;
			}
		}
		return null;
	}

	protected List<String> getTextInLinesStringSize(String text, int lineSize)
	{
		if (text != null && text != "")
		{
			List<String> text_lines = new ArrayList<String>();
			int lastIndex = 0;
			String line = "";
			for (int index = 0; index < text.length(); index++)
			{
				Character charAt = text.charAt(index);
				if ((charAt.equals(' ') || index == text.length() - 1) && index > lastIndex)
				{
					String word = text.substring(lastIndex, index + 1);
					if (this.fontRendererObj.getStringWidth(line + " " + word) < lineSize)
						line += word + " ";
					else
					{
						if (!line.isEmpty())
							text_lines.add(line);
						line = word;
					}
					lastIndex = index + 1;
				}
			}
			text_lines.add(line);
			return text_lines;
		}
		return null;
	}

	protected List<String> getTextInLinesJustifiedStringSize(String text, int lineSize)
	{
		if (text != null && text != "")
		{
			List<String> text_lines = this.getTextInLinesStringSize(text, lineSize);
			if (!text_lines.isEmpty())
			{
				List<String> final_text = new ArrayList<String>();
				int spaceSize = this.fontRendererObj.getStringWidth("A");
				for (int i = 0; i < text_lines.size() - 1; i++)
				{
					String line = text_lines.get(i);
					int missingSpaces = (lineSize - this.fontRendererObj.getStringWidth(line)) / spaceSize;
					if (missingSpaces > 0)
						for (int index = 0; index < line.length(); index++)
						{
							Character charAt = line.charAt(index);
							if (charAt.equals(' ') && missingSpaces > 0)
							{
								line = line.substring(0, index) + " " + line.substring(index);
								missingSpaces--;
								index++;
							}
						}
					final_text.add(line);
				}
				final_text.add(text_lines.get(text_lines.size() - 1));
				return final_text;
			}
		}
		return null;
	}

	protected void renderTooltip(int x, int y, List<String> tooltipData, int color, int color2)
	{
		boolean lighting = GL11.glGetBoolean(GL11.GL_LIGHTING);
		if (lighting)
			RenderHelper.disableStandardItemLighting();

		if (!tooltipData.isEmpty())
		{
			int var5 = 0;
			int var6;
			int var7;
			FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
			for (var6 = 0; var6 < tooltipData.size(); ++var6)
			{
				var7 = fontRenderer.getStringWidth(tooltipData.get(var6));
				if (var7 > var5)
					var5 = var7;
			}
			var6 = x + 12;
			var7 = y - 12;
			int var9 = 8;
			if (tooltipData.size() > 1)
				var9 += 2 + (tooltipData.size() - 1) * 10;
			float z = 300F;
			this.drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
			this.drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
			this.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
			this.drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
			this.drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);
			int var12 = (color & 0xFFFFFF) >> 1 | color & -16777216;
			this.drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
			this.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
			this.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
			this.drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);

			GL11.glDisable(GL11.GL_DEPTH_TEST);
			for (int var13 = 0; var13 < tooltipData.size(); ++var13)
			{
				String var14 = tooltipData.get(var13);
				fontRenderer.drawStringWithShadow(var14, var6, var7, -1);
				if (var13 == 0)
					var7 += 2;
				var7 += 10;
			}
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		if (!lighting)
			RenderHelper.disableStandardItemLighting();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	protected void drawGradientRect(int par1, int par2, float z, int par3, int par4, int par5, int par6)
	{
		float var7 = (par5 >> 24 & 255) / 255F;
		float var8 = (par5 >> 16 & 255) / 255F;
		float var9 = (par5 >> 8 & 255) / 255F;
		float var10 = (par5 & 255) / 255F;
		float var11 = (par6 >> 24 & 255) / 255F;
		float var12 = (par6 >> 16 & 255) / 255F;
		float var13 = (par6 >> 8 & 255) / 255F;
		float var14 = (par6 & 255) / 255F;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
		worldRenderer.startDrawingQuads();
		worldRenderer.setColorRGBA_F(var8, var9, var10, var7);
		worldRenderer.addVertex(par3, par2, z);
		worldRenderer.addVertex(par1, par2, z);
		worldRenderer.setColorRGBA_F(var12, var13, var14, var11);
		worldRenderer.addVertex(par1, par4, z);
		worldRenderer.addVertex(par3, par4, z);
		Tessellator.getInstance().draw();
		GL11.glShadeModel(GL11.GL_FLAT);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	protected void drawTexturedModalRect(int par1, int par2, float z, int par3, int par4, int par5, int par6)
	{
		this.drawTexturedModalRect(par1, par2, z, par3, par4, par5, par6, 0.00390625F, 0.00390625F);
	}

	protected void drawTexturedModalRect(int par1, int par2, float z, int par3, int par4, int par5, int par6, float f, float f1)
	{
		WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
		worldRenderer.startDrawingQuads();
		worldRenderer.addVertexWithUV(par1 + 0, par2 + par6, z, (par3 + 0) * f, (par4 + par6) * f1);
		worldRenderer.addVertexWithUV(par1 + par5, par2 + par6, z, (par3 + par5) * f, (par4 + par6) * f1);
		worldRenderer.addVertexWithUV(par1 + par5, par2 + 0, z, (par3 + par5) * f, (par4 + 0) * f1);
		worldRenderer.addVertexWithUV(par1 + 0, par2 + 0, z, (par3 + 0) * f, (par4 + 0) * f1);
		Tessellator.getInstance().draw();
	}

	protected abstract void changeGalleryBranch(byte branchID);

	protected abstract void specialActionPerformed(GuiButton button);

	protected abstract void sendMessageGuiBranch(byte guiBranch);

	protected abstract GuiButtonGallery initGallery();

	protected abstract int getGalleryButtonWidth();

	protected abstract int getGalleryButtonHeight();

	protected abstract int getGalleryButtonWidthRender();

	protected abstract int getGalleryButtonHeightRender();

	protected abstract int getGallerySpacingX();

	protected abstract int getGallerySpacingY();

	protected abstract int getGalleryBorderWidth();

	protected abstract int getGalleryBorderHeight();
}
