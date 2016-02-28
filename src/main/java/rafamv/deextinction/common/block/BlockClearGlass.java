package rafamv.deextinction.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockClearGlass extends Block
{

	public BlockClearGlass()
	{
		super(Material.glass);
		this.setHardness(0.3F);
		this.setStepSound(Block.soundTypeGlass);
		this.setUnlocalizedName("block_clear_glass");
		this.setCreativeTab(DECreativeTabs.blocks);
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
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 0;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();

		if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
			return true;

		if (block == this)
			return false;

		return super.shouldSideBeRendered(worldIn, pos, side);
	}
}
