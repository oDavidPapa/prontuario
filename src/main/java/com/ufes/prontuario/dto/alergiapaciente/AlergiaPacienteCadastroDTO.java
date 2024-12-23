package com.ufes.prontuario.dto.alergiapaciente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AlergiaPacienteCadastroDTO {

    private String descricao;
    private Long idPaciente;
}
