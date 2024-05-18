package com.ufes.prontuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleProductNotFoundException(RecursoNaoEncontradoException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UsuarioNaoAutenticadoException.class)
    public ResponseEntity<String> handleProductNotFoundException(UsuarioNaoAutenticadoException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleProductNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro Inesperado: " + e.getMessage());
    }
}
