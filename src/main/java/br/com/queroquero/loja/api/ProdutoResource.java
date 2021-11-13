package br.com.queroquero.loja.api;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.service.ProdutoService;

@Path("produtos")
public class ProdutoResource {

		
	@Inject
    ProdutoService produtoService;
	
	@POST
	public Response criarProduto(Produto produto) {
		produto = produtoService.criarProduto(produto);
		if (produto != null) {
			return Response.status(Status.CREATED).entity(produto).build();
		} else {
			return Response.serverError().build();
		}
// TODO está dando erro se envio pra criar com id informado no payload
		// como faço pra ignorar o id no payload?
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

	
	
	
}