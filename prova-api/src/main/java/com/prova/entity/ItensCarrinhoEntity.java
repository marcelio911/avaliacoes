/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.entity;

import com.prova.dto.ItemCarrinhoDTO;
import com.prova.interfaces.BaseEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "tb_carrinho_itens")
public class ItensCarrinhoEntity implements BaseEntity<ItemCarrinhoDTO>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_produto_no_carrinho")
    private ProdutoEntity produtosNoCarrinho;
    
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "itemsNoCarrinho")
    private Set<CarrinhoComprasEntity> carrinhos = new HashSet<>();

    @Column
    @NotNull
//    @Size(max = 100)
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Set<CarrinhoComprasEntity> getCarrinhos() {
        return carrinhos;
    }

    public void setCarrinhos(Set<CarrinhoComprasEntity> carrinhos) {
        this.carrinhos = carrinhos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdutoEntity getProdutosNoCarrinho() {
        return produtosNoCarrinho;
    }

    public void setProdutosNoCarrinho(ProdutoEntity produtosNoCarrinho) {
        this.produtosNoCarrinho = produtosNoCarrinho;
    }
    
    @Override
    public ItensCarrinhoEntity build(ItemCarrinhoDTO dto) {
        this.produtosNoCarrinho = new ProdutoEntity().build(dto.getProduto());
        this.quantidade = dto.getQuantidade();
        return this;
    }

    @Override
    public List<ItensCarrinhoEntity> createList(List<ItemCarrinhoDTO> listaDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "id" + this.id + ", produtosNoCarrinho" + this.produtosNoCarrinho + ", quantidade: " + this.quantidade ;
    }
    
    

}
