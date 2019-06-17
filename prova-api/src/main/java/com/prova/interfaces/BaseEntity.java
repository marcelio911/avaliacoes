package com.prova.interfaces;

import java.util.List;


/**
 *
 * @author marcelio
 */
public interface BaseEntity<T> {
	
	public BaseEntity build(T dto);
    
	public List<?> createList(List<T> listaDto);
}
