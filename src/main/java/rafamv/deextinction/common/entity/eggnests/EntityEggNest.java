package rafamv.deextinction.common.entity.eggnests;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.EggModelBase;
import rafamv.deextinction.client.renderer.animations.FlippableAnimation;
import rafamv.deextinction.client.renderer.model.ModelEggSmall;
import rafamv.deextinction.common.database.Creature;
import rafamv.deextinction.common.database.EggCreature;
import rafamv.deextinction.common.database.creatures.Chicken;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.base.EntityPushableInventory;
import rafamv.deextinction.common.item.ItemWaterThermometer;
import rafamv.deextinction.common.item.speciesitems.ItemCreatureEgg;
import rafamv.deextinction.common.message.MessageEggNest;
import rafamv.deextinction.common.registry.DEDatabaseRegistry;
import rafamv.deextinction.common.registry.DEMessageRegistry;

public abstract class EntityEggNest extends EntityPushableInventory implements IEntityAdditionalSpawnData
{
	private final List<FlippableAnimation> eggAnimations = new ArrayList<FlippableAnimation>();
	protected final List<ResourceLocation> listEggTexture = new ArrayList<ResourceLocation>();
	protected final List<EggModelBase> listEggModel = new ArrayList<EggModelBase>();
	protected final List<Integer> listEggProgress = new ArrayList<Integer>();
	protected final List<String> listEggName = new ArrayList<String>();

	private int warmthTimer = 0;

	public EntityEggNest(World worldIn, float xzSize, float ySize)
	{
		super(worldIn, xzSize, ySize);
		this.registerEggs();
	}

	public void registerEggs()
	{
		for (int i = 0; i < this.getNumberOfEggs(); i++)
		{
			this.eggAnimations.add(i, new FlippableAnimation(3, 90));
			this.listEggTexture.add(i, new ResourceLocation(DeExtinction.MODID, "textures/entities/entity_egg_small_default_texture.png"));
			this.listEggModel.add(i, new ModelEggSmall());
			this.listEggName.add(i, Chicken.NAME);
			this.listEggProgress.add(i, 0);
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(EntityEggNestKeys.KEY_STATES, Integer.valueOf(0));
		this.dataWatcher.addObject(EntityEggNestKeys.KEY_WARMTH, Integer.valueOf(50));
		this.initWarmth();
	}

	protected void initWarmth()
	{
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		if (this.worldObj.isRaining() && this.worldObj.canSeeSky(pos))
			this.setWarmth(25);
		else
		{
			if (this.inWater)
				this.setWarmth(0);
			else
				switch (this.worldObj.getLightFromNeighbors(pos))
				{
					case 15:
						this.setWarmth(100);
						break;
					case 14:
						this.setWarmth(91);
						break;
					case 13:
						this.setWarmth(83);
						break;
					case 12:
						this.setWarmth(76);
						break;
					case 11:
						this.setWarmth(70);
						break;
					case 10:
						this.setWarmth(64);
						break;
					case 9:
						this.setWarmth(58);
						break;
					case 8:
						this.setWarmth(54);
						break;
					case 7:
						this.setWarmth(47);
						break;
					case 6:
						this.setWarmth(41);
						break;
					case 5:
						this.setWarmth(35);
						break;
					case 4:
						this.setWarmth(29);
						break;
					case 3:
						this.setWarmth(22);
						break;
					case 2:
						this.setWarmth(15);
						break;
					case 1:
						this.setWarmth(7);
						break;
					case 0:
						this.setWarmth(0);
						break;
				}
		}
	}

	public int getAllWatchableBooleans()
	{
		return this.dataWatcher.getWatchableObjectInt(EntityEggNestKeys.KEY_STATES);
	}

	public void setAllWatchableBooleans(int booleans)
	{
		this.dataWatcher.updateObject(EntityEggNestKeys.KEY_STATES, booleans);
	}

	public boolean getWatchableBoolean(int flag)
	{
		return (this.dataWatcher.getWatchableObjectInt(EntityEggNestKeys.KEY_STATES) & flag) != 0;
	}

	public void setWatchableBoolean(int flagID, boolean flag)
	{
		int j = this.dataWatcher.getWatchableObjectInt(EntityEggNestKeys.KEY_STATES);
		if (flag)
			this.dataWatcher.updateObject(EntityEggNestKeys.KEY_STATES, Integer.valueOf(j | flagID));
		else
			this.dataWatcher.updateObject(EntityEggNestKeys.KEY_STATES, Integer.valueOf(j & ~flagID));
	}

	public int getWarmth()
	{
		return this.dataWatcher.getWatchableObjectInt(EntityEggNestKeys.KEY_WARMTH);
	}

	public void setWarmth(int warmth)
	{
		this.dataWatcher.updateObject(EntityEggNestKeys.KEY_WARMTH, warmth);
	}

	public int getWarmthScaled(int scaled)
	{
		return (scaled * this.dataWatcher.getWatchableObjectInt(EntityEggNestKeys.KEY_WARMTH)) / 100;
	}

	public List<FlippableAnimation> getEggAnimations()
	{
		return this.eggAnimations;
	}

	public FlippableAnimation getEggAnimation(int slotID)
	{
		return this.eggAnimations.get(slotID);
	}

	@SideOnly(Side.CLIENT)
	public float getEggAnimationProgress(int slotID)
	{
		return this.eggAnimations.get(slotID).getAnimationFraction();
	}

	protected void setEggAnimation(int slotID, FlippableAnimation animation)
	{
		this.eggAnimations.remove(slotID);
		this.eggAnimations.add(slotID, animation);
	}

	public List<ResourceLocation> getEggTextures()
	{
		return this.listEggTexture;
	}

	public ResourceLocation getEggTexture(int slotID)
	{
		return this.listEggTexture.get(slotID);
	}

	protected void setEggTexture(int slotID, ResourceLocation texture)
	{
		this.listEggTexture.remove(slotID);
		this.listEggTexture.add(slotID, texture);
	}

	public List<EggModelBase> getEggModels()
	{
		return this.listEggModel;
	}

	public EggModelBase getEggModel(int slotID)
	{
		return this.listEggModel.get(slotID);
	}

	protected void setEggModel(int slotID, EggModelBase model)
	{
		this.listEggModel.remove(slotID);
		this.listEggModel.add(slotID, model);
	}

	public List<Integer> getEggProgresses()
	{
		return this.listEggProgress;
	}

	public int getEggProgress(int slotID)
	{
		return this.listEggProgress.get(slotID);
	}

	protected void setEggProgress(int slotID, int progress)
	{
		this.listEggProgress.remove(slotID);
		this.listEggProgress.add(slotID, progress);
	}

	public List<String> getCreatureNames()
	{
		return this.listEggName;
	}

	public String getCreatureName(int slotID)
	{
		return this.listEggName.get(slotID);
	}

	protected void setCreatureName(int slotID, String creatureName)
	{
		this.listEggName.remove(slotID);
		this.listEggName.add(slotID, creatureName);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.worldObj.isRemote)
		{
			for (int slot = 0; slot < this.getNumberOfEggs(); slot++)
				if (this.getEggFlag(slot))
					this.eggAnimations.get(slot).runAnimation();
		}
		else
		{
			this.handleHatching();

			if (this.warmthTimer > 9)
			{
				this.handleWarmth();
				this.warmthTimer = 0;
			}
			else
				this.warmthTimer++;
		}
	}

	protected void handleHatching()
	{
		for (int slot = 0; slot < this.getNumberOfEggs(); slot++)
		{
			if (this.getEggFlag(slot))
			{
				int eggProgress = this.getEggProgress(slot);
				int layingBonus = this.riddenByEntity != null ? 2 : 0;
				int foliageBonus = this.getWatchableBoolean(EntityEggNestKeys.FLAG_FOLIAGE) ? 1 : 0;
				this.setEggProgress(slot, eggProgress + MathHelper.floor_float(this.getWarmth() / 10) + 1 + layingBonus + foliageBonus);

				if (eggProgress > EntityEggNestKeys.EGG_NEST_MAX_PROGRESS)
					this.hatch(slot);
			}
			else
				this.setEggProgress(slot, 0);
		}
	}

	public void hatch(int slot)
	{
		if (!this.worldObj.isRemote)
		{
			if (slot < this.getNumberOfEggs())
			{
				String creatureName = this.getCreatureName(slot);
				Creature creature = DEDatabaseRegistry.LIST_ALL_CREATURES.get(creatureName);
				if (creature != null)
				{
					boolean hasFailed = false;
					EntityLivingBase entity = creature.getEntity(this.worldObj);

					if (entity instanceof EntityDeExtinctedAgeable)
					{
						ItemStack egg = inventoryBasic.getStackInSlot(slot);
						if (egg != null && egg.getItem() instanceof ItemCreatureEgg && egg.hasTagCompound())
						{
							NBTTagCompound compound = egg.getTagCompound();
							if (compound.hasKey("ResearchProgress") && compound.hasKey("Texture") && compound.hasKey("Gender"))
							{
								EntityDeExtinctedAgeable deCreature = (EntityDeExtinctedAgeable) entity;
								deCreature = deCreature.createBabeCreature(creature, compound.getBoolean("Gender"), compound.getByte("Texture"), compound.getByte("ResearchProgress"));

								if (this.worldObj.isAirBlock(new BlockPos(this.posX + 1.0D, this.posY, this.posZ)))
									deCreature.setLocationAndAngles(this.posX + 1.0D, this.posY + 0.5D, this.posZ, this.rotationYaw, 0.0F);
								else if (this.worldObj.isAirBlock(new BlockPos(this.posX, this.posY, this.posZ + 1.0D)))
									deCreature.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ + 1.0D, this.rotationYaw, 0.0F);
								else if (this.worldObj.isAirBlock(new BlockPos(this.posX + 1.0D, this.posY, this.posZ + 1.0D)))
									deCreature.setLocationAndAngles(this.posX + 1.0D, this.posY + 0.5D, this.posZ + 1.0D, this.rotationYaw, 0.0F);
								else if (this.worldObj.isAirBlock(new BlockPos(this.posX - 1.0D, this.posY, this.posZ)))
									deCreature.setLocationAndAngles(this.posX - 1.0D, this.posY + 0.5D, this.posZ, this.rotationYaw, 0.0F);
								else if (this.worldObj.isAirBlock(new BlockPos(this.posX, this.posY, this.posZ - 1.0D)))
									deCreature.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ - 1.0D, this.rotationYaw, 0.0F);
								else if (this.worldObj.isAirBlock(new BlockPos(this.posX - 1.0D, this.posY, this.posZ - 1.0D)))
									deCreature.setLocationAndAngles(this.posX - 1.0D, this.posY + 0.5D, this.posZ - 1.0D, this.rotationYaw, 0.0F);
								else
									deCreature.setLocationAndAngles(this.posX, this.posY + 1.0D, this.posZ, this.rotationYaw, 0.0F);

								this.worldObj.spawnEntityInWorld(deCreature);
							}
							else
								hasFailed = true;
						}
						else
							hasFailed = true;
					}
					else
						hasFailed = true;

					if (hasFailed)
					{
						entity = creature.getEntity(this.worldObj);
						if (entity instanceof EntityAgeable)
							((EntityAgeable) entity).setGrowingAge(-24000);

						if (this.worldObj.isAirBlock(new BlockPos(this.posX + 1.0D, this.posY, this.posZ)))
							entity.setLocationAndAngles(this.posX + 1.0D, this.posY + 0.5D, this.posZ, this.rotationYaw, 0.0F);
						else if (this.worldObj.isAirBlock(new BlockPos(this.posX, this.posY, this.posZ + 1.0D)))
							entity.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ + 1.0D, this.rotationYaw, 0.0F);
						else if (this.worldObj.isAirBlock(new BlockPos(this.posX + 1.0D, this.posY, this.posZ + 1.0D)))
							entity.setLocationAndAngles(this.posX + 1.0D, this.posY + 0.5D, this.posZ + 1.0D, this.rotationYaw, 0.0F);
						else if (this.worldObj.isAirBlock(new BlockPos(this.posX - 1.0D, this.posY, this.posZ)))
							entity.setLocationAndAngles(this.posX - 1.0D, this.posY + 0.5D, this.posZ, this.rotationYaw, 0.0F);
						else if (this.worldObj.isAirBlock(new BlockPos(this.posX, this.posY, this.posZ - 1.0D)))
							entity.setLocationAndAngles(this.posX, this.posY + 0.5D, this.posZ - 1.0D, this.rotationYaw, 0.0F);
						else if (this.worldObj.isAirBlock(new BlockPos(this.posX - 1.0D, this.posY, this.posZ - 1.0D)))
							entity.setLocationAndAngles(this.posX - 1.0D, this.posY + 0.5D, this.posZ - 1.0D, this.rotationYaw, 0.0F);
						else
							entity.setLocationAndAngles(this.posX, this.posY + 1.0D, this.posZ, this.rotationYaw, 0.0F);

						this.worldObj.spawnEntityInWorld(entity);
					}
				}

				this.setEggProgress(slot, 0);
				this.setEggFlag(slot, false);

				ItemStack stack = this.inventoryBasic.getStackInSlot(slot);
				if (stack != null)
				{
					stack.stackSize--;
					if (stack.stackSize < 1)
						this.inventoryBasic.setInventorySlotContents(slot, null);
				}

				this.playHatchingEffect();
				this.worldObj.setEntityState(this, (byte) 6);
			}
			else
				DeExtinction.logger.error("Slot " + slot + " is not registered as a egg slot. Please, review the number of eggs and slots from this class. THIS IS A BUG!");
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte packetID)
	{
		if (packetID == (byte) 6)
			this.playHatchingEffect();
		else
			super.handleHealthUpdate(packetID);
	}

	private void playHatchingEffect()
	{
		this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, DeExtinction.prependModID("egg_cracking"), 0.4F, this.rand.nextFloat() * 0.4F + 0.8F);
		int itemID = Item.getIdFromItem(Items.egg);
		for (int i = 0; i < 12; i++)
			this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY + 0.2D, this.posZ, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, (this.rand.nextFloat() - 0.5D) * 0.5D, new int[] { itemID });
	}

	protected void setEggFlag(int slot, boolean flag)
	{
		switch (slot)
		{
			case 0:
				this.setWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0, flag);
				break;
			case 1:
				this.setWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1, flag);
				break;
			case 2:
				this.setWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2, flag);
				break;
			default:
				DeExtinction.logger.error("Tried to set an egg flag from a slot id larger than the boolean flags (for the egg render). THIS IS A BUG!");
		}
	}

	protected boolean getEggFlag(int slot)
	{
		switch (slot)
		{
			case 0:
				return this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0);
			case 1:
				return this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1);
			case 2:
				return this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2);
			default:
				DeExtinction.logger.error("Tried to get an egg flag from a slot id larger than the boolean flags (for the egg render). THIS IS A BUG!");
		}
		return false;
	}

	public boolean hasEmptyEggSlot()
	{
		switch (this.getNumberOfEggs())
		{
			case 1:
				return !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0);
			case 2:
				return !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1);
			case 3:
				return !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1) || !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2);
			default:
				return !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_0) || !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_1) || !this.getWatchableBoolean(EntityEggNestKeys.FLAG_EGG_2);
		}
	}

	private void handleWarmth()
	{
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);

		int currentWarmth = this.getWarmth();
		int warmthChange = 0;

		int layingBonus = this.riddenByEntity != null ? 1 : 0;
		int foliageBonus = this.getWatchableBoolean(EntityEggNestKeys.FLAG_FOLIAGE) ? 1 : 0;
		int rainingOnus = this.worldObj.isRaining() && this.worldObj.canSeeSky(pos) ? -3 + layingBonus : 0;
		int lightLevel = this.worldObj.getLightFromNeighbors(pos);

		if (this.inWater)
			rainingOnus -= 5;

		switch (lightLevel)
		{
			case 15:
				warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 14:
				if (currentWarmth > 91)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 13:
				if (currentWarmth > 83)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 12:
				if (currentWarmth > 76)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 11:
				if (currentWarmth > 70)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 10:
				if (currentWarmth > 64)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 9:
				if (currentWarmth > 58)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 8:
				if (currentWarmth > 54)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 7:
				if (currentWarmth > 47)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 6:
				if (currentWarmth > 41)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 5:
				if (currentWarmth > 35)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 4:
				if (currentWarmth > 29)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 3:
				if (currentWarmth > 22)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 2:
				if (currentWarmth > 15)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 1:
				if (currentWarmth > 7)
					warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				else
					warmthChange = 1 + foliageBonus + layingBonus + rainingOnus;
				break;
			case 0:
				warmthChange = -1 + foliageBonus + layingBonus + rainingOnus;
				break;
		}
		this.setWarmth(MathHelper.clamp_int(currentWarmth + warmthChange, 0, 100));
	}

	public void sendToUpdateEggInClient(int slot, String creatureName)
	{
		if (DeExtinction.isEffectiveClient())
			return;
		DEMessageRegistry.wrapper.sendToAll(new MessageEggNest(this.getEntityId(), slot, creatureName));
	}

	public void updateEggInClient(int slot, String creatureName)
	{
		Creature creature = DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(creatureName);
		if (creature != null && creature instanceof EggCreature)
		{
			EggCreature eggCreature = (EggCreature) creature;
			this.setEggTexture(slot, eggCreature.getEggTexture());
			this.setEggModel(slot, eggCreature.getEggModel());
		}
	}

	@Override
	public void onInventoryChanged(InventoryBasic inventoryBasic)
	{
		ItemStack itemStack = null;

		itemStack = inventoryBasic.getStackInSlot(this.getNumberOfEggs());
		this.setWatchableBoolean(EntityEggNestKeys.FLAG_THERMOMETER, itemStack != null && itemStack.getItem() instanceof ItemWaterThermometer);

		itemStack = inventoryBasic.getStackInSlot(this.getNumberOfEggs() + 1);
		this.setWatchableBoolean(EntityEggNestKeys.FLAG_FOLIAGE, itemStack != null && itemStack.getItem() instanceof ItemLeaves);

		if (!this.worldObj.isRemote)
		{
			for (int slot = 0; slot < this.getNumberOfEggs(); slot++)
			{
				itemStack = inventoryBasic.getStackInSlot(slot);
				if (itemStack != null && itemStack.getItem() instanceof ItemCreatureEgg)
				{
					String creatureName = ((ItemCreatureEgg) itemStack.getItem()).getCreatureName();
					Creature creature = DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(creatureName);
					if (creature != null && creature instanceof EggCreature)
					{
						this.setEggFlag(slot, true);
						this.setCreatureName(slot, ((EggCreature) creature).getName());
						this.sendToUpdateEggInClient(slot, creatureName);
					}
				}
				else
				{
					this.setEggProgress(slot, 0);
					this.setEggFlag(slot, false);
				}
			}
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public void fall(float distance, float damageMultiplier)
	{
		super.fall(distance, damageMultiplier);
		if (distance > 6.0F && this.onGround)
		{
			this.dropItemsWhenBroken();
			if (!this.worldObj.isRemote)
				this.setDead();
		}
	}

	@Override
	protected void dropItemsWhenBroken()
	{
		super.dropItemsWhenBroken();
		if (!this.worldObj.isRemote)
		{
			if (this.rand.nextFloat() < 0.6F)
				this.dropItemWithOffset(this.getEggNestItem(), 1, 0.25F);
			else
			{
				int number_of_sticks = 1 + this.rand.nextInt(5);
				for (int i = 0; i < number_of_sticks; ++i)
					this.dropItemWithOffset(Items.stick, 1, 0.25F);
			}
		}
	}

	@Override
	protected void playBreakingSoundEffect()
	{
		this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "dig.grass", 0.4F, this.rand.nextFloat() * 0.4F + 0.8F);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);
		tagCompound.setInteger("Flags", this.getAllWatchableBooleans());
		tagCompound.setByte("Warmth", (byte) this.getWarmth());

		List<Integer> list = this.listEggProgress;
		if (list != null && !list.isEmpty())
		{
			tagCompound.setByte("EggProgressSize", (byte) list.size());
			for (int i = 0; i < list.size(); i++)
			{
				tagCompound.setInteger("EggProgress" + i, list.get(i));
				tagCompound.setBoolean("EggFlag" + i, this.getEggFlag(i));
			}
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		super.readEntityFromNBT(tagCompound);
		if (tagCompound.hasKey("Flags"))
			this.setAllWatchableBooleans(tagCompound.getInteger("Flags"));
		if (tagCompound.hasKey("Warmth"))
			this.setWarmth(tagCompound.getByte("Warmth"));

		if (tagCompound.hasKey("EggProgressSize"))
		{
			int eggProgressSize = (int) tagCompound.getByte("EggProgressSize");
			for (int i = 0; i < eggProgressSize; i++)
				if (tagCompound.hasKey("EggProgress" + i))
				{
					this.setEggProgress(i, tagCompound.getInteger("EggProgress" + i));
					this.setEggFlag(i, tagCompound.getBoolean("EggFlag" + i));
				}
		}
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
		List<String> list = this.listEggName;
		int listSize = list.size();
		buffer.writeInt(listSize);
		for (int i = 0; i < listSize; i++)
			ByteBufUtils.writeUTF8String(buffer, list.get(i));
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		int listSize = additionalData.readInt();
		for (int i = 0; i < listSize; i++)
		{
			String creatureName = ByteBufUtils.readUTF8String(additionalData);
			Creature creature = DEDatabaseRegistry.LIST_DEEXTINCT_CREATURES.get(creatureName);
			if (creature != null && creature instanceof EggCreature)
			{
				EggCreature eggCreature = (EggCreature) creature;
				this.setCreatureName(i, eggCreature.getName());
				this.setEggTexture(i, eggCreature.getEggTexture());
				this.setEggModel(i, eggCreature.getEggModel());
			}
		}
	}

	protected abstract Item getEggNestItem();

	public abstract int getNumberOfEggs();
}
