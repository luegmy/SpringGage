package com.gage.incluido.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gage.incluido.entidad.ClienteJPA;
import com.gage.incluido.entidad.DocumentoIdentidadJPA;
import com.gage.incluido.repositorio.DocumentoDAO;
import com.gage.incluido.servicio.ClienteServicio;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/vistas/rest/cliente" })
public class ClienteRestControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private DocumentoDAO documentoRepositorio;

	// Para conexion con angular
	@GetMapping("/pagina")
	public ResponseEntity<Page<ClienteJPA>> listar(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		Page<ClienteJPA> clientes = clienteServicio.paginas(PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			clientes = clienteServicio.paginas(PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<ClienteJPA>>(clientes, HttpStatus.OK);
	}

	// Para conexion con angular
	@GetMapping("/")
	public List<ClienteJPA> listar() {
		return clienteServicio.listar();
	}

	// Para conexion con angular
	@PostMapping("/guardar")
	public ClienteJPA guardar(@RequestBody ClienteJPA cliente) {
		return clienteServicio.guardar(cliente);
	}

	// Para conexion con angular
	@GetMapping("/editar/{codigo}")
	public ClienteJPA editar(@PathVariable("codigo") int codigo) {
		return clienteServicio.obtener(codigo);
	}

	// Para conexion con angular
	@DeleteMapping("/eliminar/{codigo}")
	public void eliminar(@PathVariable("codigo") int codigo) {
		clienteServicio.eliminar(codigo);
	}

	@GetMapping("/documento")
	public List<DocumentoIdentidadJPA> listarDocumento() {
		return (List<DocumentoIdentidadJPA>) documentoRepositorio.findAll();
	}

	@GetMapping("/documento/{codigo}")
	public Optional<DocumentoIdentidadJPA> listarDocumentoCodigo(@PathVariable("codigo") String codigo) {
		return documentoRepositorio.findById(codigo);
	}

}
