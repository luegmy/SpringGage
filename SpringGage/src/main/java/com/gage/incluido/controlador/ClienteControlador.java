package com.gage.incluido.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gage.incluido.entidad.ClienteJPA;
import com.gage.incluido.repositorio.DocumentoDAO;
import com.gage.incluido.servicio.ClienteServicio;

@Controller
@RequestMapping({ "/vistas/incluido/cliente" })
public class ClienteControlador {

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private DocumentoDAO documentoRepositorio;

	@GetMapping("/")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("lista", clienteServicio.listar());
		return "/vistas/incluido/listaCliente";
	}

	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("codigo") int codigo, Model model) {
		ClienteJPA cliente = clienteServicio.obtener(codigo);
		model.addAttribute("titulo", "Editar cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("documentos", documentoRepositorio.findAll());
		return "/vistas/incluido/cliente";
	}

	@GetMapping("/crear")
	public String crear(Model model) {
		ClienteJPA cliente = new ClienteJPA();
		model.addAttribute("titulo", "Nuevo cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("documentos", documentoRepositorio.findAll());
		return "/vistas/incluido/cliente";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute ClienteJPA cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo cliente");
			model.addAttribute("cliente", cliente);
			model.addAttribute("documentos", documentoRepositorio.findAll());
			return "/vistas/incluido/cliente";
		}
		clienteServicio.guardar(cliente);
		return "redirect:/vistas/incluido/cliente/";

	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int codigo, Model model) {
		model.addAttribute("titulo", "Eliminar cliente");
		clienteServicio.eliminar(codigo);
		return "redirect:/vistas/incluido/cliente/";

	}

}
