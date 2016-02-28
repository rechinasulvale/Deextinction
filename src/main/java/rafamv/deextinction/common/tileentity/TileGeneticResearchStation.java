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
import rafamv.deextinction.common.container.ContainerGeneticResearchStation;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.CreatureTexture;
import rafamv.deextinction.common.database.creatures.Chicken;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class TileGeneticResearchStation extends TileEntityLockable implements IUpdatePlayerListBox, ISidedInventory
{
	public static final int[] SLOT_FOSSIL_ITEMS = new int[] { 0 };
	public static final int[] SLOT_NUTRIENT = new int[] { 1 };
	public static final int[] SLOT_EMBRYO_CONTAINER = new int[] { 2 };
	public static final int[] SLOT_EMBRYO_OUTPUT = new int[] { 3 };

	private ItemStack[] slots = new ItemStack[4];
	private String tile_custom_name;

	private static final int RESEARCH_PROGRESS_MAX = 200;
	private int researchProgress = 0;
	private boolean shouldResearch = false;

	private static final int CREATING_PROGRESS_MAX = 2400;
	private int creatingProgress = 0;

	private static final int NUTRIENTS_MAX = 1000;
	private int nutrients = 0;

	private HashMap<String, Integer> researchList = new HashMap<String, Integer>();
	private Creature creatureSelected = DEDatabaseRegistry.LIST_ALL_CREATURES.get(Chicken.NAME);
	private boolean creatureGender = true;
	private byte creatureTextureID = 0;
	private byte guiPage = 0;
	private byte guiBranch = 0;

	public TileGeneticResearchStation()
	{
		for (String creatureName : DEDatabaseRegistry.LIST_ALL_CREATURES.keySet())
			this.researchList.put(creatureName, 0);
	}

	public void setCreatureProgress(HashMap<String, Integer> creatureProgress)
	{
		this.researchList = creatureProgress;
	}

	public void setCreatureProgress(String creatureName, int creatureProgress)
	{
		this.researchList.put(creatureName, creatureProgress);
	}

	public HashMap<String, Integer> getCreatureProgressList()
	{
		return this.researchList;
	}

	public Integer getCreatureProgress(String creatureName)
	{
		return this.researchList.get(creatureName);
	}

	public void setCreatureSelected(String creatureName)
	{
		this.setCreatureSelected(DEDatabaseRegistry.LIST_ALL_CREATURES.get(creatureName));
	}

	public void setCreatureSelected(Creature creatureSelected)
	{
		this.creatureSelected = creatureSelected;
	}

	public Creature getCreatureSelected()
	{
		return this.creatureSelected;
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
		return scale * this.researchProgress / TileGeneticResearchStation.RESEARCH_PROGRESS_MAX;
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
		return scale * this.creatingProgress / TileGeneticResearchStation.CREATING_PROGRESS_MAX;
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
		return scale * this.nutrients / TileGeneticResearchStation.NUTRIENTS_MAX;
	}

	public boolean hasNutrients()
	{
		return this.nutrients > 0;
	}

	public void setTextureID(byte texture)
	{
		this.creatureTextureID = texture;
	}

	public byte getCreatureTextureID()
	{
		return this.creatureTextureID;
	}

	public CreatureTexture getMaleTexture()
	{
		HashMap<Byte, CreatureTexture> textureList = this.creatureSelected.getMaleCreatureTextures();
		if (textureList != null && textureList.containsKey(this.creatureTextureID))
			return textureList.get(this.creatureTextureID);
		return null;
	}

	public CreatureTexture getFemaleTexture()
	{
		HashMap<Byte, CreatureTexture> textureList = this.creatureSelected.getFemaleCreatureTextures();
		if (textureList != null && textureList.containsKey(this.creatureTextureID))
			return textureList.get(this.creatureTextureID);
		return null;
	}

	public void setCreatureGender(boolean creatureGender)
	{
		this.creatureGender = creatureGender;
	}

	public boolean getCreatureGender()
	{
		return this.creatureGender;
	}

	public ItemStack[] getInventory()
	{
		return this.slots;
	}

	public boolean canResearch()
	{
		if (this.creatureSelected.canCreateCreature(this.researchList) && this.researchProgress < TileGeneticResearchStation.RESEARCH_PROGRESS_MAX)
		{
			String creatureName = this.creatureSelected.getName();
			if (!this.researchList.isEmpty() && this.researchList.containsKey(creatureName))
			{
				int progress = this.researchList.get(creatureName);
				if (progress < 100)
				{
					ItemStack itemStack = this.slots[TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]];

					if (itemStack != null && this.creatureSelected.getFossilizedItems() != null)
					{
						for (ItemStack fossilStack : this.creatureSelected.getFossilizedItems().keySet())
						{
							if (fossilStack != null && fossilStack.getItem() == itemStack.getItem() && fossilStack.getMetadata() == itemStack.getMetadata())
							{
								this.researchProgress++;
								return this.researchProgress >= TileGeneticResearchStation.RESEARCH_PROGRESS_MAX;
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
		ItemStack fossilItems = this.slots[TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]];

		if (this.creatureSelected.getFossilizedItems() != null)
		{
			for (ItemStack stack : this.creatureSelected.getFossilizedItems().keySet())
			{
				if (stack != null && stack.getItem() == fossilItems.getItem() && stack.getMetadata() == fossilItems.getMetadata())
				{
					int new_progress = (int) (this.creatureSelected.getFossilizedItems().get(stack) * (1.0F + 0.3F * this.worldObj.rand.nextFloat()));

					fossilItems.stackSize--;
					if (fossilItems.stackSize < 1)
						this.slots[TileGeneticResearchStation.SLOT_FOSSIL_ITEMS[0]] = null;

					String creatureName = this.creatureSelected.getName();
					if (this.researchList.containsKey(creatureName))
					{
						int old_progress = this.researchList.get(creatureName);
						if (old_progress + new_progress > 100)
						{
							this.researchList.remove(creatureName);
							this.researchList.put(creatureName, 100);
						}
						else
						{
							this.researchList.remove(creatureName);
							this.researchList.put(creatureName, old_progress + new_progress);
						}
					}
					else
						this.researchList.put(creatureName, new_progress);

					this.researchProgress = 0;

					if (this.researchProgress >= TileGeneticResearchStation.RESEARCH_PROGRESS_MAX)
						this.shouldResearch = false;

					this.worldObj.markBlockForUpdate(this.pos);
				}
			}
		}
	}

	public boolean canConsumeFood()
	{
		ItemStack itemStack = this.slots[TileGeneticResearchStation.SLOT_NUTRIENT[0]];
		if (this.nutrients < TileGeneticResearchStation.NUTRIENTS_MAX && itemStack != null)
			return DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.containsKey(itemStack.getUnlocalizedName());
		return false;
	}

	public void consumeFood()
	{
		ItemStack itemStack = this.slots[TileGeneticResearchStation.SLOT_NUTRIENT[0]];
		String foodUnlocalizedName = itemStack.getItem().getUnlocalizedName();
		this.nutrients += DEDatabaseRegistry.CREATURE_NUTRIENTS_LIST.get(foodUnlocalizedName);
		if (this.nutrients > TileGeneticResearchStation.NUTRIENTS_MAX)
			this.nutrients = TileGeneticResearchStation.NUTRIENTS_MAX;

		itemStack.stackSize--;
		if (itemStack.stackSize < 1)
			this.slots[TileGeneticResearchStation.SLOT_NUTRIENT[0]] = null;
	}

	public void startEmbryo()
	{
		this.creatingProgress++;
	}

	public void cancelEmbryo()
	{
		if (!this.worldObj.isRemote)
		{
			this.nutrients -= this.getCreateProgressScaled(this.creatureSelected.getNutrientsRequired());
			this.creatingProgress = 0;
		}
	}

	public boolean canCreateEmbryo()
	{
		if (this.slots[TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]] == null && this.creatingProgress < TileGeneticResearchStation.CREATING_PROGRESS_MAX && this.creatureSelected != null && this.researchList != null && !this.researchList.isEmpty())
		{
			String creatureName = this.creatureSelected.getName();
			if (this.researchList.containsKey(creatureName) && this.researchList.get(creatureName) > 49 && this.nutrients >= this.creatureSelected.getNutrientsRequired())
			{
				this.creatingProgress++;
				return this.creatingProgress >= TileGeneticResearchStation.CREATING_PROGRESS_MAX;
			}
		}
		this.creatingProgress = 0;
		return false;
	}

	public void createEmbryo()
	{
		if (!this.worldObj.isRemote)
		{
			ItemStack embryoContainerItemStack = this.slots[TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]];
			ItemStack requiredEmbryoContainerItemStack = this.creatureSelected.getEmbryoContainerItem();
			if (requiredEmbryoContainerItemStack != null && requiredEmbryoContainerItemStack.getItem() != null ? embryoContainerItemStack != null : true)
			{
				ItemStack itemStack = new ItemStack(this.creatureSelected.getHatchItem());
				NBTTagCompound compound = new NBTTagCompound();
				compound.setBoolean("Gender", this.getCreatureGender());
				compound.setByte("Texture", this.getCreatureTextureID());
				if (this.creatureGender)
					compound.setString("TextureName", this.creatureSelected.getMaleCreatureTextures().get(this.getCreatureTextureID()).getTextureName());
				else
					compound.setString("TextureName", this.creatureSelected.getFemaleCreatureTextures().get(this.getCreatureTextureID()).getTextureName());
				compound.setByte("ResearchProgress", this.getCreatureProgress(this.creatureSelected.getName()).byteValue());
				itemStack.setTagCompound(compound);
				this.slots[TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT[0]] = itemStack;

				this.nutrients -= this.creatureSelected.getNutrientsRequired();
				if (this.nutrients < 0)
					this.nutrients = 0;

				if (embryoContainerItemStack != null && this.creatureSelected.getEmbryoContainerItem() != null && this.creatureSelected.getEmbryoContainerItem().getItem() == embryoContainerItemStack.getItem())
				{
					embryoContainerItemStack.stackSize--;
					if (embryoContainerItemStack.stackSize < 1)
						this.slots[TileGeneticResearchStation.SLOT_EMBRYO_CONTAINER[0]] = null;
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
				if (this.canCreateEmbryo())
					this.createEmbryo();
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
		return new ContainerGeneticResearchStation(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
		return "deextinction:genetic_research_station";
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		return side == EnumFacing.DOWN ? TileGeneticResearchStation.SLOT_EMBRYO_OUTPUT : TileGeneticResearchStation.SLOT_FOSSIL_ITEMS;
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
		return this.hasCustomName() ? this.tile_custom_name : StatCollector.translateToLocal("container.genetic_research_station.name");
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

		if (this.creatureSelected != null)
			compound.setString("Creature", String.valueOf(this.creatureSelected.getName()));

		if (!this.researchList.isEmpty())
			for (String creatureName : this.researchList.keySet())
				compound.setInteger("CreatureProgress_" + creatureName, this.researchList.get(creatureName));

		compound.setByte("ResearchProgress", (byte) this.researchProgress);
		compound.setByte("CreatingProgress", (byte) this.creatingProgress);
		compound.setShort("Nutrients", (short) this.nutrients);
		compound.setByte("Texture", this.creatureTextureID);
		compound.setBoolean("Gender", this.creatureGender);
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

		if (compound.hasKey("Creature"))
			this.creatureSelected = DEDatabaseRegistry.LIST_ALL_CREATURES.get(compound.getString("Creature"));

		HashMap<String, Creature> creature_list = DEDatabaseRegistry.LIST_ALL_CREATURES;
		if (!creature_list.isEmpty())
			for (String creatureName : creature_list.keySet())
				if (compound.hasKey("CreatureProgress_" + creatureName))
					this.researchList.put(creatureName, compound.getInteger("CreatureProgress_" + creatureName));

		if (compound.hasKey("ResearchProgress"))
			this.researchProgress = compound.getByte("ResearchProgress");

		if (compound.hasKey("CreatingProgress"))
			this.creatingProgress = compound.getByte("CreatingProgress");

		if (compound.hasKey("Nutrients"))
			this.nutrients = compound.getShort("Nutrients");

		if (compound.hasKey("Texture"))
			this.creatureTextureID = compound.getByte("Texture");

		if (compound.hasKey("Gender"))
			this.creatureGender = compound.getBoolean("Gender");
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
