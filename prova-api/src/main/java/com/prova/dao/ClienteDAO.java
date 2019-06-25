package com.prova.dao;

import com.prova.dto.ClienteFilterDTO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prova.entity.ClienteEntity;
import com.prova.repository.ClienteRepository;
import java.util.Objects;
import java.util.Set;

@Controller
public class ClienteDAO {

    @Autowired
    ClienteRepository repo;

    public List<ClienteEntity> findAll() {
        return repo.findAll();
    }
    
    public Set<ClienteEntity> findByTelCpf(ClienteFilterDTO dto) {
        return repo.findByTelCpf(dto.getTelefone(), dto.getCpf());
    }

    public <S extends ClienteEntity> S add(ClienteEntity entity) {
        if (Objects.nonNull(entity.getId())) {
            return this.update((S) entity);
        } else {
            return this.insert((S) entity);
        }
    }

    public <S extends ClienteEntity> S insert(S entity) {
        return repo.saveAndFlush(entity);
    }

    public <S extends ClienteEntity> S update(S entity) {
        return repo.saveAndFlush(entity);
    }

    public ClienteEntity getOne(Long id) {
        return repo.getOne(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
