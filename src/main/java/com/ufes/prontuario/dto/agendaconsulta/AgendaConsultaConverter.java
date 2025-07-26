package com.ufes.prontuario.dto.agendaconsulta;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.agenda.AgendaConverter;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.model.AgendaConsulta;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AgendaConsultaConverter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");


    public static AgendaConsulta toEntity(AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        return AgendaConsulta.builder()
                .auditoria(new Auditoria())
                .build();
    }

    public static AgendaConsultaDTO toDTO(AgendaConsulta agendaConsulta) {
        LocalDateTime dataAgendamento = agendaConsulta.getAgenda().getDataAgendamento();

        String dataConsultaFormatada = dataAgendamento != null
                ? dataAgendamento.format(FORMATTER)
                : null;

        return AgendaConsultaDTO.builder()
                .id(agendaConsulta.getId())
                .agenda(AgendaConverter.toDTO(agendaConsulta.getAgenda()))
                .medico(MedicoConverter.toDTO(agendaConsulta.getMedico()))
                .paciente(PacienteConverter.toDTO(agendaConsulta.getPaciente()))
                .tipoConsultaDescricao(Optional.ofNullable(agendaConsulta.getTipoConsulta()).map(TipoConsultaEnum::getDescricao).orElse(null))
                .tipoConsulta(Optional.ofNullable(agendaConsulta.getTipoConsulta()).map(TipoConsultaEnum::name).orElse(null))
                .descricao(agendaConsulta.getAgenda().getDescricao())
                .dataConsulta(dataConsultaFormatada)
                .build();
    }
}
