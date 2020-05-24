package com.gage.incluido.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.EstadoJPA;

public interface EstadoDAO extends CrudRepository<EstadoJPA, Integer> {

}
