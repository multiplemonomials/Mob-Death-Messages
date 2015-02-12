package net.multiplemonomials.mobdeathmessages.handler;

import net.multiplemonomials.mobdeathmessages.data.EERExtendedPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EntityJoinWorldHandler
{
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			EERExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
		}
	}
}
