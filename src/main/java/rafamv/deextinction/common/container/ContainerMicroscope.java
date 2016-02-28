package rafamv.deextinction.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.item.ItemBloodFilledSyringe;
import rafamv.deextinction.common.tileentity.TileMicroscope;

public class ContainerMicroscope extends Container
{
	private TileMicroscope microscope;
	private int prev_analyzing_progress;

	public ContainerMicroscope(InventoryPlayer playerInventory, TileMicroscope cleaning_table)
	{
		this.microscope = cleaning_table;

		this.addSlotToContainer(new SlotSwitchable(this.microscope, 0, 12, 20)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && stack != null && stack.getItem() instanceof ItemBloodFilledSyringe;
			}
		});

		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				this.addSlotToContainer(new SlotSwitchable(playerInventory, k + i * 9 + 9, 8 + k * 18, 174 + i * 18));

		for (int i = 0; i < 9; i++)
			this.addSlotToContainer(new SlotSwitchable(playerInventory, i, 8 + i * 18, 232));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.microscope.isUseableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);
		if (!playerIn.worldObj.isRemote)
			this.microscope.closeInventory(playerIn);
	}

	@Override
	public void addCraftingToCrafters(ICrafting listener)
	{
		super.addCraftingToCrafters(listener);
		listener.sendProgressBarUpdate(this, 0, this.microscope.getAnalyzingProgress());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting listener = (ICrafting) this.crafters.get(i);
			if (this.prev_analyzing_progress != this.microscope.getAnalyzingProgress())
				listener.sendProgressBarUpdate(this, 0, this.microscope.getAnalyzingProgress());
		}
		this.prev_analyzing_progress = this.microscope.getAnalyzingProgress();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		if (id == 0)
			this.microscope.setAnalyzingProgress(data);
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
				if (index < this.microscope.getSizeInventory())
				{
					if (!mergeItemStack(stackInSlot, 9, this.inventorySlots.size(), true))
						return null;
					slot.onSlotChange(stackInSlot, itemStackCopy);
				}
				else if (index >= this.microscope.getSizeInventory())
				{
					if (stackInSlot != null && stackInSlot.getItem() instanceof ItemBloodFilledSyringe)
					{
						if (!mergeItemStack(stackInSlot, 0, 1, false))
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
