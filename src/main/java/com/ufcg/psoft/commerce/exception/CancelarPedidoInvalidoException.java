package com.ufcg.psoft.commerce.exception;

public class CancelarPedidoInvalidoException extends CommerceException{
    public CancelarPedidoInvalidoException() {
        super("Pedidos que ja estao prontos nao podem ser cancelados!");
    }
}
