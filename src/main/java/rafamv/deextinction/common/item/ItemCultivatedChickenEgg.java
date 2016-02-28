package rafamv.deextinction.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.EntityCultivatedChickenEgg;

public class ItemCultivatedChickenEgg extends ItemEgg
{

	public ItemCultivatedChickenEgg()
	{
		super();
		this.setUnlocalizedName("item_cultivated_chicken_egg");
		this.setMaxStackSize(16);
	}

	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if (!playerIn.capabilities.isCreativeMode)
			itemStackIn.stackSize--;

		worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!worldIn.isRemote)
			worldIn.spawnEntityInWorld(new EntityCultivatedChickenEgg(worldIn, playerIn));

		return itemStackIn;
	}
}
