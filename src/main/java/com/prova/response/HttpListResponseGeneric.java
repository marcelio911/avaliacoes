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
public class HttpListResponseGeneric<T> extends CustomResponse implements BaseDTO<T> {
	
	private BaseDTO<?> objeto;
	private List<Object> listaGenerica = new ArrayList<Object>();

	public HttpListResponseGeneric(HttpEnum resposta, HttpStatus status) {
		super(resposta, status);
	}

	public HttpListResponseGeneric(String resposta, HttpStatus status) {
		super(resposta, status);
	}

	@Override
	public HttpListResponseGeneric<T> build(T detalhes) {
		setObjeto((BaseDTO) detalhes);
		return this;
	}

	@Override
	public List<?> createList(List<T> listaEntity) {
		return this.listaGenerica = this.objeto.createList(listaEntity);
	}


	public List<?> getListaGenerica() {
		return listaGenerica;
	}

	public void setListaGenerica(List<Object> listaGenerica) {
		this.listaGenerica = listaGenerica;
	}
	
	public BaseDTO getObjeto() {
		return objeto;
	}
	
	public void setObjeto(BaseDTO objeto2){
		this.objeto = (BaseDTO) objeto2;
	}

}
