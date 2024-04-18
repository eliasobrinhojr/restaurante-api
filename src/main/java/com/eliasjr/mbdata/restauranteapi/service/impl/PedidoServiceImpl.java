package com.eliasjr.mbdata.restauranteapi.service.impl;

import com.eliasjr.mbdata.restauranteapi.exception.PedidoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Pedido;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.repository.PedidoRepository;
import com.eliasjr.mbdata.restauranteapi.repository.ProdutoRepository;
import com.eliasjr.mbdata.restauranteapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Pedido criarPedido(List<Long> idsProdutos) {
        List<Produto> produtos = produtoRepository.findAllById(idsProdutos);

        if (produtos.isEmpty()) {
            throw new ProdutoNotFoundException("Produtos nao encontrados com os IDs fornecidos");
        }

        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);
        pedido.setValorTotal(produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add));

        return pedidoRepository.save(pedido);
    }

    @Override
    public Page<Pedido> listarPedidos(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Pedido adicionarProdutos(Long id, List<Long> idsProdutos) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado com ID: " + id));

        List<Produto> produtosParaAdicionar = produtoRepository.findAllById(idsProdutos);

        if (produtosParaAdicionar.isEmpty()) {
            throw new ProdutoNotFoundException("Produtos não encontrados com os IDs fornecidos");
        }

        pedido.getProdutos().addAll(produtosParaAdicionar);

        BigDecimal valorTotalAtualizado = pedido.getProdutos().stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setValorTotal(valorTotalAtualizado);

        return pedidoRepository.save(pedido);
    }
}
