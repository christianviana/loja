package br.com.queroquero.loja.api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.api.erros.Erro;
import br.com.queroquero.loja.api.erros.ErroEnum;
import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dto.ProdutoPorQtdDTO;
import br.com.queroquero.loja.service.ProdutoService;

@Path("produtos")
public class ProdutoResource {
    
    private static final Logger LOG = Logger.getLogger(ProdutoResource.class);
		
	@Inject
    ProdutoService produtoService;
	
	@POST
	public Response criarProduto(Produto produto) {
		produto = produtoService.criarProduto(produto);
		if (produto != null) {
			return Response.status(Status.CREATED).entity(produto).build();
		} else {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new Erro(ErroEnum.ERRO_CRIAR_PRODUTO)).build();
		}
	}

	@DELETE
	public Response removerProduto(Produto produto) {
		if (produtoService.removerProduto(produto)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@PUT
	public Response alterarProduto(Produto produto) {
		if (produtoService.alterarProduto(produto)) {
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

    @GET
    @Path("/mais-vendidos")
    public Response buscarMaioresVendedoresPorNumVendas() {
        try {
            List<ProdutoPorQtdDTO> produtos = produtoService.buscarProdutosMaisVendidos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            Erro erro = new Erro(ErroEnum.ERRO_BUSCA_PRODUTOS_MAIS_VENDIDOS);
            LOG.error(erro.getMsgUsuario(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }
    }
	
	
}