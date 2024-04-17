package com.eliasjr.mbdata.restauranteapi.repository;

import com.eliasjr.mbdata.restauranteapi.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Page<Produto> findAll(Pageable pageable);
}
