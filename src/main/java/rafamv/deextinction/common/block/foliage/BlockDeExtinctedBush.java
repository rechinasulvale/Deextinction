package rafamv.deextinction.common.block.foliage;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockDeExtinctedBush extends Block
{

	public BlockDeExtinctedBush(String plantName, byte variant, float halfWidth, float height)
	{
		super(Material.plants);
		this.setHardness(0.0F);
		this.setResistance(0.0F);
		this.setTickRandomly(true);
		this.setStepSound(Block.soundTypeGrass);
		this.setUnlocalizedName("block_" + plantName + "_" + variant);
		this.setBlockBounds(0.5F - halfWidth, 0.0F, 0.5F - halfWidth, 0.5F + halfWidth, height, 0.5F + halfWidth);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return this.canPlaceBlockOn(worldIn.getBlockState(pos.down()).getBlock());
	}

	protected boolean canPlaceBlockOn(Block ground)
	{
		return ground == Blocks.grass || ground == Blocks.dirt;
	}

	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		BlockPos below = pos.down();
		Block blockBelow = worldIn.getBlockState(below).getBlock();

		if (state.getBlock() != this)
			return this.canPlaceBlockOn(blockBelow);

		return this.canPlaceBlockAt(worldIn, pos);
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
			worldIn.destroyBlock(pos, false);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		this.checkAndDropBlock(worldIn, pos, state);
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
		return Block.EnumOffsetType.XYZ;
	}
}
