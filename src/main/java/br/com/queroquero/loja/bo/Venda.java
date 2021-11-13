package br.com.queroquero.loja.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * Classe que representa uma venda realizada
 */
@Entity
public class Venda {
	
	@Id
	@SequenceGenerator(name = "vendaSeq", sequenceName = "venda_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "vendaSeq")
	private Long numVenda;	
	private BigDecimal valorTotal;	

	@OneToOne
	private Vendedor vendedor;
	
	// TODO organizar pra não ter o mesmo produto duas vezes na venda

	
	// TODO confirmar qual coleção usar (concorrência, repetição, performance, ordem
	
	// o preço deve ser armazenado na venda, pois se alterar o preço do 
	// produto, as vendas feitas não podem ter o valor alterado
	@OneToMany
	private List<Item> itens = new ArrayList<>();

	public Venda() {

	}

	public Venda(Long numVenda, Vendedor vendedor, List<Item> itens) {
		this.numVenda = numVenda;
		this.vendedor = vendedor;
		this.itens = itens;
	}
	
	public Long getNumVenda() {
		return numVenda;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public List<Item> getItens() {
		return itens;
	}
	
	// TODO valor total é calculado ou persistido? pode ser diferente da soma da multiplicação dos produtos pelos preços?
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
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


	
}
