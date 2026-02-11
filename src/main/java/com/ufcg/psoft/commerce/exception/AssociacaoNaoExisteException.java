package com.ufcg.psoft.commerce.exception;

public class AssociacaoNaoExisteException extends RuntimeException {
    public AssociacaoNaoExisteException() {
        super("A associacao consultada nao existe!");
    }
}
