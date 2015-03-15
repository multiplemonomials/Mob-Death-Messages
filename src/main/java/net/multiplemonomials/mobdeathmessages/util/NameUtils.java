package net.multiplemonomials.mobdeathmessages.util;

import org.atteo.evo.inflector.English;

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
	
	public static String makeMobNamePlural(String name)
	{
		return English.plural(name);
//		if(name.equals("Squid"))
//		{
//			return "Squid";
//		}
//		if(name.equals("Silverfish"))
//		{
//			return "Silverfish";
//		}
//		
//		if(name.endsWith("y") && name.length() > 1)
//		{
//			return name.substring(0, name.length() - 1) + "ies";
//		}
//		
//		return name + "s";
	}
}
