package unir.exa.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import unir.exa.ventas.modelo.dao.ComercialDao;

@Controller
public class HomeController {
	
	@Autowired
	private ComercialDao codao;
	
	@GetMapping("/")
	public String homeComercial (Model model) {
		model.addAttribute("comerciales", codao.findAll());
		return "home";
	}

}
