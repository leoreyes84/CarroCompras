/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.DetalleVenta;
import co.edu.udistrital.carro.compras.session.DetalleVentaFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.jboss.logging.Logger;

/**
 *
 * @author Leonardo
 */
@ManagedBean
@RequestScoped
public class VerVentasMB {
    @EJB
    private DetalleVentaFacadeLocal detalleVentaFacade;
    
    // ///////////////////////////////////
    // Logger de la clase
    // //////////////////////////////////
    private static Logger _logger = Logger.getLogger(VerVentasMB.class);
    
    //////////////////////////////////////
    ////Atributos de la clase
    //////////////////////////////////////
    private List<DetalleVenta> listaVentas;
    
    /**
     * Creates a new instance of VerVentasMB
     */
    public VerVentasMB() {
    }
    
    /**
     * Metodo que inicializa el bean
     */
    @PostConstruct
    public void init() {
        //Cargar tabla
        listaVentas = detalleVentaFacade.findAll();
    }
    
   

    public List<DetalleVenta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<DetalleVenta> listaVentas) {
        this.listaVentas = listaVentas;
    }
    
}
