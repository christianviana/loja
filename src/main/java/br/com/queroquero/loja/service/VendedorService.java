package br.com.queroquero.loja.service;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendedorDAO;
import br.com.queroquero.loja.dto.VendedorPorNumVendasDTO;
import br.com.queroquero.loja.dto.VendedorPorValorVendasDTO;

@SessionScoped
public class VendedorService {

    private static final Logger LOG = Logger.getLogger(VendedorService.class);
    
	@Inject
    VendedorDAO vendedorDAO;
	
    /**
	 * Buscar vendedor pela matrícula
	 *
	 * @param matricula Matrícula do vendedor
	 * @return O vendedor. Retorna <code>null</code> se o vendedor não for
	 *         encontrado.
	 */
    public Vendedor buscarPorMatricula(Long matricula) {
        return vendedorDAO.buscarPorMatricula(matricula);    	
    }
    

	public Vendedor criarVendedor(Vendedor vendedor) {
        
        // nececessário para evitar problemas com Hibernate se a matrícula vier informado no JSON da chamada de criação
        
        vendedor.setMatricula(null);
        try {
			return vendedorDAO.criarVendedor(vendedor);
		} catch (Exception e) {
            LOG.error("Erro ao criar vendedor: " + vendedor, e);
			return null;
		}

	}
    
	public boolean removerVendedor(Vendedor vendedor) {
		vendedor = vendedorDAO.buscarPorMatricula(vendedor.getMatricula());
		if (vendedor != null) {
			vendedorDAO.removerVendedor(vendedor);
			return true;
		}
		return false;
	}

	public boolean alterarVendedor(Vendedor vendedor) {
		Vendedor vendedorBuscado = vendedorDAO.buscarPorMatricula(vendedor.getMatricula());
		if (vendedorBuscado != null) {
			vendedorBuscado.setMatricula(vendedor.getMatricula());
			vendedorBuscado.setNome(vendedor.getNome());
			vendedorDAO.alterarVendedor(vendedorBuscado);
			return true;
		}
		return false;

	}

    /**
     * Busca os vendedores que mais venderam por número de vendas.
     * 
     * @return os vendedores que mais venderam por número de vendas, e as respecticas quantidades vendidas, ordenados em
     *         ordem descrescente de quantidade de vendas
     */
    public List<VendedorPorNumVendasDTO> buscarMaioresVendedoresPorNumVendas() {
        return vendedorDAO.buscarMaioresVendedoresPorNumVendas();
    }
    
    /**
     * Busca os vendedores que mais venderam por valor vendido.
     * 
     * @return os vendedores que mais venderam por valor vendido e o respectivo valor vendido, ordenados em ordem
     *         descrescente do valor vendido
     */
    public List<VendedorPorValorVendasDTO> buscarMaioresVendedoresPorValorVendas() {
        return vendedorDAO.buscarMaioresVendedoresPorValorVendas();
    }
	
	
}
