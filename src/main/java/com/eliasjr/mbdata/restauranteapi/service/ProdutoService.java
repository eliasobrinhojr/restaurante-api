package com.eliasjr.mbdata.restauranteapi.service;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {

    Produto criarProduto(ProdutoRequest produtoDto);

    Page<Produto> listarProdutos(Pageable pageable);

    Produto atualizarProduto(Long id, ProdutoRequest produtoAtualizado);

    void deletarProduto(Long id);
}
