package com.ufes.prontuario.dto.agenda;

import com.ufes.prontuario.model.Agenda;

public class AgendaConverter {

    public static Agenda toEntity(AgendaCadastroDTO agendaCadastroDTO) {

        var agenda = new Agenda();
        agenda.setDescricao(agendaCadastroDTO.getDescricao());
        agenda.setDataAgendamento(agendaCadastroDTO.getDataAgendamento());

        return agenda;
    }

    public static AgendaDTO toDTO(Agenda agenda) {
        return AgendaDTO.builder()
                .id(agenda.getId())
                .descricao(agenda.getDescricao())
                .data(agenda.getDataAgendamento())
                .build();
    }
}
