package com.prova.dto;

import com.prova.entity.ClienteEntity;
import com.prova.interfaces.BaseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClienteDTO implements BaseDTO<ClienteEntity> {

    private Long id;

    private String nome;

    private String email;

    private String endereco;

    private String telefone;

    private String cpf;

    private Set<CarrinhoComprasDTO> carrinhoDoCliente;

    public ClienteDTO() {
        // TODO Auto-generated constructor stub
    }

    public ClienteDTO(CarrinhoComprasDTO... carrinho) {
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

    public Set<CarrinhoComprasDTO> getCarrinhoDoCliente() {
        return carrinhoDoCliente;
    }

    public void setCarrinhoDoCliente(Set<CarrinhoComprasDTO> carrinhoDoCliente) {
        this.carrinhoDoCliente = carrinhoDoCliente;
    }

    @Override
    public ClienteDTO build(ClienteEntity entidade) {
        this.id = entidade.getId();
        this.cpf = entidade.getCpf();
        this.email = entidade.getEmail();
        this.endereco = entidade.getEndereco();
        this.nome = entidade.getNome();
        this.telefone = entidade.getTelefone();
        return this;
    }

    @Override
    public List<ClienteDTO> createList(List<ClienteEntity> listaEntity) {
        List<ClienteDTO> array = new ArrayList<ClienteDTO>();
        for (ClienteEntity item : listaEntity) {
            ClienteDTO dto = new ClienteDTO();
            dto.build(item);
            array.add(dto);
        }
        return array;
    }

    @Override
    public String toString() {
        return "ClienteDTO:: " + this.id + ", " + this.nome + ", " + this.carrinhoDoCliente;
    }

}
