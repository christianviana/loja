package br.com.queroquero.loja.api;

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
class VendaResourceTest {
    
    @Test
    void testCriarVendaNaoExistenteOK() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("MM");
        venda.setVendedor(vendedor);
        
        List<Item> itens = new ArrayList<>();
        
        Item item = new Item();
        item.setCodigoProduto("1");
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
        vendedor.setMatricula("MM2");
        venda.setVendedor(vendedor);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(409);
    }
    
    @Test
    void testCriarVendaComProdutoNaoExistente() {
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("10.11"));
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("MM");
        venda.setVendedor(vendedor);
        
        List<Item> itens = new ArrayList<>();
        
        Item item = new Item();
        item.setCodigoProduto("10");
        item.setDescricao("produto 1");
        item.setQuantidade(12);
        item.setValorUnitario(new BigDecimal("12.12"));
        itens.add(item);
        
        venda.setItens(itens);
        
        given().contentType("application/json").body(venda).when().post("/api/v1/vendas").then().statusCode(409);
    }
    
}
