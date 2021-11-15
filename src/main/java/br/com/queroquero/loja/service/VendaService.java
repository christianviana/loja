package br.com.queroquero.loja.service;

import java.util.Iterator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Item;
import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendaDAO;
import br.com.queroquero.loja.service.excecoes.ProdutoInexistenteException;
import br.com.queroquero.loja.service.excecoes.VendedorInexistenteException;

@ApplicationScoped
public class VendaService {

    private static final Logger LOG = Logger.getLogger(VendaService.class);
    
	@Inject
	VendaDAO vendaDAO;
	
	@Inject
	VendedorService vendedorService;
	
    @Inject
    ProdutoService produtoService;
    
	/**
     * Cria uma venda e seus itens, se o vendedor informado na venda existir. Os dados do vendedor não são atualizados.
     * Se o vendedor não existir, a venda não é criada e lança exceção.
     * 
     * @param venda A venda a ser criada.
     * @return A venda criada. Retorna <code>null</code> se ocorrer algum erro e não for possível criar a venda.
     * 
     * @throws VendedorInexistenteException se o vendedor informado na venda não existir.
     * @throws ProdutoInexistenteException se algum produto informado na venda não existir.
     */
	@Transactional
    public Venda criarVenda(Venda venda) throws VendedorInexistenteException, ProdutoInexistenteException {

		Vendedor vendedor = vendedorService.buscarPorMatricula(venda.getVendedor().getMatricula());
		if (vendedor == null) {
            throw new VendedorInexistenteException();
		} else {
			venda.setVendedor(vendedor);
		}
        
        for (Iterator<Item> iterator = venda.getItens().iterator(); iterator.hasNext();) {
            Item item = iterator.next();
            item.setVenda(venda);
            Produto produto = produtoService.buscarPorCodigo(item.getCodigoProduto());
            if (produto == null) {
                throw new ProdutoInexistenteException();
            } else {
                item.setProduto(produto);
            }
        }

		try {
			return vendaDAO.criarVenda(venda);
		} catch (Exception e) {
            LOG.error("Erro ao criar venda: " + venda, e);
			return null;
		}

	}


}
