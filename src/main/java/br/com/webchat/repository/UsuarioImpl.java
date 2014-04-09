/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.webchat.repository;

import br.com.webchat.model.User;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "UsuarioImpl", mappedName = "ejb/UsuarioImpl")
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioImpl implements Repository<User>{
    @PersistenceContext(unitName = "webChat")
    EntityManager em;
  
    @Override
    public void saveOrUpdate(User modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByApelido(String apelido) {
       return (User) em.createQuery("select c from User c where c.apelido= :apelido",User.class).setParameter("apelido", apelido).getResultList();
    }
   

    
}
