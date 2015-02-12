package net.multiplemonomials.mobdeathmessages.chat;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import cpw.mods.fml.common.FMLCommonHandler;

public class EntityLivingDeathMessager 
{
	
	public static void showDeathMessage(EntityLiving entityLiving, DamageSource damageSource)
	{
		//                                         getCombatTracker().getDeathMessage()
		IChatComponent deathMessage = entityLiving.func_110142_aN().func_151521_b();
		
		String messageText = deathMessage.getUnformattedText();
		
		//try to fix entities that aren't named properly in the death message
		messageText.replaceAll("entity.", "");
		messageText.replaceAll(".name", "");

		
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(messageText));
	}
}
