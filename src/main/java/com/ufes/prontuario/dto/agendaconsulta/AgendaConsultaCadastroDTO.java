package com.ufes.prontuario.dto.agendaconsulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgendaConsultaCadastroDTO {

    private Long idPaciente;
    private Long idMedico;
    private Long idAgenda;
}
