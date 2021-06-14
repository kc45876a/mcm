package zsb.azb.mcm.Netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UDPServerHandler
        extends SimpleChannelInboundHandler<DatagramPacket>
{
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet)
    {
        ByteBuf buf = (ByteBuf)packet.copy().content();
    }
}
