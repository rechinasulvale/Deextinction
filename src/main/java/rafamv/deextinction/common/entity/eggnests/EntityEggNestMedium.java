package rafamv.deextinction.common.entity.eggnests;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityEggNestMedium extends EntityEggNest
{

	public EntityEggNestMedium(World worldIn)
	{
		super(worldIn, 1.0F, 0.25F);
	}

	@Override
	public double getMountedYOffset()
	{
		return (double) this.height * 0.6D;
	}

	@Override
	public int getNumberOfEggs()
	{
		return 3;
	}

	@Override
	public int getSlotsNumber()
	{
		return 5;
	}

	@Override
	protected Item getEggNestItem()
	{
		return DEItemRegistry.egg_nest_medium;
	}
}
