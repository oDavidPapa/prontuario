package com.ufes.prontuario.exception;

public class ArquivoException extends RuntimeException {

    private static final String  MSG = "Falha ao manipular o arquivo. Error: [%s]";

    public ArquivoException(String msg) {
        super(String.format(MSG, msg));
    }
}
