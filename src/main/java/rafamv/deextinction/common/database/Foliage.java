package rafamv.deextinction.common.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public abstract class Foliage
{
	protected String name;
	protected HashMap<Byte, FoliageVariant> listVariants = new HashMap<Byte, FoliageVariant>();
	protected HashMap<ItemStack, Integer> listItems = new HashMap<ItemStack, Integer>();
	protected List<Period> listPeriods = new ArrayList<Period>();
	protected HashMap<String, Integer> listPreviousFoliage = new HashMap<String, Integer>();
	protected GalleryPosition[] previousResearchPositions;
	protected ItemStack requiredItem;

	public Foliage(String name)
	{
		this.name = name;
	}

	public void initFoliage()
	{
		this.initVariants();
		this.initFossilItems();
		this.initFoliagePeriods();
		this.initPreviousFoliage();
		this.initRequiredItem();
		this.initPreviousResearchPositions();
	}

	public float getSpawnWeight()
	{
		return 1.0F;
	}

	public String getName()
	{
		return this.name;
	}

	public final String getUnlocalizedName()
	{
		return "plant." + this.name + ".name";
	}

	public HashMap<Byte, FoliageVariant> getVariants()
	{
		return this.listVariants;
	}

	public FoliageVariant getVariant(byte variantID)
	{
		return this.listVariants.get(variantID);
	}

	public HashMap<ItemStack, Integer> getFossilizedItems()
	{
		return this.listItems;
	}

	public List<Period> getPeriodsList()
	{
		return this.listPeriods;
	}

	public HashMap<String, Integer> getPreviousFoliageList()
	{
		return this.listPreviousFoliage;
	}

	public GalleryPosition[] getPreviousResearchPositions()
	{
		return this.previousResearchPositions;
	}

	public ItemStack getRequiredItem()
	{
		return this.requiredItem;
	}

	public boolean canCreateFoliage(HashMap<String, Integer> researchList)
	{
		if (researchList != null)
		{
			if (this.listPreviousFoliage != null)
			{
				for (String foliageName : this.listPreviousFoliage.keySet())
				{
					if (!researchList.containsKey(foliageName) || researchList.get(foliageName) < this.listPreviousFoliage.get(foliageName))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public void registerVariant(Block sapling, byte variantID, byte variantRequirement)
	{
		if (sapling != null)
		{
			FoliageVariant texture = new FoliageVariant(variantID, this.getName(), variantRequirement, sapling);
			if (!this.listVariants.containsValue(texture))
			{
				if (this.listVariants != null)
				{
					if (!this.listVariants.containsKey(variantID))
					{
						if (variantRequirement > 49 && variantRequirement < 101)
							this.listVariants.put(variantID, texture);
						else
							DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was a invalid requirement (" + variantRequirement + ") registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("ListVariants was null in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Foliage variant with  name '" + this.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage variant was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerVariant(Block sapling, int saplingMetadata, byte variantID, byte variantRequirement)
	{
		if (sapling != null)
		{
			FoliageVariant texture = new FoliageVariant(variantID, this.getName(), variantRequirement, sapling, saplingMetadata);
			if (!this.listVariants.containsValue(texture))
			{
				if (this.listVariants != null)
				{
					if (!this.listVariants.containsKey(variantID))
					{
						if (variantRequirement > 49 && variantRequirement < 101)
							this.listVariants.put(variantID, texture);
						else
							DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was a invalid requirement (" + variantRequirement + ") registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("ListVariants was null in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Foliage variant with  name '" + this.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage variant was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerVariant(Block sapling, int saplingAmount, int saplingMetadata, byte variantID, byte variantRequirement)
	{
		if (sapling != null)
		{
			FoliageVariant texture = new FoliageVariant(variantID, this.getName(), variantRequirement, sapling, saplingAmount, saplingMetadata);
			if (!this.listVariants.containsValue(texture))
			{
				if (this.listVariants != null)
				{
					if (!this.listVariants.containsKey(variantID))
					{
						if (variantRequirement > 49 && variantRequirement < 101)
							this.listVariants.put(variantID, texture);
						else
							DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was a invalid requirement (" + variantRequirement + ") registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Foliage variant with  id '" + variantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("ListVariants was null in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Foliage variant with  name '" + this.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage variant was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerPreviousFoliage(String foliageName)
	{
		Foliage Foliage = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(foliageName);
		if (Foliage != null)
		{
			if (!this.listPreviousFoliage.containsValue(foliageName))
				this.listPreviousFoliage.put(foliageName, 50);
			else
				DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was not found in the DEDatabaseRegistry.foliage_list. THIS IS A BUG!");
	}

	public void registerPreviousFoliage(String foliageName, int researchRequired)
	{
		Foliage Foliage = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(foliageName);
		if (Foliage != null)
		{
			if (researchRequired < 0)
			{
				researchRequired = 0;
				DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was registed in the " + this.name + " class with a research requirement lower than 0. Requirement was reset to 0. THIS IS A BUG!");
			}
			else if (researchRequired > 100)
			{
				researchRequired = 100;
				DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was registed in the " + this.name + " class with a research requirement higher than 100 Requirement was reset to 100. THIS IS A BUG!");
			}

			if (!this.listPreviousFoliage.containsValue(foliageName))
				this.listPreviousFoliage.put(foliageName, researchRequired);
			else
				DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Previous foliage with name '" + foliageName + "' was not found in the DEDatabaseRegistry.foliage_list. THIS IS A BUG!");
	}

	public void registerRequiredItem(Item requiredItem, int requiredItemMetadata)
	{
		if (requiredItem != null)
			this.requiredItem = new ItemStack(requiredItem, 1, requiredItemMetadata);
		else
			DeExtinction.logger.error("Null required item was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerRequiredItem(Block requiredItem, int requiredItemMetadata)
	{
		if (requiredItem != null)
			this.requiredItem = new ItemStack(requiredItem, 1, requiredItemMetadata);
		else
			DeExtinction.logger.error("Null required block was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerRequiredItem(ItemStack requiredItemStack)
	{
		if (requiredItemStack != null)
			if (requiredItemStack.getItem() != null)
				this.requiredItem = requiredItemStack;
			else
				DeExtinction.logger.error("Null required itemstack item was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
		else
			DeExtinction.logger.error("Null required itemstack was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void initPreviousResearchPositions()
	{
		if (this.listPreviousFoliage != null && !this.listPreviousFoliage.isEmpty())
		{
			this.previousResearchPositions = new GalleryPosition[this.listPreviousFoliage.size()];
			int i = 0;
			for (String FoliageName : this.listPreviousFoliage.keySet())
			{
				Foliage Foliage = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(FoliageName);
				if (Foliage != null)
				{
					this.previousResearchPositions[i] = Foliage.getResearchPosition();
					i++;
				}
			}
		}
	}

	public void registerFossilItem(ItemStack itemStack, int researchValue)
	{
		if (itemStack != null && itemStack.getItem() != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Foliage item source with unlocalized name '" + itemStack.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Item item, int researchValue)
	{
		ItemStack itemStack = new ItemStack(item);
		if (itemStack != null && itemStack.getItem() != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Foliage item source with unlocalized name '" + itemStack.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Item item, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(item, 1, metadata);
		if (itemStack != null && itemStack.getItem() != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Foliage item source with unlocalized name '" + itemStack.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Block block, int researchValue)
	{
		ItemStack itemStack = new ItemStack(block);
		if (itemStack != null && itemStack.getItem() != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Foliage item source with unlocalized name '" + itemStack.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Block block, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(block, 1, metadata);
		if (itemStack != null && itemStack.getItem() != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Foliage item source with unlocalized name '" + itemStack.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null foliage item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFoliagePeriod(String periodName)
	{
		if (DEDatabaseRegistry.PERIOD_LIST != null)
		{
			if (periodName != null)
			{
				if (DEDatabaseRegistry.PERIOD_LIST.containsKey(periodName))
				{
					Period period = DEDatabaseRegistry.PERIOD_LIST.get(periodName);
					if (period != null)
					{
						if (!this.listPeriods.contains(period))
							this.listPeriods.add(period);
						else
							DeExtinction.logger.error("Period with unlocalized name '" + period.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Null period was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("DEDatabaseRegistry.PERIOD_LIST does not have the period name " + periodName + " registered. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("DEDatabaseRegistry.PERIOD_LIST is null. THIS IS A BUG!");
		}
	}

	public void registerGrowthStage(byte stageID, int ageRequired, double health, double attack, double movement, double knockback, double speed, float scale)
	{

	}

	/** Returns the name of this this. */
	public final String getDisplayName()
	{
		return StatCollector.translateToLocal("foliage." + this.name + ".name");
	}

	/** Returns which period this Foliage lived. */
	public final String getFoliagePeriod()
	{
		return StatCollector.translateToLocal("foliage." + this.name + ".period");
	}

	/** Returns this Foliage height. */
	public final String getFoliageHeight()
	{
		return StatCollector.translateToLocal("foliage." + this.name + ".height");
	}

	/** Returns the place where this Foliage was found. */
	public final String getFoliageWhere()
	{
		return StatCollector.translateToLocal("foliage." + this.name + ".where");
	}

	public abstract byte getDeExtinctionBranch();

	public abstract GalleryPosition getResearchPosition();

	public abstract int getNutrientsRequired(byte variant);

	public abstract ItemStack getResearchButtonItem();

	public abstract void initRequiredItem();

	public abstract void initFossilItems();

	public abstract void initFoliagePeriods();

	public abstract void initPreviousFoliage();

	public abstract void initVariants();
}
