package net.multiplemonomials.mobdeathmessages.data;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.multiplemonomials.mobdeathmessages.network.PacketHandler;
import net.multiplemonomials.mobdeathmessages.network.message.MessageEERExtendedPlayerUpdateClient;
import net.multiplemonomials.mobdeathmessages.network.message.MessageEERExtendedPlayerUpdateServer;
import net.multiplemonomials.mobdeathmessages.proxy.CommonProxy;
import net.multiplemonomials.mobdeathmessages.reference.Names;


//http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571567-1-7-2-1-6-4-eventhandler-and
//coolAlias thank you so much

public class EERExtendedPlayer implements IExtendedEntityProperties
{	
	private final EntityPlayer player;
	
    public EntityPlayer getBoundPlayer() 
    {
		return player;
	}

	public Set<ItemStack> learnedItems;
	    
    public EERExtendedPlayer(EntityPlayer player)
    {
	    this.player = player;	    
	    learnedItems = new HashSet<ItemStack>();
    }

    public static final EERExtendedPlayer get(EntityPlayer player)
    {
    	return (EERExtendedPlayer) player.getExtendedProperties(Names.Data.EEREXTENDEDPROPERTIES);
    }
    
    public static final void register(EntityPlayer player)
    {
    	player.registerExtendedProperties(Names.Data.EEREXTENDEDPROPERTIES, new EERExtendedPlayer(player));
    }

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound masterTag = new NBTTagCompound();
		if(!learnedItems.isEmpty())
	    {
        	// Write the known items to NBT
	        NBTTagList learnedItemList = new NBTTagList();
	        
	        for(ItemStack wrappedStack : learnedItems)
	        {
                NBTTagCompound tagCompound = new NBTTagCompound();
                wrappedStack.writeToNBT(tagCompound);
                learnedItemList.appendTag(tagCompound);
	        }
	        masterTag.setTag("learnedItems", learnedItemList);
	    }
		compound.setTag(Names.Data.EEREXTENDEDPROPERTIES, masterTag);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		// read the known items from NBT
		
		if(compound != null)
		{
			NBTTagCompound masterTag = (NBTTagCompound) compound.getTag(Names.Data.EEREXTENDEDPROPERTIES);
			
		}
	}

    
    private static final String getSaveKey(EntityPlayer player)
    {
    	// no longer a username field, so use the command sender name instead:
    	return player.getCommandSenderName() + ":" + Names.Data.EEREXTENDEDPROPERTIES;
    }
    
    public static final void loadProxyData(EntityPlayer player)
    {
    	EERExtendedPlayer playerData = EERExtendedPlayer.get(player);
    	NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
    	if (savedData != null)
    	{
    		playerData.loadNBTData(savedData);
    	}
    	
    	syncExtendedPlayer(player, playerData);
    }
    
    public static void saveProxyData(EntityPlayer player)
    {
    	EERExtendedPlayer playerData = EERExtendedPlayer.get(player);
    	NBTTagCompound savedData = new NBTTagCompound();

    	playerData.saveNBTData(savedData);

    	CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }
    
    public static void syncExtendedPlayer(EntityPlayer player, EERExtendedPlayer playerData)
    {
    	if(!player.worldObj.isRemote)
    	{
    		PacketHandler.INSTANCE.sendTo(new MessageEERExtendedPlayerUpdateClient(playerData), (EntityPlayerMP)player);
    	}
    	else
    	{
    		PacketHandler.INSTANCE.sendToServer(new MessageEERExtendedPlayerUpdateServer(playerData));
    	}
    }

	@Override
	public void init(Entity entity, World world)
	{
		
	}

}
