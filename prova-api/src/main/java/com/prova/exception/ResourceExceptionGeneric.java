package com.prova.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prova.enums.HttpEnum;

@ResponseBody()
public class ResourceExceptionGeneric extends CustomException {

    private static final long serialVersionUID = 4309290441932901023L;

    public ResourceExceptionGeneric(HttpEnum resposta, HttpStatus status) {
        super(resposta, status);
    }

    public ResourceExceptionGeneric(String resposta, HttpStatus status) {
        super(resposta, status);
    }

}
