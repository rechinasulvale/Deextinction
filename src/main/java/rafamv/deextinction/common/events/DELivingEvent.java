package rafamv.deextinction.common.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rafamv.deextinction.common.entity.base.EntityPregnantMammal;
import rafamv.deextinction.common.entity.base.EntityPregnantMammalKeys;
import rafamv.deextinction.common.entity.creature.EntityQuagga;

public class DELivingEvent
{
	public static final boolean isPregnantMammal(Entity entity)
	{
		return entity instanceof EntityHorse || entity instanceof EntityQuagga;
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (this.isPregnantMammal(event.entity) && EntityPregnantMammal.get((EntityLivingBase) event.entity) == null)
			EntityPregnantMammal.register((EntityLivingBase) event.entity);
	}

	@SubscribeEvent
	public void onEntityLiving(LivingUpdateEvent event)
	{
		EntityPregnantMammal mammal = EntityPregnantMammal.get((EntityLivingBase) event.entityLiving);
		if (mammal != null && !mammal.getBabyName().equals(EntityPregnantMammalKeys.NO_EMBRYO))
		{
			if (!event.entityLiving.worldObj.isRemote)
			{
				System.out.println("Updating... " + mammal.getPregnancyProgress());
				if (mammal.getPregnancyProgress() < EntityPregnantMammalKeys.PREGNANCY_MAX_PROGRESS)
					mammal.increasePregnancyProgress();
				else
				{
					mammal.born();
					mammal.setBabyName(EntityPregnantMammalKeys.NO_EMBRYO);
					mammal.setResearchProgress((byte) 0);
					mammal.setBabyTexture((byte) 0);
					mammal.setBabyGender(true);
					mammal.setPregnancyProgress(0);
				}
			}
			event.entityLiving.onLivingUpdate();
		}
	}
}
