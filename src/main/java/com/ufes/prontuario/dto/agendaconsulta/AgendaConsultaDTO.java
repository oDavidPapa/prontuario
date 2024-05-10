package com.ufes.prontuario.dto.agendaconsulta;

import com.ufes.prontuario.dto.agenda.AgendaDTO;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgendaConsultaDTO {

    private AgendaDTO agenda;
    private PacienteDTO paciente;
    private MedicoDTO medico;
}
