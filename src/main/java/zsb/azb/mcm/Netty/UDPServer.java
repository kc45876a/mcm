package zsb.azb.mcm.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.io.PrintStream;

public class UDPServer
{
    private Channel channel;
    private final NioEventLoopGroup group = new NioEventLoopGroup();

    public ChannelFuture start(int port)
    {
        ChannelFuture f = null;
        try
        {
            Bootstrap bootstrap = new Bootstrap();

            ((Bootstrap)((Bootstrap)bootstrap.group(this.group)).channel(NioDatagramChannel.class))
                    .handler(new UDPServerHandler());
            f = bootstrap.bind(port).sync();
            this.channel = f.channel();
        }
        catch (Exception localException) {}finally
        {
            if ((f != null) && (f.isSuccess())) {
                System.out.println("Netty server listening  on port " + port + " and ready for connections...");
            } else {
                System.out.println("Netty server start up Error!");
            }
        }
        return f;
    }

    public void destroy()
    {
        System.out.println("Shutdown Netty Server...");
        if (this.channel != null) {
            this.channel.close();
        }
        this.group.shutdownGracefully();
        System.out.println("Shutdown Netty Server Success!");
    }
}
