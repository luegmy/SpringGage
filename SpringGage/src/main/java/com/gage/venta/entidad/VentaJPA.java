
package com.gage.venta.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gage.incluido.entidad.ClienteJPA;
import com.gage.seguridad.entidad.UsuarioJPA;


@Entity
@Table(name = "tb_venta")

public class VentaJPA implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int numComprobante;
	private String serie;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private String hora;
	private BigDecimal monto;
	private String observacion;
	private int numNota;

	@ManyToOne
	@JoinColumn(name = "codCliente")
	private ClienteJPA cliente;

	@ManyToOne
	@JoinColumn(name = "codComprobante")
	private ComprobanteJPA comprobante;

	@ManyToOne
	@JoinColumn(name = "codUsuario")
	private UsuarioJPA usuario;

	@JsonManagedReference
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<DetalleVentaJPA> detalles;

	public int getNumComprobante() {
		return numComprobante;
	}

	public void setNumComprobante(int numComprobante) {
		this.numComprobante = numComprobante;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getNumNota() {
		return numNota;
	}

	public void setNumNota(int numNota) {
		this.numNota = numNota;
	}

	public ClienteJPA getCliente() {
		return cliente;
	}

	public void setCliente(ClienteJPA cliente) {
		this.cliente = cliente;
	}

	public ComprobanteJPA getComprobante() {
		return comprobante;
	}

	public void setComprobante(ComprobanteJPA comprobante) {
		this.comprobante = comprobante;
	}

	public UsuarioJPA getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioJPA usuario) {
		this.usuario = usuario;
	}

	public List<DetalleVentaJPA> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleVentaJPA> detalles) {
		this.detalles = detalles;
	}

}
