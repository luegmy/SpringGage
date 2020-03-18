package com.gage.venta.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "tb_comprobante")
public class ComprobanteJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int codComprobante;
	private String descripcion;

	public int getCodComprobante() {
		return codComprobante;
	}

	public void setCodComprobante(int codComprobante) {
		this.codComprobante = codComprobante;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
