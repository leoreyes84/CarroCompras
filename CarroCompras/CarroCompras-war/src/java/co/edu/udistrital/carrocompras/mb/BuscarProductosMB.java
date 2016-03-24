/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.Categoria;
import co.edu.udistrital.carro.compras.entity.Producto;
import co.edu.udistrital.carro.compras.session.CategoriaFacadeLocal;
import co.edu.udistrital.carro.compras.session.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gerson Cespedes
 */
@ManagedBean
@RequestScoped
public class BuscarProductosMB implements Serializable {

    private List<Producto> listaProductos;
    private List<Producto> productosSeleccionados;
    private List<Categoria> listaCategorias;
    private Map<String, Integer> listNombreCat = new HashMap<String, Integer>();

    private Integer idCategoria;

    @EJB
    private ProductoFacadeLocal productoFacade;
    @EJB
    private CategoriaFacadeLocal categoriaFacade;

    /**
     * Creates a new instance of BuscarProductosMB
     */
    public BuscarProductosMB() {
    }

    /**
     * Metodo que inicializa el backing bean
     */
    @PostConstruct
    public void init() {

        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }
        listarProductos();
        listarCategorias();
    }

    private void listarCategorias() {

        listaCategorias = categoriaFacade.findAll();
        if (listNombreCat == null) {
            listNombreCat = new HashMap<>();
        }

        for (Categoria catTemp : listaCategorias) {

            listNombreCat.put(catTemp.getNombre(), catTemp.getCatId());
            System.out.println(catTemp.getNombre());
        }

    }

    private void listarProductos() {

        listaProductos = productoFacade.findAll();

        for (Producto proTemp : listaProductos) {
            System.out.println(proTemp.getProdNombre());
        }
    }

    public void filtrarProducto() {
        System.out.println("New value: " + idCategoria);

        if (idCategoria == null) {
            listaProductos = productoFacade.findAll();
        } else {

            listaProductos = productoFacade.productoPorCategoria(idCategoria);
        }

    }
    
    public void irACompras(){
        System.out.println("Entró al método de ir a compras");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaProductosSel", productosSeleccionados);
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;

    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Map<String, Integer> getListNombreCat() {
        return listNombreCat;
    }

    public void setListNombreCat(Map<String, Integer> listNombreCat) {
        this.listNombreCat = listNombreCat;
    }

    public List<Producto> getProductosSeleccionados() {
        return productosSeleccionados;
    }

    public void setProductosSeleccionados(List<Producto> productosSeleccionados) {
        this.productosSeleccionados = productosSeleccionados;
    }

}
