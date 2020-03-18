package com.gage.incluido.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.incluido.entidad.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
