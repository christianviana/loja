package br.com.queroquero.loja.api;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;

class VendaResourceTest {
    
    @Test
    void testCriarVendaNaoExistenteOK() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("MM");
        venda.setVendedor(vendedor);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(201);
    }
    
    @Test
    void testCriarVendaComVendedorNaoExistente() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("MM2");
        venda.setVendedor(vendedor);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(409);
    }
    
}
