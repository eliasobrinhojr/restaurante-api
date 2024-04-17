package com.eliasjr.mbdata.restauranteapi.service;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.model.Produto;

import java.util.List;

public interface ProdutoService {

    Produto criarProduto(ProdutoRequest produtoDto);

    List<Produto> listarProdutos();

    Produto atualizarProduto(Long id, ProdutoRequest produtoAtualizado);

    void deletarProduto(Long id);
}
