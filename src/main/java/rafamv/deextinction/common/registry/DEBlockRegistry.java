package rafamv.deextinction.common.registry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.block.BlockBotanicalResearchStation;
import rafamv.deextinction.common.block.BlockCleaningTable;
import rafamv.deextinction.common.block.BlockClearGlass;
import rafamv.deextinction.common.block.BlockFossilCreature;
import rafamv.deextinction.common.block.BlockFossilFoliage;
import rafamv.deextinction.common.block.BlockGeneticResearchStation;
import rafamv.deextinction.common.block.BlockGypsumBlock;
import rafamv.deextinction.common.block.BlockGypsumBrick;
import rafamv.deextinction.common.block.BlockGypsumStairs;
import rafamv.deextinction.common.block.BlockLowSecurityPole;
import rafamv.deextinction.common.block.BlockLowSecurityWire;
import rafamv.deextinction.common.block.BlockMicroscope;
import rafamv.deextinction.common.block.BlockPlasterJacket;
import rafamv.deextinction.common.block.BlockWaterPurifier;
import rafamv.deextinction.common.block.BlockWoodenSecurityPole;
import rafamv.deextinction.common.block.BlockWoodenSecurityWire;
import rafamv.deextinction.common.block.CustomStateMap;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedAquaticPlant;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedBush;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedLeaves;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedLogs;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedPlanks;
import rafamv.deextinction.common.block.foliage.BlockDeExtinctedSapling;
import rafamv.deextinction.common.database.foliage.Banksia;
import rafamv.deextinction.common.database.foliage.Chara;
import rafamv.deextinction.common.database.foliage.Sequoiadendron;
import rafamv.deextinction.common.tileentity.TileBotanicalResearchStation;
import rafamv.deextinction.common.tileentity.TileCleaningTable;
import rafamv.deextinction.common.tileentity.TileGeneticResearchStation;
import rafamv.deextinction.common.tileentity.TileMicroscope;
import rafamv.deextinction.common.tileentity.TilePlasterJacket;
import rafamv.deextinction.common.tileentity.TileWaterFilter;

public class DEBlockRegistry
{
	public static final BlockGeneticResearchStation genetic_research_station = new BlockGeneticResearchStation();
	public static final BlockBotanicalResearchStation botanical_research_station = new BlockBotanicalResearchStation();
	public static final BlockCleaningTable cleaning_table = new BlockCleaningTable();
	public static final BlockMicroscope microscope = new BlockMicroscope();
	public static final BlockPlasterJacket plaster_jacket = new BlockPlasterJacket();
	public static final BlockWaterPurifier water_filter = new BlockWaterPurifier();

	public static final BlockFossilCreature creature_fossil_stone_block = new BlockFossilCreature("stone");
	public static final BlockFossilCreature creature_fossil_granite_block = new BlockFossilCreature("granite");
	public static final BlockFossilCreature creature_fossil_diorite_block = new BlockFossilCreature("diorite");
	public static final BlockFossilCreature creature_fossil_andesite_block = new BlockFossilCreature("andesite");
	public static final BlockFossilCreature creature_fossil_sandstone_block = new BlockFossilCreature("sandstone");
	public static final BlockFossilFoliage foliage_fossil_stone_block = new BlockFossilFoliage("stone");

	public static final BlockGypsumBlock gypsum_block = new BlockGypsumBlock();
	public static final BlockGypsumBrick gypsum_brick = new BlockGypsumBrick();
	public static final BlockGypsumStairs gypsum_stairs = new BlockGypsumStairs();

	public static final BlockClearGlass clear_glass = new BlockClearGlass();

	public static final BlockLowSecurityPole security_pole_low = new BlockLowSecurityPole();
	public static final BlockLowSecurityWire security_wire_low = new BlockLowSecurityWire();

	public static final BlockWoodenSecurityPole security_pole_oak = new BlockWoodenSecurityPole("oak");
	public static final BlockWoodenSecurityWire security_wire_oak = new BlockWoodenSecurityWire("oak");
	public static final BlockWoodenSecurityPole security_pole_spruce = new BlockWoodenSecurityPole("spruce");
	public static final BlockWoodenSecurityWire security_wire_spruce = new BlockWoodenSecurityWire("spruce");
	public static final BlockWoodenSecurityPole security_pole_birch = new BlockWoodenSecurityPole("birch");
	public static final BlockWoodenSecurityWire security_wire_birch = new BlockWoodenSecurityWire("birch");
	public static final BlockWoodenSecurityPole security_pole_jungle = new BlockWoodenSecurityPole("jungle");
	public static final BlockWoodenSecurityWire security_wire_jungle = new BlockWoodenSecurityWire("jungle");
	public static final BlockWoodenSecurityPole security_pole_dark_oak = new BlockWoodenSecurityPole("dark_oak");
	public static final BlockWoodenSecurityWire security_wire_dark_oak = new BlockWoodenSecurityWire("dark_oak");
	public static final BlockWoodenSecurityPole security_pole_acacia = new BlockWoodenSecurityPole("acacia");
	public static final BlockWoodenSecurityWire security_wire_acacia = new BlockWoodenSecurityWire("acacia");

	public static final BlockDeExtinctedAquaticPlant chara = new BlockDeExtinctedAquaticPlant(Chara.NAME, (byte) 0, 0.5F, 1.0F);
	public static final BlockDeExtinctedBush banksia_yellow = new BlockDeExtinctedBush(Banksia.NAME, (byte) 0, 0.5F, 0.4F);
	public static final BlockDeExtinctedBush banksia_orange = new BlockDeExtinctedBush(Banksia.NAME, (byte) 1, 0.5F, 0.4F);
	public static final BlockDeExtinctedBush banksia_red = new BlockDeExtinctedBush(Banksia.NAME, (byte) 2, 0.5F, 0.4F);

	public static final BlockDeExtinctedSapling sequoiadendron_gray_sapling = new BlockDeExtinctedSapling(Sequoiadendron.NAME, (byte) 0);
	public static final BlockDeExtinctedLeaves sequoiadendron_leaves = new BlockDeExtinctedLeaves(Sequoiadendron.NAME, (byte) 0);
	public static final BlockDeExtinctedLogs sequoiadendron_gray_log = new BlockDeExtinctedLogs(Sequoiadendron.NAME, (byte) 0);
	public static final BlockDeExtinctedPlanks sequoiadendron_gray_planks = new BlockDeExtinctedPlanks(Sequoiadendron.NAME, (byte) 0);
	public static final BlockDeExtinctedSapling sequoiadendron_orange_sapling = new BlockDeExtinctedSapling(Sequoiadendron.NAME, (byte) 1);
	public static final BlockDeExtinctedLogs sequoiadendron_orange_log = new BlockDeExtinctedLogs(Sequoiadendron.NAME, (byte) 1);
	public static final BlockDeExtinctedPlanks sequoiadendron_orange_planks = new BlockDeExtinctedPlanks(Sequoiadendron.NAME, (byte) 1);
	public static final BlockDeExtinctedSapling sequoiadendron_gray_sapling_huge = new BlockDeExtinctedSapling(Sequoiadendron.NAME, (byte) 2);
	public static final BlockDeExtinctedSapling sequoiadendron_orange_sapling_huge = new BlockDeExtinctedSapling(Sequoiadendron.NAME, (byte) 3);

	public static void preInitCommon()
	{
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.genetic_research_station, TileGeneticResearchStation.class);
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.botanical_research_station, TileBotanicalResearchStation.class);
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.cleaning_table, TileCleaningTable.class);
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.microscope, TileMicroscope.class);
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.plaster_jacket, TilePlasterJacket.class);
		DEBlockRegistry.registerBlockAndTileEntity(DEBlockRegistry.water_filter, TileWaterFilter.class);

		DEBlockRegistry.registerBlock(DEBlockRegistry.creature_fossil_stone_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.creature_fossil_granite_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.creature_fossil_diorite_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.creature_fossil_andesite_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.creature_fossil_sandstone_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.foliage_fossil_stone_block);

		DEBlockRegistry.registerBlock(DEBlockRegistry.gypsum_block);
		DEBlockRegistry.registerBlock(DEBlockRegistry.gypsum_brick);
		DEBlockRegistry.registerBlock(DEBlockRegistry.gypsum_stairs);

		DEBlockRegistry.registerBlock(DEBlockRegistry.clear_glass);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_low);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_low);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_oak);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_oak);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_spruce);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_spruce);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_birch);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_birch);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_jungle);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_jungle);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_dark_oak);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_dark_oak);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_pole_acacia);
		DEBlockRegistry.registerBlock(DEBlockRegistry.security_wire_acacia);

		DEBlockRegistry.registerBlock(DEBlockRegistry.chara);
		DEBlockRegistry.registerBlock(DEBlockRegistry.banksia_yellow);
		DEBlockRegistry.registerBlock(DEBlockRegistry.banksia_orange);
		DEBlockRegistry.registerBlock(DEBlockRegistry.banksia_red);

		DEBlockRegistry.sequoiadendron_leaves.setTree(Sequoiadendron.NAME);
		DEBlockRegistry.sequoiadendron_gray_sapling.setTree(Sequoiadendron.NAME);
		DEBlockRegistry.sequoiadendron_orange_sapling.setTree(Sequoiadendron.NAME);
		DEBlockRegistry.sequoiadendron_gray_sapling_huge.setTree(Sequoiadendron.NAME);
		DEBlockRegistry.sequoiadendron_orange_sapling_huge.setTree(Sequoiadendron.NAME);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_gray_sapling);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_leaves);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_gray_log);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_gray_planks);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_orange_sapling);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_gray_sapling_huge);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_orange_sapling_huge);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_orange_log);
		DEBlockRegistry.registerBlock(DEBlockRegistry.sequoiadendron_orange_planks);
	}

	public static void initCommon()
	{

	}

	public static void postInitCommon()
	{

	}

	public static void preInitClientOnly()
	{
		DEBlockRegistry.ignoreBlockStates(DEBlockRegistry.chara, BlockLiquid.LEVEL);
		DEBlockRegistry.ignoreBlockStates(DEBlockRegistry.sequoiadendron_leaves, BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY);
	}

	public static void initClientOnly()
	{
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.genetic_research_station);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.botanical_research_station);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.cleaning_table);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.microscope);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.plaster_jacket);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.water_filter);

		DEBlockRegistry.registerBlockRender(DEBlockRegistry.creature_fossil_stone_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.creature_fossil_granite_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.creature_fossil_diorite_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.creature_fossil_andesite_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.creature_fossil_sandstone_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.foliage_fossil_stone_block);

		DEBlockRegistry.registerBlockRender(DEBlockRegistry.gypsum_block);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.gypsum_brick);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.gypsum_stairs);

		DEBlockRegistry.registerBlockRender(DEBlockRegistry.clear_glass);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_low);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_low);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_oak);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_oak);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_spruce);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_spruce);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_birch);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_birch);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_jungle);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_jungle);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_dark_oak);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_dark_oak);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_pole_acacia);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.security_wire_acacia);

		DEBlockRegistry.registerBlockRender(DEBlockRegistry.chara);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.banksia_yellow);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.banksia_orange);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.banksia_red);

		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_gray_sapling);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_leaves);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_gray_log);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_gray_planks);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_orange_sapling);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_orange_log);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_orange_planks);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_gray_sapling_huge);
		DEBlockRegistry.registerBlockRender(DEBlockRegistry.sequoiadendron_orange_sapling_huge);
	}

	public static void postInitClientOnly()
	{

	}

	/**
	 * Registers a simple block.
	 */
	private static void registerBlock(Block block)
	{
		String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
		GameRegistry.registerBlock(block, blockItemUnlocalizedName);
	}

	private static void registerTree(String treeName, BlockDeExtinctedSapling sapling, BlockDeExtinctedLeaves leaves, Block log, Block planks)
	{
		sapling.setTree(treeName);
		leaves.setTree(treeName);
		DEBlockRegistry.registerBlock(sapling);
		DEBlockRegistry.registerBlock(leaves);
		DEBlockRegistry.registerBlock(log);
		DEBlockRegistry.registerBlock(planks);
	}

	/**
	 * Registers a simple block and tile entity.
	 */
	private static void registerBlockAndTileEntity(Block block, Class clazz)
	{
		String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
		GameRegistry.registerBlock(block, blockItemUnlocalizedName);
		GameRegistry.registerTileEntity(clazz, blockItemUnlocalizedName);
	}

	/**
	 * Registers a simple block render.
	 */
	private static void registerBlockRender(Block block)
	{
		String blockItemUnlocalizedName = block.getUnlocalizedName().substring(5);
		Item blockItem = GameRegistry.findItem(DeExtinction.MODID, blockItemUnlocalizedName);
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(DeExtinction.prependModID(blockItemUnlocalizedName), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(blockItem, 0, itemModelResourceLocation);
	}

	/**
	 * Sets a block render to ignore certain states.
	 */
	private static void ignoreBlockStates(Block block, IProperty... ignoreProperties)
	{
		ModelLoader.setCustomStateMapper(block, new CustomStateMap(ignoreProperties));
	}
}
