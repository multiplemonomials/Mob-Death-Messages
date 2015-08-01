package net.multiplemonomials.mobdeathmessages.chat;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.util.NameUtils;

public class EntityLivingDeathMessager 
{
	/**
	 * Return whether a message should be shown for the dead mob based on the circumstances of death
	 * and the configuration of the mod
	 * @param deadEntity
	 * @param damageSource
	 * @return
	 */
	private static boolean shouldShowMessage(EntityLiving deadEntity, DamageSource damageSource)
	{
		boolean retval = false;
		
		//if we're only supposed to show messages involving named mobs, that gets priority
		if(ModConfiguration.showNamedMobsOnly)
		{
			if(ModConfiguration.showMobOnMobDeathMessages)
			{
				//check attacker
				if(damageSource instanceof EntityDamageSource)
				{
					EntityDamageSource entitySource = (EntityDamageSource)damageSource;
					retval = entitySource.getEntity() instanceof EntityLiving && ((EntityLiving)entitySource.getEntity()).hasCustomName();
				}
			}
			
			//check attackee
			if(!retval)
			{
				retval = deadEntity.hasCustomName();
			}
		}
		else
		{
			
			if(damageSource instanceof EntityDamageSource)
			{
				EntityDamageSource entitySource = (EntityDamageSource)damageSource;
				if(entitySource.getEntity() instanceof EntityPlayer)
				{
					retval = ModConfiguration.showPlayerOnMobDeathMessages;
				}
				else
				{
					retval = ModConfiguration.showMobOnMobDeathMessages;
				}
			}
			else
			{
				if(!ModConfiguration.showBatsDyingOfNaturalCauses && deadEntity instanceof EntityBat)
				{
					retval = false;
				}
				else
				{
					retval = ModConfiguration.showInanimateObjectOnMobDeathMessages;
				}
			}
		}
		
		return retval;
	}
	
	public static void showDeathMessage(EntityLiving deadEntity, DamageSource damageSource)
	{
		if(shouldShowMessage(deadEntity, damageSource))
		{
			//                                                          getDeathMessage()
			IChatComponent deathMessage = deadEntity.getCombatTracker().func_151521_b();
			
			String messageText = deathMessage.getUnformattedText();
			
			//try to fix entities that aren't named properly in the death message
			messageText = NameUtils.trimEntityNamesInString(messageText);
	
			//stop this silliness
			if(messageText.equals("Squid drowned"))
			{
				messageText = "Squid asphyxiated";
			}
			
			if(messageText.equals("Blaze drowned"))
			{
				messageText = "Blaze tried to swim in water";
			}
			
			FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(messageText));
		}
	}
}
