package rafamv.deextinction.common.item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class ItemDeExtinctionBucket extends ItemFluidContainer
{
	public static final Set<FluidStack> fluids = new HashSet<FluidStack>();

	public ItemDeExtinctionBucket()
	{
		super(-1, FluidContainerRegistry.BUCKET_VOLUME);
		this.setContainerItem(Items.bucket);
		this.setCreativeTab(DECreativeTabs.items);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("item_deextinction_bucket");
		this.setMaxStackSize(1);
	}

	public boolean isEmpty(ItemStack stack)
	{
		return this.getFluid(stack) == null;
	}

	public ItemStack addFluid(Fluid fluid)
	{
		FluidStack fluidStack = new FluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME);
		ItemDeExtinctionBucket.fluids.add(fluidStack);

		ItemStack filledBucket = new ItemStack(this);
		this.fill(filledBucket, fluidStack, true);

		return filledBucket;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		FluidStack fluidStack = getFluid(stack);

		if (fluidStack != null)
			return "item.item_bucket_" + fluidStack.getUnlocalizedName().substring(6);
		else
			return getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
	{
		for (FluidStack fluidStack : ItemDeExtinctionBucket.fluids)
		{
			ItemStack stack = new ItemStack(this);
			this.fill(stack, fluidStack.copy(), true);
			subItems.add(stack);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		boolean isEmpty = isEmpty(stack);
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, isEmpty);

		if (movingobjectposition == null)
			return stack;
		else
		{
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				BlockPos blockpos = movingobjectposition.getBlockPos();

				if (!world.isBlockModifiable(player, blockpos))
					return stack;

				if (!isEmpty)
				{
					BlockPos blockpos1 = blockpos.offset(movingobjectposition.sideHit);

					if (!player.canPlayerEdit(blockpos1, movingobjectposition.sideHit, stack))
						return stack;

					if (this.tryPlaceContainedLiquid(stack, world, blockpos1) && !player.capabilities.isCreativeMode)
					{
						player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
						return new ItemStack(Items.bucket);
					}
				}
			}

			return stack;
		}
	}

	public boolean tryPlaceContainedLiquid(ItemStack stack, World worldIn, BlockPos pos)
	{
		if (isEmpty(stack))
			return false;
		else
		{
			Material material = worldIn.getBlockState(pos).getBlock().getMaterial();
			boolean flag = !material.isSolid();

			if (!worldIn.isAirBlock(pos) && !flag)
				return false;
			else
			{
				if (!worldIn.isRemote && flag && !material.isLiquid())
					worldIn.destroyBlock(pos, true);

				Block block = getFluid(stack).getFluid().getBlock();
				IBlockState state = block.getDefaultState();

				if (block instanceof BlockFluidFinite)
					state = state.withProperty(BlockFluidBase.LEVEL, 7);

				worldIn.setBlockState(pos, state, 3);

				return true;
			}
		}
	}
}
