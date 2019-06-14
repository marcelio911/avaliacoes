package com.prova.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public <S extends ProdutoEntity> List<S> saveAll(Iterable<S> entities) {
		return repo.saveAll(entities);
	}

	public void flush() {
		repo.flush();
	}
	public <S extends ProdutoEntity> S insert(S entity) {
		return repo.saveAndFlush(entity);
	}
	
	public <S extends ProdutoEntity> S update(S entity) {
		return repo.saveAndFlush(entity);
	}

	public ProdutoEntity getOne(Long id) {
		return repo.getOne(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
