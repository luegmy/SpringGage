package com.gage.comunes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericoServicioImplemeta<T, ID extends Serializable> implements GenericoServicio<T, ID> {

	public abstract JpaRepository<T, ID> getDao();

	@Override
	public T guardar(T entidad) {
		return getDao().save(entidad);
	}

	@Override
	public void eliminar(ID codigo) {
		getDao().deleteById(codigo);
	}

	@Override
	public T obtener(ID codigo) {
		Optional<T> obj = getDao().findById(codigo);
		if (obj.isPresent()) {
			return obj.get();
		}
		return null;
	}

	@Override
	public List<T> listar() {
		List<T> lista = new ArrayList<>();
		getDao().findAll().forEach(obj -> lista.add(obj));
		return lista;
	}
	
	@Override
	public Page<T>paginas(Pageable pagina){
		return getDao().findAll(pagina);
		
	}

}
