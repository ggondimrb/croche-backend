package com.gabrielbatista.crochedemainha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabrielbatista.crochedemainha.domain.Imagem;
import com.gabrielbatista.crochedemainha.repositories.ImagemRepository;
import com.gabrielbatista.crochedemainha.services.exceptions.DataIntegrityException;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class ImagemService {
	
	@Autowired
	private ImagemRepository repo;
	
	public Imagem find(Integer id) {
		Optional<Imagem> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Imagem.class.getName()));
				
	}

	public Imagem insert(Imagem obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel excluir uma Imagem que possui produtos associados");
		}
	}
	
}
