package rafamv.deextinction.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.tileentity.TileMicroscope;

public class BlockMicroscope extends BlockContainerOriented
{

	public BlockMicroscope()
	{
		super(Material.iron);
		this.setUnlocalizedName("block_microscope");
		this.setHardness(2.5F);
		this.setLightLevel(0.25F);
		this.setLightOpacity(200);
		this.setStepSound(Block.soundTypeMetal);
		this.setBlockBounds(0.2F, 0.0F, 0.20F, 0.8F, 0.65F, 0.8F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH));
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(DEBlockRegistry.microscope);
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasDisplayName())
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileMicroscope)
				((TileMicroscope) tileEntity).setCustomInventoryName(stack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileMicroscope)
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileMicroscope) tileEntity);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
			return true;
		else if (!playerIn.isSneaking())
		{
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileMicroscope)
			{
				if (((TileMicroscope) tileEntity).isUseableByPlayer(playerIn))
				{
					playerIn.openGui(DeExtinction.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileMicroscope();
	}
}
