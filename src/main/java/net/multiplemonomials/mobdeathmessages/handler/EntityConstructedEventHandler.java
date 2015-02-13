package net.multiplemonomials.mobdeathmessages.handler;

import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityConstructedEventHandler 
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && MDMPlayerData.get((EntityPlayer) event.entity) == null)
		{
			MDMPlayerData.register((EntityPlayer) event.entity);
		}
		
	}

}
