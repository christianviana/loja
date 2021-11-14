package br.com.queroquero.loja.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.service.VendaService;

@Path("/vendas")
public class VendaResource {

	@Inject
	VendaService vendaService;

	@POST
	public Response criarVenda(Venda venda) {
		venda = vendaService.criarVenda(venda);
		if (venda != null) {
			return Response.status(Status.CREATED).entity(venda).build();
		} else {
			return Response.status(Status.CONFLICT).entity(new Erro(ErroEnum.ERRO_CRIAR_VENDA)).build();
		}

	}

}