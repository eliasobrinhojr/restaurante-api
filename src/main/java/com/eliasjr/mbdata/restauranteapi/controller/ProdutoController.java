package com.eliasjr.mbdata.restauranteapi.controller;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/create")
    @ApiResponse(description = "Cadastra um novo produto")
    public ResponseEntity<?> create(@Valid @RequestBody ProdutoRequest produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoDto));
    }

    @GetMapping("/list")
    @ApiResponse(description = "Lista todos os produtos paginados")
    public ResponseEntity<Page<Produto>> list(@PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos(pageable));
    }

    @PutMapping("/update")
    @ApiResponse(description = "Atualiza registro do produto")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id, @Valid @RequestBody ProdutoRequest produtoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produtoDto));
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @ApiResponse(description = "Deleta registro do produto")
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
