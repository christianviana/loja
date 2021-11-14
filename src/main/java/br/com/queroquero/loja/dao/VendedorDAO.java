package br.com.queroquero.loja.dao;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dto.VendedorPorNumVendasDTO;
import br.com.queroquero.loja.dto.VendedorPorValorVendasDTO;

@Dependent
public class VendedorDAO {

	@Inject
    EntityManager em;
	
	
    /**
     * Buscar vendedor pela matrícula
     *
     * @param matricula Matrícula do vendedor
     * @return O vendedor
     */
	public Vendedor buscarPorMatricula(String matricula) {

    	TypedQuery<Vendedor> query = em.createQuery(
                "SELECT v FROM Vendedor v WHERE v.matricula = :matricula", Vendedor.class);
        query.setParameter("matricula", matricula);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
    }

	@Transactional 
	public Vendedor criarVendedor(Vendedor vendedor) {
			em.persist(vendedor);
			return vendedor;
    }
    
	
	@Transactional
	public void removerVendedor(Vendedor vendedor) {
		if (!em.contains(vendedor)) {
			vendedor = em.merge(vendedor);
		}
		em.remove(vendedor);
	}

	@Transactional
	public void alterarVendedor(Vendedor vendedor) {
			em.merge(vendedor);

	}
	
    // list de vendedores com maior número de vendas
    public List<VendedorPorNumVendasDTO> buscarMaioresVendedoresPorNumVendas() {
        return em.createNamedQuery(Vendedor.NOME_HQL_VENDEDORES_MAIS_VENDAS, VendedorPorNumVendasDTO.class).getResultList();
    }
    
    // list de vendedores por valor vendido
    public List<VendedorPorValorVendasDTO> buscarMaioresVendedoresPorValorVendas() {
        return em.createNamedQuery(Vendedor.NOME_HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR, VendedorPorValorVendasDTO.class)
                .getResultList();
    }
	
}
