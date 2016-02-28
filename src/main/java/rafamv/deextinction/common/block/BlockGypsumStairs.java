package rafamv.deextinction.common.block;

import net.minecraft.block.BlockStairs;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockGypsumStairs extends BlockStairs
{

	public BlockGypsumStairs()
	{
		super(DEBlockRegistry.gypsum_block.getDefaultState());
		this.setLightOpacity(255);
		this.setUnlocalizedName("block_gypsum_stairs");
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}
}
