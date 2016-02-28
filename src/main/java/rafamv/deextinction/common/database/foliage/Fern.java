package rafamv.deextinction.common.database.foliage;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class Fern extends Foliage
{
	public static final String NAME = "fern";

	public Fern()
	{
		super(Fern.NAME);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.BUSHES_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(0, 0);
	}

	@Override
	public int getNutrientsRequired(byte variant)
	{
		return 50;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(Blocks.tallgrass, 1, BlockTallGrass.EnumType.FERN.getMeta());
	}

	@Override
	public void initRequiredItem()
	{

	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(Blocks.tallgrass, BlockTallGrass.EnumType.FERN.getMeta(), 8);
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
		this.registerVariant(Blocks.tallgrass, BlockTallGrass.EnumType.FERN.getMeta(), (byte) 0, (byte) 50);
	}
}
