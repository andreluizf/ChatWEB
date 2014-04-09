package br.com.chat.web.controller;

import br.com.webchat.usuario.Usuario;
import br.com.webchat.usuario.UsuarioRepository;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/informacao", asyncSupported = true)
public class ServletInfo extends HttpServlet {

    @EJB
    UsuarioRepository repository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        Usuario user = new Usuario();
        user = repository.findUser(nome);
        System.out.println(nome);
        String json = new Gson().toJson(user);
        System.out.println(" ================ aki ================ ");
        System.out.println(json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}
