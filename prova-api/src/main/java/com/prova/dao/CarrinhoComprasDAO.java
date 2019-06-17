package com.prova.dao;

import com.prova.dto.CarrinhoComprasDTO;
import com.prova.dto.ItemCarrinhoDTO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prova.entity.CarrinhoComprasEntity;
import com.prova.entity.ClienteEntity;
import com.prova.entity.ProdutoEntity;
import com.prova.enums.HttpEnum;
import com.prova.exception.CarrinhoComprasNotFoundException;
import com.prova.exception.ClienteNotFoundException;
import com.prova.repository.CarrinhoComprasRepository;
import com.prova.repository.ClienteRepository;
import com.prova.repository.ProdutoRepository;
import java.util.Objects;

@Controller
public class CarrinhoComprasDAO {

    @Autowired
    CarrinhoComprasRepository repo;

    @Autowired
    ClienteRepository repoCliente;

    @Autowired
    ProdutoRepository repoProduto;

    public Optional<CarrinhoComprasEntity> validarProduto(ItemCarrinhoDTO item) {
        return repo.validarProduto(item.getIdProduto());
    }

    public Optional<CarrinhoComprasEntity> findById(Long id) {
        return repo.findById(id);
    }

    public Optional<CarrinhoComprasEntity> findByIdCliente(Long idCliente) {
        return repo.findByIdCliente(idCliente);
    }

    public void flush() {
        repo.flush();
    }

    public <S extends CarrinhoComprasEntity> S add(ItemCarrinhoDTO item) {
        CarrinhoComprasEntity entity = addNewCart(item);
        if (Objects.nonNull(entity.getId())) {
            return this.update((S) entity);
        } else {
            return this.insert((S) entity);
        }
    }

    private CarrinhoComprasEntity addNewCart(ItemCarrinhoDTO item) {
        CarrinhoComprasEntity entity = new CarrinhoComprasEntity();
        if (validarProduto(item).isPresent()) {
            Optional<ClienteEntity> cliente = repoCliente.findById(item.getIdCliente());
            cliente.orElseThrow(() -> new ClienteNotFoundException());
//            entity.setClienteNoCarrinho(cliente.get());
            Optional<ProdutoEntity> produto = repoProduto.findById(item.getIdProduto());
            
        } else {
            throw new CarrinhoComprasNotFoundException(HttpEnum.MSG_ERRO_PRODUTO_ADD_CARRINHO_MR0500);
        }
        return entity;
    }

    public <S extends CarrinhoComprasEntity> S insert(S entity) {
        entity.setId(null);
        return repo.saveAndFlush(entity);
    }

    public <S extends CarrinhoComprasEntity> S update(S entity) {
        return repo.saveAndFlush(entity);
    }

    public CarrinhoComprasEntity getOne(Long id) {
        return repo.getOne(id);
    }

    public void delete(CarrinhoComprasEntity carrinho) {
        repo.delete(carrinho);
    }

}
