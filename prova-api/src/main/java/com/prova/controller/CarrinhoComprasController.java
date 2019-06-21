package com.prova.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prova.dao.CarrinhoComprasDAO;
import com.prova.dto.CarrinhoComprasDTO;
import com.prova.dto.ItemCarrinhoDTO;
import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ItensCarrinhoEntity;
import com.prova.enums.HttpEnum;
import com.prova.exception.CarrinhoComprasNotFoundException;
import com.prova.response.CarrinhoComprasHttpResponse;
import java.util.HashSet;
import java.util.Set;

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

    @PostMapping(value = "/add", produces = "application/json")
    public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> add(@RequestBody @Valid ItemCarrinhoDTO item) throws Exception {
        try {
            CarrinhoComprasEntity saved = dao.add(item);
            Set<ItemCarrinhoDTO> items = new HashSet<ItemCarrinhoDTO>();
            for (ItemCarrinhoDTO item1 : items) {
                ItemCarrinhoDTO dto = new ItemCarrinhoDTO(item1);
                items.add(dto);
            }            
            CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
                    HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
            return response.build(new CarrinhoComprasDTO().build(saved));
        } catch (Exception ex) {
            return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_ERRO_GENERICO_MR0500
                    + ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/validarProduto/{objeto}", produces = "application/json")
    public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> validarProduto(@RequestBody @Valid ItemCarrinhoDTO objeto) {
        Optional<CarrinhoComprasEntity> produto = dao.validarProduto(objeto);
        Optional<CarrinhoComprasEntity> detalhes = dao.findByIdCliente(objeto.getIdCliente());
        CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
                HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
        if (!produto.isPresent()) {
            return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
        } else {
            Set<ItensCarrinhoEntity> items = detalhes.get().getItemsNoCarrinho();
            for (ItensCarrinhoEntity item1 : items) {
                ItemCarrinhoDTO dto = new ItemCarrinhoDTO().build(item1);
//                items.add(dto);
            }           
            return response.build(new CarrinhoComprasDTO().build(detalhes.get()));

        }

    }
    @RequestMapping(value = "/obterPorIdCliente/{id}", method = RequestMethod.GET)
    public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> obterPorIdCliente(@PathVariable Long id) {
        Optional<CarrinhoComprasEntity> detalhes = dao.findByIdCliente(id);
        CarrinhoComprasHttpResponse<CarrinhoComprasDTO> response = new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(
                HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
        if (!detalhes.isPresent()) {
            return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
        } else {
            CarrinhoComprasDTO dto = new CarrinhoComprasDTO();
            dto.build(detalhes.orElseThrow(() -> new CarrinhoComprasNotFoundException(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404)));
            return response.build(new CarrinhoComprasDTO().build(detalhes.get()));
        }

    }

    @RequestMapping(value = "/removerItemPorIdCliente/{item}", method = RequestMethod.DELETE)
    public CarrinhoComprasHttpResponse<CarrinhoComprasDTO> delete(@RequestBody @Valid ItemCarrinhoDTO item) {
        Optional<CarrinhoComprasEntity> carrinho = dao.findByIdCliente(item.getIdCliente());
        carrinho.orElseThrow(() -> new CarrinhoComprasNotFoundException());
        dao.delete(carrinho.get());
        return new CarrinhoComprasHttpResponse<CarrinhoComprasDTO>(HttpEnum.MSG_SUCESSO_DELETE, HttpStatus.ACCEPTED);
    }

}
