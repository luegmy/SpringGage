package com.gage.incluido.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.ClienteJPA;

public interface ClienteDAO extends JpaRepository<ClienteJPA, Integer> {

}
