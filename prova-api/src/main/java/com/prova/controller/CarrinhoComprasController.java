package com.prova.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        try {
            CarrinhoComprasEntity saved = dao.insert(obj);
            CarrinhoComprasDTO produtoDTO = new CarrinhoComprasDTO();
            produtoDTO.build(saved);
            CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
                    HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
            return response.build(produtoDTO);
        } catch (Exception ex){
            return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>( HttpEnum.MSG_ERRO_GENERICO_MR0500
                    + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
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
            CarrinhoComprasDTO produtoDTO = new CarrinhoComprasDTO();
            produtoDTO.build(detalhes.get());
            if (Objects.nonNull(produtoDTO)) {
                return response.build(produtoDTO);
            }
            produtoDTO.build(detalhes.orElseThrow(() -> new CarrinhoComprasNotFoundException()));
            return response.build(produtoDTO);
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
