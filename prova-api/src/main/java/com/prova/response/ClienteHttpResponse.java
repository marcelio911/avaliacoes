package com.prova.response;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prova.enums.HttpEnum;

import lombok.Getter;
import lombok.Setter;

@ResponseBody()
@Getter
@Setter
public class ClienteHttpResponse<T> extends CustomResponse {

	private T objeto;
	private List<T> listaGenerica;

	public ClienteHttpResponse(HttpEnum resposta, HttpStatus status) {
		super(resposta, status);
	}
	
	public ClienteHttpResponse(String resposta, HttpStatus status) {
		super(resposta, status);
	}

	public ClienteHttpResponse<T> build(T detalhes) {
		this.objeto = (T) detalhes ;
		return this;
	}
	
	public ClienteHttpResponse<T> build(List<T> listaGenerica) {
		this.listaGenerica = listaGenerica ;
		return this;
	}

	public T getObjeto() {
		return objeto;
	}
	
	public List<T> getListaGenerica() {
		return listaGenerica;
	}
	


}
