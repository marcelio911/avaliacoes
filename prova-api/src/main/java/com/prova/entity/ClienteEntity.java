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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_cliente")
    private String nome;

    @Min(value = 5)
    @Column(name = "ct_email")
    private String email;

    @Column(name = "ct_endereco")
    private String endereco;

    @Max(value = 15)
    @Column(name = "ct_telefone")
    private String telefone;

    @Column(name = "dp_cliente")
    private String cpf;

    @OneToMany(mappedBy = "clienteNoCarrinho", cascade = CascadeType.ALL)
    private Set<CarrinhoComprasEntity> carrinhoDoCliente;

    public ClienteEntity() {
        // TODO Auto-generated constructor stub
    }

    public ClienteEntity(CarrinhoComprasEntity... carrinho) {
        this.carrinhoDoCliente = Stream.of(carrinho).collect(Collectors.toSet());
        this.carrinhoDoCliente.forEach(x -> x.setClienteNoCarrinho(this));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<CarrinhoComprasEntity> getCarrinhoDoCliente() {
        return carrinhoDoCliente;
    }

    public void setCarrinhoDoCliente(Set<CarrinhoComprasEntity> carrinhoDoCliente) {
        this.carrinhoDoCliente = carrinhoDoCliente;
    }

    @Override
    public String toString() {
        return "ClienteEntity:: " + this.id + ", " + this.nome + ", " + this.carrinhoDoCliente;
    }

}
