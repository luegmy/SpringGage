package com.gage.venta.entidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gage.incluido.entidad.ProductoJPA;

@Entity
@Table(name = "tb_detalle_venta")

public class DetalleVentaJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleVentaJPAPK id;

	private BigDecimal cantidad;
	private BigDecimal precio;

	@ManyToOne
	@JoinColumn(name = "numComprobante", nullable = false, insertable = false, updatable = false)
	private VentaJPA venta;

	@ManyToOne
	@JoinColumn(name = "codProducto", nullable = false, insertable = false, updatable = false)
	private ProductoJPA producto;

	// para mostrar la descripcion y codigo del producto en el detalle
	@Transient
	private int codigoProducto;
	@Transient
	private String descripcionProducto;
	@Transient
	private String descripcionTipoProducto;
	@Transient
	private int cantidadSuma;
	@Transient
	private double cantidadMonto;
	@Transient
	private String unidadMedida;

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

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getDescripcionTipoProducto() {
		return descripcionTipoProducto;
	}

	public void setDescripcionTipoProducto(String descripcionTipoProducto) {
		this.descripcionTipoProducto = descripcionTipoProducto;
	}

	public int getCantidadSuma() {
		return cantidadSuma;
	}

	public void setCantidadSuma(int cantidadSuma) {
		this.cantidadSuma = cantidadSuma;
	}

	public double getCantidadMonto() {
		return cantidadMonto;
	}

	public void setCantidadMonto(double cantidadMonto) {
		this.cantidadMonto = cantidadMonto;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
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
