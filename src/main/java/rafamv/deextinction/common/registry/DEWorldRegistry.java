package rafamv.deextinction.common.registry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import rafamv.deextinction.common.world.DEWorldGen;

public class DEWorldRegistry
{

	public static void preInitCommon()
	{

	}

	public static void initCommon()
	{
		GameRegistry.registerWorldGenerator(new DEWorldGen(), 0);
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
}
