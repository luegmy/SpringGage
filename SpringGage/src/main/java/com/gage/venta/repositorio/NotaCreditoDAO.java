package com.gage.venta.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.venta.entidad.NotaCreditoJPA;

public interface NotaCreditoDAO extends CrudRepository<NotaCreditoJPA, Integer> {

}
