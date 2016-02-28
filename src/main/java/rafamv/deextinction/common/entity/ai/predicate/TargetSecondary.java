package rafamv.deextinction.common.entity.ai.predicate;

import net.minecraft.entity.EntityLivingBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

public class TargetSecondary
{
	private Class targetClass;
	private byte attackerMinStage;
	private byte attackerMaxStage;
	private double distanceSq;

	public TargetSecondary(Class targetClass, double distance, int attackerMinStage, int attackerMaxStage)
	{
		this.targetClass = targetClass;
		this.attackerMinStage = (byte) attackerMinStage;
		this.attackerMaxStage = (byte) attackerMaxStage;
		this.distanceSq = distance * distance;
	}

	public boolean shouldAttackTarget(EntityDeExtinctedAgeable attacker, EntityLivingBase target)
	{
		if (target.getClass() == this.targetClass)
		{
			byte attackerStage = attacker.getGrowthStage();
			if (attackerStage >= this.attackerMinStage && attackerStage <= this.attackerMaxStage)
				return true;
		}
		return false;
	}

	public double getDistanceSq()
	{
		return this.distanceSq;
	}
}
