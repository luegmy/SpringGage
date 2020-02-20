package com.gage.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.gage.entidad.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
