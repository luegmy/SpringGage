package com.gage.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.entidad.Usuario;
import com.gage.repositorio.UsuarioDao;

@Service
public class UsuarioServicioImplementa extends GenericoServicioImplemeta<Usuario, Integer> implements UsuarioServicio{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public CrudRepository<Usuario, Integer> getDao() {
		return usuarioDao;
	}

}
