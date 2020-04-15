package com.gage.venta.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.venta.entidad.VentaJPA;
import com.gage.venta.repositorio.VentaDAO;

@Service
public class VentaServicioImplementa extends GenericoServicioImplemeta<VentaJPA, Integer> implements VentaServicio {

	@Autowired
	VentaDAO ventaDao;

	@Override
	public JpaRepository<VentaJPA, Integer> getDao() {
		return ventaDao;
	}

}
