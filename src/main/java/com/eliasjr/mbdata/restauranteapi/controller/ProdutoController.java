package com.eliasjr.mbdata.restauranteapi.controller;

import com.eliasjr.mbdata.restauranteapi.controller.request.ProdutoRequest;
import com.eliasjr.mbdata.restauranteapi.exception.ProdutoNotFoundException;
import com.eliasjr.mbdata.restauranteapi.model.Produto;
import com.eliasjr.mbdata.restauranteapi.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ProdutoRequest produtoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Produto>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.listarProdutos());
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam(value = "id") Long id, @Valid @RequestBody ProdutoRequest produtoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.atualizarProduto(id, produtoDto));
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam(value = "id") Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).body("Registro Deletado com sucesso");
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
