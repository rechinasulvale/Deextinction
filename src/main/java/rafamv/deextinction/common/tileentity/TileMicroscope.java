package rafamv.deextinction.common.tileentity;

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
import rafamv.deextinction.common.container.ContainerMicroscope;
import rafamv.deextinction.common.item.ItemBloodFilledSyringe;
import rafamv.deextinction.common.item.ItemEmptySyringe;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class TileMicroscope extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	public static final int[] SLOT_SAMPLE = new int[] { 0 };

	private ItemStack[] slots = new ItemStack[1];
	private String tile_custom_name;
	private String creatureName = StatCollector.translateToLocal("container.microscope.analizing");
	private int creatureAge = 0;
	private boolean creatureGender = true;
	private String creatureStatus = StatCollector.translateToLocal("container.microscope.analizing");
	private String creatureTexture = StatCollector.translateToLocal("container.microscope.analizing");

	private static final int ANALYZING_PROGRESS_MAX = 100;
	private int analyzingProgress = 0;

	public TileMicroscope()
	{

	}

	public void setAnalyzingProgress(int AnalyzingProgress)
	{
		this.analyzingProgress = AnalyzingProgress;
	}

	public int getAnalyzingProgress()
	{
		return this.analyzingProgress;
	}

	public int getAnalyzingProgressScaled(int scale)
	{
		return scale * this.analyzingProgress / TileMicroscope.ANALYZING_PROGRESS_MAX;
	}

	public boolean isAnalyzing()
	{
		return this.analyzingProgress > 0 && this.analyzingProgress < TileMicroscope.ANALYZING_PROGRESS_MAX;
	}

	public String getCreatureName()
	{
		return this.creatureName;
	}

	public int getCreatureAge()
	{
		return this.creatureAge;
	}

	public boolean getCreatureGender()
	{
		return this.creatureGender;
	}

	public String getCreatureStatus()
	{
		return this.creatureStatus;
	}

	public String getCreatureTexture()
	{
		return this.creatureTexture;
	}

	public void setCreatureName(String creatureName)
	{
		this.creatureName = creatureName;
	}

	public void setCreatureAge(int creatureAge)
	{
		this.creatureAge = creatureAge;
	}

	public void setCreatureGender(boolean creatureGender)
	{
		this.creatureGender = creatureGender;
	}

	public void setCreatureStatus(String creatureStatus)
	{
		this.creatureStatus = creatureStatus;
	}

	public void setCreatureTexture(String creatureTexture)
	{
		this.creatureTexture = creatureTexture;
	}

	public void analyzeSample()
	{
		ItemStack itemStack = this.slots[TileMicroscope.SLOT_SAMPLE[0]];
		if (this.analyzingProgress >= TileMicroscope.ANALYZING_PROGRESS_MAX)
		{
			if (itemStack != null && itemStack.getItem() instanceof ItemBloodFilledSyringe && itemStack.hasTagCompound())
				this.analyzingProgress = 0;
		}
		else
		{
			if (this.isAnalyzing())
			{
				if (itemStack == null || (itemStack != null && itemStack.getItem() instanceof ItemEmptySyringe))
					this.analyzingProgress++;
				else if (itemStack != null && itemStack.getItem() instanceof ItemBloodFilledSyringe && itemStack.hasTagCompound())
					this.analyzingProgress = 0;
			}
			else
			{
				if (itemStack != null && itemStack.getItem() instanceof ItemBloodFilledSyringe && itemStack.hasTagCompound())
				{
					NBTTagCompound compound = itemStack.getTagCompound();
					if (compound.hasKey("CreatureName") && compound.hasKey("CreatureAge") && compound.hasKey("CreatureGender") && compound.hasKey("CreatureStatus") && compound.hasKey("CreatureTexture"))
					{
						this.analyzingProgress++;
						this.slots[TileMicroscope.SLOT_SAMPLE[0]] = new ItemStack(DEItemRegistry.empty_syringe);
						this.creatureName = compound.getString("CreatureName");
						this.creatureAge = compound.getInteger("CreatureAge");
						this.creatureGender = compound.getBoolean("CreatureGender");
						this.creatureStatus = compound.getString("CreatureStatus");
						this.creatureTexture = compound.getString("CreatureTexture");
						this.worldObj.markBlockForUpdate(this.pos);
					}
					else
						this.analyzingProgress = 0;
				}
				else
					this.analyzingProgress = 0;
			}
		}
	}

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote)
			this.analyzeSample();
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerMicroscope(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
		return "deextinction:microscope";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return TileMicroscope.SLOT_SAMPLE;
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
		return this.hasCustomName() ? this.tile_custom_name : StatCollector.translateToLocal("container.microscope.name");
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

		compound.setString("CreatureName", this.creatureName);
		compound.setInteger("CreatureAge", this.creatureAge);
		compound.setBoolean("CreatureGender", this.creatureGender);
		compound.setString("CreatureStatus", this.creatureStatus);
		compound.setString("CreatureTexture", this.creatureTexture);
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

		if (compound.hasKey("CreatureName"))
			this.creatureName = compound.getString("CreatureName");

		if (compound.hasKey("CreatureAge"))
			this.creatureAge = compound.getInteger("CreatureAge");

		if (compound.hasKey("CreatureGender"))
			this.creatureGender = compound.getBoolean("CreatureGender");

		if (compound.hasKey("CreatureStatus"))
			this.creatureStatus = compound.getString("CreatureStatus");

		if (compound.hasKey("CreatureTexture"))
			this.creatureTexture = compound.getString("CreatureTexture");
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
