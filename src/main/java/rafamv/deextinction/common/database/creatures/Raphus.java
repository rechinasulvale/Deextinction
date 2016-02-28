package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.model.ModelEggMedium;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.entity.creature.EntityRaphus;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Raphus extends EggCreature
{
	public static final String NAME = "raphus_cucullatus";

	public Raphus()
	{
		super(Raphus.NAME);
	}

	@Override
	public float getSpawnWeight()
	{
		return 1.5F;
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggMedium();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_raphus_cucullatus.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityRaphus(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, 0);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 150;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.raphus_fossilized_feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.raphus_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.raphus_fossilized_feather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Quaternary.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Chicken.NAME);
	}

	@Override
	public void initMaleCreatureTextures()
	{
		this.registerMaleTexture((byte) 0, (byte) 50);
		this.registerMaleTexture((byte) 1, (byte) 60);
		this.registerMaleTexture((byte) 2, (byte) 80);
	}

	@Override
	public void initFemaleCreatureTextures()
	{
		this.registerFemaleTexture((byte) 0, (byte) 50);
		this.registerFemaleTexture((byte) 1, (byte) 60);
		this.registerFemaleTexture((byte) 2, (byte) 80);
	}

	@Override
	public void initGrowthStages()
	{
		this.registerGrowthStage((byte) 0, -24000, 5.0D, 0.0D, 0.3D, 0.20D, 1.2D, 0.35F);
		this.registerGrowthStage((byte) 1, -20000, 8.0D, 0.0D, 0.3D, 0.25D, 1.2D, 0.4F);
		this.registerGrowthStage((byte) 2, -12000, 10.0D, 0.0D, 0.3D, 0.32D, 1.1D, 0.45F);
		this.registerGrowthStage((byte) 3, -6000, 12.0D, 0.0D, 0.3D, 0.37D, 1.0D, 0.5F);
		this.registerGrowthStage((byte) 4, 0, 14.0D, 0.0D, 0.3D, 0.4D, 0.9D, 0.6F);
	}
}
