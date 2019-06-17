/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.exception;

import com.prova.enums.HttpEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author marce
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarrinhoComprasNotFoundException extends ResourceExceptionGeneric {

    public CarrinhoComprasNotFoundException() {
        super(HttpEnum.MSG_ERRO_CARRINHO_VAZIO_MR0500, HttpStatus.BAD_REQUEST);
    }
    public CarrinhoComprasNotFoundException(HttpEnum mensagem) {
        super(mensagem, HttpStatus.BAD_REQUEST);
    }
}
