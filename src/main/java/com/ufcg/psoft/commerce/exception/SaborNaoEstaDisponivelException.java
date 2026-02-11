package com.ufcg.psoft.commerce.exception;

public class SaborNaoEstaDisponivelException extends CommerceException{
    public SaborNaoEstaDisponivelException() {
        super("O sabor consultado nao esta disponivel!");
    }

}
