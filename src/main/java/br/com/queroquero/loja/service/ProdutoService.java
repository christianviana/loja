package br.com.queroquero.loja.service;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dao.ProdutoDAO;
import br.com.queroquero.loja.dto.ProdutoPorQtdDTO;
import io.quarkus.logging.Log;

@Dependent
public class ProdutoService {
	
	@Inject
    ProdutoDAO produtoDAO;

	public Produto criarProduto(Produto produto) {
		try {
			return produtoDAO.criarProduto(produto);
		} catch (Exception e) {
            Log.error("Erro ao criar produto: " + produto, e);
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
     * Busca os produtos mais vendidos.
     * 
     * @return os produtos mais vendidos e suas respectivas quantidades vendidas, ordenados em ordem descrescente das
     *         quantidades vendidas.
     */
    public List<ProdutoPorQtdDTO> buscarProdutosMaisVendidos() {
        return produtoDAO.buscarProdutosMaisVendidos();
    }
    
}
