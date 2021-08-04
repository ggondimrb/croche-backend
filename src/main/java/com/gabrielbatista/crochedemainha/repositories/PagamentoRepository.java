package com.gabrielbatista.crochedemainha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielbatista.crochedemainha.domain.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento , Integer> {

}
