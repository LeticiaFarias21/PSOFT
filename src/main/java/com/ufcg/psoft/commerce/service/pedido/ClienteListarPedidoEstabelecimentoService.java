package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoGetRequestDTO;

import java.util.List;

@FunctionalInterface
public interface ClienteListarPedidoEstabelecimentoService {
    List<PedidoGetRequestDTO> listar(Long pedidoId, Long clienteId, Long estabelecimentoId, String codigoAcesso, String status);
}
