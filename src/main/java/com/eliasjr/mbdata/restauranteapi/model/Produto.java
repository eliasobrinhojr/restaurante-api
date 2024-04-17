package com.eliasjr.mbdata.restauranteapi.model;

import com.eliasjr.mbdata.restauranteapi.model.enums.Categoria;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
