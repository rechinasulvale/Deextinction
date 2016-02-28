package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.model.ModelEggMedium;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Chicken extends EggCreature
{
	public static final String NAME = "chicken";

	public Chicken()
	{
		super(Chicken.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggMedium();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_medium_default_texture.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityChicken(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(0, 0);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 100;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(Items.feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.cultivated_chicken_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(Items.chicken, 10);
		this.registerFossilItem(Items.feather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{

	}

	@Override
	public void initPreviousCreatures()
	{

	}

	@Override
	public void initMaleCreatureTextures()
	{
		this.registerMaleTexture((byte) 0, (byte) 50);
	}

	@Override
	public void initFemaleCreatureTextures()
	{
		this.registerFemaleTexture((byte) 0, (byte) 50);
	}

	@Override
	public void initGrowthStages()
	{
		this.registerGrowthStage((byte) 0, -24000, 4.0D, 0.0D, 0.25D, 0.2D, 1.0D, 0.5F);
		this.registerGrowthStage((byte) 1, 0, 4.0D, 0.0D, 0.25D, 0.2D, 1.0D, 1.0F);
	}
}
