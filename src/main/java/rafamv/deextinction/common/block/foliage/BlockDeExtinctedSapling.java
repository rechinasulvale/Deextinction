package rafamv.deextinction.common.block.foliage;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Tree;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class BlockDeExtinctedSapling extends BlockBush implements IGrowable
{
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

	private Foliage tree;
	private byte treeVariant;

	public BlockDeExtinctedSapling(String treeName, byte variant)
	{
		this.setUnlocalizedName("block_" + treeName + "_" + variant + "_sapling");
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDeExtinctedSapling.STAGE, Integer.valueOf(0)));
		this.setStepSound(Block.soundTypeGrass);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
		this.treeVariant = variant;
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	public void setTree(String treeName)
	{
		this.tree = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(treeName);
	}

	public Foliage getTreeType()
	{
		return this.tree;
	}

	public byte getTreeVariant()
	{
		return this.treeVariant;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			super.updateTick(worldIn, pos, state, rand);

			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(worldIn, rand, pos, state);
			}
		}
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return 0;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		return worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		if (((Integer) state.getValue(BlockDeExtinctedSapling.STAGE)).intValue() == 0)
		{
			worldIn.setBlockState(pos, state.cycleProperty(BlockDeExtinctedSapling.STAGE), 4);
		}
		else
		{
			if (this.tree != null)
			{
				if (this.tree instanceof Tree)
				{
					WorldGenAbstractTree treeGenerator = ((Tree) this.tree).getTreeGenerator(this.treeVariant);
					if (treeGenerator != null)
						treeGenerator.generate(worldIn, this.RANDOM, pos);
					else
						DeExtinction.logger.error("Registered tree with variant: " + this.treeVariant + " does not has a tree generator.");
				}
				else
					DeExtinction.logger.error("Registered tree is not a tree class.");
			}
			else
				DeExtinction.logger.error("Registered tree is null.");
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BlockDeExtinctedSapling.STAGE, Integer.valueOf((meta & 0x8) >> 3));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i |= ((Integer) state.getValue(BlockDeExtinctedSapling.STAGE)).intValue() << 3;
		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BlockDeExtinctedSapling.STAGE });
	}
}
