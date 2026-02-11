package com.ufcg.psoft.commerce.service.associacao;

import com.ufcg.psoft.commerce.model.Associacao;

@FunctionalInterface
public interface AssociacaoAprovarService {
    Associacao aprovar(Long entregadorId, Long estabelecimentoId, String codigoAcesso);
}
