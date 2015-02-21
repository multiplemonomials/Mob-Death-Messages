package net.multiplemonomials.mobdeathmessages.chat;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import cpw.mods.fml.common.FMLCommonHandler;

public class KillingSpreeMessager
{
	
	/**
	 * handle when a player kills something
	 * @param player
	 * @param EntityLiving the thing they killed
	 */
	public static void handlePlayerKill(EntityPlayer player, EntityLiving deadEntity)
	{
		MDMPlayerData data = MDMPlayerData.get(player);
		if(data.killScore < 0)
		{
			//reset dying spree
			data.killScore = 0;
		}
		++data.killScore;
		
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(data.killScore);
		if(newSpree.ordinal() != data.currentKillingSpree.ordinal())
		{
			//higher ordinals = better killing sprees
			if(newSpree.ordinal() > data.currentKillingSpree.ordinal())
			{
				showPlayerKillingSpreeMessage(player.getCommandSenderName(), newSpree);
			}
			
			data.currentKillingSpree = newSpree;
		}
	}
	
	/**
	 * Handle player death, adjusting their score and dealing with Dying Sprees.
	 * @param player
	 */
	public static void handlePlayerDeath(EntityPlayer player)
	{
		MDMPlayerData data = MDMPlayerData.get(player);
		if(data.killScore > 0)
		{
			//reset dying spree
			data.killScore = 0;
		}
		else
		{
			--data.killScore;
		}
		
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(data.killScore);
		if(newSpree.ordinal() != data.currentKillingSpree.ordinal())
		{
			//lower ordinals = better dying sprees
			if(newSpree.ordinal() < data.currentKillingSpree.ordinal())
			{
				//TODO: show dying spree message
			}
			data.currentKillingSpree = newSpree;
		}
	}

	private static void showPlayerKillingSpreeMessage(String entityName, KillingSpree newSpree)
	{
		StringBuilder message = new StringBuilder();
		message.append("»§f");
		message.append(entityName);
		message.append(" is ");
		message.append(newSpree._text);
		message.append("!");
		
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(message.toString()));
	}
}
