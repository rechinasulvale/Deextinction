package rafamv.deextinction.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Period;
import rafamv.deextinction.common.item.ItemPlasterBurlapStrips;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.tileentity.TilePlasterJacket;
import rafamv.deextinction.common.world.DEFossilHelper;

public class BlockFossilFoliage extends BlockOriented
{

	public BlockFossilFoliage(String mainBlock)
	{
		super(Material.rock);
		this.setUnlocalizedName("block_" + mainBlock + "_fossil_foliage");
		this.setHardness(0.8F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
		this.setCreativeTab(DECreativeTabs.fossils);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
			return true;
		else if (!playerIn.isSneaking())
		{
			ItemStack stack = playerIn.getHeldItem();
			if (stack != null && stack.getItem() instanceof ItemPlasterBurlapStrips)
			{
				float periodNoise = DEFossilHelper.getNoise2DForPeriods(pos.getX(), pos.getZ());
				Period period = DEFossilHelper.getPeriod(periodNoise);
				if (period != null)
				{
					float foliageNoise = DEFossilHelper.getNoise2DForFossils(pos.getX(), pos.getZ());
					Foliage foliage = DEFossilHelper.getFoliage(period, foliageNoise);
					worldIn.setBlockToAir(pos);
					worldIn.setBlockState(pos, DEBlockRegistry.plaster_jacket.getDefaultState());
					TileEntity tileEntity = worldIn.getTileEntity(pos);
					if (tileEntity instanceof TilePlasterJacket)
					{
						TilePlasterJacket plasterJacket = (TilePlasterJacket) tileEntity;
						if (foliage != null)
							plasterJacket.setFossilName(foliage.getName());
						else
							plasterJacket.setFossilName(DEFossilHelper.FOSSIL_FAILED);
					}
					if (!playerIn.capabilities.isCreativeMode)
					{
						stack.stackSize--;
						if (stack.stackSize < 1)
							playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
					}
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return false;
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion)
	{
		return false;
	}
}
