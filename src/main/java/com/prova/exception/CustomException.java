/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prova.exception;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.http.HttpStatus;

import com.prova.enums.HttpEnum;


/**
 *
 * @author marcelio
 */
@XmlTransient
public abstract class CustomException extends RuntimeException {

    protected String mensagemCustimzada;
    protected String codigoResponse;
    protected HttpStatus status;

    public CustomException() {
    	super();
    }

    public CustomException(HttpEnum resposta, HttpStatus status) {
        this.codigoResponse = resposta.getCodigo();
        this.mensagemCustimzada = resposta.getMsg();
        this.status = status;
    }
    
    public CustomException(String resposta, HttpStatus status) {
        this.codigoResponse = status.toString();
        this.mensagemCustimzada = resposta;
        this.status = status;
    }
    
    public void setResposta(HttpEnum resposta) {
        this.codigoResponse = resposta.getCodigo();
        this.mensagemCustimzada = resposta.getMsg();
    }

	public String getMensagemCustimzada() {
		return mensagemCustimzada;
	}

	public void setMensagemCustimzada(String mensagemCustimzada) {
		this.mensagemCustimzada = mensagemCustimzada;
	}

	public String getCodigoResponse() {
		return codigoResponse;
	}

	public void setCodigoResponse(String codigoResponse) {
		this.codigoResponse = codigoResponse;
	}
    
}
