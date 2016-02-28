package rafamv.deextinction.common.entity.base;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import rafamv.deextinction.common.database.Creature;

public abstract class EntityDeExtinctedCreature extends EntityCreature implements IEntityOwnable, IAnimals, IAnimatedEntity
{
	protected EntityLivingBase creatureTarget = null;
	public ResourceLocation texture = null;
	protected int hunger;
	protected int animTick;
	protected int animID;

	public EntityDeExtinctedCreature(World worldIn)
	{
		super(worldIn);
		this.hunger = (int) (0.5F * this.getMaxHunger());
		this.animTick = 0;
		this.animID = 0;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_RESEARCH_QUALITY, Byte.valueOf((byte) 0));
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_STATES, Integer.valueOf(0));
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_OWNER_UUID, "");
		this.dataWatcher.addObject(EntityDeExtinctedCreatureKeys.KEY_TEXTURE_ID, Byte.valueOf((byte) 0));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.animID != 0)
			this.animTick++;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if (!this.worldObj.isRemote && this.rand.nextInt(50) == 0)
			this.increaseHunger(this.rand.nextInt(2));
	}

	@Override
	public void setAnimID(int id)
	{
		this.animID = id;
	}

	@Override
	public int getAnimID()
	{
		return this.animID;
	}

	@Override
	public void setAnimTick(int tick)
	{
		this.animTick = tick;
	}

	@Override
	public int getAnimTick()
	{
		return this.animTick;
	}

	public void spawnParticlesInPlayerHandFromItem(EntityPlayer player, Item item, int amount)
	{
		int itemID = Item.getIdFromItem(item);
		for (int i = 0; i < amount; i++)
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, player.posX, player.posY + player.getEyeHeight(), player.posZ, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, new int[] { itemID });
	}

	public void spawnParticlesFromItem(Item item, int amount, double posX, double posY, double posZ)
	{
		int itemID = Item.getIdFromItem(item);
		for (int i = 0; i < amount; i++)
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, posX, posY, posZ, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, new int[] { itemID });
	}

	public void spawnParticlesFromItem(Item item, int amount)
	{
		int itemID = Item.getIdFromItem(item);
		for (int i = 0; i < amount; i++)
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY + this.getEyeHeight(), this.posZ, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, new int[] { itemID });
	}

	public void setAITask(EntityAIBase aiBase, int priority, boolean flag)
	{
		if (flag)
			this.tasks.addTask(priority, aiBase);
		else
			this.tasks.removeTask(aiBase);
	}

	/**
	 * Returns the name of the creature. If it has a custom name tag, returns
	 * the name tag.
	 */
	public String getCreatureDisplayName()
	{
		return this.hasCustomName() ? this.getCustomNameTag() : StatCollector.translateToLocal("entity." + this.getCreature().getName() + ".name");
	}

	/**
	 * Sets a text that is not null in the chat (client side).
	 * 
	 * @param player
	 *            is player how will receive the text.
	 * 
	 * @param text
	 *            is the text that will be displayed.
	 */
	public void displayTextInChat(EntityPlayer player, String text)
	{
		if (!this.worldObj.isRemote && player != null && text != null)
			player.addChatMessage(new ChatComponentText(text));
	}

	/**
	 * Returns the current health of the creature. This is just a information
	 * for the user. Should be using "this.getHealth()" method.
	 */
	public double getCreatureCurrentHealth()
	{
		return (double) ((int) (100 * this.getHealth())) / 100;
	}

	/**
	 * Returns the health of this creature. This is just a information for the
	 * user.
	 */
	public double getCreatureHealth()
	{
		return (double) ((int) (100 * this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue())) / 100;
	}

	public int getCreatureHealthScaled(int scale)
	{
		return (int) ((double) scale * this.getCreatureCurrentHealth() / this.getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue());
	}

	/**
	 * Returns the attack of this creature. This is just a information for the
	 * user.
	 */
	public double getCreatureAttack()
	{
		return (double) ((int) (100 * this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue())) / 100;
	}

	/**
	 * Returns the speed of the creature. This is just a information for the
	 * user.
	 */
	public double getCreatureMovementSpeed()
	{
		return (double) ((int) (100 * this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue())) / 100;
	}

	/**
	 * Returns the knockback of this creature. This is just a information for
	 * the user.
	 */
	public double getCreatureKnockback()
	{
		return (double) ((int) (100 * this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())) / 100;
	}

	public ResourceLocation getTexture()
	{
		return this.texture;
	}

	public void setTexture(ResourceLocation texture)
	{
		this.texture = texture;
	}

	public void setTexture(byte id)
	{
		if (this.isMale())
			this.texture = this.getCreature().getMaleCreatureTextures().get(id).getResourceLocation();
		else
			this.texture = this.getCreature().getFemaleCreatureTextures().get(id).getResourceLocation();
	}

	public void setResearchQuality(byte states)
	{
		this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_RESEARCH_QUALITY, Byte.valueOf(states));
	}

	public byte getResearchQuality()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityDeExtinctedCreatureKeys.KEY_RESEARCH_QUALITY);
	}

	public void setTextureID(byte id)
	{
		this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_TEXTURE_ID, Byte.valueOf(id));
	}

	public byte getTextureID()
	{
		return this.dataWatcher.getWatchableObjectByte(EntityDeExtinctedCreatureKeys.KEY_TEXTURE_ID);
	}

	/**
	 * Sets the states of the creature. It uses bitwise language.
	 *
	 * @param states
	 *            is an integer representing all the states of this creature.
	 */
	public void setStates(int states)
	{
		this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_STATES, Integer.valueOf(states));
	}

	/**
	 * Returns the states of the creature. It uses bitwise language.
	 */
	public int getStates()
	{
		return this.dataWatcher.getWatchableObjectInt(EntityDeExtinctedCreatureKeys.KEY_STATES);
	}

	/**
	 * Returns true if the creature should be updated in client.
	 */
	public boolean shouldUpdate()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_UPDATE) == EntityDeExtinctedCreatureKeys.FLAG_UPDATE;
	}

	/**
	 * Sets if the creature should be updated in client.
	 */
	public void setToUpdate(boolean flag)
	{
		if (flag)
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_UPDATE);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_UPDATE);
	}

	public boolean getGender()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_GENDER) == EntityDeExtinctedCreatureKeys.FLAG_GENDER;
	}

	/**
	 * Returns true if the creature is male.
	 */
	public boolean isMale()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_GENDER) == EntityDeExtinctedCreatureKeys.FLAG_GENDER;
	}

	/**
	 * Returns true if the creature is female.
	 */
	public boolean isFemale()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_GENDER) != EntityDeExtinctedCreatureKeys.FLAG_GENDER;
	}

	/**
	 * Sets if the creature is male or female.
	 */
	public void setGender(boolean flag)
	{
		if (flag)
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_GENDER);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_GENDER);
	}

	/**
	 * Returns a string about the creature's gender.
	 */
	public String getGenderString()
	{
		if ((this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_GENDER) == EntityDeExtinctedCreatureKeys.FLAG_GENDER)
			return StatCollector.translateToLocal("entity.info.male");
		else
			return StatCollector.translateToLocal("entity.info.female");
	}

	/**
	 * Returns true if the creature is tamed.
	 */
	public boolean isTamed()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_TAMED) == EntityDeExtinctedCreatureKeys.FLAG_TAMED;
	}

	/**
	 * Sets if the creature is tamed.
	 */
	public void setTamed(boolean flag, EntityPlayer player)
	{
		if (flag)
		{
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_TAMED);
			this.setAttackTarget((EntityLivingBase) null);
			this.worldObj.setEntityState(this, (byte) 7);
		}
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_TAMED);
	}

	/**
	 * Returns true if the creature is taking off.
	 */
	public boolean isTakingOff()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_TAKING_OFF) == EntityDeExtinctedCreatureKeys.FLAG_TAKING_OFF;
	}

	/**
	 * Returns true if the creature is taking off.
	 */
	public boolean canTakeOff()
	{
		return !this.isFlying() && !this.isSitting() && !this.isSleeping();
	}

	/**
	 * Sets if the creature is taking off.
	 */
	public void setTakingOff(boolean flag)
	{
		if (flag && this.canTakeOff())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_TAKING_OFF);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_TAKING_OFF);
	}

	/**
	 * Returns true if the creature is flying.
	 */
	public boolean isFlying()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_FLYING) == EntityDeExtinctedCreatureKeys.FLAG_FLYING;
	}

	/**
	 * Returns true if the creature is flying.
	 */
	public boolean canFly()
	{
		return !this.isTakingOff() && !this.isSitting() && !this.isSleeping();
	}

	/**
	 * Sets if the creature is flying.
	 */
	public void setFlying(boolean flag)
	{
		if (flag && this.canFly())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_FLYING);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_FLYING);
	}

	/**
	 * Returns true if the creature is sitting.
	 */
	public boolean isSitting()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_SITTING) == EntityDeExtinctedCreatureKeys.FLAG_SITTING;
	}

	/**
	 * Returns true if the creature is sitting.
	 */
	public boolean canSit()
	{
		return !this.isTakingOff() && !this.isFlying();
	}

	/**
	 * Sets if the creature is sitting.
	 *
	 * @param flag
	 *            is the next state that the creature will be (true/false).
	 * @param player
	 *            is the EntityPlayer that will receive the sitting text. Set
	 *            null to not send the message.
	 */
	public void setSitting(boolean flag, EntityPlayer player)
	{
		if (flag && this.canSit())
		{
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_SITTING);
			this.setAttackTarget((EntityLivingBase) null);
			this.handleSittingText(player);
			this.isJumping = false;
		}
		else
		{
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SITTING);
			this.handleSittingText(player);
		}
	}

	/**
	 * Shows a text about the sitting state of the creature.
	 */
	public void handleSittingText(EntityPlayer player)
	{
		if (player != null)
			this.displayTextInChat(player, this.getCreatureDisplayName() + (this.isSitting() ? StatCollector.translateToLocal("entity.info.sitting") : StatCollector.translateToLocal("entity.info.notsitting")));
	}

	/**
	 * Returns true if the creature is sleeping.
	 */
	public boolean isSleeping()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_SLEEPING) == EntityDeExtinctedCreatureKeys.FLAG_SLEEPING;
	}

	/**
	 * Returns true if the creature is sleeping.
	 */
	public boolean canSleep()
	{
		return this.isSitting() && !this.isTakingOff() && !this.isFlying();
	}

	/**
	 * Sets if the creature is sleeping.
	 */
	public void setSleeping(boolean flag)
	{
		if (flag && this.canSleep())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_SLEEPING);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SLEEPING);
	}

	/**
	 * Returns true if the creature is socializing.
	 */
	public boolean isSocializing()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_SOCIALIZING) == EntityDeExtinctedCreatureKeys.FLAG_SOCIALIZING;
	}

	/**
	 * Returns true if the creature can socialize.
	 */
	public boolean canSocialize()
	{
		return !this.isSleeping() && !this.isSitting() && !this.isTakingOff();
	}

	/**
	 * Sets if the creature is socializing.
	 */
	public void setSocializing(boolean flag)
	{
		if (flag && this.canSocialize())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_SOCIALIZING);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SOCIALIZING);
	}

	/**
	 * Returns true if the creature is fleeing.
	 */
	public boolean isFleeing()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_FLEEING) == EntityDeExtinctedCreatureKeys.FLAG_FLEEING;
	}

	/**
	 * Returns true if the creature can flee.
	 */
	public boolean canFleeing()
	{
		return true;
	}

	/**
	 * Sets if the creature is fleeing.
	 */
	public void setFleeing(boolean flag)
	{
		if (flag && this.canFleeing())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_FLEEING);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_FLEEING);
	}

	/**
	 * Returns true if the creature is stalking.
	 */
	public boolean isStalking()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_STALKING) == EntityDeExtinctedCreatureKeys.FLAG_STALKING;
	}

	/**
	 * Returns true if the creature is stalking.
	 */
	public boolean canStalk()
	{
		return !this.isSleeping() && !this.isSitting() && !this.isTakingOff();
	}

	/**
	 * Sets if the creature is stalking.
	 */
	public void setStalking(boolean flag)
	{
		if (flag && this.canStalk())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_STALKING);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_STALKING);
	}

	/**
	 * Returns true if the creature was damaged recently.
	 */
	public boolean hasBeenHurt()
	{
		return this.hurtTime > 0;
	}

	/**
	 * Returns true if the creature is in love mode, searching for a mate.
	 */
	public boolean isInLove()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_IN_LOVE) == EntityDeExtinctedCreatureKeys.FLAG_IN_LOVE;
	}

	/**
	 * Sets if the creature is in love mode, searching for a mate.
	 */
	public void setInLove(boolean flag)
	{
		if (flag && !this.isSleeping())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_IN_LOVE);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_IN_LOVE);
	}

	/**
	 * Returns true if the creature is on an egg nest.
	 */
	public boolean isOnNest()
	{
		return (this.getStates() & EntityDeExtinctedCreatureKeys.FLAG_ON_NEST) == EntityDeExtinctedCreatureKeys.FLAG_ON_NEST;
	}

	/**
	 * Sets if the creature is on an egg nest.
	 */
	public void setOnNest(boolean flag)
	{
		if (flag && !this.isSleeping())
			this.setStates(this.getStates() | EntityDeExtinctedCreatureKeys.FLAG_ON_NEST);
		else
			this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_ON_NEST);
	}

	public boolean isAnyHungry()
	{
		return this.hunger < this.getMaxHunger();
	}

	public boolean isNotFull()
	{
		return this.hunger < (int) (0.8F * this.getMaxHunger());
	}

	public boolean shouldHunt()
	{
		return this.hunger < (int) (0.5F * this.getMaxHunger());
	}

	public int getHunger()
	{
		return this.hunger;
	}

	public void setHunger(int newHunger)
	{
		if (newHunger < 0)
			this.hunger = 0;
		else if (newHunger > this.getMaxHunger())
			this.hunger = this.getMaxHunger();
		else
			this.hunger = newHunger;
	}

	public void decreaseHunger(int amount)
	{
		int newHunger = this.hunger + amount;
		if (newHunger < this.getMaxHunger())
			this.hunger = newHunger;
		else
			this.hunger = this.getMaxHunger();
	}

	public void decreaseHunger()
	{
		if (this.hunger < this.getMaxHunger())
			this.hunger++;
		else
			this.hunger = this.getMaxHunger();
	}

	public void increaseHunger(int amount)
	{
		int newHunger = this.hunger - amount;
		if (newHunger > 0)
			this.hunger = newHunger;
		else
			this.hunger = 0;
	}

	public void increaseHunger()
	{
		if (this.hunger > 0)
			this.hunger--;
		else
			this.hunger = 0;
	}

	/**
	 * Clear all states from this creature.
	 */
	public void clearStatus()
	{
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_TAKING_OFF);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_FLYING);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SITTING);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SLEEPING);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_SOCIALIZING);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_STALKING);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_IN_LOVE);
		this.setStates(this.getStates() & ~EntityDeExtinctedCreatureKeys.FLAG_ON_NEST);
	}

	/**
	 * This is a separated entity living base that can be set in order to add
	 * attacks or animations.
	 *
	 * @param creature
	 *            is the target;
	 */
	public void setCreatureTarget(EntityLivingBase creature)
	{
		if (creature != this)
			this.creatureTarget = creature;
	}

	/**
	 * This is a separated entity living base that can be set in order to add
	 * attacks or animations.
	 */
	public EntityLivingBase getCreatureTarget()
	{
		return this.creatureTarget;
	}

	@Override
	public String getOwnerId()
	{
		return this.dataWatcher.getWatchableObjectString(EntityDeExtinctedCreatureKeys.KEY_OWNER_UUID);
	}

	public void setOwnerId(String ownerUuid)
	{
		this.dataWatcher.updateObject(EntityDeExtinctedCreatureKeys.KEY_OWNER_UUID, ownerUuid);
	}

	/**
	 * Returns the creature's owner.
	 */
	public EntityLivingBase getOwnerEntity()
	{
		try
		{
			UUID uuid = UUID.fromString(this.getOwnerId());
			return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
		}
		catch (IllegalArgumentException illegalargumentexception)
		{
			return null;
		}
	}

	@Override
	public Entity getOwner()
	{
		return this.getOwnerEntity();
	}

	/**
	 * Returns true if the entity is the creature's owner.
	 */
	public boolean isOwner(EntityLivingBase entityIn)
	{
		return entityIn == this.getOwnerEntity();
	}

	@Override
	public boolean allowLeashing()
	{
		return !this.getLeashed() && !this.isTakingOff() && !this.isFlying() && !this.isNotFull();
	}

	@Override
	public Team getTeam()
	{
		if (this.isTamed())
		{
			EntityLivingBase entitylivingbase = this.getOwnerEntity();
			if (entitylivingbase != null)
				return entitylivingbase.getTeam();
		}
		return super.getTeam();
	}

	@Override
	public boolean isOnSameTeam(EntityLivingBase otherEntity)
	{
		if (this.isTamed())
		{
			EntityLivingBase entitylivingbase = this.getOwnerEntity();

			if (otherEntity == entitylivingbase)
				return true;

			if (entitylivingbase != null)
				return entitylivingbase.isOnSameTeam(otherEntity);
		}
		return super.isOnSameTeam(otherEntity);
	}

	@Override
	public void onDeath(DamageSource cause)
	{
		if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("showDeathMessages") && this.hasCustomName() && this.getOwnerEntity() instanceof EntityPlayerMP)
			((EntityPlayerMP) this.getOwnerEntity()).addChatMessage(this.getCombatTracker().getDeathMessage());
		super.onDeath(cause);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("Status", this.getStates());
		tagCompound.setByte("Research", this.getResearchQuality());
		tagCompound.setByte("TextureID", this.getTextureID());
		tagCompound.setInteger("Hunger", this.getHunger());

		if (this.getOwnerId() == null)
			tagCompound.setString("OwnerUUID", "");
		else
			tagCompound.setString("OwnerUUID", this.getOwnerId());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		super.readEntityFromNBT(tagCompound);
		if (tagCompound.hasKey("Status"))
			this.setStates(tagCompound.getInteger("Status"));

		if (tagCompound.hasKey("Research"))
			this.setResearchQuality(tagCompound.getByte("Research"));

		if (tagCompound.hasKey("TextureID"))
		{
			this.setTextureID(tagCompound.getByte("TextureID"));
			this.setTexture(this.getTextureID());
		}

		if (tagCompound.hasKey("Hunger"))
			this.setHunger(tagCompound.getInteger("Hunger"));

		String s = "";
		if (tagCompound.hasKey("OwnerUUID"))
			s = tagCompound.getString("OwnerUUID");
		else
		{
			String s1 = tagCompound.getString("Owner");
			s = PreYggdrasilConverter.func_152719_a(s1);
		}

		if (s.length() > 0)
		{
			this.setOwnerId(s);
			this.setTamed(true, null);
		}
	}

	/**
	 * Returns the creature class of the creature, where basic values are set.
	 */
	public abstract Creature getCreature();

	/**
	 * Returns true if the creature can be tamed when spawning.
	 */
	public abstract boolean canBeTamedUponSpawning();

	/**
	 * Returns true if the creature is hungry.
	 */
	public abstract int getMaxHunger();

	/**
	 * Returns the list of items that this creature can eat.
	 */
	public abstract List<Item> getCreatureFoodList();

	/**
	 * Plays the eating shound of this creature depending on the item it is
	 * eating.
	 */
	public void playEatingSound(Item currentItem)
	{
		this.playSound("random.eat", 0.5F, 1.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (source.damageType == DamageSource.inWall.damageType)
			return false;
		else
			return super.attackEntityFrom(source, amount);
	}
}
