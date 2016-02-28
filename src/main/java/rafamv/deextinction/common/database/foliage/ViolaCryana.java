package rafamv.deextinction.common.database.foliage;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class ViolaCryana extends Foliage
{
	public static final String NAME = "viola_cryana";

	public ViolaCryana()
	{
		super(ViolaCryana.NAME);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.BUSHES_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, 1);
	}

	@Override
	public int getNutrientsRequired(byte variant)
	{
		return 80;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEBlockRegistry.gypsum_brick, 1, 0);
	}

	@Override
	public void initRequiredItem()
	{
		this.registerRequiredItem(Items.wheat_seeds, 0);
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEBlockRegistry.gypsum_block, 8);
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
		this.registerVariant(DEBlockRegistry.gypsum_brick, (byte) 0, (byte) 50);
	}
}
