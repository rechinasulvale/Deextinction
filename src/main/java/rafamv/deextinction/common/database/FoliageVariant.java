package rafamv.deextinction.common.database;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class FoliageVariant
{
	private String variantName;
	private byte variantID;
	private byte variantRequirement;
	private ItemStack sapling;

	public FoliageVariant(byte variantID, String foliageName, byte variantRequirement, Block sapling, int amount, int metadata)
	{
		this.variantName = foliageName + ".variant_" + variantID;
		this.variantID = variantID;
		this.variantRequirement = variantRequirement;
		this.sapling = new ItemStack(sapling, amount > 0 ? amount : 1, metadata);
	}

	public FoliageVariant(byte variantID, String foliageName, byte variantRequirement, Block sapling, int metadata)
	{
		this(variantID, foliageName, variantRequirement, sapling, 1, metadata);
	}

	public FoliageVariant(byte variantID, String foliageName, byte variantRequirement, Block sapling)
	{
		this(variantID, foliageName, variantRequirement, sapling, 1, 0);
	}

	public String getVariantName()
	{
		return StatCollector.translateToLocal("tile." + this.variantName);
	}

	public String getVariantUnlocalizedName()
	{
		return "tile." + this.variantName;
	}

	public int getVariantID()
	{
		return this.variantID;
	}

	public int getVariantRequirement()
	{
		return this.variantRequirement;
	}

	public ItemStack getSapling()
	{
		return this.sapling;
	}
}
