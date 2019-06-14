package com.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.entity.CarrinhoComprasEntity;


@Repository
public interface CarrinhoComprasRepository extends JpaRepository<CarrinhoComprasEntity, Long> {


}
