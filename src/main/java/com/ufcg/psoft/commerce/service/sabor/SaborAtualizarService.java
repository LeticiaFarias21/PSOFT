package com.ufcg.psoft.commerce.service.sabor;

import com.ufcg.psoft.commerce.dto.sabor.SaborPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Sabor;

public interface SaborAtualizarService {
    Sabor atualizar(Long id, SaborPostPutRequestDTO saborPostPutRequestDTO);
}
