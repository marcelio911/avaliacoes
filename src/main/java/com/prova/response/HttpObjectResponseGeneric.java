package com.prova.response;

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
@Getter
@Setter
public class HttpObjectResponseGeneric<T> extends CustomResponse implements BaseDTO<T> {

	private BaseDTO<?> objeto;

	public HttpObjectResponseGeneric(HttpEnum resposta, HttpStatus status) {
		super(resposta, status);
	}

	public HttpObjectResponseGeneric(String resposta, HttpStatus status) {
		super(resposta, status);
	}

	@Override
	public HttpObjectResponseGeneric<T> build(T detalhes) {
		setObjeto(objeto);
		return this;
	}

	@Override
	public List<Object> list(List<T> listaEntity) {
		return null;
	}

	public BaseDTO getObjeto() {
		return objeto;
	}
	
	public void setObjeto(BaseDTO objeto2){
		this.objeto = (BaseDTO) objeto2;
	}


}
