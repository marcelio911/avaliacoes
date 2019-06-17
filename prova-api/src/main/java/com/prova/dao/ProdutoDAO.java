package com.prova.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prova.entity.ProdutoEntity;
import com.prova.repository.ProdutoRepository;

@Controller
public class ProdutoDAO {
	
	@Autowired
	ProdutoRepository repo;
	
	public List<ProdutoEntity> findAll() {
		return repo.findAll();
	}
	
	public Optional<ProdutoEntity> findById(Long id){
		return repo.findById(id);
	}
	
        public <S extends ProdutoEntity> S insert(S entity) {
		return repo.saveAndFlush(entity);
	}
	
	public <S extends ProdutoEntity> S update(S entity) {
		return repo.saveAndFlush(entity);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
