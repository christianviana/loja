package br.com.queroquero.loja.bo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa um produto que pode ser vendido
 * 
 * Dois produtos são considerados iguais quando ambos possuem o mesmo código.
 */
@Entity
public class Produto {

	@Id
    @SequenceGenerator(name = "produtoSeq", sequenceName = "produto_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "produtoSeq")
	private Long codigo;
    private String nome;
    private BigDecimal preco;

    public Produto() {
    	
    }
    
	public Produto(Long codigo, String nome, BigDecimal preco) {
    	this.codigo = codigo;
		this.nome = nome;
    	this.preco = preco;
    }

    
    public Long getCodigo() {
    	return this.codigo;
    }

    
	public String getNome() {
    	return this.nome;
    }

    
	public BigDecimal getPreco() {
		return preco;
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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