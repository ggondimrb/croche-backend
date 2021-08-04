package com.gabrielbatista.crochedemainha.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbatista.crochedemainha.domain.Adresses;
import com.gabrielbatista.crochedemainha.services.EnderecoService;

@RestController
@RequestMapping(value="/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	@GetMapping("/principal/{clienteId}")
	@ResponseStatus(HttpStatus.OK)
	public Adresses find(@PathVariable Integer clienteId)   {
		Adresses obj = service.find(clienteId);
		return obj;
	}	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Adresses address) {
		service.update(id, address);
	}

}
