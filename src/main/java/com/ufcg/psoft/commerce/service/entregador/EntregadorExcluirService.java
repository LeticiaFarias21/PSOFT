package com.ufcg.psoft.commerce.service.entregador;

@FunctionalInterface
public interface EntregadorExcluirService {
    void excluir(Long id, String codigoDeAcesso);
}
