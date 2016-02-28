package rafamv.deextinction.common.world.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rafamv.deextinction.common.registry.DEBlockRegistry;

public class WorldGenBasicLongTree extends WorldGenDeExtinctedTree
{
	public WorldGenBasicLongTree()
	{
		super(true, DEBlockRegistry.sequoiadendron_gray_log.getDefaultState(), DEBlockRegistry.sequoiadendron_leaves.getDefaultState(), DEBlockRegistry.sequoiadendron_gray_sapling.getDefaultState());
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos)
	{
		if (TerrainGen.saplingGrowTree(worldIn, rand, pos))
		{
			int height = 10 + rand.nextInt(11);
			System.out.println(height);
			if (this.checkPillarPositiveY(worldIn, pos, 1, height))
			{
				int leavesStartHeight = (int) (0.4F * height);
				int leavesEndheight = height - leavesStartHeight;
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.08F, pos.add(-1, leavesStartHeight, 0), 0, leavesEndheight, this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.08F, pos.add(1, leavesStartHeight, 0), 0, leavesEndheight, this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.08F, pos.add(0, leavesStartHeight, -1), 0, leavesEndheight, this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.08F, pos.add(0, leavesStartHeight, 1), 0, leavesEndheight, this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.75F, pos.add(-1, leavesStartHeight, -1), 0, leavesEndheight - 1 - rand.nextInt(2), this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.75F, pos.add(-1, leavesStartHeight, 1), 0, leavesEndheight - 1 - rand.nextInt(2), this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.75F, pos.add(1, leavesStartHeight, -1), 0, leavesEndheight - 1 - rand.nextInt(2), this.leaves);
				this.buildPillarPositiveYMissingSpots(worldIn, rand, 0.75F, pos.add(1, leavesStartHeight, 1), 0, leavesEndheight - 1 - rand.nextInt(2), this.leaves);
				this.buildPillarPositiveY(worldIn, pos, 0, height, this.log.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
				this.buildSingleBlock(worldIn, pos.add(0, height, 0), this.leaves);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPositionValid(World worldIn, BlockPos pos)
	{
		Block blockToCheck = worldIn.getBlockState(pos).getBlock();
		return blockToCheck.getMaterial() == Material.air || blockToCheck.getMaterial() == Material.vine || blockToCheck.getMaterial() == Material.plants || blockToCheck == this.sapling.getBlock() || blockToCheck.getMaterial() == Material.water;
	}
}
