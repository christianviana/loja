package br.com.queroquero.loja.api;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.queroquero.loja.service.VendedorService;


public class VendedorResourceUnitTest {

	@Mock
	private VendedorService vendedorService;

	@InjectMocks
	private VendedorResource vendedorResource;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

//    @Test
//	public void testUsuarioExistente() {
//
//		Vendedor vendResposta = new Vendedor();
//		vendResposta.setNome("JJ");
//		vendResposta.setMatricula("João José");
//		when(vendedorService.buscarPorMatricula("JJ")).thenReturn(vendResposta);
//
//		given().when().get("/api/v1/vendedores/MM").then().statusCode(200)
//				.body(StringContains.containsString("João José"));
//    }
//
//	@Test
//	public void testUsuarioNaoExistente() {
//		given().when().get("/api/v1/vendedores/MM2").then().statusCode(404);
//	}

}