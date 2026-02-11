package com.ufcg.psoft.commerce.repository;

import com.ufcg.psoft.commerce.model.Associacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssociacaoRepository extends JpaRepository<Associacao, Long> {

    @Query("SELECT a FROM Associacao a WHERE a.entregador = :entregadorId AND a.estabelecimento = :estabelecimentoId")
    Optional<Associacao> findByEntregadorIdAndEstabelecimentoId(@Param("entregadorId") Long entregadorId, @Param("estabelecimentoId") Long estabelecimentoId);

}