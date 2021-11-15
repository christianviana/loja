package br.com.queroquero.loja.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.api.erros.Erro;
import br.com.queroquero.loja.api.erros.ErroEnum;
import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dto.VendedorPorNumVendasDTO;
import br.com.queroquero.loja.dto.VendedorPorValorVendasDTO;
import br.com.queroquero.loja.service.VendaService;
import br.com.queroquero.loja.service.VendedorService;

@Path("vendedores")
public class VendedorResource {

    private static final Logger LOG = Logger.getLogger(VendedorResource.class);
    
	@Inject
    VendedorService vendedorService;
	
    @Inject
    VendaService vendaService;
	
    @GET
    @Path("/{matricula}")
    public Response buscarPorMatricula(@PathParam("matricula") Long matricula) {
        Vendedor vendEncontrado = vendedorService.buscarPorMatricula(matricula);
		if (vendEncontrado == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(vendEncontrado).build();
		}
    }
    
    @POST
	public Response criarVendedor(Vendedor vendedor) {
		vendedor = vendedorService.criarVendedor(vendedor);
		if (vendedor != null) {
			return Response.status(Status.CREATED).entity(vendedor).build();
		} else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Erro(ErroEnum.ERRO_CRIAR_VENDEDOR)).build();
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
			return Response.ok(vendedor).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

    @GET
    @Path("/maiores-por-valor")
    public Response buscarMaioresVendedoresPorValorVendas() {
        try {
            List<VendedorPorValorVendasDTO> vendedores = vendedorService.buscarMaioresVendedoresPorValorVendas();
            return Response.ok(vendedores).build();
        } catch (Exception e) {
            Erro erro = new Erro(ErroEnum.ERRO_BUSCA_MAIORES_VENDEDORES_VALOR);
            LOG.error(erro.getMsgUsuario(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Erro(ErroEnum.ERRO_BUSCA_MAIORES_VENDEDORES_VALOR)).build();
        }
    }
    
    @GET
    @Path("/maiores-por-numero-vendas")
    public Response buscarMaioresVendedoresPorNumVendas() {
        try {
            List<VendedorPorNumVendasDTO> vendedores = vendedorService.buscarMaioresVendedoresPorNumVendas();
            return Response.ok(vendedores).build();
        } catch (Exception e) {
            Erro erro = new Erro(ErroEnum.ERRO_BUSCA_MAIORES_VENDEDORES_NUM_VENDAS);
            LOG.error(erro.getMsgUsuario(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(erro).build();
        }
    }
    
    
}