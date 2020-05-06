package com.gage.venta.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gage.venta.entidad.DetalleVentaJPA;
import com.gage.venta.entidad.DetalleVentaJPAPK;

@Repository
public interface DetalleVentaDAO extends JpaRepository<DetalleVentaJPA,Integer> {
	
	@Query("select d from DetalleVentaJPA d where d.id.numComprobante=?1")
	List<DetalleVentaJPA>findByFactura(int numero);
	
	DetalleVentaJPA findById(DetalleVentaJPAPK id);

}
