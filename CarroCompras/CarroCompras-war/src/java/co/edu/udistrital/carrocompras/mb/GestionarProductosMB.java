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
import co.edu.udistrital.carrocompras.util.ProductoDataModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.jboss.logging.Logger;

/**
 *
 * @author Leonardo
 */
@ManagedBean
@RequestScoped
public class GestionarProductosMB {

    // /////////////////////////////////////////////////////////////////////////
    // Logger de la clase
    // /////////////////////////////////////////////////////////////////////////
    private static Logger _logger = Logger.getLogger(GestionarProductosMB.class);

    //////////////////////////////////////
    ////Atributos de la clase
    //////////////////////////////////////
    @EJB
    private ProductoFacadeLocal productoFacade;
    @EJB
    private CategoriaFacadeLocal categoriaFacade;

    private List<Producto> productos;
    private Producto selectedProducto;
    private ProductoDataModel mediumProductoModel;

    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private int cantidad;
    private Integer idCategoria;
    private List<Categoria> listaCategorias;
    private Map<String, Integer> listNombreCat;

    //////////////////////////////////////
    ////Métodos de la clase
    //////////////////////////////////////
    public GestionarProductosMB() {
    }

    /**
     * Metodo que inicializa el bean
     */
    @PostConstruct
    public void init() {
        //Cargar tabla
        cargarTabla();
        //Cargar la lista de categorias
        listarCategorias();
    }

    /**
     * Guarda el producto
     */
    public void guardarProducto() {
        try {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setProdNombre(this.nombreProducto);
            nuevoProducto.setProdDescripcion(this.descripcion);
            nuevoProducto.setProdPrecio(this.precio);
            nuevoProducto.setProdCantidad(this.cantidad);
            //Asociación con Categoría
            Categoria categoria = new Categoria();
            categoria.setCatId(idCategoria);
            nuevoProducto.setCatId(categoria);
            //Guardar
            productoFacade.create(nuevoProducto);
            cargarTabla();
            limpiarValores();
            _logger.info("Producto creado");
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }
    

    /**
     * Modifica el producto
     */
    public void modificarProducto() {
        try {
            selectedProducto = (Producto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedProducto");
            if (selectedProducto != null) {
                //Setear valores
                selectedProducto.setProdNombre(this.nombreProducto);
                selectedProducto.setProdDescripcion(this.descripcion);
                selectedProducto.setProdPrecio(this.precio);
                selectedProducto.setProdCantidad(this.cantidad);
                Categoria categoria = new Categoria();
                categoria.setCatId(idCategoria);
                selectedProducto.setCatId(categoria);
                //Modificar
                productoFacade.edit(selectedProducto);
                this.cargarTabla();
                _logger.info("Producto modificado");
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Elimina el producto
     */
    public void eliminarProducto() {
        try {
            selectedProducto = (Producto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedProducto");
            if (selectedProducto != null) {
                //Eliminar
                productoFacade.remove(selectedProducto);
                this.cargarTabla();
                _logger.info("Producto eliminado");
            }
        } catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Carga objeto a modificar
     */
    public void cargarValoresModificar() {
        if (null == selectedProducto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR, no fue posible recuperar los datos del registro", ""));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedProducto", selectedProducto);
            nombreProducto = selectedProducto.getProdNombre();
            descripcion = selectedProducto.getProdDescripcion();
            precio = selectedProducto.getProdPrecio();
            cantidad = selectedProducto.getProdCantidad();
            idCategoria = selectedProducto.getCatId().getCatId();
        }
    }
    
    /**
     * Carga el objeto a eliminar en sesión
     */
    public void cargarObjetoSeleccionado() {
        if (null == selectedProducto) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR, no pudo ser eliminado el registro", ""));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedProducto", selectedProducto);
        }
    }

    /**
     * Carga la tabla de productos
     */
    private void cargarTabla() {
        productos = new ArrayList<Producto>();
        // Consulta todos los productos
        productos = productoFacade.findAll();
        // Adiciona al data model para selección por pantalla
        mediumProductoModel = new ProductoDataModel(productos);
    }

    /**
     * Carga las categorías disponibles
     */
    private void listarCategorias() {
        listaCategorias = categoriaFacade.findAll();
        if (listNombreCat == null) {
            listNombreCat = new HashMap<String, Integer>();
        }
        for (Categoria catTemp : listaCategorias) {
            listNombreCat.put(catTemp.getNombre(), catTemp.getCatId());
        }
    }

    /**
     * Limpia valores de ventana emergente
     */
    public void limpiarValores() {
        this.idCategoria = null;
        this.nombreProducto = null;
        this.descripcion = null;
        this.precio = 0D;
        this.cantidad = 0;
    }

    //////////////////////////////////////
    ////Getters y Setters
    //////////////////////////////////////
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

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Map<String, Integer> getListNombreCat() {
        return listNombreCat;
    }

    public void setListNombreCat(Map<String, Integer> listNombreCat) {
        this.listNombreCat = listNombreCat;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

}
