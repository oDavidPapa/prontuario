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
@Table(name = "doenca_diagnostico")
public class DoencaDiagnostico {

    @Id
    @SequenceGenerator(name = "seq_doenca_diagnostico", sequenceName = "seq_doenca_diagnostico", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_doenca_diagnostico")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_doenca")
    private Doenca doenca;

    @OneToOne
    @JoinColumn(name = "id_diagnostico")
    private Diagnostico diagnostico;
}
