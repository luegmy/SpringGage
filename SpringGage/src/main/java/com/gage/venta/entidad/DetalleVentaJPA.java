package com.gage.venta.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gage.incluido.entidad.ProductoJPA;

@Entity
@Table(name = "tb_detalle_venta")

public class DetalleVentaJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleVentaJPAPK id;

	private BigDecimal cantidad;
	private BigDecimal precio;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "numComprobante", nullable = false, insertable = false, updatable = false)
	private VentaJPA venta;

	@ManyToOne
	@JoinColumn(name = "codProducto", nullable = false, insertable = false, updatable = false)
	private ProductoJPA producto;


	public DetalleVentaJPAPK getId() {
		return id;
	}

	public void setId(DetalleVentaJPAPK id) {
		this.id = id;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public VentaJPA getVenta() {
		return venta;
	}

	public void setVenta(VentaJPA venta) {
		this.venta = venta;
	}

	public ProductoJPA getProducto() {
		return producto;
	}

	public void setProducto(ProductoJPA producto) {
		this.producto = producto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleVentaJPA other = (DetalleVentaJPA) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
