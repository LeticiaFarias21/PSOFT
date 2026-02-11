package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.model.Pedido;
import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;

@FunctionalInterface
public interface PedidoAlterarService {
    Pedido alterar(Long pedidoId, String codigoAcesso, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);

}
