package rafamv.deextinction.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class BlockConnected extends Block
{
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public BlockConnected(Material material)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockConnected.NORTH, Boolean.valueOf(false)).withProperty(BlockConnected.EAST, Boolean.valueOf(false)).withProperty(BlockConnected.SOUTH, Boolean.valueOf(false)).withProperty(BlockConnected.WEST, Boolean.valueOf(false)));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return state.withProperty(BlockConnected.NORTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.north()))).withProperty(BlockConnected.EAST, Boolean.valueOf(this.canConnectTo(worldIn, pos.east()))).withProperty(BlockConnected.SOUTH, Boolean.valueOf(this.canConnectTo(worldIn, pos.south())))
				.withProperty(BlockConnected.WEST, Boolean.valueOf(this.canConnectTo(worldIn, pos.west())));
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BlockConnected.NORTH, BlockConnected.EAST, BlockConnected.WEST, BlockConnected.SOUTH });
	}

	public abstract boolean canConnectTo(IBlockAccess worldIn, BlockPos pos);
}
