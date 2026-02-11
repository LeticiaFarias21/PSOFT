package com.ufcg.psoft.commerce.service.pedido;

@FunctionalInterface
public interface EstabelecimentoExcluirPedidoService {
    void estabelecimentoExcluir(Long pedidoId, Long estabelecimentoExclusorId);

}
