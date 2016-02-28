package rafamv.deextinction.common.item.eggnests;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestMedium;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemEggNestMedium extends Item
{

	public ItemEggNestMedium()
	{
		super();
		this.setUnlocalizedName("item_egg_nest_medium");
		this.setMaxStackSize(16);
		this.setCreativeTab(DECreativeTabs.items);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		pos = pos.offset(side);
		if (worldIn.isAirBlock(pos))
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				stack.stackSize--;
				if (stack.stackSize < 1)
					stack = null;
			}

			Entity eggNest = new EntityEggNestMedium(worldIn);
			if (eggNest != null)
			{
				if (!worldIn.isRemote)
				{
					eggNest.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat() * 360.0F), 0.0F);
					worldIn.spawnEntityInWorld(eggNest);
				}
				worldIn.playSoundAtEntity(eggNest, "dig.grass", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			}
		}
		return true;
	}
}
