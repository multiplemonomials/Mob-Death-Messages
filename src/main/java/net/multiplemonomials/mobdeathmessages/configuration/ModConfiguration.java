package net.multiplemonomials.mobdeathmessages.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.multiplemonomials.mobdeathmessages.reference.Reference;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;

public class ModConfiguration
{
	public static final String CATEGORY_DEATH_MESSAGES = "Death Message Options";
	
	public static boolean showPlayerOnMobDeathMessages;
	
	public static boolean showMobOnMobDeathMessages;
	
	public static boolean showInanimateObjectOnMobDeathMessages;
	
	public static boolean showBatsBurningToDeath;
	
	public static final String CATEGORY_KILLING_SPREES = "Killing Spree Options";
	
	public static boolean killingSpreePlayersEnabled;
	
	public static int killsForKillingSpree;
	
	public static int killsForKillingMachine;

	public static int killsForLegendary;

	public static int killsForAwesome;

	
    private static Configuration configuration;

    public static void init(File configFile)
    {
        configuration = new Configuration(configFile);
        
    	LogHelper.info("Loading configuration...");
    	
    	showPlayerOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showPlayerOnMobDeathMessages", true, "Show cases where a mob was killed by a player").getBoolean();
    	showMobOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showMobOnMobDeathMessages", true, "Show cases where a mob was killed by a player").getBoolean();
    	showInanimateObjectOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showInanimateObjectOnMobDeathMessages", true, "Show cases where a mob was killed by a player").getBoolean();
    	showBatsBurningToDeath = configuration.get(CATEGORY_DEATH_MESSAGES, "showBatsBurningToDeath", false, "Show cases where a mob was killed by a player").getBoolean();

    	
    	killingSpreePlayersEnabled = configuration.get(CATEGORY_DEATH_MESSAGES, "killingSpreePlayersEnabled", true, "Show player killing sprees").getBoolean();
    	killsForKillingSpree = configuration.get(CATEGORY_KILLING_SPREES, "killsPerBaseKillingSpree", 8, "Kills to get a level one killing spree.  Subsequent levels take twice as many per level.").getInt();
    	
    	//initialize the rest of the values using math
    	killsForKillingMachine = (2 * killsForKillingSpree) + killsForKillingSpree;
    	killsForLegendary = (4 * killsForKillingSpree) + killsForKillingMachine;
    	killsForAwesome = (8 * killsForKillingSpree) + killsForLegendary;

    	
        try
        {
            configuration.load();
        }
        catch (Exception e)
        {
            LogHelper.error(Reference.MOD_NAME + " has had a problem loading its client configuration");
        }
        finally
        {
            configuration.save();
        }
    }
    
    
}
