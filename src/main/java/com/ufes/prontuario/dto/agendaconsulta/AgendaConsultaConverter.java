package com.ufes.prontuario.dto.agendaconsulta;

import com.ufes.prontuario.dto.agenda.AgendaConverter;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.model.AgendaConsulta;

public class AgendaConsultaConverter {

    public static AgendaConsulta toEntity(AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return new AgendaConsulta();
    }

    public static AgendaConsultaDTO toDTO(AgendaConsulta agendaConsulta) {
        return AgendaConsultaDTO.builder()
                .agenda(AgendaConverter.toDTO(agendaConsulta.getAgenda()))
                .medico(MedicoConverter.toDTO(agendaConsulta.getMedico()))
                .paciente(PacienteConverter.toDTO(agendaConsulta.getPaciente()))
                .build();
    }
}
