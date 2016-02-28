package rafamv.deextinction.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.entity.eggnests.EntityEggNest;

public class MessageEggNest implements IMessage
{
	private int slot;
	private int entityID;
	private String creatureName;
	private boolean messageIsValid;

	public MessageEggNest()
	{
		this.messageIsValid = false;
	}

	public MessageEggNest(int entityID, int slot, String creatureName)
	{
		this.slot = slot;
		this.entityID = entityID;
		this.creatureName = creatureName;
		this.messageIsValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			this.slot = ByteBufUtils.readVarInt(buf, 5);
			this.entityID = ByteBufUtils.readVarInt(buf, 5);
			this.creatureName = ByteBufUtils.readUTF8String(buf);
		}
		catch (IndexOutOfBoundsException ioe)
		{
			DeExtinction.logger.error("Exception while reading MessageGeneticResearchStationCreatureName: " + ioe);
			return;
		}
		this.messageIsValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!this.messageIsValid)
			return;
		ByteBufUtils.writeVarInt(buf, this.slot, 5);
		ByteBufUtils.writeVarInt(buf, this.entityID, 5);
		ByteBufUtils.writeUTF8String(buf, this.creatureName);
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public static class Handler implements IMessageHandler<MessageEggNest, IMessage>
	{
		@Override
		public IMessage onMessage(final MessageEggNest message, MessageContext ctx)
		{
			if (ctx.side != Side.CLIENT)
			{
				DeExtinction.logger.error("MessageGeneticResearchStationCreatureName received on wrong side: " + ctx.side);
				return null;
			}
			if (!message.isMessageValid())
			{
				DeExtinction.logger.error("MessageGeneticResearchStationCreatureName was invalid " + message.toString());
				return null;
			}

			final Minecraft minecraft = Minecraft.getMinecraft();
			minecraft.addScheduledTask(new Runnable()
			{
				@Override
				public void run()
				{
					this.processMessage(message);
				}

				private void processMessage(MessageEggNest message)
				{
					World world = DeExtinction.proxy.getWorldClient();
					if (world != null)
					{
						Entity entity = world.getEntityByID(message.entityID);
						if (entity != null)
						{
							if (entity instanceof EntityEggNest)
							{
								EntityEggNest entityEggNest = (EntityEggNest) entity;
								entityEggNest.updateEggInClient(message.slot, message.creatureName);
							}
							else
								DeExtinction.logger.error("Wrong instance of entity when MessageEggNest was received.");
						}
						else
							DeExtinction.logger.error("Null instance of entity when MessageEggNest was received.");
					}
					else
						DeExtinction.logger.error("Null world when MessageEggNest was received. THIS IS A BUG!");
				}
			});
			return null;
		}
	}
}
