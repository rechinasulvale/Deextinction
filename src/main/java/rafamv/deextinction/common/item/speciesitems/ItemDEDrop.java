package rafamv.deextinction.common.item.speciesitems;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemDEDrop extends Item
{

	public ItemDEDrop(String creature_name, String itemName)
	{
		super();
		this.setUnlocalizedName("item_" + creature_name + "_" + itemName);
		this.setCreativeTab(DECreativeTabs.drops);
	}
}
