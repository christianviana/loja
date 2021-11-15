package br.com.quero.quero.loja.integracao;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.queroquero.loja.bo.Item;
import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class VendaIntegracaoTest {
    
    @Test
    void testCriarVendaNaoExistenteOK() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula(1L);
        venda.setVendedor(vendedor);
        
        List<Item> itens = new ArrayList<>();
        
        Item item = new Item();
        item.setCodigoProduto(1L);
        item.setDescricao("produto 1");
        item.setQuantidade(12);
        item.setValorUnitario(new BigDecimal("12.12"));
        itens.add(item);
        
        venda.setItens(itens);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(201);
    }
    
    @Test
    void testCriarVendaComVendedorNaoExistente() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula(10L);
        venda.setVendedor(vendedor);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(409);
    }
    
    @Test
    void testCriarVendaComProdutoNaoExistente() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula(1L);
        venda.setVendedor(vendedor);
        
        List<Item> itens = new ArrayList<>();
        
        Item item = new Item();
        item.setCodigoProduto(10L);
        item.setDescricao("produto 1");
        item.setQuantidade(12);
        item.setValorUnitario(new BigDecimal("12.12"));
        itens.add(item);
        
        venda.setItens(itens);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(409);
    }
    
}
