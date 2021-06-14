package zsb.azb.mcm.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import org.springframework.stereotype.Component;
import zsb.azb.mcm.Utils.Utilty;

@Component
public class UDPClient
{
    EventLoopGroup group = new NioEventLoopGroup();
    Channel channel = null;

    public void send(InetSocketAddress inetSocketAddress, byte[] msg)
            throws Exception
    {
        try
        {
            Bootstrap bootstrap = new Bootstrap();

            ((Bootstrap)((Bootstrap)bootstrap.group(this.group)).channel(NioDatagramChannel.class))
                    .handler(new UDPClientHandler());
            this.channel = bootstrap.bind(0).sync().channel();
            this.channel.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(msg), inetSocketAddress))

                    .syncUninterruptibly();
            System.out.println(Utilty.byte2HexStr(msg));
        }
        finally
        {
            this.channel.close();
        }
    }
}
