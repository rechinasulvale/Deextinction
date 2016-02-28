package rafamv.deextinction.common.entity.ai.predicate;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import rafamv.deextinction.common.entity.base.EntityDeExtinctedAgeable;

import com.google.common.base.Predicate;

public class DETargetSelector implements Predicate
{
	private List<TargetSecondary> targetListSecondary = new ArrayList<TargetSecondary>();
	private List<TargetAgeable> targetListAgeable = new ArrayList<TargetAgeable>();
	private EntityDeExtinctedAgeable attackerEntity;

	public DETargetSelector(EntityDeExtinctedAgeable attackerEntity, Class targetClass, double distance, byte targetMinStage, byte targetMaxStage, byte attackerMinStage, byte attackerMaxStage)
	{
		this.attackerEntity = attackerEntity;
		this.targetListAgeable.add(new TargetAgeable(targetClass, distance, targetMinStage, targetMaxStage, attackerMinStage, attackerMaxStage));
	}

	public DETargetSelector(EntityDeExtinctedAgeable attackerEntity, Class targetClass, double distance, byte attackerMinStage, byte attackerMaxStage)
	{
		this.attackerEntity = attackerEntity;
		this.targetListSecondary.add(new TargetSecondary(targetClass, distance, attackerMinStage, attackerMaxStage));
	}

	public DETargetSelector(EntityDeExtinctedAgeable attackerEntity, TargetAgeable... targetsAgeable)
	{
		this.attackerEntity = attackerEntity;
		for (TargetAgeable target : targetsAgeable)
			this.targetListAgeable.add(target);
	}

	public DETargetSelector(EntityDeExtinctedAgeable attackerEntity, TargetSecondary... targetsSecondary)
	{
		this.attackerEntity = attackerEntity;
		for (TargetSecondary target : targetsSecondary)
			this.targetListSecondary.add(target);
	}

	public DETargetSelector(EntityDeExtinctedAgeable attackerEntity, TargetAgeable[] targetsAgeable, TargetSecondary[] targetsSecondary)
	{
		this.attackerEntity = attackerEntity;
		for (TargetAgeable target : targetsAgeable)
			this.targetListAgeable.add(target);
		for (TargetSecondary target : targetsSecondary)
			this.targetListSecondary.add(target);
	}

	@Override
	public boolean apply(Object obj)
	{
		if (obj instanceof EntityDeExtinctedAgeable)
		{
			EntityDeExtinctedAgeable targetEntity = (EntityDeExtinctedAgeable) obj;
			if (targetEntity != null)
			{
				for (TargetAgeable target : this.targetListAgeable)
				{
					if (target != null && target.shouldAttackTarget(this.attackerEntity, targetEntity) && targetEntity.getDistanceSqToEntity(this.attackerEntity) < target.getDistanceSq())
						return true;
				}
			}
		}
		else
		{
			if (obj instanceof EntityLivingBase)
			{
				EntityLivingBase targetEntity = (EntityLivingBase) obj;
				if (targetEntity != null)
				{
					for (TargetSecondary target : this.targetListSecondary)
					{
						if (target != null && target.shouldAttackTarget(this.attackerEntity, targetEntity) && targetEntity.getDistanceSqToEntity(this.attackerEntity) < target.getDistanceSq())
							return true;
					}
				}
			}
		}
		return false;
	}

}
