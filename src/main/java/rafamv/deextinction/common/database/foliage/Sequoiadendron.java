package rafamv.deextinction.common.database.foliage;

import java.util.Arrays;

import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.Tree;
import rafamv.deextinction.common.database.periods.Neogene;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;
import rafamv.deextinction.common.world.trees.WorldGenSequoiadendronGrayHuge;
import rafamv.deextinction.common.world.trees.WorldGenSequoiadendronGraySmall;
import rafamv.deextinction.common.world.trees.WorldGenSequoiadendronOrangeHuge;
import rafamv.deextinction.common.world.trees.WorldGenSequoiadendronOrangeSmall;

public class Sequoiadendron extends Tree
{
	public static final String NAME = "sequoiadendron";

	public Sequoiadendron()
	{
		super(Sequoiadendron.NAME);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.TREES_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, 0);
	}

	@Override
	public int getNutrientsRequired(byte variant)
	{
		return 500;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEBlockRegistry.sequoiadendron_gray_sapling);
	}

	@Override
	public void initRequiredItem()
	{
		this.registerRequiredItem(Blocks.sapling, BlockPlanks.EnumType.SPRUCE.getMetadata());
	}

	@Override
	public void initFossilItems()
	{
		this.registerRequiredItem(DEItemRegistry.sequoiadendron_fossilized, 8);
	}

	@Override
	public void initFoliagePeriods()
	{
		this.registerFoliagePeriod(Neogene.NAME);
	}

	@Override
	public void initPreviousFoliage()
	{
		this.registerPreviousFoliage(Spruce.NAME, 50);
	}

	@Override
	public void initVariants()
	{
		this.registerTreeVariant(DEBlockRegistry.sequoiadendron_gray_sapling, 0, (byte) 0, (byte) 50, Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_gray_log)), Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_leaves)), null);
		this.registerTreeVariant(DEBlockRegistry.sequoiadendron_orange_sapling, 0, (byte) 1, (byte) 50, Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_orange_log)), Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_leaves)), null);
		this.registerTreeVariant(DEBlockRegistry.sequoiadendron_gray_sapling_huge, 0, (byte) 2, (byte) 100, Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_gray_log)), Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_leaves)), null);
		this.registerTreeVariant(DEBlockRegistry.sequoiadendron_orange_sapling_huge, 0, (byte) 3, (byte) 100, Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_orange_log)), Arrays.asList(new ItemStack(DEBlockRegistry.sequoiadendron_leaves)), null);
	}

	@Override
	public void initTreeGen()
	{
		this.registerTreeGeneration(new WorldGenSequoiadendronGraySmall(), (byte) 0);
		this.registerTreeGeneration(new WorldGenSequoiadendronOrangeSmall(), (byte) 1);
		this.registerTreeGeneration(new WorldGenSequoiadendronGrayHuge(), (byte) 2);
		this.registerTreeGeneration(new WorldGenSequoiadendronOrangeHuge(), (byte) 3);
	}
}
