package com.gage.venta.entidad;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class DetalleVentaJPAPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codProducto;
	private int numComprobante;
	
	

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	public int getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(int numComprobante) {
		this.numComprobante = numComprobante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codProducto;
		result = prime * result + numComprobante;
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
		DetalleVentaJPAPK other = (DetalleVentaJPAPK) obj;
		if (codProducto != other.codProducto)
			return false;
		if (numComprobante != other.numComprobante)
			return false;
		return true;
	}

}
