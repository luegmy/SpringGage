package com.gage.venta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.gage.venta.entidad.VentaJPA;

public interface VentaDAO extends JpaRepository<VentaJPA, Integer> {

}
