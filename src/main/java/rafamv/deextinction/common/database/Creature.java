package rafamv.deextinction.common.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public abstract class Creature
{
	protected String name;
	protected HashMap<ItemStack, Integer> listItems = new HashMap<ItemStack, Integer>();
	protected List<Period> listPeriods = new ArrayList<Period>();
	protected HashMap<String, Integer> listPreviousCreatures = new HashMap<String, Integer>();
	protected GalleryPosition[] previousResearchPositions;
	protected HashMap<Byte, CreatureTexture> listTextureMale = new HashMap<Byte, CreatureTexture>();
	protected HashMap<Byte, CreatureTexture> listTextureFemale = new HashMap<Byte, CreatureTexture>();
	protected HashMap<Byte, Integer> growthStagesMap = new HashMap<Byte, Integer>();
	protected HashMap<Byte, Double> stageToHealthMapping = new HashMap<Byte, Double>();
	protected HashMap<Byte, Double> stageToAttackMapping = new HashMap<Byte, Double>();
	protected HashMap<Byte, Double> stageToMovementMapping = new HashMap<Byte, Double>();
	protected HashMap<Byte, Double> stageToSpeedMapping = new HashMap<Byte, Double>();
	protected HashMap<Byte, Double> stageToKnockbackMapping = new HashMap<Byte, Double>();
	protected HashMap<Byte, Float> stageToScalesMapping = new HashMap<Byte, Float>();

	public Creature(String name)
	{
		this.name = name;
	}

	public void initCreature()
	{
		this.initFossilItems();
		this.initCreaturePeriods();
		this.initPreviousCreatures();
		this.initPreviousResearchPositions();
		this.initMaleCreatureTextures();
		this.initFemaleCreatureTextures();
		this.initGrowthStages();
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
		return "entity." + this.name + ".name";
	}

	public HashMap<ItemStack, Integer> getFossilizedItems()
	{
		return this.listItems;
	}

	public List<Period> getPeriodsList()
	{
		return this.listPeriods;
	}

	public HashMap<String, Integer> getPreviousCreaturesList()
	{
		return this.listPreviousCreatures;
	}

	public GalleryPosition[] getPreviousResearchPositions()
	{
		return this.previousResearchPositions;
	}

	public HashMap<Byte, CreatureTexture> getMaleCreatureTextures()
	{
		return this.listTextureMale;
	}

	public HashMap<Byte, CreatureTexture> getFemaleCreatureTextures()
	{
		return this.listTextureFemale;
	}

	public HashMap<Byte, Integer> getGrowthStagesMap()
	{
		return this.growthStagesMap;
	}

	public int getGrowthStagesSize()
	{
		return this.growthStagesMap.size();
	}

	public HashMap<Byte, Double> getStageToHealthMapping()
	{
		return this.stageToHealthMapping;
	}

	public HashMap<Byte, Double> getStageToAttackMapping()
	{
		return this.stageToAttackMapping;
	}

	public HashMap<Byte, Double> getStageToMovementMapping()
	{
		return this.stageToMovementMapping;
	}

	public HashMap<Byte, Double> getStageToSpeedMapping()
	{
		return this.stageToSpeedMapping;
	}

	public HashMap<Byte, Double> getStageToKnockbackMapping()
	{
		return this.stageToKnockbackMapping;
	}

	public HashMap<Byte, Float> getStageToScaleMapping()
	{
		return this.stageToScalesMapping;
	}

	public boolean canCreateCreature(HashMap<String, Integer> researchList)
	{
		if (researchList != null)
		{
			if (this.listPreviousCreatures != null)
			{
				for (String creatureName : this.listPreviousCreatures.keySet())
				{
					if (!researchList.containsKey(creatureName) || researchList.get(creatureName) < this.listPreviousCreatures.get(creatureName) || (this.getResearchButtonItem() != null && this.getResearchButtonItem().getItem() == DEItemRegistry.unknown_image))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public void registerPreviousCreature(String creatureName)
	{
		Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(creatureName);
		if (creature != null)
		{
			if (!this.listPreviousCreatures.containsValue(creatureName))
				this.listPreviousCreatures.put(creatureName, 50);
			else
				DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was not found in the DEDatabaseRegistry.creature_list. THIS IS A BUG!");
	}

	public void registerPreviousCreature(String creatureName, int researchRequired)
	{
		Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(creatureName);
		if (creature != null)
		{
			if (researchRequired < 0)
			{
				researchRequired = 0;
				DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was registed in the " + this.name + " class with a research requirement lower than 0. Requirement was reset to 0. THIS IS A BUG!");
			}
			else if (researchRequired > 100)
			{
				researchRequired = 100;
				DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was registed in the " + this.name + " class with a research requirement higher than 100 Requirement was reset to 100. THIS IS A BUG!");
			}

			if (!this.listPreviousCreatures.containsValue(creatureName))
				this.listPreviousCreatures.put(creatureName, researchRequired);
			else
				DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Previous creature with name '" + creatureName + "' was not found in the DEDatabaseRegistry.creature_list. THIS IS A BUG!");
	}

	public void initPreviousResearchPositions()
	{
		if (this.listPreviousCreatures != null && !this.listPreviousCreatures.isEmpty())
		{
			this.previousResearchPositions = new GalleryPosition[this.listPreviousCreatures.size()];
			int i = 0;
			for (String creatureName : this.listPreviousCreatures.keySet())
			{
				Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(creatureName);
				if (creature != null)
				{
					this.previousResearchPositions[i] = creature.getResearchPosition();
					i++;
				}
			}
		}
	}

	public void registerFossilItem(ItemStack itemStack, int researchValue)
	{
		if (itemStack != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + itemStack.getItem().getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + itemStack.getItem().getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Item item, int researchValue)
	{
		ItemStack itemStack = new ItemStack(item, 1, 0);
		if (item != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + item.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + item.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Item item, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(item, 1, metadata);
		if (item != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + item.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + item.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Item item, int amount, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(item, amount, metadata);
		if (item != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + item.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + item.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Block block, int researchValue)
	{
		ItemStack itemStack = new ItemStack(block, 1, 0);
		if (block != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + block.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + block.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Block block, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(block, 1, metadata);
		if (block != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + block.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + block.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFossilItem(Block block, int amount, int metadata, int researchValue)
	{
		ItemStack itemStack = new ItemStack(block, amount, metadata);
		if (block != null)
		{
			if (!this.listItems.containsKey(itemStack))
			{
				if (researchValue < 0)
				{
					DeExtinction.logger.error("Invalid research value for item " + block.getUnlocalizedName() + " was attemped to be registered in the " + this.name + " class. The value was reset to zero. THIS IS A BUG!");
					researchValue = 0;
				}
				this.listItems.put(itemStack, researchValue);
			}
			else
				DeExtinction.logger.error("Creature item source with unlocalized name '" + block.getUnlocalizedName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null creature item source was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerCreaturePeriod(String periodName)
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

	public void registerMaleTexture(byte textureID, byte textureRequirement)
	{
		CreatureTexture texture = new CreatureTexture(textureID, this.getName(), "male", textureRequirement);
		if (!this.listTextureMale.containsValue(texture))
		{
			if (this.listTextureMale != null)
			{
				if (!this.listTextureMale.containsKey(textureID))
				{
					if (textureRequirement > 49 && textureRequirement < 101)
						this.listTextureMale.put(textureID, texture);
					else
						DeExtinction.logger.error("Texture with  id '" + textureID + "' was a invalid requirement registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("Male texture with  id '" + textureID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("ListTextureFemale was null in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Male texture with  name '" + this.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerFemaleTexture(byte textureID, byte textureRequirement)
	{
		CreatureTexture texture = new CreatureTexture(textureID, this.getName(), "female", textureRequirement);
		if (!this.listTextureFemale.containsValue(texture))
		{
			if (this.listTextureFemale != null)
			{
				if (!this.listTextureFemale.containsKey(textureID))
				{
					if (textureRequirement > 49 && textureRequirement < 101)
						this.listTextureFemale.put(textureID, texture);
					else
						DeExtinction.logger.error("Texture with  id '" + textureID + "' was a invalid requirement registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("Female texture with  id '" + textureID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("ListTextureFemale was null in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Female texture with  name '" + this.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerGrowthStage(byte stageID, int ageRequired, double health, double attack, double movement, double knockback, double speed, float scale)
	{
		if (health > 0)
		{
			if (attack >= 0)
			{
				if (movement >= 0)
				{
					if (knockback >= 0)
					{
						if (speed >= 0)
						{
							if (this.growthStagesMap != null)
							{
								if (!this.growthStagesMap.containsKey(stageID))
								{
									this.growthStagesMap.put(stageID, ageRequired);
									this.stageToHealthMapping.put(stageID, health);
									this.stageToAttackMapping.put(stageID, attack);
									this.stageToMovementMapping.put(stageID, movement);
									this.stageToKnockbackMapping.put(stageID, knockback);
									this.stageToSpeedMapping.put(stageID, speed);
									this.stageToScalesMapping.put(stageID, scale);
								}
								else
									DeExtinction.logger.error("Stage id '" + stageID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
							}
							else
								DeExtinction.logger.error("GrowthStagesMap was null in the " + this.name + " class. THIS IS A BUG!");
						}
						else
							DeExtinction.logger.error("Invalid speed was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Invalid knockback was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("Invalid movement was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Invalid attack was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Invalid health was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	/** Returns the name of this this. */
	public final String getDisplayName()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".name");
	}

	/** Returns which period this creature lived. */
	public final String getCreaturePeriod()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".period");
	}

	/** Returns this creature height. */
	public final String getCreatureHeight()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".height");
	}

	/** Returns this creature length. */
	public final String getCreatureLength()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".length");
	}

	/** Returns this creature weight. */
	public final String getCreatureWeight()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".weight");
	}

	/** Returns the place where this creature was found. */
	public final String getCreatureWhere()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".where");
	}

	/** Returns which diet this creature had. */
	public final String getCreatureDiet()
	{
		return StatCollector.translateToLocal("entity." + this.name + ".diet");
	}

	public abstract EntityLivingBase getEntity(World worldIn);

	public abstract byte getDeExtinctionBranch();

	public abstract GalleryPosition getResearchPosition();

	public abstract int getNutrientsRequired();

	public abstract ItemStack getResearchButtonItem();

	public abstract ItemStack getEmbryoContainerItem();

	public abstract Item getHatchItem();

	public abstract void initFossilItems();

	public abstract void initCreaturePeriods();

	public abstract void initPreviousCreatures();

	public abstract void initMaleCreatureTextures();

	public abstract void initFemaleCreatureTextures();

	public abstract void initGrowthStages();
}
