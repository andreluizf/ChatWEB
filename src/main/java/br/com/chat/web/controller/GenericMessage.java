/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

/**
 *
 * @author andre
 */
public class GenericMessage implements Message {

    private User from = null;
    private User to = null;
    private String text = null;

    public GenericMessage(User from, String texto) {
        this.from = from;
        this.text = texto;
    }

    public GenericMessage(User from, User to, String texto) {
        this(from, texto);
        this.to = to;
    }

    @Override
    public User getFrom() {
        return (from);
    }

    @Override
    public User getTo() {
        return (to);
    }

    @Override
    public String getHTMLCode() {
        return (" - " + getFrom().getName() + (getTo() != null ? " fala para " + getTo().getName() : "") + ": " + text + "<br>\\n");
    }
}
