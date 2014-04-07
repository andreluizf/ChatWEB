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
public interface Message {

    public User getFrom();

    public User getTo();

    public String getHTMLCode();
}
