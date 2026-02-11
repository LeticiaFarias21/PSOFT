package com.ufcg.psoft.commerce.service.pedido;

import com.ufcg.psoft.commerce.dto.pedido.PedidoPostPutRequestDTO;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface EstabelecimentoAssociarPedidoEntregadorService {
    Pedido associar(Long pedidoId, Long entregadorId, Long estabelecimentoId, String codigoAcessoEstabelecimento, PedidoPostPutRequestDTO pedidoPostPutRequestDTO);
}
