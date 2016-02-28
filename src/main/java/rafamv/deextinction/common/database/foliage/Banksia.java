package rafamv.deextinction.common.database.foliage;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Banksia extends Foliage
{
	public static final String NAME = "banksia";

	public Banksia()
	{
		super(Banksia.NAME);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.BUSHES_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, 0);
	}

	@Override
	public int getNutrientsRequired(byte variant)
	{
		return 80;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEBlockRegistry.banksia_yellow, 1, 0);
	}

	@Override
	public void initRequiredItem()
	{
		this.registerRequiredItem(Blocks.tallgrass, BlockTallGrass.EnumType.FERN.getMeta());
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.banksia_fossilized, 8);
	}

	@Override
	public void initFoliagePeriods()
	{
		this.registerFoliagePeriod(Quaternary.NAME);
	}

	@Override
	public void initPreviousFoliage()
	{
		this.registerPreviousFoliage(Fern.NAME, 50);
	}

	@Override
	public void initVariants()
	{
		this.registerVariant(DEBlockRegistry.banksia_yellow, (byte) 0, (byte) 50);
		this.registerVariant(DEBlockRegistry.banksia_orange, (byte) 1, (byte) 75);
		this.registerVariant(DEBlockRegistry.banksia_red, (byte) 2, (byte) 100);
	}
}
