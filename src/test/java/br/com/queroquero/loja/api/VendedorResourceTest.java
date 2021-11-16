package br.com.queroquero.loja.api;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.service.VendedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
class VendedorResourceTest {

    @InjectMock
	private VendedorService vendedorService;


    @Test
    void testBuscaVendedorExistente() {
        
        Vendedor vendResposta = new Vendedor();
        vendResposta.setNome("João José");
        vendResposta.setMatricula(1L);
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendResposta);
        
        given().when().get("/api/v1/vendedores/1").then().statusCode(200)
                .body(StringContains.containsString("João José"));
    }

    @Test
    void testBuscaVendedorNaoExiste() {
        
        Vendedor vendResposta = new Vendedor();
        vendResposta.setNome("João José");
        vendResposta.setMatricula(1L);
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(null);
        
        given().when().get("/api/v1/vendedores/1").then().statusCode(404);
        
    }
    
    @Test
    void testCriarVendedorOK() {
        
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("João José");
        vendedor.setMatricula(1L);
        
        Vendedor vendedorResposta = new Vendedor();
        vendedorResposta.setNome("João José");
        vendedorResposta.setMatricula(1L);
        when(vendedorService.criarVendedor(vendedor)).thenReturn(vendedorResposta);
        
        given().contentType("application/json").body(vendedor).when().post("/api/v1/vendedores/").then()
                .statusCode(201).body(StringContains.containsString("João José"));
        
    }
    
    @Test
    void testCriarVendedorErro() {
        
        Vendedor vendedor = new Vendedor();
        vendedor.setNome("João José");
        vendedor.setMatricula(1L);
        
        Vendedor vendedorResposta = new Vendedor();
        vendedorResposta.setNome("João José");
        vendedorResposta.setMatricula(1L);
        when(vendedorService.criarVendedor(vendedor)).thenReturn(null);
        
        given().contentType("application/json").body(vendedor).when().post("/api/v1/vendedores/").then()
                .statusCode(500).body(StringContains.containsString("Erro ao criar o vendedor"));
        
    }
    
}