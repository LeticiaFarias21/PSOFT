package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface PedidoNotificarClienteService {
    String notificar(Long pedidoId);
}
