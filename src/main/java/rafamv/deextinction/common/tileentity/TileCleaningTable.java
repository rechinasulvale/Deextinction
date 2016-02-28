package rafamv.deextinction.common.tileentity;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import rafamv.deextinction.common.block.BlockPlasterJacket;
import rafamv.deextinction.common.container.ContainerCleaningTable;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.item.ItemRockPick;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class TileCleaningTable extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	public static final int[] SLOT_FOSSIL_ITEMS = new int[] { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	public static final int[] SLOT_ROCK_PICK = new int[] { 0 };
	public static final int[] SLOT_PLASTER_JACKET = new int[] { 1 };

	private ItemStack[] slots = new ItemStack[11];
	private String tile_custom_name;

	private static final int CLEANING_PROGRESS_MAX = 100;
	private int cleaningProgress = 0;
	private boolean shouldClean = false;

	public TileCleaningTable()
	{

	}

	public void setCleaningProgress(int cleaningProgress)
	{
		this.cleaningProgress = cleaningProgress;
	}

	public int getCleaningProgress()
	{
		return this.cleaningProgress;
	}

	public int getCleaningProgressScaled(int scale)
	{
		return scale * this.cleaningProgress / TileCleaningTable.CLEANING_PROGRESS_MAX;
	}

	public void setShouldClean(boolean flag)
	{
		this.shouldClean = flag;
	}

	public boolean shouldClean()
	{
		return this.shouldClean;
	}

	public boolean isCleaning()
	{
		return this.shouldClean && this.cleaningProgress > 0;
	}

	public boolean canConsumeFossil()
	{
		if (this.cleaningProgress >= TileCleaningTable.CLEANING_PROGRESS_MAX)
			return true;

		ItemStack rockPick = this.slots[TileCleaningTable.SLOT_ROCK_PICK[0]];
		if (rockPick != null && rockPick.getItem() instanceof ItemRockPick)
		{
			ItemStack plasterJacket = this.slots[TileCleaningTable.SLOT_PLASTER_JACKET[0]];
			if (plasterJacket != null && plasterJacket.getUnlocalizedName().equals(DEBlockRegistry.plaster_jacket.getUnlocalizedName()))
			{
				if (plasterJacket.hasTagCompound())
				{
					NBTTagCompound compound = plasterJacket.getTagCompound();
					if (compound != null && compound.hasKey("FossilName"))
					{
						for (int slot_number : TileCleaningTable.SLOT_FOSSIL_ITEMS)
						{
							if (this.slots[slot_number] == null)
							{
								this.cleaningProgress++;
								return false;
							}
						}
					}
				}
			}
		}
		this.cleaningProgress = 0;
		return false;
	}

	public void consumeFossil()
	{
		this.cleaningProgress = 0;

		ItemStack rock_pick = this.slots[TileCleaningTable.SLOT_ROCK_PICK[0]];
		if (rock_pick != null)
		{
			rock_pick.setItemDamage(rock_pick.getItemDamage() + 1);
			if (rock_pick.getItemDamage() >= rock_pick.getMaxDamage())
				this.slots[TileCleaningTable.SLOT_ROCK_PICK[0]] = null;
		}

		ItemStack plasterJacket = this.slots[TileCleaningTable.SLOT_PLASTER_JACKET[0]];
		if (plasterJacket != null && Block.getBlockFromItem(plasterJacket.getItem()) instanceof BlockPlasterJacket)
		{
			plasterJacket.stackSize--;
			if (plasterJacket.stackSize < 1)
				this.slots[TileCleaningTable.SLOT_PLASTER_JACKET[0]] = null;

			if (plasterJacket.hasTagCompound())
			{
				NBTTagCompound compound = plasterJacket.getTagCompound();
				if (compound.hasKey("FossilName"))
				{
					String fossilName = compound.getString("FossilName");

					Creature creature = DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(fossilName);
					if (creature != null)
					{
						HashMap<ItemStack, Integer> list_item_fossils = creature.getFossilizedItems();

						if (!list_item_fossils.isEmpty())
						{
							int randomItem = this.worldObj.rand.nextInt(list_item_fossils.size());
							int count = 0;
							ItemStack itemFossil = null;
							for (ItemStack item : list_item_fossils.keySet())
							{
								if (count == randomItem)
								{
									itemFossil = item;
									break;
								}
								count++;
							}

							if (itemFossil != null && itemFossil.getItem() != null)
							{
								for (int slot_number : TileCleaningTable.SLOT_FOSSIL_ITEMS)
									if (this.slots[slot_number] != null && this.slots[slot_number].getItem().equals(itemFossil.getItem()))
									{
										this.slots[slot_number].stackSize++;
										return;
									}

								for (int slot_number : TileCleaningTable.SLOT_FOSSIL_ITEMS)
									if (this.slots[slot_number] == null)
									{
										this.slots[slot_number] = itemFossil;
										return;
									}
							}
						}
					}

					Foliage foliage = DEDatabaseRegistry.LIST_DEEXTINCT_FOLIAGE.get(fossilName);
					if (foliage != null)
					{
						HashMap<ItemStack, Integer> list_item_fossils = foliage.getFossilizedItems();
						if (!list_item_fossils.isEmpty())
						{
							int randomItem = this.worldObj.rand.nextInt(list_item_fossils.size());
							int count = 0;
							ItemStack itemFossil = null;
							for (ItemStack item : list_item_fossils.keySet())
							{
								if (count == randomItem)
								{
									itemFossil = item;
									break;
								}
								count++;
							}

							if (itemFossil != null && itemFossil.getItem() != null)
							{
								for (int slot_number : TileCleaningTable.SLOT_FOSSIL_ITEMS)
									if (this.slots[slot_number] != null && this.slots[slot_number].getItem() == itemFossil.getItem() && this.slots[slot_number].getMetadata() == itemFossil.getMetadata())
									{
										this.slots[slot_number].stackSize++;
										return;
									}

								for (int slot_number : TileCleaningTable.SLOT_FOSSIL_ITEMS)
									if (this.slots[slot_number] == null)
									{
										this.slots[slot_number] = itemFossil;
										return;
									}
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.shouldClean)
			{
				if (this.canConsumeFossil())
					this.consumeFossil();
			}
			else
				this.cleaningProgress = 0;
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerCleaningTable(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
		return "deextinction:cleaning_table";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? TileCleaningTable.SLOT_FOSSIL_ITEMS : TileCleaningTable.SLOT_PLASTER_JACKET;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		return false;
	}

	@Override
	public int getSizeInventory()
	{
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.slots[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		if (this.slots[index] != null)
		{
			ItemStack itemstack;
			if (this.slots[index].stackSize <= count)
			{
				itemstack = this.slots[index];
				this.slots[index] = null;
				return itemstack;
			}
			else
			{
				itemstack = this.slots[index].splitStack(count);
				if (this.slots[index].stackSize == 0)
					this.slots[index] = null;
				return itemstack;
			}
		}
		else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index)
	{
		if (this.slots[index] != null)
		{
			ItemStack itemstack = this.slots[index];
			this.slots[index] = null;
			return itemstack;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		this.slots[index] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
			stack.stackSize = this.getInventoryStackLimit();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{

	}

	@Override
	public void closeInventory(EntityPlayer player)
	{

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return false;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for (int i = 0; i < this.slots.length; i++)
			this.slots[i] = null;
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.tile_custom_name : StatCollector.translateToLocal("container.cleaning_table.name");
	}

	@Override
	public boolean hasCustomName()
	{
		return this.tile_custom_name != null && this.tile_custom_name.length() > 0;
	}

	public void setCustomInventoryName(String name)
	{
		this.tile_custom_name = name;
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName());
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.slots.length; ++i)
		{
			if (this.slots[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName())
			compound.setString("CustomName", this.tile_custom_name);

		compound.setBoolean("ShouldClean", this.shouldClean);
		compound.setShort("CleaningProgress", (short) this.cleaningProgress);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 >= 0 && b0 < this.slots.length)
				this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}

		if (compound.hasKey("CustomName"))
			this.tile_custom_name = compound.getString("CustomName");

		if (compound.hasKey("ShouldClean"))
			this.shouldClean = compound.getBoolean("ShouldClean");

		if (compound.hasKey("CleaningProgress"))
			this.cleaningProgress = compound.getShort("CleaningProgress");
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return new S35PacketUpdateTileEntity(this.pos, this.getBlockMetadata(), compound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		NBTTagCompound compound = packet.getNbtCompound();
		this.readFromNBT(compound);
	}
}
