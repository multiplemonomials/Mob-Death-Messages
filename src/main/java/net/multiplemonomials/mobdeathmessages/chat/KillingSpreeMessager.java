package net.multiplemonomials.mobdeathmessages.chat;

import java.util.HashMap;

import org.atteo.evo.inflector.English;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.multiplemonomials.mobdeathmessages.MobDeathMessages;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.data.IMDMPlayerData;
import net.multiplemonomials.mobdeathmessages.reference.Names;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;
import net.multiplemonomials.mobdeathmessages.util.NameUtils;

public class KillingSpreeMessager
{
	
	/**
	 * HashMap of entity class names and kill scores to keep track of how many kills types of mob have gotten
	 */
	public static HashMap<String, Integer> mobScores = new HashMap<String, Integer>();
	
	/**
	 * handle when a player kills something
	 * @param player
	 * @param EntityLiving the thing they killed
	 */
	public static void handlePlayerKill(EntityPlayer player, EntityLiving deadEntity)
	{
		IMDMPlayerData data = player.getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null);
		
		if(data.getKillScore() < 0)
		{
			//reset dying spree
			data.setKillScore(0);
		}		
		
		data.setKillScore(data.getKillScore() + 1);
		
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(data.getKillScore());
		if(newSpree.ordinal() != data.getCurrentKillingSpree().ordinal())
		{
			//higher ordinals = better killing sprees
			if(newSpree.ordinal() > data.getCurrentKillingSpree().ordinal())
			{
				showKillingSpreeMessage(NameUtils.trimEntityNamesInString(player.getName()), false, newSpree);
				
				//give the player XP
				int expAmount = ModConfiguration.xpForKillingSpree * (1 << (newSpree.ordinal() - KillingSpree.KILLINGSPREE.ordinal()));
				LogHelper.info("Giving " + player.getName() + " " + expAmount + " xp for their " + newSpree.toString().toLowerCase());
				player.addExperience(expAmount);
			}
			
			data.setCurrentKillingSpree(newSpree);
		}
	}
	
	/**
	 * handle killing sprees when a mob kills something
	 * @param attackingEntity
	 */
	public static void handleMobKill(EntityLiving attackingEntity)
	{
		String entityClass = attackingEntity.getClass().getSimpleName();
		
		int killScore = 0;
		
		if(mobScores.containsKey(entityClass))
		{
			killScore = mobScores.get(entityClass);
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
	
				String friendlyPluralMobName = English.plural(NameUtils.getEntityNameForDisplay(attackingEntity));
				showKillingSpreeMessage(friendlyPluralMobName, true, newSpree);
			}
			
		}
		
		mobScores.put(entityClass, killScore);
	}
	
	/**
	 * handle killing sprees when a mob dies
	 * @param attackingEntity the mob that died
	 */
	public static void handleMobDeath(EntityLiving deadEntity)
	{
		String entityString = deadEntity.getClass().getSimpleName();
		
		int killScore = 0;
		
		if(mobScores.containsKey(entityString))
		{
			killScore = mobScores.get(entityString);
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
				String friendlyPluralMobName = English.plural(NameUtils.getEntityNameForDisplay(deadEntity));
				showKillingSpreeMessage(friendlyPluralMobName, true, newSpree);
			}
		}
		
		mobScores.put(entityString, killScore);
	}
	
	/**
	 * Handle player death, adjusting their score and dealing with Dying Sprees.
	 * @param player
	 */
	public static void handlePlayerDeath(EntityPlayer player)
	{
		IMDMPlayerData data = player.getCapability(MobDeathMessages.MDM_DATA_CAPABILITY, null);
		if(data.getKillScore() > 0)
		{
			//reset dying spree
			data.setKillScore(0);
		}
		else
		{
			data.setKillScore(data.getKillScore() - 1);
		}
		
		KillingSpree newSpree = KillingSpree.getKillingSpreeLevel(data.getKillScore());
		if(newSpree.ordinal() != data.getCurrentKillingSpree().ordinal())
		{
			//lower ordinals = better dying sprees
			if(newSpree.ordinal() < data.getCurrentKillingSpree().ordinal() && newSpree != KillingSpree.NONE)
			{
				showKillingSpreeMessage(NameUtils.trimEntityNamesInString(player.getName()), false, newSpree);
			}
			data.setCurrentKillingSpree(newSpree);
		}
	}

	/**
	 * 
	 * @param entityName
	 * @param plural Whether or not the entityName is plural.
	 * @param newSpree
	 */
	@SuppressWarnings("deprecation") // we actually DON'T want to use TextComponentTranslation for this because the mod isn't installed on the client so the language files won't exist
	private static void showKillingSpreeMessage(String entityName, boolean plural, KillingSpree newSpree)
	{
		ITextComponent message = new TextComponentString(I18n.translateToLocal(Names.KillingSprees.MESSAGEPREFIX));
		message.appendText(entityName);
		
		ITextComponent mobName = new TextComponentString(entityName);
		//"escape" color codes in mob names
		mobName.setStyle(new Style().setColor(TextFormatting.WHITE));
		
		message.appendText(plural ? " are " : " is ");
		message.appendSibling(new TextComponentString(I18n.translateToLocal(newSpree.getText(plural))));
		
		FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().sendChatMsg(message);
	}
}
