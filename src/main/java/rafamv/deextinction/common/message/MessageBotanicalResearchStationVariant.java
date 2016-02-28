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

public class MessageBotanicalResearchStationVariant implements IMessage
{
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private int variant;
	private boolean messageIsValid;

	public MessageBotanicalResearchStationVariant()
	{
		this.messageIsValid = false;
	}

	public MessageBotanicalResearchStationVariant(int xCoord, int yCoord, int zCoord, int variant)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
		this.variant = variant;
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
			this.variant = ByteBufUtils.readVarInt(buf, 5);
		}
		catch (IndexOutOfBoundsException ioe)
		{
			DeExtinction.logger.error("Exception while reading MessageBotanicalResearchStationVariant: " + ioe);
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
		ByteBufUtils.writeVarInt(buf, this.variant, 5);
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public static class Handler implements IMessageHandler<MessageBotanicalResearchStationVariant, IMessage>
	{
		@Override
		public IMessage onMessage(final MessageBotanicalResearchStationVariant message, MessageContext ctx)
		{
			if (ctx.side != Side.SERVER)
			{
				DeExtinction.logger.error("MessageBotanicalResearchStationVariant received on wrong side: " + ctx.side);
				return null;
			}
			if (!message.isMessageValid())
			{
				DeExtinction.logger.error("MessageBotanicalResearchStationVariant was invalid " + message.toString());
				return null;
			}

			final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
			if (sendingPlayer == null)
			{
				DeExtinction.logger.error("EntityPlayerMP was null when MessageBotanicalResearchStationVariant was received.");
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

				private void processMessage(MessageBotanicalResearchStationVariant message, EntityPlayerMP sendingPlayer)
				{
					TileEntity tileEntity = sendingPlayer.worldObj.getTileEntity(new BlockPos(message.xCoord, message.yCoord, message.zCoord));
					if (tileEntity != null)
					{
						if (tileEntity instanceof TileBotanicalResearchStation)
						{
							TileBotanicalResearchStation researchStation = (TileBotanicalResearchStation) tileEntity;
							researchStation.setFoliageVariantID((byte) message.variant);
						}
						else
							DeExtinction.logger.error("Wrong instance of tile entity when MessageBotanicalResearchStationVariant was received.");
					}
					else
						DeExtinction.logger.error("Null instance of tile entity when MessageBotanicalResearchStationVariant was received.");
				}
			});
			return null;
		}
	}
}
