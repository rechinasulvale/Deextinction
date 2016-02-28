package rafamv.deextinction.common.database.creatures;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rafamv.deextinction.client.gui.gallery.GalleryPosition;
import rafamv.deextinction.common.database.SyringeCreature;
import rafamv.deextinction.common.database.periods.Quaternary;
import rafamv.deextinction.common.entity.creature.EntityQuagga;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEItemRegistry;

public class Horse extends SyringeCreature
{
	public static final String NAME = "horse";

	public Horse()
	{
		super(Horse.NAME);
	}

	@Override
	public boolean isMother(EntityLivingBase mother)
	{
		return mother instanceof EntityHorse || mother instanceof EntityQuagga;
	}

	@Override
	public EntityLivingBase getEntity(World worldIn)
	{
		return new EntityHorse(worldIn);
	}

	@Override
	public byte getDeExtinctionBranch()
	{
		return DEDatabaseRegistry.MAMMALS_BRANCH;
	}

	@Override
	public GalleryPosition getResearchPosition()
	{
		return new GalleryPosition(0, 0);
	}

	@Override
	public int getNutrientsRequired()
	{
		return 350;
	}

	@Override
	public ItemStack getResearchButtonItem()
	{
		return new ItemStack(Items.leather);
	}

	@Override
	public Item getHatchItem()
	{
		return DEItemRegistry.horse_syringe;
	}

	@Override
	public void initFossilItems()
	{
		this.registerFossilItem(Items.leather, 6);
	}

	@Override
	public void initCreaturePeriods()
	{
		this.registerCreaturePeriod(Quaternary.NAME);
	}

	@Override
	public void initPreviousCreatures()
	{

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
		this.registerGrowthStage((byte) 0, -24000, 4.0D, 0.0D, 0.25D, 0.2D, 1.0D, 0.5F);
		this.registerGrowthStage((byte) 1, 0, 4.0D, 0.0D, 0.25D, 0.2D, 1.0D, 1.0F);
	}
}
