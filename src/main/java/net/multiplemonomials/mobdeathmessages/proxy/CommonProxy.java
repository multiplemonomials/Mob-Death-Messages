package net.multiplemonomials.mobdeathmessages.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.multiplemonomials.mobdeathmessages.configuration.ModConfiguration;
import net.multiplemonomials.mobdeathmessages.handler.EntityConstructedEventHandler;
import net.multiplemonomials.mobdeathmessages.handler.EntityJoinWorldHandler;
import net.multiplemonomials.mobdeathmessages.handler.LivingDeathEventHandler;

public abstract class CommonProxy implements IProxy
{
	/** Used to store IExtendedEntityProperties data temporarily between player death and respawn */
	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();
	
    public void registerEventHandlers()
    {
        
        MinecraftForge.EVENT_BUS.register(new EntityConstructedEventHandler());
        MinecraftForge.EVENT_BUS.register(new EntityJoinWorldHandler());
        MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());

    }
    
    public void initConfiguration(File configPath)
    {
    	ModConfiguration.init(configPath);
    }

    public void registerTileEntities()
    {
      
    }
    
    /**
    * Adds an entity's custom data to the map for temporary storage
    * @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
    */
    public static void storeEntityData(String name, NBTTagCompound compound)
    {
    	extendedEntityData.put(name, compound);
    }

    /**
    * Removes the compound from the map and returns the NBT tag stored for name or null if none exists
    */
    public static NBTTagCompound getEntityData(String name)
    {
    	return extendedEntityData.remove(name);
    }
}
