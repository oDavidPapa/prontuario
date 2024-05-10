package com.ufes.prontuario.dto.agenda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgendaDTO {

    private Long id;
    private String descricao;
    private LocalDate data;
}
