package com.eliasjr.mbdata.restauranteapi.service;

import com.eliasjr.mbdata.restauranteapi.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService {

    Pedido criarPedido(List<Long> idsProdutos);

    Page<Pedido> listarPedidos(Pageable pageable);

    Pedido adicionarProdutos(Long id, List<Long> idsProdutos);
}
