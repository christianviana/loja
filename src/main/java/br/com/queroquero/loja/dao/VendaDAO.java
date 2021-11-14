package br.com.queroquero.loja.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;

@Dependent
public class VendaDAO {

	@Inject
	EntityManager em;

	@Inject
	VendedorDAO vendedorDAO;

	@Transactional
	public Venda criarVenda(Venda venda) {
		em.persist(venda);
		return venda;
	}


	// list de produtos mais vendidos
	public List<Produto> maisVendidos() {
        return em.createNamedQuery(Venda.HQL_PRODUTOS_MAIS_VENDIDOS, Produto.class).getResultList();
	}

	// list de vendedores com maior número de vendas
	public List<Vendedor> maioresVendedoresPorNumVendas() {
        return em.createNamedQuery(Venda.HQL_VENDEDORES_MAIS_VENDAS, Vendedor.class).getResultList();
	}

	// list de vendedores por valor vendido
	public List<Vendedor> maioresVendedoresPorValorVendas() {
        return em.createNamedQuery(Venda.HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR, Vendedor.class)
                .getResultList();
	}

}
