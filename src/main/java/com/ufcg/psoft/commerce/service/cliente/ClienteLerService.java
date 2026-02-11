package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.dto.cliente.ClienteResponseDTO;

import java.util.List;

public interface ClienteLerService {
    public ClienteResponseDTO ler(Long id);

    public List<ClienteResponseDTO> ler();
}
