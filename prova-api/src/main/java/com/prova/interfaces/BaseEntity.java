package com.prova.interfaces;

import java.util.List;

import com.prova.dto.BaseDTO;

/**
 *
 * @author marcelio
 */
public interface BaseEntity {
	
	public BaseDTO objeto(BaseEntity entidade);
    
	public List<BaseDTO> lista(List<BaseEntity> listaEntity);
}
