package com.prova.dto;

import java.util.ArrayList;
import java.util.List;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ProdutoEntity;
import com.prova.entity.ClienteEntity;

public class CarrinhoComprasDTO implements BaseDTO<CarrinhoComprasEntity> {

    private Long id;

    private ClienteEntity clienteNoCarrinho;

    private ProdutoEntity produtoNoCarrinho;

    public CarrinhoComprasDTO() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getClienteNoCarrinho() {
        return clienteNoCarrinho;
    }

    public void setClienteNoCarrinho(ClienteEntity clienteNoCarrinho) {
        this.clienteNoCarrinho = clienteNoCarrinho;
    }

    public ProdutoEntity getProdutoNoCarrinho() {
        return produtoNoCarrinho;
    }

    public void setProdutoNoCarrinho(ProdutoEntity produtoNoCarrinho) {
        this.produtoNoCarrinho = produtoNoCarrinho;
    }

    @Override
    public BaseDTO<CarrinhoComprasEntity> build(CarrinhoComprasEntity entidade) {
        this.id = entidade.getId();
        this.produtoNoCarrinho = entidade.getProdutoNoCarrinho();
        this.clienteNoCarrinho = entidade.getClienteNoCarrinho();
        return this;
    }

    @Override
    public List<CarrinhoComprasDTO> createList(List<CarrinhoComprasEntity> listaEntity) {
        List<CarrinhoComprasDTO> array = new ArrayList<CarrinhoComprasDTO>();
        for (CarrinhoComprasEntity detalhes : listaEntity) {
            CarrinhoComprasDTO carrinhoDTO = new CarrinhoComprasDTO();
            carrinhoDTO.build(detalhes);
            array.add(carrinhoDTO);
        }
        return array;
    }

    @Override
    public String toString() {
        return "CarrinhoComprasDTO:: " + this.id + ", " + this.clienteNoCarrinho + ", " + this.produtoNoCarrinho;
    }

}
