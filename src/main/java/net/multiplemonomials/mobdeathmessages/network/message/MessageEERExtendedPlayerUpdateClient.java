package net.multiplemonomials.mobdeathmessages.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.multiplemonomials.mobdeathmessages.data.EERExtendedPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Message that sets the given EEREExtebdedPlayer as the player data for the client it's sent to
 *
 */
public class MessageEERExtendedPlayerUpdateClient implements IMessage, IMessageHandler<MessageEERExtendedPlayerUpdateClient, IMessage>
{
	NBTTagCompound _playerDataCompound;
	
    public MessageEERExtendedPlayerUpdateClient()
    {
    }

    public MessageEERExtendedPlayerUpdateClient(EERExtendedPlayer playerData)
    {
    	_playerDataCompound = new NBTTagCompound();
        playerData.saveNBTData(_playerDataCompound);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
    	 _playerDataCompound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
    	ByteBufUtils.writeTag(buf, _playerDataCompound);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IMessage onMessage(MessageEERExtendedPlayerUpdateClient message, MessageContext ctx)
    {
    	EERExtendedPlayer.get(FMLClientHandler.instance().getClient().thePlayer).loadNBTData(message._playerDataCompound);
    	
    	return null;
    }

    @Override
    public String toString()
    {
        return "EERExtendedPlayerUpdate";
    }
}
