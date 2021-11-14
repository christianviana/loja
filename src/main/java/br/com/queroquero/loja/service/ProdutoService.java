package br.com.queroquero.loja.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dao.ProdutoDAO;

@Dependent
public class ProdutoService {
	
	@Inject
    ProdutoDAO produtoDAO;

	@Inject
	Logger log;

	public Produto criarProduto(Produto produto) {
		try {
			return produtoDAO.criarProduto(produto);
		} catch (Exception e) {
			log.error("Erro ao criar produto: " + produto);
			log.error(e);
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
	
    
}
