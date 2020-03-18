package com.gage.incluido.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.gage.comunes.GenericoServicioImplemeta;
import com.gage.incluido.entidad.ClienteJPA;
import com.gage.incluido.repositorio.ClienteDAO;

@Service
public class ClienteServicioImplementa extends GenericoServicioImplemeta<ClienteJPA, Integer>
		implements ClienteServicio {

	@Autowired
	ClienteDAO clienteDao;

	@Override
	public CrudRepository<ClienteJPA, Integer> getDao() {
		return clienteDao;
	}

}
