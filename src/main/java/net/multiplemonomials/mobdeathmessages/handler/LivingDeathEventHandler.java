package net.multiplemonomials.mobdeathmessages.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.multiplemonomials.mobdeathmessages.chat.EntityLivingDeathMessager;
import net.multiplemonomials.mobdeathmessages.chat.KillingSpreeMessager;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;

public class LivingDeathEventHandler
{
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		
		if(entity.isServerWorld())
		{

			if(entity instanceof EntityPlayer)
			{
				if(entity instanceof FakePlayer)
				{
					return;
				}
				//handle player death
				if(ModConfiguration.killingSpreePlayersEnabled)
				{
					KillingSpreeMessager.handlePlayerDeath((EntityPlayer) entity);
				}
				
				//handle mob kill
				
				if(ModConfiguration.killingSpreePlayersVsMobsEnabled && event.getSource() instanceof EntityDamageSource)
				{
					EntityDamageSource entityDamageSource = (EntityDamageSource)event.getSource();
					Entity sourceEntity = entityDamageSource.getEntity();
					if(!(sourceEntity instanceof EntityPlayer))
					{
						KillingSpreeMessager.handleMobKill((EntityLiving) sourceEntity);
					}
				}
					
			}
			else if(entity instanceof EntityLiving)
			{
				if(event.getSource() instanceof EntityDamageSource)
				{
					EntityDamageSource entitySource = (EntityDamageSource)event.getSource();
					
					Entity source = entitySource.getEntity();
					
					if(source != null)
					{
						if(source instanceof EntityPlayer)
						{
							EntityPlayer attackingPlayer = (EntityPlayer)entitySource.getEntity();
							if(ModConfiguration.killingSpreePlayersVsMobsEnabled)
							{
								KillingSpreeMessager.handleMobDeath((EntityLiving) entity);
							}
							if(ModConfiguration.killingSpreePlayersEnabled)
							{
								KillingSpreeMessager.handlePlayerKill(attackingPlayer, (EntityLiving) entity);
							}
						}
						else if(source instanceof EntityLiving && ModConfiguration.killingSpreeMobsVsMobsEnabled)
						{
							KillingSpreeMessager.handleMobKill((EntityLiving) entitySource.getEntity());
							KillingSpreeMessager.handleMobDeath((EntityLiving) entity);
						}
					}
				}
				EntityLivingDeathMessager.showDeathMessage(((EntityLiving)entity), event.getSource());
				
			}
		}
		
	}
}
