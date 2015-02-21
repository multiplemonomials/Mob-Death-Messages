package net.multiplemonomials.mobdeathmessages.chat;

import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;

public enum KillingSpree
{
	NONE("on a Boring Spree"),
	KILLINGSPREE("on a §4Killing Spree"),
	KILLINGMACHINE("a §6Killing Machine"),
	LEGENDARY("§bLegendary"),
	AWESOME("§eAwesome");
	
	public final String _text;
	
	KillingSpree(String text)
	{
		_text = text;
	}
	
	public static KillingSpree getKillingSpreeLevel(int numberOfKills)
	{
		if(numberOfKills >= ModConfiguration.killsForAwesome)
		{
			return AWESOME;
		}
		else if(numberOfKills >= ModConfiguration.killsForLegendary)
		{
			return LEGENDARY;
		}
		else if(numberOfKills >= ModConfiguration.killsForKillingMachine)
		{
			return KILLINGMACHINE;
		}
		else if(numberOfKills >= ModConfiguration.killsForKillingSpree)
		{
			return KILLINGSPREE;
		}
		return NONE;
	}
}
