package com.gage.incluido.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gage.incluido.entidad.ProductoJPA;

public interface ProductoDAO extends JpaRepository<ProductoJPA, Integer>{

}
