package com.ufes.prontuario.dto.consulta;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaConverter;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.model.Consulta;

import java.time.LocalDateTime;
import java.util.Optional;

public class ConsultaConverter {

    public static Consulta toEntity(ConsultaCadastroDTO consultaCadastroDTO) {

        var consulta = new Consulta();
        consulta.setData(LocalDateTime.now());
        consulta.setTipo(TipoConsultaEnum.valueOf(consultaCadastroDTO.getTipo()));
        consulta.setAnamnese(consultaCadastroDTO.getAnamnese());
        consulta.setAuditoria(new Auditoria());

        return consulta;
    }

    public static ConsultaDTO toDTO(Consulta consulta) {
        return ConsultaDTO.builder()
                .id(consulta.getId())
                .data(consulta.getData())
                .anamnese(consulta.getAnamnese())
                .tipo(consulta.getTipo().getDescricao())
                .tipoEnum(consulta.getTipo().name())
                .agendaConsulta(Optional.ofNullable(consulta.getAgendaConsulta())
                        .map(AgendaConsultaConverter::toDTO)
                        .orElse(null))
                .medico(MedicoConverter.toDTO(consulta.getMedico()))
                .paciente(PacienteConverter.toDTO(consulta.getPaciente()))
                .build();
    }
}
