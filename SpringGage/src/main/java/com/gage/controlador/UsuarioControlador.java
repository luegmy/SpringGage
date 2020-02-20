package com.gage.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gage.entidad.Usuario;
import com.gage.servicio.UsuarioServicio;

@Controller
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("lista", usuarioServicio.listar());
		return "index";
	}

	@GetMapping("/save/{id}")
	public String showSave(@PathVariable("id") int codigo, Model model) {

		if (codigo != 0) {
			model.addAttribute("usuario", usuarioServicio.obtener(codigo));
		} else {
			model.addAttribute("usuario", new Usuario());
		}
		return "usuario";
	}

	@PostMapping("/save")
	public String save(Usuario usuario, Model model) {
		usuarioServicio.guardar(usuario);
		return "redirect:/";

	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int codigo, Model model) {
		usuarioServicio.eliminar(codigo);
		return "redirect:/";

	}

}
