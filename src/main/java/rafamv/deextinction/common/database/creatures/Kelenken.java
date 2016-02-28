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
import rafamv.deextinction.common.database.periods.Neogene;
import rafamv.deextinction.common.entity.creature.EntityKelenken;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Kelenken extends EggCreature
{
	public static final String NAME = "kelenken_guillermoi";

	public Kelenken()
	{
		super(Kelenken.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggMedium();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_kelenken_guillermoi.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityKelenken(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(1, 1);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 350;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.kelenken_fossilized_feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.kelenken_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.kelenken_fossilized_feather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
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
		this.registerMaleTexture((byte) 2, (byte) 75);
		this.registerMaleTexture((byte) 3, (byte) 100);
	}

	@Override
	public void initFemaleCreatureTextures()
	{
		this.registerFemaleTexture((byte) 0, (byte) 50);
		this.registerFemaleTexture((byte) 1, (byte) 75);
		this.registerFemaleTexture((byte) 2, (byte) 75);
		this.registerFemaleTexture((byte) 3, (byte) 100);
	}

	@Override
	public void initGrowthStages()
	{
		this.registerGrowthStage((byte) 0, -3000, 5.0D, 1.0D, 0.28D, 0.3D, 1.2D, 0.4F);
		this.registerGrowthStage((byte) 1, -2000, 10.0D, 2.0D, 0.28D, 0.3D, 1.2D, 0.6F);
		this.registerGrowthStage((byte) 2, -1000, 15.0D, 4.0D, 0.27D, 0.25D, 1.1D, 0.8F);
		this.registerGrowthStage((byte) 3, 0, 20.0D, 6.0D, 0.26D, 0.25D, 1.1D, 1.0F);
	}
}
