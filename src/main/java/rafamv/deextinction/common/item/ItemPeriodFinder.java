package rafamv.deextinction.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Period;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.world.DEFossilHelper;

public class ItemPeriodFinder extends Item
{

	public ItemPeriodFinder()
	{
		super();
		this.setUnlocalizedName("item_period_finder");
		this.setCreativeTab(DECreativeTabs.items);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
			return true;
		else
		{
			float periodNoise = DEFossilHelper.getNoise2DForPeriods(pos.getX(), pos.getZ());
			Period period = DEFossilHelper.getPeriod(periodNoise);
			if (period != null)
			{
				if (playerIn.capabilities.isCreativeMode)
				{
					float fossilNoise = DEFossilHelper.getNoise2DForFossils(pos.getX(), pos.getZ());
					playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("period.name") + ": " + period.getPeriodName()));

					Creature creature = DEFossilHelper.getCreature(period, fossilNoise);
					if (creature != null)
						playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("creature.name") + ": " + creature.getDisplayName()));

					Foliage foliage = DEFossilHelper.getFoliage(period, fossilNoise);
					if (foliage != null)
						playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("foliage.name") + ": " + foliage.getDisplayName()));
				}
				else
					playerIn.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("period.name") + ": " + period.getPeriodName()));
				return true;
			}
		}
		return true;
	}
}
