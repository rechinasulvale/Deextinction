package rafamv.deextinction.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiContainer
{

	public GuiBase(Container container)
	{
		super(container);
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

	@Override
	protected abstract void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY);
}
