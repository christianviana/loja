package br.com.queroquero.loja.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dao.ProdutoDAO;
import br.com.queroquero.loja.dto.ProdutoPorQtdDTO;

@ApplicationScoped
public class ProdutoService {
	
    private static final Logger LOG = Logger.getLogger(ProdutoService.class);
    
	@Inject
    ProdutoDAO produtoDAO;
    
	public Produto criarProduto(Produto produto) {
		try {
			return produtoDAO.criarProduto(produto);
		} catch (Exception e) {
            LOG.error("Erro ao criar produto: " + produto, e);
			return null;
		}

	}

	public boolean removerProduto(Produto produto) {
		produto = produtoDAO.buscarProduto(produto);
		if (produto != null) {
			produtoDAO.removerProduto(produto);
			return true;
		}
		return false;
	}

	public boolean alterarProduto(Produto produto) {
		Produto produtoBuscado = produtoDAO.buscarProduto(produto);
		if (produtoBuscado != null) {
			produtoBuscado.setNome(produto.getNome());
			produtoBuscado.setPreco(produto.getPreco());
			produtoDAO.alterarProduto(produtoBuscado);
			return true;
		}
		return false;
	}
	
    /**
     * Buscar produto por código
     *
     * @param codigo Código do produto
     * @return O produto. Retorna <code>null</code> se o produto não for encontrado.
     */
    public Produto buscarPorCodigo(String codigo) {
        return produtoDAO.buscarPorCodigo(codigo);
    }
    
    /**
     * Busca os produtos mais vendidos.
     * 
     * @return os produtos mais vendidos e suas respectivas quantidades vendidas, ordenados em ordem descrescente das
     *         quantidades vendidas.
     */
    public List<ProdutoPorQtdDTO> buscarProdutosMaisVendidos() {
        return produtoDAO.buscarProdutosMaisVendidos();
    }
    
}
