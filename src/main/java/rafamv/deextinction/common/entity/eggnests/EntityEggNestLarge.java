package rafamv.deextinction.common.entity.eggnests;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityEggNestLarge extends EntityEggNest
{

	public EntityEggNestLarge(World worldIn)
	{
		super(worldIn, 1.2F, 0.3F);
	}

	@Override
	public double getMountedYOffset()
	{
		return (double) this.height * 0.6D;
	}

	@Override
	public int getNumberOfEggs()
	{
		return 2;
	}

	@Override
	public int getSlotsNumber()
	{
		return 4;
	}

	@Override
	protected Item getEggNestItem()
	{
		return DEItemRegistry.egg_nest_large;
	}
}
