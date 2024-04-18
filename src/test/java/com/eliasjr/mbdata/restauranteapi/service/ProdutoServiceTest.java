package com.eliasjr.mbdata.restauranteapi.service;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.model.enums.Categoria;
import com.eliasjr.mbdata.restauranteapi.repository.ProdutoRepository;
import com.eliasjr.mbdata.restauranteapi.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @Test
    void criarProdutoTest() {
        ProdutoRequest produtoDto = new ProdutoRequest("Corona", BigDecimal.valueOf(8.5), Categoria.BEBIDA);
        Produto produto = new Produto(1L, "Corona", BigDecimal.valueOf(8.5), Categoria.BEBIDA);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto created = produtoService.criarProduto(produtoDto);

        assertNotNull(created);
        assertEquals(produtoDto.getNome(), created.getNome());
        assertEquals(produtoDto.getPreco(), created.getPreco());
        assertEquals(produtoDto.getCategoria(), created.getCategoria());
    }

    @Test
    void listarProdutosTest() {
        Pageable pageable = mock(Pageable.class);
        Page<Produto> expectedPage = mock(Page.class);
        when(produtoRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Produto> page = produtoService.listarProdutos(pageable);

        assertEquals(expectedPage, page);
    }

    @Test
    void atualizarProdutoTest() {
        ProdutoRequest updatedDto = new ProdutoRequest("Produto Atualizado", BigDecimal.valueOf(25.0), Categoria.SOBREMESA);
        Produto existingProduto = new Produto(1L, "Produto Original", BigDecimal.valueOf(25.0), Categoria.SOBREMESA);
        existingProduto.setId(1L);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(existingProduto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(existingProduto);

        Produto updated = produtoService.atualizarProduto(1L, updatedDto);

        assertEquals(updatedDto.getNome(), updated.getNome());
        assertEquals(updatedDto.getPreco(), updated.getPreco());
        assertEquals(updatedDto.getCategoria(), updated.getCategoria());
    }

    @Test
    void atualizarProdutoNotFoundTest() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoService.atualizarProduto(1L, new ProdutoRequest("Produto Atualizado", BigDecimal.valueOf(200.00), Categoria.ENTRADA));
        });
    }

    @Test
    void deletarProdutoTest() {
        Produto produto = new Produto(1L, "Produto XXX", BigDecimal.valueOf(100.00), Categoria.PRATO_PRINCIPAL);
        produto.setId(1L);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deletarProduto(1L);

        verify(produtoRepository).deleteById(1L);
    }

    @Test
    void deletarProdutoNotFoundTest() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> {
            produtoService.deletarProduto(1L);
        });
    }
}
