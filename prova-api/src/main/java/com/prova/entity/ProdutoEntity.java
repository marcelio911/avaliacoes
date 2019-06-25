package com.prova.entity;

import com.prova.dto.ClienteDTO;
import com.prova.dto.ProdutoDTO;
import com.prova.interfaces.BaseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_produto")
public class ProdutoEntity implements BaseEntity<ProdutoDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do produto é obrigatório")
    @Column(name = "ds_produto")
    private String descricao;

    @NotNull(message = "Por favor informar o valor do produto")
    @Column(name = "vl_produto")
    private BigDecimal valor;

    // @NotNull(message = "Por favor insira uma imagem para o produto")
    @Column(name = "im_miniatura_produto")
    private String miniatura;

    @OneToMany(mappedBy = "produtosNoCarrinho", cascade = CascadeType.ALL)
    private Set<ItensCarrinhoEntity> itensNoCarrinho;

    public ProdutoEntity() {

    }

    public ProdutoEntity(ItensCarrinhoEntity... itensCarrinho) {
        this.itensNoCarrinho = Stream.of(itensCarrinho).collect(Collectors.toSet());
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

    public Set<ItensCarrinhoEntity> getItensNoCarrinho() {
        return itensNoCarrinho;
    }

    public void setItensNoCarrinho(Set<ItensCarrinhoEntity> itensNoCarrinho) {
        this.itensNoCarrinho = itensNoCarrinho;
    }

    @Override
    public String toString() {
        return "ProdutoEntity:: " + this.id + ", " + this.descricao + ", " + this.valor;
    }

    @Override
    public ProdutoEntity build(ProdutoDTO dto) {
        this.id = dto.getId();
        this.descricao = dto.getDescricao();
        this.valor = dto.getValor();
        this.miniatura = dto.getMiniatura();
        return this;
    }

    @Override
    public List<ProdutoEntity> createList(List<ProdutoDTO> listaDto) {
        List<ProdutoEntity> array = new ArrayList<ProdutoEntity>();
        for (ProdutoDTO item : listaDto) {
            ProdutoEntity dto = new ProdutoEntity();
            dto.build(item);
            array.add(dto);
        }
        return array;
    }

}
