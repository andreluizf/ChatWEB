/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.webchat.repository;

import br.com.webchat.model.Usuario;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "UsuarioImpl", mappedName = "ejb/UsuarioImpl")
@Remote(Repository.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioImpl implements Repository<Usuario>{
    @PersistenceContext(unitName = "webChat")
    EntityManager em;
  
    @Override
    public void saveOrUpdate(Usuario modelo) {
        em.persist(modelo);
    }

    @Override
    public void delete(Usuario modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario findByApelido(String apelido) {
       return (Usuario) em.createQuery("select c from User c where c.apelido= :apelido",Usuario.class).setParameter("apelido", apelido).getResultList();
    }
   

    
}
