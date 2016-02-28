package rafamv.deextinction.common.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DEFluidRegistry;

public class TileWaterFilter extends TileEntity implements IUpdatePlayerListBox
{
	private static final int FILTERING_PROGRESS_MAX = 10;
	private static final int FILTERING_RADIUS_MAX = 6;
	private int filteringProgress = 0;
	private int filteringRadius = 1;

	public TileWaterFilter()
	{

	}

	public void setFilteringProgress(int filteringProgress)
	{
		this.filteringProgress = filteringProgress;
	}

	public int getFilteringProgress()
	{
		return this.filteringProgress;
	}

	public int getFilteringProgressScaled(int scale)
	{
		return scale * this.filteringProgress / TileWaterFilter.FILTERING_PROGRESS_MAX;
	}

	public boolean isFiltering()
	{
		return this.filteringProgress > 0;
	}

	public void setFilteringRadius(int filteringRadius)
	{
		this.filteringRadius = filteringRadius;
	}

	public int getFilteringRadius()
	{
		return this.filteringRadius;
	}

	public boolean isWaterMaterial(BlockPos pos)
	{
		return this.worldObj.getBlockState(pos).getBlock().getMaterial() == Material.water;
	}

	public boolean isWater(BlockPos pos)
	{
		return this.worldObj.getBlockState(pos).getBlock() == Blocks.water;
	}

	public boolean isClearWater(BlockPos pos)
	{
		return this.worldObj.getBlockState(pos).getBlock() == DEFluidRegistry.block_clear_water;
	}

	public boolean isWaterFilter(BlockPos pos)
	{
		return this.worldObj.getBlockState(pos).getBlock() == DEBlockRegistry.water_filter;
	}

	public boolean hasWaterFilterNextTo(BlockPos pos)
	{
		return this.isWaterFilter(pos.north()) || this.isWaterFilter(pos.south()) || this.isWaterFilter(pos.east()) || this.isWaterFilter(pos.west()) || this.isWaterFilter(pos.up()) || this.isWaterFilter(pos.down());
	}

	public boolean hasAnyWaterNextTo(BlockPos pos)
	{
		return this.isWaterMaterial(pos.north()) || this.isWaterMaterial(pos.south()) || this.isWaterMaterial(pos.east()) || this.isWaterMaterial(pos.west()) || this.isWaterMaterial(pos.up()) || this.isWaterMaterial(pos.down());
	}

	public boolean hasWaterNextTo(BlockPos pos)
	{
		return this.isWater(pos.north()) || this.isWater(pos.south()) || this.isWater(pos.east()) || this.isWater(pos.west()) || this.isWater(pos.up()) || this.isWater(pos.down());
	}

	public boolean hasWaterNextToFilter()
	{
		return this.isWater(pos.north()) || this.isWater(pos.south()) || this.isWater(pos.east()) || this.isWater(pos.west()) || this.isWater(pos.up()) || this.isWater(pos.down());
	}

	public boolean hasClearWaterNextTo(BlockPos pos)
	{
		return this.isClearWater(pos.north()) || this.isClearWater(pos.south()) || this.isClearWater(pos.east()) || this.isClearWater(pos.west()) || this.isClearWater(pos.up()) || this.isClearWater(pos.down());
	}

	public boolean hasClearWaterNextToFilter()
	{
		return this.isClearWater(pos.north()) || this.isClearWater(pos.south()) || this.isClearWater(pos.east()) || this.isClearWater(pos.west()) || this.isClearWater(pos.up()) || this.isClearWater(pos.down());
	}

	protected boolean filterPos(BlockPos pos)
	{
		if (this.worldObj.getBlockState(pos).getBlock() == Blocks.water && (this.hasClearWaterNextTo(pos) || this.hasWaterFilterNextTo(pos)))
		{
			this.worldObj.setBlockState(pos, DEFluidRegistry.block_clear_water.getDefaultState(), 2);
			return true;
		}
		return false;
	}

	@Override
	public void update()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.worldObj.isBlockPowered(this.pos))
			{
				if (this.filteringRadius <= this.FILTERING_RADIUS_MAX && (this.hasClearWaterNextToFilter() || this.hasWaterNextToFilter()))
				{
					if (this.filteringProgress++ > TileWaterFilter.FILTERING_PROGRESS_MAX)
					{
						if (!this.filter(this.filteringRadius))
						{
							if (this.filteringRadius <= this.FILTERING_RADIUS_MAX)
								this.filteringRadius++;
						}
						else
							this.filteringProgress = 0;
					}
				}
				else if (this.worldObj.rand.nextInt(100) == 0 && (this.hasClearWaterNextToFilter() || this.hasWaterNextToFilter()))
				{
					this.filteringProgress = 0;
					this.filteringRadius = 1;
				}
			}
			else
			{
				this.filteringProgress = 0;
				this.filteringRadius = 1;
			}
		}
	}

	protected boolean canFilter()
	{
		return false;
	}

	protected boolean filter(int radius)
	{
		boolean flag = false;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if (this.filterPos(this.pos.add(offsetX, offsetY, offsetZ)))
						return true;
				}
			}
		}
		return false;
	}

	protected boolean filterSphere(int radius)
	{
		boolean flag = false;
		double radius_sq = radius * radius;
		for (int offsetX = -radius; offsetX <= radius; offsetX++)
		{
			for (int offsetY = -radius; offsetY <= radius; offsetY++)
			{
				for (int offsetZ = -radius; offsetZ <= radius; offsetZ++)
				{
					if (offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ <= radius_sq)
					{
						if (this.filterPos(this.pos.add(offsetX, offsetY, offsetZ)))
							return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setByte("FilteringProgress", (byte) this.filteringProgress);
		compound.setByte("FilteringRadius", (byte) this.filteringRadius);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("FilteringProgress"))
			this.filteringProgress = compound.getByte("FilteringProgress");

		if (compound.hasKey("FilteringRadius"))
			this.filteringRadius = compound.getByte("FilteringRadius");
	}
}
