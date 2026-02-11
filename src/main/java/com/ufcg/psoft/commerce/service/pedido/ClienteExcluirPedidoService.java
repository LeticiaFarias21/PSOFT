package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface ClienteExcluirPedidoService {
    void clienteExcluir(Long pedidoId, Long clienteExclusorId);
}
