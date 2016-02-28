package rafamv.deextinction.common.container.eggnests;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestLarge;
import rafamv.deextinction.common.item.ItemWaterThermometer;
import rafamv.deextinction.common.item.eggs.ItemCreatureEggLarge;

public class ContainerEggNestLarge extends Container
{
	private EntityEggNestLarge eggNest;

	public ContainerEggNestLarge(InventoryPlayer playerInventory, IInventory eggNestInventory, EntityEggNestLarge eggNest)
	{
		this.eggNest = eggNest;

		this.addSlotToContainer(new Slot(eggNestInventory, 0, 72, 35)
		{
			@Override
			public boolean isItemValid(ItemStack itemStack)
			{
				return super.isItemValid(itemStack) && itemStack != null && itemStack.getItem() instanceof ItemCreatureEggLarge;
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}
		});

		this.addSlotToContainer(new Slot(eggNestInventory, 1, 50, 35)
		{
			@Override
			public boolean isItemValid(ItemStack itemStack)
			{
				return super.isItemValid(itemStack) && itemStack != null && itemStack.getItem() instanceof ItemCreatureEggLarge;
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}
		});

		this.addSlotToContainer(new Slot(eggNestInventory, 2, 117, 82)
		{
			@Override
			public boolean isItemValid(ItemStack itemStack)
			{
				return super.isItemValid(itemStack) && itemStack != null && itemStack.getItem() instanceof ItemWaterThermometer;
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}
		});

		this.addSlotToContainer(new Slot(eggNestInventory, 3, 61, 57)
		{
			@Override
			public boolean isItemValid(ItemStack itemStack)
			{
				return super.isItemValid(itemStack) && itemStack != null && itemStack.getItem() instanceof ItemLeaves;
			}

			@Override
			public int getSlotStackLimit()
			{
				return 1;
			}
		});

		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				this.addSlotToContainer(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 108 + i * 18));

		for (int i = 0; i < 9; i++)
			this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 166));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		if (!playerIn.worldObj.isRemote)
		{
			Slot slot = (Slot) this.inventorySlots.get(index);
			ItemStack itemStackCopy = null;
			if (slot != null && slot.getHasStack())
			{
				ItemStack stackInSlot = slot.getStack();
				itemStackCopy = stackInSlot.copy();
				if (index < this.eggNest.getSlotsNumber())
				{
					if (!this.mergeItemStack(stackInSlot, 9, this.inventorySlots.size(), true))
						return null;
					slot.onSlotChange(stackInSlot, itemStackCopy);
				}
				else if (index >= this.eggNest.getSlotsNumber())
				{
					if (stackInSlot.getItem() instanceof ItemCreatureEggLarge)
					{
						if (!this.mergeItemStack(stackInSlot, 0, 2, false))
							return null;
					}
					else if (stackInSlot.getItem() instanceof ItemWaterThermometer)
					{
						if (!this.mergeItemStack(stackInSlot, 2, 3, false))
							return null;
					}
					else
						return null;
				}
				if (stackInSlot.stackSize == 0)
					slot.putStack(null);
				else
					slot.onSlotChanged();
				return itemStackCopy;
			}
		}
		return null;
	}
}
