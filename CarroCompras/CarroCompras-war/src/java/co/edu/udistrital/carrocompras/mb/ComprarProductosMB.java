/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.DetalleVenta;
import co.edu.udistrital.carro.compras.entity.Producto;
import co.edu.udistrital.carro.compras.entity.Usuario;
import co.edu.udistrital.carro.compras.session.DetalleVentaFacadeLocal;
import co.edu.udistrital.carro.compras.session.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gerson Cespedes
 */
@ManagedBean
@RequestScoped
public class ComprarProductosMB implements Serializable {

    private List<DetalleVenta> listaDetalleVenta;
    private List<Producto> productosSelCompra;
    private List<Producto> listaProdExistentes;
    private Double valorTotalPago = 0d;
    private Integer cantidadArticulos = 0;
    private String medioPagoProducto;
    private String comentarioPago;

    @EJB
    private DetalleVentaFacadeLocal detalleVentaFacade;
    @EJB
    private ProductoFacadeLocal productoFacade;

    /**
     * Creates a new instance of BuscarProductosMB
     */
    public ComprarProductosMB() {
    }

    /**
     * Metodo que inicializa el backing bean
     */
    @PostConstruct
    public void init() {
//        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
//        System.out.println("nombre de usuario: "+username);
        if (productosSelCompra == null) {
            productosSelCompra = new ArrayList<>();
        }
        comentarioPago = "";
        productosSelCompra = (List<Producto>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaProductosSel");

        if (productosSelCompra != null && productosSelCompra.size() > 0) {
            for (Producto productoTemp : productosSelCompra) {
                valorTotalPago += productoTemp.getProdPrecio();
                cantidadArticulos++;
            }
        }
    }

    public void prepararPago() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Pago Realizado", "Pago Exitoso "));
        listaDetalleVenta = new ArrayList<>();
        listaProdExistentes = productoFacade.findAll();
        if (productosSelCompra != null && productosSelCompra.size() > 0) {
            DetalleVenta detalleVentaTemp = new DetalleVenta();
            for (Producto productoTemp : productosSelCompra) {

                detalleVentaTemp.setProdId(productoTemp);
                detalleVentaTemp.setVenFecha(new Date());
                detalleVentaTemp.setVenValor(productoTemp.getProdPrecio());
                detalleVentaTemp.setVenCantidad(1);
                detalleVentaTemp.setVenMedioPago(medioPagoProducto);
                detalleVentaTemp.setVenComentario(comentarioPago);
                detalleVentaTemp.setUsrId(new Usuario());
                detalleVentaTemp.getUsrId().setUsrId(1);

                detalleVentaFacade.create(detalleVentaTemp);
                listaDetalleVenta.add(detalleVentaTemp);
                int cantExistente = 0;
                for (Producto productoTemp2 : listaProdExistentes) {
                    if (productoTemp2.getProdId().intValue() == detalleVentaTemp.getProdId().getProdId()) {
                        cantExistente = productoTemp2.getProdCantidad();
                        cantExistente = cantExistente - detalleVentaTemp.getVenCantidad();
                        productoTemp2.setProdCantidad(cantExistente);
                        productoFacade.edit(productoTemp2);
                    }
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaProdVendidos", listaDetalleVenta);
        }
    }

    public List<Producto> getProductosSelCompra() {
        return productosSelCompra;
    }

    public void setProductosSelCompra(List<Producto> productosSelCompra) {
        this.productosSelCompra = productosSelCompra;
    }

    public Double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(Double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public Integer getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(Integer cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public String getMedioPagoProducto() {
        return medioPagoProducto;
    }

    public void setMedioPagoProducto(String medioPagoProducto) {
        this.medioPagoProducto = medioPagoProducto;
    }

    public List<DetalleVenta> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVenta> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public String getComentarioPago() {
        return comentarioPago;
    }

    public void setComentarioPago(String comentarioPago) {
        this.comentarioPago = comentarioPago;
    }

}
