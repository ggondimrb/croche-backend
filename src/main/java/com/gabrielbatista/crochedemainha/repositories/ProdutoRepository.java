package com.gabrielbatista.crochedemainha.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielbatista.crochedemainha.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categoria cat WHERE upper(obj.nome) LIKE %:nome% AND upper(cat.nome) LIKE %:categoria%")
	Page<Produto> search(String nome, String categoria, Pageable pageRequest);
	
}
