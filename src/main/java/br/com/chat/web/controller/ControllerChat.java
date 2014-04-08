package br.com.chat.web.controller;

import br.com.webchat.usuario.Usuario;
import br.com.webchat.usuario.UsuarioRepository;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ControllerChat implements Serializable {

    @EJB
    UsuarioRepository repository;

    public Usuario usuario(Usuario p) {
        return repository.findUser(p.getNome());

    }

    public void salvar(Usuario u) {
        System.out.println("=============== ");
        System.out.println(u.getNome());
        System.out.println(u.getApelido());
        System.out.println(u.getCidade());
        System.out.println("=============== ");
        if (usuario(u) == null) {
            repository.add(u);
        } else {
            System.out.println("usuario ja cadastrado");
        }

    }
}
