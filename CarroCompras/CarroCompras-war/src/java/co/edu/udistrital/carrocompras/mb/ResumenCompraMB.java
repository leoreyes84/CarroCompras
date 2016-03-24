/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carrocompras.mb;

import co.edu.udistrital.carro.compras.entity.DetalleVenta;
import co.edu.udistrital.carro.compras.entity.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gerson Cespedes
 */
@ManagedBean
@RequestScoped
public class ResumenCompraMB implements Serializable {

    private List<DetalleVenta> listaDetalleVenta;
    private Double valorTotalPago = 0d;
    private Integer cantArticulosComprados = 0;

    /**
     * Creates a new instance of BuscarProductosMB
     */
    public ResumenCompraMB() {
    }

    /**
     * Metodo que inicializa el backing bean
     */
    @PostConstruct
    public void init() {

        listaDetalleVenta = (List<DetalleVenta>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaProdVendidos");

        if (listaDetalleVenta != null && listaDetalleVenta.size() > 0) {
            for (DetalleVenta detalleVentaTemp : listaDetalleVenta) {
                valorTotalPago += detalleVentaTemp.getVenValor();
                cantArticulosComprados++;
            }
        }

    }

    public List<DetalleVenta> getListaDetalleVenta() {
        return listaDetalleVenta;
    }

    public void setListaDetalleVenta(List<DetalleVenta> listaDetalleVenta) {
        this.listaDetalleVenta = listaDetalleVenta;
    }

    public Double getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(Double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }

    public Integer getCantArticulosComprados() {
        return cantArticulosComprados;
    }

    public void setCantArticulosComprados(Integer cantArticulosComprados) {
        this.cantArticulosComprados = cantArticulosComprados;
    }

}
