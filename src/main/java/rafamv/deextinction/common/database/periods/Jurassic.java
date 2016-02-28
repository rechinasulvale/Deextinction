package rafamv.deextinction.common.database.periods;

import rafamv.deextinction.common.database.Period;

public class Jurassic extends Period
{
	public static final String NAME = "jurassic";

	public Jurassic()
	{
		super(Jurassic.NAME);
	}

	@Override
	public float getSpawnWeight()
	{
		return 0.4F;
	}
}
