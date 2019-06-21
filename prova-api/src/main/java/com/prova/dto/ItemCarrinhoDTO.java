package com.prova.dto;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ItensCarrinhoEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@XmlRootElement
public class ItemCarrinhoDTO {
    
    @XmlTransient
    private Long idCliente;
    @XmlTransient
    private Long idProduto;
    
    private ClienteDTO cliente;
    private ProdutoDTO produto;
    private Integer quantidade;

    public ItemCarrinhoDTO() {
    }

    public ItemCarrinhoDTO(ItemCarrinhoDTO novo) {
        this.idCliente = novo.idCliente;
        this.idProduto = novo.idProduto;
        this.produto = novo.produto;
        this.cliente = novo.cliente;
        this.quantidade = novo.quantidade;
    }

    public ItemCarrinhoDTO build(ItensCarrinhoEntity novo) {
        this.idProduto = novo.getProdutosNoCarrinho().getId();
        this.produto = new ProdutoDTO().build(novo.getProdutosNoCarrinho());
        this.quantidade = novo.getQuantidade();
        return this;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Set<ItemCarrinhoDTO> createList(Set<ItensCarrinhoEntity> itensNoCarrinho) {
        Set<ItemCarrinhoDTO> itens = new HashSet<ItemCarrinhoDTO>();
        //TODO implementar
        for (ItensCarrinhoEntity carrinho : itensNoCarrinho) {
            ItemCarrinhoDTO item = new ItemCarrinhoDTO();
            item.idProduto = carrinho.getProdutosNoCarrinho().getId();
            item.produto = new ProdutoDTO().build(carrinho.getProdutosNoCarrinho());
            item.quantidade = carrinho.getQuantidade();
            itens.add(item);
        }
        return itens;
    }

}
