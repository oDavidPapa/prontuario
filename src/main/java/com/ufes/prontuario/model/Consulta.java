package com.ufes.prontuario.model;

import com.ufes.prontuario.enums.TipoConsultaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Consulta {

    @Id
    @SequenceGenerator(name = "seq_consulta", sequenceName = "seq_consulta", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_consulta")
    private Long id;

    @Column
    private String motivo;

    @Column
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private TipoConsultaEnum tipo;

    @OneToOne
    @JoinColumn(name = "id_medico")
    private Medico medico;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;

    @OneToOne
    @JoinColumn(name = "id_agenda_consulta")
    private AgendaConsulta agendaConsulta;
}
