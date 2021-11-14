package br.com.queroquero.loja.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.service.VendaService;
import br.com.queroquero.loja.service.VendedorInexistenteException;
import io.quarkus.logging.Log;

@Path("/vendas")
public class VendaResource {

	@Inject
	VendaService vendaService;

	@POST
	public Response criarVenda(Venda venda) {
        
        try {
            venda = vendaService.criarVenda(venda);
            return Response.status(Status.CREATED).entity(venda).build();
        } catch (VendedorInexistenteException e) {
            Erro erro = new Erro(ErroEnum.ERRO_VENDEDOR_INEXISTENTE_AO_CRIAR_VENDA);
            Log.error(erro.getMsgUsuario());
            return Response.status(Status.CONFLICT).entity(erro).build();
        } catch (Exception e) {
            Erro erro = new Erro(ErroEnum.ERRO_CRIAR_VENDA);
            Log.error(erro.getMsgUsuario(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }


	}

}