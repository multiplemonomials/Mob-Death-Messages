package net.multiplemonomials.mobdeathmessages.proxy;

import java.io.File;

public interface IProxy
{
    public abstract void registerTileEntities();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();

    public abstract void registerKeybindings();

	public abstract void initConfiguration(File file);
}
