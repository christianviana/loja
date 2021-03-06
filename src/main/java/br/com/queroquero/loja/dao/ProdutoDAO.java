package br.com.queroquero.loja.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dto.ProdutoPorQtdDTO;

@Dependent
public class ProdutoDAO {

	@Inject
    EntityManager em;

	@Transactional 
	public Produto buscarProduto(Produto produto) {
		return em.find(Produto.class, produto.getCodigo());
	}

    @Transactional 
	public Produto criarProduto(Produto produto) {
		em.persist(produto);
		return produto;
    }

	@Transactional
	public void removerProduto(Produto produto) {
		if (!em.contains(produto)) {
			produto = em.merge(produto);
		}
		em.remove(produto);
	}

	@Transactional
	public void alterarProduto(Produto produto) {
		em.merge(produto);

	}
    
    /**
     * Buscar o produto pelo código
     *
     * @param codigo código do produto
     * @return O produto
     */
    public Produto buscarPorCodigo(Long codigo) {
        
        TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p WHERE p.codigo = :codigo", Produto.class);
        query.setParameter("codigo", codigo);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /**
     * Busca os produtos mais vendidos.
     * 
     * @return os produtos mais vendidos e suas respectivas quantidades vendidas, ordenados em ordem descrescente das
     *         quantidades vendidas.
     */
    public List<ProdutoPorQtdDTO> buscarProdutosMaisVendidos() {
        return em.createNamedQuery(Produto.NOME_HQL_PRODUTOS_MAIS_VENDIDOS, ProdutoPorQtdDTO.class).getResultList();
    }

}