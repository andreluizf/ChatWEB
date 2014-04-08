package br.com.webchat.chat;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ejb.Singleton;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebSocketChat {

    private static final Set<Session> peers
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    @OnMessage
    public void message(String message, Session client)
            throws IOException, EncodeException {
        System.out.println("Message Received: " + client.getOpenSessions().toString());

        if (message.contains(" ::user")) {
            for (Session peer : client.getOpenSessions()) {
                peer.getBasicRemote().sendText(message);
            }
        } else {
            for (Session peer : client.getOpenSessions()) {
                peer.getBasicRemote().sendText(message);
            }

        }

    }

}
