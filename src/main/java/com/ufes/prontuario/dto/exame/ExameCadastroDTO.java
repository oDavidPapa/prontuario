package com.ufes.prontuario.dto.exame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExameCadastroDTO {

    private Long id;
    private String descricao;
    private Long idConsulta;
}
