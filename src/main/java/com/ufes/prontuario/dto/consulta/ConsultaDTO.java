package com.ufes.prontuario.dto.consulta;

import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaDTO;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ConsultaDTO {

    private Long id;
    private String motivo;
    private LocalDate data;
    private String tipo;
    private MedicoDTO medico;
    private PacienteDTO paciente;
    private ConsultaDTO consulta;
    private AgendaConsultaDTO agendaConsulta;
}
