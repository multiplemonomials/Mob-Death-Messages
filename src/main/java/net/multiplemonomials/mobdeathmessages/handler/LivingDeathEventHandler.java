package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.multiplemonomials.mobdeathmessages.chat.EntityLivingDeathMessager;
import net.multiplemonomials.mobdeathmessages.chat.KillingSpreeMessager;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
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
				if(event.entity instanceof FakePlayer)
				{
					return;
				}
				//handle player death
				if(ModConfiguration.killingSpreePlayersEnabled)
				{
					KillingSpreeMessager.handlePlayerDeath((EntityPlayer) event.entity);
				}
				
				//handle mob kill
				
				if(ModConfiguration.killingSpreePlayersVsMobsEnabled && event.source instanceof EntityDamageSource)
				{
					EntityDamageSource entityDamageSource = (EntityDamageSource)event.source;
					Entity sourceEntity = entityDamageSource.getEntity();
					if(!(sourceEntity instanceof EntityPlayer))
					{
						KillingSpreeMessager.handleMobKill((EntityLiving) sourceEntity);
					}
				}
					
				//deal with extended player data
				MDMPlayerData.saveProxyData((EntityPlayer) event.entity);
			}
			else if(event.entityLiving instanceof EntityLiving)
			{
				if(event.source instanceof EntityDamageSource)
				{
					EntityDamageSource entitySource = (EntityDamageSource)event.source;
					
					Entity source = entitySource.getEntity();
					
					if(source != null)
					{
						if(source instanceof EntityPlayer)
						{
							EntityPlayer attackingPlayer = (EntityPlayer)entitySource.getEntity();
							if(ModConfiguration.killingSpreePlayersVsMobsEnabled)
							{
								KillingSpreeMessager.handleMobDeath((EntityLiving) event.entityLiving);
							}
							if(ModConfiguration.killingSpreePlayersEnabled)
							{
								KillingSpreeMessager.handlePlayerKill(attackingPlayer, (EntityLiving) event.entityLiving);
							}
						}
						else if(source instanceof EntityLiving && ModConfiguration.killingSpreeMobsVsMobsEnabled)
						{
							KillingSpreeMessager.handleMobKill((EntityLiving) entitySource.getEntity());
							KillingSpreeMessager.handleMobDeath((EntityLiving) event.entityLiving);
						}
					}
				}
				EntityLivingDeathMessager.showDeathMessage(((EntityLiving)event.entityLiving), event.source);
				
			}
		}
		
	}
}
