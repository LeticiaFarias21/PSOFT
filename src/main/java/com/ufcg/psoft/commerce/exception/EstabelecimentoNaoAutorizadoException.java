package com.ufcg.psoft.commerce.exception;

public class EstabelecimentoNaoAutorizadoException extends CommerceException{
    public EstabelecimentoNaoAutorizadoException() {
        super("O estabelecimento nao possui permissao para alterar dados de outro estabelecimento");
    }
}
