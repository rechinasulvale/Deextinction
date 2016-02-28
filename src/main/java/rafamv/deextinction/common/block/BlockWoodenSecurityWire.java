package rafamv.deextinction.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockWoodenSecurityWire extends BlockOriented
{

	public BlockWoodenSecurityWire(String woodType)
	{
		super(Material.wood);
		this.setUnlocalizedName("block_" + woodType + "_security_fence_wire");
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setHarvestLevel("axe", 0);
		this.setStepSound(Block.soundTypeWood);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		this.setDefaultFacing(worldIn, pos, worldIn.getBlockState(pos));
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			Block blockNorth = worldIn.getBlockState(pos.north()).getBlock();
			if (blockNorth instanceof BlockWoodenSecurityPole || blockNorth instanceof BlockWoodenSecurityWire)
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.EAST), 2);
				return;
			}

			Block blockEast = worldIn.getBlockState(pos.east()).getBlock();
			if (blockEast instanceof BlockWoodenSecurityPole || blockEast instanceof BlockWoodenSecurityWire)
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.SOUTH), 2);
				return;
			}

			Block blockSouth = worldIn.getBlockState(pos.south()).getBlock();
			if (blockSouth instanceof BlockWoodenSecurityPole || blockSouth instanceof BlockWoodenSecurityWire)
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.WEST), 2);
				return;
			}

			Block blockWest = worldIn.getBlockState(pos.west()).getBlock();
			if (blockWest instanceof BlockWoodenSecurityPole || blockWest instanceof BlockWoodenSecurityWire)
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.NORTH), 2);
				return;
			}

			EnumFacing enumfacing = (EnumFacing) state.getValue(BlockOriented.FACING);
			if (enumfacing == EnumFacing.NORTH && blockNorth.isFullBlock() && !blockSouth.isFullBlock())
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.SOUTH), 2);
				return;
			}

			if (enumfacing == EnumFacing.SOUTH && blockSouth.isFullBlock() && !blockNorth.isFullBlock())
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.NORTH), 2);
				return;
			}

			if (enumfacing == EnumFacing.WEST && blockWest.isFullBlock() && !blockEast.isFullBlock())
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.EAST), 2);
				return;
			}

			if (enumfacing == EnumFacing.EAST && blockEast.isFullBlock() && !blockWest.isFullBlock())
			{
				worldIn.setBlockState(pos, state.withProperty(BlockOriented.FACING, EnumFacing.WEST), 2);
				return;
			}
		}
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		Block blockNorth = worldIn.getBlockState(pos.north()).getBlock();
		if (blockNorth instanceof BlockWoodenSecurityPole || blockNorth instanceof BlockWoodenSecurityWire)
			return this.getDefaultState().withProperty(BlockOriented.FACING, EnumFacing.EAST);

		Block blockEast = worldIn.getBlockState(pos.east()).getBlock();
		if (blockEast instanceof BlockWoodenSecurityPole || blockEast instanceof BlockWoodenSecurityWire)
			return this.getDefaultState().withProperty(BlockOriented.FACING, EnumFacing.SOUTH);

		Block blockSouth = worldIn.getBlockState(pos.south()).getBlock();
		if (blockSouth instanceof BlockWoodenSecurityPole || blockSouth instanceof BlockWoodenSecurityWire)
			return this.getDefaultState().withProperty(BlockOriented.FACING, EnumFacing.WEST);

		Block blockWest = worldIn.getBlockState(pos.west()).getBlock();
		if (blockWest instanceof BlockWoodenSecurityPole || blockWest instanceof BlockWoodenSecurityWire)
			return this.getDefaultState().withProperty(BlockOriented.FACING, EnumFacing.NORTH);

		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	}

	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		EnumFacing.Axis axis = ((EnumFacing) worldIn.getBlockState(pos).getValue(FACING)).getAxis();
		if (axis == EnumFacing.Axis.Z)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.5F, 0.625F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		}
		else
		{
			this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.5F, 1.0F);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		EnumFacing.Axis axis = ((EnumFacing) worldIn.getBlockState(pos).getValue(FACING)).getAxis();
		if (axis == EnumFacing.Axis.Z)
			this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
		else
			this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
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
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return true;
	}
}
