package com.ufcg.psoft.commerce.service.estabelecimento;

import com.ufcg.psoft.commerce.dto.estabelecimento.EstabelecimentoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Estabelecimento;

public interface EstabelecimentoAtualizarService {
    Estabelecimento atualizar(Long id, EstabelecimentoPostPutRequestDTO estabelecimentoPostPutRequestDTO);

    Estabelecimento alterarDisponibilidade(Long id, String codigoDeAcesso, Boolean disponibilidade, Long saborId);
}
