package rafamv.deextinction.common.database;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import rafamv.deextinction.DeExtinction;

public class CreatureTexture
{
	private String textureName;
	private byte textureID;
	private byte textureRequirement;
	private ResourceLocation resourceLocation;

	public CreatureTexture(byte textureID, String creatureName, String gender, byte textureRequirement)
	{
		this.textureName = creatureName + "." + gender + ".texture_" + textureID;
		this.textureID = textureID;
		this.textureRequirement = textureRequirement;
		this.resourceLocation = new ResourceLocation(DeExtinction.MODID, "textures/entities/creature/" + creatureName + "/" + creatureName + "_" + gender + "_" + textureID + ".png");
	}

	public String getTextureName()
	{
		return StatCollector.translateToLocal("entity." + this.textureName);
	}

	public String getTextureUnlocalizedName()
	{
		return "entity." + this.textureName;
	}

	public int getTextureID()
	{
		return this.textureID;
	}

	public int getTextureRequirement()
	{
		return this.textureRequirement;
	}

	public ResourceLocation getResourceLocation()
	{
		return this.resourceLocation;
	}
}
