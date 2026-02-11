package com.ufcg.psoft.commerce.service.entregador;

import com.ufcg.psoft.commerce.dto.entregador.EntregadorGetResponseDTO;
import java.util.List;

@FunctionalInterface
public interface EntregadorLerService {
    List<EntregadorGetResponseDTO> ler(Long id);
}
