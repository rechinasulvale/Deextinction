package rafamv.deextinction.common.item.speciesitems;

import net.minecraft.item.ItemFood;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemDEFood extends ItemFood
{

	public ItemDEFood(String creature_name, boolean isCooked, int hungerAmount, float saturation, boolean isWolfFood)
	{
		super(hungerAmount, saturation, isWolfFood);
		this.setUnlocalizedName("item_" + creature_name + (isCooked ? "_cooked" : "_raw"));
		this.setCreativeTab(DECreativeTabs.drops);
	}
}
