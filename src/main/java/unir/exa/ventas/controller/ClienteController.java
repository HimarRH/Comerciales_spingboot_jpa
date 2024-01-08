package unir.exa.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import unir.exa.ventas.modelo.dao.ClienteDao;
import unir.exa.ventas.modelo.entity.Cliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteDao cldao;
	
	@GetMapping(" ")
	public String verCliente (Model model) {
		model.addAttribute("clientes", cldao.findAll());
		return "cliente";
	}
	
	@GetMapping("/verDetalle/{id}")
	public String verDetalle(@PathVariable("id") int idCliente, Model model) {
		Cliente cliente = cldao.findById(idCliente);
		model.addAttribute("cliente", cliente);
		return "verDetalleCliente";
		
	}
	
	@GetMapping("/modificar/{id}")
	public String verModificar (@PathVariable("id") int idCliente, Model model) {
		Cliente cliente = cldao.findById(idCliente);
		if(cliente != null) {
			model.addAttribute("cliente", cliente);
			return "editarCliente";
		}else {
			return "forward:/cliente";
		}
	}
	
	@PostMapping("/modificar/{id}")
	public String clienteModificado (Cliente cliente, @PathVariable("id") int idCliente, RedirectAttributes ratt) {
		cliente.setIdCliente(idCliente);
		if (cldao.updateCliente(cliente) == 1) {
			ratt.addFlashAttribute("mensaje", "Cliente modificado");
			return "redirect:/cliente";
		} else {
			ratt.addFlashAttribute("mensaje", "Cliente no modificado");
			return "redirect:/cliente";
		}
		
	}
	
	@GetMapping("/alta")
	public String irAlta (Model model) {
		model.addAttribute("cliente", new Cliente());
		return "altaCliente";
	}
	
	@PostMapping("/alta")
	public String darAlta (Cliente cliente, RedirectAttributes ratt) {
		cldao.insertClient(cliente);
		ratt.addFlashAttribute("mensaje", "Cliente dado de alta");
		return "redirect:/cliente";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarCliente (@PathVariable("id") int idCliente, Model model) {
		cldao.removeCliente(idCliente);
		model.addAttribute("mensaje", "Cliente eliminado");
		return "forward:/cliente";
	}
		

}
