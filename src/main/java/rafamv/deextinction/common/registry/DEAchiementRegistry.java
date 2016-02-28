package rafamv.deextinction.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class DEAchiementRegistry
{
	public static Achievement plaster_jacket;
	public static Achievement cleaning_table;
	public static Achievement genetic_research_station;

	public static void preInitCommon()
	{

	}

	public static void initCommon()
	{
		DEAchiementRegistry.plaster_jacket = new Achievement("achievement.plaster_jacket", "plaster_jacket", 0, 0, new ItemStack(DEBlockRegistry.plaster_jacket), (Achievement) null);
		DEAchiementRegistry.plaster_jacket.initIndependentStat().registerStat();

		DEAchiementRegistry.cleaning_table = new Achievement("achievement.cleaning_table", "cleaning_table", 0, 1, new ItemStack(DEBlockRegistry.cleaning_table), (Achievement) null);
		DEAchiementRegistry.cleaning_table.initIndependentStat().registerStat();

		DEAchiementRegistry.genetic_research_station = new Achievement("achievement.genetic_research_station", "genetic_research_station", 2, 2, new ItemStack(DEBlockRegistry.genetic_research_station), (Achievement) null);
		DEAchiementRegistry.genetic_research_station.initIndependentStat().registerStat();

		AchievementPage.registerAchievementPage(new AchievementPage("achievements.deextinction", new Achievement[] { DEAchiementRegistry.plaster_jacket, DEAchiementRegistry.cleaning_table, DEAchiementRegistry.genetic_research_station }));
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

	private static void registerAchievement(Achievement achievement, String unlocalizedName, int column, int row, Item item, Achievement parent)
	{
		achievement = new Achievement("achievement." + unlocalizedName, unlocalizedName, column, row, new ItemStack(item), parent);
		achievement.registerStat();
	}

	private static void registerAchievement(Achievement achievement, String unlocalizedName, int column, int row, Block block, Achievement parent)
	{
		achievement = new Achievement("achievement." + unlocalizedName, unlocalizedName, column, row, new ItemStack(block), parent);
		achievement.registerStat();
	}
}
