package br.com.queroquero.loja.api;

import static io.restassured.RestAssured.given;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class VendedorResourceTest {

    @Test
    void testBuscarVendedorExistente() {
		given().when().get("/api/v1/vendedores/MM").then().statusCode(200)
				.body(StringContains.containsString("Maria Maria"));
    }

	@Test
    void testBuscarVendedorNaoExistente() {
		given().when().get("/api/v1/vendedores/MM2").then().statusCode(404);
	}

//	@Test
//	public void testCriarVendedorExistente() {
//		given().when().post("/api/v1/vendedores/{matricula}/{nome}", "MM", "Maria Maria").then().statusCode(500);
//	}

//	@Test
//	public void testCriarVendedorNaoExistente() {
//		given().when().post("/api/v1/vendedores/{matricula}/{nome}", "PP", "Pedro Paulo").then().statusCode(201);
//	}

}