package com.ufes.prontuario.dto.consulta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConsultaCadastroDTO {

    private String motivo;
    private LocalDate data;
    private String tipo;
    private Long idMedico;
    private Long idPaciente;
    private Long idConsulta;
    private Long idAgendaConsulta;
}
