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
    void testUsuarioExistente() {
        
        Vendedor vendResposta = new Vendedor();
        vendResposta.setNome("JJ");
        vendResposta.setMatricula("João José");
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendResposta);
        
        given().when().get("/api/v1/vendedores/JJ").then().statusCode(200)
                .body(StringContains.containsString("João José"));
    }

}