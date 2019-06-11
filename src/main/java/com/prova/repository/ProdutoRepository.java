package com.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.entity.ProdutoEntity;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
