package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.EntidadeExistenteException;
import com.algaworks.socialbooks.services.exceptions.EntidadeNaoEncontradaException;

@Service
public class AutoresService {

	@Autowired
	private AutoresRepository autoresRepository;
	
	@Autowired
	private LivrosService livrosService;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor){
		if(autor.getId() != null){
			Autor a = autoresRepository.findOne(autor.getId());
			
			if(a != null){
				throw new EntidadeExistenteException("Autor já existe.");
			}
			
		}
		
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id){
		Autor autor = autoresRepository.findOne(id);
		
		if(autor == null){
			throw new EntidadeNaoEncontradaException("Autor não pôde ser encontrado.");
		}
		
		return autor;
	}
	
	public List<Livro> buscarLivros(Long autorId){
		Autor autor = buscar(autorId);
		return autor.getLivros();
	}
	
	public void salvarLivros(Long autorId, Livro livro){
		Autor autor = autoresRepository.findOne(autorId);
		livro.setAutor(autor);
		livrosService.atualizar(livro);
	}
	
}
