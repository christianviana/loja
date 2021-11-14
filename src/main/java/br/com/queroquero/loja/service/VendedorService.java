package br.com.queroquero.loja.service;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendedorDAO;

@Dependent
public class VendedorService {

	@Inject
    VendedorDAO vendedorDAO;
	
	@Inject
	Logger log;
	
    /**
	 * Buscar vendedor pela matrícula
	 *
	 * @param matricula Matrícula do vendedor
	 * @return O vendedor. Retorna <code>null</code> se o vendedor não for
	 *         encontrado.
	 */
    public Vendedor buscarPorMatricula(String matricula) {
        return vendedorDAO.buscarPorMatricula(matricula);    	
    }
    

	public Vendedor criarVendedor(Vendedor vendedor) {
		try {
			return vendedorDAO.criarVendedor(vendedor);
		} catch (Exception e) {
			log.error("Erro ao criar vendedor: " + vendedor);
			log.error(e);
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

	
	
}
