package br.com.queroquero.loja.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityExistsException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.queroquero.loja.bo.Item;
import br.com.queroquero.loja.bo.Produto;
import br.com.queroquero.loja.bo.Venda;
import br.com.queroquero.loja.bo.Vendedor;
import br.com.queroquero.loja.dao.VendaDAO;
import br.com.queroquero.loja.service.excecoes.ProdutoInexistenteException;
import br.com.queroquero.loja.service.excecoes.VendedorInexistenteException;

class VendaServiceTest {
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    private VendaDAO vendaDAO;
    
    @Mock
    private VendedorService vendedorService;
    
    @Mock
    private ProdutoService produtoService;
    
    @InjectMocks
    private VendaService vendaService;
    
    private Produto criarProduto1() {
        return new Produto("Produto 1", new BigDecimal("10.25"));
    }
    
    private Produto criarProduto2() {
        return new Produto("Produto 2", new BigDecimal("4.25"));
    }
    
    private Vendedor criarVendedor1() {
        return new Vendedor(1L, "Vendedor 1");
    }
    
    
    @Test
    void testCriarVendaOK() {
        
        Produto produto1 = criarProduto1();
        Produto produto2 = criarProduto2();
        
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("200.20"));
        ArrayList<Item> itens = new ArrayList<>();
        Item item1 = new Item();
        item1.setCodigoProduto(1L);
        item1.setDescricao("p1");
        item1.setQuantidade(2);
        item1.setValorUnitario(new BigDecimal("10.12"));
                
        Item item2 = new Item();
        item2.setCodigoProduto(2L);
        item2.setDescricao("p2");
        item2.setQuantidade(4);
        item2.setValorUnitario(new BigDecimal("4.23"));
        
        itens.add(item1);
        itens.add(item2);
        venda.setItens(itens);

        Vendedor vendedor1 = criarVendedor1();
        venda.setVendedor(vendedor1);
        
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendedor1);
        when(produtoService.buscarPorCodigo(item1.getCodigoProduto())).thenReturn(produto1);
        when(produtoService.buscarPorCodigo(item2.getCodigoProduto())).thenReturn(produto2);
        when(vendaDAO.criarVenda(venda)).thenReturn(venda);
        
        Venda vendaCriada = vendaService.criarVenda(venda);
        
        assertEquals(vendaCriada, venda);
        
    }
    
    @Test
    void testCriarVendaVendedorInexistente() {
        Produto produto1 = criarProduto1();
        Produto produto2 = criarProduto2();
        
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("200.20"));
        ArrayList<Item> itens = new ArrayList<>();
        Item item1 = new Item();
        item1.setCodigoProduto(1L);
        item1.setDescricao("p1");
        item1.setQuantidade(2);
        item1.setValorUnitario(new BigDecimal("10.12"));
        
        Item item2 = new Item();
        item2.setCodigoProduto(2L);
        item2.setDescricao("p2");
        item2.setQuantidade(4);
        item2.setValorUnitario(new BigDecimal("4.23"));
        
        itens.add(item1);
        itens.add(item2);
        venda.setItens(itens);
        
        venda.setVendedor(criarVendedor1());
        
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(null);
        when(produtoService.buscarPorCodigo(item1.getCodigoProduto())).thenReturn(produto1);
        when(produtoService.buscarPorCodigo(item2.getCodigoProduto())).thenReturn(produto2);
        when(vendaDAO.criarVenda(venda)).thenReturn(venda);
        
        assertThrows(VendedorInexistenteException.class, () -> vendaService.criarVenda(venda));
    }
    
    @Test
    void testCriarVendaProdutoInexistente() {
        
        Produto produto1 = criarProduto1();
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("200.20"));
        ArrayList<Item> itens = new ArrayList<>();
        Item item1 = new Item();
        item1.setCodigoProduto(1L);
        item1.setDescricao("p1");
        item1.setQuantidade(2);
        item1.setValorUnitario(new BigDecimal("10.12"));
        
        Item item2 = new Item();
        item2.setCodigoProduto(2L);
        item2.setDescricao("p2");
        item2.setQuantidade(4);
        item2.setValorUnitario(new BigDecimal("4.23"));
        
        itens.add(item1);
        itens.add(item2);
        venda.setItens(itens);
        
        Vendedor vendedor1 = criarVendedor1();
        venda.setVendedor(vendedor1);
        
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendedor1);
        when(produtoService.buscarPorCodigo(item1.getCodigoProduto())).thenReturn(produto1);
        when(produtoService.buscarPorCodigo(item2.getCodigoProduto())).thenReturn(null);
        when(vendaDAO.criarVenda(venda)).thenReturn(venda);
        
        assertThrows(ProdutoInexistenteException.class, () -> vendaService.criarVenda(venda));
        
    }
    
    @Test
    void testCriarVendaVendedorErroAoCriar() {
        
        Produto produto1 = criarProduto1();
        Produto produto2 = criarProduto2();
        
        Venda venda = new Venda();
        venda.setValorTotal(new BigDecimal("200.20"));
        ArrayList<Item> itens = new ArrayList<>();
        Item item1 = new Item();
        item1.setCodigoProduto(1L);
        item1.setDescricao("p1");
        item1.setQuantidade(2);
        item1.setValorUnitario(new BigDecimal("10.12"));
        
        Item item2 = new Item();
        item2.setCodigoProduto(2L);
        item2.setDescricao("p2");
        item2.setQuantidade(4);
        item2.setValorUnitario(new BigDecimal("4.23"));
        
        itens.add(item1);
        itens.add(item2);
        venda.setItens(itens);
        
        Vendedor vendedor1 = criarVendedor1();
        venda.setVendedor(vendedor1);
        
        when(vendedorService.buscarPorMatricula(ArgumentMatchers.any())).thenReturn(vendedor1);
        when(produtoService.buscarPorCodigo(item1.getCodigoProduto())).thenReturn(produto1);
        when(produtoService.buscarPorCodigo(item2.getCodigoProduto())).thenReturn(produto2);
        when(vendaDAO.criarVenda(venda)).thenThrow(EntityExistsException.class);
        
        Venda vendaCriada = vendaService.criarVenda(venda);
        assertNull(vendaCriada);
        
    }
    
}

