package com.gabrielbatista.crochedemainha.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.crochedemainha.domain.Cliente;
import com.gabrielbatista.crochedemainha.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Transactional(readOnly=true)
	Page<Order> findByCliente(Cliente cliente, Pageable pageRequest);

}
