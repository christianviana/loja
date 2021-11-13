package br.com.queroquero.loja.bo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
 * Classe que representa um item na venda
 */

@Entity
public class Item {


	@Id
	@SequenceGenerator(name = "itemSeq", sequenceName = "item_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "itemSeq")
	private Long id;
	@OneToOne
    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;

	public Item() {

	}

    public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
    	this.produto = produto;
    	this.valorUnitario = valorUnitario;
    	this.quantidade = quantidade;
    }

    public Produto getProduto() {
    	return this.produto;
    }

    public BigDecimal getValorUnitario() {
    	return this.valorUnitario;
    }

    public int getQuantidade() {
    	return this.quantidade;
    }
    

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		try {
	    	return new BigDecimal(quantidade).multiply(valorUnitario);			
		} catch (Exception e) { // para o caso de valorUnitario == null
			return new BigDecimal("0");
		}

    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		Item other = (Item) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
}
