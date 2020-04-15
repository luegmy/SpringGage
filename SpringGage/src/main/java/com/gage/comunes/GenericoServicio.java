package com.gage.comunes;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericoServicio<T, ID extends Serializable> {

	T guardar(T entidad);

	void eliminar(ID codigo);

	T obtener(ID codigo);

	List<T> listar();
	
	Page<T>paginas(Pageable pagina);
}
