package com.gage.venta.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_notacredito")
public class NotaCreditoJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String codCredito;
	private String descripcion;

	public String getCodCredito() {
		return codCredito;
	}

	public void setCodCredito(String codCredito) {
		this.codCredito = codCredito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
