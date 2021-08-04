package com.gabrielbatista.crochedemainha.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.crochedemainha.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Transactional(readOnly=true)
	Optional<Cliente> findByEmail(String email);

	@Transactional(readOnly=true)
	Optional<Cliente> findByCpfOuCnpj(String cpf);

}
