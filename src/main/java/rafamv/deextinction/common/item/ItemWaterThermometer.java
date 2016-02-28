package rafamv.deextinction.common.item;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemWaterThermometer extends Item
{

	public ItemWaterThermometer()
	{
		super();
		this.setUnlocalizedName("item_water_thermometer");
		this.setCreativeTab(DECreativeTabs.items);
	}
}
