package com.prova.entity;

import com.prova.dto.CarrinhoComprasDTO;
import com.prova.interfaces.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_carrinho_compras")
public class CarrinhoComprasEntity implements BaseEntity<CarrinhoComprasDTO>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_cliente_no_carrinho", nullable = false)
    private ClienteEntity clienteNoCarrinho;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "items_carrinho",
            joinColumns = { @JoinColumn(name = "carrinho_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_carrinho_id") })
    private Set<ItensCarrinhoEntity> itemsNoCarrinho = new HashSet<>();
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ultimaModificacao")
    private Date ultimaModificacao = new Date();

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

    public Set<ItensCarrinhoEntity> getItemsNoCarrinho() {
        return itemsNoCarrinho;
    }

    public void setItemsNoCarrinho(Set<ItensCarrinhoEntity> itemsNoCarrinho) {
        this.itemsNoCarrinho = itemsNoCarrinho;
    }

    @Override
    public CarrinhoComprasEntity build(CarrinhoComprasDTO dto) {
        this.id = dto.getId();
        this.clienteNoCarrinho = new ClienteEntity().build(dto.getClienteNoCarrinho());
        // TODO Implementar
//        this.itemsNoCarrinho = new ProdutoEntity().createList(dto.getItems());
        return this;
    }

    @Override
    public List<CarrinhoComprasEntity> createList(List<CarrinhoComprasDTO> listaDto) {
        List<CarrinhoComprasEntity> array = new ArrayList<CarrinhoComprasEntity>();
        for (CarrinhoComprasDTO item : listaDto) {
            CarrinhoComprasEntity carrinhoComprasEntity = new CarrinhoComprasEntity();
            carrinhoComprasEntity.build(item);
            array.add(carrinhoComprasEntity);
        }
        return array;
    }
    public Set<CarrinhoComprasEntity> createList(Set<CarrinhoComprasDTO> listaDto) {
        Set<CarrinhoComprasEntity> array = new HashSet<CarrinhoComprasEntity>();
        for (CarrinhoComprasDTO item : listaDto) {
            CarrinhoComprasEntity carrinhoComprasEntity = new CarrinhoComprasEntity();
            carrinhoComprasEntity.build(item);
            array.add(carrinhoComprasEntity);
        }
        return array;
    }

    @Override
    public String toString() {
//        return "";
        return "CarrinhoComprasEntity:: " + this.id + ", " + this.clienteNoCarrinho + ", " + this.itemsNoCarrinho;
    }

}
