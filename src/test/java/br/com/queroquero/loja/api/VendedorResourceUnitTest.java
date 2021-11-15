package br.com.queroquero.loja.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.queroquero.loja.service.VendedorService;


class VendedorResourceUnitTest {

	@Mock
	private VendedorService vendedorService;

	@InjectMocks
    @Spy
	private VendedorResource vendedorResource;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

    @Test
    void testUsuarioExistente() {
        
        // Vendedor vendResposta = new Vendedor();
        // vendResposta.setNome("JJ");
        // vendResposta.setMatricula("João José");
        // when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendResposta);
        //
        // given().when().get("/api/v1/vendedores/JJ").then().statusCode(200)
        // .body(StringContains.containsString("João José"));
    }

}