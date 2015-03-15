package net.multiplemonomials.mobdeathmessages.chat;

import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.reference.Names;

public enum KillingSpree
{
	DYINGSPREE(Names.KillingSprees.DYINGSPREE),
	NONE(Names.KillingSprees.NONE),
	KILLINGSPREE(Names.KillingSprees.KILLINGSPREE),
	KILLINGMACHINE(Names.KillingSprees.KILLINGMACHINE),
	LEGENDARY(Names.KillingSprees.LEGENDARY),
	AWESOME(Names.KillingSprees.AWESOME);
	
	private final String _text;
	
	KillingSpree(String text)
	{
		_text = text;
	}
	
	public String getText(boolean plural)
	{
		if(plural)
		{
			return _text + Names.KillingSprees.PLURAL;
		}
		return _text;
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
		else if(numberOfKills <= -1 * ModConfiguration.killsForKillingSpree)
		{
			return DYINGSPREE;
		}
		return NONE;
	}
}
