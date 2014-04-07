/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
public class ChatUser implements User {

    private String name = null;
    private boolean connected = true;
    private HttpServletResponse res = null;
    private HttpServletRequest request = null;
    private ArrayList messages = new ArrayList();
    private static Map users = new HashMap<Object, Object>();



    public static Usuario getByName(String name) {
        return (Usuario) users.get(name);
    }

    public static Map getUsers() {
        synchronized (users) {
            return (new HashMap(users));
        }
    }

    public static void addUser(Usuario user) {
        synchronized (users) {
            users.put(user.getNome(), user);
        }
    }

    public static void sendMessage(Message msg) throws IOException {

        // se a mensagem tem um destinatario especifico  
        if (msg != null && msg.getTo() != null) {
            msg.getTo().addMessage(msg);
            msg.getFrom().addMessage(msg);
            return;
        }

        // caso contrario envia para todos  
        Map usersList = getUsers();
        Iterator i = usersList.keySet().iterator();
        while (i.hasNext()) {
            String username = (String) i.next();
            User user = ((User) usersList.get(username));
            try {
                user.addMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
                removeUser(user);
            }
        }
    }

    public static void removeUser(User user) throws IOException {
        synchronized (users) {
            // se não existir  
            if (!users.containsValue(user)) {
                return;
            }
            // remove  
            users.remove(user.getName());
            // desconecta  
            user.disconnect();
            // notifica da saída  
            synchronized (user) {
                user.notifyAll();
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void disconnect() {
        connected = false;
    }

    @Override
    public void checkSession() {
        try {
            if (getRequest().getSession() == null
                    || getRequest().getSession().getAttribute("user") == null) {
                this.disconnect();
            }
        } catch (IllegalStateException isex) {
            this.disconnect();
        }
    }

    @Override
    public HttpServletResponse getResponse() {
        return res;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setResponse(HttpServletResponse res) {
        this.res = res;
    }

    @Override
    public void setRequest(HttpServletRequest req) {
        this.request = req;
    }

    @Override
    public void addMessage(Message msg) {
        messages.add(msg);
        synchronized (messages) {
            // sinaliza para quem está esperando  
            messages.notifyAll();
        }
    }

    @Override
    public Message getNewMessage() {
        if (messages.isEmpty()) {
            try {
                synchronized (messages) {
                    messages.wait(5 * 1000);
                }
            } catch (InterruptedException e) {
                this.disconnect();
                return (null);
            }
            if (messages.isEmpty()) {
                return (null);
            }
        }
        return (Message) (messages.remove(0));
    }

    @Override
    public void showMessage(Message msg) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
