package br.com.queroquero.loja.bo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Classe que representa um item na venda.
 * 
 * As informações do produto são replicadas no item, e não referenciadas, pois uma alteração de cadastro do produto não
 * pode influenciar nas vendas já realizadas. Ex: troca de descrição, troca de preço, etc.
 */
@Entity
public class Item {


	@Id
	@SequenceGenerator(name = "itemSeq", sequenceName = "item_id_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "itemSeq")
	@JsonIgnore
	private Long id;
    
    private String codigoProduto;
	private String descricao;
	private int quantidade;
	private BigDecimal valorUnitario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    /**
     * Aqui é feito o relacionamento com o produto no banco de dados, para facilitar pesquisas que relacionam vendas com
     * produtos
     */
    private Produto produto;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "venda_numvenda", nullable = false)
    private Venda venda;

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

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }
    
    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public Venda getVenda() {
        return venda;
    }
    
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    public String getCodigoProduto() {
        return codigoProduto;
    }
    
    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
	@Override
    public int hashCode() {
        return Objects.hash(codigoProduto);
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
        return Objects.equals(codigoProduto, other.codigoProduto);
    }
    
    @Override
    public String toString() {
        return "Item [id=" + id + ", codigoProduto=" + codigoProduto + ", descricao=" + descricao + ", quantidade="
                + quantidade + ", valorUnitario=" + valorUnitario + ", produto=" + produto + ", venda=" + venda + "]";
    }


}
