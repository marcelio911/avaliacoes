package com.prova.response;

import javax.xml.bind.annotation.XmlTransient;

import org.springframework.http.HttpStatus;

import com.prova.enums.HttpEnum;


/**
 *
 * @author marcelio
 */
@XmlTransient
public abstract class CustomResponse {

    protected String mensagemCustimzada;
    protected String codigoResponse;
    protected HttpStatus status;

    public CustomResponse() {

    }

    public CustomResponse(HttpEnum resposta, HttpStatus status) {
        this.codigoResponse = resposta.getCodigo();
        this.mensagemCustimzada = resposta.getMsg();
        this.status = status;
    }
    
    public CustomResponse(String resposta, HttpStatus status) {
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
