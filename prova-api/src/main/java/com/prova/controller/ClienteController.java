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
import org.springframework.web.bind.annotation.RestController;

import com.prova.dao.ClienteDAO;
import com.prova.dto.ClienteDTO;
import com.prova.dto.ClienteFilterDTO;
import com.prova.entity.ClienteEntity;
import com.prova.enums.HttpEnum;
import com.prova.exception.ClienteNotFoundException;
import com.prova.response.ClienteHttpResponse;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author marcelio
 * @font https://spring.io/guides/tutorials/rest/
 */
@RestController
@RequestMapping("/api/cliente")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteDAO dao;

    @PostMapping(value = "/salvar", produces = "application/json")
    public ClienteHttpResponse<ClienteDTO> salvar(@RequestBody @Valid ClienteDTO obj) throws Exception {
        try {
            ClienteEntity saved = dao.insert(new ClienteEntity().build(obj));
            ClienteDTO dto = new ClienteDTO();
            dto.build(saved);
            ClienteHttpResponse<ClienteDTO> response = new ClienteHttpResponse<ClienteDTO>(
                    HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
            return response.build(dto);
        } catch (Exception ex){
            return new ClienteHttpResponse<ClienteDTO>( HttpEnum.MSG_ERRO_GENERICO_MR0500
                    + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/listarTodos", method = RequestMethod.GET)
    public ClienteHttpResponse<ClienteDTO> listarTodos() {
        List<ClienteEntity> listaDetalhes = dao.findAll();
        if (Objects.isNull(listaDetalhes)) {
            return new ClienteHttpResponse<ClienteDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
        } else {
            ClienteDTO dto = new ClienteDTO();
            if (Objects.isNull(listaDetalhes)) {
                throw new ClienteNotFoundException();
            } else {
                ClienteHttpResponse<ClienteDTO> response = new ClienteHttpResponse<ClienteDTO>(
                        HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
                return response.build(dto.createList(listaDetalhes));
            }
        }
    }

    @PostMapping(value = "/obterPorTelCpf/{filter}", produces = "application/json")
    public ClienteHttpResponse<ClienteDTO> obterPorTelCpf(@RequestBody @Valid ClienteFilterDTO filter) {
        Optional<ClienteEntity> detalhes = dao.findByTelCpf(filter);
        ClienteHttpResponse<ClienteDTO> response = new ClienteHttpResponse<ClienteDTO>(
                HttpEnum.MSG_SUCESSO_OPERACAO_GENERICA, HttpStatus.ACCEPTED);
        if (Objects.isNull(detalhes)) {
            return new ClienteHttpResponse<ClienteDTO>(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
        } else {
            ClienteDTO dto = new ClienteDTO();
            dto.build(detalhes.get());
            if (Objects.nonNull(dto)) {
                return response.build(dto);
            }
            dto.build(detalhes.orElseThrow(() -> new ClienteNotFoundException()));
            return response.build(dto);
        }

    }

    @RequestMapping(value = "/removerPorId/{id}", method = RequestMethod.DELETE)
    public ClienteHttpResponse<ClienteDTO> delete(@PathVariable Long id) {
        dao.deleteById(id);
        return new ClienteHttpResponse<ClienteDTO>(HttpEnum.MSG_SUCESSO_DELETE, HttpStatus.ACCEPTED);
    } 

}
