package com.prova.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prova.entity.ProdutoEntity;

public class ProdutoDTO implements BaseDTO<ProdutoEntity> {

    private Long id;

    private String descricao;

    private BigDecimal valor;

    public ProdutoDTO() {
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

    @Override
    public BaseDTO<ProdutoEntity> build(ProdutoEntity entidade) {
        this.id = entidade.getId();
        this.descricao = entidade.getDescricao();
        this.valor = entidade.getValor();
        return this;
    }

    @Override
    public List<ProdutoDTO> createList(List<ProdutoEntity> listaEntity) {
        List<ProdutoDTO> array = new ArrayList<ProdutoDTO>();
        for (ProdutoEntity detalhes : listaEntity) {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.build(detalhes);
            array.add(produtoDTO);
        }
        return array;
    }

    @Override
    public String toString() {
        return "ProdutoDTO:: " + this.id + ", " + this.descricao + ", " + this.valor;
    }

}
