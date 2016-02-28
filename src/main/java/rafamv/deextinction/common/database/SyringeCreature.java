package rafamv.deextinction.common.database;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import rafamv.deextinction.common.registry.DEItemRegistry;

public abstract class SyringeCreature extends Creature
{

	public SyringeCreature(String name)
	{
		super(name);
	}

	@Override
	public ItemStack getEmbryoContainerItem()
	{
		return new ItemStack(DEItemRegistry.empty_syringe);
	}

	public abstract boolean isMother(EntityLivingBase mother);
}
