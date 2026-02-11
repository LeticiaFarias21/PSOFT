package com.ufcg.psoft.commerce.service.cliente;

import com.ufcg.psoft.commerce.model.Cliente;
import com.ufcg.psoft.commerce.dto.cliente.ClientePostPutRequestDTO;
public interface ClienteAtualizarService {
    public Cliente atualizar(Long id, String codigoAcesso, ClientePostPutRequestDTO clientePostPutRequestDTO);
}
