package rafamv.deextinction.common.block;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.Foliage;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.tileentity.TileBotanicalResearchStation;

public class BlockBotanicalResearchStation extends BlockContainerOriented
{

	public BlockBotanicalResearchStation()
	{
		super(Material.iron);
		this.setUnlocalizedName("block_botanical_research_station");
		this.setHardness(0.6F);
		this.setResistance(1.0F);
		this.setLightLevel(0.2F);
		this.setLightOpacity(200);
		this.setStepSound(Block.soundTypePiston);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
		this.setCreativeTab(DECreativeTabs.blocks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.TRANSLUCENT;
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
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileBotanicalResearchStation)
			{
				if (((TileBotanicalResearchStation) tileEntity).isUseableByPlayer(playerIn))
				{
					playerIn.openGui(DeExtinction.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileBotanicalResearchStation)
		{
			TileBotanicalResearchStation researchStation = (TileBotanicalResearchStation) tileEntity;
			if (researchStation != null)
			{
				if (placer != null && placer instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) placer;
					if (player.capabilities.isCreativeMode && player.isSneaking())
					{
						HashMap<String, Foliage> foliage_list = DEDatabaseRegistry.LIST_ALL_FOLIAGE;
						if (!foliage_list.isEmpty())
							for (String foliageName : foliage_list.keySet())
								researchStation.setFoliageProgress(foliageName, 100);
						researchStation.setFoliageSelected(DEDatabaseRegistry.LIST_ALL_FOLIAGE.keySet().iterator().next());
						researchStation.setResearchProgress(0);
						researchStation.setCreatingProgress(0);
						researchStation.setNutrients(0);
						researchStation.setGuiPage(0);
						researchStation.setFoliageVariantID((byte) 0);
					}
					else
					{
						if (stack.hasTagCompound())
						{
							NBTTagCompound compound = stack.getTagCompound();
							if (compound != null)
							{
								if (compound.hasKey("CustomName"))
									researchStation.setCustomInventoryName(compound.getString("CustomName"));

								HashMap<String, Foliage> foliage_list = DEDatabaseRegistry.LIST_ALL_FOLIAGE;
								if (!foliage_list.isEmpty())
									for (String foliageName : foliage_list.keySet())
										if (compound.hasKey("FoliageProgress_" + foliageName))
											researchStation.setFoliageProgress(foliageName, compound.getInteger("FoliageProgress_" + foliageName));

								if (compound.hasKey("Foliage"))
									researchStation.setFoliageSelected(DEDatabaseRegistry.LIST_ALL_FOLIAGE.get(compound.getString("Foliage")));

								if (compound.hasKey("ResearchProgress"))
									researchStation.setResearchProgress(compound.getByte("ResearchProgress"));

								if (compound.hasKey("CreatingProgress"))
									researchStation.setCreatingProgress(compound.getByte("CreatingProgress"));

								if (compound.hasKey("Nutrients"))
									researchStation.setNutrients(compound.getShort("Nutrients"));

								if (compound.hasKey("GuiPage"))
									researchStation.setGuiPage(compound.getByte("GuiPage"));

								if (compound.hasKey("Variant"))
									researchStation.setFoliageVariantID(compound.getByte("Variant"));
							}
						}
					}
				}
				if (stack.hasDisplayName())
					researchStation.setCustomInventoryName(stack.getDisplayName());
			}
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileBotanicalResearchStation)
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileBotanicalResearchStation) tileEntity);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if (willHarvest)
			return true;
		return super.removedByPlayer(world, pos, player, willHarvest);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te)
	{
		super.harvestBlock(world, player, pos, state, te);
		world.setBlockToAir(pos);
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune)
	{
		List<ItemStack> drops = super.getDrops(worldIn, pos, state, fortune);

		TileEntity tileEntity = worldIn.getTileEntity(pos);
		if (tileEntity instanceof TileBotanicalResearchStation)
			drops.add(this.getThisBlockWithTag((TileBotanicalResearchStation) tileEntity, pos));
		return drops;
	}

	private ItemStack getThisBlockWithTag(TileBotanicalResearchStation researchStation, BlockPos pos)
	{
		ItemStack stack = new ItemStack(DEBlockRegistry.botanical_research_station);
		if (researchStation != null)
		{
			NBTTagCompound compound = new NBTTagCompound();
			compound.setByte("ResearchProgress", (byte) researchStation.getResearchProgress());
			compound.setByte("CreatingProgress", (byte) researchStation.getCreatingProgress());
			compound.setShort("Nutrients", (short) researchStation.getNutrients());
			compound.setByte("GuiPage", researchStation.getGuiPage());
			compound.setByte("Variant", researchStation.getFoliageVariantID());

			if (researchStation.hasCustomName())
				compound.setString("CustomName", researchStation.getCustomName());

			if (researchStation.getFoliageSelected() != null)
				compound.setString("Foliage", String.valueOf(researchStation.getFoliageSelected().getName()));

			HashMap<String, Integer> progressList = researchStation.getFoliageProgressList();
			if (!progressList.isEmpty())
				for (String foliageName : progressList.keySet())
					compound.setInteger("FoliageProgress_" + foliageName, progressList.get(foliageName));

			stack.setTagCompound(compound);
		}
		return stack;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileBotanicalResearchStation();
	}
}
