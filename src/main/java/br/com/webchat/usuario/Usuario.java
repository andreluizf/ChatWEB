

package br.com.webchat.usuario;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class Usuario {
    private String nome;
    private String apelido;
    private String data;
    private String email;
    private String cidade;
    private String frase;

    public Usuario() {
    }

    public Usuario(String nome, String apelido, String data, String email, String cidade, String frase) {
        this.nome = nome;
        this.apelido = apelido;
        this.data = data;
        this.email = email;
        this.cidade = cidade;
        this.frase = frase;
    }
     

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", apelido=" + apelido + ", data=" + data + ", email=" + email + ", cidade=" + cidade + ", frase=" + frase + '}';
    }

   
    
}
