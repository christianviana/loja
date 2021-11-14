package br.com.queroquero.loja.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendaDAO;

@Dependent
public class VendaService {

	@Inject
	VendaDAO vendaDAO;
	
	@Inject
	VendedorService vendedorService;

	@Inject
	Logger log;
	
	/**
	 * Cria uma venda e seus itens, se o vendedor informado na venda existir. Os
	 * dados do vendedor não são atualizados. Se o vendedor não existir, a venda não
	 * é criada.
	 * 
	 * @param venda A venda a ser criada.
	 * @return A venda criada. Retorna <code>null</code> se não for possível criar a
	 *         venda.
	 */
	@Transactional
	public Venda criarVenda(Venda venda) {

		Vendedor vendedor = vendedorService.buscarPorMatricula(venda.getVendedor().getMatricula());
		if (vendedor == null) {
			return null;
		} else {
			venda.setVendedor(vendedor);
		}

		try {
			return vendaDAO.criarVenda(venda);
		} catch (Exception e) {
			log.error("Erro ao criar venda: " + venda);
			log.error(e);
			return null;
		}

	}


}
