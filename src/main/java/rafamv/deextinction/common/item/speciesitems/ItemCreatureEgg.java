package rafamv.deextinction.common.item.speciesitems;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.CreatureTexture;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public abstract class ItemCreatureEgg extends Item
{
	private final String creature_name;

	public ItemCreatureEgg(String creature_name)
	{
		super();
		this.setUnlocalizedName("item_" + creature_name + "_egg");
		this.creature_name = creature_name;
		this.setMaxStackSize(1);
		this.setCreativeTab(DECreativeTabs.eggs);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
	{
		if (itemStack.hasTagCompound())
		{
			NBTTagCompound compound = itemStack.getTagCompound();
			if (compound.hasKey("Gender") && compound.hasKey("TextureName") && compound.hasKey("ResearchProgress"))
			{
				if (compound.getBoolean("Gender"))
				{
					list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.item_species.research_progress") + ": " + compound.getByte("ResearchProgress") + "%");
					list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal(compound.getString("TextureName")));
					list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.item_species.male"));
				}
				else
				{
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.item_species.research_progress") + ": " + compound.getByte("ResearchProgress") + "%");
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal(compound.getString("TextureName")));
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.item_species.female"));
				}
			}
			else
			{
				if (player.capabilities.isCreativeMode)
				{
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg.data_request_0"));
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg.data_request_1"));
				}
				else
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg"));
			}
		}
		else
		{
			if (player.capabilities.isCreativeMode)
			{
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg.data_request_0"));
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg.data_request_1"));
			}
			else
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.egg"));
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		if (playerIn.capabilities.isCreativeMode)
			this.setRandomGenetics(itemStackIn, worldIn);

		return itemStackIn;
	}

	private void setRandomGenetics(ItemStack itemStackIn, World worldIn)
	{
		NBTTagCompound compound = new NBTTagCompound();
		if (DEDatabaseRegistry.LIST_ALL_CREATURES != null && !DEDatabaseRegistry.LIST_ALL_CREATURES.isEmpty())
		{
			Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(this.creature_name);
			if (creature != null)
			{
				HashMap<Byte, CreatureTexture> textures = null;

				boolean gender = worldIn.rand.nextBoolean();
				if (gender)
					textures = creature.getMaleCreatureTextures();
				else
					textures = creature.getFemaleCreatureTextures();

				if (textures != null)
				{
					byte textureID = (byte) worldIn.rand.nextInt(textures.size());
					compound.setByte("ResearchProgress", (byte) (50 + worldIn.rand.nextInt(51)));
					compound.setBoolean("Gender", gender);
					compound.setByte("Texture", textureID);
					compound.setString("TextureName", textures.get(textureID).getTextureName());

					if (itemStackIn.hasTagCompound())
					{
						NBTTagCompound itemCompound = itemStackIn.getTagCompound();

						if (itemCompound.hasKey("ResearchProgress"))
							itemCompound.removeTag("ResearchProgress");

						if (itemCompound.hasKey("Gender"))
							itemCompound.removeTag("Gender");

						if (itemCompound.hasKey("Texture"))
							itemCompound.removeTag("Texture");

						if (itemCompound.hasKey("TextureName"))
							itemCompound.removeTag("TextureName");
					}
					itemStackIn.setTagCompound(compound);
				}
			}
		}
	}

	/** Returns the name of the creature from this egg */
	public String getCreatureName()
	{
		return this.creature_name;
	}
}
