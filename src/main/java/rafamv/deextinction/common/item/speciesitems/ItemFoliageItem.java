package rafamv.deextinction.common.item.speciesitems;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemFoliageItem extends Item
{

	public ItemFoliageItem(String foliage_name, int metadata)
	{
		super();
		this.setUnlocalizedName("item_" + foliage_name + "_species_" + metadata);
		this.setCreativeTab(DECreativeTabs.fossils);
	}
}
