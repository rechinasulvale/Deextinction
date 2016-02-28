package rafamv.deextinction.common.item;

import net.minecraft.item.Item;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemEggShell extends Item
{

	public ItemEggShell()
	{
		super();
		this.setUnlocalizedName("item_eggshell");
		this.setMaxStackSize(16);
		this.setCreativeTab(DECreativeTabs.items);
	}
}
