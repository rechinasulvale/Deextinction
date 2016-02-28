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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.registry.DEBlockRegistry;
import rafamv.deextinction.common.registry.DECreativeTabs;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.tileentity.TileGeneticResearchStation;

public class BlockGeneticResearchStation extends BlockContainerOriented
{

	public BlockGeneticResearchStation()
	{
		super(Material.iron);
		this.setUnlocalizedName("block_genetic_research_station");
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
		return EnumWorldBlockLayer.CUTOUT;
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
			if (tileEntity instanceof TileGeneticResearchStation)
			{
				if (((TileGeneticResearchStation) tileEntity).isUseableByPlayer(playerIn))
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
		if (tileEntity instanceof TileGeneticResearchStation)
		{
			TileGeneticResearchStation researchStation = (TileGeneticResearchStation) tileEntity;
			if (researchStation != null)
			{
				if (placer != null && placer instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer) placer;
					if (player.capabilities.isCreativeMode && player.isSneaking())
					{
						HashMap<String, Creature> creature_list = DEDatabaseRegistry.LIST_ALL_CREATURES;
						if (!creature_list.isEmpty())
							for (String creatureName : creature_list.keySet())
								researchStation.setCreatureProgress(creatureName, 100);
						researchStation.setCreatureSelected(DEDatabaseRegistry.LIST_ALL_CREATURES.keySet().iterator().next());
						researchStation.setResearchProgress(0);
						researchStation.setCreatingProgress(0);
						researchStation.setNutrients(0);
						researchStation.setGuiPage(0);
						researchStation.setTextureID((byte) 0);
						researchStation.setCreatureGender(true);
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

								if (compound.hasKey("Creature"))
									researchStation.setCreatureSelected(DEDatabaseRegistry.LIST_ALL_CREATURES.get(compound.getString("Creature")));

								HashMap<String, Creature> creature_list = DEDatabaseRegistry.LIST_ALL_CREATURES;
								if (!creature_list.isEmpty())
									for (String creatureName : creature_list.keySet())
										if (compound.hasKey("CreatureProgress_" + creatureName))
											researchStation.setCreatureProgress(creatureName, compound.getInteger("CreatureProgress_" + creatureName));

								if (compound.hasKey("ResearchProgress"))
									researchStation.setResearchProgress(compound.getByte("ResearchProgress"));

								if (compound.hasKey("CreatingProgress"))
									researchStation.setCreatingProgress(compound.getByte("CreatingProgress"));

								if (compound.hasKey("Nutrients"))
									researchStation.setNutrients(compound.getShort("Nutrients"));

								if (compound.hasKey("GuiPage"))
									researchStation.setGuiPage(compound.getByte("GuiPage"));

								if (compound.hasKey("Texture"))
									researchStation.setTextureID(compound.getByte("Texture"));

								if (compound.hasKey("Gender"))
									researchStation.setCreatureGender(compound.getBoolean("Gender"));
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
		if (tileEntity instanceof TileGeneticResearchStation)
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileGeneticResearchStation) tileEntity);
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
		if (tileEntity instanceof TileGeneticResearchStation)
			drops.add(this.getThisBlockWithTag((TileGeneticResearchStation) tileEntity, pos));
		return drops;
	}

	private ItemStack getThisBlockWithTag(TileGeneticResearchStation researchStation, BlockPos pos)
	{
		ItemStack stack = new ItemStack(DEBlockRegistry.genetic_research_station);
		if (researchStation != null)
		{
			NBTTagCompound compound = new NBTTagCompound();
			compound.setByte("ResearchProgress", (byte) researchStation.getResearchProgress());
			compound.setByte("CreatingProgress", (byte) researchStation.getCreatingProgress());
			compound.setShort("Nutrients", (short) researchStation.getNutrients());
			compound.setByte("GuiPage", researchStation.getGuiPage());
			compound.setByte("Texture", researchStation.getCreatureTextureID());
			compound.setBoolean("Gender", researchStation.getCreatureGender());

			if (researchStation.hasCustomName())
				compound.setString("CustomName", researchStation.getCustomName());

			if (researchStation.getCreatureSelected() != null)
				compound.setString("Creature", String.valueOf(researchStation.getCreatureSelected().getName()));

			HashMap<String, Integer> progressList = researchStation.getCreatureProgressList();
			if (!progressList.isEmpty())
				for (String creatureName : progressList.keySet())
					compound.setInteger("CreatureProgress_" + creatureName, progressList.get(creatureName));

			stack.setTagCompound(compound);
		}
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if (tileEntity instanceof TileGeneticResearchStation)
		{
			TileGeneticResearchStation researchStation = (TileGeneticResearchStation) tileEntity;

			EnumFacing enumfacing = (EnumFacing) state.getValue(BlockContainerOriented.FACING);
			double posX = (double) pos.getX() + 0.5D;
			double posY = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
			double posZ = (double) pos.getZ() + 0.5D;
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;

			if (worldIn.rand.nextFloat() < 0.3F)
			{
				if (researchStation.isResearching() || researchStation.isCreating())
				{
					switch (BlockContainerOriented.SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()])
					{
						case 1:
							worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX, posY, posZ + 0.3D, 0.0D, 0.0D, 0.0D, new int[0]);
							break;
						case 2:
							worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX + 0.15D, posY, posZ - 0.15D, 0.0D, 0.0D, 0.0D, new int[0]);
							break;
						case 3:
							worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX - 0.15D, posY, posZ, 0.0D, 0.0D, 0.0D, new int[0]);
							break;
						case 4:
							worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, posX + 0.25D, posY, posZ + 0.15D, 0.0D, 0.0D, 0.0D, new int[0]);
					}
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileGeneticResearchStation();
	}
}
