package com.prova.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_carrinho_compras")
public class CarrinhoComprasEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Integer quantidade;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario_no_carrinho")
	private UsuarioEntity usuarioNoCarrinho;
	
	@ManyToOne
    @JoinColumn(name = "id_produto_no_carrinho")
    private ProdutoEntity produtoNoCarrinho;
	
	
	public CarrinhoComprasEntity() {
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
	public String toString() {
		return "ProdutoEntity:: " + this.id + ", " + this.usuarioNoCarrinho + ", " + this.produtoNoCarrinho ;
	}
	
}
