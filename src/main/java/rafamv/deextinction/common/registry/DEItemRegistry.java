package rafamv.deextinction.common.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.creatures.Aepyornis;
import rafamv.deextinction.common.database.creatures.Archaeorhynchus;
import rafamv.deextinction.common.database.creatures.Citipati;
import rafamv.deextinction.common.database.creatures.Deinonychus;
import rafamv.deextinction.common.database.creatures.Dilong;
import rafamv.deextinction.common.database.creatures.Dinornis;
import rafamv.deextinction.common.database.creatures.Dromaeosaurus;
import rafamv.deextinction.common.database.creatures.Guanlong;
import rafamv.deextinction.common.database.creatures.Horse;
import rafamv.deextinction.common.database.creatures.Kelenken;
import rafamv.deextinction.common.database.creatures.Quagga;
import rafamv.deextinction.common.database.creatures.Raphus;
import rafamv.deextinction.common.database.creatures.Sinovenator;
import rafamv.deextinction.common.database.creatures.Troodon;
import rafamv.deextinction.common.database.creatures.Utahraptor;
import rafamv.deextinction.common.database.creatures.Velociraptor;
import rafamv.deextinction.common.database.creatures.Zhenyuanlong;
import rafamv.deextinction.common.database.foliage.Banksia;
import rafamv.deextinction.common.database.foliage.Chara;
import rafamv.deextinction.common.database.foliage.Sequoiadendron;
import rafamv.deextinction.common.item.ItemBloodFilledSyringe;
import rafamv.deextinction.common.item.ItemCultivatedChickenEgg;
import rafamv.deextinction.common.item.ItemEggShell;
import rafamv.deextinction.common.item.ItemEmptySyringe;
import rafamv.deextinction.common.item.ItemGypsumPowder;
import rafamv.deextinction.common.item.ItemPeriodFinder;
import rafamv.deextinction.common.item.ItemPlasterBurlapStrips;
import rafamv.deextinction.common.item.ItemPlasterPowder;
import rafamv.deextinction.common.item.ItemRockPick;
import rafamv.deextinction.common.item.ItemUnknown;
import rafamv.deextinction.common.item.ItemWaterThermometer;
import rafamv.deextinction.common.item.eggnests.ItemEggNestLarge;
import rafamv.deextinction.common.item.eggnests.ItemEggNestMedium;
import rafamv.deextinction.common.item.eggnests.ItemEggNestSmall;
import rafamv.deextinction.common.item.eggnests.ItemEggNestTiny;
import rafamv.deextinction.common.item.eggs.ItemCreatureEggLarge;
import rafamv.deextinction.common.item.eggs.ItemCreatureEggMedium;
import rafamv.deextinction.common.item.eggs.ItemCreatureEggSmall;
import rafamv.deextinction.common.item.eggs.ItemCreatureEggTiny;
import rafamv.deextinction.common.item.speciesitems.ItemCreatureItem;
import rafamv.deextinction.common.item.speciesitems.ItemCreatureSyringe;
import rafamv.deextinction.common.item.speciesitems.ItemDEDrop;
import rafamv.deextinction.common.item.speciesitems.ItemDEFood;
import rafamv.deextinction.common.item.speciesitems.ItemFoliageItem;

public class DEItemRegistry
{
	public static final ItemEggShell eggshell = new ItemEggShell();
	public static final ItemEmptySyringe empty_syringe = new ItemEmptySyringe();
	public static final ItemBloodFilledSyringe blood_filled_syringe = new ItemBloodFilledSyringe();
	public static final ItemWaterThermometer water_thermometer = new ItemWaterThermometer();
	public static final ItemRockPick rock_pick = new ItemRockPick();
	public static final ItemGypsumPowder gypsum_powder = new ItemGypsumPowder();
	public static final ItemPlasterPowder plaster_powder = new ItemPlasterPowder();
	public static final ItemPlasterBurlapStrips plaster_burlap_strips = new ItemPlasterBurlapStrips();
	public static final ItemPeriodFinder period_machine = new ItemPeriodFinder();
	public static final ItemUnknown unknown_image = new ItemUnknown();

	public static final ItemEggNestTiny egg_nest_tiny = new ItemEggNestTiny();
	public static final ItemEggNestSmall egg_nest_small = new ItemEggNestSmall();
	public static final ItemEggNestMedium egg_nest_medium = new ItemEggNestMedium();
	public static final ItemEggNestLarge egg_nest_large = new ItemEggNestLarge();

	public static final ItemCultivatedChickenEgg cultivated_chicken_egg = new ItemCultivatedChickenEgg();
	public static final ItemCreatureEggSmall raphus_egg = new ItemCreatureEggSmall(Raphus.NAME);
	public static final ItemCreatureEggMedium kelenken_egg = new ItemCreatureEggMedium(Kelenken.NAME);
	public static final ItemCreatureEggLarge dinornis_egg = new ItemCreatureEggLarge(Dinornis.NAME);
	public static final ItemCreatureEggLarge aepyornis_egg = new ItemCreatureEggLarge(Aepyornis.NAME);
	public static final ItemCreatureEggTiny archaeorhynchus_egg = new ItemCreatureEggTiny(Archaeorhynchus.NAME);
	public static final ItemCreatureEggTiny sinovenator_egg = new ItemCreatureEggTiny(Sinovenator.NAME);
	public static final ItemCreatureEggMedium deinonychus_egg = new ItemCreatureEggMedium(Deinonychus.NAME);
	public static final ItemCreatureEggMedium utahraptor_egg = new ItemCreatureEggMedium(Utahraptor.NAME);
	public static final ItemCreatureEggSmall citipati_egg = new ItemCreatureEggSmall(Citipati.NAME);
	public static final ItemCreatureEggSmall zhenyuanlong_egg = new ItemCreatureEggSmall(Zhenyuanlong.NAME);
	public static final ItemCreatureEggSmall dromaeosaurus_egg = new ItemCreatureEggSmall(Dromaeosaurus.NAME);
	public static final ItemCreatureEggSmall velociraptor_egg = new ItemCreatureEggSmall(Velociraptor.NAME);
	public static final ItemCreatureEggSmall troodon_egg = new ItemCreatureEggSmall(Troodon.NAME);
	public static final ItemCreatureEggMedium dilong_egg = new ItemCreatureEggMedium(Dilong.NAME);
	public static final ItemCreatureEggMedium guanlong_egg = new ItemCreatureEggMedium(Guanlong.NAME);
	public static final ItemCreatureSyringe horse_syringe = new ItemCreatureSyringe(Horse.NAME);
	public static final ItemCreatureSyringe quagga_syringe = new ItemCreatureSyringe(Quagga.NAME);

	public static final ItemCreatureItem raphus_fossilized_feather = new ItemCreatureItem(Raphus.NAME, 0);
	public static final ItemCreatureItem kelenken_fossilized_feather = new ItemCreatureItem(Kelenken.NAME, 0);
	public static final ItemCreatureItem dinornis_fossilized_feather = new ItemCreatureItem(Dinornis.NAME, 0);
	public static final ItemCreatureItem aepyornis_fossilized_feather = new ItemCreatureItem(Aepyornis.NAME, 0);
	public static final ItemCreatureItem archaeorhynchus_fossilized_feather = new ItemCreatureItem(Archaeorhynchus.NAME, 0);
	public static final ItemCreatureItem sinovenator_fossilized_feather = new ItemCreatureItem(Sinovenator.NAME, 0);
	public static final ItemCreatureItem deinonychus_fossilized_claw = new ItemCreatureItem(Deinonychus.NAME, 0);
	public static final ItemCreatureItem utahraptor_fossilized_claw = new ItemCreatureItem(Utahraptor.NAME, 0);
	public static final ItemCreatureItem citipati_fossilized = new ItemCreatureItem(Citipati.NAME, 0);
	public static final ItemCreatureItem zhenyuanlong_fossilized = new ItemCreatureItem(Zhenyuanlong.NAME, 0);
	public static final ItemCreatureItem dromaeosaurus_fossilized = new ItemCreatureItem(Dromaeosaurus.NAME, 0);
	public static final ItemCreatureItem velociraptor_fossilized = new ItemCreatureItem(Velociraptor.NAME, 0);
	public static final ItemCreatureItem troodon_fossilized = new ItemCreatureItem(Troodon.NAME, 0);
	public static final ItemCreatureItem dilong_fossilized = new ItemCreatureItem(Dilong.NAME, 0);
	public static final ItemCreatureItem guanlong_fossilized = new ItemCreatureItem(Guanlong.NAME, 0);
	public static final ItemCreatureItem quagga_fossilized_hide = new ItemCreatureItem(Quagga.NAME, 0);

	public static final ItemFoliageItem chara_fossilized = new ItemFoliageItem(Chara.NAME, 0);
	public static final ItemFoliageItem banksia_fossilized = new ItemFoliageItem(Banksia.NAME, 0);
	public static final ItemFoliageItem sequoiadendron_fossilized = new ItemFoliageItem(Sequoiadendron.NAME, 0);

	public static final ItemDEFood raphus_raw = new ItemDEFood(Raphus.NAME, false, 2, 1.8F, true);
	public static final ItemDEFood raphus_cooked = new ItemDEFood(Raphus.NAME, true, 6, 10.3F, true);
	public static final ItemDEFood dinornis_raw = new ItemDEFood(Dinornis.NAME, false, 3, 2.7F, true);
	public static final ItemDEFood dinornis_cooked = new ItemDEFood(Dinornis.NAME, true, 8, 19.2F, true);
	public static final ItemDEFood aepyornis_raw = new ItemDEFood(Aepyornis.NAME, false, 3, 2.7F, true);
	public static final ItemDEFood aepyornis_cooked = new ItemDEFood(Aepyornis.NAME, true, 8, 19.2F, true);
	public static final ItemDEFood kelenken_raw = new ItemDEFood(Kelenken.NAME, false, 3, 2.7F, true);
	public static final ItemDEFood kelenken_cooked = new ItemDEFood(Kelenken.NAME, true, 8, 19.2F, true);
	public static final ItemDEFood archaeorhynchus_raw = new ItemDEFood(Archaeorhynchus.NAME, false, 2, 1.8F, true);
	public static final ItemDEFood archaeorhynchus_cooked = new ItemDEFood(Archaeorhynchus.NAME, true, 6, 10.3F, true);
	public static final ItemDEFood sinovenator_raw = new ItemDEFood(Sinovenator.NAME, false, 2, 1.8F, true);
	public static final ItemDEFood sinovenator_cooked = new ItemDEFood(Sinovenator.NAME, true, 6, 10.3F, true);
	public static final ItemDEFood deinonychus_raw = new ItemDEFood(Deinonychus.NAME, false, 2, 2.3F, true);
	public static final ItemDEFood deinonychus_cooked = new ItemDEFood(Deinonychus.NAME, true, 7, 15.2F, true);
	public static final ItemDEFood utahraptor_raw = new ItemDEFood(Utahraptor.NAME, false, 3, 2.7F, true);
	public static final ItemDEFood utahraptor_cooked = new ItemDEFood(Utahraptor.NAME, true, 8, 19.2F, true);

	public static final ItemDEDrop raphus_feather = new ItemDEDrop(Raphus.NAME, "feather");
	public static final ItemDEDrop kelenken_feather = new ItemDEDrop(Kelenken.NAME, "feather");
	public static final ItemDEDrop dinornis_feather = new ItemDEDrop(Dinornis.NAME, "feather");
	public static final ItemDEDrop aepyornis_feather = new ItemDEDrop(Aepyornis.NAME, "feather");
	public static final ItemDEDrop archaeorhynchus_feather = new ItemDEDrop(Archaeorhynchus.NAME, "feather");
	public static final ItemDEDrop sinovenator_feather = new ItemDEDrop(Sinovenator.NAME, "feather");
	public static final ItemDEDrop deinonychus_claw = new ItemDEDrop(Deinonychus.NAME, "claw");
	public static final ItemDEDrop utahraptor_claw = new ItemDEDrop(Utahraptor.NAME, "claw");
	public static final ItemDEDrop citipati_feather = new ItemDEDrop(Citipati.NAME, "feather");
	public static final ItemDEDrop zhenyuanlong_feather = new ItemDEDrop(Zhenyuanlong.NAME, "feather");
	public static final ItemDEDrop dromaeosaurus_claw = new ItemDEDrop(Dromaeosaurus.NAME, "feather");
	public static final ItemDEDrop velociraptor_claw = new ItemDEDrop(Velociraptor.NAME, "claw");
	public static final ItemDEDrop troodon_feather = new ItemDEDrop(Troodon.NAME, "feather");
	public static final ItemDEDrop dilong_feather = new ItemDEDrop(Dilong.NAME, "feather");
	public static final ItemDEDrop guanlong_feather = new ItemDEDrop(Guanlong.NAME, "feather");
	public static final ItemDEDrop quagga_hide = new ItemDEDrop(Quagga.NAME, "hide");

	public static void preInitCommon()
	{
		DEItemRegistry.registerItem(DEItemRegistry.eggshell);
		DEItemRegistry.registerItem(DEItemRegistry.empty_syringe);
		DEItemRegistry.registerItem(DEItemRegistry.blood_filled_syringe);
		DEItemRegistry.registerItem(DEItemRegistry.water_thermometer);
		DEItemRegistry.registerItem(DEItemRegistry.rock_pick);
		DEItemRegistry.registerItem(DEItemRegistry.gypsum_powder);
		DEItemRegistry.registerItem(DEItemRegistry.plaster_powder);
		DEItemRegistry.registerItem(DEItemRegistry.plaster_burlap_strips);
		DEItemRegistry.registerItem(DEItemRegistry.period_machine);
		DEItemRegistry.registerItem(DEItemRegistry.unknown_image);

		DEItemRegistry.registerItem(DEItemRegistry.egg_nest_tiny);
		DEItemRegistry.registerItem(DEItemRegistry.egg_nest_small);
		DEItemRegistry.registerItem(DEItemRegistry.egg_nest_medium);
		DEItemRegistry.registerItem(DEItemRegistry.egg_nest_large);

		DEItemRegistry.registerItem(DEItemRegistry.cultivated_chicken_egg);
		DEItemRegistry.registerItem(DEItemRegistry.raphus_egg);
		DEItemRegistry.registerItem(DEItemRegistry.kelenken_egg);
		DEItemRegistry.registerItem(DEItemRegistry.dinornis_egg);
		DEItemRegistry.registerItem(DEItemRegistry.aepyornis_egg);
		DEItemRegistry.registerItem(DEItemRegistry.archaeorhynchus_egg);
		DEItemRegistry.registerItem(DEItemRegistry.sinovenator_egg);
		DEItemRegistry.registerItem(DEItemRegistry.deinonychus_egg);
		DEItemRegistry.registerItem(DEItemRegistry.utahraptor_egg);
		DEItemRegistry.registerItem(DEItemRegistry.citipati_egg);
		DEItemRegistry.registerItem(DEItemRegistry.zhenyuanlong_egg);
		DEItemRegistry.registerItem(DEItemRegistry.dromaeosaurus_egg);
		DEItemRegistry.registerItem(DEItemRegistry.velociraptor_egg);
		DEItemRegistry.registerItem(DEItemRegistry.troodon_egg);
		DEItemRegistry.registerItem(DEItemRegistry.dilong_egg);
		DEItemRegistry.registerItem(DEItemRegistry.guanlong_egg);
		DEItemRegistry.registerItem(DEItemRegistry.horse_syringe);
		DEItemRegistry.registerItem(DEItemRegistry.quagga_syringe);

		DEItemRegistry.registerItem(DEItemRegistry.raphus_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.kelenken_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.dinornis_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.aepyornis_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.archaeorhynchus_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.sinovenator_fossilized_feather);
		DEItemRegistry.registerItem(DEItemRegistry.deinonychus_fossilized_claw);
		DEItemRegistry.registerItem(DEItemRegistry.utahraptor_fossilized_claw);
		DEItemRegistry.registerItem(DEItemRegistry.citipati_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.zhenyuanlong_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.dromaeosaurus_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.velociraptor_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.troodon_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.dilong_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.guanlong_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.quagga_fossilized_hide);

		DEItemRegistry.registerItem(DEItemRegistry.chara_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.banksia_fossilized);
		DEItemRegistry.registerItem(DEItemRegistry.sequoiadendron_fossilized);

		DEItemRegistry.registerItem(DEItemRegistry.raphus_raw);
		DEItemRegistry.registerItem(DEItemRegistry.raphus_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.dinornis_raw);
		DEItemRegistry.registerItem(DEItemRegistry.dinornis_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.aepyornis_raw);
		DEItemRegistry.registerItem(DEItemRegistry.aepyornis_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.kelenken_raw);
		DEItemRegistry.registerItem(DEItemRegistry.kelenken_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.archaeorhynchus_raw);
		DEItemRegistry.registerItem(DEItemRegistry.archaeorhynchus_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.sinovenator_raw);
		DEItemRegistry.registerItem(DEItemRegistry.sinovenator_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.deinonychus_raw);
		DEItemRegistry.registerItem(DEItemRegistry.deinonychus_cooked);
		DEItemRegistry.registerItem(DEItemRegistry.utahraptor_raw);
		DEItemRegistry.registerItem(DEItemRegistry.utahraptor_cooked);

		DEItemRegistry.registerItem(DEItemRegistry.raphus_feather);
		DEItemRegistry.registerItem(DEItemRegistry.kelenken_feather);
		DEItemRegistry.registerItem(DEItemRegistry.dinornis_feather);
		DEItemRegistry.registerItem(DEItemRegistry.aepyornis_feather);
		DEItemRegistry.registerItem(DEItemRegistry.archaeorhynchus_feather);
		DEItemRegistry.registerItem(DEItemRegistry.sinovenator_feather);
		DEItemRegistry.registerItem(DEItemRegistry.deinonychus_claw);
		DEItemRegistry.registerItem(DEItemRegistry.utahraptor_claw);
		DEItemRegistry.registerItem(DEItemRegistry.citipati_feather);
		DEItemRegistry.registerItem(DEItemRegistry.zhenyuanlong_feather);
		DEItemRegistry.registerItem(DEItemRegistry.dromaeosaurus_claw);
		DEItemRegistry.registerItem(DEItemRegistry.velociraptor_claw);
		DEItemRegistry.registerItem(DEItemRegistry.troodon_feather);
		DEItemRegistry.registerItem(DEItemRegistry.dilong_feather);
		DEItemRegistry.registerItem(DEItemRegistry.guanlong_feather);
		DEItemRegistry.registerItem(DEItemRegistry.quagga_hide);
	}

	public static void initCommon()
	{

	}

	public static void postInitCommon()
	{

	}

	public static void preInitClientOnly()
	{

	}

	public static void initClientOnly()
	{
		DEItemRegistry.registerItemRender(DEItemRegistry.eggshell);
		DEItemRegistry.registerItemRender(DEItemRegistry.empty_syringe);
		DEItemRegistry.registerItemRender(DEItemRegistry.blood_filled_syringe);
		DEItemRegistry.registerItemRender(DEItemRegistry.water_thermometer);
		DEItemRegistry.registerItemRender(DEItemRegistry.rock_pick);
		DEItemRegistry.registerItemRender(DEItemRegistry.gypsum_powder);
		DEItemRegistry.registerItemRender(DEItemRegistry.plaster_powder);
		DEItemRegistry.registerItemRender(DEItemRegistry.plaster_burlap_strips);
		DEItemRegistry.registerItemRender(DEItemRegistry.period_machine);
		DEItemRegistry.registerItemRender(DEItemRegistry.unknown_image);

		DEItemRegistry.registerItemRender(DEItemRegistry.egg_nest_tiny);
		DEItemRegistry.registerItemRender(DEItemRegistry.egg_nest_small);
		DEItemRegistry.registerItemRender(DEItemRegistry.egg_nest_medium);
		DEItemRegistry.registerItemRender(DEItemRegistry.egg_nest_large);

		DEItemRegistry.registerItemRender(DEItemRegistry.cultivated_chicken_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.raphus_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.kelenken_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.dinornis_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.aepyornis_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.archaeorhynchus_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.sinovenator_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.deinonychus_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.utahraptor_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.citipati_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.zhenyuanlong_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.dromaeosaurus_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.velociraptor_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.troodon_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.dilong_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.guanlong_egg);
		DEItemRegistry.registerItemRender(DEItemRegistry.horse_syringe);
		DEItemRegistry.registerItemRender(DEItemRegistry.quagga_syringe);

		DEItemRegistry.registerItemRender(DEItemRegistry.raphus_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.kelenken_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.dinornis_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.aepyornis_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.archaeorhynchus_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.sinovenator_fossilized_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.deinonychus_fossilized_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.utahraptor_fossilized_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.citipati_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.zhenyuanlong_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.dromaeosaurus_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.velociraptor_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.troodon_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.dilong_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.guanlong_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.quagga_fossilized_hide);

		DEItemRegistry.registerItemRender(DEItemRegistry.chara_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.banksia_fossilized);
		DEItemRegistry.registerItemRender(DEItemRegistry.sequoiadendron_fossilized);

		DEItemRegistry.registerItemRender(DEItemRegistry.raphus_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.raphus_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.dinornis_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.dinornis_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.aepyornis_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.aepyornis_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.kelenken_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.kelenken_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.archaeorhynchus_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.archaeorhynchus_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.sinovenator_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.sinovenator_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.deinonychus_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.deinonychus_cooked);
		DEItemRegistry.registerItemRender(DEItemRegistry.utahraptor_raw);
		DEItemRegistry.registerItemRender(DEItemRegistry.utahraptor_cooked);

		DEItemRegistry.registerItemRender(DEItemRegistry.raphus_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.kelenken_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.dinornis_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.aepyornis_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.archaeorhynchus_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.sinovenator_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.deinonychus_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.utahraptor_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.citipati_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.zhenyuanlong_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.dromaeosaurus_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.velociraptor_claw);
		DEItemRegistry.registerItemRender(DEItemRegistry.troodon_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.dilong_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.guanlong_feather);
		DEItemRegistry.registerItemRender(DEItemRegistry.quagga_hide);
	}

	public static void postInitClientOnly()
	{

	}

	/**
	 * Registers a simple item.
	 */
	private static void registerItem(Item item)
	{
		String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
		GameRegistry.registerItem(item, itemUnlocalizedName);
	}

	/**
	 * Registers a simple item render.
	 */
	private static void registerItemRender(Item item)
	{
		String itemUnlocalizedName = item.getUnlocalizedName().substring(5);
		ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(DeExtinction.prependModID(itemUnlocalizedName), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, itemModelResourceLocation);
	}
}
