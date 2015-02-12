package net.multiplemonomials.mobdeathmessages.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.multiplemonomials.mobdeathmessages.reference.Reference;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;

public class ModConfiguration
{
	public static boolean showMobOnPlayerDeathMessages;
	
	public static boolean showMobOnMobDeathMessages;
	
	public static boolean showBatsBurningToDeath;
	
    private static Configuration configuration;

    public static void init(File configFile)
    {
        configuration = new Configuration(configFile);
        
    	LogHelper.info("Loading client configuration...");

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
