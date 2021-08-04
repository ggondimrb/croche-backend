package com.gabrielbatista.crochedemainha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbatista.crochedemainha.domain.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

}
