package rafamv.deextinction.common.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import rafamv.deextinction.DeExtinction;
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

public class ClientProxy extends CommonProxy
{
	private Timer mcTimer;

	@Override
	public void preInit()
	{
		super.preInit();
		DEDatabaseRegistry.preInitClientOnly();
		DEEntityRegistry.preInitClientOnly();
		DEFluidRegistry.preInitClientOnly();
		DEBlockRegistry.preInitClientOnly();
		DEItemRegistry.preInitClientOnly();
		DERecipeRegistry.preInitClientOnly();
		DEMessageRegistry.preInitClientOnly();
		DEWorldRegistry.preInitClientOnly();
		DEChestRegistry.preInitClientOnly();
		DEAchiementRegistry.preInitClientOnly();
	}

	@Override
	public void init()
	{
		super.init();
		DEDatabaseRegistry.initClientOnly();
		DEEntityRegistry.initClientOnly();
		DEFluidRegistry.initClientOnly();
		DEBlockRegistry.initClientOnly();
		DEItemRegistry.initClientOnly();
		DERecipeRegistry.initClientOnly();
		DEMessageRegistry.initClientOnly();
		DEWorldRegistry.initClientOnly();
		DEChestRegistry.initClientOnly();
		DEAchiementRegistry.initClientOnly();
	}

	@Override
	public void postInit()
	{
		super.postInit();
		DEDatabaseRegistry.postInitClientOnly();
		DEEntityRegistry.postInitClientOnly();
		DEFluidRegistry.postInitClientOnly();
		DEBlockRegistry.postInitClientOnly();
		DEItemRegistry.postInitClientOnly();
		DERecipeRegistry.postInitClientOnly();
		DEMessageRegistry.postInitClientOnly();
		DEWorldRegistry.postInitClientOnly();
		DEChestRegistry.postInitClientOnly();
		DEAchiementRegistry.postInitClientOnly();
	}

	@Override
	public void initTimer()
	{
		this.mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), DeExtinction.fTimer);
	}

	@Override
	public float getPartialTick()
	{
		return this.mcTimer.renderPartialTicks;
	}

	@Override
	public World getWorldClient()
	{
		return FMLClientHandler.instance().getWorldClient();
	}

	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player)
	{
		if (player instanceof EntityPlayerMP)
		{
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
			return entityPlayerMP.theItemInWorldManager.isCreative();
		}
		else if (player instanceof EntityPlayerSP)
		{
			return Minecraft.getMinecraft().playerController.isInCreativeMode();
		}
		return false;
	}
}
