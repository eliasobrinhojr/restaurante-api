package com.eliasjr.mbdata.restauranteapi.controller;

import com.eliasjr.mbdata.restauranteapi.exception.PedidoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Pedido;
import com.eliasjr.mbdata.restauranteapi.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/pedidos")
@RestController
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ApiResponse(description = "Retorna um novo pedido criado")
    @Operation(description = "cria um novo pedido com 1 ou N produtos")
    public ResponseEntity<?> criarPedido(@RequestBody List<Long> idsProdutos) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.criarPedido(idsProdutos));
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    @ApiResponse(description = "Listar todos os pedidos paginados por id")
    @Operation(description = "lista pedidos paginados ordenados por id ordem descrecente")
    public ResponseEntity<Page<Pedido>> listarTodos(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                                    @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Pedido> pedidos = pedidoService.listarPedidos(pageable);
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping("/{id}/produtos")
    @Operation(description = "adiciona produto em um pedido existente")
    @ApiResponse(description = "Retorna um pedido exististente com produtos adicionados")
    public ResponseEntity<?> adicionarProdutosAoPedido(@PathVariable Long id, @RequestBody List<Long> idsProdutos) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.adicionarProdutos(id, idsProdutos));
        } catch (PedidoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
