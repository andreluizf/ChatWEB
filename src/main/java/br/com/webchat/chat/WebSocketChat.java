package br.com.webchat.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat/{user}")
@Singleton
public class WebSocketChat {
//   Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());

    Map<String, Session> userSessions = Collections.synchronizedMap(new HashMap<String, Session>());

    @OnOpen
    public void onOpen(Session userSession, @PathParam("user") String userName) {

        System.out.println("user ::::: " + userName);

        System.out.println("New request received. Id: " + userSession.getId());
        userSessions.put(userName, userSession);
    }

    /**
     * Callback hook for Connection close events.
     *
     * This method will be invoked when a client closes a WebSocket connection.
     *
     * @param userSession the userSession which is opened.
     */
    @OnClose
    public void onClose(Session userSession) {
        System.out.println("Connection closed. Id: " + userSession.getId());
        userSessions.remove(userSession);
    }

    /**
     * Callback hook for Message Events.
     *
     * This method will be invoked when a client send a message.
     *
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void onMessage(String message, @PathParam("user") String userName) {
        System.out.println("Message Received: " + message);
        System.out.println(userSessions.toString());
        Session session = userSessions.get(userName);
        System.out.println("Sending to " + session.getId());
        session.getAsyncRemote().sendText(message);
//        for (Session session : userSessions) {
//            System.out.println("Sending to " + session.getId());
//            session.getAsyncRemote().sendText(message);
//        }
    }

}
