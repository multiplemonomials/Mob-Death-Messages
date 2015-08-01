package net.multiplemonomials.mobdeathmessages;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.multiplemonomials.mobdeathmessages.network.PacketHandler;
import net.multiplemonomials.mobdeathmessages.proxy.IProxy;
import net.multiplemonomials.mobdeathmessages.reference.Reference;
import net.multiplemonomials.mobdeathmessages.util.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class MobDeathMessages
{
    @Instance(Reference.MOD_ID)
    public static MobDeathMessages instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LogHelper.info("Phase 1 Loading Started");
        
        proxy.initConfiguration(event.getSuggestedConfigurationFile());

    	LogHelper.info("Setting up network stuff...");
        PacketHandler.init();

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
