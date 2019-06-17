package com.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.entity.CarrinhoComprasEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CarrinhoComprasRepository extends JpaRepository<CarrinhoComprasEntity, Long> {

    @Query(value = "select * from tb_carrinho_compras where id_cliente_no_carrinho = ?1", nativeQuery = true)
    Optional<CarrinhoComprasEntity> findByIdCliente(Long id);
    
    @Query(value = "select * from tb_carrinho_compras where produto = ?1", nativeQuery = true)
    Optional<CarrinhoComprasEntity> validarProduto(Long id);

}
