package com.prova.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "O nome do produto é obrigatório")
	@Column(name = "ds_produto")
	private String descricao;
	
	@NotNull(message = "Por favor informar o valor do produto")
	@Column(name = "vl_produto")
	private BigDecimal valor;
	
	@NotNull(message = "Por favor insira uma imagem para o produto")
	@Column(name = "im_miniatura_produto")
	private String miniatura;
	
	public ProdutoEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public String getMiniatura() {
		return miniatura;
	}

	public void setMiniatura(String miniatura) {
		this.miniatura = miniatura;
	}

	@Override
	public String toString() {
		return "ProdutoEntity:: " + this.id + ", " + this.descricao + ", " + this.valor ;
	}
	
}
