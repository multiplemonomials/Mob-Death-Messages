package net.multiplemonomials.mobdeathmessages.data;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.multiplemonomials.mobdeathmessages.MobDeathMessages;

public class MDMDataCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>
{
	
	private final MDMPlayerData data = new MDMPlayerData();

	@Override
	public @Nonnull <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, final @Nullable EnumFacing side)
	{
		if(cap == MobDeathMessages.MDM_DATA_CAPABILITY)
		{
			return LazyOptional.<T>of(new NonNullSupplier<T>()
			{
				@SuppressWarnings("unchecked")
				@Override
				public T get()
				{
					return (T) data;
				}
			});
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
