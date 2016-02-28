package rafamv.deextinction.common.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMinableMultipleStatesReplacingMeta extends WorldGenerator
{
	private IBlockState target;
	private final List<IBlockState> listOreBlock;
	private final int numberOfStates;
	private final int numberOfBlocks;
	private final IBlockState blockToReplace;

	public WorldGenMinableMultipleStatesReplacingMeta(int numberOfBlocks, IBlockState blockToReplace, IBlockState... listOreBlock)
	{
		List<IBlockState> blockList = new ArrayList<IBlockState>();
		for (IBlockState blockState : listOreBlock)
			blockList.add(blockState);
		this.listOreBlock = blockList;
		this.numberOfStates = blockList.size();
		this.numberOfBlocks = numberOfBlocks;
		this.blockToReplace = blockToReplace;
	}

	public WorldGenMinableMultipleStatesReplacingMeta(int numberOfBlocks, IBlockState blockToReplace, List<IBlockState> listOreBlock)
	{
		this.listOreBlock = listOreBlock;
		this.numberOfStates = listOreBlock.size();
		this.numberOfBlocks = numberOfBlocks;
		this.blockToReplace = blockToReplace;
	}

	public boolean generate(World worldIn, Random random, BlockPos pos)
	{
		float f = random.nextFloat() * (float) Math.PI;
		double d0 = (double) ((float) (pos.getX() + 8) + MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
		double d1 = (double) ((float) (pos.getX() + 8) - MathHelper.sin(f) * (float) this.numberOfBlocks / 8.0F);
		double d2 = (double) ((float) (pos.getZ() + 8) + MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
		double d3 = (double) ((float) (pos.getZ() + 8) - MathHelper.cos(f) * (float) this.numberOfBlocks / 8.0F);
		double d4 = (double) (pos.getY() + random.nextInt(3) - 2);
		double d5 = (double) (pos.getY() + random.nextInt(3) - 2);

		for (int i = 0; i < this.numberOfBlocks; ++i)
		{
			float f1 = (float) i / (float) this.numberOfBlocks;
			double d6 = d0 + (d1 - d0) * (double) f1;
			double d7 = d4 + (d5 - d4) * (double) f1;
			double d8 = d2 + (d3 - d2) * (double) f1;
			double d9 = random.nextDouble() * (double) this.numberOfBlocks / 16.0D;
			double d10 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			double d11 = (double) (MathHelper.sin((float) Math.PI * f1) + 1.0F) * d9 + 1.0D;
			int j = MathHelper.floor_double(d6 - d10 / 2.0D);
			int k = MathHelper.floor_double(d7 - d11 / 2.0D);
			int l = MathHelper.floor_double(d8 - d10 / 2.0D);
			int i1 = MathHelper.floor_double(d6 + d10 / 2.0D);
			int j1 = MathHelper.floor_double(d7 + d11 / 2.0D);
			int k1 = MathHelper.floor_double(d8 + d10 / 2.0D);

			for (int l1 = j; l1 <= i1; ++l1)
			{
				double d12 = ((double) l1 + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D)
				{
					for (int i2 = k; i2 <= j1; ++i2)
					{
						double d13 = ((double) i2 + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D)
						{
							for (int j2 = l; j2 <= k1; ++j2)
							{
								double d14 = ((double) j2 + 0.5D - d8) / (d10 / 2.0D);

								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
								{
									BlockPos blockPos = new BlockPos(l1, i2, j2);
									if (worldIn.getBlockState(blockPos).equals(this.blockToReplace))
										worldIn.setBlockState(blockPos, this.listOreBlock.get(worldIn.rand.nextInt(this.numberOfStates)), 2);
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
