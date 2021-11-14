package br.com.queroquero.loja.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendaDAO;
import br.com.queroquero.loja.service.excecoes.VendedorInexistenteException;
import io.quarkus.logging.Log;

@Dependent
public class VendaService {

	@Inject
	VendaDAO vendaDAO;
	
	@Inject
	VendedorService vendedorService;
	
	/**
     * Cria uma venda e seus itens, se o vendedor informado na venda existir. Os dados do vendedor não são atualizados.
     * Se o vendedor não existir, a venda não é criada e lança exceção.
     * 
     * @param venda A venda a ser criada.
     * @return A venda criada. Retorna <code>null</code> se ocorrer algum erro e não for possível criar a venda.
     * @throws VendedorInexistenteException se o vendedor informado na venda não existir.
     */
	@Transactional
    public Venda criarVenda(Venda venda) throws VendedorInexistenteException {

		Vendedor vendedor = vendedorService.buscarPorMatricula(venda.getVendedor().getMatricula());
		if (vendedor == null) {
            throw new VendedorInexistenteException();
		} else {
			venda.setVendedor(vendedor);
		}

		try {
			return vendaDAO.criarVenda(venda);
		} catch (Exception e) {
            Log.error("Erro ao criar venda: " + venda, e);
			return null;
		}

	}


}
