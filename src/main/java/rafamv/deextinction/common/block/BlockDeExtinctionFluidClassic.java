package rafamv.deextinction.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockDeExtinctionFluidClassic extends BlockFluidClassic
{

	public BlockDeExtinctionFluidClassic(Fluid fluid, Material material, String fluidName)
	{
		super(fluid, material);
		this.setUnlocalizedName("block_" + fluidName);
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid())
			return false;

		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos)
	{
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid())
			return false;

		return super.displaceIfPossible(world, pos);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		Block block = world.getBlockState(pos).getBlock();
		if (block.getMaterial() == this.blockMaterial)
		{
			return false;
		}
		if (densityDir == -1 && side == EnumFacing.UP)
		{
			return true;
		}
		if (densityDir == 1 && side == EnumFacing.DOWN)
		{
			return true;
		}
		return super.shouldSideBeRendered(world, pos, side);
	}
}
