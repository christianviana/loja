package br.com.queroquero.loja.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa uma venda realizada. A coleção de itens da venda permite itens repetidos.
 */
@Entity
public class Venda {
    
	@Id
	@SequenceGenerator(name = "vendaSeq", sequenceName = "venda_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "vendaSeq")
	@JsonIgnore
	private Long numVenda;	
	/**
	 * o valor total é persistindo e não calculado (transiente) porque pode ser
	 * diferente da soma da multiplicação dos produtos pelos preços, caso tenha
	 * incidência de impostos, etc
	 */
	private BigDecimal valorTotal;	

	@ManyToOne
    @JoinColumn(nullable = false)
	private Vendedor vendedor;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    private List<Item> itens = Collections.synchronizedList(new ArrayList<>());

    public Venda() {
        
    }
    
    public Venda(Vendedor vendedor, BigDecimal valorTotal) {
        this.vendedor = vendedor;
        this.valorTotal = valorTotal;
    }

	public Long getNumVenda() {
		return numVenda;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

    public List<Item> getItens() {
		return itens;
	}

	public void setNumVenda(Long numVenda) {
		this.numVenda = numVenda;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

    public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numVenda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(numVenda, other.numVenda);
	}

	@Override
	public String toString() {
		return "Venda [numVenda=" + numVenda + ", valorTotal=" + valorTotal + ", vendedor=" + vendedor + ", itens="
				+ itens + "]";
	}
	
}
