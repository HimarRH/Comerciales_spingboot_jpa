package unir.exa.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import unir.exa.ventas.modelo.dao.ComercialDao;
import unir.exa.ventas.modelo.entity.Comercial;

@Controller
@RequestMapping("/comercial")
public class ComercialController {
	
	@Autowired
	private ComercialDao codao;
	
	@GetMapping("/verDetalle/{id}")
	public String verDetalle(@PathVariable("id") int idComercial, Model model ) {
		Comercial comercial = codao.findById(idComercial);
		if(comercial != null) {
			model.addAttribute("comercial", comercial);
			return "verDetalle";
		} else {
			return "forward:/";
		}
	}
	
	@GetMapping("/modificar/{id}")
	public String irModificar(@PathVariable("id") int idComercial, Model model) {
		Comercial comercial = codao.findById(idComercial);
		if( comercial != null) {
			model.addAttribute("comercial", comercial);
			return "editarComercial";
		} else {
			model.addAttribute("mensaje", "No existe el comercial que desea modificar");
			return "forward:/";
		}
	}
	
	@PostMapping("/modificar/{id}")
	public String modificarComercial(Comercial comercial,@PathVariable("id") int idComercial, RedirectAttributes ratt) {
		comercial.setIdComercial(idComercial);
		if(codao.updateComercial(comercial)== 1) {
			ratt.addFlashAttribute("mensaje", "Modificación Realizada");	
		} else {
			ratt.addFlashAttribute("mensaje", "Modificación no realizada");
		}
		return "redirect:/";
	}
	
	@GetMapping("/alta")
	public String irAlta(Model model) {
		model.addAttribute("comercial", new Comercial());
		return "altaComercial";
	}
	
	@PostMapping("/alta")
	public String darAlta(Comercial comercial, RedirectAttributes ratt) {
		codao.insertComercial(comercial);
		ratt.addFlashAttribute("mensaje", "Comercial dado de alta");
		return "redirect:/";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarComercial(@PathVariable("id") int idComercial, Model model) {
		codao.deleteComercial(idComercial);
		model.addAttribute("mensaje", "Comercial Eliminado");
		return "forward:/";
	}
}
