package com.gabrielbatista.crochedemainha.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielbatista.crochedemainha.domain.Produto;
import com.gabrielbatista.crochedemainha.dto.ProdutoDTO;
import com.gabrielbatista.crochedemainha.resources.utils.URL;
import com.gabrielbatista.crochedemainha.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProdutoDTO find(@PathVariable Integer id)   {
		Produto obj = service.buscar(id);
		ProdutoDTO objDto = new ProdutoDTO(obj);
		return objDto;

	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<ProdutoDTO> findPage(
			// parametros opcionais - requestPAram
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categoria", defaultValue="") String categoria,
			@RequestParam(value="page", defaultValue="1") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="12") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction)   {
		String nomeDecoded = URL.decodeParam(nome);
		Page<Produto> list = service.search(nomeDecoded, categoria, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return listDto;	
	}

	@PostMapping("/{picture}")
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadProfilePicture(
			@RequestParam(name="id") Integer id, 
			@RequestParam(name="file") MultipartFile file) {
		service.uploadProductPicture(id,file);
	}
}
