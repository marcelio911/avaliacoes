package com.prova.dto;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.interfaces.BaseDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrinhoComprasDTO implements BaseDTO<CarrinhoComprasEntity> {

    private Long id;

    private ClienteDTO clienteNoCarrinho;

    private List<ItemCarrinhoDTO> itemsNoCarrinho;

    public CarrinhoComprasDTO() {
        // TODO Auto-generated constructor stub
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getClienteNoCarrinho() {
        return clienteNoCarrinho;
    }

    public void setClienteNoCarrinho(ClienteDTO clienteNoCarrinho) {
        this.clienteNoCarrinho = clienteNoCarrinho;
    }

    public List<ItemCarrinhoDTO> getItemsNoCarrinho() {
        return itemsNoCarrinho;
    }

    public void setItemsNoCarrinho(List<ItemCarrinhoDTO> itemsNoCarrinho) {
        this.itemsNoCarrinho = itemsNoCarrinho;
    }

    @Override
    public CarrinhoComprasDTO build(CarrinhoComprasEntity entidade) {
        this.id = entidade.getId();
//        this.clienteNoCarrinho = new ClienteDTO().build(entidade.getClienteNoCarrinho());
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

    public Set<CarrinhoComprasDTO> createList(Set<CarrinhoComprasEntity> listaEntity) {
        Set<CarrinhoComprasDTO> array = new HashSet<CarrinhoComprasDTO>();
        for (CarrinhoComprasEntity detalhes : listaEntity) {
            CarrinhoComprasDTO carrinhoDTO = new CarrinhoComprasDTO();
            carrinhoDTO.build(detalhes);
            array.add(carrinhoDTO);
        }
        return array;
    }

    @Override
    public String toString() {
        return "CarrinhoComprasDTO:: " + this.id + ", " + this.clienteNoCarrinho + ", " + this.itemsNoCarrinho;
    }

}
