package rafamv.deextinction.common.item;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemPlasterPowder extends Item
{

	public ItemPlasterPowder()
	{
		super();
		this.setUnlocalizedName("item_plaster_powder");
		this.setCreativeTab(DECreativeTabs.items);
	}
}
