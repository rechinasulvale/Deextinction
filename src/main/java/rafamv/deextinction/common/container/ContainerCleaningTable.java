package rafamv.deextinction.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.item.ItemRockPick;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.tileentity.TileCleaningTable;

public class ContainerCleaningTable extends Container
{
	private TileCleaningTable cleaning_table;
	private int prev_cleaning_progress;

	public ContainerCleaningTable(InventoryPlayer playerInventory, TileCleaningTable cleaning_table)
	{
		this.cleaning_table = cleaning_table;

		this.addSlotToContainer(new SlotSwitchable(this.cleaning_table, 0, 22, 35)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && stack.getItem() instanceof ItemRockPick;
			}
		});

		this.addSlotToContainer(new SlotSwitchable(this.cleaning_table, 1, 48, 35)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && stack.getUnlocalizedName().equals(DEBlockRegistry.plaster_jacket.getUnlocalizedName());
			}
		});

		for (int x = 0; x < 3; x++)
			for (int y = 0; y < 3; y++)
				this.addSlotToContainer(new SlotSwitchable(this.cleaning_table, 2 + x + y * 3, 102 + x * 18, 17 + y * 18)
				{
					@Override
					public boolean isItemValid(ItemStack stack)
					{
						return false;
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
		return this.cleaning_table.isUseableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);
		if (!playerIn.worldObj.isRemote)
			this.cleaning_table.closeInventory(playerIn);
	}

	@Override
	public void addCraftingToCrafters(ICrafting listener)
	{
		super.addCraftingToCrafters(listener);
		listener.sendProgressBarUpdate(this, 0, this.cleaning_table.getCleaningProgress());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting listener = (ICrafting) this.crafters.get(i);
			if (this.prev_cleaning_progress != this.cleaning_table.getCleaningProgress())
				listener.sendProgressBarUpdate(this, 0, this.cleaning_table.getCleaningProgress());
		}
		this.prev_cleaning_progress = this.cleaning_table.getCleaningProgress();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		if (id == 0)
			this.cleaning_table.setCleaningProgress(data);
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
				if (index < this.cleaning_table.getSizeInventory())
				{
					if (!mergeItemStack(stackInSlot, 9, this.inventorySlots.size(), true))
						return null;
					slot.onSlotChange(stackInSlot, itemStackCopy);
				}
				else if (index >= this.cleaning_table.getSizeInventory())
				{
					if (stackInSlot.getItem() instanceof ItemRockPick)
					{
						if (!mergeItemStack(stackInSlot, 0, 1, false))
							return null;
					}
					else if (stackInSlot.getUnlocalizedName().equals(DEBlockRegistry.plaster_jacket.getUnlocalizedName()))
					{
						if (!mergeItemStack(stackInSlot, 1, 2, false))
							return null;
					}
					else
						return null;
				}
				if (stackInSlot.stackSize == 0)
					slot.putStack((ItemStack) null);
				else
					slot.onSlotChanged();
				return itemStackCopy;
			}
		}
		return null;
	}
}
