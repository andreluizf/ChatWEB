/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

import br.com.webchat.repository.Usuario;
import br.com.webchat.repository.UsuarioRepository;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cadastro", asyncSupported = true)
public class ServletCadastro extends HttpServlet {

    @EJB
    UsuarioRepository repository;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String data = request.getParameter("data");
        String apelido = request.getParameter("apelido");
        String frase = request.getParameter("frase");
        String cidade = request.getParameter("cidade");
        String email = request.getParameter("email");
        if (repository.findUser(nome) == null) {
            repository.add(new Usuario(nome, apelido, data, email, cidade, frase));
        } 
        System.out.println(repository.findUser(nome).getFrase());
    }
}
