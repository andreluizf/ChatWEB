package br.com.webchat.usuario;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class UsuarioRepository {

    static Map<String, Usuario> lists = new HashMap<String, Usuario>();

    public UsuarioRepository() {
    }

    public Usuario findUser(String nome) {
         System.out.println(nome);
      
        return lists.get(nome);
    }

    public void add(Usuario p) {
        lists.put(p.getNome(), p);
    }
}
