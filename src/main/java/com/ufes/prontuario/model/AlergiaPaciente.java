package com.ufes.prontuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "alergia_paciente")
public class AlergiaPaciente {

    @Id
    @SequenceGenerator(name = "seq_alergia_paciente", sequenceName = "seq_alergia_paciente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_alergia_paciente")
    private Long id;

    @Column
    private String descricao;

    @OneToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
