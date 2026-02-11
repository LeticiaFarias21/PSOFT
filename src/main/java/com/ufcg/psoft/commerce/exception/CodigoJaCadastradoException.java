package com.ufcg.psoft.commerce.exception;

public class CodigoJaCadastradoException extends CommerceException{

        public CodigoJaCadastradoException() {
            super("Código já cadastrado");
        }
}
