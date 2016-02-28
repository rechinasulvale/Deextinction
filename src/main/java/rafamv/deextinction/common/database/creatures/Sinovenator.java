package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.model.ModelEggLong;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.periods.Cretaceous;
import rafamv.deextinction.common.entity.creature.EntitySinovenator;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Sinovenator extends EggCreature
{
	public static final String NAME = "sinovenator_changii";

	public Sinovenator()
	{
		super(Sinovenator.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggLong();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_sinovenator_changii.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntitySinovenator(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(4, 1);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 300;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.sinovenator_fossilized_feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.sinovenator_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.sinovenator_fossilized_feather, 6);
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
		this.registerGrowthStage((byte) 0, -72000, 4.0D, 0.5D, 0.3D, 0.14D, 1.4D, 0.2F);
		this.registerGrowthStage((byte) 1, -48000, 8.0D, 1.0D, 0.3D, 0.16D, 1.135D, 0.26F);
		this.registerGrowthStage((byte) 2, -24000, 12.0D, 2.0D, 0.3D, 0.18D, 1.3D, 0.3F);
		this.registerGrowthStage((byte) 3, 0, 16.0D, 3.0D, 0.3D, 0.2D, 1.25D, 0.35F);
	}
}
