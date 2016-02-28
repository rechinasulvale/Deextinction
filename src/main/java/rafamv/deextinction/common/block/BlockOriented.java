package rafamv.deextinction.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockOriented extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockOriented(Material material)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockOriented.FACING, EnumFacing.NORTH));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultFacing(worldIn, pos, state);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			Block blockNorth = worldIn.getBlockState(pos.north()).getBlock();
			Block blockSouth = worldIn.getBlockState(pos.south()).getBlock();
			Block blockWest = worldIn.getBlockState(pos.west()).getBlock();
			Block blockEast = worldIn.getBlockState(pos.east()).getBlock();
			EnumFacing enumfacing = (EnumFacing) state.getValue(BlockOriented.FACING);
			if (enumfacing == EnumFacing.NORTH && blockNorth.isFullBlock() && !blockSouth.isFullBlock())
				enumfacing = EnumFacing.SOUTH;
			else if (enumfacing == EnumFacing.SOUTH && blockSouth.isFullBlock() && !blockNorth.isFullBlock())
				enumfacing = EnumFacing.NORTH;
			else if (enumfacing == EnumFacing.WEST && blockWest.isFullBlock() && !blockEast.isFullBlock())
				enumfacing = EnumFacing.EAST;
			else if (enumfacing == EnumFacing.EAST && blockEast.isFullBlock() && !blockWest.isFullBlock())
				enumfacing = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, enumfacing), 2);
		}
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return this.getDefaultState().withProperty(BlockOriented.FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState getStateForEntityRender(IBlockState state)
	{
		return this.getDefaultState().withProperty(BlockOriented.FACING, EnumFacing.SOUTH);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if (enumfacing.getAxis() == EnumFacing.Axis.Y)
			enumfacing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(BlockOriented.FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(BlockOriented.FACING)).getIndex();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, BlockOriented.FACING);
	}
}
