package net.multiplemonomials.mobdeathmessages.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.multiplemonomials.mobdeathmessages.chat.KillingSpree;
import net.multiplemonomials.mobdeathmessages.proxy.CommonProxy;
import net.multiplemonomials.mobdeathmessages.reference.Names;


//http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571567-1-7-2-1-6-4-eventhandler-and
//coolAlias thank you so much

public class MDMPlayerData implements IExtendedEntityProperties
{	
	private final EntityPlayer player;
	
    public EntityPlayer getBoundPlayer() 
    {
		return player;
	}

    public int killScore;
    public KillingSpree currentKillingSpree;
	    
    public MDMPlayerData(EntityPlayer player)
    {
	    this.player = player;	
	    killScore = 0;
	    currentKillingSpree = KillingSpree.NONE;
    }

    public static final MDMPlayerData get(EntityPlayer player)
    {
    	return (MDMPlayerData) player.getExtendedProperties(Names.Data.MDMPLAYERDATA);
    }
    
    public static final void register(EntityPlayer player)
    {
    	player.registerExtendedProperties(Names.Data.MDMPLAYERDATA, new MDMPlayerData(player));
    }

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound masterTag = new NBTTagCompound();
		masterTag.setInteger("killScore", killScore);

		compound.setTag(Names.Data.MDMPLAYERDATA, masterTag);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		// read the known items from NBT
		
		if(compound != null)
		{
			NBTTagCompound masterTag = (NBTTagCompound) compound.getTag(Names.Data.MDMPLAYERDATA);
			if(masterTag != null)
			{
				killScore = masterTag.getInteger("killScore");
				currentKillingSpree = KillingSpree.getKillingSpreeLevel(killScore);
			}
		}
	}

    
    private static final String getSaveKey(EntityPlayer player)
    {
    	// no longer a username field, so use the command sender name instead:
    	return player.getName() + ":" + Names.Data.MDMPLAYERDATA;
    }
    
    public static final void loadProxyData(EntityPlayer player)
    {
    	MDMPlayerData playerData = MDMPlayerData.get(player);
    	NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
    	if (savedData != null)
    	{
    		playerData.loadNBTData(savedData);
    	}
    	
    	syncExtendedPlayer(player, playerData);
    }
    
    public static void saveProxyData(EntityPlayer player)
    {
    	MDMPlayerData playerData = MDMPlayerData.get(player);
    	NBTTagCompound savedData = new NBTTagCompound();

    	playerData.saveNBTData(savedData);

    	CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }
    
    public static void syncExtendedPlayer(EntityPlayer player, MDMPlayerData playerData)
    {
    	//do nothing, since the player data only needs to live on the server.
    }

	@Override
	public void init(Entity entity, World world)
	{
		
	}

}
