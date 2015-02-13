package net.multiplemonomials.mobdeathmessages.network.message;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.multiplemonomials.mobdeathmessages.data.MDMPlayerData;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * Message that sets the given EEREExtebdedPlayer as the player data for that player on the server
 *
 */
public class MessageMBMPlayerDataUpdateServer implements IMessage, IMessageHandler<MessageMBMPlayerDataUpdateServer, IMessage>
{
	NBTTagCompound _playerDataCompound;
	
    public MessageMBMPlayerDataUpdateServer()
    {
    }

    public MessageMBMPlayerDataUpdateServer(MDMPlayerData playerData)
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

    @Override
    public IMessage onMessage(MessageMBMPlayerDataUpdateServer message, MessageContext ctx)
    {
    	MDMPlayerData.get(ctx.getServerHandler().playerEntity).loadNBTData(message._playerDataCompound);
    	
    	return null;
    }

    @Override
    public String toString()
    {
        return "EERExtendedPlayerUpdate";
    }
}
