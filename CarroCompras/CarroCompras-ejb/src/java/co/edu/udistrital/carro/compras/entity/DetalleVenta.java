/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carro.compras.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d"),
    @NamedQuery(name = "DetalleVenta.findByVenId", query = "SELECT d FROM DetalleVenta d WHERE d.venId = :venId"),
    @NamedQuery(name = "DetalleVenta.findByVenFecha", query = "SELECT d FROM DetalleVenta d WHERE d.venFecha = :venFecha"),
    @NamedQuery(name = "DetalleVenta.findByVenValor", query = "SELECT d FROM DetalleVenta d WHERE d.venValor = :venValor"),
    @NamedQuery(name = "DetalleVenta.findByVenCantidad", query = "SELECT d FROM DetalleVenta d WHERE d.venCantidad = :venCantidad"),
    @NamedQuery(name = "DetalleVenta.findByVenComentario", query = "SELECT d FROM DetalleVenta d WHERE d.venComentario = :venComentario"),
    @NamedQuery(name = "DetalleVenta.findByVenMedioPago", query = "SELECT d FROM DetalleVenta d WHERE d.venMedioPago = :venMedioPago"),
    @NamedQuery(name = "DetalleVenta.findByUsrId", query = "SELECT d FROM DetalleVenta d WHERE d.usrId = :usrId"),
    @NamedQuery(name = "DetalleVenta.findByProdId", query = "SELECT d FROM DetalleVenta d WHERE d.prodId = :prodId")})
public class DetalleVenta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ven_id")
    private Integer venId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ven_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date venFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ven_valor")
    private double venValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ven_cantidad")
    private int venCantidad;
    @Size(max = 45)
    @Column(name = "ven_comentario")
    private String venComentario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ven_medio_pago")
    private String venMedioPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usr_id")
    private int usrId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_id")
    private int prodId;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer venId) {
        this.venId = venId;
    }

    public DetalleVenta(Integer venId, Date venFecha, double venValor, int venCantidad, String venMedioPago, int usrId, int prodId) {
        this.venId = venId;
        this.venFecha = venFecha;
        this.venValor = venValor;
        this.venCantidad = venCantidad;
        this.venMedioPago = venMedioPago;
        this.usrId = usrId;
        this.prodId = prodId;
    }

    public Integer getVenId() {
        return venId;
    }

    public void setVenId(Integer venId) {
        this.venId = venId;
    }

    public Date getVenFecha() {
        return venFecha;
    }

    public void setVenFecha(Date venFecha) {
        this.venFecha = venFecha;
    }

    public double getVenValor() {
        return venValor;
    }

    public void setVenValor(double venValor) {
        this.venValor = venValor;
    }

    public int getVenCantidad() {
        return venCantidad;
    }

    public void setVenCantidad(int venCantidad) {
        this.venCantidad = venCantidad;
    }

    public String getVenComentario() {
        return venComentario;
    }

    public void setVenComentario(String venComentario) {
        this.venComentario = venComentario;
    }

    public String getVenMedioPago() {
        return venMedioPago;
    }

    public void setVenMedioPago(String venMedioPago) {
        this.venMedioPago = venMedioPago;
    }

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venId != null ? venId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.venId == null && other.venId != null) || (this.venId != null && !this.venId.equals(other.venId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udistrital.carro.compras.entity.DetalleVenta[ venId=" + venId + " ]";
    }
    
}
