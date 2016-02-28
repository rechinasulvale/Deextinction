package rafamv.deextinction.common.item.speciesitems;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemCreatureItem extends Item
{

	public ItemCreatureItem(String creature_name, int metadata)
	{
		super();
		this.setUnlocalizedName("item_" + creature_name + "_species_" + metadata);
		this.setCreativeTab(DECreativeTabs.fossils);
	}
}
