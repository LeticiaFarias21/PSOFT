package com.ufcg.psoft.commerce.service.estabelecimento;

@FunctionalInterface
public interface EstabelecimentoNotificaClienteService {
    String notificar(Long pedidoId);
}
