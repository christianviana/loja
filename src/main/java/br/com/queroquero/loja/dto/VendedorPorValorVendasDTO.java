package br.com.queroquero.loja.dto;

import java.math.BigDecimal;

import br.com.queroquero.loja.bo.Vendedor;

public class VendedorPorValorVendasDTO {
    
    private Vendedor vendedor;
    private BigDecimal valorVendido;
    
    public VendedorPorValorVendasDTO(Vendedor vendedor, BigDecimal valorVendido) {
        this.vendedor = vendedor;
        this.valorVendido = valorVendido;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }
    
    public BigDecimal getValorVendido() {
        return valorVendido;
    }
    
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void setValorVendido(BigDecimal valorVendido) {
        this.valorVendido = valorVendido;
    }

}
