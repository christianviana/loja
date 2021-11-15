package br.com.queroquero.loja.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.dao.ProdutoDAO;

class ProdutoServiceTest {
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    private ProdutoDAO produtoDAO;
    
    @InjectMocks
    private ProdutoService produtoService;
    
    @Test
    void testCriarProdutoOK() {
        
        Produto produto = new Produto();
        produto.setCodigo("1");
        produto.setNome("produto 1");
        
        Produto produtoCriado = new Produto();
        produtoCriado.setId(1L);
        produtoCriado.setCodigo("1");
        produto.setNome("produto 1");
        
        when(produtoDAO.criarProduto(produto)).thenReturn(produtoCriado);
        
        Produto produtoRetorno = produtoService.criarProduto(produto);
        
        assertNotNull(produtoRetorno);
        assertTrue((produtoRetorno.getId().equals(1L)));
        assertTrue((produtoRetorno.equals(produtoCriado)));
        
    }
    
    @Test
    void testCriarProdutoErro() {
        Produto produto = new Produto();
        produto.setCodigo("1");
        produto.setNome("produto 1");
        
        Produto produtoCriado = new Produto();
        produtoCriado.setId(1L);
        produtoCriado.setCodigo("1");
        produto.setNome("produto 1");
        
        when(produtoDAO.criarProduto(produto)).thenThrow(EntityExistsException.class);
        
        Produto produtoRetorno = produtoService.criarProduto(produto);
        assertNull(produtoRetorno);
        
    }
}
