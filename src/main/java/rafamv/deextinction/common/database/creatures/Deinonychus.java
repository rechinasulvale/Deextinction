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
import rafamv.deextinction.common.database.periods.Cretaceous;
import rafamv.deextinction.common.entity.creature.EntityDeinonychus;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Deinonychus extends EggCreature
{
	public static final String NAME = "deinonychus_antirrhopus";

	public Deinonychus()
	{
		super(Deinonychus.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggMedium();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_deinonychus_antirrhopus.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityDeinonychus(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(5, 2);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 400;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.deinonychus_fossilized_claw);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.deinonychus_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.deinonychus_fossilized_claw, 6);
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
		this.registerGrowthStage((byte) 0, -60000, 6.0D, 1.0D, 0.3D, 0.15D, 1.4D, 0.2F);
		this.registerGrowthStage((byte) 1, -48000, 10.0D, 2.0D, 0.3D, 0.2D, 1.135D, 0.28F);
		this.registerGrowthStage((byte) 2, -36000, 13.0D, 3.0D, 0.3D, 0.3D, 1.13D, 0.36F);
		this.registerGrowthStage((byte) 3, -24000, 16.0D, 4.0D, 0.3D, 0.4D, 1.13D, 0.45F);
		this.registerGrowthStage((byte) 4, -12000, 18.0D, 5.0D, 0.3D, 0.5D, 1.25D, 0.53F);
		this.registerGrowthStage((byte) 5, 0, 20.0D, 5.5D, 0.3D, 0.6D, 1.2D, 0.62F);
	}
}
