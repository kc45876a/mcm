package zsb.azb.mcm.Netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import java.io.PrintStream;

public class UDPClientHandler
        extends SimpleChannelInboundHandler<DatagramPacket>
{
    public void channelRead0(ChannelHandlerContext cxt, DatagramPacket in)
            throws Exception
    {
        System.out.println("Client received: " + in);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
