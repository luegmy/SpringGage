package com.gage.venta.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gage.incluido.servicio.ClienteServicio;
import com.gage.venta.entidad.VentaJPA;
import com.gage.venta.repositorio.ComprobanteDAO;
import com.gage.venta.servicio.VentaServicio;

@Controller
@RequestMapping("/vistas/venta")
public class VentaControlador {

	@Autowired
	private VentaServicio ventaServicio;
	
	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private ComprobanteDAO comprobanteRepositorio;

	@GetMapping("/")
	public String listarVentamod(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("lista", ventaServicio.listar());
		return "/vistas/venta/listaVenta";
	}

	@GetMapping("/crear")
	public String crear(Model model) {
		VentaJPA venta = new VentaJPA();
		model.addAttribute("titulo", "Generar venta");
		model.addAttribute("venta", venta);
		model.addAttribute("comprobantes", comprobanteRepositorio.findAll());
		model.addAttribute("clientes", clienteServicio.listar());
		return "/vistas/venta/venta";
	}

	@PostMapping("/grabar")
	public String grabar(@Valid @ModelAttribute VentaJPA venta, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Generar Venta");
			model.addAttribute("venta", venta);
			model.addAttribute("comprobantes", comprobanteRepositorio.findAll());
			return "/vistas/venta/venta";
		}
		ventaServicio.guardar(venta);
		return "redirect:/vistas/venta/";

	}
}
