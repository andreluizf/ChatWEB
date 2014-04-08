package br.com.chat.web.controller;

import br.com.webchat.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControllerChat implements Serializable {

   

    public void salvar(Usuario usuario) throws IOException {
        System.out.println(usuario.getNome());

    }
}
