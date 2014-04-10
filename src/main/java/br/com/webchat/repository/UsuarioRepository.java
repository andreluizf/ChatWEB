package br.com.webchat.repository;

import br.com.webchat.model.Usuario;
import java.util.HashMap;
import java.util.Map;


public class UsuarioRepository {

    private static Map<String, Usuario> lists = new HashMap<>();

    public UsuarioRepository() {
    }

    public static Usuario findUser(String nome) {
        return lists.get(nome);
    }

    public static void add(Usuario p) {
        lists.put(p.getNome(), p);
    }
}
