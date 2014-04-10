/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.chat.web.controller;

import br.com.webchat.model.Usuario;
import br.com.webchat.repository.UsuarioImpl;
import br.com.webchat.repository.UsuarioRepository;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cadastro")
public class ServletCadastro extends HttpServlet {

    
 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String data = request.getParameter("data");
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try {
            d = format.parse(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String apelido = request.getParameter("apelido");
        String frase = request.getParameter("frase");
        String cidade = request.getParameter("cidade");
        String email = request.getParameter("email");
      
            if (UsuarioRepository.findUser(nome) == null) {
                UsuarioRepository.add(new Usuario(nome, apelido, email, d, cidade, frase));
                String json = new Gson().toJson("200");
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
            } else {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write("500");
            }
       

    }
}
