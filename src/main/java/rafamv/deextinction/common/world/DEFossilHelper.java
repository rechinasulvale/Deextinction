package rafamv.deextinction.common.world;

import java.util.HashMap;
import java.util.LinkedList;

import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Period;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class DEFossilHelper
{
	public static final String FOSSIL_FAILED = "failed";
	public static final float NOISE_INTERVAL_CORRECTION = 0.125F;
	public static final SimplexNoise SIMPLEX_NOISE = new SimplexNoise();
	public static final HashMap<Integer, Float> PERIOD_NOISE_INTERVALS = new HashMap<Integer, Float>();
	static
	{
		HashMap<String, Period> periodList = DEDatabaseRegistry.PERIOD_LIST;

		float totalWeight = 0.0F;
		for (Period period : periodList.values())
			totalWeight += period.getSpawnWeight();

		if (totalWeight != 0.0F)
		{
			float noisePossibleInterval = 2.0F - 2.0F * DEFossilHelper.NOISE_INTERVAL_CORRECTION;

			int id = 0;
			for (Period period : periodList.values())
			{
				float periodProbability = noisePossibleInterval * period.getSpawnWeight() / totalWeight;
				DEFossilHelper.PERIOD_NOISE_INTERVALS.put(id, periodProbability);
				id++;
			}

			float probability = -1 + DEFossilHelper.NOISE_INTERVAL_CORRECTION;
			id = 0;
			for (float periodProbability : DEFossilHelper.PERIOD_NOISE_INTERVALS.values())
			{
				probability += periodProbability;
				DEFossilHelper.PERIOD_NOISE_INTERVALS.put(id, probability);
				id++;
			}
		}
	}

	/**
	 * Generates noise in 2D using a frequency of 2000.
	 *
	 * @param x
	 *            is the x position;
	 * @param z
	 *            is the z position
	 * 
	 * @return noise as a float
	 */
	public static final float getNoise2DForPeriods(float x, float z)
	{
		return DEFossilHelper.SIMPLEX_NOISE.noise(x / 2000.0F, z / 2000.0F);
	}

	/**
	 * Generates noise in 2D using a frequency of 70.
	 *
	 * @param x
	 *            is the x position;
	 * @param z
	 *            is the z position
	 * 
	 * @return noise as a float
	 */
	public static final float getNoise2DForFossils(float x, float z)
	{
		return DEFossilHelper.SIMPLEX_NOISE.noise(x / 120.0F, z / 120.0F);
	}

	public static final float getRandomNoise2D(float x, float z, float frequency)
	{
		return DEFossilHelper.SIMPLEX_NOISE.noise(x / frequency, z / frequency);
	}

	public static final Period getPeriod(float periodNoise)
	{
		HashMap<String, Period> periodList = DEDatabaseRegistry.PERIOD_LIST;
		if (periodList != null && !periodList.isEmpty())
		{
			int objID = 0;
			int numberOfPeriods = periodList.size();
			float interval = 2.0F / numberOfPeriods;

			for (int id = 0; id < numberOfPeriods; id++)
			{
				float noise = DEFossilHelper.PERIOD_NOISE_INTERVALS.get(id);
				if (noise < periodNoise)
					objID = id + 1;
				else
					break;
			}

			LinkedList<Period> periodLinkedList = new LinkedList<Period>();
			for (Period period : periodList.values())
				periodLinkedList.add(period);

			return periodLinkedList.get(objID);
		}
		return null;
	}

	public static final Creature getCreature(Period period, float creatureNoise)
	{
		if (period != null)
		{
			HashMap<Creature, Float> creatureList = period.getCreatureList();

			if (creatureList != null && !creatureList.isEmpty())
			{
				for (Creature creature : creatureList.keySet())
				{
					float noise = creatureList.get(creature);
					if (noise >= creatureNoise)
						return creature;
				}
			}
		}
		return null;
	}

	public static final Foliage getFoliage(Period period, float foliageNoise)
	{
		if (period != null)
		{
			HashMap<Foliage, Float> foliageList = period.getFoliageList();

			if (foliageList != null && !foliageList.isEmpty())
			{
				for (Foliage foliage : foliageList.keySet())
				{
					float noise = foliageList.get(foliage);
					if (noise >= foliageNoise)
						return foliage;
				}
			}
		}
		return null;
	}
}
