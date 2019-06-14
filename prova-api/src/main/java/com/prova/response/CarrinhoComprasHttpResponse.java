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
public class CarrinhoComprasHttpResponse<T> extends CustomResponse {

	private T objeto;
	private List<T> listaGenerica;

	public CarrinhoComprasHttpResponse(HttpEnum resposta, HttpStatus status) {
		super(resposta, status);
	}
	
	public CarrinhoComprasHttpResponse(String resposta, HttpStatus status) {
		super(resposta, status);
	}

	public CarrinhoComprasHttpResponse<T> build(T detalhes) {
		this.objeto = (T) detalhes ;
		return this;
	}
	
	public CarrinhoComprasHttpResponse<T> build(List<T> listaGenerica) {
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
