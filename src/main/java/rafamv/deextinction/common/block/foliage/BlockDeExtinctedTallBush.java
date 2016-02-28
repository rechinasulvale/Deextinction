package rafamv.deextinction.common.block.foliage;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDeExtinctedTallBush extends BlockDeExtinctedBush
{
	public static final PropertyEnum HEIGHT = PropertyEnum.create("height", EnumBlockHeight.class);

	public BlockDeExtinctedTallBush(String plantName, byte Variant, float halfWidth)
	{
		super(plantName, Variant, halfWidth, 1.0F);
	}

	@Override
	public boolean isReplaceable(World worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos).getBlock() != this;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	{
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(BlockDeExtinctedTallBush.HEIGHT, BlockDeExtinctedTallBush.EnumBlockHeight.UPPER), 2);
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		if (state.getBlock() != this)
			return super.canBlockStay(worldIn, pos, state);

		if (state.getValue(BlockDeExtinctedTallBush.HEIGHT) == BlockDeExtinctedTallBush.EnumBlockHeight.UPPER)
			return worldIn.getBlockState(pos.down()).getBlock() == this;
		else
			return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.up()).getBlock() == this;
	}

	@Override
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
		{
			if (state.getValue(BlockDeExtinctedTallBush.HEIGHT) == BlockDeExtinctedTallBush.EnumBlockHeight.UPPER)
			{
				worldIn.destroyBlock(pos, false);
				BlockPos bottomBlockPos = pos.down();
				Block bottomBlock = worldIn.getBlockState(bottomBlockPos).getBlock();
				if (bottomBlock == this)
					worldIn.destroyBlock(bottomBlockPos, false);
			}
			else if (state.getValue(BlockDeExtinctedTallBush.HEIGHT) == BlockDeExtinctedTallBush.EnumBlockHeight.LOWER)
			{
				worldIn.destroyBlock(pos, false);
				BlockPos upperBlockPos = pos.up();
				Block upperBlock = worldIn.getBlockState(upperBlockPos).getBlock();
				if (upperBlock == this)
					worldIn.destroyBlock(upperBlockPos, false);
			}
		}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{

	}

	@Override
	public int getDamageValue(World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);
		return iblockstate.getBlock().getMetaFromState(iblockstate);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BlockDeExtinctedTallBush.HEIGHT, BlockDeExtinctedTallBush.EnumBlockHeight.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDeExtinctedTallBush.EnumBlockHeight) state.getValue(BlockDeExtinctedTallBush.HEIGHT)).getMetadata();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, BlockDeExtinctedTallBush.HEIGHT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType()
	{
		return Block.EnumOffsetType.NONE;
	}

	public static enum EnumBlockHeight implements IStringSerializable
	{
		LOWER(0, "lower"),
		UPPER(1, "upper");

		private EnumBlockHeight(int meta, String name)
		{
			this(meta, name, name);
		}

		private EnumBlockHeight(int meta, String name, String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName()
		{
			return this.name;
		}

		public String getUnlocalizedName()
		{
			return this.unlocalizedName;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static BlockDeExtinctedTallBush.EnumBlockHeight byMetadata(int meta)
		{
			if (meta < 0 || meta >= BlockDeExtinctedTallBush.EnumBlockHeight.META_LOOKUP.length)
				meta = 0;
			return BlockDeExtinctedTallBush.EnumBlockHeight.META_LOOKUP[meta];
		}

		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private static final BlockDeExtinctedTallBush.EnumBlockHeight[] META_LOOKUP = new BlockDeExtinctedTallBush.EnumBlockHeight[values().length];
		static
		{
			BlockDeExtinctedTallBush.EnumBlockHeight[] enumValues = values();
			int enumListSize = enumValues.length;

			for (int i = 0; i < enumListSize; i++)
			{
				BlockDeExtinctedTallBush.EnumBlockHeight enumValue = enumValues[i];
				BlockDeExtinctedTallBush.EnumBlockHeight.META_LOOKUP[enumValue.getMetadata()] = enumValue;
			}
		}
	}
}
