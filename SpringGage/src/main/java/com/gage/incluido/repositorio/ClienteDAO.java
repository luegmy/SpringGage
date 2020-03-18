package com.gage.incluido.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.ClienteJPA;

public interface ClienteDAO extends CrudRepository<ClienteJPA, Integer> {

}
