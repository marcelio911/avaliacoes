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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_cliente_no_carrinho")
    private ClienteEntity clienteNoCarrinho;

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "CarrinhoComprasEntity:: " + this.id + ", " + this.clienteNoCarrinho + ", " + this.produtoNoCarrinho;
    }

}
