package rafamv.deextinction.common.entity.eggnests;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class EntityEggNestTiny extends EntityEggNest
{

	public EntityEggNestTiny(World worldIn)
	{
		super(worldIn, 0.4F, 0.15F);
	}

	@Override
	public double getMountedYOffset()
	{
		return (double) this.height * 1.2D;
	}

	@Override
	public int getNumberOfEggs()
	{
		return 3;
	}

	@Override
	public int getSlotsNumber()
	{
		return 4;
	}

	@Override
	protected Item getEggNestItem()
	{
		return DEItemRegistry.egg_nest_tiny;
	}
}
