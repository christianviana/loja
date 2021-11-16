package br.com.queroquero.loja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    
    private Produto criarProduto1() {
        return new Produto("Produto 1", new BigDecimal("10.25"));
    }
    
    private Produto criarProduto2() {
        return new Produto("Produto 2", new BigDecimal("4.25"));
    }
    
    @Test
    void testCriarProdutoOK() {
        
        Produto produto = criarProduto1();
        Produto produtoCriado = criarProduto2();
        when(produtoDAO.criarProduto(produto)).thenReturn(produtoCriado);
        
        Produto produtoRetorno = produtoService.criarProduto(produto);
        assertNotNull(produtoRetorno);
        assertTrue((produtoRetorno.equals(produtoCriado)));
    }
    
    @Test
    void testCriarProdutoErro() {
        
        Produto produto = criarProduto1();
        when(produtoDAO.criarProduto(produto)).thenThrow(EntityExistsException.class);
        Produto produtoRetorno = produtoService.criarProduto(produto);
        
        assertNull(produtoRetorno);
    }
    
    @Test
    void testCriarRemoverProdutoOK() {
        
        Produto produto = criarProduto1();
        when(produtoDAO.buscarProduto(ArgumentMatchers.any())).thenReturn(produto);
        assertTrue(produtoService.removerProduto(produto));
        
    }
    
    @Test
    void testRemoverProdutoNaoEncontrado() {
        
        Produto produto = criarProduto1();
        when(produtoDAO.buscarProduto(produto)).thenReturn(null);
        boolean retorno = produtoService.removerProduto(produto);
        
        assertFalse(retorno);
        
    }
    
    @Test
    void testAlterarProdutoOK() {
        
        Produto produto = criarProduto1();
        Produto produtoBuscado = criarProduto2();
        
        when(produtoDAO.buscarProduto(produto)).thenReturn(produtoBuscado);
        Mockito.doNothing().when(produtoDAO).alterarProduto(produto);
        
        boolean retorno = produtoService.alterarProduto(produto);
        
        // // verificar que alteração foi feita e o tipo de retorno
        assertTrue(retorno);
        assertEquals(produto.getNome(), produtoBuscado.getNome());
        assertEquals(produto.getPreco(), produtoBuscado.getPreco());
        
    }
    
    @Test
    void testAlterarProdutoNaoEncontrado() {
        Produto produto = criarProduto1();
        when(produtoDAO.buscarProduto(produto)).thenReturn(null);
        boolean retorno = produtoService.alterarProduto(produto);
        assertFalse(retorno);
        
    }
    
}
