package net.multiplemonomials.mobdeathmessages.chat;

import java.util.HashMap;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import net.multiplemonomials.mobdeathmessages.util.NameUtils;
import cpw.mods.fml.common.FMLCommonHandler;

public class KillingSpreeMessager
{
	
	/**
	 * HashMap to keep track of how many kills types of mob have gotten
	 */
	public static HashMap<String, Integer> mobScores = new HashMap<String, Integer>();
	
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
				showKillingSpreeMessage(player.getCommandSenderName(), false, newSpree);
			}
			
			data.currentKillingSpree = newSpree;
		}
	}
	
	/**
	 * handle killing sprees when a mob kills something
	 * @param attackingEntity
	 */
	public static void handleMobKill(EntityLiving attackingEntity)
	{
		// I think this is preferred to getCommandSenderName() because it is unaffected by language files.
		String entityName = EntityList.getEntityString(attackingEntity);
		
		int killScore = 0;
		
		if(mobScores.containsKey(entityName))
		{
			killScore = mobScores.get(entityName);
		}
		if(killScore < 0)
		{
			//reset dying spree
			killScore = 0;
		}
		++killScore;
		
		KillingSpree previousSpree = KillingSpree.getKillingSpreeLevel(killScore - 1);
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(killScore);
		if(newSpree.ordinal() != previousSpree.ordinal())
		{
			//higher ordinals = better killing sprees
			if(newSpree.ordinal() > previousSpree.ordinal())
			{
	
				String friendlyPluralMobName = NameUtils.makePlural(NameUtils.trimEntityNamesInString(attackingEntity.getCommandSenderName()));
				showKillingSpreeMessage(friendlyPluralMobName, true, newSpree);
			}
			
		}
		
		mobScores.put(entityName, killScore);
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

	/**
	 * 
	 * @param entityName
	 * @param plural Whether or not the entityName is plural.
	 * @param newSpree
	 */
	private static void showKillingSpreeMessage(String entityName, boolean plural, KillingSpree newSpree)
	{
		StringBuilder message = new StringBuilder();
		message.append("»§f");
		message.append(entityName);
		message.append(plural ? " are " : " is ");
		message.append(newSpree._text);
		message.append("!");
		
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(message.toString()));
	}
}
