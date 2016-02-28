package rafamv.deextinction.common.block.foliage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.database.Tree;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class BlockDeExtinctedLeaves extends BlockLeaves
{
	private Foliage tree;
	private byte treeVariant;

	public BlockDeExtinctedLeaves(String treeName, byte variant)
	{
		super();
		this.setUnlocalizedName("block_" + treeName + "_" + variant + "_leaves");
		this.setDefaultState(this.blockState.getBaseState().withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
		this.treeVariant = variant;
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	public void setTree(String treeName)
	{
		this.tree = DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(treeName);
	}

	public Foliage getTreeType()
	{
		return this.tree;
	}

	public byte getTreeVariant()
	{
		return this.treeVariant;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		Item item = null;
		if (this.tree instanceof Tree)
			item = ((Tree) this.tree).getRandomLeavesFromVariant(this.RANDOM, this.treeVariant).getItem();
		return item;
	}

	@Override
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance)
	{
		if (this.getTreeType() instanceof Tree)
		{
			ItemStack specialDrop = ((Tree) this.tree).getRandomSpecialDropsFromVariant(this.RANDOM, this.treeVariant);
			if (!worldIn.isRemote && specialDrop != null && specialDrop.getItem() != null && !worldIn.restoringBlockSnapshots)
				this.spawnAsEntity(worldIn, pos, specialDrop);
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		boolean dec = meta < 4;
		boolean check = meta < 8;
		return this.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(dec)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(check));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		if (!((Boolean) state.getValue(BlockLeaves.CHECK_DECAY)).booleanValue())
			i = 4;
		if (!((Boolean) state.getValue(BlockLeaves.DECAYABLE)).booleanValue())
			i = 8;

		return i;
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BlockLeaves.DECAYABLE, BlockLeaves.CHECK_DECAY });
	}

	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
	{
		if (!worldIn.isRemote && player.getHeldItem() != null && player.getHeldItem().getItem() == Items.shears)
		{
			player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
			this.spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, 0));
		}
		else
		{
			super.harvestBlock(worldIn, player, pos, state, te);
		}
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		IBlockState state = world.getBlockState(pos);
		return new ArrayList(Arrays.asList(new ItemStack[] { new ItemStack(this, 1, 0) }));
	}

	@Override
	public BlockPlanks.EnumType getWoodType(int meta)
	{
		return BlockPlanks.EnumType.OAK;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor()
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(IBlockState state)
	{
		return 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
	{
		return 16777215;
	}
}
