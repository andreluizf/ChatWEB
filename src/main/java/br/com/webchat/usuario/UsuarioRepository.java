
package br.com.webchat.usuario;

import java.util.ArrayList;


public class UsuarioRepository {
    static ArrayList<Usuario> list = new ArrayList<>();
  
  public UsuarioRepository() {
  }
  
  public void add(Usuario p)  {
    list.add(p);
  }
}
