package br.com.queroquero.loja.bo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que representa uma venda realizada.
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
	private Vendedor vendedor;
	
	// TODO organizar pra não ter o mesmo produto duas vezes na venda? posso
	// deixar..
	// se for set, o que acontece se vier duplicado no json? ele ignora o 2o,
	// substitui o 1o, dá erro...
	// TODO confirmar que coleção usar (concorrência, repetição, performance, ordem)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> itens = new HashSet<>();

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

    public Set<Item> getItens() {
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

    public void setItens(Set<Item> itens) {
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
