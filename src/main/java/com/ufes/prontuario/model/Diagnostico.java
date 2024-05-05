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
public class Diagnostico {

    @Id
    @SequenceGenerator(name = "seq_diagnostico", sequenceName = "seq_diagnostico", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_diagnostico")
    private Long id;

    @Column
    private String descricao;

    @Column
    private String diagnostico;

    @OneToOne
    @JoinColumn(name = "id_consulta")
    private Consulta consulta;
}
