package com.gage.venta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gage.venta.entidad.VentaJPA;

@Repository
public interface VentaDAO extends JpaRepository<VentaJPA, Integer> {
	 
}
