package com.ufes.prontuario.exception;

public class UsuarioNaoAutenticadoException extends RuntimeException {

    private static final String  MSG = "Usuário não autorizado";

    public UsuarioNaoAutenticadoException() {
        super(String.format(MSG));
    }
}

