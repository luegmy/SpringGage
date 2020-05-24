package com.gage.venta.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gage.venta.entidad.ComprobanteJPA;

@Repository
public interface ComprobanteDAO extends CrudRepository<ComprobanteJPA, Integer> {

}
