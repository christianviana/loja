package br.com.queroquero.loja.dto;

import br.com.queroquero.loja.bo.Produto;

public class ProdutoPorQtdDTO {
    
    private Produto produto;
    private int qtdVendida;
    
    public Produto getProduto() {
        return produto;
    }
    
    public int getQtdVendida() {
        return qtdVendida;
    }
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
}
