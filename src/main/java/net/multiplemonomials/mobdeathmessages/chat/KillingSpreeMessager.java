package net.multiplemonomials.mobdeathmessages.chat;

import java.util.HashMap;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import net.multiplemonomials.mobdeathmessages.reference.Names;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;
import net.multiplemonomials.mobdeathmessages.util.NameUtils;

import org.atteo.evo.inflector.English;

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
				showKillingSpreeMessage(NameUtils.trimEntityNamesInString(player.getName()), false, newSpree);
				
				//give the player XP
				int expAmount = ModConfiguration.xpForKillingSpree * (1 << (newSpree.ordinal() - KillingSpree.KILLINGSPREE.ordinal()));
				LogHelper.info("Giving " + player.getName() + " " + expAmount + " xp for their " + newSpree.toString().toLowerCase());
				player.addExperience(expAmount);
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
		String entityName = null;
		
		try
		{
			// I think this is preferred to getCommandSenderName() because it is unaffected by language files.
			entityName = EntityList.getEntityString(attackingEntity);
		}
		catch(NullPointerException error)
		{
			//some entity(s) are not in the list and will cause getEntityString() to crash
			System.out.println("Error: could not get name of entity: " + (attackingEntity == null ? "null" : attackingEntity.getClass().getName()));
			return;
		}
		
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
	
				String friendlyPluralMobName = English.plural(NameUtils.trimEntityNamesInString(attackingEntity.getName()));
				showKillingSpreeMessage(friendlyPluralMobName, true, newSpree);
			}
			
		}
		
		mobScores.put(entityName, killScore);
	}
	
	/**
	 * handle killing sprees when a mob dies
	 * @param attackingEntity the mob that died
	 */
	public static void handleMobDeath(EntityLiving deadEntity)
	{
		// I think this is preferred to getCommandSenderName() because it is unaffected by language files.
		String entityName = EntityList.getEntityString(deadEntity);
		
		int killScore = 0;
		
		if(mobScores.containsKey(entityName))
		{
			killScore = mobScores.get(entityName);
		}
		if(killScore > 0)
		{
			//reset killing spree
			killScore = 0;
		}
		--killScore;
		
		KillingSpree previousSpree = KillingSpree.getKillingSpreeLevel(killScore + 1);
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(killScore);
		if(newSpree.ordinal() != previousSpree.ordinal())
		{
			//higher ordinals = worse killing sprees
			if(newSpree.ordinal() < previousSpree.ordinal())
			{
				String friendlyPluralMobName = English.plural(NameUtils.trimEntityNamesInString(deadEntity.getName()));
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
			if(newSpree.ordinal() < data.currentKillingSpree.ordinal() && newSpree != KillingSpree.NONE)
			{
				showKillingSpreeMessage(NameUtils.trimEntityNamesInString(player.getName()), false, newSpree);
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
		message.append(StatCollector.translateToLocal(Names.KillingSprees.MESSAGEPREFIX));
		message.append(entityName);
		message.append(EnumChatFormatting.WHITE.toString()); //"escape" color codes in mob names
		message.append(plural ? " are " : " is ");
		message.append(StatCollector.translateToLocal(newSpree.getText(plural)));
		
		FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendChatMsg(new ChatComponentText(message.toString()));
	}
}
