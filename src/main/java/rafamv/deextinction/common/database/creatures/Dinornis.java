package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.model.ModelEggLarge;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.periods.Neogene;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.entity.creature.EntityDinornis;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Dinornis extends EggCreature
{
	public static final String NAME = "dinornis_novaezealandiae";

	public Dinornis()
	{
		super(Dinornis.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggLarge();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_dinornis_novaezealandiae.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityDinornis(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, -1);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 450;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.dinornis_fossilized_feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.dinornis_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.dinornis_fossilized_feather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Quaternary.NAME);
		this.registerCreaturePeriod(Neogene.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Chicken.NAME, 75);
	}

	@Override
	public void initMaleCreatureTextures()
	{
		this.registerMaleTexture((byte) 0, (byte) 50);
		this.registerMaleTexture((byte) 1, (byte) 75);
	}

	@Override
	public void initFemaleCreatureTextures()
	{
		this.registerFemaleTexture((byte) 0, (byte) 50);
		this.registerFemaleTexture((byte) 1, (byte) 75);
	}

	@Override
	public void initGrowthStages()
	{
		this.registerGrowthStage((byte) 0, -72000, 6.0D, 0.0D, 0.3D, 0.2D, 1.2D, 0.2F);
		this.registerGrowthStage((byte) 1, -60000, 8.0D, 1.0D, 0.3D, 0.25D, 1.2D, 0.23F);
		this.registerGrowthStage((byte) 2, -48000, 12.0D, 2.0D, 0.3D, 0.35D, 1.1D, 0.35F);
		this.registerGrowthStage((byte) 3, -36000, 16.0D, 3.0D, 0.3D, 0.45D, 1.1D, 0.42F);
		this.registerGrowthStage((byte) 4, -24000, 20.0D, 4.0D, 0.3D, 0.5D, 1.1D, 0.5F);
		this.registerGrowthStage((byte) 5, -12000, 22.0D, 5.0D, 0.3D, 0.55D, 1.05D, 0.6F);
		this.registerGrowthStage((byte) 6, 0, 25.0D, 6.0D, 0.3D, 0.6D, 1.0D, 0.7F);
	}
}
