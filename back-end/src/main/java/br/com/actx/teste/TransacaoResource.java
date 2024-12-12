package br.com.actx.teste;

import java.util.List;

import br.com.actx.cadastro.usuario.UsuarioEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.transaction.Status;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transacoes")
public class TransacaoResource {

	@EJB
	private TransacaoServiceAutomatica transacaoServiceAutomatica;

	@EJB
	private TransacaoServiceProgramatica transacaoServiceProgramatica;

	private TransactionManager transactionManager;
	private UserTransaction userTransaction;

	@PostConstruct
	public void onBeanTransaction() {
		this.transactionManager = this.transacaoServiceProgramatica.getTransactionManager();
		this.userTransaction = this.transacaoServiceProgramatica.getUserTransaction();
	}

	@POST
	@Path("/requerida")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoRequerida(UsuarioEntity entity) {
		transacaoServiceAutomatica.transacaoRequerida(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	@POST
	@Path("/requerNova")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoRequerNova(UsuarioEntity entity) {
		transacaoServiceAutomatica.transacaoRequerNova(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	@POST
	@Path("/obrigatoria")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoObrigatoria(UsuarioEntity entity) {
		try {
			int status = transactionManager.getStatus();
			if (status == Status.STATUS_NO_TRANSACTION) {
				userTransaction.begin();
				status = userTransaction.getStatus();
				transacaoServiceAutomatica.transacaoObrigatoria(entity);
				userTransaction.commit();
			} else {
				transacaoServiceAutomatica.transacaoObrigatoria(entity);
			}
			return Response.ok(entity).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Erro ao executar transação obrigatória: " + e.getMessage())
					.build();
		}
	}

	@GET
	@Path("/suporta/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoSuporta(@PathParam("id") Long id) {
		UsuarioEntity entity = transacaoServiceAutomatica.transacaoSuporta(id);
		return Response.ok(entity).build();
	}

	@GET
	@Path("/naoSuporta")
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoNaoSuporta() {
		List<UsuarioEntity> entities = transacaoServiceAutomatica.transacaoNaoSuporta();
		return Response.ok(entities).build();
	}

	@POST
	@Path("/nunca")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoNunca(UsuarioEntity entity) {
		transacaoServiceAutomatica.transacaoNunca(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	@POST
	@Path("/programaticaUserTransaction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoProgramaticaUserTransaction(UsuarioEntity entity) {
		transacaoServiceProgramatica.transacaoProgramaticaUserTransaction(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	@POST
	@Path("/programaticaTransactionManager")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transacaoProgramaticaTransactionManager(UsuarioEntity entity) {
		transacaoServiceProgramatica.transacaoProgramaticaTransactionManager(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}
}
