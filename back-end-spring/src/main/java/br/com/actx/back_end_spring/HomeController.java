package br.com.actx.back_end_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/teste")
	public String teste(Model model) {
		model.addAttribute("title", "Testar layout!");
		model.addAttribute("pageContent", "teste");
		return "usuario/teste";
	}
}
