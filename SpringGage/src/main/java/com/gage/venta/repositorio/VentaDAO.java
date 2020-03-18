package com.gage.venta.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.venta.entidad.VentaJPA;

public interface VentaDAO extends CrudRepository<VentaJPA, Integer> {

}
