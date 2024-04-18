package com.eliasjr.mbdata.restauranteapi.controller.request;

import com.eliasjr.mbdata.restauranteapi.model.enums.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProdutoRequest {

    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private Categoria categoria;
}
