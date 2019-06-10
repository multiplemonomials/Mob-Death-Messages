package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.multiplemonomials.mobdeathmessages.MobDeathMessages;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;

public class PlayerCloneEventHandler
{
	@SubscribeEvent
	public void onEntityJoinWorld(PlayerEvent.Clone event)
	{
		if(!event.getEntityPlayer().world.isRemote && event.isWasDeath())
		{
			NBTTagCompound mdmData = MDMPlayerData.getStorageInstance().writeNBT(event.getOriginal().getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null));
			MDMPlayerData.getStorageInstance().readNBT(event.getEntityPlayer().getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null), mdmData);
		}
	}
}
