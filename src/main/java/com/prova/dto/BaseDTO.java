package com.prova.dto;

import java.util.List;

/**
 *
 * @author marcelio
 */
public interface BaseDTO<T> {
	
	public BaseDTO build(T entidade);
    
	public List<?> createList(List<T> listaEntity);
}
