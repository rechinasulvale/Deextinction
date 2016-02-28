package rafamv.deextinction.common.container;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.item.speciesitems.ItemCreatureItem;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.tileentity.TileGeneticResearchStation;

public class ContainerGeneticResearchStation extends Container
{
	private TileGeneticResearchStation research_station;
	private int prev_research_progress;
	private int prev_creating_progress;
	private int prev_nutrients;

	public ContainerGeneticResearchStation(InventoryPlayer playerInventory, TileGeneticResearchStation research_station)
	{
		this.research_station = research_station;

		this.addSlotToContainer(new SlotSwitchable(this.research_station, 0, 8, 28)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && ContainerGeneticResearchStation.canPlaceFossil(stack);
			}
		});

		this.addSlotToContainer(new SlotSwitchable(this.research_station, 1, 8, 28)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && ContainerGeneticResearchStation.canPlaceNutrient(stack);
			}
		});

		this.addSlotToContainer(new SlotSwitchable(this.research_station, 2, 25, 54)
		{
			@Override
			public boolean isItemValid(ItemStack stack)
			{
				return super.isItemValid(stack) && ContainerGeneticResearchStation.canPlaceRequiredItem(stack);
			}
		});

		this.addSlotToContainer(new SlotSwitchable(this.research_station, 3, 8, 28));

		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 9; k++)
				this.addSlotToContainer(new SlotSwitchable(playerInventory, k + i * 9 + 9, 8 + k * 18, 174 + i * 18));

		for (int i = 0; i < 9; i++)
			this.addSlotToContainer(new SlotSwitchable(playerInventory, i, 8 + i * 18, 232));
	}

	public static boolean canPlaceFossil(ItemStack itemStack)
	{
		if (itemStack != null)
		{
			if (itemStack.getItem() instanceof ItemCreatureItem)
				return true;

			for (Creature creature : DEDatabaseRegistry.LIST_ALL_CREATURES.values())
			{
				Set<ItemStack> fossils = creature.getFossilizedItems().keySet();
				for (ItemStack fossilItemStack : fossils)
				{
					if (fossilItemStack != null && itemStack.getItem().equals(fossilItemStack.getItem()))
						return true;
				}
			}
		}
		return false;
	}

	public static boolean canPlaceNutrient(ItemStack itemStack)
	{
		if (itemStack != null && DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST != null && !DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.isEmpty())
			return DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.containsKey(itemStack.getUnlocalizedName());
		return false;
	}

	public static boolean canPlaceRequiredItem(ItemStack itemStack)
	{
		if (itemStack != null)
		{
			for (Creature creature : DEDatabaseRegistry.LIST_ALL_CREATURES.values())
			{
				ItemStack containerItemStack = creature.getEmbryoContainerItem();
				if (containerItemStack != null && itemStack.getItem().equals(containerItemStack.getItem()))
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.research_station.isUseableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);
		if (!playerIn.worldObj.isRemote)
			this.research_station.closeInventory(playerIn);
	}

	@Override
	public void addCraftingToCrafters(ICrafting listener)
	{
		super.addCraftingToCrafters(listener);
		listener.sendProgressBarUpdate(this, 0, this.research_station.getResearchProgress());
		listener.sendProgressBarUpdate(this, 1, this.research_station.getCreatingProgress());
		listener.sendProgressBarUpdate(this, 2, this.research_station.getNutrients());
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++)
		{
			ICrafting listener = (ICrafting) this.crafters.get(i);

			if (this.prev_research_progress != this.research_station.getResearchProgress())
				listener.sendProgressBarUpdate(this, 0, this.research_station.getResearchProgress());

			if (this.prev_creating_progress != this.research_station.getCreatingProgress())
				listener.sendProgressBarUpdate(this, 1, this.research_station.getCreatingProgress());

			if (this.prev_nutrients != this.research_station.getNutrients())
				listener.sendProgressBarUpdate(this, 2, this.research_station.getNutrients());

		}
		this.prev_research_progress = this.research_station.getResearchProgress();
		this.prev_creating_progress = this.research_station.getCreatingProgress();
		this.prev_nutrients = this.research_station.getNutrients();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data)
	{
		switch (id)
		{
			case 0:
				this.research_station.setResearchProgress(data);
				break;
			case 1:
				this.research_station.setCreatingProgress(data);
				break;
			case 2:
				this.research_station.setNutrients(data);
				break;
		}
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
				if (index < this.research_station.getSizeInventory())
				{
					if (!mergeItemStack(stackInSlot, 9, this.inventorySlots.size(), true))
						return null;
					slot.onSlotChange(stackInSlot, itemStackCopy);
				}
				else if (index >= this.research_station.getSizeInventory())
				{
					if (this.research_station.getGuiPage() == 2)
					{
						if (ContainerGeneticResearchStation.canPlaceFossil(stackInSlot))
						{
							if (!mergeItemStack(stackInSlot, 0, 1, false))
								return null;
						}
						else
							return null;
					}
					else if (this.research_station.getGuiPage() == 3)
					{
						if (ContainerGeneticResearchStation.canPlaceNutrient(stackInSlot))
						{
							if (!mergeItemStack(stackInSlot, 1, 2, false))
								return null;
						}
						else if (ContainerGeneticResearchStation.canPlaceRequiredItem(stackInSlot))
						{
							if (!mergeItemStack(stackInSlot, 2, 3, false))
								return null;
						}
						else
							return null;
					}
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
