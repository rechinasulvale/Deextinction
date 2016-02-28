package rafamv.deextinction.common.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedFlyingCreatureExperiment;

public class MessageFlyingEntity implements IMessage
{
	private int entityID;
	private float entityYaw;
	private Vec3 position;
	private Vec3 velocity;
	private Vec3 targetLocation;
	private boolean messageIsValid;

	public MessageFlyingEntity()
	{
		this.messageIsValid = false;
	}

	public MessageFlyingEntity(int entityID, Vec3 position, Vec3 velocity, float entityYaw, Vec3 targetLocation)
	{
		this.entityID = entityID;
		this.position = position;
		this.entityYaw = entityYaw;
		this.velocity = velocity;
		this.targetLocation = targetLocation;
		this.messageIsValid = true;
	}

	public MessageFlyingEntity(EntityDeExtinctedFlyingCreatureExperiment entity)
	{
		this(entity.getEntityId(), entity.getPositionVector(), new Vec3(entity.motionX, entity.motionY, entity.motionZ), entity.rotationYaw, entity.getTargetLocation());
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			this.entityID = buf.readInt();

			this.entityYaw = buf.readFloat();

			if (buf.readBoolean())
				this.position = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());

			if (buf.readBoolean())
				this.velocity = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());

			if (buf.readBoolean())
				this.targetLocation = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
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

		buf.writeInt(entityID);

		buf.writeFloat(entityYaw);

		boolean has = position != null;
		buf.writeBoolean(has);

		if (has)
		{
			buf.writeDouble(position.xCoord);
			buf.writeDouble(position.yCoord);
			buf.writeDouble(position.zCoord);
		}

		has = velocity != null;
		buf.writeBoolean(has);

		if (has)
		{
			buf.writeDouble(velocity.xCoord);
			buf.writeDouble(velocity.yCoord);
			buf.writeDouble(velocity.zCoord);
		}

		has = targetLocation != null;
		buf.writeBoolean(has);

		if (has)
		{
			buf.writeDouble(targetLocation.xCoord);
			buf.writeDouble(targetLocation.yCoord);
			buf.writeDouble(targetLocation.zCoord);
		}
	}

	public boolean isMessageValid()
	{
		return this.messageIsValid;
	}

	public static class Handler implements IMessageHandler<MessageFlyingEntity, IMessage>
	{
		@Override
		public IMessage onMessage(final MessageFlyingEntity message, MessageContext ctx)
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

				private void processMessage(MessageFlyingEntity message)
				{
					World world = DeExtinction.proxy.getWorldClient();
					if (world != null)
					{
						Entity entity = world.getEntityByID(message.entityID);
						if (entity != null)
						{
							if (entity instanceof EntityDeExtinctedFlyingCreatureExperiment)
							{
								EntityDeExtinctedFlyingCreatureExperiment flyingCreature = (EntityDeExtinctedFlyingCreatureExperiment) entity;

								Vec3 position = message.position;
								if (position != null)
								{
									flyingCreature.setPositionAndRotationWithoutPitch(position.xCoord, position.yCoord, position.zCoord, message.entityYaw, flyingCreature.rotationPitch, 2, false);
									flyingCreature.serverPosX = (int) (position.xCoord * 32);
									flyingCreature.serverPosY = (int) (position.yCoord * 32);
									flyingCreature.serverPosZ = (int) (position.zCoord * 32);
								}

								Vec3 velocity = message.velocity;
								if (velocity != null)
									flyingCreature.setVelocity(velocity.xCoord, velocity.yCoord, velocity.zCoord);

								flyingCreature.setTargetLocation(message.targetLocation);
							}
							else
								DeExtinction.logger.error("Wrong instance of entity when MessageFlyingEntity was received. THIS IS A BUG!");
						}
						else
							DeExtinction.logger.error("Null instance of entity when MessageFlyingEntity was received. THIS IS A BUG!");
					}
					else
						DeExtinction.logger.error("Null world when MessageFlyingEntity was received. THIS IS A BUG!");
				}
			});
			return null;
		}
	}
}
