package rafamv.deextinction.common.database.periods;

import rafamv.deextinction.common.database.Period;

public class Neogene extends Period
{
	public static final String NAME = "neogene";

	public Neogene()
	{
		super(Neogene.NAME);
	}

	@Override
	public float getSpawnWeight()
	{
		return 0.7F;
	}
}
