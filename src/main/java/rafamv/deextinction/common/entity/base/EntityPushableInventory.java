package rafamv.deextinction.common.entity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;

public abstract class EntityPushableInventory extends EntityPushable implements IInvBasic
{
	protected InventoryBasic inventoryBasic;

	public EntityPushableInventory(World worldIn, float xzSize, float ySize)
	{
		super(worldIn, xzSize, ySize);
		this.createInventory();
	}

	public void setInventoryBasic(InventoryBasic inventoryBasic)
	{
		this.inventoryBasic = inventoryBasic;
	}

	public InventoryBasic getInventoryBasic()
	{
		return inventoryBasic;
	}

	private void createInventory()
	{
		InventoryBasic entityInventory = this.inventoryBasic;
		this.inventoryBasic = new InventoryBasic("inventoryBasic", false, this.getSlotsNumber());
		this.inventoryBasic.setCustomName(this.getName());

		if (entityInventory != null)
		{
			entityInventory.func_110132_b(this);
			int i = Math.min(entityInventory.getSizeInventory(), this.inventoryBasic.getSizeInventory());
			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = entityInventory.getStackInSlot(j);
				if (itemstack != null)
					this.inventoryBasic.setInventorySlotContents(j, itemstack.copy());
			}
		}

		this.inventoryBasic.func_110134_a(this);
	}

	@Override
	public boolean interactFirst(EntityPlayer playerIn)
	{
		if (!this.worldObj.isRemote)
			this.openGUI(playerIn);
		return true;
	}

	public void openGUI(EntityPlayer playerIn)
	{
		if (!this.worldObj.isRemote && this.riddenByEntity == null)
		{
			this.inventoryBasic.setCustomName(this.getName());
			playerIn.openGui(DeExtinction.instance, -1, playerIn.worldObj, this.getEntityId(), 0, 0);
		}
	}

	public void dropInventoryItems()
	{
		if (this.inventoryBasic != null && !this.worldObj.isRemote)
			for (int i = 0; i < this.inventoryBasic.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.inventoryBasic.getStackInSlot(i);
				if (itemstack != null)
					this.entityDropItem(itemstack, 0.0F);
			}
	}

	@Override
	protected void dropItemsWhenBroken()
	{
		if (!this.worldObj.isRemote)
			this.dropInventoryItems();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inventoryBasic.getSizeInventory(); ++i)
		{
			ItemStack itemstack = this.inventoryBasic.getStackInSlot(i);
			if (itemstack != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				itemstack.writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tagCompound.setTag("Items", nbttaglist);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		NBTTagList nbttaglist = tagCompound.getTagList("Items", 10);
		this.createInventory();
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.inventoryBasic.getSizeInventory())
				this.inventoryBasic.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound1));
		}
	}

	public abstract int getSlotsNumber();
}
