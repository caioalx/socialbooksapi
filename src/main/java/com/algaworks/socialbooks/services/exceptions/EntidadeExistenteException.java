package com.algaworks.socialbooks.services.exceptions;

public class EntidadeExistenteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeExistenteException(String mensagem){
		super(mensagem);
	}
	
	public EntidadeExistenteException(String mensagem, Throwable causa){
		super(mensagem, causa);
	}
	
}
