package rafamv.deextinction.common.entity.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public class EntityPregnantMammal implements IExtendedEntityProperties
{
	private final EntityLivingBase creature;
	private byte researchProgress;
	private byte babyTexture;
	private boolean babyGender;
	private String babyName;

	public EntityPregnantMammal(EntityLivingBase creature)
	{
		this.creature = creature;
		this.babyName = EntityPregnantMammalKeys.NO_EMBRYO;
		this.researchProgress = (byte) 0;
		this.babyTexture = (byte) 0;
		this.babyGender = true;
	}

	@Override
	public void init(Entity entity, World world)
	{
		entity.getDataWatcher().addObject(EntityDeExtinctedCreatureKeys.KEY_PREGNANCY_PROGRESS, Integer.valueOf(0));
	}

	public static final void register(EntityLivingBase entity)
	{
		entity.registerExtendedProperties(EntityPregnantMammalKeys.PREGNANT_MAMMAL_PROPERTY, new EntityPregnantMammal(entity));
	}

	public static final EntityPregnantMammal get(EntityLivingBase entity)
	{
		return (EntityPregnantMammal) entity.getExtendedProperties(EntityPregnantMammalKeys.PREGNANT_MAMMAL_PROPERTY);
	}

	public void setPregnancyProgress(int progress)
	{
		this.creature.getDataWatcher().updateObject(EntityDeExtinctedCreatureKeys.KEY_PREGNANCY_PROGRESS, Integer.valueOf(progress));
	}

	public void increasePregnancyProgress()
	{
		this.creature.getDataWatcher().updateObject(EntityDeExtinctedCreatureKeys.KEY_PREGNANCY_PROGRESS, Integer.valueOf(this.getPregnancyProgress() + 1));
	}

	public int getPregnancyProgress()
	{
		return this.creature.getDataWatcher().getWatchableObjectInt(EntityDeExtinctedCreatureKeys.KEY_PREGNANCY_PROGRESS);
	}

	public int getPregnancyProgressScaled(int barSize)
	{
		return (this.getPregnancyProgress() * barSize) / EntityPregnantMammalKeys.PREGNANCY_MAX_PROGRESS;
	}

	public byte getResearchProgress()
	{
		return this.researchProgress;
	}

	public void setResearchProgress(byte researchProgress)
	{
		this.researchProgress = researchProgress;
	}

	public void setBabyTexture(byte babyTexture)
	{
		this.babyTexture = babyTexture;
	}

	public byte getBabyTexture()
	{
		return this.babyTexture;
	}

	public void setBabyGender(boolean babyGender)
	{
		this.babyGender = babyGender;
	}

	public boolean getBabyGender()
	{
		return this.babyGender;
	}

	public void setBabyName(String babyName)
	{
		this.babyName = babyName;
	}

	public String getBabyName()
	{
		return this.babyName;
	}

	public void born()
	{
		if (!this.creature.worldObj.isRemote)
		{
			Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(this.babyName);
			if (creature != null)
			{
				EntityLivingBase entity = creature.getEntity(this.creature.worldObj);
				if (entity instanceof EntityDeExtinctedAgeable)
				{
					EntityDeExtinctedAgeable deCreature = (EntityDeExtinctedAgeable) entity;
					deCreature = deCreature.createBabeCreature(creature, this.babyGender, this.babyTexture, this.researchProgress);

					if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX + 1.0D, this.creature.posY, this.creature.posZ)))
						entity.setLocationAndAngles(this.creature.posX + 1.0D, this.creature.posY + 0.5D, this.creature.posZ, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX, this.creature.posY, this.creature.posZ + 1.0D)))
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 0.5D, this.creature.posZ + 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX + 1.0D, this.creature.posY, this.creature.posZ + 1.0D)))
						entity.setLocationAndAngles(this.creature.posX + 1.0D, this.creature.posY + 0.5D, this.creature.posZ + 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX - 1.0D, this.creature.posY, this.creature.posZ)))
						entity.setLocationAndAngles(this.creature.posX - 1.0D, this.creature.posY + 0.5D, this.creature.posZ, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX, this.creature.posY, this.creature.posZ - 1.0D)))
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 0.5D, this.creature.posZ - 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX - 1.0D, this.creature.posY, this.creature.posZ - 1.0D)))
						entity.setLocationAndAngles(this.creature.posX - 1.0D, this.creature.posY + 0.5D, this.creature.posZ - 1.0D, this.creature.rotationYaw, 0.0F);
					else
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 1.0D, this.creature.posZ, this.creature.rotationYaw, 0.0F);

					this.creature.worldObj.spawnEntityInWorld(deCreature);
				}
				else
				{
					entity = creature.getEntity(this.creature.worldObj);
					if (entity instanceof EntityAgeable)
					{
						if (entity instanceof EntityHorse)
						{
							EntityHorse entityHorse = (EntityHorse) entity;
							entity = entityHorse.createChild(entityHorse);
							((EntityAgeable) entity).setGrowingAge(-24000);
						}
						else
							((EntityAgeable) entity).setGrowingAge(-24000);
					}

					if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX + 1.0D, this.creature.posY, this.creature.posZ)))
						entity.setLocationAndAngles(this.creature.posX + 1.0D, this.creature.posY + 0.5D, this.creature.posZ, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX, this.creature.posY, this.creature.posZ + 1.0D)))
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 0.5D, this.creature.posZ + 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX + 1.0D, this.creature.posY, this.creature.posZ + 1.0D)))
						entity.setLocationAndAngles(this.creature.posX + 1.0D, this.creature.posY + 0.5D, this.creature.posZ + 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX - 1.0D, this.creature.posY, this.creature.posZ)))
						entity.setLocationAndAngles(this.creature.posX - 1.0D, this.creature.posY + 0.5D, this.creature.posZ, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX, this.creature.posY, this.creature.posZ - 1.0D)))
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 0.5D, this.creature.posZ - 1.0D, this.creature.rotationYaw, 0.0F);
					else if (this.creature.worldObj.isAirBlock(new BlockPos(this.creature.posX - 1.0D, this.creature.posY, this.creature.posZ - 1.0D)))
						entity.setLocationAndAngles(this.creature.posX - 1.0D, this.creature.posY + 0.5D, this.creature.posZ - 1.0D, this.creature.rotationYaw, 0.0F);
					else
						entity.setLocationAndAngles(this.creature.posX, this.creature.posY + 1.0D, this.creature.posZ, this.creature.rotationYaw, 0.0F);

					this.creature.worldObj.spawnEntityInWorld(entity);
				}
			}
			this.playHatchingEffect();
		}
	}

	private void playHatchingEffect()
	{
		this.creature.worldObj.playSoundAtEntity(this.creature, "liquid.lavapop", 0.8F, 2.0F);
		for (int i = 0; i < 10; i++)
		{
			float f1 = (this.creature.worldObj.rand.nextFloat() * 2.0F - 1.0F) * this.creature.width * 0.5F;
			float f2 = (this.creature.worldObj.rand.nextFloat() * 2.0F - 1.0F) * this.creature.width * 0.5F;
			this.creature.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.creature.posX + (double) f1, this.creature.posY + (double) this.creature.height, this.creature.posZ + (double) f2, this.creature.motionX, this.creature.motionY, this.creature.motionZ, new int[0]);
		}
	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();
		properties.setShort("PregnancyProgress", (short) this.getPregnancyProgress());
		properties.setByte("ResearchProgress", this.researchProgress);
		properties.setByte("BabyTexture", this.babyTexture);
		properties.setBoolean("BabyGender", this.babyGender);
		properties.setString("BabyName", this.babyName);
		compound.setTag(EntityPregnantMammalKeys.PREGNANT_MAMMAL_PROPERTY, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EntityPregnantMammalKeys.PREGNANT_MAMMAL_PROPERTY);
		if (properties != null)
		{
			if (properties.hasKey("PregnancyProgress"))
				this.setPregnancyProgress((int) properties.getShort("PregnancyProgress"));
			if (properties.hasKey("ResearchProgress"))
				this.researchProgress = properties.getByte("ResearchProgress");
			if (properties.hasKey("BabyTexture"))
				this.babyTexture = properties.getByte("BabyTexture");
			if (properties.hasKey("BabyGender"))
				this.babyGender = properties.getBoolean("BabyGender");
			if (properties.hasKey("BabyName"))
				this.babyName = properties.getString("BabyName");
		}
	}
}
