package com.ufes.prontuario.dto.doenca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CidCadastroDTO {

    private String descricao;
    private String codigo;
    private Long idDiagnostico;
}
