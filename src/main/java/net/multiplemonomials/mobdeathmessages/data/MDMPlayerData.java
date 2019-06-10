package net.multiplemonomials.mobdeathmessages.data;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.multiplemonomials.mobdeathmessages.chat.KillingSpree;


//http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/1571567-1-7-2-1-6-4-eventhandler-and
//coolAlias thank you so much

public class MDMPlayerData implements IMDMPlayerData
{	
	public int killScore;
    public KillingSpree currentKillingSpree;
	    
    /*package-private*/ MDMPlayerData()
    {
	    killScore = 0;
	    currentKillingSpree = KillingSpree.NONE;
    }
    
    public int getKillScore()
	{
		return killScore;
	}

	public KillingSpree getCurrentKillingSpree()
	{
		return currentKillingSpree;
	}

	public void setKillScore(int killScore)
	{
		this.killScore = killScore;
	}

	public void setCurrentKillingSpree(KillingSpree currentKillingSpree)
	{
		this.currentKillingSpree = currentKillingSpree;
	}
	
	@Override
	public String toString()
	{
		return "MDMPlayerData: killscore=" + killScore + " , currentKillingSpree=" + currentKillingSpree.toString();
	}

	/**
	 * Handles loading and saving MDM player data
	 * @author jamie
	 *
	 */
	public static class Storage implements Capability.IStorage<IMDMPlayerData> {
	
		// overloads to eliminate extra parameters
		
		public NBTTagCompound writeNBT(IMDMPlayerData instance)
		{
			NBTTagCompound masterTag = new NBTTagCompound();
			masterTag.setInteger("mdmKillScore", instance.getKillScore());
			return masterTag;	
		}
		
		public void readNBT(IMDMPlayerData instance, NBTBase nbt) 
		{
			// read the known items from NBT
			if(nbt != null)
			{
				NBTTagCompound masterTag = ((NBTTagCompound)nbt);
				if(masterTag.hasKey("mdmKillScore"))
				{
					instance.setKillScore(masterTag.getInteger("mdmKillScore"));
					instance.setCurrentKillingSpree(KillingSpree.getKillingSpreeLevel(instance.getKillScore()));
				}
			}
		}
		
		// actual Forge-signature functions
		
		@Override
		public NBTBase writeNBT(Capability<IMDMPlayerData> capability, IMDMPlayerData instance, EnumFacing side)
		{
			return writeNBT(instance);
		}
		
		@Override
		public void readNBT(Capability<IMDMPlayerData> capability, IMDMPlayerData instance, EnumFacing side, NBTBase nbt) 
		{
			readNBT(instance, nbt);
		}
	}
	
	// we really only need one instance of Storage
	private static Storage storageInstance = null;
	public static Storage getStorageInstance()
	{
		if(storageInstance == null)
		{
			storageInstance = new Storage();
		}
		
		return storageInstance;
	}
	
	public static class Factory implements Callable<MDMPlayerData>
	{
		  @Override
		  public MDMPlayerData call() throws Exception {
		    return new MDMPlayerData();
		  }
	}
}
