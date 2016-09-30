package com.algaworks.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.ComentariosRepository;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.exceptions.EntidadeNaoEncontradaException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	private ComentariosRepository comentariosRepository;
	
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	public Livro buscar(Long id){
		Livro livro = livrosRepository.findOne(id);
		
		if(livro == null){
			throw new EntidadeNaoEncontradaException("O livro não pôde ser encontrado");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro){
		livro.setId(null);
		return livrosRepository.save(livro);
	}
	
	public void deletar(Long id){
		try{
			livrosRepository.delete(id);
		}catch(EmptyResultDataAccessException ed){
			throw new EntidadeNaoEncontradaException("O livro não pôde ser encontrado.");
		}
	}
	
	public void atualizar(Livro livro){
		verificarExistencia(livro);
		livrosRepository.save(livro);
	}
	
	private void verificarExistencia(Livro livro){
		buscar(livro.getId());
	}
	
	public Comentario salvarComentario(Long livroId, Comentario comentario){
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		
		return comentariosRepository.save(comentario);
	}
	
	public List<Comentario> listarComentarios(Long livroId){
		Livro livro = buscar(livroId);
		return livro.getComentarios();
	}
	
}
