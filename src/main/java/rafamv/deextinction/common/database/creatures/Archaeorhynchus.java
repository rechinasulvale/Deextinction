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
import rafamv.deextinction.common.entity.creature.EntityArchaeorhynchus;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Archaeorhynchus extends EggCreature
{
	public static final String NAME = "archaeorhynchus_spathula";

	public Archaeorhynchus()
	{
		super(Archaeorhynchus.NAME);
	}

	@Override
	public EggModelBase getEggModel()
	{
		return new ModelEggLong();
	}

	@Override
	public ResourceLocation getEggTexture()
	{
		return new ResourceLocation(DeExtinction.MODID, "textures/entities/eggs/entity_egg_long_default_texture.png");
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityArchaeorhynchus(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.THEROPODS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(2, 2);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 150;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(DEItemRegistry.archaeorhynchus_fossilized_feather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.archaeorhynchus_egg;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(DEItemRegistry.archaeorhynchus_fossilized_feather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Cretaceous.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{
		this.registerPreviousCreature(Chicken.NAME, 100);
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
		this.registerGrowthStage((byte) 0, -96000, 6.0D, 0.0D, 0.3D, 0.12D, 1.2D, 0.25F);
		this.registerGrowthStage((byte) 1, -72000, 7.0D, 0.0D, 0.3D, 0.14D, 1.2D, 0.35F);
		this.registerGrowthStage((byte) 2, -48000, 8.0D, 0.0D, 0.3D, 0.16D, 1.15D, 0.45F);
		this.registerGrowthStage((byte) 3, -24000, 9.0D, 0.0D, 0.3D, 0.18D, 1.15D, 0.55F);
		this.registerGrowthStage((byte) 4, 0, 10.0D, 0.0D, 0.3D, 0.2D, 1.1D, 0.6F);
	}
}
