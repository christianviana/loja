package br.com.queroquero.loja.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.queroquero.loja.dao.VendaDAO;

class VendaServiceTest {
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    private VendaDAO vendaDAO;
    
    @Mock
    private VendedorService vendedorService;
    
    @InjectMocks
    private VendaService vendaService;
    
    @Test
    void testCriarVendaOK() {
        // TODO

    }
    
    @Test
    void testCriarVendaVendedorInexistente() {
        // TODO
    }
    
    @Test
    void testCriarVendaVendedorErroAoCriar() {
        // TODO
    }
    
}

