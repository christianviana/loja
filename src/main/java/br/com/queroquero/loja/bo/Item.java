package br.com.queroquero.loja.bo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Classe que representa um item na venda
 */

@Entity
public class Item {


	@Id
	@SequenceGenerator(name = "itemSeq", sequenceName = "item_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "itemSeq")
	@JsonIgnore
	private Long id;

	/**
	 * as informações do produto são replicadas no item, e não referenciadas, pois
	 * uma alteração de cadastro do produto não pode influenciar nas vendas já
	 * realizadas. Ex: troca de descrição, troca de preço, etc.
	 */
	private String codigoProduto;
	private String descricao;
	private int quantidade;
	private BigDecimal valorUnitario;


    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
	@JsonIgnore
    public BigDecimal getValorTotal() {
		try {
	    	return new BigDecimal(quantidade).multiply(valorUnitario);			
		} catch (Exception e) { // para o caso de valorUnitario == null
			return new BigDecimal("0");
		}

    }

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", codigoProduto=" + codigoProduto + ", descricao=" + descricao + ", valorUnitario="
				+ valorUnitario + ", quantidade=" + quantidade + "]";
	}

}
