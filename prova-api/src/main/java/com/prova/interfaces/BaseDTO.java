package com.prova.interfaces;

import java.util.List;

/**
 *
 * @author marcelio
 */
public interface BaseDTO<T> {

    public BaseDTO<T> build(T entidade);

    public List<?> createList(List<T> listaEntity);
}
