package com.gage.incluido.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.incluido.entidad.ProductoJPA;
import com.gage.incluido.repositorio.ProductoDAO;

@Service
public class ProductoServicioImplementa extends GenericoServicioImplemeta<ProductoJPA, Integer> implements ProductoServicio{

	@Autowired
	ProductoDAO productoDao;
	@Override
	public JpaRepository<ProductoJPA, Integer> getDao() {
		return productoDao;
	}


}
