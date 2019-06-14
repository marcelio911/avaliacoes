package com.prova.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.prova.response.ProdutoHttpResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author marcelio
 * @font https://spring.io/guides/tutorials/rest/
 */
@RestController
@RequestMapping("/api/produtos")
@Slf4j
public class ProdutoController {

	@Autowired
	private ProdutoDAO dao;

	// 	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@PostMapping(value = "/salvar", produces = "application/json")
	public ProdutoHttpResponse<ProdutoDTO> salvar(@RequestBody @Valid ProdutoEntity obj) throws Exception {
		System.out.println(obj);
		ProdutoEntity saved = dao.insert(obj);
		DataBinder binder = new DataBinder(saved);
		binder.validate();
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.build(saved);
		if (binder.getBindingResult().hasErrors()) {
			return new ProdutoHttpResponse<ProdutoDTO>(binder.getBindingResult().getAllErrors().get(0).toString(),
					HttpStatus.BAD_REQUEST);
		} else {
			ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(
					HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
			return response.build(produtoDTO);
		}
	}

	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET)
	public ProdutoHttpResponse<ProdutoDTO> listarTodos() {
		List<ProdutoEntity> listaDetalhes = dao.findAll();
		if (Objects.isNull(listaDetalhes)) {
			return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
		} else {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			if (Objects.isNull(listaDetalhes)) {
				throw new ProdutoNotFoundException();
			} else {
				ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(
						HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
				return response.build(produtoDTO.createList(listaDetalhes));
			}
		}
	}

	@RequestMapping(value = "/obterPorId/{id}", method = RequestMethod.GET)
	public ProdutoHttpResponse<ProdutoDTO> obterPorId(@PathVariable Long id) {
		Optional<ProdutoEntity> detalhes = dao.findById(id);
		ProdutoHttpResponse<ProdutoDTO> response = new ProdutoHttpResponse<ProdutoDTO>(
						HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
		if (Objects.isNull(detalhes)) {
			return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
			// return detalhes.orElseThrow(() -> new ProdutoNotFoundException());
		} else {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.build(detalhes.get());			
			if (Objects.nonNull(produtoDTO)) {
				return response.build(produtoDTO);
			} 
			produtoDTO.build(detalhes.orElseThrow(() -> new ProdutoNotFoundException()));
			return response.build(produtoDTO);
		}

	}

	@RequestMapping(value = "/removerPorId/{id}", method = RequestMethod.DELETE)
	public ProdutoHttpResponse<ProdutoDTO> delete(@PathVariable Long id) {
		dao.deleteById(id);
		return new ProdutoHttpResponse<ProdutoDTO>(HttpEnum.MSG_SUCESSO_DELETE, HttpStatus.ACCEPTED);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class ProdutoNotFoundException extends ResourceExceptionGeneric {

		ProdutoNotFoundException() {
			super(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
		}
	}

}
