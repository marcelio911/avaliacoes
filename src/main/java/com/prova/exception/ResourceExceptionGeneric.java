package com.prova.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prova.dto.BaseDTO;
import com.prova.dto.ProdutoDTO;
import com.prova.enums.HttpEnum;

import lombok.Getter;
import lombok.Setter;

@ResponseBody()
public class ResourceExceptionGeneric<T> extends CustomException {

	private static final long serialVersionUID = 4309290441932901023L;

	public ResourceExceptionGeneric(HttpEnum resposta, HttpStatus status) {
		super(resposta, status);
	}

	public ResourceExceptionGeneric(String resposta, HttpStatus status) {
		super(resposta, status);
	}


}
