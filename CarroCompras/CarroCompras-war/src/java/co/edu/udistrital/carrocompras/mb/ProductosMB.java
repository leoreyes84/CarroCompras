/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.Producto;
import co.edu.udistrital.carro.compras.session.ProductoFacadeLocal;
import co.edu.udistrital.carrocompras.util.ProductoDataModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Leonardo
 */
@ManagedBean(name="productosMB")
@RequestScoped
public class ProductosMB {
    @EJB
    private ProductoFacadeLocal productoFacade;
    private List<Producto> productos;
    private Producto selectedProducto;
    private ProductoDataModel mediumProductoModel;
    
    /**
     * Creates a new instance of ProductosMB
     */
    public ProductosMB() {
    }
    
    
    /**
     * Metodo que inicializa el bean
     */
    @PostConstruct
    public void init(){
        productos = new ArrayList<Producto>();
        // Consulta todos los productos
        productos = productoFacade.findAll();
        // Adiciona al data model para selección por pantalla
        mediumProductoModel = new ProductoDataModel(productos);
    }
    
    

    public Producto getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(Producto selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public ProductoDataModel getMediumProductoModel() {
        return mediumProductoModel;
    }

    public void setMediumProductoModel(ProductoDataModel mediumProductoModel) {
        this.mediumProductoModel = mediumProductoModel;
    }
    
    
    
    
}
