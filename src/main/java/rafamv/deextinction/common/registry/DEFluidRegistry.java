package rafamv.deextinction.common.registry;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.block.BlockDeExtinctionFluidClassic;
import rafamv.deextinction.common.block.DeExtinctionFluid;
import rafamv.deextinction.common.item.ItemDeExtinctionBucket;

public class DEFluidRegistry
{
	public static ItemDeExtinctionBucket bucket = new ItemDeExtinctionBucket();

	public static final DeExtinctionFluid fluid_clear_water = new DeExtinctionFluid("fluid_clear_water");
	public static BlockDeExtinctionFluidClassic block_clear_water;

	public static void preInitCommon()
	{
		DEFluidRegistry.registerBucket();

		DEFluidRegistry.registerFluid(DEFluidRegistry.fluid_clear_water);
		DEFluidRegistry.block_clear_water = DEFluidRegistry.createFluidBlock(DEFluidRegistry.fluid_clear_water, Material.water);
		DEFluidRegistry.registerFluidBlock(DEFluidRegistry.block_clear_water);
	}

	public static void initCommon()
	{

	}

	public static void postInitCommon()
	{

	}

	public static void preInitClientOnly()
	{
		DEFluidRegistry.registerFluidBlockRender(DEFluidRegistry.block_clear_water);
	}

	public static void initClientOnly()
	{
		DEFluidRegistry.registerBucketRender();
	}

	public static void postInitClientOnly()
	{

	}

	/** Registers the item that contains all buckets registed here. */
	public static void registerBucket()
	{
		String bucketUnlocalizedName = DEFluidRegistry.bucket.getUnlocalizedName().substring(5);
		GameRegistry.registerItem(DEFluidRegistry.bucket, bucketUnlocalizedName);
	}

	/** Registers a fluid. */
	public static void registerFluid(Fluid fluid)
	{
		if (!FluidRegistry.registerFluid(fluid))
			throw new IllegalStateException(String.format("Unable to register fluid %s", fluid.getID()));
	}

	/** Returns a fluid block. */
	public static BlockDeExtinctionFluidClassic createFluidBlock(Fluid fluid, Material material)
	{
		if (fluid != null)
		{
			String fluidUnlocalizedName = fluid.getUnlocalizedName().substring(6);
			return new BlockDeExtinctionFluidClassic(fluid, material, fluidUnlocalizedName);
		}
		else
			DeExtinction.logger.error("Null fluid was used to register a fluid block. THIS IS A BUG!");

		return null;
	}

	/** Registers a fluid block. */
	public static void registerFluidBlock(BlockFluidBase blockFluid)
	{
		Fluid fluid = blockFluid.getFluid();
		if (fluid != null)
		{
			String blockFluidUnlocalizedName = blockFluid.getUnlocalizedName().substring(5);
			GameRegistry.registerBlock(blockFluid, blockFluidUnlocalizedName);

			ItemStack filledBucket = DEFluidRegistry.bucket.addFluid(fluid);
			FluidContainerRegistry.registerFluidContainer(fluid, filledBucket);
		}
		else
			DeExtinction.logger.error("Fluid block with null BlockFluidBase was attempted to be registered. THIS IS A BUG!");
	}

	/**
	 * Registers a fluid block render.
	 */
	private static void registerFluidBlockRender(BlockFluidBase blockFluid)
	{
		String blockFluidUnlocalizedName = blockFluid.getUnlocalizedName().substring(5);
		final ModelResourceLocation fluidLocation = new ModelResourceLocation(DeExtinction.prependModID("block_fluids"), blockFluidUnlocalizedName);

		Item fluid = Item.getItemFromBlock(blockFluid);
		ModelBakery.addVariantName(fluid);
		ModelLoader.setCustomMeshDefinition(fluid, new ItemMeshDefinition()
		{
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack)
			{
				return fluidLocation;
			}
		});
		ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase()
		{
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state)
			{
				return fluidLocation;
			}
		});
	}

	/**
	 * Registers the bucket render.
	 */
	private static void registerBucketRender()
	{
		for (FluidStack fluidStack : DEFluidRegistry.bucket.fluids)
			ModelBakery.addVariantName(DEFluidRegistry.bucket, DeExtinction.prependModID("buckets/item_bucket_" + fluidStack.getFluid().getName()));

		int metadata = 0;
		for (FluidStack fluidStack : DEFluidRegistry.bucket.fluids)
		{
			ModelResourceLocation bucketModelResourceLocation = new ModelResourceLocation(DeExtinction.prependModID("buckets/item_bucket_" + fluidStack.getFluid().getName()), "inventory");
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(DEFluidRegistry.bucket, metadata, bucketModelResourceLocation);
			metadata++;
		}
	}
}
