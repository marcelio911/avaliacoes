/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prova.dao.ProdutoDAO;
import com.prova.dto.ProdutoDTO;
import com.prova.entity.ProdutoEntity;
import com.prova.enums.HttpEnum;
import com.prova.exception.ResourceExceptionGeneric;
import com.prova.response.HttpListResponseGeneric;
import com.prova.response.ProdutoHttpResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author marcelio
 */
@Controller
@RestController
@RequestMapping("/produtos")
@Slf4j
public class ProdutoController {

	@Autowired
	private ProdutoDAO dao;

	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@RequestMapping(value = "/salvar", method = RequestMethod.PUT)
	public ProdutoHttpResponse<ProdutoDTO> saveAndFlush(@RequestBody @Valid ProdutoEntity mun) throws Exception {
		ProdutoEntity saved = dao.saveAndFlush(mun);
		DataBinder binder = new DataBinder(saved);
		binder.validate();
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.build(saved);
		if (binder.getBindingResult().hasErrors()) {
			return new ProdutoHttpResponse<ProdutoDTO>(binder.getBindingResult().getAllErrors().get(0).toString(),
					HttpStatus.BAD_REQUEST);
		} else {
			if (Objects.isNull(saved)) {
				throw new ProdutoNotFoundException();
			} else {
				ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR01, HttpStatus.ACCEPTED);
				return response.build(produtoDTO);

			}
		}
	}

	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET)
	public ProdutoHttpResponse<ProdutoDTO> listarTodos() {
		List<ProdutoEntity> listaDetalhes = dao.findAll();
		if (Objects.isNull(listaDetalhes)) {
			return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR01, HttpStatus.BAD_REQUEST);
		} else {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			if (Objects.isNull(listaDetalhes)) {
				throw new ProdutoNotFoundException();
			} else {
				ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
				return response.build(produtoDTO.createList(listaDetalhes));
			}			
		}
	}

	@RequestMapping(value = "/obterPorId/{id}", method = RequestMethod.GET)
	public ProdutoHttpResponse<ProdutoDTO> obterPorId(@PathVariable Long id) {
		Optional<ProdutoEntity> detalhes = dao.findById(id);
		if (Objects.isNull(detalhes)) {
			return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR01, HttpStatus.BAD_REQUEST);
		} else {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.build(detalhes.get());
			if (Objects.isNull(detalhes.get())) {
				throw new ProdutoNotFoundException();
			} else {
				ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
				return response.build(produtoDTO);
			}
		}
	}

	@RequestMapping(value = "/obterPorIdProduto/{id}", method = RequestMethod.GET)
	public @ResponseBody ProdutoHttpResponse<ProdutoDTO> obterPorIdProduto(@PathVariable Long id) {
		Optional<ProdutoEntity> detalhes = dao.findById(id);
		if (Objects.isNull(detalhes)) {
			return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR01, HttpStatus.BAD_REQUEST);
		} else {
			ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(
					HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.build(detalhes.get());
			response.build(produtoDTO);;
			return response;
		}
	}

	@RequestMapping(value = "/removerPorId/{id}", method = RequestMethod.DELETE)
	public HttpEntity delete(@PathVariable Long id) {
		dao.deleteById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class ProdutoNotFoundException extends ResourceExceptionGeneric<ProdutoDTO> {
		
		ProdutoNotFoundException() {
			super(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR01,	HttpStatus.BAD_REQUEST);
		}
	}

}
