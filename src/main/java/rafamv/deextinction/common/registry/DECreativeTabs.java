package rafamv.deextinction.common.registry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;

public class DECreativeTabs
{
	public static final CreativeTabs items = new CreativeTabs(DeExtinction.MODID + ".items")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			Item item = DEItemRegistry.rock_pick;
			return item != null ? item : Items.bone;
		}
	};

	public static final CreativeTabs blocks = new CreativeTabs(DeExtinction.MODID + ".blocks")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			Block block = DEBlockRegistry.gypsum_brick;
			return block != null ? Item.getItemFromBlock(block) : Item.getItemFromBlock(Blocks.stone);
		}
	};

	public static final CreativeTabs eggs = new CreativeTabs(DeExtinction.MODID + ".eggs")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			Item item = DEItemRegistry.sinovenator_egg;
			return item != null ? item : Items.egg;
		}
	};

	public static final CreativeTabs drops = new CreativeTabs(DeExtinction.MODID + ".drops")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			Item item = DEItemRegistry.sinovenator_feather;
			return item != null ? item : Items.beef;
		}
	};

	public static final CreativeTabs fossils = new CreativeTabs(DeExtinction.MODID + ".fossils")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			Item item = DEItemRegistry.sinovenator_fossilized_feather;
			return item != null ? item : Items.flint;
		}
	};
}
