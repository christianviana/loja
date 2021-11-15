package br.com.quero.quero.loja.integracao;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.com.queroquero.loja.bo.Vendedor;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class VendedorIntegracaoTest {
    
    // @Test
    // void testBuscarVendedorExistente() {
    // Vendedor vendedor = new Vendedor();
    // vendedor.setMatricula("MJ");
    // vendedor.setNome("Maria Joana");
    //
    // // cria a 1a vez e dá 201
    // given().contentType("application/json").body(vendedor).when().post("/api/v1/vendedores").then().statusCode(201);
    //
    // given().when().get("/api/v1/vendedores/MJ").then().statusCode(200)
    // .body(StringContains.containsString("Maria Joana"));
    // }

	@Test
    void testBuscarVendedorNaoExistente() {
		given().when().get("/api/v1/vendedores/MM2").then().statusCode(404);
	}

    @Test
    void testCriarVendedorExistente() {
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("MJ");
        vendedor.setNome("Maria Joana");
        
        // cria a 1a vez e dá 201
        given().contentType("application/json").body(vendedor)
            .when().post("/api/v1/vendedores").then()
                .statusCode(201);
        
        // cria a 2a vez e dá 409
        given().contentType("application/json").body(vendedor).when().post("/api/v1/vendedores").then().statusCode(409);
    }

	@Test
    void testCriarVendedorNaoExistente() {
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("CR");
        vendedor.setNome("Cleiton Ramos ");        
        given().contentType("application/json").body(vendedor)
            .when().post("/api/v1/vendedores").then()
            .statusCode(201);
	}

}