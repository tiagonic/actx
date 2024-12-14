package br.com.actx.cadastro.usuario;

import br.com.actx.arquitetura.controller.Controller;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
	public String bean() {
		return cadastroUsuarioBean.bean();
	}

	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir2(UsuarioDTO dto) {
		UsuarioEntity entity = new UsuarioEntity();
		entity.setEmail(dto.getEmail());
		entity.setLogin(dto.getLogin());
		entity.setSenha(dto.getSenha());
		this.usuarioService.inserir(entity);
		dto.setId(entity.getId());
		return Response.status(Response.Status.CREATED).entity(dto).build();
	}

}
