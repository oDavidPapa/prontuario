package com.ufes.prontuario.dto.resultadoexame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResultadoExameCadastroDTO {

    private String descricao;
    private Long idExame;
    private Long idArquivo;
}
