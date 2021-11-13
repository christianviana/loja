package br.com.queroquero.loja.api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.service.VendedorService;

@Path("vendedores")
public class VendedorResource {

	@Inject
    VendedorService vendedorService;
	
	
    @GET
    @Path("/{matricula}")
	public Response buscarPorMatricula(@PathParam("matricula") String matricula) {
		Vendedor vendEncontrado = vendedorService.buscarPorMatricula(matricula);
		if (vendEncontrado == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.status(Status.CREATED).entity(vendEncontrado).build();
		}
    }
    
    @POST
    public Response criarVendedor(Vendedor vendedor) {
		vendedor = vendedorService.criarVendedor(vendedor);
		if (vendedor != null) {
			return Response.status(Status.CREATED).entity(vendedor).build();
		} else {
			return Response.serverError().build();
		}

    }

	@DELETE
	public Response removerVendedor(Vendedor vendedor) {
		if (vendedorService.removerVendedor(vendedor)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	public Response alterarVendedor(Vendedor vendedor) {
		if (vendedorService.alterarVendedor(vendedor)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

    
    
    
}