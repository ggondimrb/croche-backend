package com.gabrielbatista.crochedemainha.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.dto.ClienteDTO;
import com.gabrielbatista.crochedemainha.dto.ClienteNewDTO;
import com.gabrielbatista.crochedemainha.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente find(@PathVariable String id)   {
		Cliente obj = service.find(id);
		return obj;
	}
	
//	@GetMapping("/{email}")
//	@ResponseStatus(HttpStatus.OK)
//	public Cliente find(@RequestParam(value="value") String email)   {
//		Cliente obj = service.findByEmail(email);
//		return obj;
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void insert(@Valid @RequestBody ClienteNewDTO objDto) { // @valid para validar atraves da DTO
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ClienteDTO> findAll() {
		List<Cliente> list = service.findAll();
		// list retorna todos os registros filhos de Cliente
		// convertendo para DTO -Data Transfer Object - objeto apenas com os dados necessarios 
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return listDto;	
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
//	@GetMapping("/{page}")
//	@ResponseStatus(HttpStatus.OK)
//	public Page<ClienteDTO> findPage(
//			// parametros opcionais - requestPAram
//			@RequestParam(value="page", defaultValue="0") Integer page,
//			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
//			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
//			@RequestParam(value="direction", defaultValue="ASC") String direction)   {
//
//		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
//		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
//		return listDto;	
//	}
	
	@PostMapping("/{picture}")
	@ResponseStatus(HttpStatus.CREATED)
	public void uploadProfilePicture(@RequestParam(name="file") MultipartFile file) { // @valid para validar atraves da DTO
		service.uploadProfilePicture(file);
	}
}
