package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorGetResponseDTO;
import com.ufcg.psoft.commerce.dto.entregador.EntregadorPostPutRequestDTO;

@FunctionalInterface
public interface EntregadorAtualizarService {
    EntregadorGetResponseDTO atualizar(Long id, EntregadorPostPutRequestDTO entregador);
}