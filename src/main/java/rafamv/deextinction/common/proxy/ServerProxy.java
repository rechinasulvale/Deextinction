package rafamv.deextinction.common.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerProxy extends CommonProxy
{

	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player)
	{
		if (player instanceof EntityPlayerMP)
		{
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
			return entityPlayerMP.theItemInWorldManager.isCreative();
		}
		return false;
	}
}
