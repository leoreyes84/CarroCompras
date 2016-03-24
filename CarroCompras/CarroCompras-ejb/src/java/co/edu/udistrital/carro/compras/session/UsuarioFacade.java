/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carro.compras.session;

import co.edu.udistrital.carro.compras.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Leonardo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "CarroCompras-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario usuarioByEmailYPass(String email, String password) {
        try {
            Query consulta;
            String cadena_consulta = "select * from usuario where usr_email= '" + email + "'and usr_contrasenia = '" + password + "'";
            // Asigna crea el query de cadena_consulta
            consulta = getEntityManager().createNativeQuery(cadena_consulta, Usuario.class);
            Usuario consultada = (Usuario) consulta.getSingleResult();
            return consultada;
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }
    
   
    
}
