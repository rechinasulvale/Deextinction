package rafamv.deextinction.common.registry;

import java.util.Random;

import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityAepyornis;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityArchaeorhynchus;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityCitipati;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityDeinonychus;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityDilong;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityDinornis;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityDromaeosaurus;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityGuanlong;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityKelenken;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityQuagga;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityRaphus;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntitySinovenator;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityTroodon;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityUtahraptor;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityVelociraptor;
import rafamv.deextinction.client.renderer.entity.creatures.RenderEntityZhenyuanlong;
import rafamv.deextinction.client.renderer.entity.eggnests.RenderEntityEggNestLarge;
import rafamv.deextinction.client.renderer.entity.eggnests.RenderEntityEggNestMedium;
import rafamv.deextinction.client.renderer.entity.eggnests.RenderEntityEggNestSmall;
import rafamv.deextinction.client.renderer.entity.eggnests.RenderEntityEggNestTiny;
import rafamv.deextinction.common.database.creatures.Aepyornis;
import rafamv.deextinction.common.database.creatures.Archaeorhynchus;
import rafamv.deextinction.common.database.creatures.Citipati;
import rafamv.deextinction.common.database.creatures.Deinonychus;
import rafamv.deextinction.common.database.creatures.Dilong;
import rafamv.deextinction.common.database.creatures.Dinornis;
import rafamv.deextinction.common.database.creatures.Dromaeosaurus;
import rafamv.deextinction.common.database.creatures.Guanlong;
import rafamv.deextinction.common.database.creatures.Kelenken;
import rafamv.deextinction.common.database.creatures.Quagga;
import rafamv.deextinction.common.database.creatures.Raphus;
import rafamv.deextinction.common.database.creatures.Sinovenator;
import rafamv.deextinction.common.database.creatures.Troodon;
import rafamv.deextinction.common.database.creatures.Utahraptor;
import rafamv.deextinction.common.database.creatures.Velociraptor;
import rafamv.deextinction.common.database.creatures.Zhenyuanlong;
import rafamv.deextinction.common.entity.creature.EntityAepyornis;
import rafamv.deextinction.common.entity.creature.EntityArchaeorhynchus;
import rafamv.deextinction.common.entity.creature.EntityCitipati;
import rafamv.deextinction.common.entity.creature.EntityDeinonychus;
import rafamv.deextinction.common.entity.creature.EntityDilong;
import rafamv.deextinction.common.entity.creature.EntityDinornis;
import rafamv.deextinction.common.entity.creature.EntityDromaeosaurus;
import rafamv.deextinction.common.entity.creature.EntityGuanlong;
import rafamv.deextinction.common.entity.creature.EntityKelenken;
import rafamv.deextinction.common.entity.creature.EntityQuagga;
import rafamv.deextinction.common.entity.creature.EntityRaphus;
import rafamv.deextinction.common.entity.creature.EntitySinovenator;
import rafamv.deextinction.common.entity.creature.EntityTroodon;
import rafamv.deextinction.common.entity.creature.EntityUtahraptor;
import rafamv.deextinction.common.entity.creature.EntityVelociraptor;
import rafamv.deextinction.common.entity.creature.EntityZhenyuanlong;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestLarge;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestMedium;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestSmall;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestTiny;

public class DEEntityRegistry
{

	public static void preInitCommon()
	{
		DEEntityRegistry.registerEntity(EntityEggNestTiny.class, "egg_nest_tiny");
		DEEntityRegistry.registerEntity(EntityEggNestSmall.class, "egg_nest_small");
		DEEntityRegistry.registerEntity(EntityEggNestMedium.class, "egg_nest_medium");
		DEEntityRegistry.registerEntity(EntityEggNestLarge.class, "egg_nest_large");

		DEEntityRegistry.registerEntityWithSpawnEgg(EntityRaphus.class, Raphus.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityKelenken.class, Kelenken.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityDinornis.class, Dinornis.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityAepyornis.class, Aepyornis.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityArchaeorhynchus.class, Archaeorhynchus.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityDeinonychus.class, Deinonychus.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntitySinovenator.class, Sinovenator.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityUtahraptor.class, Utahraptor.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityCitipati.class, Citipati.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityTroodon.class, Troodon.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityZhenyuanlong.class, Zhenyuanlong.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityDromaeosaurus.class, Dromaeosaurus.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityVelociraptor.class, Velociraptor.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityDilong.class, Dilong.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityGuanlong.class, Guanlong.NAME);
		DEEntityRegistry.registerEntityWithSpawnEgg(EntityQuagga.class, Quagga.NAME);
	}

	public static void initCommon()
	{

	}

	public static void postInitCommon()
	{

	}

	public static void preInitClientOnly()
	{

	}

	public static void initClientOnly()
	{
		DEEntityRegistry.registerEntityRender(EntityEggNestTiny.class, new RenderEntityEggNestTiny());
		DEEntityRegistry.registerEntityRender(EntityEggNestSmall.class, new RenderEntityEggNestSmall());
		DEEntityRegistry.registerEntityRender(EntityEggNestMedium.class, new RenderEntityEggNestMedium());
		DEEntityRegistry.registerEntityRender(EntityEggNestLarge.class, new RenderEntityEggNestLarge());

		DEEntityRegistry.registerEntityRender(EntityRaphus.class, new RenderEntityRaphus());
		DEEntityRegistry.registerEntityRender(EntityKelenken.class, new RenderEntityKelenken());
		DEEntityRegistry.registerEntityRender(EntityDinornis.class, new RenderEntityDinornis());
		DEEntityRegistry.registerEntityRender(EntityAepyornis.class, new RenderEntityAepyornis());
		DEEntityRegistry.registerEntityRender(EntityArchaeorhynchus.class, new RenderEntityArchaeorhynchus());
		DEEntityRegistry.registerEntityRender(EntityDeinonychus.class, new RenderEntityDeinonychus());
		DEEntityRegistry.registerEntityRender(EntitySinovenator.class, new RenderEntitySinovenator());
		DEEntityRegistry.registerEntityRender(EntityUtahraptor.class, new RenderEntityUtahraptor());
		DEEntityRegistry.registerEntityRender(EntityCitipati.class, new RenderEntityCitipati());
		DEEntityRegistry.registerEntityRender(EntityTroodon.class, new RenderEntityTroodon());
		DEEntityRegistry.registerEntityRender(EntityZhenyuanlong.class, new RenderEntityZhenyuanlong());
		DEEntityRegistry.registerEntityRender(EntityDromaeosaurus.class, new RenderEntityDromaeosaurus());
		DEEntityRegistry.registerEntityRender(EntityVelociraptor.class, new RenderEntityVelociraptor());
		DEEntityRegistry.registerEntityRender(EntityDilong.class, new RenderEntityDilong());
		DEEntityRegistry.registerEntityRender(EntityGuanlong.class, new RenderEntityGuanlong());

		DEEntityRegistry.registerEntityRender(EntityQuagga.class, new RenderEntityQuagga());
	}

	public static void postInitClientOnly()
	{

	}

	private static void registerEntity(Class Clazz, String name)
	{
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(Clazz, name, entityId);
		EntityRegistry.registerModEntity(Clazz, name, entityId, DeExtinction.instance, 64, 1, true);
	}

	private static void registerEntityWithSpawnEgg(Class Clazz, String name)
	{
		Random random = new Random(name.hashCode());
		int mainColor = random.nextInt();
		int subColor = random.nextInt();

		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(Clazz, name, entityId, mainColor, subColor);
		EntityRegistry.registerModEntity(Clazz, name, entityId, DeExtinction.instance, 64, 1, true);
	}

	private static void registerEntityWithSpawnEgg(Class Clazz, String name, int color, int subcolor)
	{
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(Clazz, name, entityId, color, subcolor);
		EntityRegistry.registerModEntity(Clazz, name, entityId, DeExtinction.instance, 64, 1, true);
	}

	private static void registerEntityRender(Class EntityClass, Render renderer)
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityClass, renderer);
	}
}
