package com.eliasjr.mbdata.restauranteapi.service.impl;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.repository.ProdutoRepository;
import com.eliasjr.mbdata.restauranteapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(ProdutoRequest produtoDto) {
        return produtoRepository.save(Produto.builder()
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .categoria(produtoDto.getCategoria())
                .build());
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto atualizarProduto(Long id, ProdutoRequest produtoAtualizado) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
