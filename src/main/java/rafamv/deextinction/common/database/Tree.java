package rafamv.deextinction.common.database;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.world.trees.WorldGenDeExtinctedTree;

public abstract class Tree extends Foliage
{
	protected HashMap<Byte, List<ItemStack>> stageToLogsMapping = new HashMap<Byte, List<ItemStack>>();
	protected HashMap<Byte, List<ItemStack>> stageToLeavesMapping = new HashMap<Byte, List<ItemStack>>();
	protected HashMap<Byte, List<ItemStack>> stageToSpecialDropsMapping = new HashMap<Byte, List<ItemStack>>();
	protected HashMap<Byte, WorldGenAbstractTree> stageToTreeGeneratorMapping = new HashMap<Byte, WorldGenAbstractTree>();

	public Tree(String name)
	{
		super(name);
	}

	public void initFoliage()
	{
		super.initFoliage();
		this.initTreeGen();
	}

	public List<ItemStack> getLogsFromVariant(byte variantID)
	{
		return this.stageToLogsMapping != null ? this.stageToLogsMapping.get(variantID) : null;
	}

	public ItemStack getRandomLogsFromVariant(Random rand, byte variantID)
	{
		return this.stageToLogsMapping != null ? (this.stageToLogsMapping.containsKey(variantID) ? (this.stageToLogsMapping.get(variantID) != null ? (!this.stageToLogsMapping.get(variantID).isEmpty() ? this.stageToLogsMapping.get(variantID).get(
				rand.nextInt(this.stageToLogsMapping.get(variantID).size())) : null) : null) : null) : null;
	}

	public List<ItemStack> getLeavesFromVariant(byte variantID)
	{
		return this.stageToLeavesMapping != null ? this.stageToLeavesMapping.get(variantID) : null;
	}

	public ItemStack getRandomLeavesFromVariant(Random rand, byte variantID)
	{
		return this.stageToLeavesMapping != null ? (this.stageToLeavesMapping.get(variantID) != null ? (!this.stageToLeavesMapping.get(variantID).isEmpty() ? this.stageToLeavesMapping.get(variantID).get(rand.nextInt(this.stageToLeavesMapping.get(variantID).size())) : null) : null) : null;
	}

	public List<ItemStack> getSpecialDropsFromVariant(byte variantID)
	{
		return this.stageToSpecialDropsMapping != null ? (this.stageToSpecialDropsMapping.containsKey(variantID) ? this.stageToSpecialDropsMapping.get(variantID) : null) : null;
	}

	public ItemStack getRandomSpecialDropsFromVariant(Random rand, byte variantID)
	{
		return this.stageToSpecialDropsMapping != null ? (this.stageToSpecialDropsMapping.containsKey(variantID) ? (this.stageToSpecialDropsMapping.get(variantID) != null ? (!this.stageToSpecialDropsMapping.get(variantID).isEmpty() ? this.stageToSpecialDropsMapping.get(variantID).get(
				rand.nextInt(this.stageToSpecialDropsMapping.get(variantID).size())) : null) : null) : null) : null;
	}

	public HashMap<Byte, FoliageVariant> getTreeVariants()
	{
		return this.listVariants;
	}

	public WorldGenAbstractTree getTreeGenerator(byte variant)
	{
		return this.stageToTreeGeneratorMapping.get(variant);
	}

	public void registerTreeVariant(Block sapling, byte treeVariantID, byte treeVariantRequirement, List<ItemStack> logs, List<ItemStack> leaves, List<ItemStack> specialDrops)
	{
		if (sapling != null)
		{
			FoliageVariant texture = new FoliageVariant(treeVariantID, this.getName(), treeVariantRequirement, sapling);
			if (!this.listVariants.containsValue(texture))
			{
				if (this.listVariants != null)
				{
					if (!this.listVariants.containsKey(treeVariantID))
					{
						if (treeVariantRequirement > 49 && treeVariantRequirement < 101)
						{
							this.listVariants.put(treeVariantID, texture);
							this.stageToLogsMapping.put(treeVariantID, logs);
							this.stageToLeavesMapping.put(treeVariantID, leaves);
							this.stageToSpecialDropsMapping.put(treeVariantID, specialDrops);
						}
						else
							DeExtinction.logger.error("Tree variant with  id '" + treeVariantID + "' was a invalid requirement registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Tree variant with  id '" + treeVariantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("ListVariants was null in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Null tree variant name was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");

		}
		else
			DeExtinction.logger.error("Null tree sapling from variant " + treeVariantID + " was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerTreeVariant(Block sapling, int saplingMetadata, byte treeVariantID, byte treeVariantRequirement, List<ItemStack> logs, List<ItemStack> leaves, List<ItemStack> specialDrops)
	{
		if (sapling != null)
		{
			FoliageVariant texture = new FoliageVariant(treeVariantID, this.getName(), treeVariantRequirement, sapling, saplingMetadata);
			if (!this.listVariants.containsValue(texture))
			{
				if (this.listVariants != null)
				{
					if (!this.listVariants.containsKey(treeVariantID))
					{
						if (treeVariantRequirement > 49 && treeVariantRequirement < 101)
						{
							this.listVariants.put(treeVariantID, texture);
							this.stageToLogsMapping.put(treeVariantID, logs);
							this.stageToLeavesMapping.put(treeVariantID, leaves);
							this.stageToSpecialDropsMapping.put(treeVariantID, specialDrops);
						}
						else
							DeExtinction.logger.error("Tree variant with  id '" + treeVariantID + "' was a invalid requirement registed in the " + this.name + " class. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Tree variant with  id '" + treeVariantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
				}
				else
					DeExtinction.logger.error("ListVariants was null in the " + this.name + " class. THIS IS A BUG!");
			}
			else
				DeExtinction.logger.error("Null tree variant name was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null tree sapling from variant " + treeVariantID + " was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public void registerTreeGeneration(WorldGenDeExtinctedTree treeGenerator, byte treeVariantID)
	{
		if (treeGenerator != null)
		{
			if (!this.stageToTreeGeneratorMapping.containsKey(treeVariantID))
				this.stageToTreeGeneratorMapping.put(treeVariantID, treeGenerator);
			else
				DeExtinction.logger.error("Tree generator with  id '" + treeVariantID + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
		else
			DeExtinction.logger.error("Null tree generator from variant " + treeVariantID + " was attemped to be registered in the " + this.name + " class. THIS IS A BUG!");
	}

	public abstract void initTreeGen();
}
