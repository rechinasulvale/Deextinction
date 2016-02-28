package rafamv.deextinction.common.block.foliage;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public enum TreeType implements IStringSerializable
{
	TEST(0, "tree_test", Blocks.sapling, new ItemStack(Items.stick));

	private TreeType(int meta, String name, Block sapling, ItemStack specialDrop)
	{
		this.meta = meta;
		this.name = name;
		this.sapling = sapling;
		this.specialDrop = specialDrop;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	public String getUnlocalizedName()
	{
		return this.name;
	}

	public int getMetadata()
	{
		return this.meta;
	}

	@Override
	public String toString()
	{
		return this.name;
	}

	public Block getSapling()
	{
		return this.sapling;
	}

	public ItemStack getSpecialDrop()
	{
		return this.specialDrop;
	}

	public static TreeType byMetadata(int meta)
	{
		if (meta < 0 || meta >= TreeType.META_LOOKUP.length)
			meta = 0;
		return TreeType.META_LOOKUP[meta];
	}

	private final int meta;
	private final String name;
	private final Block sapling;
	private final ItemStack specialDrop;
	private static final TreeType[] META_LOOKUP = new TreeType[values().length];
	static
	{
		TreeType[] enumValues = values();
		int enumListSize = enumValues.length;

		for (int i = 0; i < enumListSize; i++)
		{
			TreeType enumValue = enumValues[i];
			TreeType.META_LOOKUP[enumValue.getMetadata()] = enumValue;
		}
	}
}
