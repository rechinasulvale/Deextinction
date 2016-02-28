package rafamv.deextinction.common.database;

import java.util.HashMap;
import java.util.LinkedHashMap;

import net.minecraft.util.StatCollector;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.world.DEFossilHelper;

public abstract class Period
{
	protected String name;
	private LinkedHashMap<Creature, Float> creatureList = new LinkedHashMap<Creature, Float>();
	private LinkedHashMap<Foliage, Float> foliageList = new LinkedHashMap<Foliage, Float>();

	public Period(String name)
	{
		this.name = name;
	}

	public void initPeriod()
	{
		this.applyNoiseToCreatures();
		this.applyNoiseToFoliage();
	}

	public float getSpawnWeight()
	{
		return 1.0F;
	}

	public String getName()
	{
		return this.name;
	}

	public HashMap<Creature, Float> getCreatureList()
	{
		return this.creatureList;
	}

	public HashMap<Foliage, Float> getFoliageList()
	{
		return this.foliageList;
	}

	public void registerCreature(Creature creature)
	{
		if (creature != null)
		{
			if (!this.creatureList.containsKey(creature))
				this.creatureList.put(creature, -1.0F);
			else
				DeExtinction.logger.error("Creature '" + creature.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
	}

	public void registerFoliage(Foliage foliage)
	{
		if (foliage != null)
		{
			if (!this.foliageList.containsKey(foliage))
				this.foliageList.put(foliage, -1.0F);
			else
				DeExtinction.logger.error("Foliage '" + foliage.getName() + "' was already registed in the " + this.name + " class. THIS IS A BUG!");
		}
	}

	public void applyNoiseToCreatures()
	{
		if (this.creatureList != null && !this.creatureList.isEmpty())
		{
			float totalWeight = 0.0F;
			for (Creature creature : this.creatureList.keySet())
				totalWeight += creature.getSpawnWeight();

			if (totalWeight != 0.0F)
			{
				float noisePossibleInterval = 2.0F - 2.0F * DEFossilHelper.NOISE_INTERVAL_CORRECTION;

				for (Creature creature : this.creatureList.keySet())
					this.creatureList.put(creature, noisePossibleInterval * creature.getSpawnWeight() / totalWeight);

				float probability = -1.0F + DEFossilHelper.NOISE_INTERVAL_CORRECTION;
				int listSize = this.creatureList.size() - 1;
				int count = 0;

				for (Creature creature : this.creatureList.keySet())
				{
					probability += this.creatureList.get(creature);
					if (count < listSize)
					{
						this.creatureList.put(creature, probability);
						count++;
					}
					else
						this.creatureList.put(creature, 1.0F);
				}
			}
		}
	}

	public void applyNoiseToFoliage()
	{
		if (this.foliageList != null && !this.foliageList.isEmpty())
		{
			float totalWeight = 0.0F;
			for (Foliage foliage : this.foliageList.keySet())
				totalWeight += foliage.getSpawnWeight();

			if (totalWeight != 0.0F)
			{
				float noisePossibleInterval = 2.0F - 2.0F * DEFossilHelper.NOISE_INTERVAL_CORRECTION;

				for (Foliage foliage : this.foliageList.keySet())
					this.foliageList.put(foliage, noisePossibleInterval * foliage.getSpawnWeight() / totalWeight);

				float probability = -1.0F + DEFossilHelper.NOISE_INTERVAL_CORRECTION;
				int listSize = this.foliageList.size() - 1;
				int count = 0;

				for (Foliage foliage : this.foliageList.keySet())
				{
					probability += this.foliageList.get(foliage);
					if (count < listSize)
					{
						this.foliageList.put(foliage, probability);
						count++;
					}
					else
						this.foliageList.put(foliage, 1.0F);
				}
			}
		}
	}

	public final String getUnlocalizedName()
	{
		return "period." + this.name + ".name";
	}

	/** Returns the name of this period. */
	public final String getPeriodName()
	{
		return StatCollector.translateToLocal("period." + this.name + ".name");
	}

	/** Returns when this period started. */
	public final String getPeriodStartTime()
	{
		return StatCollector.translateToLocal("period." + this.name + ".start_time");
	}

	/** Returns when this period ended. */
	public final String getPeriodFinalTime()
	{
		return StatCollector.translateToLocal("period." + this.name + ".final_time");
	}

	/** Returns a description of this period. */
	public final String getPeriodDescription()
	{
		return StatCollector.translateToLocal("period." + this.name + ".description");
	}

	public String getCreatureListInfo()
	{
		String info = "Creatures: ";
		if (this.creatureList != null && !this.creatureList.isEmpty())
			for (Creature creature : this.creatureList.keySet())
				info += "[" + creature.getDisplayName() + ", " + this.creatureList.get(creature) + "] ";
		else
			info += "None";
		return info;
	}

	public String getFoliageListInfo()
	{
		String info = "Foliage: ";
		if (this.foliageList != null && !this.foliageList.isEmpty())
			for (Foliage foliage : this.foliageList.keySet())
				info += "[" + foliage.getDisplayName() + ", " + this.foliageList.get(foliage) + "] ";
		else
			info += "None";
		return info;
	}
}
