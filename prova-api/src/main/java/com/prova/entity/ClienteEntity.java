package com.prova.entity;

import com.prova.dto.ClienteDTO;
import com.prova.interfaces.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "tb_cliente")
public class ClienteEntity implements BaseEntity<ClienteDTO>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_cliente")
    private String nome;

    @Column(name = "ct_email")
    private String email;

    @Column(name = "ct_endereco", nullable = true)
    private String endereco;

    @Column(name = "ct_telefone")
    private String telefone;
    
    @Column(name = "dp_cliente")
    private String cpf;

    @OneToOne(
            fetch = FetchType.EAGER,
            mappedBy = "clienteNoCarrinho", cascade = CascadeType.ALL)
    private CarrinhoComprasEntity carrinhoDoCliente;

    public ClienteEntity() {
        // TODO Auto-generated constructor stub
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

    public CarrinhoComprasEntity getCarrinhoDoCliente() {
        return carrinhoDoCliente;
    }

    public void setCarrinhoDoCliente(CarrinhoComprasEntity carrinhoDoCliente) {
        this.carrinhoDoCliente = carrinhoDoCliente;
    }

    @Override
    public String toString() {
//        return "";
        return "ClienteEntity:: " + this.id + ", " + this.nome + ", " + this.carrinhoDoCliente;
    }

    @Override
    public ClienteEntity build(ClienteDTO dto) {
        this.id = dto.getId();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.endereco = dto.getEndereco();
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        return this;
    }

    public List<ClienteEntity> createList(Set<ClienteDTO> listaDto) {
        List<ClienteEntity> array = new ArrayList<ClienteEntity>();
        for (ClienteDTO dto : listaDto) {
            ClienteEntity entity = new ClienteEntity();
            entity.build(dto);
            array.add(entity);
        }
        return array;
    }

    @Override
    public List<ClienteEntity> createList(List<ClienteDTO> listaDto) {
        List<ClienteEntity> array = new ArrayList<ClienteEntity>();
        for (ClienteDTO item : listaDto) {
            ClienteEntity dto = new ClienteEntity();
            dto.build(item);
            array.add(dto);
        }
        return array;
    }

}
