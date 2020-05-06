package com.gage.venta.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gage.venta.entidad.VentaJPA;

public interface VentaDAO extends JpaRepository<VentaJPA, Integer> {

}
