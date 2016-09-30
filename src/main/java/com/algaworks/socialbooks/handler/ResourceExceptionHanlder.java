package com.algaworks.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algaworks.socialbooks.domain.DetalhesErro;
import com.algaworks.socialbooks.services.exceptions.EntidadeExistenteException;
import com.algaworks.socialbooks.services.exceptions.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ResourceExceptionHanlder {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontrado
							(EntidadeNaoEncontradaException e, HttpServletRequest http){
		
		HttpStatus statusErro = HttpStatus.NOT_FOUND;
				
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(Long.valueOf(statusErro.value()));
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/" + statusErro);
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(statusErro).body(erro);
	}
	
	@ExceptionHandler(EntidadeExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistente
							(EntidadeExistenteException e, HttpServletRequest http){

		HttpStatus statusErro = HttpStatus.CONFLICT;
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(Long.valueOf(statusErro.value()));
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/" + statusErro);
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(statusErro).body(erro);
	}		
	
}
