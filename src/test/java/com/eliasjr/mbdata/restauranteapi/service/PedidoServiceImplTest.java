package com.eliasjr.mbdata.restauranteapi.service;

import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Pedido;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.repository.PedidoRepository;
import com.eliasjr.mbdata.restauranteapi.repository.ProdutoRepository;
import com.eliasjr.mbdata.restauranteapi.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Produto produto;
    private Pedido pedido;

    @BeforeEach
    void setup() {
        produto = new Produto();
        produto.setId(1L);
        produto.setPreco(new BigDecimal("100.00"));

        pedido = new Pedido();
        pedido.setProdutos(Arrays.asList(produto));
        pedido.setValorTotal(new BigDecimal("100.00"));
    }

    @Test
    void criarPedidoSucesso() {
        when(produtoRepository.findAllById(any(List.class))).thenReturn(Arrays.asList(produto));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedido);

        Pedido novoPedido = pedidoService.criarPedido(Arrays.asList(1L));

        assertNotNull(novoPedido);
        assertEquals(new BigDecimal("100.00"), novoPedido.getValorTotal());
        verify(pedidoRepository).save(any(Pedido.class));
        verify(produtoRepository).findAllById(any(List.class));
    }

    @Test
    void criarPedidoProdutosNaoEncontrados() {
        when(produtoRepository.findAllById(any(List.class))).thenReturn(Arrays.asList());

        Exception exception = assertThrows(ProdutoNotFoundException.class, () -> {
            pedidoService.criarPedido(Arrays.asList(1L));
        });

        assertEquals("Produtos nao encontrados com os IDs fornecidos", exception.getMessage());
        verify(produtoRepository).findAllById(any(List.class));
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }
}
