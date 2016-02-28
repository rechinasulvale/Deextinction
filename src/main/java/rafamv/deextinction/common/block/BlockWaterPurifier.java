package rafamv.deextinction.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEFluidRegistry;
import rafamv.deextinction.common.tileentity.TileWaterFilter;

public class BlockWaterPurifier extends BlockContainerOriented
{

	public BlockWaterPurifier()
	{
		super(Material.iron);
		this.setUnlocalizedName("block_water_purifier");
		this.setHardness(2.0F);
		this.setStepSound(Block.soundTypePiston);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube()
	{
		// TODO SHOULD BE CHANGED
		return true;
	}

	@Override
	public boolean isFullCube()
	{
		// TODO SHOULD BE CHANGED
		return true;
	}

	@Override
	public int getRenderType()
	{
		return 3;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileWaterFilter)
		{
			TileWaterFilter filter = (TileWaterFilter) tileEntity;
			if (filter.hasClearWaterNextToFilter())
				worldIn.setBlockState(filter.getPos(), DEFluidRegistry.block_clear_water.getDefaultState(), 3);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileWaterFilter)
		{
			TileWaterFilter researchStation = (TileWaterFilter) tileEntity;

			EnumFacing enumfacing = (EnumFacing) state.getValue(BlockContainerOriented.FACING);
			double posX = (double) pos.getX() + 0.5D;
			double posY = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double posZ = (double) pos.getZ() + 0.5D;

			if (worldIn.rand.nextFloat() < 0.3F && researchStation.isFiltering())
				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX, posY, posZ, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileWaterFilter();
	}
}
