package rafamv.deextinction.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rafamv.deextinction.common.registry.DEAchiementRegistry;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEChestRegistry;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEEntityRegistry;
import rafamv.deextinction.common.registry.DEFluidRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;
import rafamv.deextinction.common.registry.DEMessageRegistry;
import rafamv.deextinction.common.registry.DERecipeRegistry;
import rafamv.deextinction.common.registry.DEWorldRegistry;

public abstract class CommonProxy
{
	public void preInit()
	{
		DEDatabaseRegistry.preInitCommon();
		DEEntityRegistry.preInitCommon();
		DEFluidRegistry.preInitCommon();
		DEBlockRegistry.preInitCommon();
		DEItemRegistry.preInitCommon();
		DERecipeRegistry.preInitCommon();
		DEMessageRegistry.preInitCommon();
		DEWorldRegistry.preInitCommon();
		DEChestRegistry.preInitCommon();
		DEAchiementRegistry.preInitCommon();
	}

	public void init()
	{
		DEDatabaseRegistry.initCommon();
		DEEntityRegistry.initCommon();
		DEFluidRegistry.initCommon();
		DEBlockRegistry.initCommon();
		DEItemRegistry.initCommon();
		DERecipeRegistry.initCommon();
		DEMessageRegistry.initCommon();
		DEWorldRegistry.initCommon();
		DEChestRegistry.initCommon();
		DEAchiementRegistry.initCommon();
	}

	public void postInit()
	{
		DEDatabaseRegistry.postInitCommon();
		DEEntityRegistry.postInitCommon();
		DEFluidRegistry.postInitCommon();
		DEBlockRegistry.postInitCommon();
		DEItemRegistry.postInitCommon();
		DERecipeRegistry.postInitCommon();
		DEMessageRegistry.postInitCommon();
		DEWorldRegistry.postInitCommon();
		DEChestRegistry.postInitCommon();
		DEAchiementRegistry.postInitCommon();
		initTimer();
	}

	public void initTimer()
	{

	}

	public float getPartialTick()
	{
		return 1.0F;
	}

	public World getWorldClient()
	{
		return null;
	}

	abstract public boolean playerIsInCreativeMode(EntityPlayer player);
}
