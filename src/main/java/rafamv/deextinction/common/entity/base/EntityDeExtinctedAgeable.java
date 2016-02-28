package rafamv.deextinction.common.entity.base;

import java.util.HashMap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.CreatureTexture;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;

public abstract class EntityDeExtinctedAgeable extends EntityDeExtinctedCreature
{
	protected int creatureAgeForNextStage;
	protected int creatureAge;

	public EntityDeExtinctedAgeable(World worldIn)
	{
		super(worldIn);
		if (this.rand.nextBoolean())
			this.createRandomAdultCreature();
		else
			this.createRandomCreature();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_AGE_STAGE, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_AGE_SCALE, Float.valueOf(0.0F));
	}

	protected HashMap<Byte, Integer> getGrowthStageMap()
	{
		return this.getCreature().getGrowthStagesMap();
	}

	@Override
	protected boolean interact(EntityPlayer player)
	{
		// this.debugMessage(player);

		ItemStack itemstack = player.inventory.getCurrentItem();
		if (itemstack != null)
		{
			Item currentItem = itemstack.getItem();

			if (!this.worldObj.isRemote && this.isNotFull())
			{
				if (this.getCreatureFoodList().contains(currentItem))
				{
					if (!player.capabilities.isCreativeMode)
					{
						--itemstack.stackSize;
					}

					if (itemstack.stackSize <= 0)
					{
						player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
					}

					String itemUnlocalizedName = itemstack.getUnlocalizedName();
					if (DEDatabaseRegistry.FOOD_LIST.containsKey(itemUnlocalizedName))
						this.decreaseHunger(DEDatabaseRegistry.FOOD_LIST.get(itemUnlocalizedName));
					else
						this.decreaseHunger(30);

					this.spawnParticlesFromItem(currentItem, 10);
					this.playEatingSound(currentItem);
					this.eatingSpecialEvent(player, itemstack);
					return true;
				}
			}
		}
		else
		{
			return this.interactWithBareHand(player);
		}
		return false;
	}

	protected boolean interactWithBareHand(EntityPlayer player)
	{
		return false;
	}

	protected void eatingSpecialEvent(EntityPlayer player, ItemStack itemstack)
	{

	}

	protected void debugMessage(EntityPlayer player)
	{
		if (this.worldObj.isRemote)
		{
			System.out.println("Client ===========================================");
			System.out.println("Creature: " + this.getCreature().getDisplayName() + ", ID: " + this.getEntityId());
			System.out.println("Research Quality: " + this.getResearchQuality());
			System.out.println("Gender: " + this.getGenderString());
			System.out.println("TextureID: " + this.getTextureID());
			System.out.println("Growth Stage: " + this.getGrowthStage());
			System.out.println("Scale: " + this.getModelScale());
			System.out.println("Health: " + this.getCreatureCurrentHealth() + "/" + this.getCreatureHealth() + " (" + (100.0D * this.getCreatureCurrentHealth() / this.getCreatureHealth()) + "%)");
			System.out.println("Attack: " + this.getCreatureAttack());
			System.out.println("Speed: " + this.getCreatureSpeed());
			System.out.println("Knockback: " + this.getCreatureKnockback());
			System.out.println("isSitting: " + this.isSitting() + ", isEating: " + this.isEating() + ", isTamed: " + this.isTamed() + ", isSocializing: " + this.isSocializing() + ", isFlying: " + this.isFlying());
			System.out.println("Hunger: " + this.getHunger() + ", is not full? " + this.isNotFull() + ", should Hunt? " + this.shouldHunt());
			System.out.println("Should Update: " + this.shouldUpdate());
			System.out.println("AI Target: " + this.getAITarget());
			System.out.println("Attack Target: " + this.getAttackTarget());
		}
		else
		{
			System.out.println("Server ===========================================");
			System.out.println("Creature: " + this.getCreature().getDisplayName() + ", ID: " + this.getEntityId());
			System.out.println("Research Quality: " + this.getResearchQuality());
			System.out.println("Gender: " + this.getGenderString());
			System.out.println("TextureID: " + this.getTextureID());
			System.out.println("Growth Stage: " + this.getGrowthStage());
			System.out.println("Scale: " + this.getModelScale());
			System.out.println("Health: " + this.getCreatureCurrentHealth() + "/" + this.getCreatureHealth() + " (" + (100.0D * this.getCreatureCurrentHealth() / this.getCreatureHealth()) + "%)");
			System.out.println("Attack: " + this.getCreatureAttack());
			System.out.println("Speed: " + this.getCreatureSpeed());
			System.out.println("Knockback: " + this.getCreatureKnockback());
			System.out.println("isSitting: " + this.isSitting() + ", isEating: " + this.isEating() + ", isTamed: " + this.isTamed() + ", isSocializing: " + this.isSocializing() + ", isFlying: " + this.isFlying());
			System.out.println("Hunger: " + this.getHunger() + ", is not full? " + this.isNotFull() + ", should Hunt? " + this.shouldHunt());
			System.out.println("Should Update: " + this.shouldUpdate());
			System.out.println("AI Target: " + this.getAITarget());
			System.out.println("Attack Target: " + this.getAttackTarget());
		}
	}

	/**
	 * Returns the speed of the creature. This is just a information for the
	 * user.
	 */
	public double getCreatureSpeed()
	{
		if (this.getCreature().getStageToSpeedMapping() != null && !this.getCreature().getStageToSpeedMapping().isEmpty())
		{
			return (double) ((int) (100 * this.getCreature().getStageToSpeedMapping().get(this.getGrowthStage()))) / 100;
		}
		else
		{
			DeExtinction.logger.error("Creature Speed map was null when getting speed of the " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
			return 1.0D;
		}
	}

	@Override
	public int getAge()
	{
		return this.creatureAge;
	}

	protected void setAge(int ticks)
	{
		this.creatureAge = ticks;
	}

	protected void increaseAge(int ticks)
	{
		this.creatureAge += ticks;
	}

	public int getNextAge()
	{
		return this.creatureAgeForNextStage;
	}

	protected void setNextAge(int ticks)
	{
		this.creatureAgeForNextStage = ticks;
	}

	@Override
	public boolean isChild()
	{
		return this.creatureAge < 0;
	}

	public boolean isAdult()
	{
		return this.creatureAge > -1;
	}

	public int getGrowthStageMapSize()
	{
		return this.getCreature().getGrowthStagesMap().size();
	}

	public int getCreatureAgeInDays()
	{
		return (int) (this.creatureAge / 24000);
	}

	public int getCreatureAgeInMonths()
	{
		return (int) (this.creatureAge / 720000);
	}

	public int getCreatureAgeInYears()
	{
		return (int) (this.creatureAge / 8640000);
	}

	public String getCreatureAgeString()
	{
		int years = (int) (this.creatureAge / 8640000);
		int months = (int) (this.creatureAge / 720000) - 12 * years;
		int days = (int) (this.creatureAge / 24000) - 30 * months;

		String yearString = "";
		String monthString = "";
		String dayString = "";

		if (years > 0)
		{
			if (months > 0 || days > 0)
			{
				if (years == 1)
					yearString = years + " " + StatCollector.translateToLocal("entity.info.year") + ", ";
				else
					yearString = years + " " + StatCollector.translateToLocal("entity.info.years") + ", ";
			}
			else
			{
				if (years == 1)
					yearString = years + " " + StatCollector.translateToLocal("entity.info.year");
				else
					yearString = years + " " + StatCollector.translateToLocal("entity.info.years");
			}
		}

		if (months > 0)
		{
			if (days > 0)
			{
				if (months == 1)
					monthString = months + " " + StatCollector.translateToLocal("entity.info.month") + ", ";
				else
					monthString = months + " " + StatCollector.translateToLocal("entity.info.months") + ", ";
			}
			else
			{
				if (months == 1)
					monthString = months + " " + StatCollector.translateToLocal("entity.info.month");
				else
					monthString = months + " " + StatCollector.translateToLocal("entity.info.months");
			}
		}

		if (days == 0)
			dayString = days + " " + StatCollector.translateToLocal("entity.info.newborn");
		else if (days == 1)
			dayString = days + " " + StatCollector.translateToLocal("entity.info.day");
		else
			dayString = days + " " + StatCollector.translateToLocal("entity.info.days");

		return (yearString + monthString + dayString);
	}

	public float getModelScale()
	{
		return this.dataWatcher.getWatchableObjectFloat(EntityDeExtinctedCreatureKeys.KEY_AGE_SCALE);
	}

	private void setModelScale(Float scale)
	{
		this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_AGE_SCALE, scale);
	}

	public byte getGrowthStage()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityDeExtinctedCreatureKeys.KEY_AGE_STAGE);
	}

	public void setGrowthStage(byte stage)
	{
		HashMap<Byte, Integer> growthStageMap = this.getGrowthStageMap();
		if (growthStageMap != null)
		{
			if (stage < growthStageMap.size())
			{
				byte nextStage = (byte) (stage + 1);
				if (nextStage < growthStageMap.size())
					this.creatureAgeForNextStage = growthStageMap.get(nextStage);
				else
					this.creatureAgeForNextStage = Integer.MAX_VALUE;

				this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_AGE_STAGE, stage);
				this.updateCreatureData(stage);
				this.setToUpdate(false);
				this.setToUpdate(true);
			}
			else
				DeExtinction.logger.error("Tried to set a stage higher than the maximum. " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
		}
		else
			DeExtinction.logger.error("Failed to set growth stage: " + stage + " at a(n) " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
	}

	/**
	 * Returns the initial growing age of the creature.
	 */
	protected int getGrowingAgeToBabe()
	{
		return this.getCreature().getGrowthStagesMap().get((byte) 0);
	}

	protected final void setScaleForStage(byte stage)
	{
		HashMap<Byte, Float> stagetoScaleMap = this.getCreature().getStageToScaleMapping();
		if (stagetoScaleMap != null && stage < stagetoScaleMap.size())
		{
			float scale = stagetoScaleMap.get(stage);
			this.setSize(this.getMaxWidth() * scale, this.getMaxHeight() * scale);
			this.setModelScale(scale);
		}
		else
			DeExtinction.logger.error("Tried to set a stage that is higher than the maximum stage at the creature " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (this.worldObj.isRemote)
		{
			if (this.shouldUpdate())
			{
				this.setScaleForStage(this.getGrowthStage());
				this.setTexture(this.getTextureID());
				this.setToUpdate(false);
			}
		}
		else
		{
			this.creatureAge++;
			if (this.creatureAge >= this.creatureAgeForNextStage)
				this.setGrowthStage((byte) (this.getGrowthStage() + 1));
		}
	}

	public EntityDeExtinctedAgeable createBabeCreature(Creature creature, boolean gender, byte textureID, byte researchProgress)
	{
		this.setGender(gender);
		this.setTextureID(textureID);
		this.setResearchQuality(researchProgress);
		this.setAge(this.getGrowingAgeToBabe());
		this.setGrowthStage((byte) 0);
		return this;
	}

	public EntityDeExtinctedAgeable createAdultCreature(Creature creature, boolean gender, byte textureID, byte researchProgress)
	{
		HashMap<Byte, Integer> growthStages = this.getGrowthStageMap();
		if (growthStages != null)
		{
			byte adultStage = this.getAdultStage();
			this.setGender(gender);
			this.setTextureID(textureID);
			this.setResearchQuality(researchProgress);
			this.setAge(growthStages.get(adultStage));
			this.setGrowthStage(adultStage);
			return this;
		}
		else
			DeExtinction.logger.error("GrowthStages were null when initializing an adult " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());

		return null;
	}

	public void createRandomChildCreature()
	{
		HashMap<Byte, Integer> growthStages = this.getGrowthStageMap();
		if (growthStages != null)
		{
			this.setResearchQuality((byte) (50 + this.rand.nextInt(51)));

			boolean gender = this.rand.nextBoolean();
			this.setGender(gender);

			Creature creature = this.getCreature();
			if (creature != null)
				if (gender)
				{
					HashMap<Byte, CreatureTexture> textures = creature.getMaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
				else
				{
					HashMap<Byte, CreatureTexture> textures = creature.getFemaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
			this.setAge(this.getGrowingAgeToBabe());
			this.setGrowthStage((byte) 0);
		}
		else
			DeExtinction.logger.error("GrowthStages were null when initializing a random adult " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
	}

	public void createRandomAdultCreature()
	{
		HashMap<Byte, Integer> growthStages = this.getGrowthStageMap();
		if (growthStages != null)
		{
			this.setResearchQuality((byte) (50 + this.rand.nextInt(51)));

			boolean gender = this.rand.nextBoolean();
			this.setGender(gender);

			Creature creature = this.getCreature();
			if (creature != null)
				if (gender)
				{
					HashMap<Byte, CreatureTexture> textures = creature.getMaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
				else
				{
					HashMap<Byte, CreatureTexture> textures = creature.getFemaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
			byte adultStage = this.getAdultStage();
			this.setAge(growthStages.get(adultStage));
			this.setGrowthStage(adultStage);
		}
		else
			DeExtinction.logger.error("GrowthStages were null when initializing a random adult " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
	}

	public ItemStack createCreatureEgg(EntityDeExtinctedAgeable otherCreature)
	{
		ItemStack newEgg = null;
		Creature creature = this.getCreature();
		if (creature != null)
		{
			newEgg = new ItemStack(creature.getHatchItem());
			NBTTagCompound compound = new NBTTagCompound();

			boolean gender = this.rand.nextBoolean();
			byte textureID = this.isMale() ? (gender ? this.getTextureID() : otherCreature.getTextureID()) : (gender ? otherCreature.getTextureID() : this.getTextureID());
			compound.setBoolean("Gender", gender);
			compound.setByte("Texture", textureID);

			if (gender)
				compound.setString("TextureName", creature.getMaleCreatureTextures().get(textureID).getTextureName());
			else
				compound.setString("TextureName", creature.getFemaleCreatureTextures().get(textureID).getTextureName());
			compound.setByte("ResearchProgress", (byte) ((1.0D + 0.1D * this.rand.nextDouble()) * Math.max(this.getResearchQuality(), otherCreature.getResearchQuality())));

			newEgg.setTagCompound(compound);
		}
		return newEgg;
	}

	public void createRandomCreature()
	{
		HashMap<Byte, Integer> growthStages = this.getGrowthStageMap();
		if (growthStages != null)
		{
			this.setResearchQuality((byte) (50 + this.rand.nextInt(51)));

			boolean gender = this.rand.nextBoolean();
			this.setGender(gender);

			Creature creature = this.getCreature();
			if (creature != null)
				if (gender)
				{
					HashMap<Byte, CreatureTexture> textures = creature.getMaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
				else
				{
					HashMap<Byte, CreatureTexture> textures = creature.getFemaleCreatureTextures();
					if (textures != null)
					{
						byte textureID = (byte) this.rand.nextInt(textures.size());
						this.setTextureID(textureID);
						CreatureTexture texture = textures.get(textureID);
						if (texture != null && this.worldObj.isRemote)
							this.texture = texture.getResourceLocation();
					}
				}
			byte adultStage = (byte) this.rand.nextInt(growthStages.size());
			this.setAge(growthStages.get(adultStage));
			this.setGrowthStage(adultStage);
		}
		else
			DeExtinction.logger.error("GrowthStages were null when initializing a random adult " + this.getCreature().getDisplayName() + " with id: " + this.getEntityId());
	}

	protected byte getAdultStage()
	{
		HashMap<Byte, Integer> growthStages = this.getGrowthStageMap();
		if (growthStages != null)
			return (byte) (growthStages.size() - 1);
		else
			return (byte) 0;
	}

	/** Updates the creature status. */
	protected void updateCreatureData(byte stage)
	{
		Creature creature = this.getCreature();
		if (creature != null)
		{
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(creature.getStageToAttackMapping().get(stage));
			this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(creature.getStageToMovementMapping().get(stage));
			this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(creature.getStageToKnockbackMapping().get(stage));
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(creature.getStageToHealthMapping().get(stage));

			if (this.isEntityAlive())
				this.setHealth((float) this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());

			if (!this.worldObj.isRemote)
				this.updateAIForGrowthStage(this.getGrowthStage());
		}
	}

	protected void removeTask(EntityAIBase ai)
	{
		if (this.tasks.taskEntries.contains(ai))
			this.tasks.removeTask(ai);
	}

	protected void addTask(EntityAIBase ai)
	{
		if (!this.tasks.taskEntries.contains(ai))
			this.tasks.addTask(4, ai);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("Age", this.getAge());
		tagCompound.setByte("GrowthStage", this.getGrowthStage());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);
		this.setAge(tagCompund.getInteger("Age"));
		this.setGrowthStage(tagCompund.getByte("GrowthStage"));
	}

	protected abstract float getMaxHeight();

	protected abstract float getMaxWidth();

	protected abstract void updateAIForGrowthStage(byte stage);

	public abstract boolean isIndependent();
}
