package com.ufcg.psoft.commerce.service.pedido;

import java.util.List;
import com.ufcg.psoft.commerce.model.Pedido;

@FunctionalInterface
public interface EstabelecimentoLerPedidoService {
    List<Pedido> estabelecimentoListar(Long pedidoId, Long estabelecimentoListadorId);

}
