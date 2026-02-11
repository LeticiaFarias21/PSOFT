package com.ufcg.psoft.commerce.exception;

public class EntregadorNaoAutorizadoException extends CommerceException{
    public EntregadorNaoAutorizadoException() {
        super("O entregador nao possui permissao para alterar dados de outro entregador");
    }
}
