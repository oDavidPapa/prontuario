package com.ufes.prontuario.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    private static final String  MSG = "Recurso não encontrado: [Entidade: %s, Id: %d]";

    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(String.format(MSG, recurso, id));
    }
}
