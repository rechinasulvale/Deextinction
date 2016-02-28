package rafamv.deextinction.common.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class DESmeltingEvent
{

	@SubscribeEvent
	public void whenSmelted(PlayerEvent.ItemSmeltedEvent event)
	{
		if (event.smelting.getItem().equals(DEItemRegistry.plaster_powder))
			;
	}
}
