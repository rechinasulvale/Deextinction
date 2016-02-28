package rafamv.deextinction.common.registry;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class DERecipeRegistry
{

	public static void preInitCommon()
	{

	}

	public static void initCommon()
	{
		GameRegistry.addSmelting(DEItemRegistry.gypsum_powder, new ItemStack(DEItemRegistry.plaster_powder, 1), 5);
		GameRegistry.addSmelting(DEItemRegistry.raphus_raw, new ItemStack(DEItemRegistry.raphus_cooked, 1), 0);
		GameRegistry.addSmelting(DEItemRegistry.dinornis_raw, new ItemStack(DEItemRegistry.dinornis_cooked, 1), 0);
		GameRegistry.addSmelting(DEItemRegistry.kelenken_raw, new ItemStack(DEItemRegistry.kelenken_cooked, 1), 0);
		GameRegistry.addSmelting(DEItemRegistry.archaeorhynchus_raw, new ItemStack(DEItemRegistry.archaeorhynchus_cooked, 1), 0);
		GameRegistry.addSmelting(DEItemRegistry.sinovenator_raw, new ItemStack(DEItemRegistry.sinovenator_cooked, 1), 0);

		GameRegistry.addShapelessRecipe(new ItemStack(DEItemRegistry.eggshell), new Object[] { new ItemStack(Items.egg) });
		GameRegistry.addShapelessRecipe(new ItemStack(DEItemRegistry.water_thermometer), new Object[] { new ItemStack(Items.potionitem, 1, 0), Items.gold_nugget });

		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_low, 1), new Object[] { "BIB", 'B', Blocks.iron_bars, 'I', Blocks.iron_block });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_low, 3), new Object[] { "BBB", 'B', Blocks.iron_bars });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_oak, 1), new Object[] { "FWF", 'F', Blocks.oak_fence, 'W', new ItemStack(Blocks.log, 1, 0) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_oak, 3), new Object[] { "FFF", 'F', Blocks.oak_fence });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_spruce, 1), new Object[] { "FWF", 'F', Blocks.spruce_fence, 'W', new ItemStack(Blocks.log, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_spruce, 3), new Object[] { "FFF", 'F', Blocks.spruce_fence });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_birch, 1), new Object[] { "FWF", 'F', Blocks.birch_fence, 'W', new ItemStack(Blocks.log, 1, 2) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_birch, 3), new Object[] { "FFF", 'F', Blocks.birch_fence });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_jungle, 1), new Object[] { "FWF", 'F', Blocks.jungle_fence, 'W', new ItemStack(Blocks.log, 1, 3) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_jungle, 3), new Object[] { "FFF", 'F', Blocks.jungle_fence });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_dark_oak, 1), new Object[] { "FWF", 'F', Blocks.dark_oak_fence, 'W', new ItemStack(Blocks.log2, 1, 4) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_dark_oak, 3), new Object[] { "FFF", 'F', Blocks.dark_oak_fence });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_pole_acacia, 1), new Object[] { "FWF", 'F', Blocks.acacia_fence, 'W', new ItemStack(Blocks.log2, 1, 5) });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.security_wire_acacia, 3), new Object[] { "FFF", 'F', Blocks.acacia_fence });

		GameRegistry.addShapedRecipe(new ItemStack(DEItemRegistry.egg_nest_tiny), new Object[] { "S.S", "SSS", 'S', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(DEItemRegistry.egg_nest_small), new Object[] { "SNS", "SSS", 'S', Items.stick, 'N', DEItemRegistry.egg_nest_tiny });
		GameRegistry.addShapedRecipe(new ItemStack(DEItemRegistry.egg_nest_medium), new Object[] { "SNS", "SSS", 'S', Items.stick, 'N', DEItemRegistry.egg_nest_small });
		GameRegistry.addShapedRecipe(new ItemStack(DEItemRegistry.egg_nest_large), new Object[] { "SNS", "SSS", 'S', Items.stick, 'N', DEItemRegistry.egg_nest_medium });

		GameRegistry.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] { "F", "S", "I", 'F', Items.flint, 'S', Items.stick, 'I', DEItemRegistry.dinornis_feather });
		GameRegistry.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] { "F", "S", "I", 'F', Items.flint, 'S', Items.stick, 'I', DEItemRegistry.raphus_feather });
		GameRegistry.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] { "F", "S", "I", 'F', Items.flint, 'S', Items.stick, 'I', DEItemRegistry.kelenken_feather });
		GameRegistry.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] { "F", "S", "I", 'F', Items.flint, 'S', Items.stick, 'I', DEItemRegistry.archaeorhynchus_feather });
		GameRegistry.addShapedRecipe(new ItemStack(Items.arrow, 4), new Object[] { "F", "S", "I", 'F', Items.flint, 'S', Items.stick, 'I', DEItemRegistry.sinovenator_feather });

		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.gypsum_brick, 4), new Object[] { "GG", "GG", 'G', DEBlockRegistry.gypsum_block });
		GameRegistry.addShapedRecipe(new ItemStack(DEBlockRegistry.gypsum_stairs, 4), new Object[] { "G  ", "GG ", "GGG", 'G', DEBlockRegistry.gypsum_brick });

		IRecipe genetic_research_station = new ShapedOreRecipe(new ItemStack(DEBlockRegistry.genetic_research_station), new Object[] { false, " R ", "IBI", "ICI", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'C', Blocks.crafting_table, 'R', Items.bone });
		GameRegistry.addRecipe(genetic_research_station);

		IRecipe botanical_research_station = new ShapedOreRecipe(new ItemStack(DEBlockRegistry.genetic_research_station), new Object[] { false, " R ", "IBI", "ICI", 'I', Items.iron_ingot, 'B', Blocks.iron_block, 'C', Blocks.crafting_table, 'R', Blocks.tallgrass });
		GameRegistry.addRecipe(botanical_research_station);

		IRecipe cleaning_table = new ShapedOreRecipe(new ItemStack(DEBlockRegistry.cleaning_table), new Object[] { false, "SSS", "PCP", 'S', Blocks.wooden_slab, 'P', Blocks.planks, 'C', Blocks.crafting_table });
		GameRegistry.addRecipe(cleaning_table);

		IRecipe water_filter = new ShapedOreRecipe(new ItemStack(DEBlockRegistry.water_filter), new Object[] { false, "III", "BIB", "III", 'B', Items.bucket, 'I', Items.iron_ingot });
		GameRegistry.addRecipe(water_filter);

		IRecipe plaster_burlap_strips = new ShapedOreRecipe(new ItemStack(DEItemRegistry.plaster_burlap_strips, 4), new Object[] { false, "PGP", "GWG", "PGP", 'P', Items.paper, 'G', DEItemRegistry.plaster_powder, 'W', new ItemStack(Items.water_bucket.setContainerItem(Items.bucket)) });
		GameRegistry.addRecipe(plaster_burlap_strips);

		IRecipe plaster_burlap_strips_inverted = new ShapedOreRecipe(new ItemStack(DEItemRegistry.plaster_burlap_strips, 4), new Object[] { false, "GPG", "PWP", "GPG", 'P', Items.paper, 'G', DEItemRegistry.plaster_powder, 'W', new ItemStack(Items.water_bucket.setContainerItem(Items.bucket)) });
		GameRegistry.addRecipe(plaster_burlap_strips_inverted);

		IRecipe rock_pick_1 = new ShapedOreRecipe(new ItemStack(DEItemRegistry.rock_pick), new Object[] { true, "..I", ".SI", "S..", 'I', Items.iron_ingot, 'S', Items.stick });
		GameRegistry.addRecipe(rock_pick_1);
	}

	public static void postInitCommon()
	{

	}

	public static void preInitClientOnly()
	{

	}

	public static void initClientOnly()
	{

	}

	public static void postInitClientOnly()
	{

	}
}
