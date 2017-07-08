package net.multiplemonomials.mobdeathmessages.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.multiplemonomials.mobdeathmessages.MobDeathMessages;

public class MDMDataCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>
{
	
	private final MDMPlayerData data = new MDMPlayerData();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == MobDeathMessages.MDM_DATA_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == MobDeathMessages.MDM_DATA_CAPABILITY)
		{
			return MobDeathMessages.MDM_DATA_CAPABILITY.<T>cast(data);
		}
		
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT()
	{
		return MDMPlayerData.getStorageInstance().writeNBT(data);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		MDMPlayerData.getStorageInstance().readNBT(data, nbt);
	}

}
