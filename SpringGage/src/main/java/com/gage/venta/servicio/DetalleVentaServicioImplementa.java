package com.gage.venta.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.venta.entidad.DetalleVentaJPA;
import com.gage.venta.repositorio.DetalleVentaDAO;

@Service
public class DetalleVentaServicioImplementa extends GenericoServicioImplemeta<DetalleVentaJPA, Integer> implements DetalleVentaServicio {

	@Autowired
	DetalleVentaDAO detalleVentaDao;

	@Override
	public JpaRepository<DetalleVentaJPA, Integer> getDao() {
		return detalleVentaDao;
	}

}
