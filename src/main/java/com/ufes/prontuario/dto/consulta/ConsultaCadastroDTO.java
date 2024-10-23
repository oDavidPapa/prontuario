package com.ufes.prontuario.dto.consulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConsultaCadastroDTO {

    private String anamnese;
    private String tipo;
    private Long idMedico;
    private Long idPaciente;
    private Long idConsulta;
    private Long idAgendaConsulta;
}
