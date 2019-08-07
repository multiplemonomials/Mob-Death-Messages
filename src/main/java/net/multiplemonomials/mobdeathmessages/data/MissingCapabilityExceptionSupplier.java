package net.multiplemonomials.mobdeathmessages.data;

import net.minecraftforge.common.util.NonNullSupplier;

/**
 * Supplier to pass to LazyOptional.orElseThrow().
 * 
 * Throws an exception if the capability is missing.
 * @author jamie
 *
 */
public class MissingCapabilityExceptionSupplier implements NonNullSupplier<RuntimeException>
{
	public static final MissingCapabilityExceptionSupplier INSTANCE = new MissingCapabilityExceptionSupplier();

	@Override
	public RuntimeException get()
	{
		return new RuntimeException("Mob Death Messages capability expected on this entity!");
	}

}
