package br.com.actx.back_end_spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/actx")
	public String actx(Model model) {
		return "home";
	}

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}

	@GetMapping("/about")
	public String about(Model model) {
		return "about";
	}

	@GetMapping("/teste")
	public String teste(Model model) {
		model.addAttribute("title", "Testar layout!");
		model.addAttribute("pageContent", "teste");
		return "usuario/teste";
	}
}
