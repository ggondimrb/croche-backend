package com.gabrielbatista.crochedemainha.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielbatista.crochedemainha.domain.Order;
import com.gabrielbatista.crochedemainha.dto.OrderDTO;
import com.gabrielbatista.crochedemainha.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public OrderDTO find(@PathVariable Integer id)   {
		OrderDTO obj = service.find(id);
		return obj;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void insert(@Valid @RequestBody Order obj) { // @valid para validar atraves da DTO
		obj = service.insert(obj);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<OrderDTO> findPage(
			// parametros opcionais - requestPAram
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="instant") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction)   {

		Page<OrderDTO> list = service.findPage(page, linesPerPage, orderBy, direction);		
		return list;	
	}
}
