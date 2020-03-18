package com.gage.incluido.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.ProductoJPA;

public interface ProductoDAO extends CrudRepository<ProductoJPA, Integer>{

}
