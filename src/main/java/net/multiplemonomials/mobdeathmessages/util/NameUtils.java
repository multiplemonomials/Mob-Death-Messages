package net.multiplemonomials.mobdeathmessages.util;


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
}
