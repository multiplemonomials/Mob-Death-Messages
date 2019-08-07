package net.multiplemonomials.mobdeathmessages;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.multiplemonomials.mobdeathmessages.data.IMDMPlayerData;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import net.multiplemonomials.mobdeathmessages.reference.Reference;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;

@Mod(value = Reference.MOD_ID)
public class MobDeathMessages
{
    public static MobDeathMessages instance;


	@CapabilityInject(IMDMPlayerData.class)
	public static Capability<IMDMPlayerData> MDM_DATA_CAPABILITY = null;


    @EventHandler
    public void preInit(FMLCommonSetupEvent event)
    {
        LogHelper.info("Phase 1 Loading Started");
        
        proxy.initConfiguration(event.getSuggestedConfigurationFile());

    	LogHelper.info("Setting up network stuff...");
        PacketHandler.init();
        
    	LogHelper.info("Registering custom capability...");
        CapabilityManager.INSTANCE.register(IMDMPlayerData.class, MDMPlayerData.getStorageInstance(), new MDMPlayerData.Factory());

        LogHelper.info("Phase 1 Loading Complete!");

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        LogHelper.info("Phase 2 Loading Started");

        LogHelper.info("Registering event handlers...");
        proxy.registerEventHandlers();

        LogHelper.info("Done loading!");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
