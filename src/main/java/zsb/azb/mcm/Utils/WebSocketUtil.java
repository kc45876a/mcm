package zsb.azb.mcm.Utils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

@ServerEndpoint("/webSocket")
@Component
public class WebSocketUtil
{
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet();
    private Session session;

    @OnOpen
    public void onOpen(Session session)
    {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();

        System.out.println("��������������");
    }

    @OnClose
    public void onClose()
    {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("����������������������������" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("����������������:" + message);
        try
        {
            session.getBasicRemote().sendText(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("��������");
        error.printStackTrace();
    }

    public void sendMessage(String message)
            throws IOException
    {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message)
            throws IOException
    {
        for (WebSocketUtil item : webSocketSet) {
            try
            {
                item.sendMessage(message);
            }
            catch (IOException e) {}
        }
    }

    public static synchronized int getOnlineCount()
    {
        return onlineCount;
    }

    public static synchronized void addOnlineCount()
    {
        onlineCount += 1;
    }

    public static synchronized void subOnlineCount()
    {
        onlineCount -= 1;
    }
}
