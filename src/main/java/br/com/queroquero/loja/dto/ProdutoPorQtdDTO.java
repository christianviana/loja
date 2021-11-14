package br.com.queroquero.loja.dto;

import br.com.queroquero.loja.bo.Produto;

public class ProdutoPorQtdDTO {
    
    private Produto produto;
    private long qtdVendida;
    
    public ProdutoPorQtdDTO(Produto produto, long qtdVendida) {
        this.produto = produto;
        this.qtdVendida = qtdVendida;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public long getQtdVendida() {
        return qtdVendida;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void setQtdVendida(long qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
}
