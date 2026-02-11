package com.ufcg.psoft.commerce.exception;


public class SaborDisponivelException extends CommerceException{
    public SaborDisponivelException() {
        super("O sabor consultado ja esta disponivel!");
    }
}
