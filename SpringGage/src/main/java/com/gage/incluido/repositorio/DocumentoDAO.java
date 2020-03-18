package com.gage.incluido.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.DocumentoIdentidadJPA;

public interface DocumentoDAO extends CrudRepository<DocumentoIdentidadJPA, String> {

}
