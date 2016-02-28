package rafamv.deextinction.common.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import rafamv.deextinction.client.gui.GuiBotanicalResearchStation;
import rafamv.deextinction.client.gui.GuiCleaningTable;
import rafamv.deextinction.client.gui.GuiGeneticResearchStation;
import rafamv.deextinction.client.gui.GuiMicroscope;
import rafamv.deextinction.client.gui.eggnests.GuiEggNestLarge;
import rafamv.deextinction.client.gui.eggnests.GuiEggNestMedium;
import rafamv.deextinction.client.gui.eggnests.GuiEggNestSmall;
import rafamv.deextinction.client.gui.eggnests.GuiEggNestTiny;
import rafamv.deextinction.common.container.ContainerBotanicalResearchStation;
import rafamv.deextinction.common.container.ContainerCleaningTable;
import rafamv.deextinction.common.container.ContainerGeneticResearchStation;
import rafamv.deextinction.common.container.ContainerMicroscope;
import rafamv.deextinction.common.container.eggnests.ContainerEggNestLarge;
import rafamv.deextinction.common.container.eggnests.ContainerEggNestMedium;
import rafamv.deextinction.common.container.eggnests.ContainerEggNestSmall;
import rafamv.deextinction.common.container.eggnests.ContainerEggNestTiny;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestLarge;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestMedium;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestSmall;
import rafamv.deextinction.common.entity.eggnests.EntityEggNestTiny;
import rafamv.deextinction.common.tileentity.TileBotanicalResearchStation;
import rafamv.deextinction.common.tileentity.TileCleaningTable;
import rafamv.deextinction.common.tileentity.TileGeneticResearchStation;
import rafamv.deextinction.common.tileentity.TileMicroscope;

public class DEGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == -1)
		{
			Entity entity = world.getEntityByID(x);
			if (entity instanceof EntityEggNestTiny)
			{
				EntityEggNestTiny eggNest = (EntityEggNestTiny) entity;
				return new ContainerEggNestTiny(player.inventory, eggNest.getInventoryBasic(), eggNest);
			}
			else if (entity instanceof EntityEggNestSmall)
			{
				EntityEggNestSmall eggNest = (EntityEggNestSmall) entity;
				return new ContainerEggNestSmall(player.inventory, eggNest.getInventoryBasic(), eggNest);
			}
			else if (entity instanceof EntityEggNestMedium)
			{
				EntityEggNestMedium eggNest = (EntityEggNestMedium) entity;
				return new ContainerEggNestMedium(player.inventory, eggNest.getInventoryBasic(), eggNest);
			}
			else if (entity instanceof EntityEggNestLarge)
			{
				EntityEggNestLarge eggNest = (EntityEggNestLarge) entity;
				return new ContainerEggNestLarge(player.inventory, eggNest.getInventoryBasic(), eggNest);
			}
		}
		else
		{
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof TileGeneticResearchStation)
				return new ContainerGeneticResearchStation(player.inventory, (TileGeneticResearchStation) tileEntity);
			if (tileEntity instanceof TileCleaningTable)
				return new ContainerCleaningTable(player.inventory, (TileCleaningTable) tileEntity);
			if (tileEntity instanceof TileMicroscope)
				return new ContainerMicroscope(player.inventory, (TileMicroscope) tileEntity);
			if (tileEntity instanceof TileBotanicalResearchStation)
				return new ContainerBotanicalResearchStation(player.inventory, (TileBotanicalResearchStation) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (id == -1)
		{
			Entity entity = world.getEntityByID(x);
			if (entity instanceof EntityEggNestTiny)
				return new GuiEggNestTiny(player.inventory, (EntityEggNestTiny) entity);
			else if (entity instanceof EntityEggNestSmall)
				return new GuiEggNestSmall(player.inventory, (EntityEggNestSmall) entity);
			else if (entity instanceof EntityEggNestMedium)
				return new GuiEggNestMedium(player.inventory, (EntityEggNestMedium) entity);
			else if (entity instanceof EntityEggNestLarge)
				return new GuiEggNestLarge(player.inventory, (EntityEggNestLarge) entity);
		}
		else
		{
			BlockPos pos = new BlockPos(x, y, z);
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof TileGeneticResearchStation)
				return new GuiGeneticResearchStation(player.inventory, (TileGeneticResearchStation) tileEntity);
			if (tileEntity instanceof TileCleaningTable)
				return new GuiCleaningTable(player.inventory, (TileCleaningTable) tileEntity);
			if (tileEntity instanceof TileMicroscope)
				return new GuiMicroscope(player.inventory, (TileMicroscope) tileEntity);
			if (tileEntity instanceof TileBotanicalResearchStation)
				return new GuiBotanicalResearchStation(player.inventory, (TileBotanicalResearchStation) tileEntity);
		}
		return null;
	}
}
