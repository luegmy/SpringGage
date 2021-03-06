package com.gage.incluido.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.incluido.entidad.Usuario;
import com.gage.incluido.repositorio.UsuarioDao;

@Service
public class UsuarioServicioImplementa extends GenericoServicioImplemeta<Usuario, Integer> implements UsuarioServicio{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public JpaRepository<Usuario, Integer> getDao() {
		return usuarioDao;
	}

}
