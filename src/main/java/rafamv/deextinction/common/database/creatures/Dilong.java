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
import rafamv.deextinction.client.renderer.model.ModelEggMedium;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.periods.Cretaceous;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Dilong extends EggCreature
{
	public static final String NAME = "dilong_paradoxus";

	public Dilong()
	{
		super(Dilong.NAME);
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
		return new GalleryPosition(3, 6);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 280;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.unknown_image);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.dilong_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.unknown_image, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Cretaceous.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Citipati.NAME, 100);
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
		this.registerGrowthStage((byte) 0, -60000, 6.0D, 1.0D, 0.3D, 0.15D, 1.4D, 0.2F);
		this.registerGrowthStage((byte) 1, -48000, 10.0D, 2.0D, 0.3D, 0.2D, 1.135D, 0.28F);
		this.registerGrowthStage((byte) 2, -36000, 13.0D, 3.0D, 0.3D, 0.3D, 1.13D, 0.34F);
		this.registerGrowthStage((byte) 3, -24000, 16.0D, 4.0D, 0.3D, 0.4D, 1.13D, 0.42F);
		this.registerGrowthStage((byte) 4, -12000, 18.0D, 5.0D, 0.3D, 0.5D, 1.25D, 0.5F);
		this.registerGrowthStage((byte) 5, 0, 20.0D, 5.5D, 0.3D, 0.6D, 1.2D, 0.58F);
	}
}
