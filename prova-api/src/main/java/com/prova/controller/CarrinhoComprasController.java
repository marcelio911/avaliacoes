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

import com.prova.dao.CarrinhoComprasDAO;
import com.prova.dto.CarrinhoComprasDTO;
import com.prova.entity.CarrinhoComprasEntity;
import com.prova.enums.HttpEnum;
import com.prova.exception.ResourceExceptionGeneric;
import com.prova.response.CarrinhoComprasHttpResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author marcelio
 * @font https://spring.io/guides/tutorials/rest/
 */
@RestController
@RequestMapping("/api/carrinho")
@Slf4j
public class CarrinhoComprasController {

	@Autowired
	private CarrinhoComprasDAO dao;

	// 	private static final Logger logger = LoggerFactory.getLogger(CarrinhoComprasController.class);

	@PostMapping(value = "/salvar", produces = "application/json")
	public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> salvar(@RequestBody @Valid CarrinhoComprasEntity obj) throws Exception {
		System.out.println(obj);
		CarrinhoComprasEntity saved = dao.insert(obj);
		DataBinder binder = new DataBinder(saved);
		binder.validate();
		CarrinhoComprasDTO CarrinhoComprasDTO = new CarrinhoComprasDTO();
		CarrinhoComprasDTO.build(saved);
		if (binder.getBindingResult().hasErrors()) {
			return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(binder.getBindingResult().getAllErrors().get(0).toString(),
					HttpStatus.BAD_REQUEST);
		} else {
			CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
					HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
			return response.build(CarrinhoComprasDTO);
		}
	}

	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET)
	public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> listarTodos() {
		List<CarrinhoComprasEntity> listaDetalhes = dao.findAll();
		if (Objects.isNull(listaDetalhes)) {
			return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
		} else {
			CarrinhoComprasDTO CarrinhoComprasDTO = new CarrinhoComprasDTO();
			if (Objects.isNull(listaDetalhes)) {
				throw new CarrinhoComprasNotFoundException();
			} else {
				CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
						HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
				return response.build(CarrinhoComprasDTO.createList(listaDetalhes));
			}
		}
	}

	@RequestMapping(value = "/obterPorId/{id}", method = RequestMethod.GET)
	public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> obterPorId(@PathVariable Long id) {
		Optional<CarrinhoComprasEntity> detalhes = dao.findById(id);
		CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
						HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
		if (Objects.isNull(detalhes)) {
			return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
			// return detalhes.orElseThrow(() -> new CarrinhoComprasNotFoundException());
		} else {
			CarrinhoComprasDTO CarrinhoComprasDTO = new CarrinhoComprasDTO();
			CarrinhoComprasDTO.build(detalhes.get());			
			if (Objects.nonNull(CarrinhoComprasDTO)) {
				return response.build(CarrinhoComprasDTO);
			} 
			CarrinhoComprasDTO.build(detalhes.orElseThrow(() -> new CarrinhoComprasNotFoundException()));
			return response.build(CarrinhoComprasDTO);
		}

	}

	@RequestMapping(value = "/removerPorId/{id}", method = RequestMethod.DELETE)
	public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> delete(@PathVariable Long id) {
		dao.deleteById(id);
		return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_SUCESSO_DELETE, HttpStatus.ACCEPTED);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class CarrinhoComprasNotFoundException extends ResourceExceptionGeneric {

		CarrinhoComprasNotFoundException() {
			super(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
		}
	}

}
