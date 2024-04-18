package com.eliasjr.mbdata.restauranteapi.service.impl;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.repository.ProdutoRepository;
import com.eliasjr.mbdata.restauranteapi.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(ProdutoRequest produtoDto) {
        log.info("Criando um novo produto = {}", produtoDto.getNome());

        return produtoRepository.save(Produto.builder()
                .nome(produtoDto.getNome())
                .preco(produtoDto.getPreco())
                .categoria(produtoDto.getCategoria())
                .build());
    }

    @Override
    public Page<Produto> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Produto atualizarProduto(Long id, ProdutoRequest produtoAtualizado) {
        log.info("Atualizando um  produto = {}", id);
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setCategoria(produtoAtualizado.getCategoria());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new ProdutoNotFoundException("Produto com id " + id + " nao encontrado"));
    }

    @Override
    public void deletarProduto(Long id) {
        log.info("Excluindo um produto = {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFoundException("Produto com id " + id + " nao encontrado"));
        produtoRepository.deleteById(produto.getId());
    }
}
