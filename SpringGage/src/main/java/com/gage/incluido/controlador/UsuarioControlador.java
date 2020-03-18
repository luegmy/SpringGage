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

import com.gage.incluido.entidad.Usuario;
import com.gage.incluido.servicio.UsuarioServicio;

@Controller
@RequestMapping("/vistas/usuario")
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("lista", usuarioServicio.listar());
		return "/vistas/usuario/index";
	}

	@GetMapping("/editar/{codigo}")
	public String editar(@PathVariable("id") int codigo, Model model) {
		Usuario usuario=usuarioServicio.obtener(codigo);
		model.addAttribute("titulo", "Editar suario");
		model.addAttribute("usuario", usuario);
		return "/vistas/usuario/usuario";
	}
	
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Usuario usuario=new Usuario();
		model.addAttribute("titulo", "Nuevo usuario");
		model.addAttribute("usuario", usuario);
		return "/vistas/usuario/usuario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Nuevo usuario");
			model.addAttribute("usuario", usuario);
			return "/vistas/usuario/usuario";
		}
		usuarioServicio.guardar(usuario);
		return "redirect:/vistas/usuario/";

	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int codigo, Model model) {
		usuarioServicio.eliminar(codigo);
		return "redirect:/vistas/usuario/";

	}

}
