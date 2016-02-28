package rafamv.deextinction.common.database;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.common.registry.DEItemRegistry;

public abstract class EggCreature extends Creature
{

	public EggCreature(String name)
	{
		super(name);
	}

	@Override
	public ItemStack getEmbryoContainerItem()
	{
		return new ItemStack(DEItemRegistry.eggshell);
	}

	public abstract EggModelBase getEggModel();

	public abstract ResourceLocation getEggTexture();
}
