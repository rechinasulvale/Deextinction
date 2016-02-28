package rafamv.deextinction.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;

public class MessageAnimation implements IMessage
{

	private byte animID;
	private int entityID;

	public MessageAnimation()
	{
	}

	public MessageAnimation(byte anim, int entity)
	{
		animID = anim;
		entityID = entity;
	}

	@Override
	public void toBytes(ByteBuf buffer)
	{
		buffer.writeByte(animID);
		buffer.writeInt(entityID);
	}

	@Override
	public void fromBytes(ByteBuf buffer)
	{
		animID = buffer.readByte();
		entityID = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<MessageAnimation, IMessage>
	{
		@Override
		public IMessage onMessage(MessageAnimation packet, MessageContext ctx)
		{
			World world = DeExtinction.proxy.getWorldClient();
			IAnimatedEntity entity = (IAnimatedEntity) world.getEntityByID(packet.entityID);
			if (entity != null && packet.animID != -1)
			{
				entity.setAnimID(packet.animID);
				if (packet.animID == 0)
					entity.setAnimTick(0);
			}
			return null;
		}
	}
}
