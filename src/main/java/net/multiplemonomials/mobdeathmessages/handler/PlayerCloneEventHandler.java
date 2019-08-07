package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.multiplemonomials.mobdeathmessages.MobDeathMessages;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import net.multiplemonomials.mobdeathmessages.data.MissingCapabilityExceptionSupplier;

public class PlayerCloneEventHandler
{
	@SubscribeEvent
	public void onEntityJoinWorld(PlayerEvent.Clone event)
	{
		if(!event.getEntityPlayer().world.isRemote && event.isWasDeath())
		{
			NBTTagCompound mdmData = MDMPlayerData.getStorageInstance().writeNBT(event.getOriginal().getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null).orElseThrow(MissingCapabilityExceptionSupplier.INSTANCE));
			MDMPlayerData.getStorageInstance().readNBT(event.getEntityPlayer().getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null).orElseThrow(MissingCapabilityExceptionSupplier.INSTANCE), mdmData);
		}
	}
}
