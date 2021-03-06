package rafamv.deextinction.common.world;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.base.Predicate;

public class WorldGenSingleMinable extends WorldGenerator
{
	private IBlockState block;
	private Predicate<IBlockState> target;

	public WorldGenSingleMinable(IBlockState block, Predicate<IBlockState> target)
	{
		this.block = block;
		this.target = target;
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock().isReplaceableOreGen(world, pos, this.target))
			world.setBlockState(pos, this.block);
		return true;
	}
}
