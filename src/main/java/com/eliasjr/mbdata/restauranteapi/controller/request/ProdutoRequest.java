package com.eliasjr.mbdata.restauranteapi.controller.request;

import com.eliasjr.mbdata.restauranteapi.model.enums.Categoria;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoRequest {
    private String nome;
    private BigDecimal preco;
    private Categoria categoria;
}
