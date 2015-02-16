package net.multiplemonomials.mobdeathmessages.chat;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.util.NameUtils;
import cpw.mods.fml.common.FMLCommonHandler;

public class EntityLivingDeathMessager 
{
	
	
	public static void showDeathMessage(EntityLiving entityLiving, DamageSource damageSource)
	{
		
		//return if the damage source is disabled in config
		if(damageSource instanceof EntityDamageSource)
		{
			EntityDamageSource entitySource = (EntityDamageSource)damageSource;
			if(entitySource.getEntity() instanceof EntityPlayer)
			{
				if(!ModConfiguration.showPlayerOnMobDeathMessages)
				{
					return;
				}
			}
			else
			{
				if(!ModConfiguration.showMobOnMobDeathMessages)
				{
					return;
				}
			}
		}
		else
		{
			if(!ModConfiguration.showInanimateObjectOnMobDeathMessages)
			{
				return;
			}
		}
		
		//                                         getCombatTracker().getDeathMessage()
		IChatComponent deathMessage = entityLiving.func_110142_aN().func_151521_b();
		
		String messageText = deathMessage.getUnformattedText();
		
		//try to fix entities that aren't named properly in the death message
		messageText = NameUtils.trimEntityNamesInString(messageText);

		
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(messageText));
	}
}
