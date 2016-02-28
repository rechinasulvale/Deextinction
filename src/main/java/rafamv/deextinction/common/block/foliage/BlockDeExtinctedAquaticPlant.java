package rafamv.deextinction.common.block.foliage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockDeExtinctedAquaticPlant extends Block
{

	public BlockDeExtinctedAquaticPlant(String plantName, byte variant, float width, float height)
	{
		super(Material.water);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setUnlocalizedName("block_" + plantName + "_" + variant);
		this.setTickRandomly(true);
		this.setBlockBounds(0.5F - width, 0.0F, 0.5F - width, 0.5F + width, height, 0.5F + width);
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
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public boolean isReplaceable(World worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state)
	{
		return null;
	}

	protected boolean canPlaceBlockOn(Block ground)
	{
		return ground == Blocks.dirt || ground == Blocks.sand || ground == Blocks.gravel;
	}

	protected static List<IBlockState> getBlocksAround(World world, BlockPos pos)
	{
		List<IBlockState> blocks = new ArrayList<IBlockState>();
		blocks.add(world.getBlockState(pos.north()));
		blocks.add(world.getBlockState(pos.south()));
		blocks.add(world.getBlockState(pos.west()));
		blocks.add(world.getBlockState(pos.east()));
		blocks.add(world.getBlockState(pos.up()));
		return blocks;
	}

	protected void breakPlant(World world, BlockPos pos, IBlockState state)
	{
		IBlockState blockAbove = world.getBlockState(pos.up());
		if (blockAbove.getBlock() == Blocks.water)
			world.setBlockState(pos, Blocks.water.getStateFromMeta(((Integer) blockAbove.getValue(BlockLiquid.LEVEL)).intValue()), 3);
		else
			world.setBlockState(pos, Blocks.water.getStateFromMeta(0), 3);
	}

	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
			worldIn.destroyBlock(pos, true);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
	{
		super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		this.checkAndDropBlock(worldIn, pos, state);
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
		this.breakPlant(worldIn, pos, state);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return this.canBlockStay(worldIn, pos, this.getDefaultState());
	}

	public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
	{
		Block blockAbove = world.getBlockState(pos.up()).getBlock();
		Block blockDown = world.getBlockState(pos.down()).getBlock();

		if (blockAbove.getMaterial() != Material.water || !this.canPlaceBlockOn(blockDown))
			return false;

		boolean flag = false;
		List<IBlockState> blocksAround = this.getBlocksAround(world, pos);
		for (int i = 0; i < blocksAround.size(); i++)
			if (blocksAround.get(i).getBlock().getMaterial() == Material.water)
				flag = true;
		return flag;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BlockLiquid.LEVEL, 0);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer) state.getValue(BlockLiquid.LEVEL)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BlockLiquid.LEVEL });
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType()
	{
		return Block.EnumOffsetType.XYZ;
	}
}
