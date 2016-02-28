package rafamv.deextinction.common.block.foliage;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockDeExtinctedVeryTallBushGrowable extends Block implements IGrowable
{
	public static final PropertyEnum HEIGHT = PropertyEnum.create("height", EnumBlockHeight.class);

	public BlockDeExtinctedVeryTallBushGrowable(String plantName, int Variant, float width, float height)
	{
		super(Material.plants);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.LOWER));
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setUnlocalizedName("block_" + plantName + "_" + Variant);
		this.setBlockBounds(0.5F - width, 0.0F, 0.5F - width, 0.5F + width, height, 0.5F + width);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
		{
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.air.getDefaultState(), 3);
		}
	}

	protected boolean canPlaceBlockOn(Block blockBelow)
	{
		return blockBelow == this || blockBelow == Blocks.grass || blockBelow == Blocks.dirt;
	}

	public boolean canGrowAbove(Block blockAbove)
	{
		return blockAbove.getMaterial() == Material.air;
	}

	@Override
	public boolean isReplaceable(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() != this;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return this.canPlaceBlockOn(worldIn.getBlockState(pos.down()).getBlock()) && this.canGrowAbove(worldIn.getBlockState(pos.up()).getBlock());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		IBlockState blockStateBelow = worldIn.getBlockState(pos.down());
		if (blockStateBelow.getBlock() == this)
		{
			Comparable heightBelow = blockStateBelow.getValue(BlockDeExtinctedVeryTallBushGrowable.HEIGHT);
			if (heightBelow == BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.LOWER)
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.MIDDLE), 2);
			else if (heightBelow == BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.MIDDLE)
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.UPPER), 2);
		}
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		BlockPos down = pos.down();
		Block blockBelow = worldIn.getBlockState(down).getBlock();

		if (blockBelow != this)
			return this.canPlaceBlockOn(blockBelow);

		return blockBelow.canPlaceBlockAt(worldIn, down);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		return state.getValue(BlockDeExtinctedVeryTallBushGrowable.HEIGHT) != BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.UPPER;
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		return state.getValue(BlockDeExtinctedVeryTallBushGrowable.HEIGHT) != BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.UPPER;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		BlockPos posAbove = pos.up();
		if (this.canPlaceBlockAt(worldIn, posAbove))
		{
			Comparable height = state.getValue(BlockDeExtinctedVeryTallBushGrowable.HEIGHT);

			if (height != BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.LOWER)
				worldIn.setBlockState(posAbove, this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.MIDDLE), 2);
			else if (height != BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.MIDDLE)
				worldIn.setBlockState(posAbove, this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.UPPER), 2);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	{
		return null;
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

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType()
	{
		return Block.EnumOffsetType.NONE;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBushGrowable.HEIGHT, BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.values()[meta]);
	}

	@Override
	public int getDamageValue(World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		return iblockstate.getBlock().getMetaFromState(iblockstate);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight) state.getValue(BlockDeExtinctedVeryTallBushGrowable.HEIGHT)).getMetadata();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, BlockDeExtinctedVeryTallBushGrowable.HEIGHT);
	}

	public static enum EnumBlockHeight implements IStringSerializable
	{
		LOWER(0, "lower"),
		MIDDLE(1, "middle"),
		UPPER(2, "upper");

		private EnumBlockHeight(int meta, String name)
		{
			this(meta, name, name);
		}

		private EnumBlockHeight(int meta, String name, String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName()
		{
			return this.name;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight byMetadata(int meta)
		{
			if (meta < 0 || meta >= BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.META_LOOKUP.length)
				meta = 0;
			return BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.META_LOOKUP[meta];
		}

		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private static final BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight[] META_LOOKUP = new BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight[values().length];
		static
		{
			BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight[] enumValues = values();
			int enumListSize = enumValues.length;

			for (int i = 0; i < enumListSize; i++)
			{
				BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight enumValue = enumValues[i];
				BlockDeExtinctedVeryTallBushGrowable.EnumBlockHeight.META_LOOKUP[enumValue.getMetadata()] = enumValue;
			}
		}
	}
}
