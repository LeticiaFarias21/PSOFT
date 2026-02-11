package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;

@FunctionalInterface
public interface ClienteCriarService {
    public Cliente criar(ClientePostPutRequestDTO clientePostPutRequestDTO);
}
