package com.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prova.entity.ClienteEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    
    @Query(value = "select * from tb_cliente where ct_telefone ilike %?1% or dp_cpf ilike %?2 ", nativeQuery = true)
    Set<ClienteEntity> findByTelCpf(String telefone, String cpf);
    

}
