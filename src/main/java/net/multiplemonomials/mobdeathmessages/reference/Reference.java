package net.multiplemonomials.mobdeathmessages.reference;

public class Reference
{
    public static final String MOD_ID = "mobdeathmessages";
    public static final String MOD_NAME = "Mod Death Messages";
    public static final String FINGERPRINT = "@FINGERPRINT@";
    public static final String VERSION = "1.0.1";
    public static final String SERVER_PROXY_CLASS = "net.multiplemonomials.mobdeathmessages.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "net.multiplemonomials.mobdeathmessages.proxy.ClientProxy";
    
    //This can't be in Textures.java because that class can't be loaded on a server
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
}
