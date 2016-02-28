package rafamv.deextinction.common.item;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemRockPick extends Item
{

	public ItemRockPick()
	{
		super();
		this.setUnlocalizedName("item_rock_pick");
		this.setMaxStackSize(1);
		this.setMaxDamage(64);
		this.setCreativeTab(DECreativeTabs.items);
	}
}
