package com.ufes.prontuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Agenda {

    @Id
    @SequenceGenerator(name = "seq_agenda", sequenceName = "seq_agenda", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda")
    private Long id;

    @Column
    private LocalDateTime dataAgendamento;

    @Column
    private String descricao;
}
