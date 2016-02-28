package rafamv.deextinction.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.tileentity.TileBotanicalResearchStation;

public class MessageBotanicalResearchStationShouldResearch implements IMessage
{
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int shouldResearch;
	private boolean messageIsValid;

	public MessageBotanicalResearchStationShouldResearch()
	{
		this.messageIsValid = false;
	}

	public MessageBotanicalResearchStationShouldResearch(int xCoord, int yCoord, int zCoord, boolean shouldResearch)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.shouldResearch = shouldResearch ? 1 : 0;
		this.messageIsValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			this.xCoord = ByteBufUtils.readVarInt(buf, 5);
			this.yCoord = ByteBufUtils.readVarInt(buf, 5);
			this.zCoord = ByteBufUtils.readVarInt(buf, 5);
			this.shouldResearch = ByteBufUtils.readVarInt(buf, 5);
		}
		catch (IndexOutOfBoundsException ioe)
		{
			DeExtinction.logger.error("Exception while reading MessageBotanicalResearchStationShouldResearch: " + ioe);
			return;
		}
		this.messageIsValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!this.messageIsValid)
			return;
		ByteBufUtils.writeVarInt(buf, this.xCoord, 5);
		ByteBufUtils.writeVarInt(buf, this.yCoord, 5);
		ByteBufUtils.writeVarInt(buf, this.zCoord, 5);
		ByteBufUtils.writeVarInt(buf, this.shouldResearch, 5);
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public static class Handler implements IMessageHandler<MessageBotanicalResearchStationShouldResearch, IMessage>
	{
		@Override
		public IMessage onMessage(final MessageBotanicalResearchStationShouldResearch message, MessageContext ctx)
		{
			if (ctx.side != Side.SERVER)
			{
				DeExtinction.logger.error("MessageBotanicalResearchStationShouldResearch received on wrong side: " + ctx.side);
				return null;
			}
			if (!message.isMessageValid())
			{
				DeExtinction.logger.error("MessageBotanicalResearchStationShouldResearch was invalid " + message.toString());
				return null;
			}

			final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
			if (sendingPlayer == null)
			{
				DeExtinction.logger.error("EntityPlayerMP was null when MessageBotanicalResearchStationShouldResearch was received.");
				return null;
			}

			final WorldServer playerWorldServer = sendingPlayer.getServerForPlayer();
			playerWorldServer.addScheduledTask(new Runnable()
			{
				@Override
				public void run()
				{
					this.processMessage(message, sendingPlayer);
				}

				private void processMessage(MessageBotanicalResearchStationShouldResearch message, EntityPlayerMP sendingPlayer)
				{
					TileEntity tileEntity = sendingPlayer.worldObj.getTileEntity(new BlockPos(message.xCoord, message.yCoord, message.zCoord));
					if (tileEntity != null)
					{
						if (tileEntity instanceof TileBotanicalResearchStation)
						{
							TileBotanicalResearchStation researchStation = (TileBotanicalResearchStation) tileEntity;
							researchStation.setShouldResearch(message.shouldResearch == 1);
						}
						else
							DeExtinction.logger.error("Wrong instance of tile entity when MessageBotanicalResearchStationShouldResearch was received.");
					}
					else
						DeExtinction.logger.error("Null instance of tile entity when MessageBotanicalResearchStationShouldResearch was received.");
				}
			});
			return null;
		}
	}
}
