package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.multiplemonomials.mobdeathmessages.chat.EntityLivingDeathMessager;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.data.EERExtendedPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;

public class LivingDeathEventHandler
{
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		Side currentEffectiveSide = FMLCommonHandler.instance().getEffectiveSide();
		if(currentEffectiveSide == Side.SERVER)
		{
			if(event.entity instanceof EntityPlayer)
			{
				EERExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
			}
			//stop bats in caves from burning to death all the time
			else if((!ModConfiguration.showBatsBurningToDeath) && (event.entityLiving instanceof EntityBat && event.source.isFireDamage()))
			{
				event.setCanceled(true);
			}
			else if(event.entityLiving instanceof EntityLiving)
			{
				EntityLivingDeathMessager.showDeathMessage(((EntityLiving)event.entityLiving), event.source);
			}
		}
		
	}
}
