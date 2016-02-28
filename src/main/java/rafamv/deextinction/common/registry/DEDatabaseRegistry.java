package rafamv.deextinction.common.registry;

import java.util.HashMap;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Period;
import rafamv.deextinction.common.database.creatures.Aepyornis;
import rafamv.deextinction.common.database.creatures.Archaeorhynchus;
import rafamv.deextinction.common.database.creatures.Chicken;
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
import rafamv.deextinction.common.database.foliage.Fern;
import rafamv.deextinction.common.database.foliage.Sequoiadendron;
import rafamv.deextinction.common.database.foliage.Spruce;
import rafamv.deextinction.common.database.periods.Cambrian;
import rafamv.deextinction.common.database.periods.Carboniferous;
import rafamv.deextinction.common.database.periods.Cretaceous;
import rafamv.deextinction.common.database.periods.Devonian;
import rafamv.deextinction.common.database.periods.Jurassic;
import rafamv.deextinction.common.database.periods.Neogene;
import rafamv.deextinction.common.database.periods.Ordovician;
import rafamv.deextinction.common.database.periods.Paleogene;
import rafamv.deextinction.common.database.periods.Permian;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.database.periods.Silurian;
import rafamv.deextinction.common.database.periods.Triassic;

public class DEDatabaseRegistry
{
	public static final HashMap<String, Period> PERIOD_LIST = new HashMap<String, Period>();

	public static final HashMap<String, Integer> FOOD_LIST = new HashMap<String, Integer>();
	public static final HashMap<String, Integer> CREATURE_NUTRIENTS_LIST = new HashMap<String, Integer>();
	public static final HashMap<String, Integer> FOLIAGE_NUTRIENTS_LIST = new HashMap<String, Integer>();

	public static final HashMap<String, Creature> LIST_VANILLA_CREATURES = new HashMap<String, Creature>();
	public static final HashMap<String, Creature> LIST_DEEXTINCT_CREATURES = new HashMap<String, Creature>();
	public static final HashMap<String, Creature> LIST_ALL_CREATURES = new HashMap<String, Creature>();
	public static final HashMap<String, Creature> LIST_CREATURE_THEROPODS_BRANCH = new HashMap<String, Creature>();
	public static final HashMap<String, Creature> LIST_CREATURE_MAMMALS_BRANCH = new HashMap<String, Creature>();

	public static final HashMap<String, Foliage> LIST_VANILLA_FOLIAGE = new HashMap<String, Foliage>();
	public static final HashMap<String, Foliage> LIST_DEEXTINCT_FOLIAGE = new HashMap<String, Foliage>();
	public static final HashMap<String, Foliage> LIST_ALL_FOLIAGE = new HashMap<String, Foliage>();
	public static final HashMap<String, Foliage> LIST_FOLIAGE_BUSHES_BRANCH = new HashMap<String, Foliage>();
	public static final HashMap<String, Foliage> LIST_FOLIAGE_TREES_BRANCH = new HashMap<String, Foliage>();

	public static final byte TREES_BRANCH = -2;
	public static final byte BUSHES_BRANCH = -1;
	public static final byte THEROPODS_BRANCH = 0;
	public static final byte MAMMALS_BRANCH = 1;

	static
	{
		DEDatabaseRegistry.registerFoodList();

		DEDatabaseRegistry.registerPeriods();

		DEDatabaseRegistry.registerCreatures();
		DEDatabaseRegistry.registerUndoneCreatures();
		DEDatabaseRegistry.registerCreatureTree();

		DEDatabaseRegistry.registerFoliage();
		DEDatabaseRegistry.registerFoliageTree();
	}

	private static void registerCreatures()
	{
		DEDatabaseRegistry.registerVanillaCreature(new Chicken());
		DEDatabaseRegistry.registerDeExtinctCreature(new Raphus());
		DEDatabaseRegistry.registerDeExtinctCreature(new Kelenken());
		DEDatabaseRegistry.registerDeExtinctCreature(new Dinornis());
		DEDatabaseRegistry.registerDeExtinctCreature(new Aepyornis());
		DEDatabaseRegistry.registerDeExtinctCreature(new Archaeorhynchus());
		DEDatabaseRegistry.registerDeExtinctCreature(new Sinovenator());
		DEDatabaseRegistry.registerDeExtinctCreature(new Deinonychus());
		DEDatabaseRegistry.registerDeExtinctCreature(new Utahraptor());
		DEDatabaseRegistry.registerDeExtinctCreature(new Citipati());
		DEDatabaseRegistry.registerDeExtinctCreature(new Troodon());
		DEDatabaseRegistry.registerDeExtinctCreature(new Zhenyuanlong());
		DEDatabaseRegistry.registerDeExtinctCreature(new Dromaeosaurus());
		DEDatabaseRegistry.registerDeExtinctCreature(new Velociraptor());
		DEDatabaseRegistry.registerDeExtinctCreature(new Dilong());
		DEDatabaseRegistry.registerDeExtinctCreature(new Guanlong());

		DEDatabaseRegistry.registerVanillaCreature(new Horse());
		DEDatabaseRegistry.registerDeExtinctCreature(new Quagga());
	}

	private static void registerUndoneCreatures()
	{

		/*
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Nothronychus());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Therizinosaurus());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Shuvuuia());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Struthiomimus());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Pelecanimimus());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Compsognathus());
		 * DEDatabaseRegistry.registerDeExtinctCreature(new Eotyrannus());
		 */
	}

	private static void registerFoliage()
	{
		DEDatabaseRegistry.registerVanillaFoliage(new Fern());
		DEDatabaseRegistry.registerDeExtinctFoliage(new Chara());
		DEDatabaseRegistry.registerDeExtinctFoliage(new Banksia());

		DEDatabaseRegistry.registerVanillaFoliage(new Spruce());
		DEDatabaseRegistry.registerVanillaFoliage(new Sequoiadendron());
	}

	private static void registerFoodList()
	{
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.apple.getUnlocalizedName(), 64);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.baked_potato.getUnlocalizedName(), 122);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.bread.getUnlocalizedName(), 110);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cake.getUnlocalizedName(), 168);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.carrot.getUnlocalizedName(), 78);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_chicken.getUnlocalizedName(), 132);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_fish.getUnlocalizedName(), 110);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_mutton.getUnlocalizedName(), 156);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_porkchop.getUnlocalizedName(), 208);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_beef.getUnlocalizedName(), 208);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cooked_rabbit.getUnlocalizedName(), 110);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.cookie.getUnlocalizedName(), 24);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.melon.getUnlocalizedName(), 6);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.mushroom_stew.getUnlocalizedName(), 132);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.potato.getUnlocalizedName(), 16);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.pumpkin_pie.getUnlocalizedName(), 128);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.rabbit_stew.getUnlocalizedName(), 220);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.beef.getUnlocalizedName(), 48);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.chicken.getUnlocalizedName(), 32);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.fish.getUnlocalizedName(), 24);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.mutton.getUnlocalizedName(), 32);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.porkchop.getUnlocalizedName(), 48);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.rabbit.getUnlocalizedName(), 38);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.rotten_flesh.getUnlocalizedName(), 10);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.bone.getUnlocalizedName(), 20);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.sugar.getUnlocalizedName(), 5);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.wheat_seeds.getUnlocalizedName(), 5);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.wheat.getUnlocalizedName(), 32);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.melon_seeds.getUnlocalizedName(), 10);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.pumpkin_seeds.getUnlocalizedName(), 10);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Items.egg.getUnlocalizedName(), 30);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Item.getItemFromBlock(Blocks.pumpkin).getUnlocalizedName(), 60);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(Item.getItemFromBlock(Blocks.melon_block).getUnlocalizedName(), 80);

		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.raphus_raw.getUnlocalizedName(), 40);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.raphus_cooked.getUnlocalizedName(), 164);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.dinornis_raw.getUnlocalizedName(), 52);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.dinornis_cooked.getUnlocalizedName(), 226);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.aepyornis_raw.getUnlocalizedName(), 52);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.aepyornis_cooked.getUnlocalizedName(), 226);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.archaeorhynchus_raw.getUnlocalizedName(), 40);
		DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.put(DEItemRegistry.archaeorhynchus_cooked.getUnlocalizedName(), 164);

		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.apple.getUnlocalizedName(), 64);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.baked_potato.getUnlocalizedName(), 122);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.bread.getUnlocalizedName(), 110);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.carrot.getUnlocalizedName(), 78);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.melon.getUnlocalizedName(), 6);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.potato.getUnlocalizedName(), 16);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.pumpkin_pie.getUnlocalizedName(), 128);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.bone.getUnlocalizedName(), 20);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.sugar.getUnlocalizedName(), 5);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.wheat_seeds.getUnlocalizedName(), 5);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.wheat.getUnlocalizedName(), 32);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.melon_seeds.getUnlocalizedName(), 10);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Items.pumpkin_seeds.getUnlocalizedName(), 10);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Item.getItemFromBlock(Blocks.pumpkin).getUnlocalizedName(), 60);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Item.getItemFromBlock(Blocks.melon_block).getUnlocalizedName(), 80);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.yellow_flower.getUnlocalizedName(), 25);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.red_flower.getUnlocalizedName(), 25);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.brown_mushroom.getUnlocalizedName(), 20);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.red_mushroom.getUnlocalizedName(), 20);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.sapling.getUnlocalizedName(), 25);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.leaves.getUnlocalizedName(), 5);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.log.getUnlocalizedName(), 40);
		DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.put(Blocks.log2.getUnlocalizedName(), 40);

		if (DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST != null && !DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.isEmpty())
			for (String foodName : DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.keySet())
				if (!DEDatabaseRegistry.FOOD_LIST.containsKey(foodName))
					DEDatabaseRegistry.FOOD_LIST.put(foodName, DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.get(foodName));
				else if (!DEDatabaseRegistry.FOOD_LIST.get(foodName).equals(DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.get(foodName)))
					DeExtinction.logger.error("Same food for creatures and foods from " + foodName + "(" + DEDatabaseRegistry.FOOD_LIST.get(foodName) + "/" + DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.get(foodName) + "), but with different nutrition values. THIS IS A BUG!");

		if (DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST != null && !DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.isEmpty())
			for (String foodName : DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.keySet())
				if (!DEDatabaseRegistry.FOOD_LIST.containsKey(foodName))
					DEDatabaseRegistry.FOOD_LIST.put(foodName, DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.get(foodName));
				else if (!DEDatabaseRegistry.FOOD_LIST.get(foodName).equals(DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.get(foodName)))
					DeExtinction.logger.error("Same food for creatures and foods from " + foodName + "(" + DEDatabaseRegistry.FOOD_LIST.get(foodName) + "/" + DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.get(foodName) + "), but with different nutrition values. THIS IS A BUG!");
	}

	public static void preInitCommon()
	{

	}

	public static void initCommon()
	{
		for (Creature creature : DEDatabaseRegistry.LIST_ALL_CREATURES.values())
			creature.initCreature();

		for (Creature creature : DEDatabaseRegistry.LIST_ALL_CREATURES.values())
			DEDatabaseRegistry.addCreatureInBranch(creature);

		for (Creature creature : DEDatabaseRegistry.LIST_ALL_CREATURES.values())
			DEDatabaseRegistry.addCreatureInPeriods(creature);

		for (Foliage foliage : DEDatabaseRegistry.LIST_ALL_FOLIAGE.values())
			foliage.initFoliage();

		for (Foliage foliage : DEDatabaseRegistry.LIST_ALL_FOLIAGE.values())
			DEDatabaseRegistry.addFoliageInBranch(foliage);

		for (Foliage foliage : DEDatabaseRegistry.LIST_ALL_FOLIAGE.values())
			DEDatabaseRegistry.addFoliageInPeriods(foliage);

		for (Period period : DEDatabaseRegistry.PERIOD_LIST.values())
			period.initPeriod();
	}

	public static void postInitCommon()
	{
		DeExtinction.logger.info("");
		DeExtinction.logger.info("DE-EXTINCTION CHECKING");
		if (!DEDatabaseRegistry.PERIOD_LIST.isEmpty())
		{
			DeExtinction.logger.info("-------------------------------");
			DeExtinction.logger.info("The following periods, creatures, foliage, and foods were registered:");
			for (Period period : DEDatabaseRegistry.PERIOD_LIST.values())
			{
				DeExtinction.logger.info("");
				DeExtinction.logger.info(period.getPeriodName());
				DeExtinction.logger.info(period.getCreatureListInfo());
				DeExtinction.logger.info(period.getFoliageListInfo());
			}

			DeExtinction.logger.info("");
			DeExtinction.logger.info("Food list: " + DEDatabaseRegistry.FOOD_LIST.keySet());
		}
		DeExtinction.logger.info("");
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

	private static void registerPeriod(Period period, boolean shouldRegistry)
	{
		if (shouldRegistry)
			if (period != null)
			{
				if (!DEDatabaseRegistry.PERIOD_LIST.containsValue(period))
					DEDatabaseRegistry.PERIOD_LIST.put(period.getName(), period);
				else
					DeExtinction.logger.error("Period " + period.getName() + " is already registered in the DEDatabaseRegistry.PERIOD_LIST. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Period is null. THIS IS A BUG!");
	}

	private static void registerPeriods()
	{
		DEDatabaseRegistry.registerPeriod(new Cambrian(), false);
		DEDatabaseRegistry.registerPeriod(new Ordovician(), false);
		DEDatabaseRegistry.registerPeriod(new Silurian(), false);
		DEDatabaseRegistry.registerPeriod(new Devonian(), false);
		DEDatabaseRegistry.registerPeriod(new Carboniferous(), false);
		DEDatabaseRegistry.registerPeriod(new Permian(), false);
		DEDatabaseRegistry.registerPeriod(new Triassic(), false);
		DEDatabaseRegistry.registerPeriod(new Jurassic(), true);
		DEDatabaseRegistry.registerPeriod(new Cretaceous(), true);
		DEDatabaseRegistry.registerPeriod(new Paleogene(), false);
		DEDatabaseRegistry.registerPeriod(new Neogene(), true);
		DEDatabaseRegistry.registerPeriod(new Quaternary(), true);
	}

	private static void registerVanillaCreature(Creature creature)
	{
		if (creature != null)
		{
			String creatureName = creature.getName();
			if (!DEDatabaseRegistry.LIST_VANILLA_CREATURES.containsKey(creatureName))
				DEDatabaseRegistry.LIST_VANILLA_CREATURES.put(creatureName, creature);
			else
				DeExtinction.logger.error("Vanilla creature " + creatureName + " is already registered in the DEDatabaseRegistry.LIST_VANILLA_CREATURES. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Vanilla creature is null. THIS IS A BUG!");
	}

	private static void registerDeExtinctCreature(Creature creature)
	{
		if (creature != null)
		{
			String creatureName = creature.getName();
			if (!DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.containsValue(creatureName))
				DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.put(creatureName, creature);
			else
				DeExtinction.logger.error("Creature " + creatureName + " is already registered in the DEDatabaseRegistry.LIST_DEEXTINCTED_CREATURES. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Creature is null. THIS IS A BUG!");
	}

	/**
	 * Adds all creatures to the all creatures HashMap, which are used to
	 * research them.
	 */
	private static void registerCreatureTree()
	{
		for (Creature creature : DEDatabaseRegistry.LIST_VANILLA_CREATURES.values())
		{
			if (creature != null)
			{
				String creatureName = creature.getName();
				if (!DEDatabaseRegistry.LIST_ALL_CREATURES.containsValue(creatureName))
					DEDatabaseRegistry.LIST_ALL_CREATURES.put(creatureName, creature);
				else
					DeExtinction.logger.error("Creature " + creatureName + " is already registered in the DEDatabaseRegistry.LIST_ALL_CREATURES. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Creature is null. THIS IS A BUG!");
		}

		for (Creature creature : DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.values())
		{
			if (creature != null)
			{
				String creatureName = creature.getName();
				if (!DEDatabaseRegistry.LIST_ALL_CREATURES.containsValue(creatureName))
					DEDatabaseRegistry.LIST_ALL_CREATURES.put(creatureName, creature);
				else
					DeExtinction.logger.error("Creature " + creatureName + " is already registered in the DEDatabaseRegistry.LIST_ALL_CREATURES. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Creature is null. THIS IS A BUG!");
		}
	}

	/**
	 * Adds non-vanilla creatures to the periods HashMaps, which are used to
	 * spawn their fossils.
	 */
	private static void addCreatureInPeriods(Creature creature)
	{
		if (creature != null)
		{
			String creatureName = creature.getName();
			if (DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.containsKey(creatureName))
			{
				if (!DEDatabaseRegistry.LIST_VANILLA_CREATURES.containsKey(creatureName))
				{
					List<Period> periodList = creature.getPeriodsList();
					if (periodList != null)
					{
						for (Period period : periodList)
						{
							String periodName = period.getName();
							if (periodName == Quaternary.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Quaternary.NAME).registerCreature(creature);
							else if (periodName == Neogene.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Neogene.NAME).registerCreature(creature);
							else if (periodName == Paleogene.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Paleogene.NAME).registerCreature(creature);
							else if (periodName == Cretaceous.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Cretaceous.NAME).registerCreature(creature);
							else if (periodName == Jurassic.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Jurassic.NAME).registerCreature(creature);
							else if (periodName == Triassic.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Triassic.NAME).registerCreature(creature);
							else if (periodName == Permian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Permian.NAME).registerCreature(creature);
							else if (periodName == Carboniferous.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Carboniferous.NAME).registerCreature(creature);
							else if (periodName == Devonian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Devonian.NAME).registerCreature(creature);
							else if (periodName == Silurian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Silurian.NAME).registerCreature(creature);
							else if (periodName == Ordovician.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Ordovician.NAME).registerCreature(creature);
							else if (periodName == Cambrian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Cambrian.NAME).registerCreature(creature);
						}
					}
					else
						DeExtinction.logger.info("Creature " + creatureName + " was not registed in any period. Is that correct?");
				}
			}
		}
		else
			DeExtinction.logger.error("Creature is null. THIS IS A BUG!");
	}

	/**
	 * Adds creatures to their respective branch in deextinction, which are used
	 * to create the tree branches in the genetic research station.
	 */
	private static void addCreatureInBranch(Creature creature)
	{
		if (creature != null)
		{
			String creatureName = creature.getName();
			if (DEDatabaseRegistry.LIST_ALL_CREATURES.containsKey(creatureName))
			{
				int branch = creature.getDeExtinctionBranch();
				switch (branch)
				{
					case DEDatabaseRegistry.THEROPODS_BRANCH:
						DEDatabaseRegistry.LIST_CREATURE_THEROPODS_BRANCH.put(creatureName, creature);
						break;
					case DEDatabaseRegistry.MAMMALS_BRANCH:
						DEDatabaseRegistry.LIST_CREATURE_MAMMALS_BRANCH.put(creatureName, creature);
						break;
				}
			}
			else
				DeExtinction.logger.error("Creature " + creatureName + " is not registered in the DEDatabaseRegistry.LIST_ALL_CREATURES. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Creature is null. THIS IS A BUG!");
	}

	private static void registerVanillaFoliage(Foliage foliage)
	{
		if (foliage != null)
		{
			String foliageName = foliage.getName();
			if (!DEDatabaseRegistry.LIST_VANILLA_FOLIAGE.containsKey(foliageName))
				DEDatabaseRegistry.LIST_VANILLA_FOLIAGE.put(foliageName, foliage);
			else
				DeExtinction.logger.error("Vanilla foliage " + foliageName + " is already registered in the DEDatabaseRegistry.LIST_VANILLA_FOLIAGE. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Vanilla foliage is null. THIS IS A BUG!");
	}

	private static void registerDeExtinctFoliage(Foliage foliage)
	{
		if (foliage != null)
		{
			String foliageName = foliage.getName();
			if (!DEDatabaseRegistry.LIST_DEEXTINCT_FOLIAGE.containsValue(foliageName))
				DEDatabaseRegistry.LIST_DEEXTINCT_FOLIAGE.put(foliageName, foliage);
			else
				DeExtinction.logger.error("Foliage " + foliageName + " is already registered in the DEDatabaseRegistry.LIST_DEEXTINCTED_FOLIAGE. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Foliage is null. THIS IS A BUG!");
	}

	/**
	 * Adds all foliage to the all foliage HashMap, which are used to research
	 * them.
	 */
	private static void registerFoliageTree()
	{
		for (Foliage foliage : DEDatabaseRegistry.LIST_VANILLA_FOLIAGE.values())
		{
			if (foliage != null)
			{
				String foliageName = foliage.getName();
				if (!DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsValue(foliageName))
					DEDatabaseRegistry.LIST_ALL_FOLIAGE.put(foliageName, foliage);
				else
					DeExtinction.logger.error("Foliage " + foliageName + " is already registered in the DEDatabaseRegistry.LIST_ALL_FOLIAGE. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Foliage is null. THIS IS A BUG!");
		}

		for (Foliage foliage : DEDatabaseRegistry.LIST_DEEXTINCT_FOLIAGE.values())
		{
			if (foliage != null)
			{
				String foliageName = foliage.getName();
				if (!DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsValue(foliageName))
					DEDatabaseRegistry.LIST_ALL_FOLIAGE.put(foliageName, foliage);
				else
					DeExtinction.logger.error("Foliage " + foliageName + " is already registered in the DEDatabaseRegistry.LIST_ALL_FOLIAGE. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Foliage is null. THIS IS A BUG!");
		}
	}

	/**
	 * Adds non-vanilla foliage to the periods HashMaps, which are used to spawn
	 * their fossils.
	 */
	private static void addFoliageInPeriods(Foliage foliage)
	{
		if (foliage != null)
		{
			String foliageName = foliage.getName();
			if (DEDatabaseRegistry.LIST_DEEXTINCT_FOLIAGE.containsKey(foliageName))
			{
				if (!DEDatabaseRegistry.LIST_VANILLA_FOLIAGE.containsKey(foliageName))
				{
					List<Period> periodList = foliage.getPeriodsList();
					if (periodList != null)
					{
						for (Period period : periodList)
						{
							String periodName = period.getName();
							if (periodName == Quaternary.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Quaternary.NAME).registerFoliage(foliage);
							else if (periodName == Neogene.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Neogene.NAME).registerFoliage(foliage);
							else if (periodName == Paleogene.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Paleogene.NAME).registerFoliage(foliage);
							else if (periodName == Cretaceous.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Cretaceous.NAME).registerFoliage(foliage);
							else if (periodName == Jurassic.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Jurassic.NAME).registerFoliage(foliage);
							else if (periodName == Triassic.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Triassic.NAME).registerFoliage(foliage);
							else if (periodName == Permian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Permian.NAME).registerFoliage(foliage);
							else if (periodName == Carboniferous.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Carboniferous.NAME).registerFoliage(foliage);
							else if (periodName == Devonian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Devonian.NAME).registerFoliage(foliage);
							else if (periodName == Silurian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Silurian.NAME).registerFoliage(foliage);
							else if (periodName == Ordovician.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Ordovician.NAME).registerFoliage(foliage);
							else if (periodName == Cambrian.NAME)
								DEDatabaseRegistry.PERIOD_LIST.get(Cambrian.NAME).registerFoliage(foliage);
						}
					}
					else
						DeExtinction.logger.info("Foliage " + foliageName + " was not registed in any period. Is that correct?");
				}
			}
		}
		else
			DeExtinction.logger.error("Foliage is null. THIS IS A BUG!");
	}

	/**
	 * Adds foliage to their respective branch in deextinction, which are used
	 * to create the tree branches in the genetic research station.
	 */
	private static void addFoliageInBranch(Foliage foliage)
	{
		if (foliage != null)
		{
			String foliageName = foliage.getName();
			if (DEDatabaseRegistry.LIST_ALL_FOLIAGE.containsKey(foliageName))
			{
				int branch = foliage.getDeExtinctionBranch();
				switch (branch)
				{
					case DEDatabaseRegistry.BUSHES_BRANCH:
						DEDatabaseRegistry.LIST_FOLIAGE_BUSHES_BRANCH.put(foliageName, foliage);
						break;
					case DEDatabaseRegistry.TREES_BRANCH:
						DEDatabaseRegistry.LIST_FOLIAGE_TREES_BRANCH.put(foliageName, foliage);
						break;
				}
			}
			else
				DeExtinction.logger.error("Foliage " + foliageName + " is not registered in the DEDatabaseRegistry.LIST_ALL_FOLIAGE. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Foliage is null. THIS IS A BUG!");
	}
}
