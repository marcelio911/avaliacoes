package com.prova.response;

import com.prova.dto.CarrinhoComprasDTO;
import com.prova.dto.ItemCarrinhoDTO;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prova.enums.HttpEnum;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@ResponseBody()
@Getter
@Setter
public class CarrinhoComprasHttpResponse<T> extends CustomResponse {

    private boolean adicionado;
    private CarrinhoComprasDTO carrinho;

    public CarrinhoComprasHttpResponse(HttpEnum resposta, HttpStatus status) {
        super(resposta, status);
    }

    public CarrinhoComprasHttpResponse(String resposta, HttpStatus status) {
        super(resposta, status);
    }

    public CarrinhoComprasHttpResponse<T> build(CarrinhoComprasDTO carrinho) {
        this.carrinho = carrinho;
        return this;
    }

    public boolean getAdicionado() {
        return adicionado;
    }

}
