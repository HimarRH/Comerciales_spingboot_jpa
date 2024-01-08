package unir.exa.ventas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import unir.exa.ventas.modelo.dao.ClienteDao;
import unir.exa.ventas.modelo.dao.ComercialDao;
import unir.exa.ventas.modelo.dao.PedidoDao;
import unir.exa.ventas.modelo.entity.Pedido;


@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoDao pedao;
	
	@Autowired
	private ComercialDao codao;
	
	@Autowired
	private ClienteDao cldao;
	
	@GetMapping(" ")
	public String verPedidos (Model model) {
		model.addAttribute("pedidos", pedao.findAll());
		return "pedido";
	}
	
	@GetMapping("/verDetalle/{id}")
	public String verDetallePedido(@PathVariable ("id") int idPedido, Model model) {
		model.addAttribute("pedido", pedao.findById(idPedido));
		return "verDetallePedido";
	}
	
	@GetMapping("/porCliente/{id}")
	public String pedidoCliente (@PathVariable("id") int idCliente, Model model) {
		model.addAttribute("pedidos", pedao.pedidoPorCliente(idCliente));
		return "pedidoCliente";
		
	}
	
	@GetMapping("/porComercial/{id}")
	public String pedidoComercial (@PathVariable("id") int idComercial, Model model) {
		model.addAttribute("pedidos", pedao.pedidoPorComercial(idComercial));
		return "pedidoComercial";
	}

	@GetMapping("/alta")
	public String irAlta(Model model) {
		model.addAttribute("pedido", new Pedido());
		model.addAttribute("comerciales", codao.findAll());
		model.addAttribute("clientes", cldao.findAll());
		return "altaPedido";
	}
	
	@PostMapping("/alta")
	public String darAlta(Pedido pedido, RedirectAttributes ratt) {
		pedido.setComercial(codao.findById(pedido.getComercial().getIdComercial()));
		pedido.setCliente(cldao.findById(pedido.getCliente().getIdCliente()));
		pedao.insertPedido(pedido);
		ratt.addFlashAttribute("mensaje", "Alta ok");
		
		return "redirect:/pedido";
	}
	
	@GetMapping("/modificar/{id}")
	public String irEditar(@PathVariable("id") int idPedido, Model model) {
		Pedido pedido = pedao.findById(idPedido);
		if (pedido != null) {
			model.addAttribute("pedido", pedido);
			model.addAttribute("comerciales", codao.findAll());
			model.addAttribute("clientes", cldao.findAll());
			return "editarPedido";
		}else {
			model.addAttribute("mensaje", "No se encuentra");
			return "forward:/pedido";
		}
	}
	
	@PostMapping("/modificar/{id}")
	public String editarPedido(Pedido pedido,@PathVariable("id") int idPedido, RedirectAttributes ratt) {
		pedido.setIdPedido(idPedido);
		pedido.setComercial(codao.findById(pedido.getComercial().getIdComercial()));
		pedido.setCliente(cldao.findById(pedido.getCliente().getIdCliente()));
		if (pedao.updatePedido(pedido) == 1) {
			ratt.addFlashAttribute("mensaje", "Pedido editado");
			return "redirect:/pedido";
		}else {
			ratt.addFlashAttribute("mensaje", "Pedido no editado");
			return "redirect:/pedido";
		}
	
	}
	
	
	@InitBinder
	public void initBinder (WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
