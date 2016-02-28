package rafamv.deextinction;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.Logger;

import rafamv.deextinction.common.entity.base.IAnimatedEntity;
import rafamv.deextinction.common.events.DECraftingEvent;
import rafamv.deextinction.common.events.DEFillBucketEvent;
import rafamv.deextinction.common.events.DELivingEvent;
import rafamv.deextinction.common.events.DEPickupEvent;
import rafamv.deextinction.common.message.MessageAnimation;
import rafamv.deextinction.common.proxy.CommonProxy;
import rafamv.deextinction.common.registry.DEGuiHandler;
import rafamv.deextinction.common.registry.DEMessageRegistry;

@Mod(modid = DeExtinction.MODID, name = DeExtinction.MODNAME, version = DeExtinction.VERSION)
public class DeExtinction
{
	public static final String MODID = "deextinction";
	public static final String MODNAME = "DeExtinction Mod";
	public static final String VERSION = "Beta version";

	@Instance(DeExtinction.MODID)
	public static DeExtinction instance;

	@SidedProxy(clientSide = "rafamv.deextinction.common.proxy.ClientProxy", serverSide = "rafamv.deextinction.common.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static Logger logger;

	public static final String[] fTimer;

	static
	{
		fTimer = new String[] { "field_71428_T", "S", "timer" };
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		DeExtinction.logger = event.getModLog();
		DeExtinction.logger.info("Loading " + DeExtinction.class.getSimpleName() + "...");
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new DEGuiHandler());

		FMLCommonHandler.instance().bus().register(new DECraftingEvent());
		FMLCommonHandler.instance().bus().register(new DEPickupEvent());

		MinecraftForge.EVENT_BUS.register(new DEFillBucketEvent());
		MinecraftForge.EVENT_BUS.register(new DELivingEvent());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
		DeExtinction.logger.info("Successfully loaded " + DeExtinction.class.getSimpleName() + "!");
	}

	public static boolean isClient()
	{
		return FMLCommonHandler.instance().getSide().isClient();
	}

	public static boolean isEffectiveClient()
	{
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public static void sendAnimPacket(IAnimatedEntity entity, int animID)
	{
		if (isEffectiveClient())
			return;
		entity.setAnimID(animID);
		DEMessageRegistry.wrapper.sendToAll(new MessageAnimation((byte) animID, ((Entity) entity).getEntityId()));
	}

	/**
	 * Prepends the mod id to a string.
	 *
	 * @param name
	 * 
	 * @return "MODID:name"
	 */
	public static String prependModID(String name)
	{
		return DeExtinction.MODID + ":" + name;
	}

	/**
	 * Prepends the mod id and folder to a string.
	 *
	 * @param folder
	 * @param name
	 * 
	 * @return "MODID:folder/name"
	 */
	public static String prependModIDandFolder(String folder, String name)
	{
		return DeExtinction.MODID + ":" + folder + "/" + name;
	}
}
