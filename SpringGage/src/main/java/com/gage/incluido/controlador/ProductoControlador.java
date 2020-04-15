package com.gage.incluido.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gage.incluido.entidad.ProductoJPA;
import com.gage.incluido.entidad.TipoProductoJPA;
import com.gage.incluido.entidad.UnidadMedidaJPA;
import com.gage.incluido.repositorio.TipoProductoDAO;
import com.gage.incluido.repositorio.UnidadMedidaDAO;
import com.gage.incluido.servicio.ProductoServicio;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vistas/incluido/producto")
public class ProductoControlador {

	@Autowired
	private ProductoServicio productoServicio;

	@Autowired
	private TipoProductoDAO tipoRepositorio;

	@Autowired
	private UnidadMedidaDAO medidaRepositorio;

	@GetMapping("/")
	public List<ProductoJPA> listar() {
		return productoServicio.listar();
	}

	@PostMapping("/guardar")
	public ProductoJPA guardar(@RequestBody ProductoJPA producto) {
		return productoServicio.guardar(producto);
	}
	
	@GetMapping("/editar/{codigo}")
	public ProductoJPA editar(@PathVariable("codigo") int codigo) {
		return productoServicio.obtener(codigo);

	}

	@GetMapping("/medida")
	public List<UnidadMedidaJPA> listarUM() {
		return (List<UnidadMedidaJPA>) medidaRepositorio.findAll();
	}

	@GetMapping("/tipo")
	public List<TipoProductoJPA> listarTipoProducto() {
		return (List<TipoProductoJPA>) tipoRepositorio.findAll();
	}

	/*
	 * @GetMapping("/") public String listar(Model model) {
	 * model.addAttribute("titulo", "Listado de producto");
	 * model.addAttribute("lista", productoServicio.listar()); return
	 * "/vistas/incluido/listaProducto"; }
	 */

	/*
	 * @GetMapping("/editar/{codigo}") public String editar(@PathVariable("codigo")
	 * int codigo, Model model) { ProductoJPA producto =
	 * productoServicio.obtener(codigo); model.addAttribute("titulo",
	 * "Editar producto"); model.addAttribute("producto", producto);
	 * model.addAttribute("tipos", tipoRepositorio.findAll());
	 * model.addAttribute("medidas", medidaRepositorio.findAll()); return
	 * "/vistas/incluido/producto"; }
	 */

	@GetMapping("/crear")
	public String crear(Model model) {
		ProductoJPA producto = new ProductoJPA();
		model.addAttribute("titulo", "Nuevo producto");
		model.addAttribute("producto", producto);
		model.addAttribute("tipos", tipoRepositorio.findAll());
		model.addAttribute("medidas", medidaRepositorio.findAll());
		return "/vistas/incluido/producto";
	}

	/*
	 * @PostMapping("/guardar") public String guardar(@Valid @ModelAttribute
	 * ProductoJPA producto, BindingResult result, Model model) { if
	 * (result.hasErrors()) { model.addAttribute("titulo", "Nuevo producto");
	 * model.addAttribute("producto", producto); model.addAttribute("tipos",
	 * tipoRepositorio.findAll()); model.addAttribute("medidas",
	 * medidaRepositorio.findAll()); return "/vistas/incluido/producto"; }
	 * productoServicio.guardar(producto); return
	 * "redirect:/vistas/incluido/producto/";
	 * 
	 * }
	 */

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int codigo, Model model) {
		productoServicio.eliminar(codigo);
		return "redirect:/vistas/incluido/producto/";

	}

}
