package rafamv.deextinction.common.tileentity;

import java.util.HashMap;

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
import rafamv.deextinction.common.container.ContainerBotanicalResearchStation;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.FoliageVariant;
import rafamv.deextinction.common.database.foliage.Fern;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class TileBotanicalResearchStation extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	public static final int[] SLOT_FOSSIL_ITEMS = new int[] { 0 };
	public static final int[] SLOT_NUTRIENT = new int[] { 1 };
	public static final int[] SLOT_PREVIOUS_FOLIAGE = new int[] { 2 };
	public static final int[] SLOT_SAPLING_OUTPUT = new int[] { 3 };

	private ItemStack[] slots = new ItemStack[4];
	private String tile_custom_name;

	private static final int RESEARCH_PROGRESS_MAX = 200;
	private int researchProgress = 0;
	private boolean shouldResearch = false;

	private static final int CREATING_PROGRESS_MAX = 600;
	private int creatingProgress = 0;

	private static final int NUTRIENTS_MAX = 1000;
	private int nutrients = 0;

	private HashMap<String, Integer> researchList = new HashMap<String, Integer>();
	private Foliage foliageSelected = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(Fern.NAME);
	private byte foliageVariantID = 0;
	private byte guiPage = 0;
	private byte guiBranch = 0;

	public TileBotanicalResearchStation()
	{
		for (String foliageName : DEDatabaseRegistry.LIST_ALL_FOLIAGE.keySet())
			this.researchList.put(foliageName, 0);
	}

	public void setFoliageProgress(HashMap<String, Integer> foliageProgress)
	{
		this.researchList = foliageProgress;
	}

	public void setFoliageProgress(String foliageName, int foliageProgress)
	{
		this.researchList.put(foliageName, foliageProgress);
	}

	public HashMap<String, Integer> getFoliageProgressList()
	{
		return this.researchList;
	}

	public Integer getFoliageProgress(String foliageName)
	{
		return this.researchList.get(foliageName);
	}

	public void setFoliageSelected(String foliageName)
	{
		this.setFoliageSelected(DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(foliageName));
	}

	public void setFoliageSelected(Foliage foliageSelected)
	{
		this.foliageSelected = foliageSelected;
	}

	public Foliage getFoliageSelected()
	{
		return this.foliageSelected;
	}

	public void setGuiPage(int guiPage)
	{
		if (this.creatingProgress == 0)
			this.guiPage = (byte) guiPage;
	}

	public byte getGuiPage()
	{
		return this.guiPage;
	}

	public void setGuiBranch(int guiBranch)
	{
		this.guiBranch = (byte) guiBranch;
	}

	public byte getGuiBranch()
	{
		return this.guiBranch;
	}

	public void setResearchProgress(int researchProgress)
	{
		this.researchProgress = researchProgress;
	}

	public int getResearchProgress()
	{
		return this.researchProgress;
	}

	public int getResearchProgressScaled(int scale)
	{
		return scale * this.researchProgress / TileBotanicalResearchStation.RESEARCH_PROGRESS_MAX;
	}

	public void setShouldResearch(boolean flag)
	{
		this.shouldResearch = flag;
	}

	public boolean shouldResearch()
	{
		return this.shouldResearch;
	}

	public boolean isResearching()
	{
		return this.shouldResearch && this.researchProgress > 0;
	}

	public void setCreatingProgress(int creatingProgress)
	{
		this.creatingProgress = creatingProgress;
	}

	public int getCreatingProgress()
	{
		return this.creatingProgress;
	}

	public int getCreateProgressScaled(int scale)
	{
		return scale * this.creatingProgress / TileBotanicalResearchStation.CREATING_PROGRESS_MAX;
	}

	public boolean isCreating()
	{
		return this.creatingProgress > 0;
	}

	public int getNutrients()
	{
		return this.nutrients;
	}

	public void setNutrients(int value)
	{
		this.nutrients = value;
	}

	public int getNutrientsScaled(int scale)
	{
		return scale * this.nutrients / TileBotanicalResearchStation.NUTRIENTS_MAX;
	}

	public boolean hasNutrients()
	{
		return this.nutrients > 0;
	}

	public void setFoliageVariantID(byte variant)
	{
		this.foliageVariantID = variant;
	}

	public byte getFoliageVariantID()
	{
		return this.foliageVariantID;
	}

	public FoliageVariant getVariants()
	{
		HashMap<Byte, FoliageVariant> variantList = this.foliageSelected.getVariants();
		if (variantList != null && variantList.containsKey(this.foliageVariantID))
			return variantList.get(this.foliageVariantID);
		return null;
	}

	public ItemStack[] getInventory()
	{
		return this.slots;
	}

	public boolean canResearch()
	{
		if (this.foliageSelected.canCreateFoliage(this.researchList) && this.researchProgress < TileBotanicalResearchStation.RESEARCH_PROGRESS_MAX)
		{
			String foliageName = this.foliageSelected.getName();
			if (!this.researchList.isEmpty() && this.researchList.containsKey(foliageName))
			{
				int progress = this.researchList.get(foliageName);
				if (progress < 100)
				{
					ItemStack itemStack = this.slots[TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]];

					if (itemStack != null && this.foliageSelected.getFossilizedItems() != null)
					{
						for (ItemStack fossilStack : this.foliageSelected.getFossilizedItems().keySet())
						{
							if (fossilStack != null && fossilStack.getItem() == itemStack.getItem() && fossilStack.getMetadata() == itemStack.getMetadata())
							{
								this.researchProgress++;
								return this.researchProgress >= TileBotanicalResearchStation.RESEARCH_PROGRESS_MAX;
							}
						}
					}
				}
			}
		}
		this.researchProgress = 0;
		return false;
	}

	public void research()
	{
		ItemStack fossilItems = this.slots[TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]];

		if (this.foliageSelected.getFossilizedItems() != null)
		{
			for (ItemStack stack : this.foliageSelected.getFossilizedItems().keySet())
			{
				if (stack != null && stack.getItem() == fossilItems.getItem() && stack.getMetadata() == fossilItems.getMetadata())
				{
					int new_progress = (int) (this.foliageSelected.getFossilizedItems().get(stack) * (1.0F + 0.3F * this.worldObj.rand.nextFloat()));

					fossilItems.stackSize--;
					if (fossilItems.stackSize < 1)
						this.slots[TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS[0]] = null;

					String foliageName = this.foliageSelected.getName();
					if (this.researchList.containsKey(foliageName))
					{
						int old_progress = this.researchList.get(foliageName);
						if (old_progress + new_progress > 100)
						{
							this.researchList.remove(foliageName);
							this.researchList.put(foliageName, 100);
						}
						else
						{
							this.researchList.remove(foliageName);
							this.researchList.put(foliageName, old_progress + new_progress);
						}
					}
					else
						this.researchList.put(foliageName, new_progress);

					this.researchProgress = 0;

					if (this.researchProgress >= TileBotanicalResearchStation.RESEARCH_PROGRESS_MAX)
						this.shouldResearch = false;

					this.worldObj.markBlockForUpdate(this.pos);
				}
			}
		}
	}

	public boolean canConsumeFood()
	{
		ItemStack itemStack = this.slots[TileBotanicalResearchStation.SLOT_NUTRIENT[0]];
		if (this.nutrients < TileBotanicalResearchStation.NUTRIENTS_MAX && itemStack != null)
			return DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.containsKey(itemStack.getUnlocalizedName());
		return false;
	}

	public void consumeFood()
	{
		ItemStack itemStack = this.slots[TileBotanicalResearchStation.SLOT_NUTRIENT[0]];
		String foodUnlocalizedName = itemStack.getItem().getUnlocalizedName();
		this.nutrients += DEDatabaseRegistry.FOLIAGE_NUTRIENTS_LIST.get(foodUnlocalizedName);
		if (this.nutrients > TileBotanicalResearchStation.NUTRIENTS_MAX)
			this.nutrients = TileBotanicalResearchStation.NUTRIENTS_MAX;

		itemStack.stackSize--;
		if (itemStack.stackSize < 1)
			this.slots[TileBotanicalResearchStation.SLOT_NUTRIENT[0]] = null;
	}

	public void startSapling()
	{
		this.creatingProgress++;
	}

	public void cancelSapling()
	{
		if (!this.worldObj.isRemote)
		{
			this.nutrients -= this.getCreateProgressScaled(this.foliageSelected.getNutrientsRequired(this.foliageVariantID));
			this.creatingProgress = 0;
		}
	}

	public boolean canCreateSapling()
	{
		if (this.slots[TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]] == null && this.creatingProgress < TileBotanicalResearchStation.CREATING_PROGRESS_MAX && this.foliageSelected != null && this.researchList != null && !this.researchList.isEmpty())
		{
			String foliageName = this.foliageSelected.getName();
			if (this.researchList.containsKey(foliageName) && this.researchList.get(foliageName) > 49 && this.nutrients >= this.foliageSelected.getNutrientsRequired(this.foliageVariantID))
			{
				this.creatingProgress++;
				return this.creatingProgress >= TileBotanicalResearchStation.CREATING_PROGRESS_MAX;
			}
		}
		this.creatingProgress = 0;
		return false;
	}

	public void createSapling()
	{
		if (!this.worldObj.isRemote)
		{
			ItemStack saplingContainerItemStack = this.slots[TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]];
			ItemStack requiredSaplingContainerItemStack = this.foliageSelected.getRequiredItem();
			if (requiredSaplingContainerItemStack != null && requiredSaplingContainerItemStack.getItem() != null ? saplingContainerItemStack != null : true)
			{
				FoliageVariant variant = this.foliageSelected.getVariant(this.foliageVariantID);
				if (variant != null)
				{
					this.slots[TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT[0]] = variant.getSapling();

					this.nutrients -= this.foliageSelected.getNutrientsRequired(this.foliageVariantID);
					if (this.nutrients < 0)
						this.nutrients = 0;

					if (saplingContainerItemStack != null && this.foliageSelected.getRequiredItem() != null && this.foliageSelected.getRequiredItem().getItem() == saplingContainerItemStack.getItem())
					{
						saplingContainerItemStack.stackSize--;
						if (saplingContainerItemStack.stackSize < 1)
							this.slots[TileBotanicalResearchStation.SLOT_PREVIOUS_FOLIAGE[0]] = null;
					}
				}
			}
			this.creatingProgress = 0;
		}
	}

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.isCreating())
			{
				if (this.canCreateSapling())
					this.createSapling();
			}
			else
			{
				if (this.shouldResearch)
				{
					if (this.canResearch())
						this.research();
				}
				else
					this.researchProgress = 0;
			}
			if (this.canConsumeFood())
				this.consumeFood();
		}
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerBotanicalResearchStation(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
		return "deextinction:botanical_research_station";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? TileBotanicalResearchStation.SLOT_SAPLING_OUTPUT : TileBotanicalResearchStation.SLOT_FOSSIL_ITEMS;
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
		return this.hasCustomName() ? this.tile_custom_name : StatCollector.translateToLocal("container.botanical_research_station.name");
	}

	public String getCustomName()
	{
		return this.tile_custom_name;
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

		compound.setByte("GuiPage", this.guiPage);
		compound.setByte("GuiBranch", this.guiBranch);

		if (this.foliageSelected != null)
			compound.setString("Foliage", String.valueOf(this.foliageSelected.getName()));

		if (!this.researchList.isEmpty())
			for (String foliageName : this.researchList.keySet())
				compound.setInteger("FoliageProgress_" + foliageName, this.researchList.get(foliageName));

		compound.setByte("ResearchProgress", (byte) this.researchProgress);
		compound.setByte("CreatingProgress", (byte) this.creatingProgress);
		compound.setShort("Nutrients", (short) this.nutrients);
		compound.setByte("Variant", this.foliageVariantID);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		if (compound.hasKey("Items"))
		{
			NBTTagList nbttaglist = compound.getTagList("Items", 10);
			this.slots = new ItemStack[this.getSizeInventory()];
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				byte b0 = nbttagcompound1.getByte("Slot");
				if (b0 >= 0 && b0 < this.slots.length)
					this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		if (compound.hasKey("CustomName"))
			this.tile_custom_name = compound.getString("CustomName");

		if (compound.hasKey("GuiPage"))
			this.guiPage = compound.getByte("GuiPage");

		if (compound.hasKey("GuiBranch"))
			this.guiBranch = compound.getByte("GuiBranch");

		if (compound.hasKey("Foliage"))
			this.foliageSelected = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(compound.getString("Foliage"));

		HashMap<String, Foliage> foliage_list = DEDatabaseRegistry.LIST_ALL_FOLIAGE;
		if (!foliage_list.isEmpty())
			for (String foliageName : foliage_list.keySet())
				if (compound.hasKey("FoliageProgress_" + foliageName))
					this.researchList.put(foliageName, compound.getInteger("FoliageProgress_" + foliageName));

		if (compound.hasKey("ResearchProgress"))
			this.researchProgress = compound.getByte("ResearchProgress");

		if (compound.hasKey("CreatingProgress"))
			this.creatingProgress = compound.getByte("CreatingProgress");

		if (compound.hasKey("Nutrients"))
			this.nutrients = compound.getShort("Nutrients");

		if (compound.hasKey("Variant"))
			this.foliageVariantID = compound.getByte("Variant");
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
