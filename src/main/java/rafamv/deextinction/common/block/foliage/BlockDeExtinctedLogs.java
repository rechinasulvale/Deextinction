package rafamv.deextinction.common.block.foliage;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockDeExtinctedLogs extends BlockLog
{

	public BlockDeExtinctedLogs(String treeName, int variant)
	{
		super();
		this.setUnlocalizedName("block_" + treeName + "_" + variant + "_log");
		this.setResistance(0.5F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	public IBlockState getStateFromMeta(int meta)
	{
		IBlockState iBlockState = this.getDefaultState();
		switch (meta & 0xC)
		{
			case 0:
				iBlockState = iBlockState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
				break;
			case 4:
				iBlockState = iBlockState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.X);
				break;
			case 8:
				iBlockState = iBlockState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
				break;
			default:
				iBlockState = iBlockState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
		}

		return iBlockState;
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		switch (BlockDeExtinctedLogs.SwitchEnumAxis.AXIS_LOOKUP[((BlockLog.EnumAxis) state.getValue(BlockLog.LOG_AXIS)).ordinal()])
		{
			case 1:
				i = 4;
				break;
			case 2:
				i = 8;
				break;
			case 3:
				i = 12;
		}
		return i;
	}

	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BlockLog.LOG_AXIS });
	}

	static final class SwitchEnumAxis
	{
		static final int[] AXIS_LOOKUP = new int[BlockLog.EnumAxis.values().length];
		private static final String __OBFID = "CL_00002083";

		static
		{
			try
			{
				AXIS_LOOKUP[BlockLog.EnumAxis.X.ordinal()] = 1;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				AXIS_LOOKUP[BlockLog.EnumAxis.Z.ordinal()] = 2;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				AXIS_LOOKUP[BlockLog.EnumAxis.NONE.ordinal()] = 3;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}
}
