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
	
	public static boolean showNamedMobsOnly;
	
	public static boolean showBatsBurningToDeath;
		
	public static final String CATEGORY_KILLING_SPREES = "Killing Spree Options";
	
	public static int xpForKillingSpree;
	
	public static boolean killingSpreePlayersEnabled;
	
	public static int killsForKillingSpree;
	
	public static int killsForKillingMachine;

	public static int killsForLegendary;

	public static int killsForAwesome;
	
	public static boolean killingSpreePlayersVsMobsEnabled;

	public static boolean killingSpreeMobsVsMobsEnabled;
	
    private static Configuration configuration;

    public static void init(File configFile)
    {
        configuration = new Configuration(configFile);
        
    	LogHelper.info("Loading configuration...");
    	
    	showPlayerOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showPlayerOnMobDeathMessages", true, "Show cases where a mob was killed by a player").getBoolean();
    	showMobOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showMobOnMobDeathMessages", true, "Show cases where a mob was killed by a mob").getBoolean();
    	showNamedMobsOnly = configuration.get(CATEGORY_DEATH_MESSAGES, "showNamedMobsOnly", false, "Only show death messages involving mobs which have a custom name. If MobvMob messages are enabled, the named mob can be the attacker as well.").getBoolean();
    	showInanimateObjectOnMobDeathMessages = configuration.get(CATEGORY_DEATH_MESSAGES, "showInanimateObjectOnMobDeathMessages", true, "Show cases where a mob was killed by an inanimate object").getBoolean();
    	showBatsBurningToDeath = configuration.get(CATEGORY_DEATH_MESSAGES, "showBatsBurningToDeath", false, "Show bats burning to death").getBoolean();

    	xpForKillingSpree = configuration.get(CATEGORY_KILLING_SPREES, "xpForKillingSpree", 10, "Amount of XP awarded to players who get a killing spree.  Each subsequent level of spree gets twice as much xp.  Set to 0 to disable.").getInt();
    	killingSpreePlayersEnabled = configuration.get(CATEGORY_KILLING_SPREES, "killingSpreePlayersEnabled", true, "Show player killing sprees").getBoolean();
    	killsForKillingSpree = configuration.get(CATEGORY_KILLING_SPREES, "killsPerBaseKillingSpree", 8, "Kills to get a level one killing spree.  Subsequent levels take twice as many per level.").getInt();
    	
    	//initialize the rest of the values using math
    	killsForKillingMachine = (2 * killsForKillingSpree) + killsForKillingSpree;
    	killsForLegendary = (4 * killsForKillingSpree) + killsForKillingMachine;
    	killsForAwesome = (8 * killsForKillingSpree) + killsForLegendary;
    	
    	killingSpreePlayersVsMobsEnabled = configuration.get(CATEGORY_KILLING_SPREES, "killingSpreePlayersVsMobsEnabled", true, "Show killing sprees when mobs kill players").getBoolean();
    	killingSpreeMobsVsMobsEnabled = configuration.get(CATEGORY_KILLING_SPREES, "killingSpreeMobsVsMobsEnabled", true, "Show killing sprees when mobs kill mobs").getBoolean();
    	
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
