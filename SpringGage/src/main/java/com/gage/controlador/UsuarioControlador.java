package com.gage.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gage.entidad.Usuario;
import com.gage.servicio.UsuarioServicio;

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

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") int codigo, Model model) {

		if (codigo != 0) {

			model.addAttribute("titulo", "Editar usuario");
			model.addAttribute("usuario", usuarioServicio.obtener(codigo));
		} else {

			model.addAttribute("titulo", "Crear suario");
			model.addAttribute("usuario", new Usuario());
		}
		return "/vistas/usuario/usuario";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute Usuario usuario,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear suario");
			model.addAttribute("usuario", new Usuario());
			System.out.println("error");
			return "/vistas/usuario/usuario";
		}
		usuarioServicio.guardar(usuario);
		return "redirect:/vistas/usuario/";

	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int codigo, Model model) {
		usuarioServicio.eliminar(codigo);
		return "redirect:/vistas/usuario/";

	}

}
