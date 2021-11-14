package br.com.queroquero.loja.dao;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import br.com.queroquero.loja.bo.Venda;

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

}
