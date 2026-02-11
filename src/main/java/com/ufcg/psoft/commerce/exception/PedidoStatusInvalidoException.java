package com.ufcg.psoft.commerce.exception;

public class PedidoStatusInvalidoException extends CommerceException{
    public PedidoStatusInvalidoException() {
        super("O pedido consultado nao existe!");
    }
}
