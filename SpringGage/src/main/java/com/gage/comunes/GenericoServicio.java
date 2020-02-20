package com.gage.comunes;

import java.io.Serializable;
import java.util.List;

public interface GenericoServicio<T, ID extends Serializable> {

	T guardar(T entidad);

	void eliminar(ID codigo);

	T obtener(ID codigo);

	List<T> listar();
}
