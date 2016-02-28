package rafamv.deextinction.common.block.foliage;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDeExtinctedVeryTallBush extends BlockDeExtinctedBush
{
	public static final PropertyEnum HEIGHT = PropertyEnum.create("height", EnumBlockHeight.class);

	public BlockDeExtinctedVeryTallBush(String plantName, byte Variant, float halfWidth)
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
		return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up()) && worldIn.isAirBlock(pos.up(2));
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBush.HEIGHT, BlockDeExtinctedVeryTallBush.EnumBlockHeight.MIDDLE), 2);
		worldIn.setBlockState(pos.up(2), this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBush.HEIGHT, BlockDeExtinctedVeryTallBush.EnumBlockHeight.UPPER), 2);
	}

	@Override
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
	{
		if (state.getBlock() != this)
			return super.canBlockStay(worldIn, pos, state);

		Comparable height = state.getValue(BlockDeExtinctedVeryTallBush.HEIGHT);

		if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.UPPER)
			return worldIn.getBlockState(pos.down()).getBlock() == this;
		else if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.MIDDLE)
			return worldIn.getBlockState(pos.down()).getBlock() == this && worldIn.getBlockState(pos.up()).getBlock() == this;
		else if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.LOWER)
			return super.canPlaceBlockAt(worldIn, pos) && worldIn.getBlockState(pos.up()).getBlock() == this;

		return super.canBlockStay(worldIn, pos, state);
	}

	@Override
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!this.canBlockStay(worldIn, pos, state))
		{
			Comparable height = state.getValue(BlockDeExtinctedVeryTallBush.HEIGHT);
			if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.UPPER)
			{
				worldIn.destroyBlock(pos, false);

				BlockPos middleBlockPos = pos.down();
				BlockPos lowerBlockPos = pos.down(2);
				Block middleBlock = worldIn.getBlockState(middleBlockPos).getBlock();
				Block lowerBlock = worldIn.getBlockState(lowerBlockPos).getBlock();

				if (middleBlock == this)
					worldIn.destroyBlock(middleBlockPos, false);
				if (lowerBlock == this)
					worldIn.destroyBlock(lowerBlockPos, false);
			}
			else if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.MIDDLE)
			{
				worldIn.destroyBlock(pos, false);

				BlockPos upperBlockPos = pos.up();
				BlockPos lowerBlockPos = pos.down();
				Block upperBlock = worldIn.getBlockState(upperBlockPos).getBlock();
				Block lowerBlock = worldIn.getBlockState(lowerBlockPos).getBlock();

				if (upperBlock == this)
					worldIn.destroyBlock(upperBlockPos, false);
				if (lowerBlock == this)
					worldIn.destroyBlock(lowerBlockPos, false);
			}
			else if (height == BlockDeExtinctedVeryTallBush.EnumBlockHeight.LOWER)
			{
				worldIn.destroyBlock(pos, false);

				BlockPos upperBlockPos = pos.up(2);
				BlockPos middleBlockPos = pos.up();
				Block upperBlock = worldIn.getBlockState(upperBlockPos).getBlock();
				Block middleBlock = worldIn.getBlockState(middleBlockPos).getBlock();

				if (upperBlock == this)
					worldIn.destroyBlock(upperBlockPos, false);
				if (middleBlock == this)
					worldIn.destroyBlock(middleBlockPos, false);
			}
		}
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
		return this.getDefaultState().withProperty(BlockDeExtinctedVeryTallBush.HEIGHT, BlockDeExtinctedVeryTallBush.EnumBlockHeight.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockDeExtinctedVeryTallBush.EnumBlockHeight) state.getValue(BlockDeExtinctedVeryTallBush.HEIGHT)).getMetadata();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, BlockDeExtinctedVeryTallBush.HEIGHT);
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
		MIDDLE(1, "middle"),
		UPPER(2, "upper");

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

		public static BlockDeExtinctedVeryTallBush.EnumBlockHeight byMetadata(int meta)
		{
			if (meta < 0 || meta >= BlockDeExtinctedVeryTallBush.EnumBlockHeight.META_LOOKUP.length)
				meta = 0;
			return BlockDeExtinctedVeryTallBush.EnumBlockHeight.META_LOOKUP[meta];
		}

		private final int meta;
		private final String name;
		private final String unlocalizedName;
		private static final BlockDeExtinctedVeryTallBush.EnumBlockHeight[] META_LOOKUP = new BlockDeExtinctedVeryTallBush.EnumBlockHeight[values().length];
		static
		{
			BlockDeExtinctedVeryTallBush.EnumBlockHeight[] enumValues = values();
			int enumListSize = enumValues.length;

			for (int i = 0; i < enumListSize; i++)
			{
				BlockDeExtinctedVeryTallBush.EnumBlockHeight enumValue = enumValues[i];
				BlockDeExtinctedVeryTallBush.EnumBlockHeight.META_LOOKUP[enumValue.getMetadata()] = enumValue;
			}
		}
	}
}
