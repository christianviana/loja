package br.com.queroquero.loja.bo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa um produto que pode ser vendido. Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 * 
 */
@Entity
@NamedQuery(name = Produto.NOME_HQL_PRODUTOS_MAIS_VENDIDOS, query = Produto.HQL_PRODUTOS_MAIS_VENDIDOS)

public class Produto {
    
    public Produto() {
    }
    
    public Produto(String nome, BigDecimal preco) {
        this.nome = nome;
        this.preco = preco;
    }
    
    // @formatter:off
    
    public static final String NOME_HQL_PRODUTOS_MAIS_VENDIDOS = "buscarProdutosMaisVendidos";
    public static final String HQL_PRODUTOS_MAIS_VENDIDOS = 
            "select new br.com.queroquero.loja.dto.ProdutoPorQtdDTO(produto, sum(item.quantidade)) "
             + "from Item item join item.produto produto "
             + "group by produto.id order by sum(item.quantidade) desc";
    
    // @formatter:on
    
    @Id
    @SequenceGenerator(name = "produtoSeq", sequenceName = "produto_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "produtoSeq")
    private Long codigo;
    private String nome;
    private BigDecimal preco;

    
    public Long getCodigo() {
        return codigo;
    }
    
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    
 	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + "]";
	}

    
}