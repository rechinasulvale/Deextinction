package rafamv.deextinction.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class ItemEmptySyringe extends Item
{

	public ItemEmptySyringe()
	{
		super();
		this.setUnlocalizedName("item_empty_syringe");
		this.setMaxStackSize(16);
		this.setCreativeTab(DECreativeTabs.items);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack syringe, EntityPlayer player, EntityLivingBase entityLivingBase)
	{
		if (entityLivingBase instanceof EntityDeExtinctedAgeable)
		{
			EntityDeExtinctedAgeable creature = (EntityDeExtinctedAgeable) entityLivingBase;
			if (creature != null)
			{
				ItemStack blood_filled_syringe = new ItemStack(DEItemRegistry.blood_filled_syringe);
				NBTTagCompound compound = new NBTTagCompound();

				compound.setString("CreatureName", creature.hasCustomName() ? creature.getCustomNameTag() : ("entity." + creature.getCreature().getName() + ".name"));
				compound.setInteger("CreatureAge", creature.getAge());
				compound.setBoolean("CreatureGender", creature.getGender());

				int healthStats = creature.getCreatureHealthScaled(100);
				if (healthStats < 25)
					compound.setString("CreatureStatus", "entity.info.severely_hurt");
				else if (healthStats < 70)
					compound.setString("CreatureStatus", "entity.info.hurt");
				else if (healthStats < 95)
					compound.setString("CreatureStatus", "entity.info.slightly_hurt");
				else
					compound.setString("CreatureStatus", "entity.info.healthy");

				if (creature.isMale())
					compound.setString("CreatureTexture", creature.getCreature().getMaleCreatureTextures().get(creature.getTextureID()).getTextureUnlocalizedName());
				else
					compound.setString("CreatureTexture", creature.getCreature().getFemaleCreatureTextures().get(creature.getTextureID()).getTextureUnlocalizedName());

				if (!player.capabilities.isCreativeMode)
				{
					syringe.stackSize--;
					if (syringe.stackSize < 1)
						syringe = null;
				}

				blood_filled_syringe.setTagCompound(compound);
				if (!player.inventory.addItemStackToInventory(blood_filled_syringe))
					player.dropPlayerItemWithRandomChoice(blood_filled_syringe, false);
			}
		}
		return false;
	}
}
