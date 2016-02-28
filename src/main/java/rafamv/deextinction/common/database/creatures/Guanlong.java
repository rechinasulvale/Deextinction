package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.model.ModelEggLarge;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.periods.Jurassic;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Guanlong extends EggCreature
{
	public static final String NAME = "guanlong_wucaii";

	public Guanlong()
	{
		super(Guanlong.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggLarge();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_large_default_texture.png");
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
		return new GalleryPosition(4, 6);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 400;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.unknown_image);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.guanlong_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.unknown_image, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Jurassic.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Dilong.NAME, 50);
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
		this.registerGrowthStage((byte) 0, -72000, 7.0D, 5.5D, 0.3D, 0.15D, 1.3D, 0.2F);
		this.registerGrowthStage((byte) 1, -60000, 12.0D, 1.0D, 0.3D, 0.2D, 1.3D, 0.3F);
		this.registerGrowthStage((byte) 2, -48000, 14.0D, 2.5D, 0.3D, 0.3D, 1.25D, 0.4F);
		this.registerGrowthStage((byte) 3, -36000, 16.0D, 4.0D, 0.3D, 0.4D, 1.25D, 0.5F);
		this.registerGrowthStage((byte) 4, -24000, 18.0D, 5.0D, 0.3D, 0.5D, 1.20D, 0.6F);
		this.registerGrowthStage((byte) 5, -12000, 21.0D, 6.0D, 0.3D, 0.6D, 1.15D, 0.7F);
		this.registerGrowthStage((byte) 6, 0, 24.0D, 7.0D, 0.3D, 0.7D, 1.10D, 0.8F);
	}
}
