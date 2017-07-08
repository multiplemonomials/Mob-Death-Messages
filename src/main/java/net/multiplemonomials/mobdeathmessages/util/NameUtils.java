package net.multiplemonomials.mobdeathmessages.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;

public class NameUtils
{
	/**
	 * Some mod mobs' names still contain entity.foo.name stuff around them.
	 * 
	 * This function removes any "entity." and ".name" strings in a string
	 * 
	 * It copies the passed string, and the original isn't modified.
	 * @return
	 */
	public static String trimEntityNamesInString(String text)
	{
		String newText = text.replaceAll("entity.", "");
		newText = newText.replaceAll(".name", "");
		return newText;
	}

	public static String getEntityNameForDisplay(EntityLiving entity)
	{
		String name = trimEntityNamesInString(entity.getName());
		
		// for some reason, calling getName() on a Zombie Pigman returns "Zombie"
		if(name.equals("Zombie") || entity instanceof EntityPigZombie)
		{
			name = "Zombie Pigman";
		}
		
		return name;
	}
}
