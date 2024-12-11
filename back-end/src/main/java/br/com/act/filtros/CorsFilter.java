package br.com.act.filtros;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        responseContext.getHeaders().add("Access-Control-Expose-Headers", "Location");
        responseContext.getHeaders().add("Access-Control-Max-Age", "3600");
    }
}
