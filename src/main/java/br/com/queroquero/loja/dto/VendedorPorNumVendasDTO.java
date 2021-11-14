package br.com.queroquero.loja.dto;

import br.com.queroquero.loja.bo.Vendedor;

public class VendedorPorNumVendasDTO {
    
    private Vendedor vendedor;
    private long qtdVendida;
    
    public VendedorPorNumVendasDTO(Vendedor vendedor, long qtdVendida) {
        this.vendedor = vendedor;
        this.qtdVendida = qtdVendida;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public long getQtdVendida() {
        return qtdVendida;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void setQtdVendida(long qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
}
