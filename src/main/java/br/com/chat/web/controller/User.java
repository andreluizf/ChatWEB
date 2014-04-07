/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
public interface User {

    public String getName();

    boolean isConnected();

    public void disconnect();

    public void checkSession();

    public HttpServletResponse getResponse();

    public HttpServletRequest getRequest();

    public void setResponse(HttpServletResponse res);

    public void setRequest(HttpServletRequest req);

    public void addMessage(Message msg);

    public Message getNewMessage();

    public void showMessage(Message msg) throws IOException;
}
