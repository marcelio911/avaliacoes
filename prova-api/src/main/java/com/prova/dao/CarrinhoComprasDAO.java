package com.prova.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.repository.CarrinhoComprasRepository;

@Controller
public class CarrinhoComprasDAO {
	
	@Autowired
	CarrinhoComprasRepository repo;
	
	public List<CarrinhoComprasEntity> findAll() {
		return repo.findAll();
	}
	
	public Optional<CarrinhoComprasEntity> findById(Long id){
		return repo.findById(id);
	}
	
	public <S extends CarrinhoComprasEntity> List<S> saveAll(Iterable<S> entities) {
		return repo.saveAll(entities);
	}

	public void flush() {
		repo.flush();
	}
	public <S extends CarrinhoComprasEntity> S insert(S entity) {
		entity.setId(null);
		return repo.saveAndFlush(entity);
	}
	
	public <S extends CarrinhoComprasEntity> S update(S entity) {
		return repo.saveAndFlush(entity);
	}

	public CarrinhoComprasEntity getOne(Long id) {
		return repo.getOne(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
