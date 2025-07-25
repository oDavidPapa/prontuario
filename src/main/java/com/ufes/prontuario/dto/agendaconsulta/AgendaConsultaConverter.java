package com.ufes.prontuario.dto.agendaconsulta;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.agenda.AgendaConverter;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.model.AgendaConsulta;

import java.util.Optional;

public class AgendaConsultaConverter {

    public static AgendaConsulta toEntity(AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return AgendaConsulta.builder()
                .auditoria(new Auditoria())
                .build();
    }

    public static AgendaConsultaDTO toDTO(AgendaConsulta agendaConsulta) {
        return AgendaConsultaDTO.builder()
                .agenda(AgendaConverter.toDTO(agendaConsulta.getAgenda()))
                .medico(MedicoConverter.toDTO(agendaConsulta.getMedico()))
                .paciente(PacienteConverter.toDTO(agendaConsulta.getPaciente()))
                .tipoConsultaDescricao(Optional.ofNullable(agendaConsulta.getTipoConsulta()).map(TipoConsultaEnum::getDescricao).orElse(null))
                .tipoConsulta(Optional.ofNullable(agendaConsulta.getTipoConsulta()).map(TipoConsultaEnum::name).orElse(null))
                .build();
    }
}
