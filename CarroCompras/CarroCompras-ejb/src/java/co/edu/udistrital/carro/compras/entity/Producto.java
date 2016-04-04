/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udistrital.carro.compras.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lreyes
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByProdId", query = "SELECT p FROM Producto p WHERE p.prodId = :prodId"),
    @NamedQuery(name = "Producto.findByProdNombre", query = "SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre"),
    @NamedQuery(name = "Producto.findByProdDescripcion", query = "SELECT p FROM Producto p WHERE p.prodDescripcion = :prodDescripcion"),
    @NamedQuery(name = "Producto.findByProdPrecio", query = "SELECT p FROM Producto p WHERE p.prodPrecio = :prodPrecio"),
    @NamedQuery(name = "Producto.findByProdCantidad", query = "SELECT p FROM Producto p WHERE p.prodCantidad = :prodCantidad")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_id")
    private Integer prodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "prod_nombre")
    private String prodNombre;
    @Size(max = 45)
    @Column(name = "prod_descripcion")
    private String prodDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_precio")
    private double prodPrecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_cantidad")
    private int prodCantidad;
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    @ManyToOne(optional = false)
    private Categoria catId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private List<DetalleVenta> detalleVentaList;    
    @Size(max = 255)
    @Column(name = "prod_ruta_img")
    private String prodRutaImg;

    public Producto() {
    }

    public Producto(Integer prodId) {
        this.prodId = prodId;
    }

    public Producto(Integer prodId, String prodNombre, double prodPrecio, int prodCantidad) {
        this.prodId = prodId;
        this.prodNombre = prodNombre;
        this.prodPrecio = prodPrecio;
        this.prodCantidad = prodCantidad;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdDescripcion() {
        return prodDescripcion;
    }

    public void setProdDescripcion(String prodDescripcion) {
        this.prodDescripcion = prodDescripcion;
    }

    public double getProdPrecio() {
        return prodPrecio;
    }

    public void setProdPrecio(double prodPrecio) {
        this.prodPrecio = prodPrecio;
    }

    public int getProdCantidad() {
        return prodCantidad;
    }

    public void setProdCantidad(int prodCantidad) {
        this.prodCantidad = prodCantidad;
    }

    public Categoria getCatId() {
        return catId;
    }

    public void setCatId(Categoria catId) {
        this.catId = catId;
    }

    @XmlTransient
    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public String getProdRutaImg() {
        return prodRutaImg;
    }

    public void setProdRutaImg(String prodRutaImg) {
        this.prodRutaImg = prodRutaImg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.udistrital.carro.compras.entity.Producto[ prodId=" + prodId + " ]";
    }
    
}
