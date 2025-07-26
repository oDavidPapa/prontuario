package com.ufes.prontuario.dto.agendaconsulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgendaConsultaCadastroDTO {

    private Long idPaciente;
    private Long idMedico;
    private String tipoConsulta;

    private LocalDateTime dataAgendamento;
    private String descricao;

}
