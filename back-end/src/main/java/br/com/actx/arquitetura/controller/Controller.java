package br.com.actx.arquitetura.controller;

import java.io.Serializable;
import java.util.List;

import br.com.actx.arquitetura.domain.entity.AbstractEntity;
import br.com.actx.arquitetura.service.AbstractService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public abstract class Controller<ID extends Serializable, T extends AbstractEntity<ID>, S extends AbstractService<ID, T, ?>> {

	private S service;

	protected Controller() {
		this(null);
	}

	protected Controller(S service) {
		this.service = service;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		List<T> entities = service.listar();
		return Response.ok(entities).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorId(@PathParam("id") ID id) {
		T entity = service.buscarPorId(id);
		if (entity != null) {
			return Response.ok(entity).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(T entity) {
		service.inserir(entity);
		return Response.status(Response.Status.CREATED).entity(entity).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") ID id) {
		T entity = service.buscarPorId(id);
		if (entity != null) {
			service.excluir(entity);
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	protected S getService() {
		return service;
	}

	protected void setService(S service) {
		this.service = service;
	}

}
