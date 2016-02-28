package rafamv.deextinction.common.events;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import rafamv.deextinction.common.registry.DEAchiementRegistry;
import rafamv.deextinction.common.registry.DEBlockRegistry;

public class DECraftingEvent
{

	@SubscribeEvent
	public void whenCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem().equals(Item.getItemFromBlock(DEBlockRegistry.cleaning_table)))
			event.player.addStat(DEAchiementRegistry.cleaning_table, 1);

		if (event.crafting.getItem().equals(Item.getItemFromBlock(DEBlockRegistry.genetic_research_station)))
			event.player.addStat(DEAchiementRegistry.genetic_research_station, 1);
	}
}
