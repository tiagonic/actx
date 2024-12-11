package br.com.act.cadastro.usuario;

import br.com.act.arquitetura.controller.Controller;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
public class UsuarioResource extends Controller<Long, UsuarioEntity, UsuarioService> {

	@EJB
	private UsuarioService usuarioService;

	@Inject
	private CadastroUsuarioBean cadastroUsuarioBean;

	@PostConstruct
	private void onService() {
		super.setService(this.usuarioService);
	}

	@GET
	@Path("/bean")
	@Produces(MediaType.TEXT_PLAIN)
	public String init() {
		cadastroUsuarioBean.listar();
		return "Bean inicializado!";
	}

}
