package rafamv.deextinction.common.events;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import rafamv.deextinction.common.registry.DEAchiementRegistry;
import rafamv.deextinction.common.registry.DEBlockRegistry;

public class DEPickupEvent
{

	@SubscribeEvent
	public void whenPickup(PlayerEvent.ItemPickupEvent event)
	{
		if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(DEBlockRegistry.plaster_jacket)))
			event.player.addStat(DEAchiementRegistry.plaster_jacket, 1);
	}
}
