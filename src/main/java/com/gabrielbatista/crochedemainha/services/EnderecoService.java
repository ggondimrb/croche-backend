package com.gabrielbatista.crochedemainha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielbatista.crochedemainha.domain.Adresses;
import com.gabrielbatista.crochedemainha.domain.enums.Perfil;
import com.gabrielbatista.crochedemainha.repositories.EnderecoRepository;
import com.gabrielbatista.crochedemainha.security.UserSS;
import com.gabrielbatista.crochedemainha.services.exceptions.AuthorizationException;
import com.gabrielbatista.crochedemainha.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {	
	
	@Autowired
	private EnderecoRepository repo;	
	
	public Adresses find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		Optional<Adresses> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Adresses.class.getName()));
				
	}	
	
	public List<Adresses> findAll() {
		return repo.findAll();
	}

	public void update(Integer id, Adresses newObj) {
		Optional<Adresses> obj = repo.findById(id);
		obj.get().setCep(newObj.getCep());
		obj.get().setNum(newObj.getNum());
		obj.get().setComplement(newObj.getComplement());
		obj.get().setStreet(newObj.getStreet());
		obj.get().setDistrict(newObj.getDistrict());
		repo.save(obj.get());
		
	}
	
}
