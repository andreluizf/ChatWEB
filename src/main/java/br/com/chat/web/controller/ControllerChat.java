package br.com.chat.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class ControllerChat implements Serializable {

    @Inject
    Usuario usuario;
    List users = new ArrayList<Usuario>();

    public List getChatUser() {
        ArrayList<String> list = new ArrayList<String>(ChatUser.getUsers().values());

        System.out.println(list.size());
        return list;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void salvar(Usuario usuario) throws IOException {
        System.out.println(usuario.getNome());
        if (ChatUser.getByName(usuario.getNome()) != null) {
            throw new IllegalArgumentException("Invalid username");
        } else {
            ChatUser.addUser(usuario);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }

    }
}
