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
public class ProdutoNotFoundException extends ResourceExceptionGeneric {

    public ProdutoNotFoundException() {
        super(HttpEnum.MSG_ERRO_NENHUM_RESULTADO_MR0404, HttpStatus.BAD_REQUEST);
    }
}
