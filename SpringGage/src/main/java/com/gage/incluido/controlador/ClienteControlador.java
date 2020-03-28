package com.gage.incluido.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gage.incluido.entidad.ClienteJPA;
import com.gage.incluido.entidad.DocumentoIdentidadJPA;
import com.gage.incluido.repositorio.DocumentoDAO;
import com.gage.incluido.servicio.ClienteServicio;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({ "/vistas/incluido/cliente" })
public class ClienteControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private DocumentoDAO documentoRepositorio;

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
		return  documentoRepositorio.findById(codigo);
	}

	/*
	 * @GetMapping("/") public String listar(Model model) {
	 * model.addAttribute("titulo", "Listado de clientes");
	 * model.addAttribute("lista", clienteServicio.listar()); return
	 * "/vistas/incluido/listaCliente"; }
	 * 
	 * @GetMapping("/editar/{codigo}") public String editar(@PathVariable("codigo")
	 * int codigo, Model model) { ClienteJPA cliente =
	 * clienteServicio.obtener(codigo); model.addAttribute("titulo",
	 * "Editar cliente"); model.addAttribute("cliente", cliente);
	 * model.addAttribute("documentos", documentoRepositorio.findAll()); return
	 * "/vistas/incluido/cliente"; }
	 * 
	 * @GetMapping("/crear") public String crear(Model model) { ClienteJPA cliente =
	 * new ClienteJPA(); model.addAttribute("titulo", "Nuevo cliente");
	 * model.addAttribute("cliente", cliente); model.addAttribute("documentos",
	 * documentoRepositorio.findAll()); return "/vistas/incluido/cliente"; }
	 * 
	 * @PostMapping("/guardar") public String guardar(@Valid @ModelAttribute
	 * ClienteJPA cliente, BindingResult result, Model model) { if
	 * (result.hasErrors()) { model.addAttribute("titulo", "Nuevo cliente");
	 * model.addAttribute("cliente", cliente); model.addAttribute("documentos",
	 * documentoRepositorio.findAll()); return "/vistas/incluido/cliente"; }
	 * clienteServicio.guardar(cliente); return
	 * "redirect:/vistas/incluido/cliente/";
	 * 
	 * }
	 * 
	 * @GetMapping("/eliminar/{id}") public String eliminar(@PathVariable("id") int
	 * codigo, Model model) { model.addAttribute("titulo", "Eliminar cliente");
	 * clienteServicio.eliminar(codigo); return
	 * "redirect:/vistas/incluido/cliente/";
	 * 
	 * }
	 */
}
