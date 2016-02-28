package rafamv.deextinction.common.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockLowSecurityPole extends BlockConnected
{

	public BlockLowSecurityPole()
	{
		super(Material.rock);
		this.setUnlocalizedName("block_low_security_fence_pole");
		this.setHardness(1.0F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		boolean hasNorth = this.canConnectTo(worldIn, pos.north());
		boolean hasSouth = this.canConnectTo(worldIn, pos.south());
		boolean hasWest = this.canConnectTo(worldIn, pos.west());
		boolean hasEast = this.canConnectTo(worldIn, pos.east());

		float xInit = 0.25F;
		float xfinal = 0.75F;
		float zInit = 0.25F;
		float zFinal = 0.75F;

		if (hasNorth)
			zInit = 0.0F;

		if (hasSouth)
			zFinal = 1.0F;

		if (hasNorth || hasSouth)
		{
			this.setBlockBounds(xInit, 0.0F, zInit, xfinal, 1.0F, zFinal);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		}

		zInit = 0.25F;
		zFinal = 0.75F;

		if (hasWest)
			xInit = 0.0F;

		if (hasEast)
			xfinal = 1.0F;

		if (hasWest || hasEast || !hasNorth && !hasSouth)
		{
			this.setBlockBounds(xInit, 0.0F, zInit, xfinal, 1.0F, zFinal);
			super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
		}

		if (hasNorth)
			zInit = 0.0F;

		if (hasSouth)
			zFinal = 1.0F;

		this.setBlockBounds(xInit, 0.0F, zInit, xfinal, 1.0F, zFinal);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		boolean hasNorth = this.canConnectTo(worldIn, pos.north());
		boolean hasSouth = this.canConnectTo(worldIn, pos.south());
		boolean hasWest = this.canConnectTo(worldIn, pos.west());
		boolean hasEast = this.canConnectTo(worldIn, pos.east());
		float xInit = 0.25F;
		float xfinal = 0.75F;
		float zInit = 0.25F;
		float zFinal = 0.75F;

		if (hasNorth)
			zInit = 0.0F;

		if (hasSouth)
			zFinal = 1.0F;

		if (hasWest)
			xInit = 0.0F;

		if (hasEast)
			xfinal = 1.0F;

		this.setBlockBounds(xInit, 0.0F, zInit, xfinal, 1.0F, zFinal);
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
	public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
	{
		Block block = worldIn.getBlockState(pos).getBlock();
		return block == DEBlockRegistry.security_wire_low;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		return true;
	}
}
