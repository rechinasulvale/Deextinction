package rafamv.deextinction.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.tileentity.TileCleaningTable;

public class BlockCleaningTable extends BlockContainerOriented
{

	public BlockCleaningTable()
	{
		super(Material.iron);
		this.setUnlocalizedName("block_cleaning_table");
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeMetal);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.7F, 1.0F);
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
	public int getRenderType()
	{
		return 3;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.hasDisplayName())
		{
			TileEntity tile_entity = worldIn.getTileEntity(pos);
			if (tile_entity instanceof TileCleaningTable)
				((TileCleaningTable) tile_entity).setCustomInventoryName(stack.getDisplayName());
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tile_entity = worldIn.getTileEntity(pos);
		if (tile_entity instanceof TileCleaningTable)
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileCleaningTable) tile_entity);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
			return true;
		else if (!playerIn.isSneaking())
		{
			TileEntity tile_entity = worldIn.getTileEntity(pos);
			if (tile_entity instanceof TileCleaningTable)
			{
				if (((TileCleaningTable) tile_entity).isUseableByPlayer(playerIn))
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
		return new TileCleaningTable();
	}
}
