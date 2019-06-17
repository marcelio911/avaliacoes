package com.prova.repository;

import com.prova.dto.ClienteFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.entity.ClienteEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    
    @Query(value = "select * from tb_cliente where ct_telefone < ?1 or dp_cpf < ?2 ", nativeQuery = true)
    Optional<ClienteEntity> findByTelCpf(String telefone, String cpf);
    

}
