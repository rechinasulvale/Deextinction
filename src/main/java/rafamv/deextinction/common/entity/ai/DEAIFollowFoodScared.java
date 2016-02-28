package rafamv.deextinction.common.entity.ai;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class DEAIFollowFoodScared extends EntityAIBase
{
	private List<Item> foodList = new ArrayList<Item>();
	private EntityDeExtinctedAgeable creature;
	private EntityPlayer player;
	private World worldObj;
	private boolean scaredByPlayerMovement;
	private boolean isRunning;
	private double targetX;
	private double targetY;
	private double targetZ;
	private double speed;
	private double rotationPitchOfThePlayer;
	private double rotationYawOfThePlayer;
	private int chance;
	private int timer;

	public DEAIFollowFoodScared(EntityDeExtinctedAgeable creature, int chance, boolean scaredByPlayerMovement, Item... items)
	{
		this.creature = creature;
		this.scaredByPlayerMovement = scaredByPlayerMovement;
		this.worldObj = creature.worldObj;
		this.chance = chance;
		for (Item item : items)
			this.foodList.add(item);
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.timer > 0)
		{
			this.timer--;
			return false;
		}
		else
		{
			if (this.worldObj.rand.nextInt(this.chance) == 0)
			{
				if (this.creature.isSitting() || this.creature.isSleeping() || this.creature.isFleeing() || this.creature.isFlying() || this.creature.isTakingOff() || this.creature.isOnNest() || this.creature.isSocializing() || this.creature.isStalking() || this.creature.isSocializing()
						|| this.creature.isStalking())
					return false;

				this.player = this.worldObj.getClosestPlayerToEntity(this.creature, 10.0D);
				if (this.player == null)
					return false;
				else
				{
					ItemStack itemstack = this.player.getCurrentEquippedItem();
					if (itemstack == null)
						return false;
					else if (this.creature.getAttackTarget() == null)
						return false;
					return this.foodList.contains(itemstack.getItem());
				}
			}
		}
		return false;
	}

	@Override
	public void startExecuting()
	{
		if (this.creature.isSitting())
			this.creature.setSitting(false, null);

		if (this.creature.isSleeping())
			this.creature.setSleeping(false);

		if (this.creature.isFleeing())
			this.creature.setFleeing(false);

		if (this.creature.isOnNest())
			this.creature.setOnNest(false);

		if (this.creature.isFlying())
			this.creature.setFlying(false);

		if (this.creature.isEating())
			this.creature.setEating(false);

		if (this.creature.isTakingOff())
			this.creature.setTakingOff(false);

		if (this.creature.isSocializing())
			this.creature.setSocializing(false);

		if (this.creature.isStalking())
			this.creature.setStalking(false);

		this.speed = this.creature.getCreatureSpeed();
		this.targetX = this.player.posX;
		this.targetY = this.player.posY;
		this.targetZ = this.player.posZ;
		this.isRunning = true;
	}

	@Override
	public boolean continueExecuting()
	{
		if (this.scaredByPlayerMovement)
		{
			if (this.creature.getDistanceSqToEntity(this.player) < 36.0D)
			{
				if (this.player.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
					return false;
				if (Math.abs(this.player.rotationPitch - this.rotationPitchOfThePlayer) > 5.0D || Math.abs(this.player.rotationYaw - this.rotationYawOfThePlayer) > 5.0D)
					return false;
			}
			else
			{
				this.targetX = this.player.posX;
				this.targetY = this.player.posY;
				this.targetZ = this.player.posZ;
			}
			this.rotationPitchOfThePlayer = this.player.rotationPitch;
			this.rotationYawOfThePlayer = this.player.rotationYaw;
		}
		return this.shouldExecute();
	}

	@Override
	public void resetTask()
	{
		this.creature.getNavigator().clearPathEntity();
		this.isRunning = false;
		this.player = null;
		this.timer = 100;
	}

	@Override
	public void updateTask()
	{
		this.creature.getLookHelper().setLookPositionWithEntity(this.player, 30.0F, this.creature.getVerticalFaceSpeed());
		if (this.creature.getDistanceSqToEntity(this.player) < 6.25D)
			this.creature.getNavigator().clearPathEntity();
		else
			this.creature.getNavigator().tryMoveToEntityLiving(this.player, this.speed);
	}

	public boolean isRunning()
	{
		return this.isRunning;
	}
}
