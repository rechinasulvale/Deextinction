package rafamv.deextinction.common.world.trees;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public abstract class WorldGenDeExtinctedTree extends WorldGenAbstractTree
{
	protected IBlockState log;
	protected IBlockState leaves;
	protected IBlockState sapling;

	public WorldGenDeExtinctedTree(boolean doBlockNotify, IBlockState log, IBlockState iBlockState, IBlockState sapling)
	{
		super(doBlockNotify);
		this.log = log;
		this.leaves = iBlockState;
		this.sapling = sapling;
	}

	public IBlockState getLog()
	{
		return this.log;
	}

	public IBlockState getLeaves()
	{
		return this.leaves;
	}

	public IBlockState getSapling()
	{
		return this.sapling;
	}

	public boolean checkCenteredSquareXZ(World worldIn, BlockPos pos, int size)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetZ = -size; offsetZ <= size; offsetZ++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetX, 0, offsetZ)))
					return false;
			}
		}
		return true;
	}

	public boolean checkCenteredSquareXY(World worldIn, BlockPos pos, int size)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetY = -size; offsetY <= size; offsetY++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, 0)))
					return false;
			}
		}
		return true;
	}

	public boolean checkCenteredSquareYZ(World worldIn, BlockPos pos, int size)
	{
		for (int offsetY = -size; offsetY <= size; offsetY++)
		{
			for (int offsetZ = -size; offsetZ <= size; offsetZ++)
			{
				if (!this.isPositionValid(worldIn, pos.add(0, offsetY, offsetZ)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarPositiveX(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetHeight, offsetBase, offsetBase)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarPositiveY(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetBase, offsetHeight, offsetBase)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarPositiveZ(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetBase, offsetBase, offsetHeight)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarNegativeX(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(-offsetHeight, offsetBase, offsetBase)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarNegativeY(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetBase, -offsetHeight, offsetBase)))
					return false;
			}
		}
		return true;
	}

	public boolean checkPillarNegativeZ(World worldIn, BlockPos pos, int size, int height)
	{
		for (int offsetBase = -size; offsetBase <= size; offsetBase++)
		{
			for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
			{
				if (!this.isPositionValid(worldIn, pos.add(offsetBase, offsetBase, -offsetHeight)))
					return false;
			}
		}
		return true;
	}

	public boolean checkCenteredCube(World worldIn, BlockPos pos, int size)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetY = -size; offsetY <= size; offsetY++)
			{
				for (int offsetZ = -size; offsetZ <= size; offsetZ++)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCenteredCube(World worldIn, BlockPos pos, int sizeX, int sizeY, int sizeZ)
	{
		for (int offsetX = -sizeX; offsetX <= sizeX; offsetX++)
		{
			for (int offsetY = -sizeY; offsetY <= sizeY; offsetY++)
			{
				for (int offsetZ = -sizeZ; offsetZ <= sizeZ; offsetZ++)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXZ(World worldIn, BlockPos pos, int radius)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, 0, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXZ(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, 0, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXZ(World worldIn, BlockPos pos, int radius, int addLooseInterval)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, 0, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXY(World worldIn, BlockPos pos, int radius)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, 0)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXY(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, 0)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleXY(World worldIn, BlockPos pos, int radius, int addLooseInterval)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, 0)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleYZ(World worldIn, BlockPos pos, int radius)
	{
		float radiusSQ = radius * radius;
		for (int offsetY = -radius; offsetY <= radius; offsetY++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(0, offsetY, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleYZ(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(0, offsetY, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkCircleYZ(World worldIn, BlockPos pos, int radius, int addLooseInterval)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetY = -radius; offsetY <= radius; offsetY++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					if (!this.isPositionValid(worldIn, pos.add(0, offsetY, offsetZ)))
						return false;
				}
			}
		}
		return true;
	}

	public boolean checkSphere(World worldIn, BlockPos pos, int radius)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, offsetZ)))
							return false;
					}
				}
			}
		}
		return true;
	}

	public boolean checkSphere(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
					{
						if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, offsetZ)))
							return false;
					}
				}
			}
		}
		return true;
	}

	public boolean checkSphere(World worldIn, BlockPos pos, int radius, int addLooseInterval)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
					{
						if (!this.isPositionValid(worldIn, pos.add(offsetX, offsetY, offsetZ)))
							return false;
					}
				}
			}
		}
		return true;
	}

	public void buildSingleBlock(World worldIn, BlockPos pos, IBlockState iBlockState)
	{
		if (this.isPositionValid(worldIn, pos))
			worldIn.setBlockState(pos, iBlockState);
	}

	public void buildCenteredSquareXZ(World worldIn, BlockPos pos, int size, IBlockState iBlockState)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetZ = -size; offsetZ <= size; offsetZ++)
			{
				BlockPos placementPos = pos.add(offsetX, 0, offsetZ);
				if (this.isPositionValid(worldIn, placementPos))
					worldIn.setBlockState(placementPos, iBlockState);
			}
		}
	}

	public void buildCenteredSquareXY(World worldIn, BlockPos pos, int size, IBlockState iBlockState)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetY = -size; offsetY <= size; offsetY++)
			{
				BlockPos placementPos = pos.add(offsetX, offsetY, 0);
				if (this.isPositionValid(worldIn, placementPos))
					worldIn.setBlockState(placementPos, iBlockState);
			}
		}
	}

	public void buildCenteredSquareYZ(World worldIn, BlockPos pos, int size, IBlockState iBlockState)
	{
		for (int offsetY = -size; offsetY <= size; offsetY++)
		{
			for (int offsetZ = -size; offsetZ <= size; offsetZ++)
			{
				BlockPos placementPos = pos.add(0, offsetY, offsetZ);
				if (this.isPositionValid(worldIn, placementPos))
					worldIn.setBlockState(placementPos, iBlockState);
			}
		}
	}

	public void buildPillarPositiveX(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetHeight, offsetBaseY, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarPositiveXMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetHeight, offsetBaseY, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarPositiveY(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetHeight, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarPositiveYMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetHeight, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarPositiveZ(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetBaseY, offsetHeight);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarPositiveZMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetBaseY, offsetHeight);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeX(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(-offsetHeight, offsetBaseY, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeXMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(-offsetHeight, offsetBaseY, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeY(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, -offsetHeight, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeYMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseZ = -size; offsetBaseZ <= size; offsetBaseZ++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, -offsetHeight, offsetBaseZ);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeZ(World worldIn, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetBaseY, -offsetHeight);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildPillarNegativeZMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int size, int height, IBlockState iBlockState)
	{
		for (int offsetBaseX = -size; offsetBaseX <= size; offsetBaseX++)
		{
			for (int offsetBaseY = -size; offsetBaseY <= size; offsetBaseY++)
			{
				for (int offsetHeight = 0; offsetHeight < height; offsetHeight++)
				{
					BlockPos placementPos = pos.add(offsetBaseX, offsetBaseY, -offsetHeight);
					if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCenteredCube(World worldIn, BlockPos pos, int size, IBlockState iBlockState)
	{
		for (int offsetX = -size; offsetX <= size; offsetX++)
		{
			for (int offsetY = -size; offsetY <= size; offsetY++)
			{
				for (int offsetZ = -size; offsetZ <= size; offsetZ++)
				{
					BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCenteredCube(World worldIn, BlockPos pos, int sizeX, int sizeY, int sizeZ, IBlockState iBlockState)
	{
		for (int offsetX = -sizeX; offsetX <= sizeX; offsetX++)
		{
			for (int offsetY = -sizeY; offsetY <= sizeY; offsetY++)
			{
				for (int offsetZ = -sizeZ; offsetZ <= sizeZ; offsetZ++)
				{
					BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXZ(World worldIn, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, 0, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXZ(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, 0, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXZ(World worldIn, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetX * offsetX + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, 0, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXY(World worldIn, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, offsetY, 0);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXY(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, offsetY, 0);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleXY(World worldIn, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				if ((offsetX * offsetX + offsetY * offsetY) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(offsetX, offsetY, 0);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleYZ(World worldIn, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		for (int offsetY = -radius; offsetY <= radius; offsetY++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(0, offsetY, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleYZ(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetY = -radius; offsetY <= radius; offsetY++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(0, offsetY, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildCircleYZ(World worldIn, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetY = -radius; offsetY <= radius; offsetY++)
		{
			for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
			{
				if ((offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
				{
					BlockPos placementPos = pos.add(0, offsetY, offsetZ);
					if (this.isPositionValid(worldIn, placementPos))
						worldIn.setBlockState(placementPos, iBlockState);
				}
			}
		}
	}

	public void buildSphere(World worldIn, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpotsAfterRadius(World worldIn, Random rand, float skipGenProbability, float skippingRadius, BlockPos pos, int radius, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius;
		float skippingRadiusSQ = skippingRadius * skippingRadius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					float distanceSQ = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;
					if (distanceSQ <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
						{
							if (distanceSQ < skippingRadiusSQ)
								worldIn.setBlockState(placementPos, iBlockState);
							else if (rand.nextFloat() > skipGenProbability)
								worldIn.setBlockState(placementPos, iBlockState);
						}
					}
				}
			}
		}
	}

	public void buildSphere(World worldIn, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpotsAfterRadius(World worldIn, Random rand, float skipGenProbability, float skippingRadius, BlockPos pos, int radius, float multiplyLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius * multiplyLooseInterval;
		float skippingRadiusSQ = skippingRadius * skippingRadius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					float distanceSQ = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;
					if (distanceSQ <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
						{
							if (distanceSQ < skippingRadiusSQ)
								worldIn.setBlockState(placementPos, iBlockState);
							else if (rand.nextFloat() > skipGenProbability)
								worldIn.setBlockState(placementPos, iBlockState);
						}
					}
				}
			}
		}
	}

	public void buildSphere(World worldIn, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpots(World worldIn, Random rand, float skipGenProbability, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if ((offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ) <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos) && rand.nextFloat() > skipGenProbability)
							worldIn.setBlockState(placementPos, iBlockState);
					}
				}
			}
		}
	}

	public void buildSphereMissingSpotsAfterRadius(World worldIn, Random rand, float skipGenProbability, float skippingRadius, BlockPos pos, int radius, int addLooseInterval, IBlockState iBlockState)
	{
		float radiusSQ = radius * radius + addLooseInterval;
		float skippingRadiusSQ = skippingRadius * skippingRadius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					float distanceSQ = offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ;
					if (distanceSQ <= radiusSQ)
					{
						BlockPos placementPos = pos.add(offsetX, offsetY, offsetZ);
						if (this.isPositionValid(worldIn, placementPos))
						{
							if (distanceSQ < skippingRadiusSQ)
								worldIn.setBlockState(placementPos, iBlockState);
							else if (rand.nextFloat() > skipGenProbability)
								worldIn.setBlockState(placementPos, iBlockState);
						}
					}
				}
			}
		}
	}

	public abstract boolean isPositionValid(World worldIn, BlockPos pos);
}
