package net.multiplemonomials.mobdeathmessages.network;

import net.multiplemonomials.mobdeathmessages.network.message.MessageMBMPlayerDataUpdateClient;
import net.multiplemonomials.mobdeathmessages.network.message.MessageMBMPlayerDataUpdateServer;
import net.multiplemonomials.mobdeathmessages.reference.Reference;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
    	INSTANCE.registerMessage(new MessageMBMPlayerDataUpdateClient(), MessageMBMPlayerDataUpdateClient.class, 0, Side.CLIENT);
    	INSTANCE.registerMessage(new MessageMBMPlayerDataUpdateServer(), MessageMBMPlayerDataUpdateServer.class, 1, Side.SERVER);	
    }
}
