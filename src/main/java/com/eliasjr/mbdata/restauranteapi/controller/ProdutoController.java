package com.eliasjr.mbdata.restauranteapi.controller;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/produtos")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/create")
    @ApiResponse(description = "Produto")
    @Operation(description = "Cadastra um novo produto")
    public ResponseEntity<?> create(@Valid @RequestBody ProdutoRequest produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoDto));
    }

    @GetMapping("/list")
    @ApiResponse(description = "Produtos Paginados")
    @Operation(description = "Lista todos os produtos paginados")
    public ResponseEntity<Page<Produto>> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortField", defaultValue = "nome") String sortField,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos(pageable));
    }

    @PutMapping("/update")
    @ApiResponse(description = "Retorna um produto atualizado ")
    @Operation(description = "Atualiza um registro na base")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id, @Valid @RequestBody ProdutoRequest produtoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produtoDto));
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @ApiResponse(description = "Retorna OK se excluido com sucesso")
    @Operation(description = "Deleta registro produto por id da base")
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
