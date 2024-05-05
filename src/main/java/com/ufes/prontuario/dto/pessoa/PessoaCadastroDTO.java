package com.ufes.prontuario.dto.pessoa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PessoaCadastroDTO {

    private String nome;
    private String cpf;
    private char sexo;
    private LocalDate dataNascimento;
    private Long idContato;
    private Long idUsuario;
}
