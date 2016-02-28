package rafamv.deextinction.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.tileentity.TilePlasterJacket;

public class BlockPlasterJacket extends Block implements ITileEntityProvider
{

	public BlockPlasterJacket()
	{
		super(Material.rock);
		this.setUnlocalizedName("block_plaster_jacket");
		this.setHardness(1.25F);
		this.setResistance(25.0F);
		this.setStepSound(Block.soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TilePlasterJacket)
		{
			TilePlasterJacket plasterJacket = (TilePlasterJacket) tileEntity;
			if (plasterJacket != null)
			{
				if (stack.hasTagCompound())
				{
					NBTTagCompound compound = stack.getTagCompound();
					if (compound != null && compound.hasKey("FossilName"))
						plasterJacket.setFossilName(compound.getString("FossilName"));
				}
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if (willHarvest)
			return true;
		return super.removedByPlayer(world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tileEntity)
	{
		super.harvestBlock(world, player, pos, state, tileEntity);
		world.setBlockToAir(pos);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> drops = super.getDrops(worldIn, pos, state, fortune);

		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TilePlasterJacket)
			drops.add(this.getThisBlockWithTag((TilePlasterJacket) tileEntity, pos));
		return drops;
	}

	private ItemStack getThisBlockWithTag(TilePlasterJacket plasterJacket, BlockPos pos)
	{
		ItemStack stack = new ItemStack(DEBlockRegistry.plaster_jacket);
		if (plasterJacket != null)
		{
			NBTTagCompound compound = new NBTTagCompound();
			if (plasterJacket.hasFossilName())
				compound.setString("FossilName", plasterJacket.getFossilName());
			stack.setTagCompound(compound);
		}
		return stack;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TilePlasterJacket();
	}
}
