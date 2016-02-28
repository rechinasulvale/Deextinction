package rafamv.deextinction.common.item.speciesitems;

import java.util.HashMap;
import java.util.List;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.CreatureTexture;
import rafamv.deextinction.common.database.SyringeCreature;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedCreature;
import rafamv.deextinction.common.entity.base.EntityPregnantMammal;
import rafamv.deextinction.common.entity.base.EntityPregnantMammalKeys;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class ItemCreatureSyringe extends Item
{
	private final String creature_name;

	public ItemCreatureSyringe(String creature_name)
	{
		super();
		this.setUnlocalizedName("item_" + creature_name + "_syringe");
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
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe.data_request_0"));
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe.data_request_1"));
				}
				else
					list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe"));
			}
		}
		else
		{
			if (player.capabilities.isCreativeMode)
			{
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe.data_request_0"));
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe.data_request_1"));
			}
			else
				list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("item.item_species.invalid.syringe"));
		}
	}

	/** Returns the name of the creature from this egg */
	public String getCreatureName()
	{
		return this.creature_name;
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

	@Override
	public boolean itemInteractionForEntity(ItemStack syringe, EntityPlayer player, EntityLivingBase entityLivingBase)
	{
		if (syringe.hasTagCompound())
		{
			NBTTagCompound compound = syringe.getTagCompound();
			if (compound.hasKey("ResearchProgress") && compound.hasKey("Texture") && compound.hasKey("Gender"))
			{
				Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(this.creature_name);
				if (creature != null && creature instanceof SyringeCreature)
				{
					SyringeCreature syringeCreature = (SyringeCreature) creature;
					if (syringeCreature.isMother(entityLivingBase))
					{
						if (entityLivingBase instanceof EntityAgeable)
						{
							EntityAgeable vanillaAgeable = (EntityAgeable) entityLivingBase;
							if (!vanillaAgeable.isChild())
								this.setBaby(player.worldObj, player, vanillaAgeable, syringe, compound);
							return true;
						}
						else if (entityLivingBase instanceof EntityDeExtinctedAgeable)
						{
							EntityDeExtinctedAgeable deExtinctedAgeable = (EntityDeExtinctedAgeable) entityLivingBase;
							if (deExtinctedAgeable.isAdult())
								this.setBaby(player.worldObj, player, deExtinctedAgeable, syringe, compound);
							return true;
						}
						else if (entityLivingBase instanceof EntityDeExtinctedCreature)
							this.setBaby(player.worldObj, player, (EntityDeExtinctedCreature) entityLivingBase, syringe, compound);
						return true;
					}
				}
			}
		}
		return false;
	}

	private void setBaby(World world, EntityPlayer player, EntityLivingBase mother, ItemStack syringe, NBTTagCompound compound)
	{
		EntityPregnantMammal mammal = EntityPregnantMammal.get(mother);

		if (mammal == null)
		{
			EntityPregnantMammal.register(mother);
			mammal = EntityPregnantMammal.get(mother);
		}

		if (mammal != null && mammal.getBabyName().equals(EntityPregnantMammalKeys.NO_EMBRYO))
		{
			mammal.setBabyName(this.creature_name);
			mammal.setResearchProgress(compound.getByte("ResearchProgress"));
			mammal.setBabyTexture(compound.getByte("Texture"));
			mammal.setBabyGender(compound.getBoolean("Gender"));

			if (!player.capabilities.isCreativeMode)
			{
				syringe.stackSize--;
				if (syringe.stackSize < 1)
					syringe = null;
			}

			if (world.isRemote)
				player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("item.item_species.syringe.embryo_inseminated")));
		}
	}
}
