package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface PedidoCancelarService {
    void cancelar(Long pedidoId, String codigoAcessoCliente);
}
