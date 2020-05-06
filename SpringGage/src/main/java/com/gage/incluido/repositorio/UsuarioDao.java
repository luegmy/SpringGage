package com.gage.incluido.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gage.incluido.entidad.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

}
