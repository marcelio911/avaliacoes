package com.prova.repository;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ItensCarrinhoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinhoEntity, Long> {

    @Query(value = "select * from tb_carrinho_itens where id_produto_no_carrinho = ?1", nativeQuery = true)
    Optional<ItensCarrinhoEntity> validarProduto(Long id);

}
