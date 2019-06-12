package com.prova.dto;

import java.util.ArrayList;
import java.util.List;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ProdutoEntity;
import com.prova.entity.UsuarioEntity;

public class CarrinhoComprasDTO implements BaseDTO<CarrinhoComprasEntity>{

	private Long id;
	
	private UsuarioEntity usuarioNoCarrinho;
	
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
	
	public UsuarioEntity getUsuarioNoCarrinho() {
		return usuarioNoCarrinho;
	}

	public void setUsuarioNoCarrinho(UsuarioEntity usuarioNoCarrinho) {
		this.usuarioNoCarrinho = usuarioNoCarrinho;
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
		this.usuarioNoCarrinho = entidade.getUsuarioNoCarrinho();
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
		return "ProdutoEntity:: " + this.id + ", " + this.usuarioNoCarrinho + ", " + this.produtoNoCarrinho ;
	}
	
}
