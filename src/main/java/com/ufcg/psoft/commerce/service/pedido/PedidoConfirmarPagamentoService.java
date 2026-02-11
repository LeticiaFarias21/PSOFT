package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

public interface PedidoConfirmarPagamentoService {
    Pedido confirmarPagamento(Long clienteId, Long pedidoId, String metodoPagamento, String codigoAcessoCliente, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);

}
