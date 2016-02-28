package rafamv.deextinction.common.database.foliage;

import java.util.Arrays;

import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.Tree;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class Spruce extends Tree
{
	public static final String NAME = "spruce";

	public Spruce()
	{
		super(Spruce.NAME);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.TREES_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(0, 0);
	}

	@Override
	public int getNutrientsRequired(byte variant)
	{
		return variant == 0 ? 200 : 500;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(Blocks.sapling, 1, BlockPlanks.EnumType.SPRUCE.getMetadata());
	}

	@Override
	public void initRequiredItem()
	{

	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(Blocks.sapling, BlockPlanks.EnumType.SPRUCE.getMetadata(), 8);
	}

	@Override
	public void initFoliagePeriods()
	{

	}

	@Override
	public void initPreviousFoliage()
	{

	}

	@Override
	public void initVariants()
	{
		this.registerTreeVariant(Blocks.sapling, BlockPlanks.EnumType.SPRUCE.getMetadata(), (byte) 0, (byte) 50, Arrays.asList(new ItemStack(Blocks.log, BlockPlanks.EnumType.SPRUCE.getMetadata())), Arrays.asList(new ItemStack(Blocks.leaves, BlockPlanks.EnumType.SPRUCE.getMetadata())),
				Arrays.asList(new ItemStack(Items.apple)));
	}

	@Override
	public void initTreeGen()
	{

	}
}
