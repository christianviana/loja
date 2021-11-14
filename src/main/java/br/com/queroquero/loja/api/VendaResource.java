package br.com.queroquero.loja.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.api.erros.Erro;
import br.com.queroquero.loja.api.erros.ErroEnum;
import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.service.VendaService;
import br.com.queroquero.loja.service.excecoes.VendedorInexistenteException;

@Path("/vendas")
public class VendaResource {

    private static final Logger LOG = Logger.getLogger(VendaResource.class);
    
	@Inject
	VendaService vendaService;

	@POST
	public Response criarVenda(Venda venda) {
        
        try {
            venda = vendaService.criarVenda(venda);
            return Response.status(Status.CREATED).entity(venda).build();
        } catch (VendedorInexistenteException e) {
            Erro erro = new Erro(ErroEnum.ERRO_VENDEDOR_INEXISTENTE_AO_CRIAR_VENDA);
            LOG.error(erro.getMsgUsuario());
            return Response.status(Status.CONFLICT).entity(erro).build();
        } catch (Exception e) {
            Erro erro = new Erro(ErroEnum.ERRO_CRIAR_VENDA);
            LOG.error(erro.getMsgUsuario(), e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(erro).build();
        }


	}

}