package rafamv.deextinction.common.database.periods;

import rafamv.deextinction.common.database.Period;

public class Cretaceous extends Period
{
	public static final String NAME = "cretaceous";

	public Cretaceous()
	{
		super(Cretaceous.NAME);
	}

	@Override
	public float getSpawnWeight()
	{
		return 2.5F;
	}
}
