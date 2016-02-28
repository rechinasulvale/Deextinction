package rafamv.deextinction.common.registry;

import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.message.MessageAnimation;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationCancelSapling;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationCreateSapling;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationFoliageName;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationGuiBranch;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationGuiPage;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationShouldResearch;
import rafamv.deextinction.common.message.MessageBotanicalResearchStationVariant;
import rafamv.deextinction.common.message.MessageCleaningTable;
import rafamv.deextinction.common.message.MessageEggNest;
import rafamv.deextinction.common.message.MessageFlyingEntity;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCancelEmbryo;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCreateEmbryo;
import rafamv.deextinction.common.message.MessageGeneticResearchStationCreatureName;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGenderAndTexture;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGuiBranch;
import rafamv.deextinction.common.message.MessageGeneticResearchStationGuiPage;
import rafamv.deextinction.common.message.MessageGeneticResearchStationShouldResearch;

public class DEMessageRegistry
{
	public static SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(DeExtinction.MODID);
	private static int NEXT_ID = 0;

	public static void preInitCommon()
	{
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationGuiPage.class, MessageGeneticResearchStationGuiPage.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationGuiBranch.class, MessageGeneticResearchStationGuiBranch.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationShouldResearch.class, MessageGeneticResearchStationShouldResearch.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationCreatureName.class, MessageGeneticResearchStationCreatureName.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationGenderAndTexture.class, MessageGeneticResearchStationGenderAndTexture.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationCreateEmbryo.class, MessageGeneticResearchStationCreateEmbryo.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageGeneticResearchStationCancelEmbryo.class, MessageGeneticResearchStationCancelEmbryo.Handler.class);

		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationGuiPage.class, MessageBotanicalResearchStationGuiPage.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationGuiBranch.class, MessageBotanicalResearchStationGuiBranch.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationShouldResearch.class, MessageBotanicalResearchStationShouldResearch.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationFoliageName.class, MessageBotanicalResearchStationFoliageName.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationVariant.class, MessageBotanicalResearchStationVariant.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationCreateSapling.class, MessageBotanicalResearchStationCreateSapling.Handler.class);
		DEMessageRegistry.registerMessageOnServer(MessageBotanicalResearchStationCancelSapling.class, MessageBotanicalResearchStationCancelSapling.Handler.class);

		DEMessageRegistry.registerMessageOnServer(MessageCleaningTable.class, MessageCleaningTable.Handler.class);

		DEMessageRegistry.registerMessageOnClient(MessageEggNest.class, MessageEggNest.Handler.class);

		DEMessageRegistry.registerMessageOnClient(MessageFlyingEntity.class, MessageFlyingEntity.Handler.class);

		DEMessageRegistry.registerMessageOnClient(MessageAnimation.class, MessageAnimation.Handler.class);
	}

	public static void initCommon()
	{

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

	public static void registerMessageOnServer(Class iMessage, Class iMessageHandler)
	{
		DEMessageRegistry.wrapper.registerMessage(iMessageHandler, iMessage, DEMessageRegistry.NEXT_ID, Side.SERVER);
		DEMessageRegistry.NEXT_ID++;
	}

	public static void registerMessageOnClient(Class iMessage, Class iMessageHandler)
	{
		DEMessageRegistry.wrapper.registerMessage(iMessageHandler, iMessage, DEMessageRegistry.NEXT_ID, Side.CLIENT);
		DEMessageRegistry.NEXT_ID++;
	}

	public static void sendToAllTracking(IMessage message, Entity entity)
	{
		if (!entity.worldObj.isRemote)
		{
			Set<EntityPlayer> players = ((WorldServer) entity.worldObj).getEntityTracker().getTrackingPlayers(entity);

			for (EntityPlayer player : players)
				DEMessageRegistry.wrapper.sendTo(message, (EntityPlayerMP) player);
		}
		else
			DeExtinction.logger.warn("Massege: " + message + " attempted to send a message to other players from the client.", new Exception());
	}
}
