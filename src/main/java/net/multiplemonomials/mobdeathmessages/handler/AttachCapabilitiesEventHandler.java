package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.multiplemonomials.mobdeathmessages.data.MDMDataCapabilityProvider;
import net.multiplemonomials.mobdeathmessages.reference.Names;

public class AttachCapabilitiesEventHandler 
{
	@SubscribeEvent
	public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event)
	{
		if (event.getObject() instanceof EntityPlayer)
		{
			event.addCapability(Names.Data.MDMPLAYERDATA, new MDMDataCapabilityProvider());
		}
		
	}

}
