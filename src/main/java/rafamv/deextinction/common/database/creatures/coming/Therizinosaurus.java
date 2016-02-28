package rafamv.deextinction.common.database.creatures.coming;

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

public class Therizinosaurus extends EggCreature
{
	public static final String NAME = "therizinosaurus_cheloniformis";

	public Therizinosaurus()
	{
		super(Therizinosaurus.NAME);
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
		return new GalleryPosition(4, 4);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 9999;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.unknown_image);
	}

	@Override
	public Item getHatchItem()
	{
		return Items.egg;
	}

	@Override
	public void initFossilItems()
	{

	}

	@Override
	public void initCreaturePeriods()
	{

	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Nothronychus.NAME, 101);
	}

	@Override
	public void initMaleCreatureTextures()
	{

	}

	@Override
	public void initFemaleCreatureTextures()
	{

	}

	@Override
	public void initGrowthStages()
	{

	}
}
