package com.prova.exception;

import com.prova.enums.HttpEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author marce
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends ResourceExceptionGeneric {

    public ClienteNotFoundException() {
        super(HttpEnum.MSG_ERRO_CARRINHO_VAZIO_MR0500, HttpStatus.BAD_REQUEST);
    }
    
    public ClienteNotFoundException(HttpEnum mensagem) {
        super(mensagem, HttpStatus.BAD_REQUEST);
    }
}
