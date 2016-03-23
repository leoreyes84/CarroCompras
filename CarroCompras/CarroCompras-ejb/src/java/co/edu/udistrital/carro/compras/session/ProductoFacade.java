/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carro.compras.session;

import co.edu.udistrital.carro.compras.entity.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Leonardo
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {
    @PersistenceContext(unitName = "CarroCompras-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    public List<Producto> productoPorCategoria(Integer idCategoria) {
        try {
            Query consulta;
            String cadena_consulta = "select * from producto where cat_id = " + idCategoria;
            
            // Asigna crea el query de cadena_consulta
            consulta = getEntityManager().createNativeQuery(cadena_consulta, Producto.class);
            List<Producto> consultada = (List<Producto>) consulta.getResultList();
            return consultada;
        } catch (Exception e) {
            e.getCause();
            return null;
        }
    }

}
