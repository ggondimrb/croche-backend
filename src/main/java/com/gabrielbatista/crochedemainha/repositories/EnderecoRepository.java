package com.gabrielbatista.crochedemainha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbatista.crochedemainha.domain.Adresses;

@Repository
public interface EnderecoRepository extends JpaRepository<Adresses, Integer> {

}
