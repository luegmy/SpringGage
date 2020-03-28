package com.gage.incluido.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_cliente")

public class ClienteJPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCliente;
	@NotNull(message = "Ingrese un nombre")
	private String nombre;
	@NotNull(message = "Ingrese una direccion")
	private String direccion;
	@NotNull(message = "Seleccione el docuemnto")
	private String nroDocumento;
	private String telefono;
	private String correo;

	@ManyToOne
	@JoinColumn(name = "codDocumento")
	private DocumentoIdentidadJPA documento;

	public int getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public DocumentoIdentidadJPA getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoIdentidadJPA documento) {
		this.documento = documento;
	}

}
