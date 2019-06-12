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
	
	
	ProdutoRepository repo;
	
	@Autowired
	public ProdutoDAO(ProdutoRepository repo) {
		this.repo = repo;
	}
	
	public List<ProdutoEntity> findAll() {
		return repo.findAll();
	}
	
	public Optional<ProdutoEntity> findById(Long id){
		return repo.findById(id);
	}
	
	public List<ProdutoEntity> findAll(Sort sort) {
		return  repo.findAll(sort);
	}	
	
	public List<ProdutoEntity> findAllById(Iterable<Long> ids) {
		return repo.findAllById(ids);
	}

	
	public <S extends ProdutoEntity> List<S> saveAll(Iterable<S> entities) {
		return repo.saveAll(entities);
	}

	
	public void flush() {
		repo.flush();
	}
	
	public <S extends ProdutoEntity> S saveAndFlush(S entity) {
		return repo.saveAndFlush(entity);
	}

		
	public ProdutoEntity getOne(Long id) {
		return repo.getOne(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	
	public void delete(ProdutoEntity entity) {
		repo.delete(entity);

	}	

}
