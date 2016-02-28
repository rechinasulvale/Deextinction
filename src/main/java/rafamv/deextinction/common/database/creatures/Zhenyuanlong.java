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

public class Zhenyuanlong extends EggCreature
{
	public static final String NAME = "zhenyuanlong_suni";

	public Zhenyuanlong()
	{
		super(Zhenyuanlong.NAME);
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
		return new GalleryPosition(4, 2);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 250;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.unknown_image);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.zhenyuanlong_egg;
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
		this.registerPreviousCreature(Archaeorhynchus.NAME, 50);
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
		this.registerGrowthStage((byte) 0, -72000, 4.0D, 0.5D, 0.3D, 0.14D, 1.35D, 0.2F);
		this.registerGrowthStage((byte) 1, -48000, 8.0D, 1.0D, 0.3D, 0.16D, 1.1325D, 0.3F);
		this.registerGrowthStage((byte) 2, -24000, 12.0D, 2.0D, 0.3D, 0.18D, 1.3D, 0.4F);
		this.registerGrowthStage((byte) 3, 0, 14.0D, 2.5D, 0.3D, 0.2D, 1.25D, 0.5F);
	}
}
