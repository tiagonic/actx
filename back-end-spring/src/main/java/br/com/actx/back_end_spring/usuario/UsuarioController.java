package br.com.actx.back_end_spring.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/pesquisar")
    public String pesquisar(@RequestParam(required = false) String login, Model model) {
        List<Usuario> usuarios;
        if (login != null && !login.isEmpty()) {
            usuarios = usuarioService.buscarPorLogin(login);
        } else {
            usuarios = usuarioService.listarTodos();
        }
        model.addAttribute("usuarios", usuarios);
        return "pesquisar";
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "listar";
    }

    @GetMapping("/incluir")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "incluir";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Usuario usuario, Model model) {
        usuarioService.salvar(usuario);
        return "redirect:/usuarios/pesquisar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "incluir";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return "redirect:/usuarios/pesquisar";
    }
}
