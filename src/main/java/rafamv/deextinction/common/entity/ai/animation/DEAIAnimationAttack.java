package rafamv.deextinction.common.entity.ai.animation;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedCreature;

public class DEAIAnimationAttack extends DEAIAnimation
{
	private EntityLivingBase creatureTarget;
	private int damageTick;
	private int duration;

	public DEAIAnimationAttack(EntityDeExtinctedAgeable creature, int duration, int damageTick)
	{
		super(creature);
		this.damageTick = (damageTick > duration) ? duration : damageTick;
		this.duration = duration;
		this.creatureTarget = null;
	}

	@Override
	public int getAnimID()
	{
		return DEAnimationList.ATTACKING;
	}

	@Override
	public boolean isAutomatic()
	{
		return true;
	}

	@Override
	public int getDuration()
	{
		return this.duration;
	}

	@Override
	public void startExecuting()
	{
		super.startExecuting();
		this.creatureTarget = this.getEntity().getAttackTarget();
	}

	@Override
	public void updateTask()
	{
		if (this.getEntityAsIAnimatedEntity().getAnimTick() == this.damageTick && this.creatureTarget != null)
		{
			EntityDeExtinctedCreature creature = this.getEntity();
			double distanceSqFromTarget = creature.getDistanceSq(this.creatureTarget.posX, this.creatureTarget.getEntityBoundingBox().minY, this.creatureTarget.posZ);
			double minDistanceSqToAttack = (double) (10.0F * (creature.width * creature.width + this.creatureTarget.width * this.creatureTarget.width));
			if (distanceSqFromTarget <= minDistanceSqToAttack)
				this.creatureTarget.attackEntityFrom(DamageSource.causeMobDamage(creature), (float) creature.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
		}
	}

	@Override
	public void resetTask()
	{
		super.resetTask();
		this.creatureTarget = null;
	}
}
