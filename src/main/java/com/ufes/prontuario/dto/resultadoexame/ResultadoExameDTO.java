package com.ufes.prontuario.dto.resultadoexame;

import com.ufes.prontuario.dto.arquivo.ArquivoDTO;
import com.ufes.prontuario.dto.exame.ExameDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResultadoExameDTO {

    private Long id;
    private String descricao;
    private ExameDTO exame;
    private ArquivoDTO arquivo;
}
