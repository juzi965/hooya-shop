package top.hooya.shop.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.hooya.shop.exception.CustomExceptionHandler;
import top.hooya.shop.pojo.UserInfo;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author juzi9
 * @date 2020-03-19 16:50
 */
@Component
@ServerEndpoint("/websocket/{userId}") //此注解相当于设置访问URL
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();

    private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private String id;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.id = userId;
        webSockets.add(this);
        sessionPool.put(userId, session);
        logger.info("用户ID:[{}][websocket消息]有新的连接，总数为:[{}]",userId,webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        sessionPool.remove(id);
        logger.info("[websocket消息]连接断开，总数为:[{}]",webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("[websocket消息]收到客户端消息:[{}]",message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for(WebSocket webSocket : webSockets) {
            logger.info("[websocket消息]广播消息:[{}]",message);

            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(UserInfo userInfo, String message) {
        logger.info("[websocket消息][{}]单点消息:[{}]",userInfo.getUserName(),message);

        Session session = sessionPool.get(String.valueOf(userInfo.getId()));
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
