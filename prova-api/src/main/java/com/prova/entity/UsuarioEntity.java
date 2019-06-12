package com.prova.entity;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nm_usuario")
	private String nome;
	
	@Column(name = "ct_endereco")
	private String endereco;
	
	@Column(name = "ct_telefone")
	private String telefone;

	@OneToMany(mappedBy = "usuarioNoCarrinho", cascade = CascadeType.ALL)
	private Set<CarrinhoComprasEntity> carrinhoDoUsuario;

	public UsuarioEntity() {
		// TODO Auto-generated constructor stub
	}

	public UsuarioEntity(CarrinhoComprasEntity... carrinho) {
		this.carrinhoDoUsuario = Stream.of(carrinho).collect(Collectors.toSet());
		this.carrinhoDoUsuario.forEach(x -> x.setUsuarioNoCarrinho(this));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ProdutoEntity:: " + this.id + ", " + this.nome + ", " + this.carrinhoDoUsuario;
	}

}