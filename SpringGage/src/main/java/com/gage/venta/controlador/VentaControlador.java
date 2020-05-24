package com.gage.venta.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gage.formato.sunat.Formato;
import com.gage.incluido.entidad.EstadoJPA;
import com.gage.incluido.repositorio.EstadoDAO;
import com.gage.incluido.servicio.ClienteServicio;
import com.gage.venta.entidad.ComprobanteJPA;
import com.gage.venta.entidad.DetalleVentaJPA;
import com.gage.venta.entidad.DetalleVentaJPAPK;
import com.gage.venta.entidad.NotaCreditoJPA;
import com.gage.venta.entidad.NotaDebitoJPA;
import com.gage.venta.entidad.VentaJPA;
import com.gage.venta.repositorio.ComprobanteDAO;
import com.gage.venta.repositorio.DetalleVentaDAO;
import com.gage.venta.repositorio.NotaCreditoDAO;
import com.gage.venta.repositorio.NotaDebitoDAO;
import com.gage.venta.repositorio.VentaDAO;
import com.gage.venta.servicio.DetalleVentaServicio;
import com.gage.venta.servicio.VentaServicio;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vistas/venta")
public class VentaControlador {
	
	Formato formato=null;

	@Autowired
	private VentaServicio ventaServicio;

	@Autowired
	private ClienteServicio clienteServicio;

	@Autowired
	private ComprobanteDAO comprobanteRepositorio;
	
	@Autowired
	private NotaCreditoDAO creditoRepositorio;
	
	@Autowired
	private NotaDebitoDAO debitoRepositorio;

	@Autowired
	private DetalleVentaDAO detalleRepositorio;

	@Autowired
	private DetalleVentaServicio detalleVentaServicio;
	
	@Autowired
	private EstadoDAO estadoRepositorio;

	@GetMapping("/")
	public List<VentaJPA> listar() {
		return ventaServicio.listar();
	}

	@GetMapping("/editar/{numero}")
	public VentaJPA editar(@PathVariable int numero) {
		return ventaServicio.obtener(numero);
	}

	@GetMapping("/obtener/{comprobante}")
	public int obtenerComprobante(@PathVariable int comprobante) {
		return listar().stream().filter(v -> v.getComprobante().getCodComprobante() == comprobante)
				.mapToInt(v -> v.getNumComprobante()).max().orElse(0) + 1;
	}

	@PostMapping("/guardar")
	public VentaJPA grabar(@RequestBody VentaJPA venta) {
		formato=new Formato();
		int numeroFormateado=formato.obtenerTalonario(venta.getComprobante().getCodComprobante(), venta.getNumComprobante());
		venta.setNumComprobante(numeroFormateado);
		return ventaServicio.guardar(venta);
	}

	@PutMapping("/anular/{numero}")
	public VentaJPA anular(@PathVariable("numero") int numero,@RequestBody VentaJPA venta) {
		EstadoJPA objEstado=new EstadoJPA();
		objEstado.setCodEstado(3);
		venta.setEstado(objEstado);
		return ventaServicio.guardar(venta);
		
	}

	@GetMapping("/detalle/{numero}")
	public List<DetalleVentaJPA> listar(@PathVariable("numero") int numero) {
		return detalleRepositorio.findByFactura(numero);
	}

	// se utiliza el repositorio ya que la clase generica acepta como id un entero,
	// y esta entidad tiene clave compuesta
	@GetMapping("/detalle/editar/{codigo}/{numero}")
	public DetalleVentaJPA editar(@PathVariable("codigo") int codProducto,
			@PathVariable("numero") int numComprobante) {
		DetalleVentaJPAPK dvpk = new DetalleVentaJPAPK();
		DetalleVentaJPA dv = new DetalleVentaJPA();
		dvpk.setCodProducto(codProducto);
		dvpk.setNumComprobante(numComprobante);
		dv.setId(dvpk);

		return detalleRepositorio.findById(dv.getId());
	}

	@PostMapping("/detalle/guardar")
	public List<DetalleVentaJPA> guardar(@RequestBody List<DetalleVentaJPA> detalles) {
		return (List<DetalleVentaJPA>) detalleVentaServicio.guardarLista(detalles);
	}

	@DeleteMapping("/detalle/eliminar/{codigo}/{numero}")
	public String eliminar(@PathVariable("codigo") int codProducto, @PathVariable("numero") int numComprobante) {

		DetalleVentaJPAPK dvpk = new DetalleVentaJPAPK();
		DetalleVentaJPA dv = new DetalleVentaJPA();
		DetalleVentaJPA dvE = new DetalleVentaJPA();
		dvpk.setCodProducto(codProducto);
		dvpk.setNumComprobante(numComprobante);
		dv.setId(dvpk);

		dvE = detalleRepositorio.findById(dv.getId());
		detalleVentaServicio.eliminar(dvE);
		return "detalle elimado" + codProducto + "--" + numComprobante;
	}

	@GetMapping("/comprobante")
	public List<ComprobanteJPA> listarC() {
		return (List<ComprobanteJPA>) comprobanteRepositorio.findAll();
	}
	
	@GetMapping("/credito")
	public List<NotaCreditoJPA> listarCredito() {
		return (List<NotaCreditoJPA>) creditoRepositorio.findAll();
	}
	
	@GetMapping("/debito")
	public List<NotaDebitoJPA> listarDebito() {
		return (List<NotaDebitoJPA>) debitoRepositorio.findAll();
	}
	
	@GetMapping("/estado")
	public List<EstadoJPA> listarE() {
		return (List<EstadoJPA>) estadoRepositorio.findAll();
	}

	/*
	 * @GetMapping("/") public String listarVenta(Model model) {
	 * model.addAttribute("titulo", "Listado de ventas");
	 * model.addAttribute("lista", ventaServicio.listar()); return
	 * "/vistas/venta/listaVenta"; }
	 */

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
