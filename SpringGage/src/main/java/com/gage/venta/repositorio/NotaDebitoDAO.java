package com.gage.venta.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.venta.entidad.NotaDebitoJPA;

public interface NotaDebitoDAO extends CrudRepository<NotaDebitoJPA, Integer> {

}
