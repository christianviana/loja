package br.com.queroquero.loja.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa um vendedor da loja.
 * 
 */
@Entity
@NamedQuery(name = Vendedor.NOME_HQL_VENDEDORES_MAIS_VENDAS, query = Vendedor.HQL_VENDEDORES_MAIS_VENDAS)
@NamedQuery(name = Vendedor.NOME_HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR, query = Vendedor.HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR)
public class Vendedor {

    // @formatter:off
    public static final String NOME_HQL_VENDEDORES_MAIS_VENDAS = "buscarVendedoresMaisVendas";
    public static final String HQL_VENDEDORES_MAIS_VENDAS = 
            "select new br.com.queroquero.loja.dto.VendedorPorNumVendasDTO(vendedor, count(venda.id)) "
            + "from Venda venda join venda.vendedor vendedor "
            + "group by vendedor.id order by count(venda.id) desc";
    
    public static final String NOME_HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR = "buscarVendedoresMaioresVendasValor";
    public static final String HQL_VENDEDORES_COM_MAIORES_VENDAS_POR_VALOR = 
            "select new br.com.queroquero.loja.dto.VendedorPorValorVendasDTO(vendedor, sum(venda.valorTotal)) "
            + "from Venda venda join venda.vendedor vendedor "
            + "group by vendedor.id order by sum(venda.valorTotal) desc";
    
    // @formatter:on
    
	@Id
    @SequenceGenerator(name = "vendedorSeq", sequenceName = "vendedor_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "vendedorSeq")
    private Long matricula;
    private String nome;
    


    public Long getMatricula() {
    	return this.matricula;
    }

    
    public String getNome() {
    	return this.nome;
    }


    public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
        return "Vendedor [matricula=" + matricula + ", nome=" + nome + "]";
	}


  
}