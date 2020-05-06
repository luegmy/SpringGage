package com.gage.incluido.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/vistas/rest/producto")
public class ProductoRestControlador {

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

	
	@GetMapping("/crear")
	public String crear(Model model) {
		ProductoJPA producto = new ProductoJPA();
		model.addAttribute("titulo", "Nuevo producto");
		model.addAttribute("producto", producto);
		model.addAttribute("tipos", tipoRepositorio.findAll());
		model.addAttribute("medidas", medidaRepositorio.findAll());
		return "/vistas/incluido/producto";
	}


}
